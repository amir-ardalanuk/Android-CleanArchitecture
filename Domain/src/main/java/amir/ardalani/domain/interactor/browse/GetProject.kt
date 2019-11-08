package amir.ardalani.domain.interactor.browse

import amir.ardalani.domain.executor.PostExecutionThread
import amir.ardalani.domain.interactor.ObservableUseCase
import amir.ardalani.domain.model.Project
import amir.ardalani.domain.repository.ProjectRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetProject @Inject constructor(
    val projectRepository: ProjectRepository ,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project> , Nothing?>(postExecutionThread) {
    override fun buildUseCaseObservalbe(params: Nothing?): Observable<List<Project>> {
        return projectRepository.getProjects()
    }

}