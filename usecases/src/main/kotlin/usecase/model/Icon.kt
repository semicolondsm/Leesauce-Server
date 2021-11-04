package usecase.model

import io.ktor.http.content.*

open class DefaultResponse (
    val message: String
)

class UploadIconRequest (
    val iconName: String,
    val file: PartData.FileItem
)

class IconResultResponse(message: String) : DefaultResponse(message)