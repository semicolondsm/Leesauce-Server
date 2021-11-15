package usecase.usecases.source

import domain.entity.ResourceType
import domain.repository.ResourceRepository
import usecase.model.GetSourceListResponse
import usecase.usecases.NormalUsecase

class GetSourceListUsecase (
    private val repository: ResourceRepository
) : NormalUsecase<GetSourceListResponse>() {
    override suspend fun executor(): GetSourceListResponse {
        val resource = repository.findAllByType(ResourceType.SOUR)

        return GetSourceListResponse(
            resource.map {
                GetSourceListResponse.Source(
                    name = it.name,
                    url = it.url
                )
            }
        )
    }
}