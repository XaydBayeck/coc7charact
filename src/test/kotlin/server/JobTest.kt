package server

import kotlinx.serialization.json.Json
import kotlinx.serialization.parseList
import org.junit.Test
import java.io.File

class JobTest {
    @Test
    fun jobTest() {

        val jsonPath = "src/resource/webroot/json/joblist.json"
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
