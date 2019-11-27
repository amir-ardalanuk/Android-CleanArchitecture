package com.example.data.mapper

import amir.ardalani.domain.model.Project
import com.example.data.model.ProjectEntity

open class ProjectMapper : EntityMapper<ProjectEntity,Project> {
    override fun mapFromEntity(entity: ProjectEntity): Project {
        return Project(entity.id,entity.name,entity.fullname,entity.starCount,entity.dateCreated,entity.ownerName,entity.ownerAvater,false)
    }

    override fun mapToEntity(domain: Project): ProjectEntity {
        return ProjectEntity(domain.id,domain.name,domain.fullname,domain.starCount,domain.dateCreated,domain.ownerName,domain.ownerAvater)
    }

}