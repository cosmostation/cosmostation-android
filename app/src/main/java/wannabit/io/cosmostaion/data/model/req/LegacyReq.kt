package wannabit.io.cosmostaion.data.model.req

import com.fasterxml.jackson.annotation.JsonInclude
import com.google.gson.GsonBuilder
import java.nio.charset.Charset

data class BroadcastReq(
    var mode: String,
    var tx: StdTxValue?
)

data class StdSignMsg (
    var chain_id: String,
    var account_number: String?,
    var sequence: String?,
    var fee: LFee,
    var msgs: MutableList<Msg>,
    var memo: String? = ""
) {
    fun toSignByte(): ByteArray {
        val presenter = GsonBuilder().disableHtmlEscaping().create()
        return presenter.toJson(this).toByteArray(Charset.forName("UTF-8"))
    }
}

data class Signature(
    var pub_key: PubKey,
    var signature: String?,
    var account_number: String?,
    var sequence: String?
)

data class PubKey(
    var type: String,
    var value: String?
)

data class StdTx(
    val type: String?,
    val value: StdTxValue?
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class StdTxValue(
    val msg: MutableList<Msg>? = null,
    val fee: LFee,
    val signatures: MutableList<Signature>,
    val memo: String?
)

class Msg {
    var type: String? = ""
    var value: Value? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class Value {
    var from_address: String? = null
    var to_address: String? = null
    var amount: MutableList<LCoin>? = null
    var delegator_address: String? = null
    var quantity: LCoin? = null
    var validator_addresses: MutableList<String?>? = null
}

data class LCoin(var denom: String, var amount: String)
data class LFee(var gas: String, var amount: MutableList<LCoin>)