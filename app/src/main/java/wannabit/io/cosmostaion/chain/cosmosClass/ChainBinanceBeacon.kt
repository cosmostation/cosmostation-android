package wannabit.io.cosmostaion.chain.cosmosClass

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
import wannabit.io.cosmostaion.database.model.RefAddress
import java.math.BigDecimal
import java.math.RoundingMode

class ChainBinanceBeacon : CosmosLine() {

    override var chainType: ChainType? = ChainType.COSMOS_TYPE
    override var name: String = "BNB Beacon"
    override var tag: String = "binance714"
    override var logo: Int = R.drawable.chain_binance
    override var swipeLogo: Int = R.drawable.chain_swipe_binance
    override var apiName: String = ""
    override var stakeDenom: String? = "BNB"
    override var supportStaking = false

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/714'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true),
        ChildNumber(714, true),
        ChildNumber.ZERO_HARDENED,
        ChildNumber.ZERO
    )
    override var accountPrefix: String? = "bnb"

    val lcdUrl = "https://dex.binance.org/"
    val BNB_GECKO_ID = "binancecoin"

    fun assetImg(originSymbol: String): String {
        return CosmostationConstants.CHAIN_BASE_URL + "bnb-beacon-chain/asset/" + originSymbol.lowercase() + ".png"
    }

    override fun loadLcdData(id: Long) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = safeApiCall { RetrofitInstance.beaconApi.accountInfo(address) }) {
            is NetworkResult.Success -> {
                lcdAccountInfo = response.data
                loadLcdMoreData(id)
            }

            is NetworkResult.Error -> {
                loadLcdMoreData(id)
            }
        }
    }

    private fun loadLcdMoreData(id: Long) = runBlocking {
        CoroutineScope(Dispatchers.Default).let {
            loadBeaconTokens()

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
                    lcdAccountInfo?.balances?.size?.toLong())
                BaseData.updateRefAddressesMain(refAddress)
            }
            it.cancel()
        }
    }

    override fun allAssetValue(): BigDecimal {
        return lcdBalanceValueSum()
    }

    private suspend fun loadBeaconTokens() {
        when (val response = safeApiCall { RetrofitInstance.beaconApi.beaconTokens("1000") }) {
            is NetworkResult.Success -> {
                lcdBeaconTokens = response.data
            }

            is NetworkResult.Error -> {
                return
            }
        }
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

    fun lcdBalanceValue(denom: String?): BigDecimal {
        denom?.let {
            if (it == stakeDenom) {
                val amount = lcdBalanceAmount(denom)
                val price = BaseData.getPrice(ChainBinanceBeacon().BNB_GECKO_ID)
                return price.multiply(amount).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    private fun lcdBalanceValueSum(): BigDecimal {
        var sumValue = BigDecimal.ZERO
        lcdAccountInfo?.balances?.forEach { balance ->
            sumValue = sumValue.add(lcdBalanceValue(balance.symbol))
        }
        return sumValue
    }
}