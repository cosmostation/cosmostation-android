package wannabit.io.cosmostaion.chain.cosmosClass

import android.content.Context
import android.net.Uri
import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants
import java.math.BigDecimal
import java.math.RoundingMode

@Parcelize
class ChainBinanceBeacon : CosmosLine(), Parcelable {

    override var name: String = "BNB Beacon"
    override var tag: String = "binance714"
    override var logo: Int = R.drawable.chain_binance
    override var swipeLogo: Int = R.drawable.chain_swipe_binance
    override var apiName: String = ""
    override var stakeDenom: String? = "BNB"
    override var supportStaking = false

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/714'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(714, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "bnb"

    val lcdUrl = "https://dex.binance.org/"

    fun assetImg(originSymbol: String): String {
        return CosmostationConstants.CHAIN_BASE_URL + "bnb-beacon-chain/asset/" + originSymbol.lowercase() + ".png"
    }

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return lcdBalanceValueSum(isUsd)
    }

    override fun isTxFeePayable(c: Context): Boolean {
        val availableAmount = lcdBalanceAmount(stakeDenom)
        return availableAmount > BigDecimal(BNB_BEACON_BASE_FEE)
    }

    fun lcdBalanceAmount(denom: String?): BigDecimal {
        val balance = lcdAccountInfo?.balances?.firstOrNull { it.symbol == denom }
        if (balance != null) {
            return balance.free.toBigDecimal()
        }
        return BigDecimal.ZERO
    }

    fun lcdBnbFrozenAmount(denom: String): BigDecimal {
        val balance = lcdAccountInfo?.balances?.firstOrNull { it.symbol == denom }
        if (balance != null) {
            return balance.frozen.toBigDecimal()
        }
        return BigDecimal.ZERO
    }

    fun lcdBnbLockedAmount(denom: String): BigDecimal {
        val balance = lcdAccountInfo?.balances?.firstOrNull { it.symbol == denom }
        if (balance != null) {
            return balance.locked.toBigDecimal()
        }
        return BigDecimal.ZERO
    }

    override fun lcdBalanceValue(denom: String?, isUsd: Boolean?): BigDecimal {
        denom?.let {
            if (it == stakeDenom) {
                val amount = lcdBalanceAmount(denom)
                val price = BaseData.getPrice(BNB_GECKO_ID, isUsd)
                return price.multiply(amount).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    private fun lcdBalanceValueSum(isUsd: Boolean? = false): BigDecimal {
        var sumValue = BigDecimal.ZERO
        lcdAccountInfo?.balances?.forEach { balance ->
            sumValue = sumValue.add(lcdBalanceValue(balance.symbol, isUsd))
        }
        return sumValue
    }

    override fun explorerAccount(): Uri? {
        address?.let {
            return Uri.parse(EXPLORER_BINANCE_URL + "address/" + it)
        }
        return null
    }

    override fun explorerTx(hash: String?): Uri? {
        hash?.let {
            return Uri.parse(EXPLORER_BINANCE_URL + "tx/" + it)
        }
        return null
    }
}

const val BNB_BEACON_BASE_FEE = "0.000075"
const val EXPLORER_BINANCE_URL = "https://explorer.bnbchain.org/"
const val BNB_GECKO_ID = "binancecoin"