package usecase.model

import domain.entity.ResourceType

class SearchRequest (
    val name: String?
)

class SearchResponse (
    val leeSources: List<LeeSauce>
) {
    class LeeSauce (
        val name: String,
        val url: String,
        val type: ResourceType
    )
}