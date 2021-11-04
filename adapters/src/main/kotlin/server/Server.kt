package server

import config.config
import config.configureHTTP
import config.configureRouting
import di.configureKoin
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        val config = config()

        configureRouting()
        configureHTTP()
        configureKoin(config)
    }.start(wait = true)
}