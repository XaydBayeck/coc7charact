package server

import io.vertx.core.AbstractVerticle
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.FaviconHandler
import io.vertx.ext.web.handler.StaticHandler

class MyServer(
        private val port: Int,
        private val _404pageName: String,
        private val webPath: String,
        private val faviconPath: String
) : AbstractVerticle() {

    private val characterBuilder = CharacterBuilder()

    override fun start() {
        val vert = Vertx.vertx()

        val router = createRouter(vert)

        val server = vert.createHttpServer()
        server.requestHandler(router).listen(port)
        println("Server is starting in port $port")
    }

    private fun createRouter(vert: Vertx) = Router.router(vert).apply {
        route().handler(BodyHandler.create())
        route().handler(FaviconHandler.create(faviconPath))
        post("/post/character/:part").handler(characterPostHandler)
        get("/").handler(myRootHandler)
        get("/get/character/:part").handler(characterGetHandler)
        route("/static/*").handler(StaticHandler.create().setWebRoot(webPath))
        errorHandler(404, myFailureHandler)
    }

    // Handlers
    private val myRootHandler = Handler<RoutingContext> { req ->
        req.response().end("welcome!")
    }

    private val myFailureHandler = Handler<RoutingContext> { req ->
        req.response()
                .putHeader("content-type", "text/html")
                .statusCode = 404
        req.reroute("/static/$_404pageName")
    }

    private val characterPostHandler = Handler<RoutingContext> { req ->
        val part = req.pathParam("part")
        println("POST the character $part")
        val jsonObj = req.bodyAsJson
        when (part) {
            "Attribute" -> {
                this.characterBuilder.addAttribute(jsonObj)
            }
            "Information" -> {
                this.characterBuilder.information = jsonObj
            }
            "Job" -> {
                this.characterBuilder.job = jsonObj
            }
            "damageAndBody" -> {
                this.characterBuilder.damageAndBody = jsonObj
            }
        }
        req.response().end(jsonObj.encode())
    }

    private val characterGetHandler = Handler<RoutingContext> { req ->
        val part = req.pathParam("part")
        println("GET the character $part")
        val jsonObj = when (part) {
            "Attribute" -> {
                val attribute = this.characterBuilder.attribute
                val attr = this.characterBuilder.attr
                val json = JsonObject().apply {
                    put("chAttr",attribute)
                    put("attr",attr)
                }
                json
            }
            "Information" -> {
                this.characterBuilder.information
            }
            "Job" -> {
                this.characterBuilder.job
            }
            "damageAndBody" -> {
                this.characterBuilder.damageAndBody
            }
            else -> {
                null
            }
        }
        if (jsonObj == null) {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .statusCode = 404
            req.response().end("Character's $part is not found!")
        } else {
            req.response().end(jsonObj.encode())
        }
    }
}

fun main() {
    val projectSettings = ProjectSettings("src/resource/server.properties")
    val server = MyServer(
            projectSettings.port,
            projectSettings.notfound,
            projectSettings.webPath,
            projectSettings.faviconPath
    )

    server.start()
}
