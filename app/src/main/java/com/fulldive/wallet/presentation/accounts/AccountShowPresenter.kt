package com.fulldive.wallet.presentation.accounts

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.fulldive.wallet.di.modules.DefaultPresentersModule
import com.fulldive.wallet.extensions.withDefaults
import com.fulldive.wallet.interactors.QRCodeInteractor
import com.fulldive.wallet.presentation.base.BaseMoxyPresenter
import com.joom.lightsaber.ProvidedBy
import wannabit.io.cosmostaion.R
import javax.inject.Inject

@ProvidedBy(DefaultPresentersModule::class)
class AccountShowPresenter @Inject constructor(
    private val qrCodeInteractor: QRCodeInteractor
) : BaseMoxyPresenter<AccountShowMoxyView>() {

    lateinit var address: String

    override fun onFirstViewAttach() {
        qrCodeInteractor
            .generate(address)
            .withDefaults()
            .compositeSubscribe(onSuccess = viewState::showQRCode)
    }

    fun onCopyButtonClicked(context: Context) {
        (context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
            .setPrimaryClip(ClipData.newPlainText("address", address))
        viewState.showMessage(R.string.str_copied)
        viewState.dismiss()
    }
}