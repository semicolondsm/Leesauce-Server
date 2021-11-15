package usecase.usecases.icon

import domain.entity.ResourceType
import domain.repository.ResourceRepository
import usecase.model.GetIconListResponse
import usecase.usecases.NormalUsecase

class GetIconListUsecase (
    private val repository: ResourceRepository
) : NormalUsecase<GetIconListResponse>() {
    override suspend fun executor(): GetIconListResponse {
        val icons = repository.findAllByType(ResourceType.ICON)

        return GetIconListResponse(
            icons.map {
                GetIconListResponse.Icon(
                    name = it.name,
                    url = it.url
                )
            }
        )
    }
}