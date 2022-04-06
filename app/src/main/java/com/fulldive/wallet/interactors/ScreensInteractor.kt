package com.fulldive.wallet.interactors

import com.fulldive.wallet.di.modules.DefaultInteractorsModule
import com.fulldive.wallet.extensions.or
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ProvidedBy(DefaultInteractorsModule::class)
class ScreensInteractor @Inject constructor() {
    private val resultListeners = mutableMapOf<String, PublishSubject<Any>>()

    fun observeResult(
        requestCode: String,
    ): Observable<Any> {
        resultListeners.keys.toList().forEach { key ->
            val item = resultListeners[key]
            if (item?.hasThrowable() != false || item.hasComplete() || !item.hasObservers()) {
                resultListeners.remove(key)
            }
        }
        return resultListeners[requestCode]
            .or {
                PublishSubject.create<Any>().also { subject ->
                    resultListeners[requestCode] = subject
                }
            }
    }

    fun sendResult(
        requestCode: String,
        result: Any
    ) {
        resultListeners[requestCode]?.let { publisher ->
            publisher.onNext(result)
            publisher.onComplete()
            resultListeners.remove(requestCode)
        }
    }
}