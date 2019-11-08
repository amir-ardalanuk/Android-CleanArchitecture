package amir.ardalani.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    val schedule:Scheduler
}