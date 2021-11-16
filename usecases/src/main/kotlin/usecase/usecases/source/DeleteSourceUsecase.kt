package usecase.usecases.source

import domain.SourceNotFoundException
import domain.entity.ResourceType
import domain.repository.ResourceRepository
import usecase.model.DefaultResponse
import usecase.model.DeleteSourceRequest
import usecase.usecases.AuthUsecase
import usecase.usecases.common.deleteFile
import usecase.usecases.common.suspendedTx

class DeleteSourceUsecase (
    private val repository: ResourceRepository
) : AuthUsecase<DeleteSourceRequest, DefaultResponse>() {
    override suspend fun executor(request: DeleteSourceRequest): DefaultResponse {

        val icon = repository.findByNameAndType(request.name, ResourceType.SOUR)
            ?: throw SourceNotFoundException()

        deleteFile(icon.url)

        suspendedTx {
            repository.deleteByNameAndType(request.name, ResourceType.SOUR)
        }

        return DefaultResponse("소스가 성공적으로 삭제되었습니다.")
    }
}