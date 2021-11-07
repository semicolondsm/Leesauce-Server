package config

import exception.FileNameIsNeededException
import exception.FileNotFoundException
import exception.PasswordNeedException
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.serialization.*
import org.koin.ktor.ext.inject
import usecase.model.UploadIconRequest
import usecase.usecases.UsecaseType
import usecase.usecases.icon.UploadIconUsecase

fun getAuthorizationHeader(request: ApplicationRequest): String {
    return request.headers["Authorization"] ?: throw PasswordNeedException()
}

fun Application.configureRouting() {
    // usecase dependencies
    val uploadIconUsecase: UploadIconUsecase by inject()

    install(ContentNegotiation) {
        json()
    }

    routing {
        post("/icon") {
            // checks token
            val password = getAuthorizationHeader(call.request)

            val multiMap = call.receiveMultipart().readAllParts()
                .associateBy { it.name }.toMap()

            val nameItem: PartData.FormItem? by multiMap
            val iconItem: PartData.FileItem? by multiMap

            val name = nameItem?.value ?: throw FileNameIsNeededException()
            val icon = iconItem ?: throw FileNotFoundException()

            uploadIconUsecase.invoke(UploadIconRequest(name, icon), password)
        }
    }
}