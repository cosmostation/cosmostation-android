package com.fulldive.wallet.di

import android.app.Activity
import android.app.Application
import android.os.Bundle

class EnrichableLifecycleCallbacks(private val injectorHolder: IInjectorHolder) :
    Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        (activity as? IEnrichableActivity)?.let { it ->
            it.appInjector = injectorHolder.getInjector()
        }
    }

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) = Unit

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit
}