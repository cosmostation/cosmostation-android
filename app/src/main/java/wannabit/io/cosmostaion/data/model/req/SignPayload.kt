package wannabit.io.cosmostaion.data.model.req

import com.fasterxml.jackson.annotation.JsonInclude
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val from_address: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val to_address: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val amount: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val send: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val caller: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val pkg_path: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val func: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val args: List<String>? = null,
)
