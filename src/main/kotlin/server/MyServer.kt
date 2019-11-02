package server

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.FaviconHandler
import io.vertx.ext.web.handler.StaticHandler
import java.io.File

class MyServer(
        private val port: Int,
        private val _404pageName: String,
        private val webPath: String,
        private val faviconPath: String
) : AbstractVerticle() {

    override fun start() {

        val vertx = Vertx.vertx()

        val server = vertx.createHttpServer()

        // web 路由器
        val router = Router.router(vertx)

        // favicon.ico handler
        router.route().handler(
                FaviconHandler.create(faviconPath)
        )

        // web handler
        router.route(HttpMethod.GET, "/web/:path").handler { context ->
            val response = context.response()
            val path = context.request().getParam("path")
            response.putHeader("content-type", "text/plain")
            val file = File("$webPath/$path")
            if (file.exists()) {
                context.reroute("/static/$path")
            } else {
                println("src/web/$path is not exists!")
                context.reroute("/not_found")
            }
        }

        // static handler
        router.route("/static/*").handler(
                StaticHandler
                        .create()
                        .setWebRoot(webPath)
                        .setFilesReadOnly(true)
        )

        // not found handler
        router.route().pathRegex("/not_found|.*").handler { context ->
            context.response()
                    .putHeader("content-type", "text/html")
                    .statusCode = 404
            context.reroute("/static/$_404pageName")
        }

        server.requestHandler(router).listen(port)
        println("server is starting in $port")
    }
}

fun main() {
    val projectSettings = ProjectSettings()
    val server = MyServer(
            projectSettings.port,
            projectSettings.notfound,
            projectSettings.webPath,
            projectSettings.faviconPath
    )

    server.start()
}