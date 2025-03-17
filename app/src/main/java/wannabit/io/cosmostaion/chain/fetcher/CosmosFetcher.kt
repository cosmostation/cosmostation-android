package wannabit.io.cosmostaion.chain.fetcher

import com.cosmos.auth.v1beta1.AuthProto
import com.cosmos.base.tendermint.v1beta1.QueryProto
import com.cosmos.base.tendermint.v1beta1.ServiceGrpc
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.distribution.v1beta1.DistributionProto
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmos.staking.v1beta1.StakingProto.DelegationResponse
import com.cosmos.staking.v1beta1.StakingProto.UnbondingDelegation
import com.cosmos.vesting.v1beta1.VestingProto
import com.desmos.profiles.v3.ModelsProfileProto
import com.ethermint.types.v1.AccountProto
import com.google.gson.JsonObject
import com.google.protobuf.Any
import com.google.protobuf.Timestamp
import com.ibc.core.channel.v1.QueryGrpc
import com.ibc.core.channel.v1.QueryProto.QueryChannelClientStateRequest
import com.ibc.lightclients.tendermint.v1.TendermintProto
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dateToLong
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.Cw721Model
import wannabit.io.cosmostaion.data.model.res.AssetPath
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.TimeUnit

open class CosmosFetcher(private val chain: BaseChain) {

    var cosmosLcdAuth: JsonObject? = null
    var cosmosAuth: Any? = null

    var cosmosAccountNumber: Long? = null
    var cosmosSequence: Long? = null
    var cosmosBalances: MutableList<CoinProto.Coin>? = null
    var cosmosVestings = mutableListOf<CoinProto.Coin>()
    var cosmosDelegations = mutableListOf<DelegationResponse>()
    var cosmosUnbondings = mutableListOf<UnbondingDelegation>()
    var cosmosRewards = mutableListOf<DistributionProto.DelegationDelegatorReward>()
    var rewardAddress: String? = ""
    var cosmosValidators = mutableListOf<StakingProto.Validator>()
    var cosmosOriginValidators = mutableListOf<StakingProto.Validator>()
    var cosmosBaseFees = mutableListOf<CoinProto.DecCoin>()

    var tokens = mutableListOf<Token>()
    var cw721s = mutableListOf<JsonObject>()
    var cw721Fetched = false
    var cw721Models = mutableListOf<Cw721Model>()

    open fun denomValue(denom: String, isUsd: Boolean? = false): BigDecimal? {
        return if (denom == chain.stakeDenom) {
            balanceValue(denom, isUsd).add(vestingValue(denom, isUsd))
                .add(rewardValue(denom, isUsd)).add(delegationValueSum(isUsd))
                .add(unbondingValueSum(isUsd))

        } else {
            balanceValue(denom, isUsd).add(vestingValue(denom, isUsd))
                .add(rewardValue(denom, isUsd))
        }
    }

    fun tokenValue(address: String, isUsd: Boolean? = false): BigDecimal {
        tokens.firstOrNull { it.contract == address }?.let { tokenInfo ->
            val price = BaseData.getPrice(tokenInfo.coinGeckoId, isUsd)
            return price.multiply(tokenInfo.amount?.toBigDecimal())
                .movePointLeft(tokenInfo.decimals).setScale(6, RoundingMode.DOWN)
        } ?: run {
            return BigDecimal.ZERO
        }
    }

    fun allTokenValue(isUsd: Boolean? = false): BigDecimal {
        var result = BigDecimal.ZERO
        tokens.forEach { token ->
            val price = BaseData.getPrice(token.coinGeckoId, isUsd)
            val value = price.multiply(token.amount?.toBigDecimal()).movePointLeft(token.decimals)
                .setScale(6, RoundingMode.DOWN)
            result = result.add(value)
        }
        return result
    }

    open fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return balanceValueSum(isUsd).add(vestingValueSum(isUsd)).add(delegationValueSum(isUsd))
            .add(unbondingValueSum(isUsd)).add(rewardValueSum(isUsd))
    }

    fun valueCoinCnt(): Int {
        return cosmosBalances?.count { BaseData.getAsset(chain.apiName, it.denom) != null } ?: 0
    }

    fun valueTokenCnt(): Int {
        return tokens.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
    }

    fun balanceAmount(denom: String): BigDecimal {
        if (cosmosBalances?.isNotEmpty() == true) {
            return cosmosBalances?.firstOrNull { it.denom == denom }?.amount?.toBigDecimal()
                ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun balanceValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, denom)?.let { asset ->
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

    fun vestingValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = vestingAmount(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    fun vestingValueSum(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosVestings.forEach { vesting ->
            sum = sum.add(vestingValue(vesting.denom, isUsd))
        }
        return sum
    }

    open fun delegationAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        val delegationListCopy = cosmosDelegations.toList()
        for (delegation in delegationListCopy) {
            delegation.balance?.let {
                sum = sum.add(delegation.balance?.amount?.toBigDecimal())
            } ?: run {
                sum = sum.add(BigDecimal.ZERO)
            }
        }
        return sum
    }

    fun delegationValueSum(isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = delegationAmountSum()
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    open fun unbondingAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosUnbondings.forEach { unbonding ->
            unbonding.entriesList.forEach { entry ->
                sum = sum.add(entry.balance.toBigDecimal())
            }
        }
        return sum
    }

    fun unbondingValueSum(isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = unbondingAmountSum()
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
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
        return sum.movePointLeft(18).setScale(0, RoundingMode.DOWN)
    }

    fun rewardValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = rewardAmountSum(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    fun rewardValueSum(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        rewardAllCoins().forEach { rewardCoin ->
            BaseData.getAsset(chain.apiName, rewardCoin.denom)?.let { asset ->
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

    fun rewardAllCoins(): List<CoinProto.Coin> {
        val result: MutableList<CoinProto.Coin> = mutableListOf()
        cosmosRewards.forEach { reward ->
            reward.rewardList.forEach { coin ->
                val calAmount =
                    coin.amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.DOWN)
                if (calAmount > BigDecimal.ZERO) {
                    result.add(
                        CoinProto.Coin.newBuilder().setDenom(coin.denom)
                            .setAmount(calAmount.toPlainString()).build()
                    )
                }
            }
        }
        return result
    }

    fun rewardOtherDenoms(): Int {
        return rewardAllCoins().map { it.denom }.distinct().count { it != chain.stakeDenom }
    }

    fun claimableRewards(): MutableList<DistributionProto.DelegationDelegatorReward?> {
        val result = mutableListOf<DistributionProto.DelegationDelegatorReward?>()
        cosmosRewards.forEach { reward ->
            run loop@{
                for (i in 0 until reward.rewardCount) {
                    val rewardAmount = reward.getReward(i).amount.toBigDecimal().movePointLeft(18)
                        .setScale(0, RoundingMode.DOWN)
                    BaseData.getAsset(chain.apiName, reward.rewardList[i].denom)?.let { asset ->
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

    fun valueAbleRewards(): MutableList<DistributionProto.DelegationDelegatorReward?> {
        val result: MutableList<DistributionProto.DelegationDelegatorReward?> = mutableListOf()
        cosmosRewards.forEach { reward ->
            var eachRewardValue = BigDecimal.ZERO
            for (i in 0 until reward.rewardCount) {
                val rewardAmount = reward.getReward(i).amount.toBigDecimal().movePointLeft(18)
                    .setScale(0, RoundingMode.DOWN)
                BaseData.getAsset(chain.apiName, reward.getReward(i).denom)?.let { asset ->
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

    fun compoundAbleRewards(): MutableList<DistributionProto.DelegationDelegatorReward?> {
        val result: MutableList<DistributionProto.DelegationDelegatorReward?> = mutableListOf()
        cosmosRewards.forEach { reward ->
            reward.rewardList.firstOrNull { it.denom == chain.stakeDenom }?.amount?.let { amount ->
                val rewardAmount =
                    amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.DOWN)
                BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                    val price = BaseData.getPrice(asset.coinGeckoId, true)
                    val value = price.multiply(rewardAmount).movePointLeft(asset.decimals ?: 6)
                        .setScale(6, RoundingMode.DOWN)
                    if (value >= BigDecimal("0.1")) {
                        result.add(reward)
                    }
                }
            }
        }
        return result
    }

    open fun allStakingDenomAmount(): BigDecimal? {
        return balanceAmount(chain.stakeDenom).add(vestingAmount(chain.stakeDenom))
            ?.add(delegationAmountSum())?.add(unbondingAmountSum())
            ?.add(rewardAmountSum(chain.stakeDenom))
    }

    fun getLcd(): String {
        var url: String
        val endpoint = Prefs.getLcdEndpoint(chain)
        url = if (endpoint != "") {
            endpoint
        } else {
            chain.lcdUrl
        }
        if (!url.endsWith("/")) {
            url += "/"
        }
        return url
    }

    fun getGrpc(): Pair<String, Int> {
        val endPoint = Prefs.getGrpcEndpoint(chain)
        if (endPoint.isNotEmpty() && endPoint.split(":").count() == 2) {
            val host = endPoint.split(":")[0].trim()
            val port = endPoint.split(":").getOrNull(1)?.trim()?.toIntOrNull() ?: 443
            return Pair(host, port)
        }
        return Pair(chain.grpcHost, chain.grpcPort)
    }

    fun getChannel(): ManagedChannel? {
        return if (getGrpc().first.isEmpty()) {
            null
        } else {
            ManagedChannelBuilder.forAddress(
                getGrpc().first, getGrpc().second
            ).useTransportSecurity().build()
        }
    }

    suspend fun lastHeight(): Long {
        return if (endPointType(chain) == CosmosEndPointType.USE_GRPC) {
            val blockStub =
                ServiceGrpc.newBlockingStub(getChannel()).withDeadlineAfter(30L, TimeUnit.SECONDS)
            val blockRequest = QueryProto.GetLatestBlockRequest.newBuilder().build()
            val lastBlock = blockStub.getLatestBlock(blockRequest)
            lastBlock.block.header.height
        } else {
            RetrofitInstance.lcdApi(chain).lcdNewLastHeightInfo().lastHeight()
        }
    }

    suspend fun ibcClient(assetPath: AssetPath?): Long {
        return if (endPointType(chain) == CosmosEndPointType.USE_GRPC) {
            val ibcClientStub =
                QueryGrpc.newBlockingStub(getChannel()).withDeadlineAfter(8L, TimeUnit.SECONDS)
            val ibcClientRequest =
                QueryChannelClientStateRequest.newBuilder().setChannelId(assetPath?.channel)
                    .setPortId(assetPath?.port).build()
            val ibcClientResponse = ibcClientStub.channelClientState(ibcClientRequest)
            val lastHeight =
                TendermintProto.ClientState.parseFrom(ibcClientResponse.identifiedClientState.clientState.value).latestHeight
            return lastHeight.revisionNumber

        } else {
            RetrofitInstance.lcdApi(chain).lcdIbcClientInfo(assetPath?.channel, assetPath?.port)
                .revisionNumber()
        }
    }

    fun endPointType(chain: BaseChain): CosmosEndPointType? {
        return Prefs.getEndpointType(chain)
    }
}

fun JsonObject.balance(): MutableList<CoinProto.Coin> {
    val result = mutableListOf<CoinProto.Coin>()
    this["balances"].asJsonArray.forEach { balance ->
        val coin = CoinProto.Coin.newBuilder().setDenom(balance.asJsonObject["denom"].asString)
            .setAmount(balance.asJsonObject["amount"].asString).build()
        result.add(coin)
    }
    return result
}

fun JsonObject.delegations(): MutableList<DelegationResponse> {
    val result = mutableListOf<DelegationResponse>()
    for (i in 0 until this["delegation_responses"].asJsonArray.size()) {
        val delegation = this["delegation_responses"].asJsonArray[i]
        val staking = StakingProto.Delegation.newBuilder()
            .setDelegatorAddress(delegation.asJsonObject["delegation"].asJsonObject["delegator_address"].asString)
            .setValidatorAddress(delegation.asJsonObject["delegation"].asJsonObject["validator_address"].asString)
            .setShares(delegation.asJsonObject["delegation"].asJsonObject["shares"].asString)
            .build()

        val balance = CoinProto.Coin.newBuilder()
            .setDenom(delegation.asJsonObject["balance"].asJsonObject["denom"].asString)
            .setAmount(delegation.asJsonObject["balance"].asJsonObject["amount"].asString).build()

        val delegationResponse =
            DelegationResponse.newBuilder().setDelegation(staking).setBalance(balance).build()
        result.add(delegationResponse)
    }
    return result
}

fun JsonObject.unDelegations(): MutableList<UnbondingDelegation> {
    val result = mutableListOf<UnbondingDelegation>()
    for (i in 0 until this["unbonding_responses"].asJsonArray.size()) {
        val unBonding = this["unbonding_responses"].asJsonArray[i]
        val entries = mutableListOf<StakingProto.UnbondingDelegationEntry>()
        for (j in 0 until unBonding.asJsonObject["entries"].asJsonArray.size()) {
            val entry = unBonding.asJsonObject["entries"].asJsonArray[j]
            val height = entry.asJsonObject["creation_height"].asString.toLong()
            entry.asJsonObject["completion_time"].asString?.let { date ->
                val dpTime = dateToLong("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSX", date)
                val time = Timestamp.newBuilder().setSeconds(dpTime / 1000).build()
                val tempEntry =
                    StakingProto.UnbondingDelegationEntry.newBuilder().setCreationHeight(height)
                        .setBalance(entry.asJsonObject["balance"].asString).setCompletionTime(time)
                        .build()
                entries.add(tempEntry)
            }
        }
        val unBondingResponse = UnbondingDelegation.newBuilder()
            .setDelegatorAddress(unBonding.asJsonObject["delegator_address"].asString)
            .setValidatorAddress(unBonding.asJsonObject["validator_address"].asString)
            .addAllEntries(entries).build()
        result.add(unBondingResponse)
    }
    return result
}

fun JsonObject.rewards(): MutableList<DistributionProto.DelegationDelegatorReward> {
    val result = mutableListOf<DistributionProto.DelegationDelegatorReward>()
    for (i in 0 until this["rewards"].asJsonArray.size()) {
        val reward = this["rewards"].asJsonArray[i]
        val coins = mutableListOf<CoinProto.DecCoin>()
        reward.asJsonObject["reward"].asJsonArray.forEach { rewardCoin ->
            val balance =
                CoinProto.DecCoin.newBuilder().setDenom(rewardCoin.asJsonObject["denom"].asString)
                    .setAmount(
                        rewardCoin.asJsonObject["amount"].asString.toBigDecimal().movePointRight(18)
                            .setScale(0, RoundingMode.DOWN).toString()
                    ).build()
            coins.add(balance)
        }
        val rewardResponse = DistributionProto.DelegationDelegatorReward.newBuilder()
            .setValidatorAddress(reward.asJsonObject["validator_address"].asString)
            .addAllReward(coins).build()
        result.add(rewardResponse)
    }
    return result
}

fun JsonObject.rewardAddress(): String {
    return this.asJsonObject["withdraw_address"].asString
}

fun JsonObject.feeMarket(): MutableList<CoinProto.DecCoin> {
    val result = mutableListOf<CoinProto.DecCoin>()
    this["prices"].asJsonArray.forEach { coin ->
        val feeMakretCoin =
            CoinProto.DecCoin.newBuilder().setDenom(coin.asJsonObject["denom"].asString).setAmount(
                coin.asJsonObject["amount"].asString.toBigDecimal().movePointRight(18)
                    .setScale(18, RoundingMode.DOWN).toString()
            ).build()
        result.add(feeMakretCoin)
    }
    return result
}

fun JsonObject.validators(status: StakingProto.BondStatus): MutableList<StakingProto.Validator> {
    val result = mutableListOf<StakingProto.Validator>()
    this["validators"].asJsonArray.forEach { validator ->
        val description = StakingProto.Description.newBuilder()
            .setMoniker(validator.asJsonObject["description"].asJsonObject["moniker"].asString)
            .setIdentity(validator.asJsonObject["description"].asJsonObject["identity"].asString)
            .setWebsite(validator.asJsonObject["description"].asJsonObject["website"].asString)
            .setSecurityContact(validator.asJsonObject["description"].asJsonObject["security_contact"].asString)
            .setDetails(validator.asJsonObject["description"].asJsonObject["details"].asString)
            .build()

        val commissionRate = StakingProto.CommissionRates.newBuilder().setRate(
            validator.asJsonObject["commission"].asJsonObject["commission_rates"].asJsonObject["rate"].asString.toBigDecimal()
                .movePointRight(18).setScale(0, RoundingMode.DOWN).toString()
        ).setMaxRate(
            validator.asJsonObject["commission"].asJsonObject["commission_rates"].asJsonObject["max_rate"].asString.toBigDecimal()
                .movePointRight(18).setScale(0, RoundingMode.DOWN).toString()
        ).setMaxChangeRate(
            validator.asJsonObject["commission"].asJsonObject["commission_rates"].asJsonObject["max_change_rate"].asString.toBigDecimal()
                .movePointRight(18).setScale(0, RoundingMode.DOWN).toString()
        )
        val commission =
            StakingProto.Commission.newBuilder().setCommissionRates(commissionRate).build()

        val validatorResponse = StakingProto.Validator.newBuilder()
            .setOperatorAddress(validator.asJsonObject["operator_address"].asString)
            .setJailed(validator.asJsonObject["jailed"].asBoolean)
            .setTokens(validator.asJsonObject["tokens"].asString).setStatus(status)
            .setDescription(description).setCommission(commission).build()
        result.add(validatorResponse)
    }
    return result
}

fun JsonObject.lastHeight(): Long {
    return this["block"].asJsonObject["header"].asJsonObject["height"].asString.toLong()
}

fun JsonObject.revisionNumber(): Long {
    return this["identified_client_state"].asJsonObject["client_state"].asJsonObject["latest_height"].asJsonObject["revision_number"].asString.toLong()
}

fun JsonObject.accountNumber(): Long {
    return getAsJsonPrimitive("account_number")?.asString?.toLongOrNull()
        ?: getAsJsonObject("base_vesting_account")?.getAsJsonObject("base_account")
            ?.getAsJsonPrimitive("account_number")?.asString?.toLongOrNull()
        ?: getAsJsonObject("base_account")?.getAsJsonPrimitive("account_number")?.asString?.toLongOrNull()
        ?: getAsJsonObject("account")?.getAsJsonObject("base_vesting_account")
            ?.getAsJsonObject("base_account")
            ?.getAsJsonPrimitive("account_number")?.asString?.toLongOrNull()
        ?: getAsJsonObject("account")?.getAsJsonObject("base_account")
            ?.getAsJsonPrimitive("account_number")?.asString?.toLongOrNull()
        ?: getAsJsonObject("account")?.getAsJsonPrimitive("account_number")?.asString?.toLongOrNull()
        ?: getAsJsonObject("account")?.getAsJsonObject("account_number")
            ?.getAsJsonPrimitive("account_number")?.asString?.toLongOrNull() ?: 0L
}

fun JsonObject.sequence(): Long {
    return getAsJsonPrimitive("sequence")?.asString?.toLongOrNull()
        ?: getAsJsonObject("base_vesting_account")?.getAsJsonObject("base_account")
            ?.getAsJsonPrimitive("sequence")?.asString?.toLongOrNull()
        ?: getAsJsonObject("base_account")?.getAsJsonPrimitive("sequence")?.asString?.toLongOrNull()
        ?: getAsJsonObject("account")?.getAsJsonObject("base_vesting_account")
            ?.getAsJsonObject("base_account")
            ?.getAsJsonPrimitive("sequence")?.asString?.toLongOrNull()
        ?: getAsJsonObject("account")?.getAsJsonObject("base_account")
            ?.getAsJsonPrimitive("sequence")?.asString?.toLongOrNull()
        ?: getAsJsonObject("account")?.getAsJsonPrimitive("sequence")?.asString?.toLongOrNull()
        ?: getAsJsonObject("account")?.getAsJsonObject("account_number")
            ?.getAsJsonPrimitive("sequence")?.asString?.toLongOrNull() ?: 0L
}

fun Any.accountInfos(): Triple<String, Long, Long> {
    var rawAccount = this
    if (rawAccount.typeUrl?.contains(ModelsProfileProto.Profile.getDescriptor().fullName) == true) {
        rawAccount = ModelsProfileProto.Profile.parseFrom(value).account
    }

    if (rawAccount.typeUrl.contains(AuthProto.BaseAccount.getDescriptor().fullName)) {
        AuthProto.BaseAccount.parseFrom(rawAccount.value)?.let { account ->
            return Triple(account.address, account.accountNumber, account.sequence)
        }
    } else if (rawAccount.typeUrl.contains(VestingProto.PeriodicVestingAccount.getDescriptor().fullName)) {
        VestingProto.PeriodicVestingAccount.parseFrom(rawAccount.value).baseVestingAccount.baseAccount?.let { account ->
            return Triple(account.address, account.accountNumber, account.sequence)
        }

    } else if (rawAccount.typeUrl.contains(VestingProto.ContinuousVestingAccount.getDescriptor().fullName)) {
        VestingProto.ContinuousVestingAccount.parseFrom(rawAccount.value).baseVestingAccount.baseAccount?.let { account ->
            return Triple(account.address, account.accountNumber, account.sequence)
        }

    } else if (rawAccount.typeUrl.contains(VestingProto.DelayedVestingAccount.getDescriptor().fullName)) {
        VestingProto.DelayedVestingAccount.parseFrom(rawAccount.value).baseVestingAccount.baseAccount?.let { account ->
            return Triple(account.address, account.accountNumber, account.sequence)
        }

    } else if (rawAccount.typeUrl.contains(com.stride.vesting.VestingProto.StridePeriodicVestingAccount.getDescriptor().fullName)) {
        com.stride.vesting.VestingProto.StridePeriodicVestingAccount.parseFrom(rawAccount.value).baseVestingAccount.baseAccount?.let { account ->
            return Triple(account.address, account.accountNumber, account.sequence)
        }

    } else if (rawAccount.typeUrl.contains(com.injective.types.v1beta1.AccountProto.EthAccount.getDescriptor().fullName)) {
        com.injective.types.v1beta1.AccountProto.EthAccount.parseFrom(rawAccount.value).baseAccount?.let { account ->
            return Triple(account.address, account.accountNumber, account.sequence)
        }

    } else if (rawAccount.typeUrl.contains(com.artela.types.v1.AccountProto.EthAccount.getDescriptor().fullName)) {
        com.artela.types.v1.AccountProto.EthAccount.parseFrom(rawAccount.value).baseAccount?.let { account ->
            return Triple(account.address, account.accountNumber, account.sequence)
        }

    } else if (rawAccount.typeUrl.contains(AccountProto.EthAccount.getDescriptor().fullName)) {
        AccountProto.EthAccount.parseFrom(rawAccount.value).baseAccount?.let { account ->
            return Triple(account.address, account.accountNumber, account.sequence)
        }

    } else if (rawAccount.typeUrl.contains(com.eth.types.v1.AccountProto.EthAccount.getDescriptor().fullName)) {
        com.eth.types.v1.AccountProto.EthAccount.parseFrom(rawAccount.value).baseAccount?.let { account ->
            return Triple(account.address, account.accountNumber, account.sequence)
        }

    } else {
        return Triple("", -1, -1)
    }
    return Triple("", -1, -1)
}