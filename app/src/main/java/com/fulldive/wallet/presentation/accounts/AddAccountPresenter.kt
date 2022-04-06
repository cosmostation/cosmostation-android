package com.fulldive.wallet.presentation.accounts

import android.content.Context
import android.content.Intent
import com.fulldive.wallet.di.modules.DefaultPresentersModule
import com.fulldive.wallet.presentation.base.BaseMoxyPresenter
import com.joom.lightsaber.ProvidedBy
import com.fulldive.wallet.presentation.accounts.create.CreateAccountActivity
import wannabit.io.cosmostaion.activities.RestoreActivity
import wannabit.io.cosmostaion.activities.RestoreKeyActivity
import wannabit.io.cosmostaion.activities.WatchingAccountAddActivity
import javax.inject.Inject

@ProvidedBy(DefaultPresentersModule::class)
class AddAccountPresenter @Inject constructor() : BaseMoxyPresenter<AddAccountMoxyView>() {
    lateinit var chain: String

    fun onImportKeyClicked(packageContext: Context) {
        showActivity(packageContext, RestoreKeyActivity::class.java)

    }

    fun onImportMnemonicClicked(packageContext: Context) {
        showActivity(packageContext, RestoreActivity::class.java)
    }

    fun onWatchAddressClicked(packageContext: Context) {
        showActivity(packageContext, WatchingAccountAddActivity::class.java)
    }

    fun onCreateClicked(packageContext: Context) {
        showActivity(packageContext, CreateAccountActivity::class.java)
    }

    private fun showActivity(packageContext: Context, clazz: Class<*>) {
        val intent = Intent(packageContext, clazz)
        if (chain.isNotEmpty()) {
            intent.putExtra(CreateAccountActivity.KEY_CHAIN, chain)
        }
        viewState.startActivity(intent)
        viewState.dismiss()
    }
}