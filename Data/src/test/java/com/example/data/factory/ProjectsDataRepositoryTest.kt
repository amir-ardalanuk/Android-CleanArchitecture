package com.example.data.factory

import amir.ardalani.domain.model.Project
import com.example.data.ProjectDataRepository
import com.example.data.mapper.ProjectMapper
import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectCache
import com.example.data.repository.ProjectDataStore
import com.example.data.store.ProjectDataStoreFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class ProjectsDataRepositoryTest {

    @Mock private lateinit var  mapper :ProjectMapper
    @Mock private lateinit var   factory : ProjectDataStoreFactory
    @Mock private lateinit var  store : ProjectDataStore
    @Mock private lateinit var  cache : ProjectCache
    private lateinit var repository : ProjectDataRepository


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)

        stubFactoryGetDataStore()
        stubFactoryGetCacheStore()
        stubIsCacheExpired(Single.just(false))
        stubIsProjectsCached(Single.just(false))
        stubSaveProjectsInCache(Completable.complete())

        repository = ProjectDataRepository(mapper,cache,factory)
    }

    @Test
    fun getProjectsCompletes(){
        stubGetProject(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val project = ProjectFactory.makeProject()
        stubMapper(project)

        val testObserver = repository.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData(){
        val projectEntity = ProjectFactory.makeProjectEntity()
        val project = ProjectFactory.makeProject()

        stubGetProject(Observable.just(listOf(projectEntity)))
        stubMapper(project)

        val testObserver = repository.getProjects().test()
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun getBookmarkProjectsComplete(){
        val projectEntity = ProjectFactory.makeProjectEntity()
        val project = ProjectFactory.makeProject()

        stubGetBookMarkProject(Observable.just(listOf(projectEntity)))
        stubMapper(project)

        val testObserver = repository.getBookmarkedProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkProjectsReturnsData(){
        val projectEntity = ProjectFactory.makeProjectEntity()
        val project = ProjectFactory.makeProject()

        stubGetBookMarkProject(Observable.just(listOf(projectEntity)))
        stubMapper(project)

        val testObserver = repository.getBookmarkedProjects().test()
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun bookmarkProjectComplete(){
        stubBookmarkedProject(Completable.complete())

        val testObserver = repository.bookmarkProject(ProjectDataFactory.randomUuid()).test()
        testObserver.assertComplete()
    }

    @Test
    fun unbookmarkProjectComplete(){
        stubUnBookmarkedProject(Completable.complete())

        val testObserver = repository.unBookmarkProject(ProjectDataFactory.randomUuid()).test()
        testObserver.assertComplete()
    }




    //MARK: - Stub

    fun stubBookmarkedProject(completable: Completable){
        whenever(store.setBookmarkProject(any())).thenReturn(completable)
    }

    fun stubUnBookmarkedProject(completable: Completable){
        whenever(store.setUnBookmarkProject(any())).thenReturn(completable)
    }
    private fun stubGetBookMarkProject(just: Observable<List<ProjectEntity>>?) {
        whenever(store.getBookmarkedProjects()).thenReturn(just)
    }

    fun stubMapper(model : Project){
        whenever(mapper.mapFromEntity(any())).thenReturn(model)
    }

    fun stubIsCacheExpired(single : Single<Boolean>){
        whenever(cache.isProjectCacheExpired()).thenReturn(single)
    }

    fun stubIsProjectsCached(single : Single<Boolean>){
        whenever(cache.areProjectsCached()).thenReturn(single)
    }

    fun stubGetProject(observable : Observable<List<ProjectEntity>>){
        whenever(store.getProjects()).thenReturn(observable)
    }

    fun stubFactoryGetDataStore(){
        whenever(factory.getDataStore(any(),any())).thenReturn(store)
    }

    fun stubFactoryGetCacheStore(){
        whenever(factory.getCacheDataStore()).thenReturn(store)
    }

    fun stubSaveProjectsInCache(completable: Completable){
        whenever(store.saveProjects(any())).thenReturn(completable)
    }

}