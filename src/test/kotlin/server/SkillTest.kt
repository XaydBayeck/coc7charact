package server

import kotlinx.serialization.json.Json
import kotlinx.serialization.parseList
import org.junit.Test
import java.io.File

class SkillTest {
    @Test
    fun testSkill() {
        val jsonPath = "src/resource/webroot/json/skill.json"
        var jsonList = ""
        File(jsonPath).forEachLine(action = {
            jsonList += it
            jsonList += "\n"
        })
        val skillsData = Json.parseList<SkillData>(jsonList)
        val skills = ArrayList<Skill>(0)
        for (skillData in skillsData) {
            val skill = Skill(skillData)
            skills.add(skill)
        }

        for (skill in skills) {
            val str = skill.toString()
            println(str)
        }
    }
}
