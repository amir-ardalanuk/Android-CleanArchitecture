package com.example.data.repository

import com.example.data.model.ProjectEntity
import io.reactivex.Observable

interface ProjectRemote {
    fun getProjects(): Observable<List<ProjectEntity>>
}