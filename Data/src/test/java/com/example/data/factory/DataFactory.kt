package com.example.data.factory

import amir.ardalani.domain.model.Project
import com.example.data.model.ProjectEntity

object ProjectFactory{
    fun makeProjectEntity():ProjectEntity {
        return ProjectEntity(ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomBoolean())
    }

    fun makeProject():Project {
        return Project(
            ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomUuid(),
            ProjectDataFactory.randomBoolean())
    }
}