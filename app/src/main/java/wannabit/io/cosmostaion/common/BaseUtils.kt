package wannabit.io.cosmostaion.common

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.vesting.v1beta1.VestingProto
import com.stride.vesting.VestingProto.StridePeriodicVestingAccount
import org.bitcoinj.core.Address
import org.bitcoinj.params.MainNetParams
import org.bitcoinj.params.TestNet3Params
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Calendar
import java.util.Locale
import java.util.regex.Pattern

object BaseUtils {

    fun onParseVesting(chain: BaseChain) {
        if (chain.cosmosFetcher?.endPointType(chain) == CosmosEndPointType.USE_GRPC) {
            onParseVestingAccount(chain)
        } else {
            onParseLcdVestingAccount(chain)
        }
    }

    private fun onParseVestingAccount(chain: BaseChain) {
        chain.cosmosFetcher?.cosmosVestings?.clear()
        val authInfo = chain.cosmosFetcher?.cosmosAuth
        authInfo?.let { auth ->
            chain.cosmosFetcher?.cosmosBalances?.let { cosmosBalances ->
                if (auth.typeUrl.contains(VestingProto.PeriodicVestingAccount.getDescriptor().fullName)) {
                    val vestingAccount = VestingProto.PeriodicVestingAccount.parseFrom(auth.value)

                    cosmosBalances.forEach { coin ->
                        val denom = coin.denom
                        var dpBalance = BigDecimal.ZERO
                        var dpVesting = BigDecimal.ZERO
                        var originalVesting = BigDecimal.ZERO
                        var remainVesting = BigDecimal.ZERO
                        var delegatedVesting = BigDecimal.ZERO

                        dpBalance = coin.amount.toBigDecimal()

                        vestingAccount.baseVestingAccount.originalVestingList.forEach { vesting ->
                            if (vesting.denom == denom) {
                                originalVesting = originalVesting.add(vesting.amount.toBigDecimal())
                            }
                        }

                        vestingAccount.baseVestingAccount.delegatedVestingList.forEach { vesting ->
                            if (vesting.denom == denom) {
                                delegatedVesting =
                                    delegatedVesting.add(vesting.amount.toBigDecimal())
                            }
                        }

                        remainVesting =
                            onParsePeriodicRemainVestingAmountByDenom(vestingAccount, denom)

                        dpVesting = remainVesting.subtract(delegatedVesting)

                        dpVesting = if (dpVesting <= BigDecimal.ZERO) BigDecimal.ZERO else dpVesting

                        if (remainVesting > delegatedVesting) {
                            dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting)
                        }

                        if (dpVesting > BigDecimal.ZERO) {
                            val vestingCoin = CoinProto.Coin.newBuilder().setDenom(denom)
                                .setAmount(dpVesting.toPlainString()).build()
                            chain.cosmosFetcher?.cosmosVestings?.add(vestingCoin)
                            var replace = -1
                            for (i in 0 until cosmosBalances.size) {
                                if (cosmosBalances[i].denom == denom) {
                                    replace = i
                                }
                            }
                            if (replace >= 0) {
                                val tempBalances = cosmosBalances.toMutableList()
                                tempBalances[replace] = CoinProto.Coin.newBuilder().setDenom(denom)
                                    .setAmount(dpBalance.toPlainString()).build()
                                chain.cosmosFetcher?.cosmosBalances = tempBalances
                            }
                        }
                    }

                } else if (auth.typeUrl.contains(VestingProto.ContinuousVestingAccount.getDescriptor().fullName)) {
                    val vestingAccount = VestingProto.ContinuousVestingAccount.parseFrom(auth.value)

                    cosmosBalances.forEach { coin ->
                        val denom = coin.denom
                        var dpBalance = BigDecimal.ZERO
                        var dpVesting = BigDecimal.ZERO
                        var originalVesting = BigDecimal.ZERO
                        var remainVesting = BigDecimal.ZERO
                        var delegatedVesting = BigDecimal.ZERO

                        dpBalance = coin.amount.toBigDecimal()

                        vestingAccount.baseVestingAccount.originalVestingList.forEach { vesting ->
                            if (vesting.denom == denom) {
                                originalVesting = originalVesting.add(vesting.amount.toBigDecimal())
                            }
                        }

                        vestingAccount.baseVestingAccount.delegatedVestingList.forEach { vesting ->
                            if (vesting.denom == denom) {
                                delegatedVesting =
                                    delegatedVesting.add(vesting.amount.toBigDecimal())
                            }
                        }

                        val cTime = Calendar.getInstance().time.time
                        val vestingStart = vestingAccount.startTime * 1000
                        val vestingEnd = vestingAccount.baseVestingAccount.endTime * 1000
                        remainVesting = if (cTime < vestingStart) {
                            originalVesting
                        } else if (cTime > vestingEnd) {
                            BigDecimal.ZERO
                        } else {
                            val progress =
                                (cTime - vestingStart).toFloat() / (vestingEnd - vestingStart).toFloat()
                            originalVesting.multiply(BigDecimal((1 - progress).toDouble()))
                                .setScale(0, RoundingMode.UP)
                        }

                        dpVesting = remainVesting.subtract(delegatedVesting)

                        dpVesting = if (dpVesting <= BigDecimal.ZERO) BigDecimal.ZERO else dpVesting

                        if (remainVesting > BigDecimal.ZERO) {
                            dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting)
                        }

                        if (dpVesting > BigDecimal.ZERO) {
                            val vestingCoin = CoinProto.Coin.newBuilder().setDenom(denom)
                                .setAmount(dpVesting.toPlainString()).build()
                            chain.cosmosFetcher?.cosmosVestings?.add(vestingCoin)
                            var replace = -1
                            for (i in 0 until cosmosBalances.size) {
                                if (cosmosBalances[i].denom == denom) {
                                    replace = i
                                }
                            }
                            if (replace >= 0) {
                                val tempBalances = cosmosBalances.toMutableList()
                                tempBalances[replace] = CoinProto.Coin.newBuilder().setDenom(denom)
                                    .setAmount(dpBalance.toPlainString()).build()
                                chain.cosmosFetcher?.cosmosBalances = tempBalances
                            }
                        }
                    }

                } else if (auth.typeUrl.contains(VestingProto.DelayedVestingAccount.getDescriptor().fullName)) {
                    val vestingAccount = VestingProto.DelayedVestingAccount.parseFrom(auth.value)

                    cosmosBalances.forEach { coin ->
                        val denom = coin.denom
                        var dpBalance = BigDecimal.ZERO
                        var dpVesting = BigDecimal.ZERO
                        var originalVesting = BigDecimal.ZERO
                        var remainVesting = BigDecimal.ZERO
                        var delegatedVesting = BigDecimal.ZERO

                        dpBalance = coin.amount.toBigDecimal()

                        vestingAccount.baseVestingAccount.originalVestingList.forEach { vesting ->
                            if (vesting.denom == denom) {
                                originalVesting = originalVesting.add(vesting.amount.toBigDecimal())
                            }
                        }

                        val cTime = Calendar.getInstance().time.time
                        val vestingEnd = vestingAccount.baseVestingAccount.endTime * 1000
                        if (cTime < vestingEnd) {
                            remainVesting = originalVesting
                        }

                        vestingAccount.baseVestingAccount.delegatedVestingList.forEach { vesting ->
                            if (coin.denom == denom) {
                                delegatedVesting =
                                    delegatedVesting.add(vesting.amount.toBigDecimal())
                            }
                        }

                        dpVesting = remainVesting.subtract(delegatedVesting)

                        dpVesting = if (dpVesting <= BigDecimal.ZERO) BigDecimal.ZERO else dpVesting

                        if (remainVesting > BigDecimal.ZERO) {
                            dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting)
                        }

                        if (dpVesting > BigDecimal.ZERO) {
                            val vestingCoin = CoinProto.Coin.newBuilder().setDenom(denom)
                                .setAmount(dpVesting.toPlainString()).build()
                            chain.cosmosFetcher?.cosmosVestings?.add(vestingCoin)
                            var replace = -1
                            for (i in 0 until cosmosBalances.size) {
                                if (cosmosBalances[i].denom == denom) {
                                    replace = i
                                }
                            }
                            if (replace >= 0) {
                                val tempBalances = cosmosBalances.toMutableList()
                                tempBalances[replace] = CoinProto.Coin.newBuilder().setDenom(denom)
                                    .setAmount(dpBalance.toPlainString()).build()
                                chain.cosmosFetcher?.cosmosBalances = tempBalances
                            }
                        }
                    }

                } else if (auth.typeUrl.contains(StridePeriodicVestingAccount.getDescriptor().fullName)) {
                    val vestingAccount = StridePeriodicVestingAccount.parseFrom(auth.value)

                    cosmosBalances.forEach { coin ->
                        val denom = coin.denom
                        var dpBalance = BigDecimal.ZERO
                        var dpVesting = BigDecimal.ZERO
                        var originalVesting = BigDecimal.ZERO
                        var remainVesting = BigDecimal.ZERO
                        var delegatedVesting = BigDecimal.ZERO
                        var delegatedFree = BigDecimal.ZERO

                        dpBalance = coin.amount.toBigDecimal()

                        vestingAccount.baseVestingAccount.originalVestingList.forEach { vesting ->
                            if (vesting.denom == denom) {
                                originalVesting = originalVesting.add(vesting.amount.toBigDecimal())
                            }
                        }

                        vestingAccount.baseVestingAccount.originalVestingList.forEach { vesting ->
                            if (vesting.denom == denom) {
                                delegatedVesting =
                                    delegatedVesting.add(vesting.amount.toBigDecimal())
                            }
                        }

                        vestingAccount.baseVestingAccount.delegatedFreeList.forEach { vesting ->
                            if (vesting.denom == denom) {
                                delegatedFree = delegatedFree.add(vesting.amount.toBigDecimal())
                            }
                        }

                        remainVesting =
                            onParseStridePeriodicRemainVestingsAmountByDenom(vestingAccount, denom)
                        dpVesting = remainVesting.subtract(delegatedVesting).subtract(delegatedFree)
                        dpVesting = if (dpVesting <= BigDecimal.ZERO) BigDecimal.ZERO else dpVesting

                        if (remainVesting > delegatedVesting.add(delegatedFree)) {
                            dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting)
                        }
                        dpBalance = if (dpBalance <= BigDecimal.ZERO) BigDecimal.ZERO else dpBalance

                        if (dpVesting > BigDecimal.ZERO) {
                            val vestingCoin = CoinProto.Coin.newBuilder().setDenom(denom)
                                .setAmount(dpVesting.toPlainString()).build()
                            chain.cosmosFetcher?.cosmosVestings?.add(vestingCoin)
                            var replace = -1
                            for (i in 0 until cosmosBalances.size) {
                                if (cosmosBalances[i].denom == denom) {
                                    replace = i
                                }
                            }
                            if (replace >= 0) {
                                val tempBalances = cosmosBalances.toMutableList()
                                tempBalances[replace] = CoinProto.Coin.newBuilder().setDenom(denom)
                                    .setAmount(dpBalance.toPlainString()).build()
                                chain.cosmosFetcher?.cosmosBalances = tempBalances
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onParseLcdVestingAccount(chain: BaseChain) {
        chain.cosmosFetcher?.cosmosVestings?.clear()
        val authInfo = chain.cosmosFetcher?.cosmosLcdAuth

        authInfo?.let { auth ->
            chain.cosmosFetcher?.cosmosBalances?.let { cosmosBalances ->
                if (auth["@type"].asString.contains(VestingProto.PeriodicVestingAccount.getDescriptor().fullName)) {
                    val vestingAccount = auth["base_vesting_account"].asJsonObject

                    cosmosBalances.forEach { coin ->
                        val denom = coin.denom
                        var dpBalance = BigDecimal.ZERO
                        var dpVesting = BigDecimal.ZERO
                        var originalVesting = BigDecimal.ZERO
                        var remainVesting = BigDecimal.ZERO
                        var delegatedVesting = BigDecimal.ZERO

                        dpBalance = coin.amount.toBigDecimal()

                        vestingAccount["original_vesting"].asJsonArray.forEach { vesting ->
                            if (vesting.asJsonObject["denom"].asString == denom) {
                                originalVesting =
                                    originalVesting.add(vesting.asJsonObject["amount"].asString.toBigDecimal())
                            }
                        }

                        vestingAccount["delegated_vesting"].asJsonArray.forEach { vesting ->
                            if (vesting.asJsonObject["denom"].asString == denom) {
                                delegatedVesting =
                                    delegatedVesting.add(vesting.asJsonObject["amount"].asString.toBigDecimal())
                            }
                        }

                        remainVesting = onParsePeriodicRemainVestingAmountByDenom(chain, denom)

                        dpVesting = remainVesting.subtract(delegatedVesting)

                        dpVesting = if (dpVesting <= BigDecimal.ZERO) BigDecimal.ZERO else dpVesting

                        if (remainVesting > delegatedVesting) {
                            dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting)
                        }

                        if (dpVesting > BigDecimal.ZERO) {
                            val vestingCoin = CoinProto.Coin.newBuilder().setDenom(denom)
                                .setAmount(dpVesting.toPlainString()).build()
                            chain.cosmosFetcher?.cosmosVestings?.add(vestingCoin)
                            var replace = -1
                            for (i in 0 until cosmosBalances.size) {
                                if (cosmosBalances[i].denom == denom) {
                                    replace = i
                                }
                            }
                            if (replace >= 0) {
                                val tempBalances = cosmosBalances.toMutableList()
                                tempBalances[replace] = CoinProto.Coin.newBuilder().setDenom(denom)
                                    .setAmount(dpBalance.toPlainString()).build()
                                chain.cosmosFetcher?.cosmosBalances = tempBalances
                            }
                        }
                    }

                } else if (auth["@type"].asString.contains(VestingProto.ContinuousVestingAccount.getDescriptor().fullName)) {
                    val vestingAccount = auth["base_vesting_account"].asJsonObject

                    cosmosBalances.forEach { coin ->
                        val denom = coin.denom
                        var dpBalance = BigDecimal.ZERO
                        var dpVesting = BigDecimal.ZERO
                        var originalVesting = BigDecimal.ZERO
                        var remainVesting = BigDecimal.ZERO
                        var delegatedVesting = BigDecimal.ZERO

                        dpBalance = coin.amount.toBigDecimal()

                        vestingAccount["original_vesting"].asJsonArray.forEach { vesting ->
                            if (vesting.asJsonObject["denom"].asString == denom) {
                                originalVesting =
                                    originalVesting.add(vesting.asJsonObject["amount"].asString.toBigDecimal())
                            }
                        }

                        vestingAccount["delegated_vesting"].asJsonArray.forEach { vesting ->
                            if (vesting.asJsonObject["denom"].asString == denom) {
                                delegatedVesting =
                                    delegatedVesting.add(vesting.asJsonObject["amount"].asString.toBigDecimal())
                            }
                        }

                        val cTime = Calendar.getInstance().time.time
                        val vestingStart = (auth["start_time"].asString ?: "0").toLong() * 1000
                        val vestingEnd =
                            (vestingAccount["end_time"].asString ?: "0").toLong() * 1000
                        remainVesting = if (cTime < vestingStart) {
                            originalVesting
                        } else if (cTime > vestingEnd) {
                            BigDecimal.ZERO
                        } else {
                            val progress =
                                (cTime - vestingStart).toFloat() / (vestingEnd - vestingStart).toFloat()
                            originalVesting.multiply(BigDecimal((1 - progress).toDouble()))
                                .setScale(0, RoundingMode.UP)
                        }

                        dpVesting = remainVesting.subtract(delegatedVesting)

                        dpVesting = if (dpVesting <= BigDecimal.ZERO) BigDecimal.ZERO else dpVesting

                        if (remainVesting > BigDecimal.ZERO) {
                            dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting)
                        }

                        if (dpVesting > BigDecimal.ZERO) {
                            val vestingCoin = CoinProto.Coin.newBuilder().setDenom(denom)
                                .setAmount(dpVesting.toPlainString()).build()
                            chain.cosmosFetcher?.cosmosVestings?.add(vestingCoin)
                            var replace = -1
                            for (i in 0 until cosmosBalances.size) {
                                if (cosmosBalances[i].denom == denom) {
                                    replace = i
                                }
                            }
                            if (replace >= 0) {
                                val tempBalances = cosmosBalances.toMutableList()
                                tempBalances[replace] = CoinProto.Coin.newBuilder().setDenom(denom)
                                    .setAmount(dpBalance.toPlainString()).build()
                                chain.cosmosFetcher?.cosmosBalances = tempBalances
                            }
                        }
                    }

                } else if (auth["@type"].asString.contains(VestingProto.DelayedVestingAccount.getDescriptor().fullName)) {
                    val vestingAccount = auth["base_vesting_account"].asJsonObject

                    cosmosBalances.forEach { coin ->
                        val denom = coin.denom
                        var dpBalance = BigDecimal.ZERO
                        var dpVesting = BigDecimal.ZERO
                        var originalVesting = BigDecimal.ZERO
                        var remainVesting = BigDecimal.ZERO
                        var delegatedVesting = BigDecimal.ZERO

                        dpBalance = coin.amount.toBigDecimal()

                        vestingAccount["original_vesting"].asJsonArray.forEach { vesting ->
                            if (vesting.asJsonObject["denom"].asString == denom) {
                                originalVesting =
                                    originalVesting.add(vesting.asJsonObject["amount"].asString.toBigDecimal())
                            }
                        }

                        val cTime = Calendar.getInstance().time.time
                        val vestingEnd =
                            (vestingAccount["end_time"].asString ?: "0").toLong() * 1000
                        if (cTime < vestingEnd) {
                            remainVesting = originalVesting
                        }

                        vestingAccount["delegated_vesting"].asJsonArray.forEach { vesting ->
                            if (vesting.asJsonObject["denom"].asString == denom) {
                                delegatedVesting =
                                    delegatedVesting.add(vesting.asJsonObject["amount"].asString.toBigDecimal())
                            }
                        }

                        dpVesting = remainVesting.subtract(delegatedVesting)

                        dpVesting = if (dpVesting <= BigDecimal.ZERO) BigDecimal.ZERO else dpVesting

                        if (remainVesting > BigDecimal.ZERO) {
                            dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting)
                        }

                        if (dpVesting > BigDecimal.ZERO) {
                            val vestingCoin = CoinProto.Coin.newBuilder().setDenom(denom)
                                .setAmount(dpVesting.toPlainString()).build()
                            chain.cosmosFetcher?.cosmosVestings?.add(vestingCoin)
                            var replace = -1
                            for (i in 0 until cosmosBalances.size) {
                                if (cosmosBalances[i].denom == denom) {
                                    replace = i
                                }
                            }
                            if (replace >= 0) {
                                val tempBalances = cosmosBalances.toMutableList()
                                tempBalances[replace] = CoinProto.Coin.newBuilder().setDenom(denom)
                                    .setAmount(dpBalance.toPlainString()).build()
                                chain.cosmosFetcher?.cosmosBalances = tempBalances
                            }
                        }
                    }

                } else if (auth["@type"].asString.contains(StridePeriodicVestingAccount.getDescriptor().fullName)) {
                    val vestingAccount = auth["base_vesting_account"].asJsonObject

                    cosmosBalances.forEach { coin ->
                        val denom = coin.denom
                        var dpBalance = BigDecimal.ZERO
                        var dpVesting = BigDecimal.ZERO
                        var originalVesting = BigDecimal.ZERO
                        var remainVesting = BigDecimal.ZERO
                        var delegatedVesting = BigDecimal.ZERO
                        var delegatedFree = BigDecimal.ZERO

                        dpBalance = coin.amount.toBigDecimal()

                        vestingAccount["original_vesting"].asJsonArray.forEach { vesting ->
                            if (vesting.asJsonObject["denom"].asString == denom) {
                                originalVesting =
                                    originalVesting.add(vesting.asJsonObject["amount"].asString.toBigDecimal())
                            }
                        }

                        vestingAccount["delegated_vesting"].asJsonArray.forEach { vesting ->
                            if (vesting.asJsonObject["denom"].asString == denom) {
                                delegatedVesting =
                                    delegatedVesting.add(vesting.asJsonObject["amount"].asString.toBigDecimal())
                            }
                        }

                        vestingAccount["delegated_free"].asJsonArray.forEach { vesting ->
                            if (vesting.asJsonObject["denom"].asString == denom) {
                                delegatedFree =
                                    delegatedFree.add(vesting.asJsonObject["amount"].asString.toBigDecimal())
                            }
                        }

                        remainVesting =
                            onParseStridePeriodicRemainVestingsAmountByDenom(chain, denom)
                        dpVesting = remainVesting.subtract(delegatedVesting).subtract(delegatedFree)
                        dpVesting = if (dpVesting <= BigDecimal.ZERO) BigDecimal.ZERO else dpVesting

                        if (remainVesting > delegatedVesting.add(delegatedFree)) {
                            dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting)
                        }
                        dpBalance = if (dpBalance <= BigDecimal.ZERO) BigDecimal.ZERO else dpBalance

                        if (dpVesting > BigDecimal.ZERO) {
                            val vestingCoin = CoinProto.Coin.newBuilder().setDenom(denom)
                                .setAmount(dpVesting.toPlainString()).build()
                            chain.cosmosFetcher?.cosmosVestings?.add(vestingCoin)
                            var replace = -1
                            for (i in 0 until cosmosBalances.size) {
                                if (cosmosBalances[i].denom == denom) {
                                    replace = i
                                }
                            }
                            if (replace >= 0) {
                                val tempBalances = cosmosBalances.toMutableList()
                                tempBalances[replace] = CoinProto.Coin.newBuilder().setDenom(denom)
                                    .setAmount(dpBalance.toPlainString()).build()
                                chain.cosmosFetcher?.cosmosBalances = tempBalances
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onParsePeriodicUnLockTime(
        vestingAccount: VestingProto.PeriodicVestingAccount, position: Int
    ): Long {
        var result: Long = vestingAccount.startTime
        for (i in 0..position) {
            result += vestingAccount.vestingPeriodsList[i].length
        }
        return result * 1000
    }

    private fun onParsePeriodicRemainVestings(vestingAccount: VestingProto.PeriodicVestingAccount): MutableList<VestingProto.Period> {
        val result: MutableList<VestingProto.Period> = mutableListOf()
        val cTime = Calendar.getInstance().time.time
        for (i in 0 until vestingAccount.vestingPeriodsCount) {
            val unlockTime: Long = onParsePeriodicUnLockTime(vestingAccount, i)
            if (cTime < unlockTime) {
                val temp = VestingProto.Period.newBuilder().setLength(unlockTime)
                    .addAllAmount(vestingAccount.vestingPeriodsList[i].amountList).build()
                result.add(temp)
            }
        }
        return result
    }

    private fun onParsePeriodicRemainVestingsByDenom(
        vestingAccount: VestingProto.PeriodicVestingAccount, denom: String
    ): MutableList<VestingProto.Period> {
        val result: MutableList<VestingProto.Period> = mutableListOf()
        for (vp in onParsePeriodicRemainVestings(vestingAccount)) {
            for (coin in vp.amountList) {
                if (coin.denom == denom) {
                    result.add(vp)
                }
            }
        }
        return result
    }

    private fun onParsePeriodicRemainVestingAmountByDenom(
        vestingAccount: VestingProto.PeriodicVestingAccount, denom: String
    ): BigDecimal {
        var result = BigDecimal.ZERO
        val periods = onParsePeriodicRemainVestingsByDenom(vestingAccount, denom)
        for (vp in periods) {
            for (coin in vp.amountList) {
                if (coin.denom == denom) {
                    result = result.add(coin.amount.toBigDecimal())
                }
            }
        }
        return result
    }

    private fun onParseStridePeriodicRemainVestingsByDenom(
        vestingAccount: StridePeriodicVestingAccount, denom: String
    ): MutableList<VestingProto.Period> {
        val result: MutableList<VestingProto.Period> = mutableListOf()
        val cTime = Calendar.getInstance().time.time
        for (period in vestingAccount.vestingPeriodsList) {
            val vestingEnd = (period.startTime + period.length) * 1000
            if (cTime < vestingEnd) {
                for (vesting in period.amountList) {
                    if (vesting.denom.equals(denom)) {
                        val temp = VestingProto.Period.newBuilder().setLength(vestingEnd)
                            .addAllAmount(period.amountList).build()
                        result.add(temp)
                    }
                }
            }
        }
        return result
    }

    private fun onParseStridePeriodicRemainVestingsAmountByDenom(
        vestingAccount: StridePeriodicVestingAccount, denom: String
    ): BigDecimal {
        var result = BigDecimal.ZERO
        val vpList: List<VestingProto.Period> =
            onParseStridePeriodicRemainVestingsByDenom(vestingAccount, denom)
        for (vp in vpList) {
            for (coin in vp.amountList) {
                if (coin.denom.equals(denom)) {
                    result = result.add(BigDecimal(coin.amount))
                }
            }
        }
        return result
    }

    private fun onParsePeriodicRemainVestingAmountByDenom(
        chain: BaseChain, denom: String
    ): BigDecimal {
        var result = BigDecimal.ZERO
        val periods = onParsePeriodicRemainVestingsByDenom(chain, denom)
        for (vp in periods) {
            for (coin in vp.amountList) {
                if (coin.denom == denom) {
                    result = result.add(coin.amount.toBigDecimal())
                }
            }
        }
        return result
    }

    private fun onParsePeriodicRemainVestingsByDenom(
        chain: BaseChain, denom: String
    ): MutableList<VestingProto.Period> {
        val result: MutableList<VestingProto.Period> = mutableListOf()
        for (vp in onParsePeriodicRemainVestings(chain)) {
            for (coin in vp.amountList) {
                if (coin.denom == denom) {
                    result.add(vp)
                }
            }
        }
        return result
    }

    private fun onParsePeriodicRemainVestings(chain: BaseChain): MutableList<VestingProto.Period> {
        val result: MutableList<VestingProto.Period> = mutableListOf()
        val cTime = Calendar.getInstance().time.time
        chain.cosmosFetcher?.cosmosLcdAuth?.let {
            for (i in 0 until it["vesting_periods"].asJsonArray.size()) {
                val unlockTime: Long = onParsePeriodicUnLockTime(chain, i)
                if (cTime < unlockTime) {
                    val coins = mutableListOf<CoinProto.Coin>()
                    it["vesting_periods"].asJsonArray[i].asJsonObject["amount"].asJsonArray.forEach { rawCoin ->
                        coins.add(
                            CoinProto.Coin.newBuilder()
                                .setDenom(rawCoin.asJsonObject["denom"].asString)
                                .setAmount(rawCoin.asJsonObject["amount"].asString).build()
                        )
                    }
                    val temp =
                        VestingProto.Period.newBuilder().setLength(unlockTime).addAllAmount(coins)
                            .build()
                    result.add(temp)
                }
            }
        }
        return result
    }

    private fun onParseStridePeriodicRemainVestingsAmountByDenom(
        chain: BaseChain, denom: String
    ): BigDecimal {
        var result = BigDecimal.ZERO
        val vpList: List<VestingProto.Period> =
            onParseStridePeriodicRemainVestingsByDenom(chain, denom)
        for (vp in vpList) {
            for (coin in vp.amountList) {
                if (coin.denom.equals(denom)) {
                    result = result.add(BigDecimal(coin.amount))
                }
            }
        }
        return result
    }

    private fun onParseStridePeriodicRemainVestingsByDenom(
        chain: BaseChain, denom: String
    ): MutableList<VestingProto.Period> {
        val result: MutableList<VestingProto.Period> = mutableListOf()
        val cTime = Calendar.getInstance().time.time
        chain.cosmosFetcher?.cosmosLcdAuth?.let {
            for (period in it["vesting_periods"].asJsonArray) {
                val vestingEnd =
                    (period.asJsonObject["start_time"].asString.toLong() + period.asJsonObject["length"].asString.toLong()) * 1000
                if (cTime < vestingEnd) {
                    val coins = mutableListOf<CoinProto.Coin>()
                    for (vesting in period.asJsonObject["amount"].asJsonArray) {
                        if (vesting.asJsonObject["denom"].asString.equals(denom, true)) {
                            coins.add(
                                CoinProto.Coin.newBuilder()
                                    .setDenom(vesting.asJsonObject["denom"].asString)
                                    .setAmount(vesting.asJsonObject["amount"].asString).build()
                            )
                        }
                    }
                    val temp =
                        VestingProto.Period.newBuilder().setLength(vestingEnd).addAllAmount(coins)
                            .build()
                    result.add(temp)
                }
            }
        }
        return result
    }

    private fun onParsePeriodicUnLockTime(
        chain: BaseChain, position: Int
    ): Long {
        var result = chain.cosmosFetcher?.cosmosLcdAuth?.get("start_time")?.asLong ?: 0
        for (i in 0..position) {
            result += (chain.cosmosFetcher?.cosmosLcdAuth?.get("vesting_periods")?.asJsonArray?.get(
                i
            )?.asJsonObject?.get("length")?.asString ?: "0").toLong()
        }
        return result * 1000
    }

    fun checkPasscodePattern(pinCode: String): Boolean {
        if (pinCode.length != 5) return false
        val regex = "^\\d{4}+[A-Z]{1}$"
        val p = Pattern.compile(regex)
        val m = p.matcher(pinCode)
        return m.matches()
    }

    const val LANGUAGE_ENGLISH = 1
    const val LANGUAGE_KOREAN = 2
    const val LANGUAGE_JAPANESE = 3

    fun updateResources(context: Context?): Context? {
        val locale: Locale = when (Prefs.language) {
            LANGUAGE_ENGLISH -> {
                Locale("en")
            }

            LANGUAGE_KOREAN -> {
                Locale("ko")
            }

            LANGUAGE_JAPANESE -> {
                Locale("ja")
            }

            else -> {
                Resources.getSystem().configuration.locales[0]
            }
        }
        Locale.setDefault(locale)
        val config = Configuration(context?.resources?.configuration)
        config.setLocale(locale)
        return context?.createConfigurationContext(config)
    }

    fun isValidChainAddress(chain: BaseChain?, address: String?): Boolean {
        if (address?.isEmpty() == true) {
            return false
        }
        if (address?.startsWith("0x") == true) {
            if (BaseKey.isValidEthAddress(address)) {
                return true
            }
            return false
        }

        if (!BaseKey.isValidBech32(address)) {
            return false
        }
        if (address?.startsWith(chain?.accountPrefix + 1) == false) {
            return false
        }
        return true
    }

    fun isValidBechAddress(line: BaseChain?, address: String?): Boolean {
        if (address?.isEmpty() == true) {
            return false
        }
        if (address?.startsWith("0x") == true) {
            return false
        }

        if (!BaseKey.isValidBech32(address)) {
            return false
        }
        if (address?.startsWith(line?.accountPrefix + 1) == false) {
            return false
        }
        return true
    }

    fun isValidBitAddress(chain: ChainBitCoin84, address: String): Boolean {
        return try {
            val network = if (chain.isTestnet) {
                TestNet3Params.get()
            } else {
                MainNetParams.get()
            }
            Address.fromString(network, address)
            true
        } catch (e: Exception) {
            false
        }
    }
}