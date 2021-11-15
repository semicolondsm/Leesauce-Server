package usecase.model

import io.ktor.http.content.*

class UploadSourceRequest (
    val sourceName: String,
    val file: PartData.FileItem
)

class GetSourceListResponse (
    val sources: List<Source>
) {
    class Source (
        val name: String,
        val url: String
    )
}

class DeleteSourceRequest (
    val name: String
)