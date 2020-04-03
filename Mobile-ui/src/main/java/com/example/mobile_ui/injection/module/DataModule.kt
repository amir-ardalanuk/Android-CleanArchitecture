package com.example.mobile_ui.injection.module

import amir.ardalani.domain.repository.ProjectRepository
import com.example.data.ProjectDataRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectDataRepository):ProjectRepository
}