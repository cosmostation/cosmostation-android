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
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAaron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAgoric118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAgoric564
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAkash
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAlthea118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainArchway
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAssetMantle
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAtomone
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAxelar
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBand
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBitcanna
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBitsong
import wannabit.io.cosmostaion.chain.cosmosClass.ChainC4E
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCarbon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCelestia
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCheqd
import wannabit.io.cosmostaion.chain.cosmosClass.ChainChihuahua
import wannabit.io.cosmostaion.chain.cosmosClass.ChainComdex
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCoreum
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCosmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCryptoorg
import wannabit.io.cosmostaion.chain.cosmosClass.ChainDesmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainDoravota
import wannabit.io.cosmostaion.chain.cosmosClass.ChainDungeon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainDydx
import wannabit.io.cosmostaion.chain.cosmosClass.ChainElys
import wannabit.io.cosmostaion.chain.cosmosClass.ChainFetchAi
import wannabit.io.cosmostaion.chain.cosmosClass.ChainFetchAi60Old
import wannabit.io.cosmostaion.chain.cosmosClass.ChainFetchAi60Secp
import wannabit.io.cosmostaion.chain.cosmosClass.ChainFinschia
import wannabit.io.cosmostaion.chain.cosmosClass.ChainFirma
import wannabit.io.cosmostaion.chain.cosmosClass.ChainGovgen
import wannabit.io.cosmostaion.chain.cosmosClass.ChainGravityBridge
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInjective
import wannabit.io.cosmostaion.chain.cosmosClass.ChainIris
import wannabit.io.cosmostaion.chain.cosmosClass.ChainIxo
import wannabit.io.cosmostaion.chain.cosmosClass.ChainJackal
import wannabit.io.cosmostaion.chain.cosmosClass.ChainJuno
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKi
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKyve
import wannabit.io.cosmostaion.chain.cosmosClass.ChainLava
import wannabit.io.cosmostaion.chain.cosmosClass.ChainLikeCoin
import wannabit.io.cosmostaion.chain.cosmosClass.ChainLum118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainLum880
import wannabit.io.cosmostaion.chain.cosmosClass.ChainMantra
import wannabit.io.cosmostaion.chain.cosmosClass.ChainMars
import wannabit.io.cosmostaion.chain.cosmosClass.ChainMedibloc
import wannabit.io.cosmostaion.chain.cosmosClass.ChainMigaloo
import wannabit.io.cosmostaion.chain.cosmosClass.ChainMilkyway
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNibiru
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNoble
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNolus
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNyx
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Secp
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOmniflix
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOnomy
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOrai
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOsmosis
import wannabit.io.cosmostaion.chain.cosmosClass.ChainPassage
import wannabit.io.cosmostaion.chain.cosmosClass.ChainPersistence118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainPersistence750
import wannabit.io.cosmostaion.chain.cosmosClass.ChainProvenance
import wannabit.io.cosmostaion.chain.cosmosClass.ChainPryzm
import wannabit.io.cosmostaion.chain.cosmosClass.ChainQuasar
import wannabit.io.cosmostaion.chain.cosmosClass.ChainQuicksilver
import wannabit.io.cosmostaion.chain.cosmosClass.ChainRegen
import wannabit.io.cosmostaion.chain.cosmosClass.ChainRizon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSaga
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSecret118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSecret529
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSeda
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSei
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSelf
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSentinel
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSge
import wannabit.io.cosmostaion.chain.cosmosClass.ChainShentu
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSommelier
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSource
import wannabit.io.cosmostaion.chain.cosmosClass.ChainStafi
import wannabit.io.cosmostaion.chain.cosmosClass.ChainStargaze
import wannabit.io.cosmostaion.chain.cosmosClass.ChainStride
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSynternet
import wannabit.io.cosmostaion.chain.cosmosClass.ChainTeritori
import wannabit.io.cosmostaion.chain.cosmosClass.ChainTerra
import wannabit.io.cosmostaion.chain.cosmosClass.ChainThorchain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainUnification
import wannabit.io.cosmostaion.chain.cosmosClass.ChainUx
import wannabit.io.cosmostaion.chain.cosmosClass.ChainXion
import wannabit.io.cosmostaion.chain.cosmosClass.ChainXpla
import wannabit.io.cosmostaion.chain.evmClass.ChainAiozEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainAltheaEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainArbitrum
import wannabit.io.cosmostaion.chain.evmClass.ChainAvalanche
import wannabit.io.cosmostaion.chain.evmClass.ChainBase
import wannabit.io.cosmostaion.chain.evmClass.ChainBinanceSmart
import wannabit.io.cosmostaion.chain.evmClass.ChainCantoEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainCronos
import wannabit.io.cosmostaion.chain.evmClass.ChainDymensionEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainEthereum
import wannabit.io.cosmostaion.chain.evmClass.ChainEvmosEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainFantom
import wannabit.io.cosmostaion.chain.evmClass.ChainHaqqEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainHumansEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainKaia
import wannabit.io.cosmostaion.chain.evmClass.ChainKavaEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainOptimism
import wannabit.io.cosmostaion.chain.evmClass.ChainPlanqEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainPolygon
import wannabit.io.cosmostaion.chain.evmClass.ChainRealioEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainRouterchainEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainTenetEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainXplaEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainZetaEvm
import wannabit.io.cosmostaion.chain.fetcher.CosmosFetcher
import wannabit.io.cosmostaion.chain.fetcher.EvmFetcher
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin44
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin49
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.majorClass.ChainNamada
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.testnetClass.ChainBitcoin44Testnet
import wannabit.io.cosmostaion.chain.testnetClass.ChainBitcoin49Testnet
import wannabit.io.cosmostaion.chain.testnetClass.ChainBitcoin84Testnet
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.chain.testnetClass.ChainMantraTestnet
import wannabit.io.cosmostaion.chain.testnetClass.ChainNeutronTestnet
import wannabit.io.cosmostaion.chain.testnetClass.ChainNillionTestnet
import wannabit.io.cosmostaion.chain.testnetClass.ChainStroyTestnet
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.hexToString
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
    open var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.UNKNOWN
    open var chainIdCosmos: String = ""
    open var address: String = ""
    open var stakeDenom: String = ""
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
    var evmRpcFetcher: EvmFetcher? = null

    open var mainAddress: String = ""
    open var mainUrl: String = ""

    var fetchState = FetchState.IDLE

    var coinCnt = 0
    var tokenCnt = 0

    fun chainIdEvmDecimal(): String {
        return chainIdEvm.hexToString()
    }

    fun chainIdForSwap(): String {
        if (supportCosmos()) {
            return chainIdCosmos
        } else if (supportEvm) {
            return chainIdEvm
        }
        return ""
    }

    fun getHDPath(lastPath: String): String {
        return accountKeyType.hdPath.replace("X", lastPath)
    }

    fun setInfoWithSeed(seed: ByteArray?, parentPath: List<ChildNumber>, lastPath: String) {
        privateKey = BaseKey.getPrivateKey(accountKeyType.pubkeyType, seed, parentPath, lastPath)
        setInfoWithPrivateKey(privateKey)
    }

    open fun setInfoWithPrivateKey(privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        if (accountKeyType.pubkeyType == PubKeyType.COSMOS_SECP256K1) {
            address =
                BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)

        } else {
            evmAddress =
                BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
            if (supportCosmos()) {
                address = ByteUtils.convertEvmToBech32(evmAddress, accountPrefix)
            }
        }
    }

    open fun cosmosFetcher(): CosmosFetcher? {
        if (!supportCosmos()) return null
        if (cosmosFetcher == null) {
            cosmosFetcher = CosmosFetcher(this)
        }
        return cosmosFetcher
    }

    fun evmRpcFetcher(): EvmFetcher? {
        if (!supportEvm) return null
        if (evmRpcFetcher == null) {
            evmRpcFetcher = EvmFetcher(this)
        }
        return evmRpcFetcher
    }

    fun supportCosmos(): Boolean {
        return !(cosmosEndPointType == null || cosmosEndPointType == CosmosEndPointType.UNKNOWN)
    }

    fun isEvmCosmos(): Boolean {
        return supportCosmos() && supportEvm
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

    fun getInitFee(c: Context): TxProto.Fee? {
        return if (getDefaultFeeCoins(c).isNotEmpty()) {
            val fee = getDefaultFeeCoins(c).first()
            val feeCoin =
                CoinProto.Coin.newBuilder().setDenom(fee.denom).setAmount(fee.amount).build()
            TxProto.Fee.newBuilder().setGasLimit(getInitGasLimit()).addAmount(feeCoin).build()
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
            return TxProto.Fee.newBuilder().setGasLimit(getInitGasLimit()).addAmount(feeCoin)
                .build()
        }
        return null
    }

    fun getBaseFee(c: Context, position: Int, denom: String?): TxProto.Fee {
        val feeDatas = getFeeInfos(c)[position].feeDatas
        val rate = feeDatas.firstOrNull { it.denom == denom }?.gasRate ?: BigDecimal.ZERO
        val coinAmount =
            rate?.multiply(getInitGasLimit().toBigDecimal())?.setScale(0, RoundingMode.DOWN)
        return TxProto.Fee.newBuilder().setGasLimit(getInitGasLimit()).addAmount(
            CoinProto.Coin.newBuilder().setDenom(denom).setAmount(coinAmount.toString()).build()
        ).build()
    }

    fun getBaseFeeInfo(c: Context): FeeInfo {
        return getFeeInfos(c)[getFeeBasePosition()]
    }

    fun getFeeBasePosition(): Int {
        return getChainListParam()?.getAsJsonObject("cosmos_fee_info")?.get("base")?.asInt ?: 0
    }

    fun getInitGasLimit(): Long {
        return getChainListParam()?.getAsJsonObject("cosmos_fee_info")?.let {
            it.get("init_gas_limit")?.asLong
        } ?: run {
            BaseConstant.BASE_GAS_AMOUNT.toLong()
        }
    }

    fun getDefaultFeeCoins(c: Context): MutableList<CoinProto.Coin> {
        val result: MutableList<CoinProto.Coin> = mutableListOf()
        val gasAmount = getInitGasLimit().toBigDecimal()
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
        getChainListParam()?.getAsJsonObject("cosmos_fee_info")?.let {
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

    fun simulatedGasMultiply(): Double {
        return getChainListParam()?.getAsJsonObject("cosmos_fee_info")
            ?.get("simulated_gas_multiply")?.asDouble ?: run {
            1.3
        }
    }

    fun isSupportFeeMarket(): Boolean? {
        return if (getChainListParam()?.get("cosmos_fee_info")?.asJsonObject?.get("is_feemarket") == null) {
            false
        } else {
            getChainListParam()?.get("cosmos_fee_info")?.asJsonObject?.get("is_feemarket")?.asBoolean
        }
    }

    fun isEvmSupportEip1559(): Boolean {
        return getChainListParam()?.get("evm_fee_info")?.let {
            it.asJsonObject["is_eip1559"].asBoolean
        } ?: run {
            false
        }
    }

    fun evmSimulatedGasMultiply(): BigInteger? {
        return if (getChainListParam()?.get("evm_fee_info")?.isJsonNull == true) {
            BigInteger("13")
        } else {
            (getChainListParam()?.get("evm_fee_info")?.asJsonObject?.get("simulated_gas_multiply")?.asDouble?.toBigDecimal()
                ?.multiply(BigDecimal(10)))?.toBigInteger()
        }
    }

    fun skipAffiliate(): String {
        return BaseData.chainParam?.get("cosmos")?.asJsonObject?.get("params")?.asJsonObject?.get("chainlist_params")?.asJsonObject?.get(
            "skip_Affiliate"
        )?.asString ?: "50"
    }

    fun isTxFeePayable(c: Context): Boolean {
        getDefaultFeeCoins(c).forEach { fee ->
            if (fee.amount.toBigDecimal() <= cosmosFetcher?.balanceAmount(fee.denom)) {
                return true
            }
        }
        return false
    }

    fun txTimeoutPadding(): Long {
        return getChainListParam()?.get("tx_timeout_padding")?.asLong ?: 30
    }

    fun getInterchainProviderParams(): JsonObject? {
        return try {
            getChainParam()?.getAsJsonObject("params")
                ?.getAsJsonObject("interchain_provider_params") ?: JsonObject()
        } catch (e: Exception) {
            JsonObject()
        }
    }

    fun isSimulable(): Boolean {
        return getChainListParam()?.getAsJsonObject("cosmos_fee_info")
            ?.get("is_simulable")?.asBoolean ?: true
    }

    fun isSendEnabled(): Boolean {
        return getChainListParam()?.get("is_send_enabled")?.asBoolean ?: true
    }

    fun isStakeEnabled(): Boolean {
        return getChainListParam()?.get("is_stake_enabled")?.asBoolean ?: true
    }

    fun isSupportMobileDapp(): Boolean {
        return getChainListParam()?.get("is_support_moblie_dapp")?.asBoolean ?: false
    }

    fun isSupportMintscan(): Boolean {
        return getChainListParam()?.get("is_support_mintscan")?.asBoolean ?: false
    }

    fun isSupportErc20(): Boolean {
        return (getChainListParam()?.get("is_support_erc20")?.asBoolean ?: false && supportEvm)
    }

    fun isSupportCw20(): Boolean {
        return getChainListParam()?.get("is_support_cw20")?.asBoolean ?: false
    }

    fun isSupportCw721(): Boolean {
        return getChainListParam()?.get("is_support_cw721")?.asBoolean ?: false
    }

    fun explorerAccount(address: String): Uri? {
        getChainListParam()?.getAsJsonObject("explorer")
            ?.get("account")?.asString?.let { urlString ->
                return Uri.parse(urlString.replace("\${address}", address))
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

    fun votingThreshold(): BigDecimal? {
        return if (getChainListParam()?.get("voting_threshold")?.isJsonNull == false) {
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
        if (fetchState == FetchState.SUCCESS) {
            if (this is ChainBitCoin84) {
                return btcFetcher?.allAssetValue(isUsd) ?: BigDecimal.ZERO
            }
            else if (this is ChainSui) {
                return suiFetcher?.allAssetValue(isUsd) ?: BigDecimal.ZERO

            } else if (this is ChainNamada) {
                return namadaFetcher?.allAssetValue(isUsd) ?: BigDecimal.ZERO

            } else if (this is ChainOkt996Keccak) {
                return oktFetcher?.allAssetValue(isUsd) ?: BigDecimal.ZERO

            } else if (this is ChainOktEvm) {
                return oktFetcher?.allAssetValue(isUsd)?.add(evmRpcFetcher?.allTokenValue(isUsd))
                    ?: BigDecimal.ZERO

            } else if (isEvmCosmos()) {
                val allValue =
                    cosmosFetcher?.allAssetValue(isUsd)?.add(cosmosFetcher?.allTokenValue(isUsd))
                        ?: BigDecimal.ZERO
                evmRpcFetcher?.let { evmRpc ->
                    return allValue.add(evmRpc.allTokenValue(isUsd))
                }
                return allValue

            } else if (supportCosmos()) {
                return cosmosFetcher?.allAssetValue(isUsd)?.add(cosmosFetcher?.allTokenValue(isUsd))
                    ?: BigDecimal.ZERO

            } else {
                return evmRpcFetcher?.allAssetValue(isUsd)?.add(evmRpcFetcher?.allTokenValue(isUsd))
                    ?: BigDecimal.ZERO
            }
        }
        return BigDecimal.ZERO
    }
}

fun allChains(): MutableList<BaseChain> {
    var chains = mutableListOf<BaseChain>()
    chains.add(ChainCosmos())
    chains.add(ChainAaron())
    chains.add(ChainAgoric564())
    chains.add(ChainAgoric118())
    chains.add(ChainAiozEvm())
    chains.add(ChainAkash())
    chains.add(ChainAltheaEvm())
    chains.add(ChainAlthea118())
    chains.add(ChainArbitrum())
    chains.add(ChainArchway())
    chains.add(ChainAssetMantle())
    chains.add(ChainAtomone())
    chains.add(ChainAvalanche())
    chains.add(ChainAxelar())
    chains.add(ChainBand())
    chains.add(ChainBase())
    chains.add(ChainBitcanna())
    chains.add(ChainBitCoin44())
    chains.add(ChainBitCoin49())
    chains.add(ChainBitCoin84())
    chains.add(ChainBitsong())
    chains.add(ChainBinanceSmart())
    chains.add(ChainCantoEvm())
    chains.add(ChainCarbon())
    chains.add(ChainCelestia())
    chains.add(ChainC4E())
    chains.add(ChainCheqd())
    chains.add(ChainChihuahua())
    chains.add(ChainComdex())
    chains.add(ChainCoreum())
    chains.add(ChainCronos())
    chains.add(ChainCryptoorg())
    chains.add(ChainDesmos())
    chains.add(ChainDoravota())
    chains.add(ChainDungeon())
    chains.add(ChainDydx())
    chains.add(ChainDymensionEvm())
    chains.add(ChainElys())
    chains.add(ChainEthereum())
    chains.add(ChainEvmosEvm())
    chains.add(ChainFantom())
    chains.add(ChainFetchAi())
    chains.add(ChainFetchAi60Old())
    chains.add(ChainFetchAi60Secp())
    chains.add(ChainFinschia())
    chains.add(ChainFirma())
    chains.add(ChainGovgen())
    chains.add(ChainGravityBridge())
    chains.add(ChainHaqqEvm())
    chains.add(ChainHumansEvm())
    chains.add(ChainInjective())
    chains.add(ChainIris())
    chains.add(ChainIxo())
    chains.add(ChainJackal())
    chains.add(ChainJuno())
    chains.add(ChainKaia())
    chains.add(ChainKavaEvm())
    chains.add(ChainKava459())
    chains.add(ChainKava118())
    chains.add(ChainKi())
    chains.add(ChainKyve())
    chains.add(ChainLava())
    chains.add(ChainLikeCoin())
    chains.add(ChainLum880())
    chains.add(ChainLum118())
    chains.add(ChainMantra())
    chains.add(ChainMars())
    chains.add(ChainMedibloc())
    chains.add(ChainMigaloo())
    chains.add(ChainMilkyway ())
    chains.add(ChainNamada())
    chains.add(ChainNeutron())
    chains.add(ChainNibiru())
    chains.add(ChainNoble())
    chains.add(ChainNolus())
    chains.add(ChainNyx())
    chains.add(ChainOktEvm())
    chains.add(ChainOkt996Keccak())
    chains.add(ChainOkt996Secp())
    chains.add(ChainOmniflix())
    chains.add(ChainOnomy())
    chains.add(ChainOrai())
    chains.add(ChainOsmosis())
    chains.add(ChainOptimism())
    chains.add(ChainPassage())
    chains.add(ChainPersistence118())
    chains.add(ChainPersistence750())
    chains.add(ChainPlanqEvm())
    chains.add(ChainPolygon())
    chains.add(ChainProvenance())
    chains.add(ChainPryzm())
    chains.add(ChainQuasar())
    chains.add(ChainQuicksilver())
    chains.add(ChainRealioEvm())
    chains.add(ChainRegen())
    chains.add(ChainRizon())
    chains.add(ChainRouterchainEvm())
    chains.add(ChainSaga())
    chains.add(ChainSecret529())
    chains.add(ChainSecret118())
    chains.add(ChainSeda())
    chains.add(ChainSei())
    chains.add(ChainSelf())
    chains.add(ChainSentinel())
    chains.add(ChainSge())
    chains.add(ChainShentu())
    chains.add(ChainSommelier())
    chains.add(ChainSource())
    chains.add(ChainStafi())
    chains.add(ChainStargaze())
    chains.add(ChainStride())
    chains.add(ChainSui())
    chains.add(ChainSynternet())
    chains.add(ChainTenetEvm())
    chains.add(ChainTeritori())
    chains.add(ChainTerra())
    chains.add(ChainThorchain())
    chains.add(ChainUnification())
    chains.add(ChainUx())
    chains.add(ChainXion())
    chains.add(ChainXplaEvm())
    chains.add(ChainXpla())
    chains.add(ChainZetaEvm())

//    chains.add(ChainCosmosTestnet())
//    chains.add(ChainArtelaTestnet())
    chains.add(ChainBitcoin44Testnet())
    chains.add(ChainBitcoin49Testnet())
    chains.add(ChainBitcoin84Testnet())
    chains.add(ChainInitiaTestnet())
    chains.add(ChainMantraTestnet())
    chains.add(ChainNeutronTestnet())
    chains.add(ChainNillionTestnet())
    chains.add(ChainStroyTestnet())

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

enum class PubKeyType { ETH_KECCAK256, COSMOS_SECP256K1, BERA_SECP256K1, SUI_ED25519, BTC_LEGACY, BTC_NESTED_SEGWIT, BTC_NATIVE_SEGWIT, NAMADA_ED25519, NONE }

enum class CosmosEndPointType { UNKNOWN, USE_GRPC, USE_LCD }

enum class FetchState { IDLE, BUSY, SUCCESS, FAIL }