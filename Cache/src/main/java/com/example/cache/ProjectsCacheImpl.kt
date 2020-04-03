package com.example.cache

import com.example.cache.Mapper.CachedProjectMapper
import com.example.cache.db.ProjectsDatabase
import com.example.cache.model.Config
import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ProjectsCacheImpl @Inject constructor(
    private val projectsDatabase: ProjectsDatabase,
    private val mapper: CachedProjectMapper) : ProjectCache {


    override fun clearProject(): Completable {
        return Completable.defer {
            projectsDatabase.cacheProjectDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectsDatabase.cacheProjectDao().insertProject(
                projects.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }


    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return projectsDatabase.cacheProjectDao().getProjects()
            .map { list -> list.map { mapper.mapFromCached(it) } }
    }



    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cacheProjectDao()
            .getBookmarkedProjects()
            .toObservable()
            .map { list -> list.map { mapper.mapFromCached(it) } }
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cacheProjectDao().setBookmarkStatus(true, projectId)
            Completable.complete()
        }
    }

    override fun setProjectAsUnBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cacheProjectDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Single<Boolean> {
        return projectsDatabase.cacheProjectDao()
            .cachedProjectsExist()
            .map { it > 0 }
    }

    override fun setLastCachTime(lastCache: Long): Completable {
        return Completable.defer {
            projectsDatabase.configDao().insertProject(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isProjectCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return projectsDatabase.configDao().getConfig()
            .onErrorReturn { Config(lastCacheTime = 0) }
            .map { currentTime - it.lastCacheTime > expirationTime }
    }

}