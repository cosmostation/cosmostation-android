package com.fulldive.wallet.di.modules

import android.content.Context
import com.joom.lightsaber.Module
import com.joom.lightsaber.Provide
import wannabit.io.cosmostaion.base.BaseData
import javax.inject.Singleton

@Module
class DatabaseModule(val context: Context) {

    @Provide
    @Singleton
    fun provideWalletBaseData(): BaseData {
        return BaseData(context)
    }
}
