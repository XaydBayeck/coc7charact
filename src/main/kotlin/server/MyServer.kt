package server

import io.vertx.core.AbstractVerticle
import io.vertx.core.Handler
import io.vertx.core.Vertx
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

    private val kinds = arrayListOf("fonts", "stylesheet")
    val characterBuilder=CharacterBuilder()

    override fun start() {
        val vert= Vertx.vertx()

        val router = createRouter(vert)

        val server = vert.createHttpServer()
        server.requestHandler(router).listen(port)
        println("Server is starting in port $port")
    }

    private fun createRouter(vert:Vertx) = Router.router(vert).apply {
        route().handler(BodyHandler.create())
        route().handler(FaviconHandler.create(faviconPath))
        post("/post/character/:part").handler(characterPostHandler)
        get("/").handler(myRootHandler)
        route("/static/*").handler(StaticHandler.create().setWebRoot(webPath))
        errorHandler(404,myFailureHandler)
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
        println("POST the Attribute")
        val part = req.pathParam("part")
        val jsonObj = req.bodyAsJson
        when (part) {
            "Attribute" -> {
                this.characterBuilder.addAttribute(jsonObj)
            }
            "Information" -> {
                this.characterBuilder.information=jsonObj
            }
            "Job" -> {
                this.characterBuilder.job=jsonObj
            }
        }
        req.response().end(jsonObj.encode())
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
