package usecase.usecases.source

import domain.entity.ResourceType
import domain.repository.ResourceRepository
import usecase.model.DefaultResponse
import usecase.model.DeleteSourceRequest
import usecase.usecases.AuthUsecase

class DeleteSourceUsecase (
    private val repository: ResourceRepository
) : AuthUsecase<DeleteSourceRequest, DefaultResponse>() {
    override suspend fun executor(request: DeleteSourceRequest): DefaultResponse {
        repository.deleteByNameAndType(request.name, ResourceType.SOUR)

        return DefaultResponse("소스가 성공적으로 삭제되었습니다.")
    }
}