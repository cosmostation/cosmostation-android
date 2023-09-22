package wannabit.io.cosmostaion.common

import android.content.Context
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.data.model.Asset
import wannabit.io.cosmostaion.data.model.NetworkResult
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun formatString(input: String, point: Int): SpannableString {
    val spannableString = SpannableString(input)
    spannableString.setSpan(RelativeSizeSpan(0.8f), spannableString.length - point, spannableString.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    return spannableString
}

fun formatAmount(input: String, decimal: Int): SpannableString {
    val spannableString = SpannableString(getDecimalFormat(decimal).format(input.toBigDecimal()))
    spannableString.setSpan(RelativeSizeSpan(0.8f), spannableString.length - decimal, spannableString.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    return spannableString
}

fun assetValue(value: BigDecimal): SpannableString {
    val formatted = BaseData.currencySymbol() + " " + getDecimalFormat(3).format(value)
    return formatString(formatted, 3)
}

fun formatAssetValue(value: BigDecimal): SpannableString {
    val spannableString = assetValue(value)
    spannableString.setSpan(RelativeSizeSpan(0.8f), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    return spannableString
}

fun priceChangeStatus(lastUpDown: BigDecimal): SpannableString {
    return if (BigDecimal.ZERO > lastUpDown) {
        formatString("- " + lastUpDown.toString().replace("-", "") + "%", 3)
    } else {
        formatString("+ $lastUpDown%", 3)
    }
}

fun TextView.priceChangeStatusColor(lastUpDown: BigDecimal) {
    if (BigDecimal.ZERO > lastUpDown) {
        setTextColor(ContextCompat.getColorStateList(context, R.color.color_accent_red))
    } else {
        setTextColor(ContextCompat.getColorStateList(context, R.color.color_accent_green))
    }
}

fun ImageView.setTokenImg(asset: Asset) {
    Picasso.get().load(BaseConstant.CHAIN_BASE_URL + asset.image).error(R.drawable.token_default).into(this)
}

fun ImageView.setTokenImg(tokenImg: String) {
    Picasso.get().load(tokenImg).error(R.drawable.token_default).into(this)
}

fun AppCompatActivity.makeToast(id: Int) {
    Toast.makeText(this, this.getString(id), Toast.LENGTH_SHORT).show()
}

fun Context.makeToast(id: Int) {
    Toast.makeText(this, this.getString(id), Toast.LENGTH_SHORT).show()
}

fun Context.makeToast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun formatCurrentTimeToYear(): String {
    val locale = Locale.getDefault()
    val date = Calendar.getInstance()
    val dateFormat = SimpleDateFormat(
        if (locale == Locale.US) "MMMM dd, yyyy" else "yyyy.M.d",
        locale
    )
    return dateFormat.format(date.time)
}

fun formatTxTimeToYear(context: Context, timeString: String): String {
    val locale = Locale.getDefault()
    val inputFormat = SimpleDateFormat(context.getString(R.string.str_tx_time_format))
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = inputFormat.parse(timeString)

    val outputFormat = SimpleDateFormat(
        if (locale == Locale.US) "MMMM dd, yyyy" else "yyyy.M.d",
        locale
    )
    outputFormat.timeZone = TimeZone.getDefault()
    return outputFormat.format(date)
}

fun formatTxTimeToHour(context: Context, timeString: String): String {
    val inputFormat = SimpleDateFormat(context.getString(R.string.str_tx_time_format))
    val outputFormat = SimpleDateFormat(context.getString(R.string.str_dp_time_format2))
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    return outputFormat.format(inputFormat.parse(timeString))
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

fun View.goneOrVisible(visible: Boolean) {
    visibility = if (visible) View.GONE else View.VISIBLE
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

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): NetworkResult<T> {
    return try {
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

fun getDecimalFormat(cnt: Int): DecimalFormat {
    val formatter = NumberFormat.getNumberInstance(Locale.US)
    val decimalFormat = formatter as DecimalFormat
    decimalFormat.roundingMode = RoundingMode.DOWN
    when (cnt) {
        0 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0")
        1 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0")
        2 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00")
        3 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000")
        4 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000")
        5 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000")
        6 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000")
        7 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000")
        8 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000")
        9 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000")
        10 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000000")
        11 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000000")
        12 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000000")
        13 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000000000")
        14 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000000000")
        15 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000000000")
        16 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000000000000")
        17 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000000000000")
        18 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000000000000")
        else -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000")
    }
    return decimalFormat
}

