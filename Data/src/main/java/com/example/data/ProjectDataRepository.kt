package com.example.data

import amir.ardalani.domain.model.Project
import amir.ardalani.domain.repository.ProjectRepository
import com.example.data.mapper.ProjectMapper
import com.example.data.repository.ProjectCache
import com.example.data.store.ProjectDataStoreFactory
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

open class ProjectDataRepository @Inject constructor(
    private val mapper : ProjectMapper ,
    private val cache : ProjectCache ,
    private val factory : ProjectDataStoreFactory
) : ProjectRepository {
    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(cache.areProjectsCached().toObservable(),
            cache.isProjectCacheExpired().toObservable(),
            BiFunction<Boolean,Boolean,Pair<Boolean,Boolean>>{ areCached , isExpiredCache ->
                Pair(areCached,isExpiredCache)
            })
            .flatMap{
            factory.getDataStore(it.first,it.second).getProjects()
            }
            .flatMap {projects ->
            factory.getCacheDataStore()
                .saveProjects(projects)
                .andThen(Observable.just(projects))
        }
            .map {projects ->
            projects.map {
                mapper.mapFromEntity(it)
            }
        }
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects()
            .map { projects -> projects.map { mapper.mapFromEntity(it) } }
    }

    override fun bookmarkProject(projectId: String): Completable {
       return factory.getCacheDataStore().setBookmarkProject(projectId)
    }

    override fun unBookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setUnBookmarkProject(projectId)
    }

}