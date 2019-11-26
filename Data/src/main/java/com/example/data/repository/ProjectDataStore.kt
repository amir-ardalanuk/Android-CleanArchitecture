package com.example.data.repository

import com.example.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

open interface ProjectDataStore {

    fun getProjects():Observable<List<ProjectEntity>>

    fun saveProjects(projects:List<ProjectEntity>):Completable

    fun clearProject():Completable

    fun getBookmarkedProjects():Observable<List<ProjectEntity>>

    fun setBookmarkProject(projectId :String):Completable

    fun setUnBookmarkProject(projectId :String):Completable
}