package com.example.data.store

import com.example.data.repository.ProjectDataStore
import javax.inject.Inject

open class ProjectDataStoreFactory @Inject constructor(
  private val projectsCacheDataStore: ProjectCacheDataStore ,
  private val projectsRemoteDataStore : ProjectRemoteDataStore
) {
    open fun getDataStore(projectsCached : Boolean ,
                          cacheExpired : Boolean):ProjectDataStore {
        return if (projectsCached && !cacheExpired){
            projectsCacheDataStore
        }else{
            projectsRemoteDataStore
        }
    }

    open fun getCacheDataStore(): ProjectDataStore{
        return projectsCacheDataStore
    }
}