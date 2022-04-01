package com.fulldive.wallet.presentation.base

abstract class BaseOnErrorConsumer : (Throwable) -> Unit {

    override fun invoke(error: Throwable) {
        onError(error)
    }

    abstract fun onError(error: Throwable)

    abstract fun onNoConnectionError()
}
