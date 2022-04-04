package com.fulldive.wallet.di.components

import android.content.Context
import com.fulldive.wallet.di.modules.*
import com.joom.lightsaber.Component
import com.joom.lightsaber.Import
import javax.inject.Singleton

@Component
@Singleton
class ApplicationComponent(private var context: Context) {

    @Import
    fun getDefaultModule() = DefaultModule()

    @Import
    fun getDefaultInteractorsModule() = DefaultInteractorsModule()

    @Import
    fun getDefaultRepositoryModule() = DefaultRepositoryModule()

    @Import
    fun getDefaultPresentersModule() = DefaultPresentersModule()

    @Import
    fun getDefaultLocalStorageModule() = DefaultLocalStorageModule()

    @Import
    fun getAppConfigurationModule() = ApplicationConfigurationModule(context)

    @Import
    fun getDatabaseModule() = DatabaseModule(context)
}
