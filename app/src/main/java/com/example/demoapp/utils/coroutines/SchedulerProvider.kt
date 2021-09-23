package com.example.demoapp.utils.coroutines

import io.reactivex.Scheduler

/**
 * Rx Scheduler Provider
 */
interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
}