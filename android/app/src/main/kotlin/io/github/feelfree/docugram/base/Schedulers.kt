package io.github.feelfree.docugram.base

import io.reactivex.Scheduler

// Providing schedulers by Dagger allows us to easily test presenters
interface Schedulers {
    fun mainThread() : Scheduler
    fun backgroundThread() : Scheduler
}