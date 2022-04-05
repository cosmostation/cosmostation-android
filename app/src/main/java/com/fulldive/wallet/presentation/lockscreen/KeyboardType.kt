package com.fulldive.wallet.presentation.lockscreen

sealed class KeyboardType(val id: Int) {
    object Numeric : KeyboardType(0)
    object Alphabet : KeyboardType(1)
}
