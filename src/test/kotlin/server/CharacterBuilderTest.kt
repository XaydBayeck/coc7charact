package server

import io.vertx.core.json.JsonObject
import kotlinx.serialization.UnstableDefault
import org.junit.Test

class CharacterBuilderTest {
    val Attributes = """
        { 
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
    """.trimIndent()

    val Information = """
        {
            "plName":"sid",
            "pcName":"sid",
            "sex":"man",
            "job":"student",
            "age":24,
            "center":"2000s",
            "addr":"ChengDu",
            "home":"GuiZhou"
        }
    """.trimIndent()

    @UnstableDefault
    @Test
    fun characterBuilderTest() {
        val characterBuilder = CharacterBuilder()

        val Attribute = JsonObject(this.Attributes)
        val Information = JsonObject(this.Information)

        characterBuilder.addAttribute(Attribute)
        characterBuilder.information = Information

        val character = characterBuilder.makeCharacter()

        println(character.toString())

    }
}