package config

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
        get("/resource") {
            call.respondText("Hello World!")
        }
    }
}
