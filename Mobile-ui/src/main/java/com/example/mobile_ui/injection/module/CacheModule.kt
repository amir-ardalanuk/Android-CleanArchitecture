package com.example.mobile_ui.injection.module

import android.app.Application
import com.example.cache.ProjectsCacheImpl
import com.example.cache.db.ProjectsDatabase
import com.example.data.repository.ProjectCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideDataBase(application: Application):ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache:ProjectsCacheImpl):ProjectCache

}