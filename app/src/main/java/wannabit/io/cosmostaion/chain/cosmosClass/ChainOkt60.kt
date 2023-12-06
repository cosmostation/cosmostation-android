package wannabit.io.cosmostaion.chain.cosmosClass

import android.content.Context
import com.google.common.collect.ImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
import wannabit.io.cosmostaion.database.model.RefAddress
import java.math.BigDecimal
import java.math.RoundingMode

open class ChainOkt60: CosmosLine() {

    var oktLcdAccountInfo: OktAccountResponse? = null
    var oktDepositedInfo: OktDepositedResponse? = null
    var oktWithdrawInfo: OktWithdrawResponse? = null
    var oktTokenInfo: OktTokenResponse? = null
    var oktValidatorInfo: MutableList<OktValidatorResponse> = mutableListOf()

    override var chainType: ChainType? = ChainType.COSMOS_TYPE
    override var name: String = "OKT"
    override var chainId: String = "exchain-66"
    override var tag: String = "okt60_Keccak"
    override var logo: Int = R.drawable.chain_okt
    override var swipeLogo: Int = R.drawable.chain_swipe_okt
    override var apiName: String = "okc"
    override var stakeDenom: String? = "okt"
    override var supportStaking = false

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true),
        ChildNumber(60, true),
        ChildNumber.ZERO_HARDENED,
        ChildNumber.ZERO
    )
    override var accountPrefix: String? = "ex"
    override var supportErc20: Boolean = true
    override var evmCompatible: Boolean = true

    val lcdUrl = "https://exchainrpc.okex.org/okexchain/v1/"
    override var rpcUrl: String = "https://exchainrpc.okex.org"

    fun assetImg(originSymbol: String): String {
        return CosmostationConstants.CHAIN_BASE_URL + "okc/asset/" + originSymbol.lowercase() + ".png"
    }

    override fun loadLcdData(id: Long) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = safeApiCall { RetrofitInstance.oktApi.oktAccountInfo(address) }) {
            is NetworkResult.Success -> {
                oktLcdAccountInfo = response.data
                loadLcdMoreData(id)
            }

            is NetworkResult.Error -> {
                loadLcdMoreData(id)
            }
        }
    }

    private fun loadLcdMoreData(id: Long) = runBlocking {
        CoroutineScope(Dispatchers.Default).let {
            loadOktDeposited()
            loadOktWithdraw()
            loadOktTokens()

            loadDataCallback?.onDataLoaded(true)
            fetched = true

            if (fetched) {
                val refAddress = RefAddress(
                    id,
                    tag,
                    address,
                    allAssetValue().toString(),
                    lcdBalanceAmount(stakeDenom).toString(),
                    "0",
                    oktLcdAccountInfo?.value?.coins?.size?.toLong())
                BaseData.updateRefAddressesMain(refAddress)
            }
            it.cancel()
        }
    }

    private suspend fun loadOktDeposited() {
        when (val response = safeApiCall { RetrofitInstance.oktApi.oktDepositInfo(address) }) {
            is NetworkResult.Success -> {
                oktDepositedInfo = response.data
            }

            is NetworkResult.Error -> {
                return
            }
        }
    }

    private suspend fun loadOktWithdraw() {
        when (val response = safeApiCall { RetrofitInstance.oktApi.oktWithdrawInfo(address) }) {
            is NetworkResult.Success -> {
                oktWithdrawInfo = response.data
            }

            is NetworkResult.Error -> {
                return
            }
        }
    }

    private suspend fun loadOktTokens() {
        when (val response = safeApiCall { RetrofitInstance.oktApi.oktTokens() }) {
            is NetworkResult.Success -> {
                oktTokenInfo = response.data
            }

            is NetworkResult.Error -> {
                return
            }
        }
    }

    fun loadValidators() = CoroutineScope(Dispatchers.IO).launch {
        if (oktValidatorInfo.size > 0) { return@launch }

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

    override fun allAssetValue(): BigDecimal {
        return lcdBalanceValue(stakeDenom).add(lcdOktDepositValue()).add(lcdOktWithdrawValue())
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

    override fun lcdBalanceValue(denom: String?): BigDecimal {
        denom?.let {
            if (it == stakeDenom) {
                val amount = lcdBalanceAmount(denom)
                val price = BaseData.getPrice(OKT_GECKO_ID)
                return price.multiply(amount).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    fun lcdOktDepositAmount(): BigDecimal {
        return oktDepositedInfo?.tokens?.toBigDecimal() ?: BigDecimal.ZERO
    }

    private fun lcdOktDepositValue(): BigDecimal {
        val price = BaseData.getPrice(OKT_GECKO_ID)
        val amount = lcdOktDepositAmount()
        return price.multiply(amount).setScale(6, RoundingMode.DOWN)
    }

    fun lcdOktWithdrawAmount(): BigDecimal {
        return oktWithdrawInfo?.quantity?.toBigDecimal() ?: BigDecimal.ZERO
    }

    private fun lcdOktWithdrawValue(): BigDecimal {
        val price = BaseData.getPrice(OKT_GECKO_ID)
        val amount = lcdOktWithdrawAmount()
        return price.multiply(amount).setScale(6, RoundingMode.DOWN)
    }
}

const val OKT_EXPLORER = "https://www.oklink.com/oktc/"
const val OKT_BASE_FEE = "0.000080000000000000"
const val OKT_GECKO_ID = "oec-token"