package server

import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.*
import org.junit.Test

class CharacterTest {
    @UnstableDefault
    @Test
    fun characterTest() {
        val jobJson = """
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
        val job = Json.parse(Job.serializer(), jobJson)

        val chInform = CharacterData.ChInform("sid", "sid", "man", job, 24, "2000s", "ChengDu", "GuiZhou")
        val chAttr = CharacterData.ChAttr(70, 65, 75, 65, 70, 70, 70, 70, 7)

        val hp = (chAttr.siz + chAttr.con) / 10
        val mp = chAttr.pow / 5
        val san = chAttr.pow

        val attr = CharacterData.Attr(hp, hp, mp, mp, san, 75)

        val damageAndBody = CharacterData.DamageAndBody(CharacterData.DamageAndBody.DamagePlusValue(0, 1, 4), 1)

        val characterise = CharacterData(chInform, chAttr, attr, damageAndBody)

        // serializing objects
        val jsonData = Json.stringify(CharacterData.serializer(), characterise)
        // serializing lists
        val jsonList = Json.stringify(CharacterData.serializer().list, listOf(characterise))

        println(jsonData)
        println(jsonList)

        val characterJson = """
            {
                "chInfo":{
                    "plName":"sid",
                    "pcName":"sid",
                    "sex":"man",
                    "job":$jobJson,
                    "age":24,
                    "center":"2000s",
                    "addr":"ChengDu",
                    "home":"GuiZhou"
                },
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
                },
                "damageAndBody":{
                    "damagePlusValue":{
                        "fixed":0,
                        "diceNum":1,
                        "diceFace":4
                    },
                    "body":1
                }
            }
        """
        val characterData = Json.parse(CharacterData.serializer(), characterJson)
        println(characterData)

        val character = Character(characterData)
        println(character.toString())
    }
}