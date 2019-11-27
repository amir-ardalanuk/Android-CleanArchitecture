package com.example.remote

import com.example.data.model.ProjectEntity
import com.example.remote.factory.ProjectDataFactory
import com.example.remote.mapper.ProjectResponseModelMapper
import com.example.remote.model.ProjectModel
import com.example.remote.model.ProjectsResponseModel
import com.example.remote.service.GithubTrendingService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test

class ProjectRemoteImplTest {

    val mapper = mock<ProjectResponseModelMapper>()
    val service = mock<GithubTrendingService>()
    val remote = ProjectRemoteImpl(service,mapper)

    @Test
    fun githubServicesComplete(){
        stubGithubTrendingServices(Observable.just(ProjectDataFactory.makeProjectResponse()))
        stubProjectResponseModelMapper(ProjectDataFactory.makeProject(),ProjectDataFactory.makeProjectEntity())

        val testObservable = remote.getProjects().test()
        testObservable.assertComplete()
    }

    @Test
    fun githubServicesCallServer(){
        stubGithubTrendingServices(Observable.just(ProjectDataFactory.makeProjectResponse()))
        stubProjectResponseModelMapper(ProjectDataFactory.makeProject(),ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories("language:kotlin", "stars","desc")
    }
    @Test
    fun githubServicesReturnsData(){
        val response = ProjectDataFactory.makeProjectResponse()
        stubGithubTrendingServices(Observable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.map {
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectResponseModelMapper(it,entity)
        }
        val testObservable = remote.getProjects().test()
        testObservable.assertValue(entities)
    }

    fun stubGithubTrendingServices(observable : Observable<ProjectsResponseModel>){
        whenever(service.searchRepositories(any(), any(), any())).thenReturn(observable)
    }

    fun stubProjectResponseModelMapper(model : ProjectModel , entity: ProjectEntity){
        whenever(mapper.mapFromModel(model)).thenReturn(entity)
    }
}