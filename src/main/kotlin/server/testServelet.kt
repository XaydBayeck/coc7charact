package server

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import java.io.File
import java.io.InputStreamReader
import java.net.InetSocketAddress
import kotlin.io.forEachLine as forEachLine

fun main(args: Array<String>) {
    val server = HttpServer.create(InetSocketAddress(8989), 100)
    server.createContext("/", GetHandler())
    server.start()
    println("Server is starting......")
}

class GetHandler : HttpHandler {
    private val notfound = "<h1>404 Not Found</h1>No context found for request"

    @UnstableDefault
    override fun handle(exchange: HttpExchange) {

        val method = exchange.requestMethod
        val response = exchange.responseBody
        val url = exchange.requestURI.path
        val content: String
        val request = exchange.requestBody
        val isr = InputStreamReader(request, "UTF-8")
        content = when (method) {
            "GET" -> doGet(url)
            "POST" -> doPost(isr)
            else -> notfound
        }

        val contentBin = content.toByteArray()

        exchange.responseHeaders.set("Content-Type", "charset=utf-8")

        if (notfound != content) {
            exchange.sendResponseHeaders(200, contentBin.size.toLong())
            response.write(contentBin)
        } else {
            exchange.sendResponseHeaders(404, contentBin.size.toLong())
            response.write(contentBin)
        }

        response.flush()
        response.close()
    }

    fun doGet(url: String): String {

        val path = "src/web$url"
        println(path)
        var tmp = ""
        File(path).forEachLine(action = { s ->
            s.forEach { tmp += it }
            tmp += "\n"
        })
        return tmp
    }

    @UnstableDefault
    fun doPost(isr: InputStreamReader): String {
        var returns = ""
        var tmp = isr.read()
        while (tmp != -1) {
            returns += tmp.toChar()
            tmp = isr.read()
        }
        println(returns)

        val characterData = Json.parse(CharacterData.serializer(), returns)
        val character = Character(characterData)

        return character.toHtml()
    }
}
