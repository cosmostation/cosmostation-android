package com.fulldive.wallet.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus.or { View(baseContext) }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
    view.clearFocus()
}
