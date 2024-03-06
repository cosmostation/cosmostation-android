package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.res.VestingData
import java.math.BigDecimal
import java.math.RoundingMode

class ChainNeutron : CosmosLine() {

    var neutronDeposited: BigDecimal = BigDecimal.ZERO
    var neutronVesting: VestingData? = null

    override var name: String = "Neutron"
    override var tag: String = "neutron118"
    override var logo: Int = R.drawable.chain_neutron
    override var swipeLogo: Int = R.drawable.chain_swipe_neutron
    override var apiName: String = "neutron"
    override var stakeDenom: String? = "untrn"
    override var supportStaking = false

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true),
        ChildNumber(118, true),
        ChildNumber.ZERO_HARDENED,
        ChildNumber.ZERO
    )
    override var accountPrefix: String? = "neutron"

    override var grpcHost: String = "grpc-neutron.cosmostation.io"

    override fun denomValue(denom: String, isUsd: Boolean?): BigDecimal {
        return if (denom == stakeDenom) {
            balanceValue(denom, isUsd).add(neutronVestingValue(isUsd)).add(neutronDepositedValue(isUsd))
        } else {
            balanceValue(denom, isUsd)
        }
    }

    override fun allStakingDenomAmount(): BigDecimal {
        stakeDenom?.let { denom ->
            return balanceAmount(denom).add(neutronVestingAmount()).add(neutronDeposited)
        }
        return BigDecimal.ZERO
    }

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return balanceValueSum(isUsd).add(neutronVestingValue(isUsd)).add(neutronDepositedValue(isUsd))
    }

    fun neutronVestingAmount(): BigDecimal? {
        neutronVesting?.let {
            val allocatedAmount = it.allocated_amount?.toBigDecimal()
            val withdrawnAmount = it.withdrawn_amount?.toBigDecimal()
            return allocatedAmount?.subtract(withdrawnAmount)
        }
        return BigDecimal.ZERO
    }

    private fun neutronVestingValue(isUsd: Boolean? = false): BigDecimal {
        stakeDenom?.let { denom ->
            BaseData.getAsset(apiName, denom)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
                val amount = neutronVestingAmount()
                return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                    .setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    private fun neutronDepositedValue(isUsd: Boolean? = false): BigDecimal {
        stakeDenom?.let { denom ->
            BaseData.getAsset(apiName, denom)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
                val amount = neutronDeposited
                return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
            }
        }
        return BigDecimal.ZERO
    }
}

const val NEUTRON_VAULT_ADDRESS =
    "neutron1qeyjez6a9dwlghf9d6cy44fxmsajztw257586akk6xn6k88x0gus5djz4e"
const val NEUTRON_VESTING_CONTRACT_ADDRESS =
    "neutron1h6828as2z5av0xqtlh4w9m75wxewapk8z9l2flvzc29zeyzhx6fqgp648z"

const val NEUTRON_SINGLE_MODULE = 1
const val NEUTRON_MULTI_MODULE = 2
const val NEUTRON_OVERRULE_MODULE = 3