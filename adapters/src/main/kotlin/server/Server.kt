package server

import config.configureHTTP
import config.configureRouting
import di.configureKoin
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureHTTP()
        configureKoin()
    }.start(wait = true)
}