package wannabit.io.cosmostaion.chain

import com.google.gson.JsonObject
import wannabit.io.cosmostaion.common.BaseData
import java.math.BigDecimal
import java.math.RoundingMode

class BtcFetcher(private val chain: BaseChain) : CosmosFetcher(chain) {

    var btcBalances = BigDecimal.ZERO
    var btcPendingInput = BigDecimal.ZERO
    var btcPendingOutput = BigDecimal.ZERO
    var btcBlockHeight: Long = 0
    var btcHistory: MutableList<JsonObject> = mutableListOf()

    var bitState = true

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        val price = BaseData.getPrice(chain.coinGeckoId, isUsd)
        return (btcBalances.add(btcPendingInput)).multiply(price).setScale(8, RoundingMode.DOWN)
    }

    fun mempoolUrl(): String {
        if (chain.isTestnet) {
            return "https://mempool.space/testnet/"
        }
        return "https://mempool.space/"
    }
}