package com.fulldive.wallet.di

import com.joom.lightsaber.Injector

interface IInjectorHolder {
    fun getInjector(): Injector
}

interface IChildInjectorHolder {
    fun getChildInjector(): Injector
}

interface IEnrichableActivity {
    var appInjector: Injector
}