package wannabit.io.cosmostaion.common

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.vesting.v1beta1.VestingProto
import com.stride.vesting.VestingProto.StridePeriodicVestingAccount
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_BINANCE_BNB
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_BINANCE_BTCB
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_BINANCE_BUSD
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_BINANCE_XRPB
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_KAVA_BNB
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_KAVA_BTCB
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_KAVA_BUSD
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_KAVA_XRPB
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Calendar
import java.util.Locale
import java.util.regex.Pattern

object BaseUtils {
    fun onParseVestingAccount(line: CosmosLine) {
        val authInfo = line.cosmosAuth
        var denom = ""
        var dpBalance = BigDecimal.ZERO
        var dpVesting = BigDecimal.ZERO
        var originalVesting = BigDecimal.ZERO
        var remainVesting = BigDecimal.ZERO
        var delegatedVesting = BigDecimal.ZERO

        authInfo?.let { auth ->
            if (auth.typeUrl.contains(VestingProto.PeriodicVestingAccount.getDescriptor().fullName)) {
                val vestingAccount = VestingProto.PeriodicVestingAccount.parseFrom(auth.value)

                line.cosmosBalances.forEach { coin ->
                    denom = coin.denom
                    dpBalance = coin.amount.toBigDecimal()

                    vestingAccount.baseVestingAccount.originalVestingList.forEach { vesting ->
                        if (vesting.denom == denom) {
                            originalVesting = originalVesting.add(vesting.amount.toBigDecimal())
                        }
                    }

                    vestingAccount.baseVestingAccount.delegatedVestingList.forEach { vesting ->
                        if (vesting.denom == denom) {
                            delegatedVesting = delegatedVesting.add(vesting.amount.toBigDecimal())
                        }
                    }

                    remainVesting = onParsePeriodicRemainVestingAmountByDenom(vestingAccount, denom)

                    dpVesting = remainVesting.subtract(delegatedVesting)

                    dpVesting = if (dpVesting <= BigDecimal.ZERO) BigDecimal.ZERO else dpVesting

                    if (remainVesting > delegatedVesting) {
                        dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting)
                    }

                    if (dpVesting > BigDecimal.ZERO) {
                        val vestingCoin = CoinProto.Coin.newBuilder().
                                            setDenom(denom).
                                            setAmount(dpVesting.toPlainString()).build()
                        line.cosmosVestings.add(vestingCoin)
                        var replace = -1
                        for (i in 0 until line.cosmosBalances.size) {
                            if (line.cosmosBalances[i].denom == denom) {
                                replace = i
                            }
                        }
                        if (replace >= 0) {
                            val tempBalances = line.cosmosBalances.toMutableList()
                            tempBalances[replace] = CoinProto.Coin.newBuilder().setDenom(denom)
                                .setAmount(dpBalance.toPlainString()).build()
                            line.cosmosBalances = tempBalances
                        }
                    }
                }

            } else if (auth.typeUrl.contains(VestingProto.ContinuousVestingAccount.getDescriptor().fullName)) {
                val vestingAccount = VestingProto.ContinuousVestingAccount.parseFrom(auth.value)

                line.cosmosBalances.forEach { coin ->
                    dpBalance = coin.amount.toBigDecimal()

                    vestingAccount.baseVestingAccount.originalVestingList.forEach { vesting ->
                        if (vesting.denom == denom) {
                            originalVesting = originalVesting.add(vesting.amount.toBigDecimal())
                        }
                    }

                    vestingAccount.baseVestingAccount.delegatedVestingList.forEach { vesting ->
                        if (vesting.denom == denom) {
                            delegatedVesting = delegatedVesting.add(vesting.amount.toBigDecimal())
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
                        val progress = (cTime - vestingStart).toFloat() / (vestingEnd - vestingStart).toFloat()
                        originalVesting.multiply(BigDecimal((1 - progress).toDouble())).setScale(0, RoundingMode.UP)
                    }

                    dpVesting = remainVesting.subtract(delegatedVesting)

                    dpVesting = if (dpVesting <= BigDecimal.ZERO) BigDecimal.ZERO else dpVesting

                    if (remainVesting > BigDecimal.ZERO) {
                        dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting)
                    }

                    if (dpVesting > BigDecimal.ZERO) {
                        val vestingCoin = CoinProto.Coin.newBuilder().setDenom(denom).setAmount(dpVesting.toPlainString()).build()
                        line.cosmosVestings.add(vestingCoin)
                        var replace = -1
                        for (i in 0 until line.cosmosBalances.size) {
                            if (line.cosmosBalances[i].denom == denom) {
                                replace = i
                            }
                        }
                        if (replace >= 0) {
                            val tempBalances = line.cosmosBalances.toMutableList()
                            tempBalances[replace] = CoinProto.Coin.newBuilder().setDenom(denom)
                                .setAmount(dpBalance.toPlainString()).build()
                            line.cosmosBalances = tempBalances
                        }
                    }
                }

            } else if (auth.typeUrl.contains(VestingProto.DelayedVestingAccount.getDescriptor().fullName)) {
                val vestingAccount = VestingProto.DelayedVestingAccount.parseFrom(auth.value)

                line.cosmosBalances.forEach { coin ->
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
                            delegatedVesting = delegatedVesting.add(vesting.amount.toBigDecimal())
                        }
                    }

                    dpVesting = remainVesting.subtract(delegatedVesting)

                    dpVesting = if (dpVesting <= BigDecimal.ZERO) BigDecimal.ZERO else dpVesting

                    if (remainVesting > BigDecimal.ZERO) {
                        dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting)
                    }

                    if (dpVesting > BigDecimal.ZERO) {
                        val vestingCoin = CoinProto.Coin.newBuilder().setDenom(denom).setAmount(dpVesting.toPlainString()).build()
                        line.cosmosVestings.add(vestingCoin)
                        var replace = -1
                        for (i in 0 until line.cosmosBalances.size) {
                            if (line.cosmosBalances[i].denom == denom) {
                                replace = i
                            }
                        }
                        if (replace >= 0) {
                            val tempBalances = line.cosmosBalances.toMutableList()
                            tempBalances[replace] = CoinProto.Coin.newBuilder().setDenom(denom)
                                .setAmount(dpBalance.toPlainString()).build()
                            line.cosmosBalances = tempBalances
                        }
                    }
                }

            } else if (auth.typeUrl.contains(StridePeriodicVestingAccount.getDescriptor().fullName)) {
                val vestingAccount = StridePeriodicVestingAccount.parseFrom(auth.value)

                line.cosmosBalances.forEach { coin ->
                    var delegatedFree = BigDecimal.ZERO
                    dpBalance = coin.amount.toBigDecimal()

                    vestingAccount.baseVestingAccount.originalVestingList.forEach { vesting ->
                        if (vesting.denom == denom) {
                            originalVesting = originalVesting.add(vesting.amount.toBigDecimal())
                        }
                    }

                    vestingAccount.baseVestingAccount.originalVestingList.forEach { vesting ->
                        if (vesting.denom == denom) {
                            delegatedVesting = delegatedVesting.add(vesting.amount.toBigDecimal())
                        }
                    }

                    vestingAccount.baseVestingAccount.delegatedFreeList.forEach { vesting ->
                        if (vesting.denom == denom) {
                            delegatedFree = delegatedFree.add(vesting.amount.toBigDecimal())
                        }
                    }

                    remainVesting = onParseStridePeriodicRemainVestingsAmountByDenom(vestingAccount, denom)
                    dpVesting = remainVesting.subtract(delegatedVesting).subtract(delegatedFree)
                    dpVesting = if (dpVesting <= BigDecimal.ZERO) BigDecimal.ZERO else dpVesting

                    if (remainVesting > delegatedVesting.add(delegatedFree)) {
                        dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting)
                    }
                    dpBalance = if (dpBalance <= BigDecimal.ZERO) BigDecimal.ZERO else dpBalance

                    if (dpVesting > BigDecimal.ZERO) {
                        val vestingCoin = CoinProto.Coin.newBuilder().setDenom(denom).setAmount(dpVesting.toPlainString()).build()
                        line.cosmosVestings.add(vestingCoin)
                        var replace = -1
                        for (i in 0 until line.cosmosBalances.size) {
                            if (line.cosmosBalances[i].denom == denom) {
                                replace = i
                            }
                        }
                        if (replace >= 0) {
                            val tempBalances = line.cosmosBalances.toMutableList()
                            tempBalances[replace] = CoinProto.Coin.newBuilder().setDenom(denom)
                                .setAmount(dpBalance.toPlainString()).build()
                            line.cosmosBalances = tempBalances
                        }
                    }
                }
            }
        }
    }

    private fun onParsePeriodicUnLockTime(
        vestingAccount: VestingProto.PeriodicVestingAccount,
        position: Int
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
        vestingAccount: VestingProto.PeriodicVestingAccount,
        denom: String
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
        vestingAccount: VestingProto.PeriodicVestingAccount,
        denom: String
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
        vestingAccount: StridePeriodicVestingAccount,
        denom: String
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
        vestingAccount: StridePeriodicVestingAccount,
        denom: String
    ): BigDecimal {
        var result = BigDecimal.ZERO
        val vpList: List<VestingProto.Period> = onParseStridePeriodicRemainVestingsByDenom(vestingAccount, denom)
        for (vp in vpList) {
            for (coin in vp.amountList) {
                if (coin.denom.equals(denom)) {
                    result = result.add(BigDecimal(coin.amount))
                }
            }
        }
        return result
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
        val locale: Locale =
            when (Prefs.language) {
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

    fun isValidChainAddress(line: CosmosLine?, address: String?): Boolean {
        if (address?.isEmpty() == true) { return false }
        if (address?.startsWith("0x") == true) {
            if (BaseKey.isValidEthAddress(address)) {
                return true
            }
            return false
        }

        if (!BaseKey.isValidBech32(address)) { return false }
        if (address?.startsWith(line?.accountPrefix + 1) == false) {
            return false
        }
        return true
    }

    fun isHtlcSwappableCoin(line: CosmosLine, denom: String): Boolean {
        if (line is ChainBinanceBeacon) {
            when (denom) {
                TOKEN_HTLC_BINANCE_BNB -> { return true }
                TOKEN_HTLC_BINANCE_BTCB -> { return true }
                TOKEN_HTLC_BINANCE_XRPB -> { return true }
                TOKEN_HTLC_BINANCE_BUSD -> { return true }
            }
        } else {
            when (denom) {
                TOKEN_HTLC_KAVA_BNB -> { return true }
                TOKEN_HTLC_KAVA_BTCB -> { return true }
                TOKEN_HTLC_KAVA_XRPB -> { return true }
                TOKEN_HTLC_KAVA_BUSD -> { return true }
            }
        }
        return false
    }
}