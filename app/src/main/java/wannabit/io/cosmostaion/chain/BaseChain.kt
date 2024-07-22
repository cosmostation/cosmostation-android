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
import wannabit.io.cosmostaion.chain.cosmosClass.ChainArchway
import wannabit.io.cosmostaion.chain.cosmosClass.ChainLcdArchway
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainEthereum
import wannabit.io.cosmostaion.chain.evmClass.ChainEvmosEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.BigInteger
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

    var cosmosFetcher: CosmosFetcher? = null
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

    open fun cosmosFetcher(): CosmosFetcher? {
        if (!isCosmos()) return null
        if (cosmosFetcher == null) {
            cosmosFetcher = CosmosFetcher(this)
        }
        return cosmosFetcher
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

    fun isEth(): Boolean {
        return supportEvm && !isCosmos()
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
            if (minFee.amount.toBigDecimal() <= cosmosFetcher?.balanceAmount(minFee.denom)) {
                feeCoin = minFee
                break
            }
        }
        if (feeCoin != null) {
            return TxProto.Fee.newBuilder().setGasLimit(getFeeBaseGasAmount())
                .addAmount(feeCoin).build()
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

    fun getFeeBaseGasAmount(): Long {
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

    fun supportFeeMarket(): Boolean? {
        return if (getChainListParam()?.get("fee")?.asJsonObject?.get("feemarket")?.isJsonNull == true) {
            false
        } else {
            getChainListParam()?.get("fee")?.asJsonObject?.get("feemarket")?.asBoolean
        }
    }

    fun evmSupportEip1559(): Boolean {
        return getChainListParam()?.get("evm_fee")?.let {
            it.asJsonObject["eip1559"].asBoolean
        } ?: run {
            false
        }
    }

    fun evmGasMultiply(): BigInteger? {
        return if (getChainListParam()?.get("evm_fee")?.isJsonNull == true) {
            BigInteger("13")
        } else {
            (getChainListParam()?.get("evm_fee")?.asJsonObject?.get("simul_gas_multiply")?.asDouble?.toBigDecimal()
                ?.multiply(BigDecimal(10)))?.toBigInteger()
        }
    }

    fun isTxFeePayable(c: Context): Boolean {
        getDefaultFeeCoins(c).forEach { fee ->
            if (fee.amount.toBigDecimal() <= cosmosFetcher?.balanceAmount(fee.denom)) {
                return true
            }
        }
        return false
    }

    fun txTimeout(): Long {
        return getChainListParam()?.get("tx_timeout_add")?.asLong ?: 30
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
        getChainListParam()?.get("name_for_dapp")?.let {
            return it.asString?.lowercase()
        }
        return ""
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

    fun voteThreshold(): BigDecimal? {
        return if (getChainListParam()?.get("voting_threshold")?.isJsonNull == true) {
            getChainListParam()?.get("voting_threshold")?.asString?.toBigDecimal()
        } else {
            return BigDecimal.ZERO
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
            val allValue = cosmosFetcher?.allAssetValue(isUsd)?.add(cosmosFetcher?.allTokenValue(isUsd))
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
                        ?.add(evmRpcFetcher?.allTokenValue(isUsd)) ?: BigDecimal.ZERO
                }

                is ChainNeutron -> {
                    return neutronFetcher?.allAssetValue(isUsd)
                        ?.add(cosmosFetcher?.allTokenValue(isUsd)) ?: BigDecimal.ZERO
                }

                else -> {
                    return cosmosFetcher?.allAssetValue(isUsd)?.add(cosmosFetcher?.allTokenValue(isUsd))
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
    var chains = mutableListOf<BaseChain>()
//    chains.add(ChainCosmos())
//    chains.add(ChainAkash())
//    chains.add(ChainAltheaEvm())
//    chains.add(ChainAlthea118())
//    chains.add(ChainArbitrum())
    chains.add(ChainArchway())
    chains.add(ChainLcdArchway())
//    chains.add(ChainAvalanche())
//    chains.add(ChainAssetMantle())
//    chains.add(ChainAxelar())
//    chains.add(ChainBand())
//    chains.add(ChainBase())
//    chains.add(ChainBitcanna())
//    chains.add(ChainBitsong())
//    chains.add(ChainBinanceSmart())
//    chains.add(ChainCantoEvm())
//    chains.add(ChainCelestia())
//    chains.add(ChainChihuahua())
//    chains.add(ChainComdex())
//    chains.add(ChainCoreum())
//    chains.add(ChainCronos())
//    chains.add(ChainCryptoorg())
//    chains.add(ChainCudos())
//    chains.add(ChainDesmos())
//    chains.add(ChainDydx())
//    chains.add(ChainDymensionEvm())
    chains.add(ChainEthereum())
    chains.add(ChainEvmosEvm())
//    chains.add(ChainFetchAi())
//    chains.add(ChainFetchAi60Old())
//    chains.add(ChainFetchAi60Secp())
//    chains.add(ChainFinschia())
//    chains.add(ChainGovgen())
//    chains.add(ChainGravityBridge())
//    chains.add(ChainHumansEvm())
//    chains.add(ChainInjective())
//    chains.add(ChainIris())
//    chains.add(ChainIxo())
//    chains.add(ChainJuno())
//    chains.add(ChainKavaEvm())
//    chains.add(ChainKava459())
//    chains.add(ChainKava118())
//    chains.add(ChainKi())
//    chains.add(ChainKyve())
//    chains.add(ChainLava())
//    chains.add(ChainLikeCoin())
//    chains.add(ChainLum880())
//    chains.add(ChainLum118())
//    chains.add(ChainMars())
//    chains.add(ChainMedibloc())
//    chains.add(ChainNeutron())
//    chains.add(ChainNibiru())
//    chains.add(ChainNoble())
//    chains.add(ChainNyx())
//    chains.add(ChainOktEvm())
//    chains.add(ChainOkt996Keccak())
//    chains.add(ChainOkt996Secp())
//    chains.add(ChainOmniflix())
//    chains.add(ChainOnomy())
//    chains.add(ChainOsmosis())
//    chains.add(ChainOptimism())
//    chains.add(ChainPassage())
//    chains.add(ChainPersistence118())
//    chains.add(ChainPersistence750())
//    chains.add(ChainPolygon())
//    chains.add(ChainProvenance())
//    chains.add(ChainQuasar())
//    chains.add(ChainQuicksilver())
//    chains.add(ChainRegen())
//    chains.add(ChainRizon())
//    chains.add(ChainSaga())
//    chains.add(ChainSecret529())
//    chains.add(ChainSecret118())
//    chains.add(ChainSei())
//    chains.add(ChainSentinel())
//    chains.add(ChainShentu())
//    chains.add(ChainSommelier())
//    chains.add(ChainStafi())
//    chains.add(ChainStargaze())
//    chains.add(ChainStride())
//    chains.add(ChainTeritori())
//    chains.add(ChainTerra())
//    chains.add(ChainUx())
//    chains.add(ChainXplaEvm())
//    chains.add(ChainXpla())
//
//    chains.add(ChainCosmosTestnet())
//    chains.add(ChainArtelaTestnet())
//    chains.add(ChainNeutronTestnet())
//    chains.add(ChainNillionTestnet())

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
        chains = chains.filter { it.isDefault }.toMutableList()
    }
    if (!Prefs.displayTestnet) {
        chains = chains.filter { !it.isTestnet }.toMutableList()
    }
    return chains
}

data class AccountKeyType(
    var pubkeyType: PubKeyType, var hdPath: String
)

val DEFAULT_DISPLAY_CHAIN = mutableListOf(
    "cosmos118", "ethereum60", "neutron118", "kava60", "osmosis118", "dydx118"
)

val EVM_BASE_FEE = BigDecimal("588000000000000")

enum class PubKeyType { ETH_KECCAK256, COSMOS_SECP256K1, BERA_SECP256K1, SUI_ED25519, NONE }