package com.fulldive.wallet.presentation.accounts

import com.fulldive.wallet.extensions.withDefaults
import com.fulldive.wallet.interactors.QRCodeInteractor
import com.fulldive.wallet.presentation.base.BaseMoxyPresenter

class AccountShowPresenter : BaseMoxyPresenter<AccountShowMoxyView>() {

    lateinit var address: String

    override fun onFirstViewAttach() {
        QRCodeInteractor
            .generate(address)
            .withDefaults()
            .compositeSubscribe(onSuccess = viewState::showQRCode)
    }
}