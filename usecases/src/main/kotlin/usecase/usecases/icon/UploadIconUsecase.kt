package usecase.usecases.icon

import io.ktor.http.content.*
import usecase.model.IconResultResponse
import usecase.model.UploadIconRequest
import usecase.usecases.AuthUsecase
import java.io.File

class UploadIconUsecase (
    private val uploadDir: String
) : AuthUsecase<UploadIconRequest, IconResultResponse>() {
    override suspend fun executor(request: UploadIconRequest): IconResultResponse {
        val ext = File(request.file.originalFileName!!).extension
        val file = File(uploadDir + request.iconName + ext)

        request.file.streamProvider().use { its -> file.outputStream().buffered().use { its.copyTo(it) } }
        request.file.dispose()

        return IconResultResponse("아이콘이 성공적으로 등록되었습니다.")
    }
}