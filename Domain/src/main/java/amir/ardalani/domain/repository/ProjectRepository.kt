package amir.ardalani.domain.repository

import amir.ardalani.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.*

interface ProjectRepository {

    fun getProjects(): Observable<List<Project>>

    fun getBookmarkedProjects():Observable<List<Project>>

    fun bookmarkProject(projectId : String) : Completable

    fun unBookmarkProject(projectId : String) : Completable
}