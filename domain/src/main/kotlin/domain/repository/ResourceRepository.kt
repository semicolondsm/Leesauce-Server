package domain.repository

import domain.entity.Resource
import domain.entity.ResourceType

interface ResourceRepository : Repository<Resource, Int> {
    suspend fun findByNameAndType(name: String, type: ResourceType): Resource?
    suspend fun findAllByType(type: ResourceType): List<Resource>
    suspend fun findAllLikeName(name: String): List<Resource>
    suspend fun deleteByNameAndType(name: String, type: ResourceType)
}