package com.example.remote

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectRemote
import com.example.remote.mapper.ProjectResponseModelMapper
import com.example.remote.service.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

class ProjectRemoteImpl @Inject constructor(
    private val service: GithubTrendingService,
    private val mapper: ProjectResponseModelMapper
) : ProjectRemote {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("", "", "")
            .map { it.items.map { mapper.mapFromModel(it) } }
    }

}