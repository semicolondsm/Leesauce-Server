package usecase.usecases.source

import domain.SourceAlreadyExistsException
import domain.entity.Resource
import domain.entity.ResourceType
import domain.repository.ResourceRepository
import usecase.Environment
import usecase.model.DefaultResponse
import usecase.model.UploadSourceRequest
import usecase.usecases.AuthUsecase
import usecase.usecases.common.writeFile
import java.io.File

class UploadSourceUsecase (
    private val repository: ResourceRepository,
    private val environment: Environment
) : AuthUsecase<UploadSourceRequest, DefaultResponse>() {
    override suspend fun executor(request: UploadSourceRequest): DefaultResponse {

        val file = request.file
        val sourceName = request.sourceName
        val uploadDir = environment.uploadDir

        if (repository.findByNameAndType(sourceName, ResourceType.SOUR) != null)
            throw SourceAlreadyExistsException()

        val ext = File(file.originalFileName!!).extension
        val pathName = "$uploadDir/source/$sourceName.$ext"

        writeFile(file, pathName)

        repository.create(
            Resource(
                name = sourceName,
                url = "${environment.baseUrl}/source/$sourceName.$ext",
                type = ResourceType.SOUR
            )
        )

        return DefaultResponse("소스가 성공적으로 등록되었습니다.")
    }
}