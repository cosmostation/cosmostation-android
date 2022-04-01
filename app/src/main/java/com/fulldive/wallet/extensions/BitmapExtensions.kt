package com.fulldive.wallet.extensions

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.common.BitMatrix

fun BitMatrix.toBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
    for (x in 0 until width) {
        for (y in 0 until height) {
            bitmap.setPixel(x, y, if (this[x, y]) Color.BLACK else Color.WHITE)
        }
    }
    return bitmap
}
