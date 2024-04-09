package wannabit.io.cosmostaion.chain

import android.content.Context
import android.net.Uri
import android.os.Parcelable
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmos.staking.v1beta1.StakingProto.DelegationResponse
import com.cosmos.staking.v1beta1.StakingProto.UnbondingDelegation
import com.cosmos.tx.v1beta1.TxProto
import com.google.gson.JsonObject
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAkash
import wannabit.io.cosmostaion.chain.cosmosClass.ChainArchway
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAssetMantle
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAxelar
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBand
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBitcanna
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBitsong
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCelestia
import wannabit.io.cosmostaion.chain.cosmosClass.ChainChihuahua
import wannabit.io.cosmostaion.chain.cosmosClass.ChainComdex
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCoreum
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCosmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCrescent
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCryptoorg
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCudos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainDesmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainDydx
import wannabit.io.cosmostaion.chain.cosmosClass.ChainEmoney
import wannabit.io.cosmostaion.chain.cosmosClass.ChainFetchAi
import wannabit.io.cosmostaion.chain.cosmosClass.ChainFetchAi60Old
import wannabit.io.cosmostaion.chain.cosmosClass.ChainFetchAi60Secp
import wannabit.io.cosmostaion.chain.cosmosClass.ChainFinschia
import wannabit.io.cosmostaion.chain.cosmosClass.ChainGovgen
import wannabit.io.cosmostaion.chain.cosmosClass.ChainGravityBridge
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInjective
import wannabit.io.cosmostaion.chain.cosmosClass.ChainIris
import wannabit.io.cosmostaion.chain.cosmosClass.ChainIxo
import wannabit.io.cosmostaion.chain.cosmosClass.ChainJuno
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKi
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKyve
import wannabit.io.cosmostaion.chain.cosmosClass.ChainLikeCoin
import wannabit.io.cosmostaion.chain.cosmosClass.ChainLum118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainLum880
import wannabit.io.cosmostaion.chain.cosmosClass.ChainMars
import wannabit.io.cosmostaion.chain.cosmosClass.ChainMedibloc
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNibiru
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNoble
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNyx
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Secp
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOmniflix
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOnomy
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOsmosis
import wannabit.io.cosmostaion.chain.cosmosClass.ChainPassage
import wannabit.io.cosmostaion.chain.cosmosClass.ChainPersistence118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainPersistence750
import wannabit.io.cosmostaion.chain.cosmosClass.ChainProvenance
import wannabit.io.cosmostaion.chain.cosmosClass.ChainQuasar
import wannabit.io.cosmostaion.chain.cosmosClass.ChainQuicksilver
import wannabit.io.cosmostaion.chain.cosmosClass.ChainRegen
import wannabit.io.cosmostaion.chain.cosmosClass.ChainRizon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSaga
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSecret118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSecret529
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSei
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSentinel
import wannabit.io.cosmostaion.chain.cosmosClass.ChainShentu
import wannabit.io.cosmostaion.chain.cosmosClass.ChainSommelier
import wannabit.io.cosmostaion.chain.cosmosClass.ChainStafi
import wannabit.io.cosmostaion.chain.cosmosClass.ChainStargaze
import wannabit.io.cosmostaion.chain.cosmosClass.ChainStride
import wannabit.io.cosmostaion.chain.cosmosClass.ChainTeritori
import wannabit.io.cosmostaion.chain.cosmosClass.ChainTerra
import wannabit.io.cosmostaion.chain.cosmosClass.ChainUx
import wannabit.io.cosmostaion.chain.cosmosClass.ChainXpla
import wannabit.io.cosmostaion.common.BaseConstant.BASE_GAS_AMOUNT
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.CosmostationConstants.CHAIN_BASE_URL
import wannabit.io.cosmostaion.data.model.res.AccountResponse
import wannabit.io.cosmostaion.data.model.res.BnbToken
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode

@Parcelize
open class CosmosLine : BaseChain(), Parcelable {

    open var stakeDenom: String? = ""
    open var supportCw20 = false
    open var supportErc20 = false
    open var supportStaking = true
    open var supportNft = false

    open var evmCompatible = false

    open var grpcHost: String = ""
    open var grpcPort = 443
    open var rpcUrl = ""
    private var duration = 20L

    var rewardAddress: String? = ""
    var cosmosAuth: com.google.protobuf.Any? = null
    var cosmosValidators = mutableListOf<StakingProto.Validator>()
    var cosmosBalances: MutableList<Coin>? = null
    var cosmosVestings = mutableListOf<Coin>()
    var cosmosDelegations = mutableListOf<DelegationResponse>()
    var cosmosUnbondings = mutableListOf<UnbondingDelegation>()
    var cosmosRewards = mutableListOf<DelegationDelegatorReward>()

    var tokens = mutableListOf<Token>()

    var lcdAccountInfo: AccountResponse? = null
    var lcdBeaconTokens = mutableListOf<BnbToken>()

    override fun setInfoWithSeed(
        seed: ByteArray?, parentPath: List<ChildNumber>, lastPath: String
    ) {
        privateKey = BaseKey.getPrivateKey(seed, parentPath, lastPath)
        publicKey = BaseKey.getPubKeyFromPKey(privateKey)
        address = BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
    }

    override fun setInfoWithPrivateKey(privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey)
        address = BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
    }

    open fun lcdBalanceValue(denom: String?, isUsd: Boolean? = false): BigDecimal {
        return BigDecimal.ZERO
    }

    fun getInitFee(c: Context): TxProto.Fee? {
        return if (getDefaultFeeCoins(c).isNotEmpty()) {
            val fee = getDefaultFeeCoins(c).first()
            val feeCoin = Coin.newBuilder().setDenom(fee.denom).setAmount(fee.amount).build()
            TxProto.Fee.newBuilder().setGasLimit(getFeeBaseGasAmount()).addAmount(feeCoin).build()
        } else {
            null
        }
    }

    fun getInitPayableFee(c: Context): TxProto.Fee? {
        var feeCoin: Coin? = null
        for (i in 0 until getDefaultFeeCoins(c).size) {
            val minFee = getDefaultFeeCoins(c)[i]
            if (balanceAmount(minFee.denom) >= minFee.amount.toBigDecimal()) {
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

    fun getBaseFee(c: Context, position: Int, denom: String?): TxProto.Fee {
        val gasAmount = getFeeBaseGasDpAmount()
        val feeDatas = getFeeInfos(c)[position].feeDatas
        val rate = feeDatas.firstOrNull { it.denom == denom }?.gasRate ?: BigDecimal.ZERO
        val coinAmount = rate?.multiply(gasAmount)?.setScale(0, RoundingMode.DOWN)
        return TxProto.Fee.newBuilder().setGasLimit(getFeeBaseGasAmount()).addAmount(
            Coin.newBuilder().setDenom(denom).setAmount(coinAmount.toString()).build()
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
            BASE_GAS_AMOUNT.toLong()
        }
    }

    private fun getFeeBaseGasDpAmount(): BigDecimal {
        return BigDecimal(getFeeBaseGasAmount().toString())
    }

    open fun isTxFeePayable(c: Context): Boolean {
        getDefaultFeeCoins(c).forEach { fee ->
            if (balanceAmount(fee.denom) >= BigDecimal(fee.amount)) {
                return true
            }
        }
        return false
    }

    fun getDefaultFeeCoins(c: Context): MutableList<Coin> {
        val result: MutableList<Coin> = mutableListOf()
        val gasAmount = getFeeBaseGasDpAmount()
        if (getFeeInfos(c).size > 0) {
            val feeDatas = getFeeInfos(c)[getFeeBasePosition()].feeDatas
            feeDatas.forEach { feeData ->
                val amount = feeData.gasRate?.multiply(gasAmount)?.setScale(0, RoundingMode.DOWN)
                result.add(
                    Coin.newBuilder().setDenom(feeData.denom).setAmount(amount.toString()).build()
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

    fun isGasSimulable(): Boolean {
        return getChainListParam()?.getAsJsonObject("fee")?.get("isSimulable")?.asBoolean ?: true
    }

    fun voteThreshold(): String {
        return getChainListParam()?.get("voting_threshold")?.asString ?: run {
            "0"
        }
    }

    fun gasMultiply(): Double {
        return getChainListParam()?.getAsJsonObject("fee")?.get("simul_gas_multiply")?.asDouble
            ?: run {
                1.2
            }
    }

    fun balanceAmount(denom: String): BigDecimal {
        if (cosmosBalances?.isNotEmpty() == true) {
            return cosmosBalances?.firstOrNull { it.denom == denom }?.amount?.toBigDecimal()
                ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun balanceValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = balanceAmount(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            } ?: run {
                return BigDecimal.ZERO
            }
        }
        return BigDecimal.ZERO
    }

    fun balanceValueSum(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        if (cosmosBalances?.isNotEmpty() == true) {
            cosmosBalances?.forEach { balance ->
                sum = sum.add(balanceValue(balance.denom, isUsd))
            }
        }
        return sum
    }

    fun vestingAmount(denom: String): BigDecimal {
        if (cosmosVestings.isNotEmpty()) {
            return cosmosVestings.firstOrNull { it.denom == denom }?.amount?.toBigDecimal()
                ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    private fun vestingValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = vestingAmount(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    private fun vestingValueSum(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosVestings.forEach { vesting ->
            sum = sum.add(vestingValue(vesting.denom, isUsd))
        }
        return sum
    }

    fun delegationAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosDelegations.forEach { delegation ->
            delegation.balance?.let {
                sum = sum.add(delegation.balance?.amount?.toBigDecimal())
            } ?: run {
                sum = sum.add(BigDecimal.ZERO)
            }
        }
        return sum
    }

    private fun delegationValueSum(isUsd: Boolean? = false): BigDecimal {
        stakeDenom?.let {
            BaseData.getAsset(apiName, it)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
                val amount = delegationAmountSum()
                asset.decimals?.let { decimal ->
                    return price.multiply(amount).movePointLeft(decimal)
                        .setScale(6, RoundingMode.DOWN)
                }
            }
            return BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun unbondingAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosUnbondings.forEach { unbonding ->
            unbonding.entriesList.forEach { entry ->
                sum = sum.add(entry.balance.toBigDecimal())
            }
        }
        return sum
    }

    private fun unbondingValueSum(isUsd: Boolean? = false): BigDecimal {
        stakeDenom?.let {
            BaseData.getAsset(apiName, it)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
                val amount = unbondingAmountSum()
                asset.decimals?.let { decimal ->
                    return price.multiply(amount).movePointLeft(decimal)
                        .setScale(6, RoundingMode.DOWN)
                }
            }
            return BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun rewardAmountSum(denom: String): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosRewards.forEach { reward ->
            val matchReward = reward.rewardList.firstOrNull { it.denom == denom }
            val rewardAmount = matchReward?.amount?.toBigDecimalOrNull() ?: BigDecimal.ZERO
            sum = sum.add(rewardAmount)
        }
        return sum.movePointLeft(18).setScale(0, RoundingMode.DOWN)
    }

    private fun rewardValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = rewardAmountSum(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    fun rewardAllCoins(): List<Coin> {
        val result: MutableList<Coin> = mutableListOf()
        cosmosRewards.forEach { reward ->
            reward.rewardList.forEach { coin ->
                val calAmount =
                    coin.amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.DOWN)
                if (calAmount > BigDecimal.ZERO) {
                    result.add(
                        Coin.newBuilder().setDenom(coin.denom).setAmount(calAmount.toPlainString())
                            .build()
                    )
                }
            }
        }
        return result
    }

    fun rewardOtherDenoms(): Int {
        return rewardAllCoins().map { it.denom }.distinct().count { it != stakeDenom }
    }

    private fun rewardValueSum(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        rewardAllCoins().forEach { rewardCoin ->
            BaseData.getAsset(apiName, rewardCoin.denom)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
                val amount = rewardCoin.amount.toBigDecimal()
                val value = asset.decimals?.let {
                    price.multiply(amount)?.movePointLeft(it)?.setScale(6, RoundingMode.DOWN)
                }
                sum = sum.add(value)
            }
        }
        return sum
    }

    fun claimableRewards(): MutableList<DelegationDelegatorReward?> {
        val result = mutableListOf<DelegationDelegatorReward?>()

        cosmosRewards.forEach { reward ->
            run loop@{
                for (i in 0 until reward.rewardCount) {
                    val rewardAmount = reward.getReward(i).amount.toBigDecimal().movePointLeft(18)
                        .setScale(0, RoundingMode.DOWN)
                    BaseData.getAsset(apiName, reward.rewardList[i].denom)?.let { asset ->
                        val calAmount = rewardAmount.movePointLeft(asset.decimals ?: 6)
                            .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                        if (calAmount > BigDecimal("0.1")) {
                            result.add(reward)
                            return@loop
                        }
                    }
                }
            }
        }
        return result
    }

    fun valueAbleRewards(): MutableList<DelegationDelegatorReward?> {
        val result: MutableList<DelegationDelegatorReward?> = mutableListOf()

        cosmosRewards.forEach { reward ->
            var eachRewardValue = BigDecimal.ZERO
            for (i in 0 until reward.rewardCount) {
                val rewardAmount = reward.getReward(i).amount.toBigDecimal().movePointLeft(18)
                    .setScale(0, RoundingMode.DOWN)
                BaseData.getAsset(apiName, reward.getReward(i).denom)?.let { asset ->
                    val price = BaseData.getPrice(asset.coinGeckoId, true)
                    val value = price.multiply(rewardAmount).movePointLeft(asset.decimals ?: 6)
                        .setScale(6, RoundingMode.DOWN)
                    eachRewardValue = eachRewardValue.add(value)

                    if (eachRewardValue >= BigDecimal("0.1")) {
                        result.add(reward)
                        return@forEach
                    }
                }
            }
        }
        return result
    }

    open fun allStakingDenomAmount(): BigDecimal? {
        stakeDenom?.let {
            return balanceAmount(it).add(vestingAmount(it))?.add(delegationAmountSum())
                ?.add(unbondingAmountSum())?.add(rewardAmountSum(it))
        }
        return BigDecimal.ZERO
    }

    override fun tokenValue(address: String, isUsd: Boolean?): BigDecimal {
        tokens.firstOrNull { it.address == address }?.let { tokenInfo ->
            val price = BaseData.getPrice(tokenInfo.coinGeckoId, isUsd)
            return price.multiply(tokenInfo.amount?.toBigDecimal())
                .movePointLeft(tokenInfo.decimals).setScale(6, RoundingMode.DOWN)
        } ?: run {
            return BigDecimal.ZERO
        }
    }

    override fun allTokenValue(isUsd: Boolean?): BigDecimal {
        var result = BigDecimal.ZERO
        tokens.forEach { token ->
            val price = BaseData.getPrice(token.coinGeckoId, isUsd)
            val value = price.multiply(token.amount?.toBigDecimal()).movePointLeft(token.decimals)
                .setScale(6, RoundingMode.DOWN)
            result = result.add(value)
        }
        return result
    }

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return balanceValueSum(isUsd).add(vestingValueSum(isUsd)).add(delegationValueSum(isUsd))
            .add(unbondingValueSum(isUsd)).add(rewardValueSum(isUsd))
    }

    override fun allValue(isUsd: Boolean?): BigDecimal {
        return allAssetValue(isUsd).add(allTokenValue(isUsd))
    }

    override fun explorerAccount(): Uri? {
        getChainListParam()?.getAsJsonObject("explorer")
            ?.get("account")?.asString?.let { urlString ->
                address?.let {
                    return Uri.parse(urlString.replace("\${address}", it))

                } ?: run {
                    return null
                }
            }
        return null
    }

    override fun explorerTx(hash: String?): Uri? {
        getChainListParam()?.getAsJsonObject("explorer")?.get("tx")?.asString?.let { urlString ->
            hash?.let {
                return Uri.parse(urlString.replace("\${hash}", it))

            } ?: run {
                return null
            }
        }
        return null
    }

    override fun explorerProposal(id: String?): Uri? {
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

    open fun denomValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        return if (denom == stakeDenom) {
            balanceValue(denom, isUsd).add(vestingValue(denom, isUsd))
                .add(rewardValue(denom, isUsd)).add(delegationValueSum(isUsd))
                .add(unbondingValueSum(isUsd))

        } else {
            balanceValue(denom, isUsd).add(vestingValue(denom, isUsd))
                .add(rewardValue(denom, isUsd))
        }
    }

    fun monikerImg(opAddress: String?): String {
        return "$CHAIN_BASE_URL$apiName/moniker/$opAddress.png"
    }

    fun getGrpc(): Pair<String, Int> {
        val endPoint = Prefs.getGrpcEndpoint(this)
        if (endPoint.isNotEmpty() && endPoint.split(":").count() == 2) {
            val host = endPoint.split(":")[0].trim()
            val port = endPoint.split(":").getOrNull(1)?.trim()?.toIntOrNull() ?: 443
            return Pair(host, port)
        }
        return Pair(grpcHost, grpcPort)
    }
}


fun allCosmosLines(): MutableList<CosmosLine> {
    val lines = mutableListOf<CosmosLine>()
    lines.add(ChainCosmos())
    lines.add(ChainAkash())
//    lines.add(ChainAlthea118())
    lines.add(ChainArchway())
    lines.add(ChainAssetMantle())
    lines.add(ChainAxelar())
    lines.add(ChainBand())
    lines.add(ChainBitcanna())
    lines.add(ChainBitsong())
    lines.add(ChainCelestia())
    lines.add(ChainChihuahua())
    lines.add(ChainComdex())
    lines.add(ChainCoreum())
    lines.add(ChainCrescent())
    lines.add(ChainCryptoorg())
    lines.add(ChainCudos())
    lines.add(ChainDesmos())
    lines.add(ChainDydx())
    lines.add(ChainEmoney())
    lines.add(ChainFetchAi())
    lines.add(ChainFetchAi60Secp())
    lines.add(ChainFetchAi60Old())
    lines.add(ChainFinschia())
    lines.add(ChainGovgen())
    lines.add(ChainGravityBridge())
    lines.add(ChainInjective())
    lines.add(ChainIris())
    lines.add(ChainIxo())
    lines.add(ChainJuno())
    lines.add(ChainKava459())
    lines.add(ChainKava118())
    lines.add(ChainKi())
    lines.add(ChainKyve())
    lines.add(ChainLikeCoin())
    lines.add(ChainLum880())
    lines.add(ChainLum118())
    lines.add(ChainMars())
    lines.add(ChainMedibloc())
    lines.add(ChainNeutron())
    lines.add(ChainNibiru())
    lines.add(ChainNoble())
    lines.add(ChainNyx())
    lines.add(ChainOmniflix())
    lines.add(ChainOnomy())
    lines.add(ChainOsmosis())
    lines.add(ChainPassage())
    lines.add(ChainPersistence118())
    lines.add(ChainPersistence750())
    lines.add(ChainProvenance())
    lines.add(ChainQuasar())
    lines.add(ChainQuicksilver())
    lines.add(ChainRegen())
    lines.add(ChainRizon())
    lines.add(ChainSaga())
    lines.add(ChainSecret529())
    lines.add(ChainSecret118())
    lines.add(ChainSei())
    lines.add(ChainSentinel())
    lines.add(ChainShentu())
    lines.add(ChainSommelier())
    lines.add(ChainStafi())
    lines.add(ChainStargaze())
    lines.add(ChainStride())
    lines.add(ChainTeritori())
    lines.add(ChainTerra())
    lines.add(ChainUx())
    lines.add(ChainXpla())
    lines.add(ChainBinanceBeacon())
    lines.add(ChainOkt996Keccak())
    lines.add(ChainOkt996Secp())

    lines.forEach { line ->
        if (line.chainIdCosmos.isEmpty()) {
            line.getChainListParam()?.get("chain_id_cosmos")?.asString?.let { cosmosChainId ->
                line.chainIdCosmos = cosmosChainId
            }
        }
    }
    if (!Prefs.displayLegacy) {
        return lines.filter { it.isDefault }.toMutableList()
    }
    return lines
}

val DEFAULT_DISPLAY_COSMOS = mutableListOf(
    "cosmos118", "neutron118", "kava459", "osmosis118", "dydx118", "crypto-org394", "celestia118"
)