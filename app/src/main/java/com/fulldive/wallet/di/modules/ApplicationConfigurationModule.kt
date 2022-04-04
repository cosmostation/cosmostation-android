package com.fulldive.wallet.di.modules

import android.content.Context
import com.fulldive.wallet.rx.AppSchedulers
import com.fulldive.wallet.rx.ISchedulersProvider
import com.joom.lightsaber.Module
import com.joom.lightsaber.Provide
import javax.inject.Singleton

@Singleton
@Module
class ApplicationConfigurationModule(private val context: Context) {

    @Provide
    fun getAppContext(): Context = context

    @Singleton
    @Provide
    fun provideSchedulersProvider(): ISchedulersProvider = AppSchedulers

}
