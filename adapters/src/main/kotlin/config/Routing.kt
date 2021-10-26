package config

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.serialization.*
import org.koin.ktor.ext.inject
import usecase.usecases.icon.UploadIconUsecase

fun getAuthorizationHeader(request: ApplicationRequest): String {
    return request.headers["Authorization"] ?: throw PasswordNeedException()
}

fun Application.configureRouting() {

    // usecase dependencies
    val uploadIconUsecase by inject<UploadIconUsecase>()

    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/icon") {
            // checks token
            getAuthorizationHeader(call.request)

            val multipart = call.receiveMultipart()

        }
    }
}

// Handler exceptions
class PasswordNeedException : Exception("this api requires password to use.")