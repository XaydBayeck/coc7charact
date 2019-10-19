package server

import kotlin.test.assertEquals
import org.junit.Test

class ServerTest {
    @Test
    fun testServer() {
        val handler = GetHandler()

        val testString = """<!DOCTYPE html><html lang="zh_cn"><head>    <meta charset="UTF-8">    <title>test</title></head><body>    <h1>Succesful!</h1></body></html>"""

        assertEquals(testString, handler.doGet("/test.html"))
    }
}
