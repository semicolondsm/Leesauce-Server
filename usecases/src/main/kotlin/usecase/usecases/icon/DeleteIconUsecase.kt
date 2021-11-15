package usecase.usecases.icon

import domain.entity.ResourceType
import domain.repository.ResourceRepository
import usecase.model.DefaultResponse
import usecase.model.DeleteIconRequest
import usecase.usecases.AuthUsecase
import usecase.usecases.common.suspendedTx

class DeleteIconUsecase (
    private val repository: ResourceRepository
) : AuthUsecase<DeleteIconRequest, DefaultResponse>() {
    override suspend fun executor(request: DeleteIconRequest): DefaultResponse {
        suspendedTx {
            repository.deleteByNameAndType(request.name, ResourceType.ICON)
        }

        return DefaultResponse("아이콘이 성공적으로 삭제되었습니다.")
    }
}