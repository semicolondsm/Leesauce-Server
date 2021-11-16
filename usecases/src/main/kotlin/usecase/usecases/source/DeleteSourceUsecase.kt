package usecase.usecases.source

import domain.SourceNotFoundException
import domain.entity.ResourceType
import domain.repository.ResourceRepository
import usecase.Environment
import usecase.model.DefaultResponse
import usecase.model.DeleteSourceRequest
import usecase.usecases.AuthUsecase
import usecase.usecases.common.deleteFile
import usecase.usecases.common.suspendedTx

class DeleteSourceUsecase (
    private val repository: ResourceRepository,
    private val environment: Environment
) : AuthUsecase<DeleteSourceRequest, DefaultResponse>() {
    override suspend fun executor(request: DeleteSourceRequest): DefaultResponse {

        repository.findByNameAndType(request.name, ResourceType.SOUR)
            ?: throw SourceNotFoundException()

        deleteFile("environment.uploadDir/source/${request.name}")

        suspendedTx {
            repository.deleteByNameAndType(request.name, ResourceType.SOUR)
        }

        return DefaultResponse("소스가 성공적으로 삭제되었습니다.")
    }
}