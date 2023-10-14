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
import com.google.gson.Gson
import com.google.protobuf.ByteString
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAkash
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCosmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainEvmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInjective
import wannabit.io.cosmostaion.chain.cosmosClass.ChainIris
import wannabit.io.cosmostaion.chain.cosmosClass.ChainJuno
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.chain.cosmosClass.ChainLum118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOsmosis
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.AccountResponse
import wannabit.io.cosmostaion.data.model.BnbToken
import wannabit.io.cosmostaion.data.model.Cw20Balance
import wannabit.io.cosmostaion.data.model.NetworkResult
import wannabit.io.cosmostaion.data.model.Token
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.TimeUnit

open class CosmosLine : BaseChain() {

    open lateinit var stakeDenom: String
    open var supportCw20 = false
    open var supportErc20 = false

    open var evmCompatible = false

    private var grpcPort = 443
    private var duration = 8L

    var cosmosAuth: com.google.protobuf.Any? = null
    var cosmosBalances = mutableListOf<Coin>()
    var cosmosVestings = mutableListOf<Coin>()
    var cosmosDelegations = mutableListOf<DelegationResponse>()
    var cosmosUnbondings = mutableListOf<UnbondingDelegation>()
    var cosmosRewards = mutableListOf<DelegationDelegatorReward>()

    var tokens = mutableListOf<Token>()

    var lcdAccountInfo: AccountResponse? = null
    var lcdBeaconTokens = mutableListOf<BnbToken>()

    interface LoadDataCallback {
        fun onDataLoaded(isLoaded: Boolean)
    }

    private var loadDataCallback: LoadDataCallback? = null

    fun setLoadDataCallBack(callback: LoadDataCallback) {
        loadDataCallback = callback
    }

    private fun getChannel(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(grpcHost, grpcPort).useTransportSecurity().build()
    }

    fun allAssetValue(): BigDecimal {
        var allValue: BigDecimal
        if (this is ChainBinanceBeacon) {
            allValue = lcdBalanceValue(stakeDenom)

        } else {
            allValue = balanceValueSum().add(vestingValueSum()).add(delegationValueSum()).
            add(unbondingValueSum()).add(rewardValueSum()).add(allCw20ValueSum())
            if (supportCw20) {
                allValue = allValue.add(allCw20ValueSum())
            }
        }
        return allValue
    }

    fun loadData() {
        if (this is ChainBinanceBeacon) {
            loadLcdData()
        } else {
            loadGrpcData()
        }
    }

    private fun loadGrpcData() = CoroutineScope(Dispatchers.IO).launch {
        val channel = getChannel()
        val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = QueryProto.QueryAccountRequest.newBuilder().setAddress(address).build()

        try {
            stub.account(request)?.let { response ->
                cosmosAuth = response.account
                loadGrpcMoreData(channel)
            }
        } catch (e: Exception) {
            loadDataCallback?.onDataLoaded(true)
            fetched = true
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

    private fun loadGrpcMoreData(channel: ManagedChannel) = runBlocking {
        CoroutineScope(Dispatchers.Default).let {
            if (supportCw20) {
                loadCw20Token()
            }

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

    private fun loadLcdData() = runBlocking {
        CoroutineScope(Dispatchers.Default).let {
            if (this@CosmosLine is ChainBinanceBeacon) {
//                loadNodeInfo()
                loadAccountInfo()
                loadBeaconTokens()
            }

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

    private suspend fun loadCw20Token() {
        when (val response = safeApiCall { RetrofitInstance.mintscanApi.cw20token(this.apiName) }) {
            is NetworkResult.Success -> {
                tokens = response.data.assets
                loadAllCw20Balance()
            }

            is NetworkResult.Error -> {
                return
            }
        }
    }

    private fun loadCw20Balance(channel: ManagedChannel, token: Token) {
        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val req = Cw20Balance(address)
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val request = com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder().setAddress(token.address).setQueryData(queryData).build()

        stub.smartContractState(request)?.let { response ->
            val json = JSONObject(response.data.toStringUtf8())
            token.amount = json.get("balance").toString()
        }
    }

    private fun loadAllCw20Balance() {
        val channel = getChannel()
        val scope = CoroutineScope(Dispatchers.Default)
        val deferredList = mutableListOf<Deferred<Unit>>()

        tokens.forEach { token ->
            val deferred = scope.async {
                loadCw20Balance(channel, token)
            }
            deferredList.add(deferred)
        }

        runBlocking {
            deferredList.awaitAll()
        }

        try {
            channel.shutdown()
        } catch (_: Exception) {
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
            return cosmosVestings.firstOrNull { it.denom == denom }?.amount?.toBigDecimal() ?: BigDecimal.ZERO
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

    fun cw20Value(address: String): BigDecimal {
        val token = tokens.firstOrNull { it.address == address }
        if (token != null) {
            val price = BaseData.getPrice(token.coinGeckoId)
            return price.multiply(token.amount?.toBigDecimal()).movePointLeft(token.decimals).setScale(6, RoundingMode.HALF_UP)
        }
        return BigDecimal.ZERO
    }

    private fun allCw20ValueSum(): BigDecimal {
        var sumValue = BigDecimal.ZERO
        tokens.forEach { token ->
            val price = BaseData.getPrice(token.coinGeckoId)
            val value = price.multiply(token.amount?.toBigDecimal()).movePointLeft(token.decimals).setScale(6, RoundingMode.HALF_UP)
            sumValue = sumValue.add(value)
        }
        return sumValue
    }

    fun denomValue(denom: String): BigDecimal {
        return if (denom == stakeDenom) {
            balanceValue(denom).add(vestingValue(denom)).add(rewardValue(denom)).
            add(delegationValueSum()).add(unbondingValueSum())

        } else {
            balanceValue(denom).add(vestingValue(denom)).add(rewardValue(denom))
        }
    }

    private suspend fun loadNodeInfo() {
        when (val response = safeApiCall { RetrofitInstance.beaconApi.nodeInfo() }) {
            is NetworkResult.Success -> {

            }

            is NetworkResult.Error -> {
                return
            }
        }
    }

    private suspend fun loadAccountInfo() {
        when (val response = safeApiCall { RetrofitInstance.beaconApi.accountInfo(address) }) {
            is NetworkResult.Success -> {
                lcdAccountInfo = response.data
            }

            is NetworkResult.Error -> {
                return
            }
        }
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

    fun lcdBalanceAmount(denom: String): BigDecimal {
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

    fun lcdBalanceValue(denom: String): BigDecimal {
        if (denom == stakeDenom) {
            val amount = lcdBalanceAmount(denom)
            val price = BaseData.getPrice(ChainBinanceBeacon().BNB_GECKO_ID)
            return price.multiply(amount).setScale(6, RoundingMode.HALF_UP)
        }
        return BigDecimal.ZERO
    }

    fun lcdBalanceValueSum(): BigDecimal {
        var sumValue = BigDecimal.ZERO
        lcdAccountInfo?.balances?.forEach { balance ->
            sumValue = sumValue.add(lcdBalanceValue(balance.symbol))
        }
        return sumValue
    }
}


fun allCosmosLines(): List<CosmosLine> {
    val lines = mutableListOf<CosmosLine>()
    lines.add(ChainCosmos())
    lines.add(ChainAkash())
    lines.add(ChainBinanceBeacon())
    lines.add(ChainEvmos())
    lines.add(ChainInjective())
    lines.add(ChainIris())
    lines.add(ChainJuno())
    lines.add(ChainKava459())
    lines.add(ChainKava118())
    lines.add(ChainLum118())
    lines.add(ChainOsmosis())
    return lines
}

val DEFAULT_DISPLAY_COSMOS = mutableListOf("cosmos118", "osmosis118", "binance714", "evmos60")