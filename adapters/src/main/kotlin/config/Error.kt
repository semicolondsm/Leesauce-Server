package config

import domain.BusinessException
import exception.ValidationException
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

class Error (
    val message: String
)

fun Application.configureError() {
    install(StatusPages) {
        exception<Throwable> { cause ->
            call.respond(HttpStatusCode.Companion.InternalServerError, Error(cause.message))
        }

        exception<ValidationException> { cause ->
            call.respond(HttpStatusCode.BadRequest, Error(cause.message))
        }

        exception<BusinessException> { cause ->
            call.respond(HttpStatusCode.fromValue(cause.status), Error(cause.message))
        }
    }
}