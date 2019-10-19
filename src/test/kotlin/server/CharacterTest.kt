package server

import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.*
import org.junit.Test

class CharacterTest {
    @UnstableDefault
    @Test
    fun characterTest() {
        val chInform = CharacterData.ChInform("sid", "sid", "man", "student", 24, "2000s", "ChengDu", "GuiZhou")
        val chAttr = CharacterData.ChAttr(70, 65, 75, 65, 70, 70, 70, 70)

        val hp = (chAttr.siz + chAttr.con) / 10
        val mp = chAttr.pow / 5
        val san = chAttr.pow

        val attr = CharacterData.Attr(hp, hp, mp, mp, san, 75)

        val characterise = CharacterData(chInform, chAttr, attr)

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
                    "job":"student",
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
                    "pow":70
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
        """
        val characterData = Json.parse(CharacterData.serializer(), characterJson)
        println(characterData)

        val character = Character(characterData)
        println(character.toString())

        val characterJson2="""
           {"chInfo":{"plName":"sid","pcName":"sid","sex":"男","job":"","age":24,"center":"现代","addr":"ChengDu","home":"GuiZhou"},"chAttr":{"str":67,"con":87,"siz":56,"dex":76,"app":54,"int":65,"pow":64,"edu":76},"attr":{"hp":14,"hpm":14,"mp":12,"mpm":12,"san":64,"luck":65}} 
        """
        val characterData2=Json.parse(CharacterData.serializer(),characterJson2)
        println(characterData2)
        val character2=Character(characterData2)
        println(character2.toString())
    }
}