package com.example.cache.Mapper

import com.example.cache.model.CachedProject
import com.example.data.model.ProjectEntity
import javax.inject.Inject

class CachedProjectMapper @Inject constructor(): CacheMapper<CachedProject, ProjectEntity> {

    override fun mapFromCached(type: CachedProject): ProjectEntity {
        return with(type) {
            ProjectEntity(id, name, fullName, starCount, dateCreated, ownerName, ownerAvatar)
        }
    }

    override fun mapToCached(type: ProjectEntity): CachedProject {
        return with(type) {
            CachedProject(id, name, fullname, starCount, dateCreated, ownerName,ownerAvater ,false)
        }
    }
}