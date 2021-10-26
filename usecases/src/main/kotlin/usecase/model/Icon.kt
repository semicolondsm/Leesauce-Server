package usecase.model

import io.ktor.http.content.*

data class UploadIconRequest (
    val iconName: String,
    val file: MultiPartData
)

data class IconResultResponse (
    val message: String
)