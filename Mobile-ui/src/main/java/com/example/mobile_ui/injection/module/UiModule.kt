package com.example.mobile_ui.injection.module

import amir.ardalani.domain.executor.PostExecutionThread
import androidx.annotation.UiThread
import com.example.mobile_ui.activity.HomeActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecuteThread(uiThread: UiThread):PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesHomeActivity():HomeActivity
}