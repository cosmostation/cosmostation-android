package com.fulldive.wallet.presentation.accounts.watch

import com.fulldive.wallet.di.modules.DefaultPresentersModule
import com.fulldive.wallet.extensions.singleCallable
import com.fulldive.wallet.extensions.withDefaults
import com.fulldive.wallet.interactors.ClipboardInteractor
import com.fulldive.wallet.interactors.ScreensInteractor
import com.fulldive.wallet.interactors.accounts.AccountsInteractor
import com.fulldive.wallet.interactors.accounts.DuplicateAccountException
import com.fulldive.wallet.interactors.accounts.MaxAccountsException
import com.fulldive.wallet.presentation.base.BaseMoxyPresenter
import com.fulldive.wallet.presentation.chains.choicenet.ChoiceChainDialogFragment
import com.fulldive.wallet.rx.AppSchedulers
import com.google.zxing.integration.android.IntentResult
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import java.util.*
import javax.inject.Inject

@ProvidedBy(DefaultPresentersModule::class)
class WatchAccountPresenter @Inject constructor(
    private val accountsInteractor: AccountsInteractor,
    private val clipboardInteractor: ClipboardInteractor,
    private val screensInteractor: ScreensInteractor
) : BaseMoxyPresenter<WatchAccountMoxyView>() {

    fun onCancelClicked() {
        viewState.finish()
    }

    fun onNextClicked(address: String) {
        val chains = BaseChain
            .getChainsByAddress(address)
            .filter(BaseChain::isSupported)
        when (chains.size) {
            0 -> viewState.showMessage(R.string.error_invalid_address)
            1 -> createAccount(chains[0], address)
            else -> selectChainAndCreateAccount(chains, address)
        }
    }

    fun onPasteClicked() {
        clipboardInteractor
            .getClip()
            .subscribeOn(AppSchedulers.ui())
            .observeOn(AppSchedulers.ui())
            .compositeSubscribe(
                onSuccess = { data ->
                    if (data.isNotEmpty()) {
                        viewState.setAddress(data)
                    } else {
                        viewState.showMessage(R.string.error_clipboard_no_data)
                    }
                }

            )
    }

    fun onScanQRResultsReceived(result: IntentResult) {
        result
            .contents
            ?.trim()
            ?.takeIf(String::isNotEmpty)
            ?.let(viewState::setAddress)
    }

    private fun selectChainAndCreateAccount(chains: List<BaseChain>, address: String) {
        singleCallable { UUID.randomUUID().toString() }
            .subscribeOn(AppSchedulers.ui())
            .observeOn(AppSchedulers.ui())
            .flatMap { requestCode ->
                singleCallable {
                    val dialog = ChoiceChainDialogFragment
                        .newInstance(
                            requestCode = requestCode,
                            chains = chains.map(BaseChain::getChain)
                        )
                    viewState.showDialog(dialog, "dialog", true)
                    requestCode
                }
            }
            .flatMapObservable(screensInteractor::observeResult)
            .compositeSubscribe(
                onNext = { result ->
                    (result as? BaseChain)?.let { chain ->
                        createAccount(chain, address)
                    }
                }
            )
    }

    private fun createAccount(chain: BaseChain, address: String) {
        viewState.showWaitDialog()
        singleCallable { accountsInteractor.getAccountsByChain(chain) }
            .flatMapCompletable { chains ->
                if (chains.size >= 5) {
                    Completable.error(MaxAccountsException())
                } else {
                    accountsInteractor.createEmptyAccount(
                        chain,
                        address
                    )
                }
            }
            .withDefaults()
            .doAfterTerminate { viewState.hideWaitDialog() }
            .compositeSubscribe(
                onSuccess = viewState::showMainActivity,
                onError = object : OnErrorConsumer() {
                    override fun onError(error: Throwable) {
                        viewState.showMessage(
                            when (error) {
                                is MaxAccountsException -> R.string.error_max_account_number
                                is DuplicateAccountException -> R.string.error_already_imported_address
                                else -> R.string.error_import_errer
                            }
                        )
                    }
                }
            )
    }
}