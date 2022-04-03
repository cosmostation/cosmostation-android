package com.fulldive.wallet.di.modules

import android.content.Context
import com.joom.lightsaber.Module
import com.joom.lightsaber.Provide
import wannabit.io.cosmostaion.base.BaseDB
import javax.inject.Singleton

@Module
class DatabaseModule(val context: Context) {

    @Provide
    @Singleton
    internal fun provideWalletDatabase(): BaseDB {
        return BaseDB.getInstance(context)
            ?: throw IllegalStateException("WalletDatabase can't be null")
    }
}
