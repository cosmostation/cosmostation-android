package wannabit.io.cosmostaion.data.model.res

import android.content.Context
import android.text.TextUtils
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import wannabit.io.cosmostaion.R

@JsonClass(generateAdapter = true)
data class BnbHistoryResponse(val tx: MutableList<BnbHistory>?)

data class BnbHistory(
    val txHash: String?,
    val blockHeight: Int?,
    val txType: String?,
    val timeStamp: String?,
    @Json(name = "fromAddr") val fromAddress: String?,
    @Json(name = "toAddr") val toAddress: String?,
    val code: Int?
) {

    fun isSuccess(): Boolean {
        code?.let {
            if (it > 0) {
                return true
            }
            return false
        }
        return false
    }

    fun bnbTxType(context: Context, address: String?): String {
        return when (txType) {
            "HTL_TRANSFER" -> {
                when {
                    fromAddress == address -> context.getString(R.string.tx_send_htlc)
                    toAddress == address -> context.getString(R.string.tx_receive_htlc)
                    else -> context.getString(R.string.tx_create_htlc)
                }
            }

            "NEW_ORDER" -> context.getString(R.string.tx_new_order)
            "CANCEL_ORDER" -> context.getString(R.string.tx_Cancel_order)
            "TRANSFER" -> {
                if (!TextUtils.isEmpty(fromAddress) && fromAddress == address) {
                    context.getString(R.string.tx_send)
                } else {
                    context.getString(R.string.tx_receive)
                }
            }

            "CLAIM_HTL" -> context.getString(R.string.tx_claim_htlc)
            "REFUND_HTL" -> context.getString(R.string.tx_refund_htlc)
            else -> context.getString(R.string.tx_unknown)
        }
    }
}