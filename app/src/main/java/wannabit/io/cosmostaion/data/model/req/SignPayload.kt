package wannabit.io.cosmostaion.data.model.req

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
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
    val max_deposit: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val pkg_path: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val func: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val args: List<String>? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val creator: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("package")
    val `package`: MemPackage? = null,
)

data class MemPackage(
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val name: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val path: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val files: List<File>? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val type: AnyValue? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val info: AnyValue? = null
)

data class File(
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val name: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val body: String? = null
)

data class AnyValue(
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val type_url: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val value: String? = null
)
