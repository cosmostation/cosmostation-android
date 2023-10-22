package wannabit.io.cosmostaion.data.model.res

import java.math.BigDecimal

data class FeeInfo(
    var title: String? = "",
    var msg: String? = "",
    var feeDatas: List<FeeData> = mutableListOf()
) {
    constructor(data: String?) : this() {
        if (data == null) return
        feeDatas = data.split(",").map { FeeData(it) }
    }
}

data class FeeData(
    var denom: String? = null,
    var gasRate: BigDecimal? = null
) {
    constructor(data: String?) : this() {
        val regex = Regex("[0-9]*\\.?[0-9]*")
        val matchResult = regex.find(data ?: "")
        if (matchResult != null) {
            val rawGasRate = matchResult.value
            val denomIndex = rawGasRate.length

            denom = data?.substring(denomIndex)
            gasRate = BigDecimal(rawGasRate)
        }
    }
}