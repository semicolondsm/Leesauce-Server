package usecase.usecases.logo

import domain.entity.ResourceType
import domain.repository.ResourceRepository
import usecase.model.GetLogoListResponse
import usecase.usecases.NormalUsecase

class GetLogoListUsecase (
    private val repository: ResourceRepository
) : NormalUsecase<GetLogoListResponse>() {
    override suspend fun executor(): GetLogoListResponse {
        val logos = repository.findAllByType(ResourceType.LOGO)

        return GetLogoListResponse(
            logos.map {
                GetLogoListResponse.Logo(
                    name = it.name,
                    url = it.url
                )
            }
        )
    }
}