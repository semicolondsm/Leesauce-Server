package domain.entity

data class Resource(
    override val id: Int = -1,
    val name: String,
    val url: String,
    val type: ResourceType
): Entity

enum class ResourceType {
    SOUR,
    ICON,
    LOGO
}