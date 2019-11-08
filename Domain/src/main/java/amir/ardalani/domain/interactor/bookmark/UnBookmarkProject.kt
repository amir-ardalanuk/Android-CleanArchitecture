package amir.ardalani.domain.interactor.bookmark

import amir.ardalani.domain.executor.PostExecutionThread
import amir.ardalani.domain.interactor.CompletableUseCase
import amir.ardalani.domain.repository.ProjectRepository
import io.reactivex.Completable
import java.lang.IllegalArgumentException
import javax.inject.Inject

class UnBookmarkProject @Inject constructor(
    val projectRepository: ProjectRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<UnBookmarkProject.Params>(postExecutionThread){

    override fun buildUseCaseObservalbe(params: Params?): Completable {
        if(params == null) throw IllegalArgumentException("Params can't be empty")
        return  projectRepository.unBookmarkProject(params.projectId)
    }

    data class Params constructor(val projectId : String){
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }
}