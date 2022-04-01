package com.fulldive.wallet.presentation.base

import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface ICompositable {

    val compositeDisposable: CompositeDisposable
    val defaultOnErrorConsumer: (Throwable) -> Unit

    fun Completable.compositeSubscribe(
        onSuccess: () -> Unit = {},
        onError: (Throwable) -> Unit = defaultOnErrorConsumer
    ) = subscribe(onSuccess, onError)
        .composite()

    fun <T : Any> Flowable<T>.compositeSubscribe(
        onNext: (T) -> Unit = {},
        onError: (Throwable) -> Unit = defaultOnErrorConsumer,
        onComplete: () -> Unit = {}
    ) = subscribe(onNext, onError, onComplete)
        .composite()

    fun <T : Any> Single<T>.compositeSubscribe(
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = defaultOnErrorConsumer
    ) = subscribe(onSuccess, onError)
        .composite()

    fun <T : Any> Maybe<T>.compositeSubscribe(
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = defaultOnErrorConsumer
    ) = subscribe(onSuccess, onError)
        .composite()

    fun <T : Any> Observable<T>.compositeSubscribe(
        onNext: (T) -> Unit = {},
        onError: (Throwable) -> Unit = defaultOnErrorConsumer,
        onComplete: () -> Unit = {}
    ) = subscribe(onNext, onError, onComplete)
        .composite()

    fun Completable.justSubscribe(
        onSuccess: () -> Unit = {},
        onError: (Throwable) -> Unit = defaultOnErrorConsumer
    ) = subscribe(onSuccess, onError)

    fun <T : Any> Single<T>.justSubscribe(
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = defaultOnErrorConsumer
    ) = subscribe(onSuccess, onError)

    private fun Disposable.composite(): Disposable {
        compositeDisposable.add(this)
        return this
    }

    fun onDestroy()
}
