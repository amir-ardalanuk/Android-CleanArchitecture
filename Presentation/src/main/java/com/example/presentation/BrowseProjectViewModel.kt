package com.example.presentation

import amir.ardalani.domain.interactor.bookmark.BookmarkProject
import amir.ardalani.domain.interactor.bookmark.GetBookmarkedProject
import amir.ardalani.domain.interactor.bookmark.UnBookmarkProject
import amir.ardalani.domain.interactor.browse.GetProject
import amir.ardalani.domain.model.Project
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.presentation.mapper.ProjectViewMapper
import com.example.presentation.model.ProjectViewModel
import com.example.presentation.state.Resource
import com.example.presentation.state.ResourseState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseProjectViewModel @Inject constructor(private val getProject : GetProject ,
                                                 private val bookmarkedProject: BookmarkProject,
                                                 private val unBookmarkProject: UnBookmarkProject,
                                                 private val mapper : ProjectViewMapper) :ViewModel(){
    override fun onCleared() {
        getProject.dispose()
        super.onCleared()
    }

    private val liveData : MutableLiveData<Resource<List<ProjectViewModel>>> =  MutableLiveData()


    fun getProject(): LiveData<Resource<List<ProjectViewModel>>> {
        return liveData
    }

    fun bookmarkProject(id : String){
        liveData.postValue(Resource(ResourseState.LOADING,null,null))
        return bookmarkedProject.execute(BookmarkProjectSubscriber(),BookmarkProject.Params.forProject(id))
    }

    fun unbookmarkProject(id : String){
        liveData.postValue(Resource(ResourseState.LOADING,null,null))
        return unBookmarkProject.execute(BookmarkProjectSubscriber(),UnBookmarkProject.Params.forProject(id))
    }

    fun fetchProjects(){
        liveData.postValue(Resource(ResourseState.LOADING,null,null))
        return getProject.execute(ProjectSubscribe(),null)
    }


    inner class ProjectSubscribe: DisposableObserver<List<Project>>() {
        override fun onComplete() {}

        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourseState.SUCCESS ,
                t.map{ mapper.mapToView(it)} , null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourseState.ERROR,null,e.localizedMessage))
        }

    }

    inner class BookmarkProjectSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {

            liveData.postValue(Resource(ResourseState.SUCCESS,liveData.value?.data,null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourseState.ERROR,null,e.localizedMessage))
        }

    }
}