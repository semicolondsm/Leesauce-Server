package repository.resource

import domain.entity.Resource
import domain.entity.ResourceType
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

internal object ResourceTable : IntIdTable() {
    val name = varchar("name", 50)
    val url = varchar("url", 100)
    val type = char("type", 4)

    init {
        uniqueIndex(name, type)
    }
}

class ResourceEntity(id: EntityID<Int>): IntEntity(id) {
    var name by ResourceTable.name
    var url by ResourceTable.url
    var type by ResourceTable.type.transform(
        { type -> type.name },
        { type -> ResourceType.valueOf(type) }
    )

    companion object : IntEntityClass<ResourceEntity>(ResourceTable)

    fun toResource() = Resource(id.value, name, url, type)
}