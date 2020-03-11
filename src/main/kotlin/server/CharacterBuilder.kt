package server

import io.vertx.core.json.JsonObject
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

class CharacterBuilder {
    var attribute = JsonObject("""
{
    "chAttr":{
        "str":70,
        "con":65,
        "siz":75,
        "dex":65,
        "app":70,
        "edu":70,
        "int":70,
        "pow":70,
        "mov":7
    }
}
    """.trimIndent())
    var attr = JsonObject()
    var information = JsonObject()
    var job = JsonObject("""
{
    "profsPoint": {
      "skill1": "教育",
      "mag1": 4,
      "skill2": "力量",
      "orSkill2": "力量",
      "mag2": 0
    }
}
    """.trimIndent())
    var damageAndBody = JsonObject()

    fun addAttribute(_Attr: JsonObject) {
        this.attribute = _Attr.getJsonObject("chAttr")
        this.attr = _Attr.getJsonObject("attr")
    }

    @UnstableDefault
    fun makeCharacter(): Character {
        val characterJson = StringBuffer("{\n\"chInfo\":")
        characterJson.append(this.information)
        characterJson.append(",\n\"chAttr\":")
        characterJson.append(this.attribute)
        characterJson.append(",\n\"attr\":")
        characterJson.append(this.attr)
        characterJson.append(",\n\"damageAndBody\":")
        characterJson.append(this.damageAndBody)
        characterJson.append("}")
        println(characterJson.toString())
        val characterData = Json.parse(CharacterData.serializer(), characterJson.toString())
        return Character(characterData)
    }

    private fun findAttr(attrName: String): Int {
        return when (attrName) {
            "力量" -> attribute.getJsonObject("chAttr").getInteger("str")
            "体质" -> attribute.getJsonObject("chAttr").getInteger("con")
            "体型" -> attribute.getJsonObject("chAttr").getInteger("siz")
            "敏捷" -> attribute.getJsonObject("chAttr").getInteger("dex")
            "外貌" -> attribute.getJsonObject("chAttr").getInteger("app")
            "教育" -> attribute.getJsonObject("chAttr").getInteger("edu")
            "智力" -> attribute.getJsonObject("chAttr").getInteger("int")
            "意志" -> attribute.getJsonObject("chAttr").getInteger("pow")
            else -> 0
        }
    }

    fun calculateProbesPoint(): Int {
        val profsPoint = job.getJsonObject("profsPoint")
        val mag1 = profsPoint.getInteger("mag1")
        val skill1 = profsPoint.getString("skill1")

        val attrValue1 = findAttr(skill1)

        val mag2 = profsPoint.getInteger("mag2")

        val attrValue2Final =
                if (mag2 != 0) {
                    val skill2 = profsPoint.getString("skill2")
                    val orSkill2 = profsPoint.getString("orSkill2")
                    val attrValue2 = findAttr(skill2)
                    val orAttrValue2 = findAttr(orSkill2)

                    if (attrValue2 > orAttrValue2) {
                        attrValue2 * mag2
                    } else {
                        orAttrValue2 * mag2
                    }
                } else {
                    0
                }

        return attrValue1 * mag1 + attrValue2Final
    }

    fun calculateInterestPoint(): Int {
        return findAttr("智力") * 4
    }
}