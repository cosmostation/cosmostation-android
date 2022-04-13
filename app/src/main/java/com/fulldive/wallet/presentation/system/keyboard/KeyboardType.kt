package com.fulldive.wallet.presentation.system.keyboard

sealed class KeyboardType(val id: Int) {
    object Numeric : KeyboardType(0)
    object Alphabet : KeyboardType(1)
}
