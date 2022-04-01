package com.fulldive.wallet.interactors

import android.graphics.Bitmap
import com.fulldive.wallet.extensions.safeSingle
import com.fulldive.wallet.extensions.toBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import io.reactivex.Single

object QRCodeInteractor {
    private const val DEFAULT_SIZE = 480

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
}