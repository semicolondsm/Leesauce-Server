package server

import config.config
import config.configureError
import config.configureHTTP
import config.configureRouting
import di.configureKoin
import io.ktor.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) = EngineMain.main(args)

@Suppress("unused")
fun Application.module(testing: Boolean = false) {
    val config = config()

    configureHTTP()
    configureKoin(config)
    configureRouting()
    configureError()
}