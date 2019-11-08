package amir.ardalani.domain.interactor.bookmark

import amir.ardalani.domain.executor.PostExecutionThread
import amir.ardalani.domain.repository.ProjectRepository
import amir.ardalani.domain.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BookmarkProjectTest {
    lateinit var bookmarkProject: BookmarkProject
    @Mock lateinit var projectRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        bookmarkProject = BookmarkProject(projectRepository,postExecutionThread)
    }

    @Test
    fun bookmarkProjectTestComplete(){
        stubBookmarkProject(Completable.complete())
        val test  = bookmarkProject.buildUseCaseObservalbe(
            BookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        test.assertComplete()
    }


    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectTrowExeption(){
        bookmarkProject.buildUseCaseObservalbe().test()
    }

    fun stubBookmarkProject(completable: Completable){
        whenever(projectRepository.bookmarkProject(any())).thenReturn(completable)
    }
}