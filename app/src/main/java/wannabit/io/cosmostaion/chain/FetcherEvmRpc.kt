package wannabit.io.cosmostaion.chain

import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal

class FetcherEvmRpc(chain: BaseChain) {

    var chain: BaseChain

    var evmBalance = BigDecimal.ZERO
    var evmTokens = mutableListOf<Token>()

    var web3j: Web3j? = null

    init {
        this.chain = chain
    }

    fun getEvmRpc(): String {
        val endpoint = Prefs.getEvmRpcEndpoint(chain)
        return if (endpoint?.isNotEmpty() == true) {
            endpoint
        } else {
            chain.evmRpcURL
        }
    }
}