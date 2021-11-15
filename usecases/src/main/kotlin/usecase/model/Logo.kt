package usecase.model

import io.ktor.http.content.*

class UploadLogoRequest (
    val logoName: String,
    val file: PartData.FileItem
)

class GetLogoListResponse (
    val logos: List<Logo>
) {
    class Logo (
        val name: String,
        val url: String
    )
}

class DeleteLogoRequest (
    val name: String
)