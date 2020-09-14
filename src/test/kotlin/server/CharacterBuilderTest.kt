package server

import io.vertx.core.json.JsonObject
import org.junit.Test

class CharacterBuilderTest {
    private val jobJson = """
  {
    "name": "自定义",
    "description": "与KP商量自定义符合世界观背景的职业",
    "profsPoint": {
      "skill1": "教育",
      "mag1": 4,
      "skill2": "力量",
      "orSkill2": "力量",
      "mag2": 0
    },
    "belief": {
      "max": 0,
      "min": 100
    },
    "relationShip": "无",
    "proSkill": [
      "暂无"
    ],
    "kind": "普通职业",
    "skillPoint": "教育 X 4"
  }
        """.trimIndent()

    private val attributes = """
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
            },
            "attr":{
                "hp":14,
                "hpm":14,
                "mp":14,
                "mpm":14,
                "san":70,
                "luck":75
            }
        }
    """.trimIndent()

    private val information = """
        {
            "plName":"sid",
            "pcName":"sid",
            "sex":"man",
            "job":$jobJson,
            "age":24,
            "center":"2000s",
            "addr":"ChengDu",
            "home":"GuiZhou"
        }
    """.trimIndent()

    private val damageAndBody = """
        {
            "damagePlusValue":{
                "fixed":0,
                "diceNum":1,
                "diceFace":4
            },
            "body":1
        }
    """.trimIndent()

    @Test
    fun characterBuilderTest() {
        val characterBuilder = CharacterBuilder()

        val attribute = JsonObject(this.attributes)
        val information = JsonObject(this.information)
        val damageAndBody = JsonObject(this.damageAndBody)

        characterBuilder.addAttribute(attribute)
        characterBuilder.information = information
        characterBuilder.damageAndBody = damageAndBody

        val character = characterBuilder.makeCharacter()

        println(character.toString())

    }
}