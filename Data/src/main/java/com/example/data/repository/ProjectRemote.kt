package com.example.data.repository

import com.example.data.model.ProjectEntity
import io.reactivex.Observable
import java.util.*

interface ProjectRemote {
    fun getProjects():Observable<List<ProjectEntity>>
}