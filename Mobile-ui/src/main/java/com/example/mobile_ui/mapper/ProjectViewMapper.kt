package com.example.mobile_ui.mapper


import com.example.mobile_ui.model.Project
import com.example.presentation.model.ProjectViewModel
import javax.inject.Inject

class ProjectViewMapper @Inject constructor():ViewMapper<ProjectViewModel,Project> {
    override fun mapToView(presentation: ProjectViewModel): Project {
        return Project(presentation.id,presentation.name,presentation.fullname,presentation.starCount,presentation.dateCreate,presentation.ownerName,presentation.ownerAvatar,presentation.isBookmark)
    }

}