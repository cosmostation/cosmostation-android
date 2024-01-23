package wannabit.io.cosmostaion.chain

import android.content.Context
import android.os.Parcelable
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmos.staking.v1beta1.StakingProto.DelegationResponse
import com.cosmos.staking.v1beta1.StakingProto.UnbondingDelegation
import com.cosmos.tx.v1beta1.TxProto
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAkash
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCosmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCrescent
import wannabit.io.cosmostaion.chain.cosmosClass.ChainEvmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainFetchAi
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInjective
import wannabit.io.cosmostaion.chain.cosmosClass.ChainIris
import wannabit.io.cosmostaion.chain.cosmosClass.ChainJuno
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava60
import wannabit.io.cosmostaion.chain.cosmosClass.ChainLum118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Secp
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOmniflix
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOsmosis
import wannabit.io.cosmostaion.chain.cosmosClass.ChainPersistence118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainStargaze
import wannabit.io.cosmostaion.chain.cosmosClass.ChainStride
import wannabit.io.cosmostaion.chain.cosmosClass.ChainXplaKeccak256
import wannabit.io.cosmostaion.common.BaseConstant.BASE_GAS_AMOUNT
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.CosmostationConstants.CHAIN_BASE_URL
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.res.AccountResponse
import wannabit.io.cosmostaion.data.model.res.BnbToken
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Param
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

    open var evmCompatible = false

    open var grpcHost: String = ""
    open var grpcPort = 443
    open var rpcUrl = ""
    private var duration = 20L

    var rewardAddress: String? = ""
    var cosmosAuth: com.google.protobuf.Any? = null
    var cosmosValidators = mutableListOf<StakingProto.Validator>()
    var cosmosBalances: MutableList<Coin>? = mutableListOf()
    var cosmosVestings = mutableListOf<Coin>()
    var cosmosDelegations = mutableListOf<DelegationResponse>()
    var cosmosUnbondings = mutableListOf<UnbondingDelegation>()
    var cosmosRewards = mutableListOf<DelegationDelegatorReward>()

    var tokens = mutableListOf<Token>()

    var cw20tokens = mutableListOf<Token>()
    var erc20tokens = mutableListOf<Token>()
    var param: Param? = null

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
        publicKey = BaseKey.getPubKeyFromPKey(privateKey)
        address = BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
    }

    open fun lcdBalanceValue(denom: String?): BigDecimal {
        return BigDecimal.ZERO
    }

    open fun allAssetValue(): BigDecimal {
        return balanceValueSum().add(vestingValueSum()).add(delegationValueSum())
            .add(unbondingValueSum()).add(rewardValueSum())
    }

    fun allValue(): BigDecimal {
        return allAssetValue().add(allTokenValue())
    }

    fun getInitFee(c: Context): TxProto.Fee? {
        val fee = getDefaultFeeCoins(c).first()
        val feeCoin = Coin.newBuilder().setDenom(fee.denom).setAmount(fee.amount).build()
        return TxProto.Fee.newBuilder().setGasLimit(BASE_GAS_AMOUNT.toLong()).addAmount(feeCoin)
            .build()
    }

    fun getBaseFee(c: Context, position: Int, denom: String?): TxProto.Fee {
        val gasAmount = BigDecimal(BASE_GAS_AMOUNT)
        val feeDatas = getFeeInfos(c)[position].feeDatas
        val rate = feeDatas.firstOrNull { it.denom == denom }?.gasRate ?: BigDecimal.ZERO
        val coinAmount = rate?.multiply(gasAmount)?.setScale(0, RoundingMode.DOWN)
        return TxProto.Fee.newBuilder().setGasLimit(BASE_GAS_AMOUNT.toLong()).addAmount(
            Coin.newBuilder().setDenom(denom).setAmount(coinAmount.toString()).build()
        ).build()
    }

    fun getFeeBasePosition(): Int {
        return param?.params?.chainlistParams?.fee?.base?.toInt() ?: 0
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
        val gasAmount = BigDecimal(BASE_GAS_AMOUNT)
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
        param?.params?.chainlistParams?.fee?.rate?.forEach { rate ->
            result.add(FeeInfo(rate))
        }

        if (result.size == 1) {
            result[0].title = c.getString(R.string.str_fixed)
            result[0].msg = c.getString(R.string.str_fee_speed_title_fixed)
        } else if (result.size == 2) {
            result[1].title = c.getString(R.string.str_average)
            result[1].msg = c.getString(R.string.str_fee_speed_title_average)
            if (result[0].feeDatas[0].gasRate == BigDecimal.ZERO) {
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
            if (result[0].feeDatas[0].gasRate == BigDecimal.ZERO) {
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
        param?.params?.chainlistParams?.fee?.simulGasMultiply?.let { multiply ->
            return multiply
        } ?: run {
            return 1.2
        }
    }

    suspend fun loadParam(): Param? {
        return when (val response = safeApiCall { RetrofitInstance.mintscanApi.param(apiName) }) {
            is NetworkResult.Success -> {
                response.data.body()
            }

            is NetworkResult.Error -> {
                null
            }
        }
    }

    suspend fun loadCw20Token(): MutableList<Token> {
        return when (val response =
            safeApiCall { RetrofitInstance.mintscanApi.cw20token(apiName) }) {
            is NetworkResult.Success -> {
                response.data.assets
            }

            is NetworkResult.Error -> {
                mutableListOf()
            }
        }
    }

    suspend fun loadErc20Token(): MutableList<Token> {
        return when (val response =
            safeApiCall { RetrofitInstance.mintscanApi.erc20token(this.apiName) }) {
            is NetworkResult.Success -> {
                response.data.assets
            }

            is NetworkResult.Error -> {
                mutableListOf()
            }
        }
    }

    fun balanceAmount(denom: String): BigDecimal {
        if (cosmosBalances?.isNotEmpty() == true) {
            return cosmosBalances?.firstOrNull { it.denom == denom }?.amount?.toBigDecimal()
                ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun balanceValue(denom: String): BigDecimal {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId)
            val amount = balanceAmount(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            } ?: run {
                return BigDecimal.ZERO
            }
        }
        return BigDecimal.ZERO
    }

    fun balanceValueSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        if (cosmosBalances?.isNotEmpty() == true) {
            cosmosBalances?.forEach { balance ->
                sum = sum.add(balanceValue(balance.denom))
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

    private fun vestingValue(denom: String): BigDecimal {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId)
            val amount = vestingAmount(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    private fun vestingValueSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosVestings.forEach { vesting ->
            sum = sum.add(vestingValue(vesting.denom))
        }
        return sum
    }

    fun delegationAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosDelegations.forEach { delegation ->
            sum = sum.add(delegation.balance.amount.toBigDecimal())
        }
        return sum
    }

    private fun delegationValueSum(): BigDecimal {
        stakeDenom?.let {
            BaseData.getAsset(apiName, it)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId)
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

    private fun unbondingValueSum(): BigDecimal {
        stakeDenom?.let {
            BaseData.getAsset(apiName, it)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId)
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

    private fun rewardValue(denom: String): BigDecimal {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId)
            val amount = rewardAmountSum(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    fun rewardAllCoins(): List<Coin> {
        return cosmosRewards.flatMap { reward ->
            reward.rewardList.mapNotNull { coin ->
                val calAmount =
                    coin.amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.DOWN)
                if (calAmount > BigDecimal.ZERO) {
                    Coin.newBuilder().setDenom(coin.denom).setAmount(calAmount.toPlainString())
                        .build()
                } else {
                    null
                }
            }
        }
    }

    fun rewardOtherDenoms(): Int {
        return rewardAllCoins().map { it.denom }.distinct().count { it != stakeDenom }
    }

    private fun rewardValueSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        rewardAllCoins().forEach { rewardCoin ->
            BaseData.getAsset(apiName, rewardCoin.denom)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId)
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
                        if (calAmount > BigDecimal("0.01")) {
                            result.add(reward)
                            return@loop
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

    fun tokenValue(address: String): BigDecimal {
        tokens.firstOrNull { it.address == address }?.let { tokenInfo ->
            val price = BaseData.getPrice(tokenInfo.coinGeckoId)
            return price.multiply(tokenInfo.amount?.toBigDecimal())
                .movePointLeft(tokenInfo.decimals).setScale(6, RoundingMode.DOWN)
        } ?: run {
            return BigDecimal.ZERO
        }
    }

    fun allTokenValue(): BigDecimal {
        var result = BigDecimal.ZERO
        tokens.forEach { token ->
            val price = BaseData.getPrice(token.coinGeckoId)
            val value = price.multiply(token.amount?.toBigDecimal()).movePointLeft(token.decimals)
                .setScale(6, RoundingMode.DOWN)
            result = result.add(value)
        }
        return result
    }

    open fun denomValue(denom: String): BigDecimal {
        return if (denom == stakeDenom) {
            balanceValue(denom).add(vestingValue(denom)).add(rewardValue(denom))
                .add(delegationValueSum()).add(unbondingValueSum())

        } else {
            balanceValue(denom).add(vestingValue(denom)).add(rewardValue(denom))
        }
    }

    fun monikerImg(opAddress: String?): String {
        return "$CHAIN_BASE_URL$apiName/moniker/$opAddress.png"
    }
}


fun allCosmosLines(): MutableList<CosmosLine> {
    val lines = mutableListOf<CosmosLine>()
    lines.add(ChainCosmos())
    lines.add(ChainAkash())
    lines.add(ChainCrescent())
    lines.add(ChainEvmos())
    lines.add(ChainFetchAi())
    lines.add(ChainInjective())
    lines.add(ChainIris())
    lines.add(ChainJuno())
    lines.add(ChainKava60())
    lines.add(ChainKava459())
    lines.add(ChainKava118())
    lines.add(ChainLum118())
    lines.add(ChainNeutron())
    lines.add(ChainOsmosis())
    lines.add(ChainOmniflix())
    lines.add(ChainPersistence118())
    lines.add(ChainStargaze())
    lines.add(ChainStride())
    lines.add(ChainXplaKeccak256())
    lines.add(ChainBinanceBeacon())
    lines.add(ChainOkt996Secp())
    lines.add(ChainOkt60())

    lines.forEach { line ->
        if (line.chainId.isEmpty()) {
            line.chainId =
                BaseData.chains?.firstOrNull { it.chain == line.apiName }?.chainId.toString()
        }
    }
    if (!Prefs.displayLegacy) {
        return lines.filter { it.isDefault }.toMutableList()
    }
    return lines
}

val DEFAULT_DISPLAY_COSMOS = mutableListOf("cosmos118", "evmos60", "osmosis118")