package wannabit.io.cosmostaion.chain.cosmosClass

import android.content.Context
import com.google.common.collect.ImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.OktAccountResponse
import wannabit.io.cosmostaion.data.model.res.OktDepositedResponse
import wannabit.io.cosmostaion.data.model.res.OktTokenResponse
import wannabit.io.cosmostaion.data.model.res.OktValidatorResponse
import wannabit.io.cosmostaion.data.model.res.OktWithdrawResponse
import java.math.BigDecimal
import java.math.RoundingMode

open class ChainOkt996Keccak : CosmosLine() {

    var oktLcdAccountInfo: OktAccountResponse? = null
    var oktDepositedInfo: OktDepositedResponse? = null
    var oktWithdrawInfo: OktWithdrawResponse? = null
    var oktTokenInfo: OktTokenResponse? = null
    var oktValidatorInfo: MutableList<OktValidatorResponse> = mutableListOf()

    override var isDefault = false
    override var chainType: ChainType? = ChainType.COSMOS_TYPE
    override var name: String = "OKT"
    override var chainId: String = "exchain-66"
    override var tag: String = "okt996_Keccak"
    override var logo: Int = R.drawable.chain_okt
    override var swipeLogo: Int = R.drawable.chain_swipe_okt
    override var apiName: String = "okc"
    override var stakeDenom: String? = "okt"
    override var supportStaking = false

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/996'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(996, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "ex"
    override var supportErc20: Boolean = true
    override var evmCompatible: Boolean = true

    val lcdUrl = "https://exchainrpc.okex.org/okexchain/v1/"
    override var rpcUrl: String = "https://exchainrpc.okex.org"

    fun assetImg(originSymbol: String): String {
        return CosmostationConstants.CHAIN_BASE_URL + "okc/asset/" + originSymbol.lowercase() + ".png"
    }

    fun loadValidators() = CoroutineScope(Dispatchers.IO).launch {
        if (oktValidatorInfo.size > 0) {
            return@launch
        }

        when (val response = safeApiCall { RetrofitInstance.oktApi.oktValidators() }) {
            is NetworkResult.Success -> {
                oktValidatorInfo.clear()
                response.data.sortWith { o1, o2 ->
                    when {
                        o1.description?.moniker == "Cosmostation" -> -1
                        o2.description?.moniker == "Cosmostation" -> 1
                        o1.jailed == true && o2.jailed == false -> 1
                        o1.jailed == false && o2.jailed == true -> -1
                        o1.delegatorShares.toDouble() > o2.delegatorShares.toDouble() -> -1
                        o1.delegatorShares.toDouble() < o2.delegatorShares.toDouble() -> 1
                        else -> 0
                    }
                }
                oktValidatorInfo = response.data
            }

            is NetworkResult.Error -> {
                return@launch
            }
        }
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

const val OKT_EXPLORER = "https://www.oklink.com/oktc/"
const val OKT_BASE_FEE = "0.008000000000000000"
const val OKT_GECKO_ID = "oec-token"