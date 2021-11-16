package usecase.usecases.logo

import domain.LogoNotFoundException
import domain.entity.ResourceType
import domain.repository.ResourceRepository
import usecase.model.DefaultResponse
import usecase.model.DeleteLogoRequest
import usecase.usecases.AuthUsecase
import usecase.usecases.common.suspendedTx

class DeleteLogoUsecase (
    private val repository: ResourceRepository
) : AuthUsecase<DeleteLogoRequest, DefaultResponse>() {
    override suspend fun executor(request: DeleteLogoRequest): DefaultResponse {

        val logo = repository.findByNameAndType(request.name, ResourceType.LOGO)
            ?: throw LogoNotFoundException()

        suspendedTx {
            repository.deleteByNameAndType(request.name, ResourceType.LOGO)
        }

        return DefaultResponse("로고가 성공적으로 삭제되었습니다.")
    }
}