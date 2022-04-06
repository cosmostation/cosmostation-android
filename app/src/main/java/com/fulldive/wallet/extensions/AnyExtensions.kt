package com.fulldive.wallet.extensions

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import wannabit.io.cosmostaion.utils.WLog

fun Any?.isNotNull(): Boolean {
    return null != this
}

fun Bundle?.orEmpty(): Bundle = this ?: Bundle.EMPTY
fun Boolean?.orFalse() = this ?: false
fun Boolean?.orTrue() = this ?: true
inline fun <T> Boolean?.ifTrue(block: () -> T) = if (this == true) block.invoke() else null
inline fun <T> Boolean?.ifFalse(block: () -> T) = if (this == false) block.invoke() else null

fun <T> T?.or(value: T) = this ?: value
inline fun <T> T?.or(block: () -> T) = this ?: block.invoke()
inline fun <T, R> T?.letOr(block: (T) -> R, blockElse: () -> R): R {
    return if (this != null) block.invoke(this) else blockElse.invoke()
}

fun String?.orNull(): String? = if (this.isNullOrEmpty()) null else this
fun CharSequence?.orEmpty(): CharSequence = this ?: ""
fun CharSequence?.orEmptyString(): String = this.orEmpty().toString()

fun Context.getColorCompat(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)
fun Context.getDrawableCompat(resId: Int) = ContextCompat.getDrawable(this, resId)

inline fun <R> safe(block: () -> R?): R? {
    return try {
        block.invoke()
    } catch (ex: Exception) {
        ex.printStackTrace()
        WLog.e(ex.toString())
        null
    }
}

fun Context.toast(message: Int): Toast = Toast
    .makeText(this.applicationContext, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }

fun Context.toast(message: CharSequence): Toast = Toast
    .makeText(this.applicationContext, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }


fun Context.longToast(message: Int): Toast = Toast
    .makeText(this.applicationContext, message, Toast.LENGTH_LONG)
    .apply {
        show()
    }

fun Context.longToast(message: CharSequence): Toast = Toast
    .makeText(this.applicationContext, message, Toast.LENGTH_LONG)
    .apply {
        show()
    }

inline fun <reified T> unsafeLazy(noinline initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)

fun <A, B> combine(a: A, b: B) = a to b
fun <A, B, C> combine(a: A, b: B, c: C) = Triple(a, b, c)