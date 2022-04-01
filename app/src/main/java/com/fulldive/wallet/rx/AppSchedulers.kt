package com.fulldive.wallet.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AppSchedulers : ISchedulersProvider {
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun computation(): Scheduler = Schedulers.computation()
    override fun newThread(): Scheduler = Schedulers.newThread()
    override fun io(): Scheduler = Schedulers.io()
}