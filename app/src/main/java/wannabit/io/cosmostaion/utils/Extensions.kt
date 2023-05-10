package wannabit.io.cosmostaion.utils

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import wannabit.io.cosmostaion.R
import java.math.BigDecimal
import java.math.RoundingMode

fun View.visibleOrGone(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.visibleOrInvisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun AppCompatActivity.makeToast(id: Int) {
    Toast.makeText(this, this.getString(id), Toast.LENGTH_SHORT).show()
}

fun Context.makeToast(id: Int) {
    Toast.makeText(this, this.getString(id), Toast.LENGTH_SHORT).show()
}

fun EditText.amountWatcher(
    c: Context, inputText: EditText, onUpdateOutputView: Unit, inDecimalChecker: String, inDecimalSetter: String, availableMaxAmount: BigDecimal, inputDecimal: Int
) {
    inputText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(et: Editable?) {
            onUpdateOutputView
            val es = et.toString().trim()
            if (TextUtils.isEmpty(es)) {
                inputText.background = ContextCompat.getDrawable(c, R.drawable.edittext_box)
            } else if (es.startsWith(".")) {
                inputText.background = ContextCompat.getDrawable(c, R.drawable.edittext_box)
                inputText.setText("")
            } else if (es.endsWith(".")) {
                inputText.background = ContextCompat.getDrawable(c, R.drawable.edittext_box_error)
                inputText.visibility = View.VISIBLE
            } else if (inputText.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                inputText.setText("0")
                inputText.setSelection(1)
            }
            if (es == inDecimalChecker) {
                inputText.setText(inDecimalSetter)
                inputText.setSelection(inputDecimal + 1)
            } else {
                try {
                    val inputAmount = BigDecimal(es)
                    if (BigDecimal.ZERO >= inputAmount) {
                        inputText.background = ContextCompat.getDrawable(c, R.drawable.edittext_box_error)
                        return
                    }
                    val checkPosition = inputAmount.movePointRight(inputDecimal)
                    val checkMax = checkPosition.setScale(0, RoundingMode.DOWN)
                    if (checkPosition.compareTo(checkMax) != 0 || checkPosition != checkMax) {
                        val recover = es.substring(0, es.length - 1)
                        inputText.setText(recover)
                        inputText.setSelection(recover.length)
                        return
                    }
                    if (inputAmount > availableMaxAmount.movePointLeft(inputDecimal).setScale(inputDecimal, RoundingMode.CEILING)) {
                        inputText.background = ContextCompat.getDrawable(c, R.drawable.edittext_box_error)
                    } else {
                        inputText.background = ContextCompat.getDrawable(c, R.drawable.edittext_box)
                    }
                    inputText.setSelection(inputText.text.length)
                } catch (_: Exception) {
                }
            }
        }
    })
}