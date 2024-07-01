package wannabit.io.cosmostaion.chain

import android.content.Context
import android.net.Uri
import android.os.Parcelable
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.google.gson.JsonObject
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCosmos
import wannabit.io.cosmostaion.chain.evmClass.ChainDymensionEvm
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode

@Parcelize
open class BaseChain : Parcelable {

    open var name: String = ""
    open var tag: String = ""
    open var logo: Int = R.drawable.token_default
    open var swipeLogo: Int = -1
    open var isTestnet: Boolean = false
    open var isDefault: Boolean = true
    open var apiName: String = ""
    open var accountPrefix: String? = ""

    open lateinit var accountKeyType: AccountKeyType
    open var publicKey: ByteArray? = null
    open var privateKey: ByteArray? = null
    open var setParentPath: List<ChildNumber> = mutableListOf()

    // cosmos
    open var supportCosmosGrpc = false
    open var supportCosmosLcd = false
    open var chainIdCosmos: String = ""
    open var address: String = ""
    open var stakeDenom: String = ""
    open var supportCw20 = false
    open var supportErc20 = false
    open var supportStaking = true
    open var supportNft = false
    open var grpcHost: String = ""
    open var grpcPort = 443
    open var rpcUrl = ""

    open var supportEvm = false
    open var chainIdEvm: String = ""
    open var evmAddress: String = ""
    open var coinSymbol: String = ""
    open var coinGeckoId: String = ""
    open var coinLogo = -1
    open var evmRpcURL: String = ""
    var web3j: Web3j? = null

    lateinit var grpcFetcher: FetcherGrpc
    lateinit var evmRpcFetcher: FetcherEvmRpc

    open var fetched = false

    fun getHDPath(lastPath: String): String {
        return accountKeyType.hdPath.replace("X", lastPath)
    }

    fun setInfoWithSeed(seed: ByteArray?, parentPath: List<ChildNumber>, lastPath: String) {
        privateKey = BaseKey.getPrivateKey(seed, parentPath, lastPath)
        setInfoWithPrivateKey(privateKey)
    }

    fun setInfoWithPrivateKey(privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey)
        if (accountKeyType.pubkeyType == PubKeyType.COSMOS_SECP256K1) {
            address =
                BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
        } else if (accountKeyType.pubkeyType == PubKeyType.SUI_ED25519) {

        } else {
            evmAddress =
                BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
            if (isCosmos()) {
                address = ByteUtils.convertEvmToBech32(evmAddress, accountPrefix)
            }
        }
    }

    fun initFetcher() {
        if (supportEvm) {
            evmRpcFetcher = FetcherEvmRpc(this)
        }
        if (supportCosmosGrpc) {
            grpcFetcher = FetcherGrpc(this)
        }
    }

    fun tokenValue(address: String, isUsd: Boolean? = false): BigDecimal {
        grpcFetcher.tokens.firstOrNull { it.address == address }?.let { tokenInfo ->
            val price = BaseData.getPrice(tokenInfo.coinGeckoId, isUsd)
            return price.multiply(tokenInfo.amount?.toBigDecimal())
                .movePointLeft(tokenInfo.decimals).setScale(6, RoundingMode.DOWN)
        } ?: run {
            return BigDecimal.ZERO
        }
    }

    fun allTokenValue(isUsd: Boolean? = false): BigDecimal {
        var result = BigDecimal.ZERO
        grpcFetcher.tokens.forEach { token ->
            val price = BaseData.getPrice(token.coinGeckoId, isUsd)
            val value = price.multiply(token.amount?.toBigDecimal()).movePointLeft(token.decimals)
                .setScale(6, RoundingMode.DOWN)
            result = result.add(value)
        }
        return result
    }

    fun allAssetValue(isUsd: Boolean?): BigDecimal {
        grpcFetcher.apply {
            return balanceValueSum(isUsd).add(vestingValueSum(isUsd)).add(delegationValueSum(isUsd))
                .add(unbondingValueSum(isUsd)).add(rewardValueSum(isUsd))
        }
    }

    fun allValue(isUsd: Boolean?): BigDecimal {
        return allAssetValue(isUsd).add(allTokenValue(isUsd))
    }

    fun isCosmos(): Boolean {
        return supportCosmosGrpc || supportCosmosLcd
    }

    fun getInitFee(c: Context): TxProto.Fee? {
        return if (getDefaultFeeCoins(c).isNotEmpty()) {
            val fee = getDefaultFeeCoins(c).first()
            val feeCoin =
                CoinProto.Coin.newBuilder().setDenom(fee.denom).setAmount(fee.amount).build()
            TxProto.Fee.newBuilder().setGasLimit(getFeeBaseGasAmount()).addAmount(feeCoin).build()
        } else {
            null
        }
    }

    fun getInitPayableFee(c: Context): TxProto.Fee? {
        var feeCoin: CoinProto.Coin? = null
        for (i in 0 until getDefaultFeeCoins(c).size) {
            val minFee = getDefaultFeeCoins(c)[i]
            if (grpcFetcher.balanceAmount(minFee.denom) >= minFee.amount.toBigDecimal()) {
                feeCoin = minFee
                break
            }
        }
        if (feeCoin != null) {
            return TxProto.Fee.newBuilder().setGasLimit(getFeeBaseGasAmount()).addAmount(feeCoin)
                .build()
        }
        return null
    }

    fun getBaseFee(c: Context, position: Int, denom: String?): TxProto.Fee {
        val gasAmount = getFeeBaseGasDpAmount()
        val feeDatas = getFeeInfos(c)[position].feeDatas
        val rate = feeDatas.firstOrNull { it.denom == denom }?.gasRate ?: BigDecimal.ZERO
        val coinAmount = rate?.multiply(gasAmount)?.setScale(0, RoundingMode.DOWN)
        return TxProto.Fee.newBuilder().setGasLimit(getFeeBaseGasAmount()).addAmount(
            CoinProto.Coin.newBuilder().setDenom(denom).setAmount(coinAmount.toString()).build()
        ).build()
    }

    fun getBaseFeeInfo(c: Context): FeeInfo {
        return getFeeInfos(c)[getFeeBasePosition()]
    }

    fun getFeeBasePosition(): Int {
        return getChainListParam()?.getAsJsonObject("fee")?.get("base")?.asInt ?: 0
    }

    private fun getFeeBaseGasAmount(): Long {
        return getChainListParam()?.getAsJsonObject("fee")?.let {
            it.get("init_gas_limit")?.asLong
        } ?: run {
            BaseConstant.BASE_GAS_AMOUNT.toLong()
        }
    }

    private fun getFeeBaseGasDpAmount(): BigDecimal {
        return BigDecimal(getFeeBaseGasAmount().toString())
    }

    fun getDefaultFeeCoins(c: Context): MutableList<CoinProto.Coin> {
        val result: MutableList<CoinProto.Coin> = mutableListOf()
        val gasAmount = getFeeBaseGasDpAmount()
        if (getFeeInfos(c).size > 0) {
            val feeDatas = getFeeInfos(c)[getFeeBasePosition()].feeDatas
            feeDatas.forEach { feeData ->
                val amount = feeData.gasRate?.multiply(gasAmount)?.setScale(0, RoundingMode.DOWN)
                result.add(
                    CoinProto.Coin.newBuilder().setDenom(feeData.denom).setAmount(amount.toString())
                        .build()
                )
            }
        }
        return result
    }

    fun getFeeInfos(c: Context): MutableList<FeeInfo> {
        val result: MutableList<FeeInfo> = mutableListOf()
        getChainListParam()?.getAsJsonObject("fee")?.let {
            it.getAsJsonArray("rate").forEach { rate ->
                result.add(FeeInfo(rate.asString))
            }
        }

        if (result.size == 1) {
            result[0].title = c.getString(R.string.str_fixed)
            result[0].msg = c.getString(R.string.str_fee_speed_title_fixed)
        } else if (result.size == 2) {
            result[1].title = c.getString(R.string.str_average)
            result[1].msg = c.getString(R.string.str_fee_speed_title_average)
            if (result[0].feeDatas[0].gasRate == BigDecimal("0.0")) {
                result[0].title = c.getString(R.string.str_free)
                result[0].msg = c.getString(R.string.str_fee_speed_title_zero)
            } else {
                result[0].title = c.getString(R.string.str_tiny)
                result[0].msg = c.getString(R.string.str_fee_speed_title_tiny)
            }
        } else if (result.size == 3) {
            result[2].title = c.getString(R.string.str_average)
            result[2].msg = c.getString(R.string.str_fee_speed_title_average)
            result[1].title = c.getString(R.string.str_low)
            result[1].msg = c.getString(R.string.str_fee_speed_title_low)
            if (result[0].feeDatas[0].gasRate == BigDecimal("0.0")) {
                result[0].title = c.getString(R.string.str_free)
                result[0].msg = c.getString(R.string.str_fee_speed_title_zero)
            } else {
                result[0].title = c.getString(R.string.str_tiny)
                result[0].msg = c.getString(R.string.str_fee_speed_title_tiny)
            }
        }
        return result
    }

    fun isTxFeePayable(c: Context): Boolean {
        getDefaultFeeCoins(c).forEach { fee ->
            if (grpcFetcher.balanceAmount(fee.denom) >= BigDecimal(fee.amount)) {
                return true
            }
        }
        return false
    }

    fun getChainParam(): JsonObject? {
        return try {
            return BaseData.chainParam?.getAsJsonObject(apiName)
        } catch (e: Exception) {
            JsonObject()
        }
    }

    fun getChainListParam(): JsonObject? {
        return try {
            getChainParam()?.getAsJsonObject("params")?.getAsJsonObject("chainlist_params")
                ?: JsonObject()
        } catch (e: Exception) {
            JsonObject()
        }
    }

    fun chainDappName(): String? {
        return getChainListParam()?.get("name_for_dapp")?.asString?.lowercase()
    }

    fun isBankLocked(): Boolean {
        return getChainListParam()?.get("isBankLocked")?.asBoolean ?: false
    }

    fun isEcosystem(): Boolean {
        return getChainListParam()?.get("moblie_dapp")?.asBoolean ?: false
    }

    fun explorerAccount(): Uri? {
        getChainListParam()?.getAsJsonObject("explorer")
            ?.get("account")?.asString?.let { urlString ->
                address.let {
                    return Uri.parse(urlString.replace("\${address}", it))
                }
            }
        return null
    }

    open fun explorerTx(hash: String?): Uri? {
        return null
    }

    open fun explorerProposal(id: String?): Uri? {
        return null
    }
}

fun allCosmosLines(): MutableList<BaseChain> {
    val chains = mutableListOf<BaseChain>()
    chains.add(ChainCosmos())
    chains.add(ChainDymensionEvm())
//    lines.add(ChainAkash())
//    lines.add(ChainAlthea118())
//    lines.add(ChainArchway())
//    lines.add(ChainAssetMantle())
//    lines.add(ChainAxelar())
//    lines.add(ChainBand())
//    lines.add(ChainBitcanna())
//    lines.add(ChainBitsong())
//    lines.add(ChainCelestia())
//    lines.add(ChainChihuahua())
//    lines.add(ChainComdex())
//    lines.add(ChainCoreum())
//    lines.add(ChainCryptoorg())
//    lines.add(ChainCudos())
//    lines.add(ChainDesmos())
//    lines.add(ChainDydx())
//    lines.add(ChainFetchAi())
//    lines.add(ChainFetchAi60Secp())
//    lines.add(ChainFetchAi60Old())
//    lines.add(ChainFinschia())
//    lines.add(ChainGovgen())
//    lines.add(ChainGravityBridge())
//    lines.add(ChainInjective())
//    lines.add(ChainIris())
//    lines.add(ChainIxo())
//    lines.add(ChainJuno())
//    lines.add(ChainKava459())
//    lines.add(ChainKava118())
//    lines.add(ChainKi())
//    lines.add(ChainKyve())
//    lines.add(ChainLikeCoin())
//    lines.add(ChainLum880())
//    lines.add(ChainLum118())
//    lines.add(ChainMars())
//    lines.add(ChainMedibloc())
//    lines.add(ChainNeutron())
//    lines.add(ChainNibiru())
//    lines.add(ChainNoble())
//    lines.add(ChainNyx())
//    lines.add(ChainOmniflix())
//    lines.add(ChainOnomy())
//    lines.add(ChainOsmosis())
//    lines.add(ChainPassage())
//    lines.add(ChainPersistence118())
//    lines.add(ChainPersistence750())
//    lines.add(ChainProvenance())
//    lines.add(ChainQuasar())
//    lines.add(ChainQuicksilver())
//    lines.add(ChainRegen())
//    lines.add(ChainRizon())
//    lines.add(ChainSaga())
//    lines.add(ChainSecret529())
//    lines.add(ChainSecret118())
//    lines.add(ChainSei())
//    lines.add(ChainSentinel())
//    lines.add(ChainShentu())
//    lines.add(ChainSommelier())
//    lines.add(ChainStafi())
//    lines.add(ChainStargaze())
//    lines.add(ChainStride())
//    lines.add(ChainTeritori())
//    lines.add(ChainTerra())
//    lines.add(ChainUx())
//    lines.add(ChainXpla())
//
//
//    lines.add(ChainOkt996Keccak())
//    lines.add(ChainOkt996Secp())

//    lines.add(ChainCrescent())
//    lines.add(ChainEmoney())
//    lines.add(ChainBinanceBeacon())

    chains.forEach { chain ->
        if (chain.chainIdCosmos.isEmpty()) {
            chain.getChainListParam()?.get("chain_id_cosmos")?.asString?.let { cosmosChainId ->
                chain.chainIdCosmos = cosmosChainId
            }
        }
    }
    if (!Prefs.displayLegacy) {
        return chains.filter { it.isDefault }.toMutableList()
    }
    return chains
}

data class AccountKeyType(
    var pubkeyType: PubKeyType, var hdPath: String
)

val DEFAULT_DISPLAY_CHAIN = mutableListOf(
//    "cosmos118", "neutron118", "osmosis118", "dydx118", "crypto-org394", "celestia118"
    "cosmos118", "dymension60"
)

enum class PubKeyType { ETH_KECCAK256, COSMOS_SECP256K1, BERA_SECP256K1, SUI_ED25519, NONE }

//fun allIbcChains(): MutableList<CosmosLine> {
//    val lines = mutableListOf<CosmosLine>()
//    lines.addAll(allCosmosLines())
//    lines.addAll(allEvmLines().filter { it.supportCosmos })
//    return lines
//}