package usecase.model

import io.ktor.http.content.*

class UploadIconRequest (
    val iconName: String,
    val file: PartData.FileItem
)

class GetIconListResponse (
    val icons: List<Icon>
) {
    class Icon (
        val name: String,
        val url: String
    )
}

class DeleteIconRequest (
    val name: String
)