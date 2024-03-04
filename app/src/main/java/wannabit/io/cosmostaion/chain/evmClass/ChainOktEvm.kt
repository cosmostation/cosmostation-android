package wannabit.io.cosmostaion.chain.evmClass

import android.content.Context
import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_BASE_FEE
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.data.model.res.OktAccountResponse
import wannabit.io.cosmostaion.data.model.res.OktDepositedResponse
import wannabit.io.cosmostaion.data.model.res.OktTokenResponse
import wannabit.io.cosmostaion.data.model.res.OktWithdrawResponse
import java.math.BigDecimal
import java.math.RoundingMode

class ChainOktEvm : EthereumLine() {

    var oktLcdAccountInfo: OktAccountResponse? = null
    var oktDepositedInfo: OktDepositedResponse? = null
    var oktWithdrawInfo: OktWithdrawResponse? = null
    var oktTokenInfo: OktTokenResponse? = null

    override var supportCosmos: Boolean = true
    override var name: String = "OKT"
    override var chainId: String = "exchain-66"
    override var tag: String = "okt60_Keccak"
    override var logo: Int = R.drawable.chain_okt
    override var swipeLogo: Int = R.drawable.chain_swipe_okt
    override var apiName: String = "okc"
    override var stakeDenom: String? = "okt"

    override var coinSymbol: String = "OKT"
    override var coinGeckoId: String = "oec-token"
    override var coinLogo: Int = R.drawable.token_okt
    override var supportStaking = false

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "ex"

    val lcdUrl = "https://exchainrpc.okex.org/okexchain/v1/"
    override var rpcUrl: String = "https://exchainrpc.okex.org"

    fun assetImg(originSymbol: String): String {
        return CosmostationConstants.CHAIN_BASE_URL + "okc/asset/" + originSymbol.lowercase() + ".png"
    }

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return lcdBalanceValue(stakeDenom, isUsd).add(lcdOktDepositValue(isUsd))
            .add(lcdOktWithdrawValue(isUsd))
    }

    override fun isTxFeePayable(c: Context): Boolean {
        val availableAmount = lcdBalanceAmount(stakeDenom)
        return availableAmount > BigDecimal(OKT_BASE_FEE)
    }

    fun lcdBalanceAmount(denom: String?): BigDecimal {
        val balance = oktLcdAccountInfo?.value?.coins?.firstOrNull { it.denom == denom }
        if (balance != null) {
            return balance.amount.toBigDecimal()
        }
        return BigDecimal.ZERO
    }

    override fun lcdBalanceValue(denom: String?, isUsd: Boolean?): BigDecimal {
        denom?.let {
            if (it == stakeDenom) {
                val amount = lcdBalanceAmount(denom)
                val price = BaseData.getPrice(OKT_GECKO_ID, isUsd)
                return price.multiply(amount).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    fun lcdOktDepositAmount(): BigDecimal {
        return oktDepositedInfo?.tokens?.toBigDecimal() ?: BigDecimal.ZERO
    }

    private fun lcdOktDepositValue(isUsd: Boolean? = false): BigDecimal {
        val price = BaseData.getPrice(OKT_GECKO_ID, isUsd)
        val amount = lcdOktDepositAmount()
        return price.multiply(amount).setScale(6, RoundingMode.DOWN)
    }

    fun lcdOktWithdrawAmount(): BigDecimal {
        return oktWithdrawInfo?.quantity?.toBigDecimal() ?: BigDecimal.ZERO
    }

    private fun lcdOktWithdrawValue(isUsd: Boolean? = false): BigDecimal {
        val price = BaseData.getPrice(OKT_GECKO_ID, isUsd)
        val amount = lcdOktWithdrawAmount()
        return price.multiply(amount).setScale(6, RoundingMode.DOWN)
    }
}