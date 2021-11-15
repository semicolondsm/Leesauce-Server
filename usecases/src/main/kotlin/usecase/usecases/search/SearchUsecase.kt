package usecase.usecases.search

import domain.entity.Resource
import domain.repository.ResourceRepository
import usecase.model.SearchRequest
import usecase.model.SearchResponse
import usecase.usecases.RequestUsecase

class SearchUsecase(
    private val repository: ResourceRepository
) : RequestUsecase<SearchRequest, SearchResponse>() {
    override suspend fun executor(request: SearchRequest): SearchResponse {
        val leeSauces: List<Resource> = if (request.name != null)
            repository.findAllLikeName(request.name)
        else
            repository.findAll()

        return SearchResponse(
            leeSauces.map {
                SearchResponse.LeeSauce(
                    name = it.name,
                    url = it.url,
                    type = it.type
                )
            }
        )
    }
}