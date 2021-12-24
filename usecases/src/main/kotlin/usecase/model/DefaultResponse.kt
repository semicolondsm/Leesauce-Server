package usecase.model

import domain.entity.ResourceType

open class DefaultResponse (
    val message: String
)

open class Response (
    val name: String,
    val url: String,
    val type: ResourceType
)
