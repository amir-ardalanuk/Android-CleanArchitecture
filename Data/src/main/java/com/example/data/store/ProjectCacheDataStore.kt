package com.example.data.store

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectCache
import com.example.data.repository.ProjectDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

open class ProjectCacheDataStore @Inject constructor(
    private  val projectCache: ProjectCache
) : ProjectDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectCache.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectCache.saveProjects(projects)
            .andThen(projectCache.setLastCachTime(System.currentTimeMillis()))
    }

    override fun clearProject(): Completable {
        return projectCache.clearProject()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectCache.getBookmarkedProjects()
    }

    override fun setBookmarkProject(projectId: String): Completable {
        return  projectCache.setProjectAsBookmarked(projectId)
    }

    override fun setUnBookmarkProject(projectId: String): Completable {
        return projectCache.setProjectAsUnBookmarked(projectId)
    }

}