package config

import exception.FileNameIsNeededException
import exception.FileNotFoundException
import exception.NameIsNeededException
import exception.PasswordNeedException
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.serialization.*
import org.koin.ktor.ext.inject
import usecase.model.*
import usecase.usecases.icon.DeleteIconUsecase
import usecase.usecases.icon.GetIconListUsecase
import usecase.usecases.icon.UploadIconUsecase
import usecase.usecases.logo.DeleteLogoUsecase
import usecase.usecases.logo.GetLogoListUsecase
import usecase.usecases.logo.UploadLogoUsecase
import usecase.usecases.search.SearchUsecase
import usecase.usecases.source.DeleteSourceUsecase
import usecase.usecases.source.GetSourceListUsecase
import usecase.usecases.source.UploadSourceUsecase

fun Application.configureRouting() {
    // usecase dependencies
    val searchUsecase: SearchUsecase by inject()
    val getIconUsecase: GetIconListUsecase by inject()
    val getLogoUsecase: GetLogoListUsecase by inject()
    val getSourceUsecase: GetSourceListUsecase by inject()
    val uploadIconUsecase: UploadIconUsecase by inject()
    val uploadLogoUsecase: UploadLogoUsecase by inject()
    val uploadSourceUsecase: UploadSourceUsecase by inject()
    val deleteIconUsecase: DeleteIconUsecase by inject()
    val deleteLogoUsecase: DeleteLogoUsecase by inject()
    val deleteSourceUsecase: DeleteSourceUsecase by inject()

    install(ContentNegotiation) {
        jackson()
    }

    routing {
        get("/leesauce") {
            val name = call.request.queryParameters["name"]

            call.respond(searchUsecase(SearchRequest(name)))
        }

        get("/icon") {
            call.respond(getIconUsecase())
        }

        get("/logo") {
            call.respond(getLogoUsecase())
        }

        get("/source") {
            call.respond(getSourceUsecase())
        }

        post("/icon") {
            // checks password
            val password = getAuthorizationHeader(call.request)
            val (name, icon) = getNameAndIconFromFile(call)

            call.respond(HttpStatusCode.Created, uploadIconUsecase(UploadIconRequest(name, icon), password))
        }

        post("/logo") {
            val password = getAuthorizationHeader(call.request)
            val (name, logo) = getNameAndIconFromFile(call)

            call.respond(HttpStatusCode.Created, uploadLogoUsecase(UploadLogoRequest(name, logo), password))
        }

        post("/source") {
            val password = getAuthorizationHeader(call.request)
            val (name, source) = getNameAndIconFromFile(call)

            call.respond(HttpStatusCode.Created, uploadSourceUsecase(UploadSourceRequest(name, source), password))
        }

        delete("/icon/{name}") {
            val password = getAuthorizationHeader(call.request)
            val name = call.parameters["name"] ?: throw NameIsNeededException()

            call.respond(deleteIconUsecase(DeleteIconRequest(name), password))
        }

        delete("/logo/{name}") {
            val password = getAuthorizationHeader(call.request)
            val name = call.parameters["name"] ?: throw NameIsNeededException()

            call.respond(deleteLogoUsecase(DeleteLogoRequest(name), password))
        }

        delete("/source/{name}") {
            val password = getAuthorizationHeader(call.request)
            val name = call.parameters["name"] ?: throw NameIsNeededException()

            call.respond(deleteSourceUsecase(DeleteSourceRequest(name), password))
        }
    }
}

fun getAuthorizationHeader(request: ApplicationRequest): String {
    return request.headers["Authorization"] ?: throw PasswordNeedException()
}

suspend inline fun getNameAndIconFromFile(call: ApplicationCall): Pair<String, PartData.FileItem> {
    val multiMap = call.receiveMultipart().readAllParts()
        .associateBy { it.name }.toMap()

    val name: PartData = multiMap["name"] ?: throw FileNameIsNeededException()
    val file: PartData = multiMap["file"] ?: throw FileNotFoundException()

    val nameItem = (name as PartData.FormItem).value
    val fileItem = file as PartData.FileItem

    return nameItem to fileItem
}