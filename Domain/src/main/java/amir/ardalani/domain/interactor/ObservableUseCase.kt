package amir.ardalani.domain.interactor

import amir.ardalani.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T,in Params> constructor(
    private val postExecutionThread: PostExecutionThread) {

    val disposeables = CompositeDisposable()

    abstract fun buildUseCaseObservalbe(params : Params? = null) : Observable<T>

    open fun execute(observer : DisposableObserver<T>,params: Params?){
        val observable = buildUseCaseObservalbe(params).subscribeOn(Schedulers.io()).observeOn(postExecutionThread.schedule)
        addDisposeable(observable.subscribeWith(observer))
    }

    fun addDisposeable(disposable: Disposable){
        this.disposeables.add(disposable)
    }
}