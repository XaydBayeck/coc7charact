package server

import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.parseList
import org.junit.Test
import java.io.File

class JobTest {
    @ImplicitReflectionSerializer
    @Test
    fun jobTest() {

        val jsonPath = "src/resource/joblist.json"
        var jsonList = ""
        File(jsonPath).forEachLine(action = {
            jsonList += it
            jsonList += "\n"
        })
        val jobList = Json.parseList<Job>(jsonList)

        for (job in jobList) {
            val str = job.toString()
            println(str)
        }
    }
}