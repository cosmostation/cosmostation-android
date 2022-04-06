package com.fulldive.wallet.presentation.accounts.create

import androidx.fragment.app.DialogFragment
import com.fulldive.wallet.presentation.base.BaseMoxyView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import wannabit.io.cosmostaion.base.BaseChain

interface CreateAccountMoxyView : BaseMoxyView {
    @AddToEndSingle
    fun showAccountAddress(address: String)

    @AddToEndSingle
    fun showChain(chain: BaseChain)

    @AddToEndSingle
    fun showMnemonic(mnemonicWords: List<String>)

    @OneExecution
    fun showWaitDialog()

    @OneExecution
    fun hideWaitDialog()

    @OneExecution
    fun showDialog(
        dialogFragment: DialogFragment,
        tag: String,
        cancelable: Boolean
    )

    @OneExecution
    fun requestCheckPassword()

    @OneExecution
    fun requestCreatePassword()

    @OneExecution
    fun showMainActivity()

    @OneExecution
    fun finish()
}
