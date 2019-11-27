package com.example.remote.factory

import com.example.data.model.ProjectEntity
import com.example.remote.model.OwnerModel
import com.example.remote.model.ProjectModel
import com.example.remote.model.ProjectsResponseModel

object ProjectDataFactory {

    fun makeOwener() : OwnerModel{
        return OwnerModel(DataFactory.randomUuid(),DataFactory.randomUuid())
    }

    fun makeProject() : ProjectModel {
        return ProjectModel(
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            makeOwener()
        )
    }


    fun makeProjectEntity() : ProjectEntity {
        return ProjectEntity(
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid()
        )
    }

    fun makeProjectResponse() : ProjectsResponseModel{
        return ProjectsResponseModel(listOf(makeProject(), makeProject()))
    }
}