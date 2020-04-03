package com.example.mobile_ui

import amir.ardalani.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class  UiThread @Inject constructor() : PostExecutionThread {
    override val schedule: Scheduler
        get() = AndroidSchedulers.mainThread()

}