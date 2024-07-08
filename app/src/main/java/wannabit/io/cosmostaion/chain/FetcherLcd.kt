package wannabit.io.cosmostaion.chain

import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.math.BigDecimal

open class FetcherLcd(chain: BaseChain) {

    var chain: BaseChain

    var lcdNodeInfo: JsonObject? = JsonObject()

    init {
        this.chain = chain
    }

    open fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return BigDecimal.ZERO
    }

    open fun tokenValue(address: String, isUsd: Boolean? = false): BigDecimal {
        return BigDecimal.ZERO
    }

    open fun allTokenValue(isUsd: Boolean?): BigDecimal {
        return BigDecimal.ZERO
    }

    open fun lcdBalanceAmount(denom: String?): BigDecimal {
        return BigDecimal.ZERO
    }

    open fun loadValidators(): Job {
        return Job()
    }
}