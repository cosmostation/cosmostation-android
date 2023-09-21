package wannabit.io.cosmostaion.utils

import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import cosmos.authz.v1beta1.Authz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.model.NetworkResult
import java.io.Serializable
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

fun Context.makeToast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

suspend fun <T> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
    return withContext(Dispatchers.IO) {
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

fun <T: Serializable> Intent.intentSerializable(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, clazz)
    } else {
        this.getSerializableExtra(key) as T?
    }
}

// Authz Type
fun setAuthzType(grant: Authz.GrantAuthorization): String? {
    grant.authorization.typeUrl?.let { url ->
        val authorizationValue = grant.authorization.value
        return when {
            url.contains(Authz.GenericAuthorization.getDescriptor().fullName) -> {
                Authz.GenericAuthorization.parseFrom(authorizationValue)?.let { generic ->
                    when {
                        generic.msg.contains("Send") -> "Send"
                        generic.msg.contains("Delegate") -> "Delegate"
                        generic.msg.contains("Undelegate") -> "Undelegate"
                        generic.msg.contains("Redelegate") -> "Redelegate"
                        generic.msg.equals("/cosmos.gov.v1beta1.MsgVote") -> "Vote"
                        generic.msg.equals("/cosmos.gov.v1beta1.MsgVoteWeighted") -> "Vote Weighted"
                        generic.msg.contains("WithdrawDelegatorReward") -> "Claim Reward"
                        generic.msg.contains("WithdrawValidatorCommission") -> "Claim Commission"
                        else -> "Unknown"
                    }
                }
            }
            url.contains(cosmos.bank.v1beta1.Authz.SendAuthorization.getDescriptor().fullName) -> "Send"
            else -> {
                cosmos.staking.v1beta1.Authz.StakeAuthorization.parseFrom(authorizationValue)?.let { stake ->
                    when (stake.authorizationType) {
                        cosmos.staking.v1beta1.Authz.AuthorizationType.AUTHORIZATION_TYPE_DELEGATE -> "Delegate"
                        cosmos.staking.v1beta1.Authz.AuthorizationType.AUTHORIZATION_TYPE_REDELEGATE -> "Redelegate"
                        cosmos.staking.v1beta1.Authz.AuthorizationType.AUTHORIZATION_TYPE_UNDELEGATE -> "Undelegate"
                        else -> "Unknown"
                    }
                }
            }
        }
    }
    return "Unknown"
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