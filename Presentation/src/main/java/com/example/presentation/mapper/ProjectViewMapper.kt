package com.example.presentation.mapper

import amir.ardalani.domain.model.Project
import com.example.presentation.model.ProjectViewModel
import javax.inject.Inject

class ProjectViewMapper @Inject constructor() : Mapper<ProjectViewModel,Project> {
    override fun mapToView(type: Project): ProjectViewModel {
        return ProjectViewModel(type.id,type.name,type.fullname,type.starCount,type.dateCreated,type.ownerName,type.ownerAvater)
    }

}