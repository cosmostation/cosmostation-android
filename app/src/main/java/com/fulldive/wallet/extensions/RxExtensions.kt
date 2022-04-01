package com.fulldive.wallet.extensions

import com.fulldive.wallet.rx.AppSchedulers
import io.reactivex.*
import wannabit.io.cosmostaion.utils.WLog

fun <T> Single<T>.withDefaults(): Single<T> {
    return compose {
        it.subscribeOn(AppSchedulers.io())
            .observeOn(AppSchedulers.ui())
            .doOnError { error -> WLog.e(error.toString()) }
    }
}

fun <T> Flowable<T>.withDefaults(): Flowable<T> {
    return compose {
        it.subscribeOn(AppSchedulers.io())
            .observeOn(AppSchedulers.ui())
            .doOnError { error -> WLog.e(error.toString()) }
    }
}

fun <T> Maybe<T>.withDefaults(): Maybe<T> {
    return compose {
        it.subscribeOn(AppSchedulers.io())
            .observeOn(AppSchedulers.ui())
            .doOnError { error -> WLog.e(error.toString()) }
    }
}

fun <T> Observable<T>.withDefaults(): Observable<T> {
    return compose {
        it.subscribeOn(AppSchedulers.io())
            .observeOn(AppSchedulers.ui())
            .doOnError { error -> WLog.e(error.toString()) }
    }
}

fun Completable.withDefaults(): Completable {
    return compose {
        it.subscribeOn(AppSchedulers.io())
            .observeOn(AppSchedulers.ui())
            .doOnError { error -> WLog.e(error.toString()) }
    }
}

inline fun <reified R> singleCallable(noinline callable: () -> R): Single<R> {
    return Single.fromCallable(callable)
}

inline fun <reified R> safeSingle(noinline callable: () -> R?): Single<R> {
    return Single.create { emitter ->
        try {
            val result = callable()
            if (result == null) {
                emitter.tryOnError(NullPointerException())
            } else {
                emitter.onSuccess(result)
            }
        } catch (ex: Exception) {
            emitter.tryOnError(ex)
        }
    }
}

inline fun <reified R> safeMaybe(noinline callable: () -> R?): Maybe<R> {
    return Maybe.create { emitter ->
        try {
            val result = callable()
            if (result == null) {
                emitter.tryOnError(NullPointerException())
            } else {
                emitter.onSuccess(result)
            }
        } catch (ex: Exception) {
            emitter.tryOnError(ex)
        }
    }
}

fun completeCallable(callable: () -> Unit): Completable {
    return Completable.fromCallable(callable)
}

fun safeCompletable(callable: () -> Unit): Completable {
    return Completable.create { emitter ->
        try {
            callable()
            emitter.onComplete()
        } catch (ex: Exception) {
            emitter.tryOnError(ex)
        }
    }
}

fun <T> T.toSingle(): Single<T> = Single.just(this)
