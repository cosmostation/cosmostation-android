package com.fulldive.wallet.presentation.main.intro

import android.content.Context
import android.content.Intent
import com.fulldive.wallet.di.modules.DefaultPresentersModule
import com.fulldive.wallet.extensions.*
import com.fulldive.wallet.interactors.accounts.AccountsInteractor
import com.fulldive.wallet.presentation.base.BaseMoxyPresenter
import com.fulldive.wallet.presentation.chains.choicenet.ChoiceNetDialogFragment
import com.joom.lightsaber.ProvidedBy
import wannabit.io.cosmostaion.base.BaseApplication
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ProvidedBy(DefaultPresentersModule::class)
class IntroPresenter @Inject constructor(
    private val context: Context,
    private val accountsInteractor: AccountsInteractor
) : BaseMoxyPresenter<IntroMoxyView>() {

    lateinit var intent: Intent

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        accountsInteractor
            .upgradeAccountAddressForPath()
            .onErrorComplete()
            .delay(2500, TimeUnit.MILLISECONDS)
            .andThen(accountsInteractor.getAccounts())
            .map { it.isNotEmpty() }
            .withDefaults()
            .compositeSubscribe(onSuccess = { hasAccounts ->
                if (hasAccounts) {
                    nextScreen()
                } else {
                    viewState.showButtonsPanel()
                }
            })
    }

    fun onStartButtonClicked() {
        viewState.showDialog(ChoiceNetDialogFragment.newInstance(true), "dialog", true)
    }

    private fun nextScreen() {
        safeSingle { (context.applicationContext as? BaseApplication)?.needShowLockScreen() == true }
            .flatMap { showLockScreen ->
                if (showLockScreen) {
                    combine(true, -1).toSingle()
                } else {
                    safeSingle { intent.extras?.getString("notifyto")?.trim().orNull() }
                        .flatMapCompletable(accountsInteractor::selectAccountForAddress)
                        .toSingleDefault(2)
                        .onErrorReturnItem(0)
                        .map { tabIndex -> combine(false, tabIndex) }
                }
            }
            .withDefaults()
            .compositeSubscribe(
                onSuccess = { (showLockScreen, tabIndex) ->
                    if (showLockScreen) {
                        viewState.showLockScreen()
                    } else {
                        viewState.showMainActivity(tabIndex)
                    }
                }
            )
    }
}