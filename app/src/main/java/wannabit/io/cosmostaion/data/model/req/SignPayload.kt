package wannabit.io.cosmostaion.data.model.req

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName


data class SignPayload(
    val chain_id: String,
    val account_number: String,
    val sequence: String,
    val fee: Fee,
    val msgs: MutableList<Msgs>?,
    val memo: String? = ""
) {
    fun toJson(): String {
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(this)
    }
}

data class Fee(
    val gas_wanted: String,
    val gas_fee: String
)

data class Msgs(
    @SerializedName("@type")
    val type: String,
    val from_address: String,
    val to_address: String,
    val amount: String
)
