package wannabit.io.cosmostaion.chain

import android.net.Uri
import android.os.Parcelable
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmos.staking.v1beta1.StakingProto.DelegationResponse
import com.cosmos.staking.v1beta1.StakingProto.UnbondingDelegation
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCosmos
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants.CHAIN_BASE_URL
import wannabit.io.cosmostaion.data.model.res.AccountResponse
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode

@Parcelize
open class CosmosLine : BaseChain(), Parcelable {

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

//    var cw721s = mutableListOf<JsonObject>()
//    var cw721Fetched = false
//    var cw721Models = mutableListOf<Cw721Model>()

    open fun lcdBalanceValue(denom: String?, isUsd: Boolean? = false): BigDecimal {
        return BigDecimal.ZERO
    }

    fun isGasSimulable(): Boolean {
        return getChainListParam()?.getAsJsonObject("fee")?.get("isSimulable")?.asBoolean ?: true
    }

    fun isBankLocked(): Boolean {
        return getChainListParam()?.get("isBankLocked")?.asBoolean ?: false
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

    fun compoundAbleRewards(): MutableList<DelegationDelegatorReward?> {
        val result: MutableList<DelegationDelegatorReward?> = mutableListOf()

        stakeDenom?.let { denom ->
            cosmosRewards.forEach { reward ->
                reward.rewardList.firstOrNull { it.denom == denom }?.amount?.let { amount ->
                    val rewardAmount =
                        amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.DOWN)
                    BaseData.getAsset(apiName, denom)?.let { asset ->
                        val price = BaseData.getPrice(asset.coinGeckoId, true)
                        val value = price.multiply(rewardAmount)
                            .movePointLeft(asset.decimals ?: 6).setScale(6, RoundingMode.DOWN)
                        if (value >= BigDecimal("0.1")) {
                            result.add(reward)
                        }
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


fun allCosmosLines(): MutableList<BaseChain> {
    val lines = mutableListOf<BaseChain>()
    lines.add(ChainCosmos())
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

//    lines.forEach { line ->
//        if (line.chainIdCosmos.isEmpty()) {
//            line.getChainListParam()?.get("chain_id_cosmos")?.asString?.let { cosmosChainId ->
//                line.chainIdCosmos = cosmosChainId
//            }
//        }
//    }
    if (!Prefs.displayLegacy) {
        return lines.filter { it.isDefault }.toMutableList()
    }
    return lines
}

val DEFAULT_DISPLAY_COSMOS = mutableListOf(
    "cosmos118", "neutron118", "osmosis118", "dydx118", "crypto-org394", "celestia118"
)