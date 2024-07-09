package wannabit.io.cosmostaion.chain

import android.content.Context
import android.net.Uri
import android.os.Parcelable
import android.util.Log
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.google.gson.JsonObject
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainArchway
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAxelar
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCosmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainJuno
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Secp
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOsmosis
import wannabit.io.cosmostaion.chain.cosmosClass.ChainStargaze
import wannabit.io.cosmostaion.chain.evmClass.ChainDymensionEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainEthereum
import wannabit.io.cosmostaion.chain.evmClass.ChainEvmosEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainKavaEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.toHex
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
    open var accountPrefix: String = ""

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
    open var supportStaking = true
    open var supportNft = false
    open var grpcHost: String = ""
    open var grpcPort = 443
    open var lcdUrl: String = ""

    open var supportEvm = false
    open var chainIdEvm: String = ""
    open var evmAddress: String = ""
    open var coinSymbol: String = ""
    open var coinGeckoId: String = ""
    open var coinLogo = -1
    open var addressLogo = -1
    open var evmRpcURL: String = ""
    var web3j: Web3j? = null

    var grpcFetcher: FetcherGrpc? = null
    var evmRpcFetcher: FetcherEvmRpc? = null
    var lcdFetcher: FetcherLcd? = null

    open var fetched = false

    fun getHDPath(lastPath: String): String {
        return accountKeyType.hdPath.replace("X", lastPath)
    }

    fun setInfoWithSeed(seed: ByteArray?, parentPath: List<ChildNumber>, lastPath: String) {
        privateKey = BaseKey.getPrivateKey(seed, parentPath, lastPath)
        setInfoWithPrivateKey(privateKey)
    }

    open fun setInfoWithPrivateKey(privateKey: ByteArray?) {
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

    open fun grpcFetcher(): FetcherGrpc? {
        if (!supportCosmosGrpc) {
            return null
        }
        if (grpcFetcher == null) {
            grpcFetcher = FetcherGrpc(this)
        }
        return grpcFetcher
    }

    open fun lcdFetcher(): FetcherLcd? {
        if (!supportCosmosLcd) {
            return null
        }
        if (lcdFetcher == null) {
            lcdFetcher = FetcherLcd(this)
        }
        return lcdFetcher
    }

    fun evmRpcFetcher(): FetcherEvmRpc? {
        if (!supportEvm) {
            return null
        }
        if (evmRpcFetcher == null) {
            evmRpcFetcher = FetcherEvmRpc(this)
        }
        return evmRpcFetcher
    }

    fun isCosmos(): Boolean {
        return supportCosmosGrpc || supportCosmosLcd
    }

    fun isEvmCosmos(): Boolean {
        return supportCosmosGrpc && supportEvm
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
            if (minFee.amount.toBigDecimal() <= grpcFetcher?.balanceAmount(minFee.denom)) {
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

    fun gasMultiply(): Double {
        return getChainListParam()?.getAsJsonObject("fee")?.get("simul_gas_multiply")?.asDouble
            ?: run {
                1.3
            }
    }

    fun isTxFeePayable(c: Context): Boolean {
        getDefaultFeeCoins(c).forEach { fee ->
            if (fee.amount.toBigDecimal() <= grpcFetcher?.balanceAmount(fee.denom)) {
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

    fun isGasSimulable(): Boolean {
        return getChainListParam()?.getAsJsonObject("fee")?.get("isSimulable")?.asBoolean ?: true
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

    fun explorerTx(hash: String?): Uri? {
        getChainListParam()?.getAsJsonObject("explorer")?.get("tx")?.asString?.let { urlString ->
            hash?.let {
                return Uri.parse(urlString.replace("\${hash}", it))

            } ?: run {
                return null
            }
        }
        return null
    }

    fun explorerProposal(id: String?): Uri? {
        getChainListParam()?.getAsJsonObject("explorer")
            ?.get("proposal")?.asString?.let { urlString ->
                id?.let {
                    return Uri.parse(urlString.replace("\${id}", it))
                } ?: run {
                    return null
                }
            }
        return null
    }

    fun voteThreshold(): String {
        return getChainListParam()?.get("voting_threshold")?.asString ?: run {
            "0"
        }
    }

    fun monikerImg(opAddress: String?): String {
        return "${CosmostationConstants.CHAIN_BASE_URL}$apiName/moniker/$opAddress.png"
    }

    open fun assetImg(originSymbol: String): String {
        return ""
    }

    fun allValue(isUsd: Boolean?): BigDecimal {
        if (isEvmCosmos()) {
            val allValue = grpcFetcher?.allAssetValue(isUsd)?.add(grpcFetcher?.allTokenValue(isUsd))
                ?: BigDecimal.ZERO
            evmRpcFetcher?.let { evmRpc ->
                return allValue.add(evmRpc.allTokenValue(isUsd))
            }
            return allValue

        } else if (isCosmos()) {
            when (this) {
                is ChainOkt996Keccak -> {
                    oktFetcher?.let { lcd ->
                        return lcd.allAssetValue(isUsd)
                    }
                    return BigDecimal.ZERO
                }

                is ChainOktEvm -> {
                    return evmRpcFetcher?.allAssetValue(isUsd)
                        ?.add(evmRpcFetcher?.allTokenValue(isUsd))
                        ?: BigDecimal.ZERO
                }

                else -> {
                    return grpcFetcher?.allAssetValue(isUsd)?.add(grpcFetcher?.allTokenValue(isUsd))
                        ?: BigDecimal.ZERO
                }
            }

        } else {
            return evmRpcFetcher?.allAssetValue(isUsd)?.add(evmRpcFetcher?.allTokenValue(isUsd))
                ?: BigDecimal.ZERO
        }
    }
}

fun allChains(): MutableList<BaseChain> {
    val chains = mutableListOf<BaseChain>()
    chains.add(ChainCosmos())
    chains.add(ChainArchway())
    chains.add(ChainAxelar())
    chains.add(ChainDymensionEvm())
    chains.add(ChainEthereum())
    chains.add(ChainEvmosEvm())
    chains.add(ChainJuno())
    chains.add(ChainKavaEvm())
    chains.add(ChainKava459())
    chains.add(ChainOktEvm())
    chains.add(ChainOkt996Keccak())
    chains.add(ChainOkt996Secp())
    chains.add(ChainOsmosis())
    chains.add(ChainNeutron())
    chains.add(ChainStargaze())
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
            chain.getChainListParam()?.get("chain_id_cosmos")?.let { cosmosChainId ->
                chain.chainIdCosmos = cosmosChainId.asString
            }
        }

        if (chain.chainIdEvm.isEmpty()) {
            chain.getChainListParam()?.get("chain_id_evm")?.let { evmChainId ->
                chain.chainIdEvm = evmChainId.asString
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