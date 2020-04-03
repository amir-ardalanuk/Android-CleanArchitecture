package com.example.mobile_ui.injection.module

import com.example.data.repository.ProjectRemote
import com.example.mobile_ui.BuildConfig
import com.example.remote.ProjectRemoteImpl
import com.example.remote.service.GithubTrendingService
import com.example.remote.service.GithubTrendingServiceFactory
import dagger.Binds

import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService():GithubTrendingService {
            return GithubTrendingServiceFactory().makeGithubTrandingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectRemote(projectRemote: ProjectRemoteImpl):ProjectRemote
}