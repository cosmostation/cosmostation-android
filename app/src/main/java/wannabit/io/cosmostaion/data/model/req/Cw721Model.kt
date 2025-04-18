package wannabit.io.cosmostaion.data.model.req

import com.google.gson.JsonObject
import wannabit.io.cosmostaion.data.model.res.Cw721

data class Cw721Model(
    var info: Cw721,
    var tokens: MutableList<Cw721TokenModel>
) {
    fun sortId() {
        tokens.sortBy { it.tokenId.toDouble() }
    }
}

data class Cw721TokenModel(
    val tokenId: String = "",
    val tokenInfo: JsonObject?,
    val tokenDetail: JsonObject?
)
