package wannabit.io.cosmostaion.chain

import com.cosmos.auth.v1beta1.QueryGrpc
import com.cosmos.auth.v1beta1.QueryProto
import com.cosmos.bank.v1beta1.QueryGrpc.newBlockingStub
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest
import com.cosmos.base.query.v1beta1.PaginationProto
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorDelegationsRequest
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorUnbondingDelegationsRequest
import com.cosmos.staking.v1beta1.StakingProto.DelegationResponse
import com.cosmos.staking.v1beta1.StakingProto.UnbondingDelegation
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAkash
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCosmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainEvmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.chain.cosmosClass.ChainLum118
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.TimeUnit

open class CosmosLine : BaseChain() {

    open lateinit var stakeDenom: String

    private var grpcPort = 443
    private var duration = 8L

    var cosmosAuth: com.google.protobuf.Any? = null
    var cosmosBalances = mutableListOf<Coin>()
    var cosmosVestings = mutableListOf<Coin>()
    var cosmosDelegations = mutableListOf<DelegationResponse>()
    var cosmosUnbondings = mutableListOf<UnbondingDelegation>()
    var cosmosRewards = mutableListOf<DelegationDelegatorReward>()

    interface LoadDataCallback {
        fun onDataLoaded(isLoaded: Boolean)
    }

    private var loadDataCallback: LoadDataCallback? = null

    fun setLoadDataCallBack(callback: LoadDataCallback) {
        loadDataCallback = callback
    }

    fun loadAuth() = CoroutineScope(Dispatchers.IO).launch {
        val channel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort).useTransportSecurity().build()
        val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = QueryProto.QueryAccountRequest.newBuilder().setAddress(address).build()

        try {
            stub.account(request)?.let { response ->
                cosmosAuth = response.account
                loadData(channel)
            } ?: run {
                fetched = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            channel.shutdown()
            try {
                if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                    channel.shutdownNow()
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private fun loadData(channel: ManagedChannel) = runBlocking {
        CoroutineScope(Dispatchers.Default).let {
            loadBalance(channel)
            loadDelegation(channel)
            loadUnbonding(channel)
            loadReward(channel)

            BaseUtils.onParseVestingAccount(this@CosmosLine)
            loadDataCallback?.onDataLoaded(true)
            fetched = true
            it.cancel()
        }
    }

    private fun loadBalance(channel: ManagedChannel) {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(2000).build()
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(address).build()

        stub.allBalances(request)?.let { response ->
            cosmosBalances = response.balancesList
        }
    }

    private fun loadDelegation(channel: ManagedChannel) {
        val stub = com.cosmos.staking.v1beta1.QueryGrpc.newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = QueryDelegatorDelegationsRequest.newBuilder().setDelegatorAddr(address).build()

        stub.delegatorDelegations(request)?.let { response ->
            cosmosDelegations = response.delegationResponsesList
        }
    }

    private fun loadUnbonding(channel: ManagedChannel) {
        val stub = com.cosmos.staking.v1beta1.QueryGrpc.newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = QueryDelegatorUnbondingDelegationsRequest.newBuilder().setDelegatorAddr(address).build()

        stub.delegatorUnbondingDelegations(request)?.let { response ->
            cosmosUnbondings = response.unbondingResponsesList
        }
    }

    private fun loadReward(channel: ManagedChannel) {
        val stub = com.cosmos.distribution.v1beta1.QueryGrpc.newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = com.cosmos.distribution.v1beta1.QueryProto.QueryDelegationTotalRewardsRequest.newBuilder().setDelegatorAddress(address).build()

        stub.delegationTotalRewards(request)?.let { response ->
            cosmosRewards = response.rewardsList
        }
    }

    fun balanceAmount(denom: String): BigDecimal {
        if (cosmosBalances.isNotEmpty()) {
            return cosmosBalances.first { it.denom == denom }.amount.toBigDecimal()
        }
        return BigDecimal.ZERO
    }

    fun balanceValue(denom: String): BigDecimal {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId)
            val amount = balanceAmount(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.HALF_UP)
            } ?: run {
                return BigDecimal.ZERO
            }
        }
        return BigDecimal.ZERO
    }

    fun balanceValueSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        if (cosmosBalances.isNotEmpty()) {
            cosmosBalances.forEach { balance ->
                sum = sum.add(balanceValue(balance.denom))
            }
        }
        return sum
    }

    fun vestingAmount(denom: String): BigDecimal {
        if (cosmosVestings.isNotEmpty()) {
            return cosmosVestings.first { it.denom == denom }.amount.toBigDecimal()
        }
        return BigDecimal.ZERO
    }

    fun vestingValue(denom: String): BigDecimal {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId)
            val amount = vestingAmount(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.HALF_UP)
            }
        }
        return BigDecimal.ZERO
    }

    fun vestingValueSum(): BigDecimal {
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

    fun delegationValueSum(): BigDecimal {
        BaseData.getAsset(apiName, stakeDenom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId)
            val amount = delegationAmountSum()
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.HALF_UP)
            }
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

    fun unbondingValueSum(): BigDecimal {
        BaseData.getAsset(apiName, stakeDenom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId)
            val amount = unbondingAmountSum()
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.HALF_UP)
            }
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
        return sum.movePointLeft(18).setScale(0, RoundingMode.HALF_UP)
    }

    fun rewardValue(denom: String): BigDecimal {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId)
            val amount = rewardAmountSum(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.HALF_UP)
            }
        }
        return BigDecimal.ZERO
    }

    fun rewardAllCoins(): List<Coin> {
        val allRewards = mutableListOf<Coin>()
        cosmosRewards.forEach { reward ->
            reward.rewardList.forEach { coin ->
                val calReward = Coin.newBuilder().
                setDenom(coin.denom).
                setAmount(coin.amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.HALF_UP).toString()).build()
                allRewards.add(calReward)
            }
        }
        return allRewards
    }

    fun rewardOtherDenoms(): Int {
        val otherDenoms = mutableListOf<String>()
        rewardAllCoins().forEach { coin ->
            if (!otherDenoms.contains(coin.denom)) {
                otherDenoms.add(coin.denom)
            }
        }
        otherDenoms.removeAll { it == stakeDenom }
        return otherDenoms.size
    }

    fun rewardValueSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        rewardAllCoins().forEach { rewardCoin ->
            BaseData.getAsset(apiName, rewardCoin.denom)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId)
                val amount = rewardCoin.amount.toBigDecimal()
                val value = asset.decimals?.let { price.multiply(amount)?.movePointLeft(it)?.setScale(6, RoundingMode.HALF_UP) }
                sum = sum.add(value)
            }
        }
        return sum
    }

    fun allStakingDenomAmount(): BigDecimal? {
        return balanceAmount(stakeDenom).add(vestingAmount(stakeDenom))?.add(delegationAmountSum())?.
        add(unbondingAmountSum())?.add(rewardAmountSum(stakeDenom))
    }

    fun allAssetValue(): BigDecimal {
        val allValue = balanceValueSum().add(vestingValueSum()).add(delegationValueSum()).
        add(unbondingValueSum()).add(rewardValueSum())
        return allValue
    }

    fun denomValue(denom: String): BigDecimal {
        return if (denom == stakeDenom) {
            balanceValue(denom).add(vestingValue(denom)).add(rewardValue(denom)).
            add(delegationValueSum()).add(unbondingValueSum())

        } else {
            balanceValue(denom).add(vestingValue(denom)).add(rewardValue(denom))
        }
    }
}


fun allCosmosLines(): List<CosmosLine> {
    val lines = mutableListOf<CosmosLine>()
    lines.add(ChainCosmos())
    lines.add(ChainAkash())
    lines.add(ChainEvmos())
    lines.add(ChainKava459())
    lines.add(ChainLum118())
    return lines
}