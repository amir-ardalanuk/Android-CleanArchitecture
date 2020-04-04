package com.example.mobile_ui.injection.module

import amir.ardalani.domain.executor.PostExecutionThread
import com.example.mobile_ui.UiThread
import com.example.mobile_ui.activity.home.HomeActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread):PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesHomeActivity(): HomeActivity
}