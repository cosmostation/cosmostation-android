package com.fulldive.wallet.presentation.lockscreen

import com.fulldive.wallet.di.modules.DefaultPresentersModule
import com.fulldive.wallet.extensions.withDefaults
import com.fulldive.wallet.interactors.secret.SecretInteractor
import com.fulldive.wallet.presentation.base.BaseMoxyPresenter
import com.joom.lightsaber.ProvidedBy
import moxy.MvpAppCompatActivity
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.utils.WLog
import wannabit.io.cosmostaion.utils.WUtil
import javax.inject.Inject

@ProvidedBy(DefaultPresentersModule::class)
class SetPasswordPresenter @Inject constructor(
    private val secretInteractor: SecretInteractor
) : BaseMoxyPresenter<SetPasswordMoxyView>() {
    private var userInput: String = ""
    private var confirmInput: String = ""
    private var isConfirmSequence = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setCheckPasswordHintVisible(false)
        clear()
    }

    fun onUserInsertKey(input: Char) {
        var updateField = when {
            userInput.isEmpty() -> {
                userInput = input.toString()
                true
            }
            userInput.length < 5 -> {
                userInput += input
                true
            }
            else -> false
        }

        when {
            userInput.length == 4 -> {
                viewState.switchKeyboard(KeyboardType.Alphabet)
            }
            userInput.length == 5 && WUtil.checkPasscodePattern(userInput) -> {
                checkPassword()
            }
            userInput.length == 5 -> {
                clear()
                updateField = false
            }
        }
        if (updateField) {
            viewState.updatePasswordField(userInput.length)
        }
    }

    fun onBackPressed() {
        when {
            userInput.isNotEmpty() -> userDeleteKey()
            else -> {
                viewState.finishWithResult(MvpAppCompatActivity.RESULT_CANCELED)
            }
        }
    }

    fun userDeleteKey() {
        when {
            userInput.isEmpty() -> onBackPressed()
            userInput.length == 4 -> {
                userInput = userInput.substring(0, userInput.length - 1)
                viewState.switchKeyboard(KeyboardType.Numeric)
            }
            else -> {
                userInput = userInput.substring(0, userInput.length - 1)
            }
        }
        viewState.updatePasswordField(userInput.length)
    }

    fun onShakeEnded() {
        clear()
    }

    private fun clear() {
        userInput = ""
        viewState.clear()
        viewState.switchKeyboard(KeyboardType.Numeric)
    }

    private fun checkPassword() {
        if (isConfirmSequence) {
            viewState.setCheckPasswordHintVisible(false)
            if (confirmInput == userInput) {
                viewState.showWaitDialog()
                secretInteractor
                    .setPassword(userInput)
                    .withDefaults()
                    .compositeSubscribe(
                        onSuccess = {
                            WLog.w("Account was saved")
                            viewState.finishWithResult(MvpAppCompatActivity.RESULT_OK)
                        }
                    ) {
                        viewState.hideWaitDialog()
                        viewState.shakeView()
                        isConfirmSequence = false
                        viewState.showMessage(R.string.str_unknown_error_msg)
                    }
            } else {
                viewState.shakeView()
                isConfirmSequence = false
                viewState.showMessage(R.string.error_msg_password_not_same)
            }
        } else {
            isConfirmSequence = true
            confirmInput = userInput
            userInput = ""
            clear()
            viewState.shuffleKeyboard()
            viewState.setCheckPasswordHintVisible(true)
        }
    }
}