package repository.resource

import domain.entity.Resource
import domain.entity.ResourceType
import domain.repository.ResourceRepository
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import repository.DatabaseFactory.query
import repository.DefaultDAO

class ResourceRepositoryImpl : ResourceRepository, DefaultDAO<Resource, Int, ResourceEntity>(ResourceEntity) {

    override suspend fun findByNameAndType(name: String, type: ResourceType) = query {
        ResourceEntity.find { ResourceTable.name.eq(name) and ResourceTable.type.eq(type.toString()) }
            .firstOrNull()?.toResource()
    }

    override suspend fun findAllByType(type: ResourceType) = query {
        ResourceEntity.find { ResourceTable.type eq type.toString() }.map { it.toResource() }
    }

    override suspend fun create(entity: Resource): Resource = query {
        val resource = ResourceEntity.new {
            name = entity.name
            url = entity.url
            type = entity.type
        }

        resource.toResource()
    }

    override suspend fun update(entity: Resource): Resource = query {
        val resource = ResourceEntity[entity.id].apply {
            name = entity.name
            url = entity.url
            type = entity.type
        }

        resource.toResource()
    }

    override suspend fun findAllLikeName(name: String) = query {
        ResourceEntity.find { ResourceTable.name like "%$name%" }.map { it.toResource() }
    }

    override suspend fun deleteByNameAndType(name: String, type: ResourceType) {
        ResourceTable.deleteWhere { ResourceTable.name.eq(name) and ResourceTable.type.eq(type.toString()) }
    }

    override fun ResourceEntity.toDomain() = this.toResource()
}