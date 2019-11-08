package amir.ardalani.domain.interactor

import amir.ardalani.domain.executor.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Params> constructor(
    private val postExecutionThread: PostExecutionThread) {

    val disposeables = CompositeDisposable()

    abstract fun buildUseCaseObservalbe(params : Params? = null) : Completable

    open fun execute(observer : DisposableCompletableObserver,params: Params?){
        val completable = buildUseCaseObservalbe(params).subscribeOn(Schedulers.io()).observeOn(postExecutionThread.schedule)
        addDisposeable(completable.subscribeWith(observer))
    }

    fun addDisposeable(disposable: Disposable){
        this.disposeables.add(disposable)
    }
}