package com.example.data.store

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectDataStore
import com.example.data.repository.ProjectRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject
import kotlin.UnsupportedOperationException

class ProjectRemoteDataStore @Inject constructor(
    private val projectRemote: ProjectRemote
) : ProjectDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectRemote.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("Saving project is't support in RemoteDataStore")
    }

    override fun clearProject(): Completable {
        throw UnsupportedOperationException("clearProject is't support in RemoteDataStore")
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("getBookmarkedProjects is't support in RemoteDataStore")
    }

    override fun setBookmarkProject(projectId: String): Completable {
        throw UnsupportedOperationException("setBookmarkProject is't support in RemoteDataStore")
    }

    override fun setUnBookmarkProject(projectId: String): Completable {
        throw UnsupportedOperationException("setUnBookmarkProject is't support in RemoteDataStore")
    }

}