package wannabit.io.cosmostaion.common

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.data.model.NetworkResult
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun AppCompatActivity.makeToast(id: Int) {
    Toast.makeText(this, this.getString(id), Toast.LENGTH_SHORT).show()
}

fun Context.makeToast(id: Int) {
    Toast.makeText(this, this.getString(id), Toast.LENGTH_SHORT).show()
}

fun Context.makeToast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Date.formatToViewTimeDefaults(): String {
    val sdf = SimpleDateFormat("MMM dd, hh:mm aa", Locale.US)
    return sdf.format(this)
}

fun BigInteger.formatGasDecimal(): String {
    return this.formatDecimal(9, 9)
}

fun String.formatDecimal(decimal: Int = 9, trim: Int = 3): String {
    return BigInteger(this).formatDecimal(decimal, trim)
}

fun BigInteger.formatDecimal(decimal: Int = 9, trim: Int = 3): String {
    if (this <= BigInteger("0")) {
        return "0.0"
    }
    return BigDecimal(this).multiply(BigDecimal(0.1).pow(decimal)).setScale(trim, RoundingMode.DOWN)
        .toString()
}

fun String.parseDecimal(decimal: Int = 9): BigInteger {
    return BigDecimal(this).multiply(BigDecimal(10).pow(decimal)).toBigInteger()
}

fun View.visibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.visibleOrInvisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun EditText.addDecimalCheckListener(max: () -> String, decimal: Int) {
    val editText = this
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let {
                if (it.isBlank()) {
                    return
                }

                val inputAmount = BigDecimal(it.toString())
                val available = BigDecimal(max())
                if (inputAmount > available) {
                    editText.setText(max())
                    editText.setSelection(editText.length())
                }
                if (inputAmount.scale() > decimal) {
                    editText.setText(
                        BigDecimal(it.toString()).setScale(decimal, RoundingMode.DOWN).toString()
                    )
                    editText.setSelection(editText.length())
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }
    })
}

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): NetworkResult<T> {
    return withContext(dispatcher) {
        try {
            val response = apiCall.invoke()

            response?.let {
                NetworkResult.Success(it)
            } ?: run {
                NetworkResult.Error("Response Empty", "No Response")
            }

        } catch (e: Exception) {
            NetworkResult.Error("Unknown Error", e.message ?: "Unknown error occurred.")
        }
    }
}