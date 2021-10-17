package com.semicolonDSM

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.semicolonDSM.configuration.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureHTTP()
    }.start(wait = true)
}
