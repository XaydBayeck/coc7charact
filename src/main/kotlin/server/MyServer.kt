package server

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.FaviconHandler
import io.vertx.ext.web.handler.StaticHandler
import java.io.File

class MyServer(
        private val port: Int,
        private val _404pageName: String,
        private val webPath: String,
        private val faviconPath: String
) : AbstractVerticle() {

    private val kinds = arrayListOf("fonts", "stylesheet")

    override fun start() {

        vertx = Vertx.vertx()

        val server = vertx.createHttpServer()

        // web 路由器
        val router = Router.router(vertx)

        // 创建 BodyHandler
        router.route().handler(BodyHandler.create())

        // favicon.ico handler
        router.route().handler(
                FaviconHandler.create(faviconPath)
        )

        router.route(HttpMethod.GET, "/web/:kind/:path").handler { context ->
            val path = context.request().getParam("path")
            val kind = context.request().getParam("kind")
            println("route-Web-turn $kind/$path")
            context.reroute("/$kind/$path")
        }

        // web handler
        router.route(HttpMethod.GET, "/web/:path").handler { context ->
            val response = context.response()
            val path = context.request().getParam("path")
            println("route-Web $path")
            response.putHeader("content-type", "text/plain")
            context.reroute(fileTest(path))
        }

        router.route(HttpMethod.GET, "/javascripts/:path").handler { context ->
            val response = context.response()
            val path = context.request().getParam("path")
            println("route-Style $path")
            response.putHeader("content-type", "text/plain")
            context.reroute(fileTest("/javascripts/$path"))
        }

        router.route(HttpMethod.GET, "/stylesheet/:path").handler { context ->
            val response = context.response()
            val path = context.request().getParam("path")
            println("route-Style $path")
            response.putHeader("content-type", "text/plain")
            context.reroute(fileTest("/stylesheet/$path"))
        }

        router.route(HttpMethod.GET, "/fonts/:path").handler { context ->
            val response = context.response()
            val path = context.request().getParam("path")
            println("route-Fonts $path")
            response.putHeader("content-type", "text/plain")
            context.reroute(fileTest("/fonts/$path"))
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

    private fun fileTest(path: String): String {
        val file = File("$webPath/$path")
        return if (!file.exists()) {
            println("$webPath/$path is not exists!")
            "/not_found"
        } else {
            "/static/$path"
        }
    }
}

fun main() {
    val projectSettings=ProjectSettings("src/resource/server.properties")
    val server = MyServer(
            projectSettings.port,
            projectSettings.notfound,
            projectSettings.webPath,
            projectSettings.faviconPath
    )

    server.start()
}
