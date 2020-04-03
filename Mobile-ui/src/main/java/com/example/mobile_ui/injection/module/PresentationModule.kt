package com.example.mobile_ui.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_ui.injection.ViewModelFactory
import com.example.presentation.BrowseProjectViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class PresentationModule{

    @Binds
    @IntoMap
    @ViewModelKey(BrowseProjectViewModel::class)
    abstract fun  bindBrowesProjectsViewModel(viewmodel:BrowseProjectViewModel):ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory:ViewModelFactory):ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value:KClass<out ViewModel>)