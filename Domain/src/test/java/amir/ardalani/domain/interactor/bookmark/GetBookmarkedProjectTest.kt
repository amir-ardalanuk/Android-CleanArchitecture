package amir.ardalani.domain.interactor.bookmark
import amir.ardalani.domain.executor.PostExecutionThread
import amir.ardalani.domain.model.Project
import amir.ardalani.domain.repository.ProjectRepository
import amir.ardalani.domain.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookmarkedProjectTest {
    lateinit var project: GetBookmarkedProject
    @Mock lateinit var projectRepository : ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        project = GetBookmarkedProject(projectRepository,postExecutionThread)
    }

    @Test
    fun getBookmarkProjectComplete(){
        stubGetBookmarkProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val test  = project.buildUseCaseObservalbe().test()
        test.assertComplete()
    }

    @Test
    fun getBookmarkProjectAssertEqual(){
        val projects = ProjectDataFactory.makeProjectList(2)
        stubGetBookmarkProjects(Observable.just(projects))
        val test  = project.buildUseCaseObservalbe().test()
        test.assertValue(projects)
    }

    fun stubGetBookmarkProjects(observable : Observable<List<Project>>){
        whenever(projectRepository.getBookmarkedProjects()).thenReturn(observable)

    }

}