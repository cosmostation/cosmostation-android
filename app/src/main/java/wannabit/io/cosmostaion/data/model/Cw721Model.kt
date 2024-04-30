package wannabit.io.cosmostaion.data.model

import com.google.gson.JsonObject

data class Cw721Model(
    var info: JsonObject,
    var tokens: MutableList<Cw721TokenModel>
) {
    fun sortId() {
        tokens.sortBy { it.tokenId }
    }
}

data class Cw721TokenModel(
    val tokenId: String = "",
    val tokenInfo: JsonObject?,
    val tokenDetail: JsonObject?
)
