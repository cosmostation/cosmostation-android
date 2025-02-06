package wannabit.io.cosmostaion.chain.fetcher

import com.cosmos.base.v1beta1.CoinProto
import com.google.gson.JsonObject
import com.zrchain.validation.HybridValidationProto.ValidatorHV
import com.zrchain.validation.StakingProto.BondStatus
import com.zrchain.validation.StakingProto.Commission
import com.zrchain.validation.StakingProto.CommissionRates
import com.zrchain.validation.StakingProto.DelegationResponse
import com.zrchain.validation.StakingProto.Description
import com.zrchain.validation.StakingProto.UnbondingDelegation
import com.zrchain.validation.StakingProto.UnbondingDelegationEntry
import com.zrchain.validation.StakingProto.ValAddresses
import wannabit.io.cosmostaion.chain.BaseChain
import java.math.BigDecimal
import java.math.RoundingMode

class ZenrockFetcher(private val chain: BaseChain) : CosmosFetcher(chain) {

    var zenrockDelegations = mutableListOf<DelegationResponse>()
    var zenrockUnbondings = mutableListOf<UnbondingDelegation>()
    var zenrockValidators = mutableListOf<ValidatorHV>()
    var zenrockOriginValidators = mutableListOf<ValidatorHV>()

    override fun delegationAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        val delegationListCopy = zenrockDelegations.toList()
        for (delegation in delegationListCopy) {
            delegation.balance?.let {
                sum = sum.add(delegation.balance?.amount?.toBigDecimal())
            } ?: run {
                sum = sum.add(BigDecimal.ZERO)
            }
        }
        return sum
    }

    override fun unbondingAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        zenrockUnbondings.forEach { unbonding ->
            unbonding.entriesList.forEach { entry ->
                sum = sum.add(entry.balance.toBigDecimal())
            }
        }
        return sum
    }
}

fun JsonObject.validators(status: BondStatus): MutableList<ValidatorHV> {
    val result = mutableListOf<ValidatorHV>()
    this["validators"].asJsonArray.forEach { validator ->
        val description = Description.newBuilder()
            .setMoniker(validator.asJsonObject["description"].asJsonObject["moniker"].asString)
            .setIdentity(validator.asJsonObject["description"].asJsonObject["identity"].asString)
            .setWebsite(validator.asJsonObject["description"].asJsonObject["website"].asString)
            .setSecurityContact(validator.asJsonObject["description"].asJsonObject["security_contact"].asString)
            .setDetails(validator.asJsonObject["description"].asJsonObject["details"].asString)
            .build()

        val commissionRate = CommissionRates.newBuilder().setRate(
            validator.asJsonObject["commission"].asJsonObject["commission_rates"].asJsonObject["rate"].asString.toBigDecimal()
                .movePointRight(18).setScale(0, RoundingMode.DOWN).toString()
        ).setMaxRate(
            validator.asJsonObject["commission"].asJsonObject["commission_rates"].asJsonObject["max_rate"].asString.toBigDecimal()
                .movePointRight(18).setScale(0, RoundingMode.DOWN).toString()
        ).setMaxChangeRate(
            validator.asJsonObject["commission"].asJsonObject["commission_rates"].asJsonObject["max_change_rate"].asString.toBigDecimal()
                .movePointRight(18).setScale(0, RoundingMode.DOWN).toString()
        )
        val commission = Commission.newBuilder().setCommissionRates(commissionRate).build()
        val validatorResponse = ValidatorHV.newBuilder()
            .setOperatorAddress(validator.asJsonObject["operator_address"].asString)
            .setJailed(validator.asJsonObject["jailed"].asBoolean).setStatus(status)
            .setDescription(description).setCommission(commission)
            .setTokensNative(validator.asJsonObject["tokensNative"].asString)
            .setTokensAVS(validator.asJsonObject["tokensAVS"].asString).build()
        result.add(validatorResponse)
    }
    return result
}

fun JsonObject.zenrockDelegations(): MutableList<DelegationResponse> {
    val result = mutableListOf<DelegationResponse>()
    for (i in 0 until this["delegation_responses"].asJsonArray.size()) {
//        val delegationResponse = this["delegation_responses"].asJsonArray[i]
//        val delegation = Delegation.newBuilder()
//        val balances: MutableList<CoinProto.Coin> = mutableListOf()
//
//        delegation.setDelegatorAddress(delegationResponse.asJsonObject["delegation"].asJsonObject["delegator_address"].asString)
//        delegation.setValidatorAddress(delegationResponse.asJsonObject["delegation"].asJsonObject["validator_address"].asString)
//        delegationResponse.asJsonObject["delegation"].asJsonObject["shares"].asJsonArray.forEach { share ->
//            val shareCoin =
//                CoinProto.DecCoin.newBuilder().setDenom(share.asJsonObject["denom"].asString)
//                    .setAmount(share.asJsonObject["amount"].asString).build()
//            delegation.addShares(shareCoin)
//        }
//
//        delegationResponse.asJsonObject["balance"].asJsonArray.forEach { balance ->
//            val coin = CoinProto.Coin.newBuilder().setDenom(balance.asJsonObject["denom"].asString)
//                .setAmount(balance.asJsonObject["amount"].asString).build()
//            balances.add(coin)
//        }
//
//        val tempDelegationResponse =
//            DelegationResponse.newBuilder().setDelegation(delegation).addAllBalance(balances)
//                .build()
//        result.add(tempDelegationResponse)
    }
    return result
}

fun JsonObject.zenrockUnDelegations(): MutableList<UnbondingDelegation> {
    val result = mutableListOf<UnbondingDelegation>()
    for (i in 0 until this["unbonding_responses"].asJsonArray.size()) {
        val unBonding = this["unbonding_responses"].asJsonArray[i]
        val entries = mutableListOf<UnbondingDelegationEntry>()
        for (j in 0 until unBonding.asJsonObject["entries"].asJsonArray.size()) {
//            val entry = unBonding.asJsonObject["entries"].asJsonArray[j]
//            val height = entry.asJsonObject["creation_height"].asString.toLong()
//            entry.asJsonObject["completion_time"].asString?.let { date ->
//                val balances: MutableList<CoinProto.Coin> = mutableListOf()
//                val dpTime = dateToLong("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSX", date)
//                val time = Timestamp.newBuilder().setSeconds(dpTime / 1000).build()
//                entry.asJsonObject["balance"].asJsonArray.forEach { balance ->
//                    val coin =
//                        CoinProto.Coin.newBuilder().setDenom(balance.asJsonObject["denom"].asString)
//                            .setAmount(balance.asJsonObject["amount"].asString).build()
//                    balances.add(coin)
//                }
//                val tempEntry = UnbondingDelegationEntry.newBuilder().setCreationHeight(height)
//                    .addAllBalance(balances).setCompletionTime(time).build()
//                entries.add(tempEntry)
//            }
        }
//        val unBondingResponse = UnbondingDelegation.newBuilder()
//            .setDelegatorAddress(unBonding.asJsonObject["delegator_address"].asString)
//            .setValidatorAddress(unBonding.asJsonObject["validator_address"].asString)
//            .addAllEntries(entries).build()
//        result.add(unBondingResponse)
    }
    return result
}

fun JsonObject.zenrockValidators(status: BondStatus): MutableList<ValAddresses> {
    val result = mutableListOf<ValAddresses>()
    this["validators"].asJsonArray.forEach { validator ->
        val description = Description.newBuilder()
            .setMoniker(validator.asJsonObject["description"].asJsonObject["moniker"].asString)
            .setIdentity(validator.asJsonObject["description"].asJsonObject["identity"].asString)
            .setWebsite(validator.asJsonObject["description"].asJsonObject["website"].asString)
            .setSecurityContact(validator.asJsonObject["description"].asJsonObject["security_contact"].asString)
            .setDetails(validator.asJsonObject["description"].asJsonObject["details"].asString)
            .build()

        val commissionRate = CommissionRates.newBuilder().setRate(
            validator.asJsonObject["commission"].asJsonObject["commission_rates"].asJsonObject["rate"].asString.toBigDecimal()
                .movePointRight(18).setScale(0, RoundingMode.DOWN).toString()
        ).setMaxRate(
            validator.asJsonObject["commission"].asJsonObject["commission_rates"].asJsonObject["max_rate"].asString.toBigDecimal()
                .movePointRight(18).setScale(0, RoundingMode.DOWN).toString()
        ).setMaxChangeRate(
            validator.asJsonObject["commission"].asJsonObject["commission_rates"].asJsonObject["max_change_rate"].asString.toBigDecimal()
                .movePointRight(18).setScale(0, RoundingMode.DOWN).toString()
        )
        val commission = Commission.newBuilder().setCommissionRates(commissionRate).build()

        val tokens: MutableList<CoinProto.Coin> = mutableListOf()
        validator.asJsonObject["tokens"].asJsonArray.forEach { token ->
            val coin = CoinProto.Coin.newBuilder().setDenom(token.asJsonObject["denom"].asString)
                .setAmount(token.asJsonObject["amount"].asString).build()
            tokens.add(coin)
        }

//        val validatorResponse = Validator.newBuilder()
//            .setOperatorAddress(validator.asJsonObject["operator_address"].asString)
//            .setJailed(validator.asJsonObject["jailed"].asBoolean)
//            .addAllTokens(tokens).setStatus(status)
//            .setDescription(description).setCommission(commission).build()
//        result.add(validatorResponse)
    }
    return result
}