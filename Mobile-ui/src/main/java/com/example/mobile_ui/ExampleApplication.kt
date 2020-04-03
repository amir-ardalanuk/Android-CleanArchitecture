package com.example.mobile_ui

import android.app.Application
import dagger.android.DaggerApplication
import dagger.android.DaggerApplication_MembersInjector
import timber.log.Timber

class ExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupTimber()

    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree());

    }
}