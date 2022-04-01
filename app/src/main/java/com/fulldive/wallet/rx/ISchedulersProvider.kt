package com.fulldive.wallet.rx

import io.reactivex.Scheduler

interface ISchedulersProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun newThread(): Scheduler
    fun io(): Scheduler
}
