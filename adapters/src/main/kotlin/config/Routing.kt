package config

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.serialization.*

fun getAuthorizationHeader(request: ApplicationRequest): String {
    return request.headers["Authorization"] ?: throw PasswordNeedException()
}

fun Application.configureRouting() {

    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/icon") {
            getAuthorizationHeader(call.request)
        }
    }
}

// Handler exceptions
class PasswordNeedException : Exception("this api requires password to use.")