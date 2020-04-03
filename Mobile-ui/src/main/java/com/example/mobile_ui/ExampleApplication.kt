package com.example.mobile_ui

import android.app.Activity
import android.app.Application
import com.example.mobile_ui.injection.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector

import timber.log.Timber
import javax.inject.Inject

class ExampleApplication : Application() , HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate() {
        super.onCreate()
        setupTimber()
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree());

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }


}