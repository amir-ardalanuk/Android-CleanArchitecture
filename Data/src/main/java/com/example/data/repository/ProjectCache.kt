package com.example.data.repository

import com.example.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface ProjectCache {

    fun clearProject():Completable

    fun saveProjects(projects: List<ProjectEntity>):Completable

    fun getProjects(): Flowable<List<ProjectEntity>>

    fun getBookmarkedProjects():Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId : String) : Completable

    fun setProjectAsUnBookmarked(projectId : String) : Completable

    fun areProjectsCached():Single<Boolean>

    fun setLastCachTime(lastCache: Long) :Completable

    fun isProjectCacheExpired():Single<Boolean>
}