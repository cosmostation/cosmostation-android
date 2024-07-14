package wannabit.io.cosmostaion.chain

import kotlinx.coroutines.Job
import java.math.BigDecimal

open class FetcherLcd(chain: BaseChain) {

    var chain: BaseChain

    init {
        this.chain = chain
    }

    open fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return BigDecimal.ZERO
    }

    open fun lcdBalanceAmount(denom: String?): BigDecimal {
        return BigDecimal.ZERO
    }

    open fun loadValidators(): Job {
        return Job()
    }

    fun getLcd(): String {
        return chain.lcdUrl
    }
}