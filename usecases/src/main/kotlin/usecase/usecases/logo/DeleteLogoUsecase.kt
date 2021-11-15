package usecase.usecases.logo

import domain.entity.ResourceType
import domain.repository.ResourceRepository
import usecase.model.DefaultResponse
import usecase.model.DeleteLogoRequest
import usecase.usecases.AuthUsecase

class DeleteLogoUsecase (
    private val repository: ResourceRepository
) : AuthUsecase<DeleteLogoRequest, DefaultResponse>() {
    override suspend fun executor(request: DeleteLogoRequest): DefaultResponse {
        repository.deleteByNameAndType(request.name, ResourceType.LOGO)

        return DefaultResponse("로고가 성공적으로 삭제되었습니다.")
    }
}