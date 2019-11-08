package amir.ardalani.domain.interactor

import amir.ardalani.domain.executor.PostExecutionThread
import amir.ardalani.domain.interactor.browse.GetProject
import amir.ardalani.domain.model.Project
import amir.ardalani.domain.repository.ProjectRepository
import amir.ardalani.domain.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableJust
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProjectTest {
    private lateinit var getProject: GetProject
    @Mock lateinit var projectRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getProject = GetProject(projectRepository,postExecutionThread)
    }

    @Test
    fun getProjectsCompletes(){
        stubGetProject(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val test = getProject.buildUseCaseObservalbe().test()
        test.assertComplete()
    }

    @Test
    fun getProjectsReturnData(){
        val projects = ProjectDataFactory.makeProjectList(2)
        stubGetProject(Observable.just(projects))
        val test = getProject.buildUseCaseObservalbe().test()
        test.assertValue(projects)
    }

    fun stubGetProject(observable : Observable<List<Project>>){
        whenever(projectRepository.getProjects()).thenReturn(observable)
    }
}