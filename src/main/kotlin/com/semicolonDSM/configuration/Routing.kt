package com.semicolonDSM.configuration

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.serialization.*

fun Application.configureRouting() {
    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
