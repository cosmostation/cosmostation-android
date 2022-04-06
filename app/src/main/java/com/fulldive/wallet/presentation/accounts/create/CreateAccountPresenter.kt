package com.fulldive.wallet.presentation.accounts.create

import com.fulldive.wallet.di.modules.DefaultPresentersModule
import com.fulldive.wallet.extensions.completeCallable
import com.fulldive.wallet.extensions.letOr
import com.fulldive.wallet.extensions.singleCallable
import com.fulldive.wallet.extensions.withDefaults
import com.fulldive.wallet.interactors.ScreensInteractor
import com.fulldive.wallet.interactors.accounts.AccountsInteractor
import com.fulldive.wallet.interactors.accounts.DuplicateAccountException
import com.fulldive.wallet.interactors.secret.SecretInteractor
import com.fulldive.wallet.models.local.AccountSecrets
import com.fulldive.wallet.presentation.base.BaseMoxyPresenter
import com.fulldive.wallet.presentation.chains.choicenet.ChoiceNetDialogFragment
import com.fulldive.wallet.rx.AppSchedulers
import com.joom.lightsaber.ProvidedBy
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.utils.WLog
import java.util.*
import javax.inject.Inject

@ProvidedBy(DefaultPresentersModule::class)
class CreateAccountPresenter @Inject constructor(
    private val accountsInteractor: AccountsInteractor,
    private val secretInteractor: SecretInteractor,
    private val screensInteractor: ScreensInteractor
) : BaseMoxyPresenter<CreateAccountMoxyView>() {
    var chain: BaseChain? = null
    private var accountSecrets: AccountSecrets? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        chain.letOr({ chain ->
            viewState.showChain(chain)
            generateMnemonic(chain)
        }, ::requestChain)
    }

    fun onShowMnemonicClicked() {
        accountsInteractor
            .checkExistsPassword()
            .withDefaults()
            .compositeSubscribe(
                onSuccess = { hasPassword ->
                    if (hasPassword) {
                        viewState.requestCheckPassword()
                    } else {
                        viewState.requestCreatePassword()
                    }
                }
            )
    }

    fun onCreateAccountClicked() {
        val secrets = accountSecrets
        val chain = this.chain
        if (secrets != null && chain != null) {
            viewState.showWaitDialog()
            accountsInteractor
                .createAccount(chain, secrets)
                .withDefaults()
                .doOnError {
                    viewState.hideWaitDialog()
                }
                .compositeSubscribe(
                    onSuccess = {
                        viewState.showMainActivity()
                        viewState.finish()
                    },
                    onError = object : OnErrorConsumer() {
                        override fun onError(error: Throwable) {
                            when (error) {
                                is DuplicateAccountException -> viewState.showMessage(R.string.error_duplicate_account)
                                else -> super.onError(error)
                            }
                        }
                    }
                )
        }
    }

    fun onCheckPasswordSuccessfully() {
        accountSecrets?.let { secrets ->
            viewState.showMnemonic(secrets.mnemonic)
        }
    }

    fun onSetPasswordSuccessfully() {
        accountSecrets?.let { secrets ->
            viewState.showMnemonic(secrets.mnemonic)
        }
    }

    private fun requestChain() {
        singleCallable { UUID.randomUUID().toString() }
            .subscribeOn(AppSchedulers.ui())
            .observeOn(AppSchedulers.ui())
            .flatMap { requestCode ->
                completeCallable {
                    val dialog = ChoiceNetDialogFragment.newInstance(false, requestCode)
                    viewState.showDialog(dialog, "dialog", false)
                }
                    .toSingleDefault(requestCode)
            }
            .flatMapObservable(screensInteractor::observeResult)
            .compositeSubscribe(
                onNext = { result ->
                    onChainReceived(result as? BaseChain)
                },
                onError = object : OnErrorConsumer() {
                    override fun onError(error: Throwable) {
                        super.onError(error)
                        onChainReceived(null)
                    }
                }
            )
    }

    private fun generateMnemonic(chain: BaseChain) {
        viewState.showWaitDialog()
        WLog.w("fftf, generateMnemonic: $chain")
        secretInteractor
            .createSecrets(chain)
            .withDefaults()
            .doAfterTerminate {
                viewState.hideWaitDialog()
            }
            .compositeSubscribe(
                onSuccess = ::onAccountSecretsReceived,
                onError = object : OnErrorConsumer() {
                    override fun onError(error: Throwable) {
                        super.onError(error)
                        viewState.finish()
                    }
                }
            )
    }

    private fun onChainReceived(baseChain: BaseChain?) {
        if (baseChain == null) {
            viewState.finish()
        } else {
            this.chain = baseChain
            viewState.showChain(baseChain)
            generateMnemonic(baseChain)
        }
    }

    private fun onAccountSecretsReceived(accountSecrets: AccountSecrets) {
        this.accountSecrets = accountSecrets
        WLog.w("fftf, generateMnemonic: $chain => ${accountSecrets.address}")
        viewState.showAccountAddress(accountSecrets.address)
    }
}