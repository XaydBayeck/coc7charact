package server

import io.vertx.core.json.JsonObject
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

class CharacterBuilder {
    var attribute = JsonObject()
    var attr=JsonObject()
    var information = JsonObject()
    var job=JsonObject()
    var damageAndBody=JsonObject()

    fun addAttribute(_Attr:JsonObject){
        this.attribute=_Attr.getJsonObject("chAttr")
        this.attr=_Attr.getJsonObject("attr")
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
}