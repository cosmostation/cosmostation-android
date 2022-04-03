package com.fulldive.wallet.interactors

import android.graphics.Bitmap
import com.fulldive.wallet.di.modules.DefaultInteractorsModule
import com.fulldive.wallet.extensions.safeSingle
import com.fulldive.wallet.extensions.toBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ProvidedBy(DefaultInteractorsModule::class)
class QRCodeInteractor @Inject constructor() {
    fun generate(
        text: String,
        size: Int = DEFAULT_SIZE,
        format: BarcodeFormat = BarcodeFormat.QR_CODE
    ): Single<Bitmap> {
        return safeSingle {
            QRCodeWriter()
                .encode(text, format, size, size)
                .toBitmap()
        }
    }

    companion object {
        private const val DEFAULT_SIZE = 480
    }
}