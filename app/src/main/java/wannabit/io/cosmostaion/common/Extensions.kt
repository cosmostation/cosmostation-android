package wannabit.io.cosmostaion.common

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.cosmos.base.v1beta1.CoinProto
import com.kava.cdp.v1beta1.GenesisProto.CollateralParam
import com.kava.cdp.v1beta1.QueryProto.CDPResponse
import com.kava.hard.v1beta1.HardProto.MoneyMarket
import com.kava.incentive.v1beta1.QueryProto
import com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse
import com.kava.swap.v1beta1.QueryProto.DepositResponse
import com.kava.swap.v1beta1.QueryProto.PoolResponse
import com.squareup.picasso.Picasso
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseConstant.CONSTANT_D
import wannabit.io.cosmostaion.common.BaseUtils.LANGUAGE_ENGLISH
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemToastBinding
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import kotlin.math.ceil

fun formatString(input: String, point: Int): SpannableString {
    val spannableString = SpannableString(input)
    spannableString.setSpan(
        RelativeSizeSpan(0.8f),
        spannableString.length - point,
        spannableString.length,
        Spannable.SPAN_INCLUSIVE_INCLUSIVE
    )
    return spannableString
}

fun formatAmount(input: String, decimal: Int): SpannableString {
    val spannableString = SpannableString(getDecimalFormat(decimal).format(input.toBigDecimal()))
    spannableString.setSpan(
        RelativeSizeSpan(0.8f),
        spannableString.length - decimal,
        spannableString.length,
        Spannable.SPAN_INCLUSIVE_INCLUSIVE
    )
    return spannableString
}

fun assetValue(value: BigDecimal, isUsd: Boolean? = false): SpannableString {
    val formatted = if (isUsd == true) {
        "$" + " " + getDecimalFormat(3).format(value)
    } else {
        BaseData.currencySymbol() + " " + getDecimalFormat(3).format(value)
    }
    return formatString(formatted, 3)
}

fun formatAssetValue(value: BigDecimal, isUsd: Boolean? = false): SpannableString {
    val spannableString = assetValue(value, isUsd)
    spannableString.setSpan(RelativeSizeSpan(0.8f), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    return spannableString
}

fun formatPercent(value: String): SpannableString {
    return formatString("$value%", 3)
}

fun priceChangeStatus(lastUpDown: BigDecimal): SpannableString {
    return if (BigDecimal.ZERO > lastUpDown) {
        formatString("- " + lastUpDown.toString().replace("-", "") + "%", 3)
    } else {
        formatString("+ $lastUpDown%", 3)
    }
}

fun TextView.priceChangeStatusColor(lastUpDown: BigDecimal) {
    if (BigDecimal.ZERO > lastUpDown) {
        if (Prefs.priceStyle == 0) {
            setTextColor(ContextCompat.getColorStateList(context, R.color.color_accent_red))
        } else {
            setTextColor(ContextCompat.getColorStateList(context, R.color.color_accent_green))
        }

    } else {
        if (Prefs.priceStyle == 0) {
            setTextColor(ContextCompat.getColorStateList(context, R.color.color_accent_green))
        } else {
            setTextColor(ContextCompat.getColorStateList(context, R.color.color_accent_red))
        }
    }
}

fun TextView.hiddenStatus(amount: SpannableString) {
    if (Prefs.hideValue) {
        text = "✱✱✱✱"
        textSize = 10f
    } else {
        text = amount
        textSize = 12f
    }
}

fun Activity.toMoveAnimation() {
    this.overridePendingTransition(
        R.anim.anim_slide_in_left, R.anim.anim_slide_out_right
    )
}

fun Activity.toMoveBack() {
    this.overridePendingTransition(
        R.anim.anim_slide_in_right, R.anim.anim_slide_out_left
    )
}

fun FragmentActivity.toMoveFragment(
    currentFragment: Fragment, moveFragment: Fragment, stackName: String
) {
    this.supportFragmentManager.beginTransaction().setCustomAnimations(
        R.animator.to_right, R.animator.from_right, R.animator.to_left, R.animator.from_left
    ).add(R.id.fragment_container, moveFragment).hide(currentFragment).setReorderingAllowed(true)
        .addToBackStack(stackName).commitAllowingStateLoss()
}

fun ImageView.setTokenImg(asset: Asset) {
    Picasso.get().load(CosmostationConstants.CHAIN_BASE_URL + asset.image)
        .error(R.drawable.token_default).into(this)
}

fun ImageView.setTokenImg(tokenImg: String) {
    Picasso.get().load(tokenImg).error(R.drawable.token_default).into(this)
}

fun ImageView.setImg(resourceId: Int) {
    Picasso.get().load(resourceId).into(this)
}

fun ImageView.setMonikerImg(line: CosmosLine, opAddress: String?) {
    Picasso.get().load(line.monikerImg(opAddress)).error(R.drawable.icon_default_vaildator)
        .into(this)
}

fun AppCompatActivity.makeToast(id: Int) {
    Toast.makeText(this, this.getString(id), Toast.LENGTH_SHORT).show()
}

fun Context.makeToast(id: Int) {
    Toast.makeText(this, this.getString(id), Toast.LENGTH_SHORT).show()
}

fun Context.makeToast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(view: View?, msg: String?, isTx: Boolean) {
    val inflater = LayoutInflater.from(this)
    val toastBinding =
        ItemToastBinding.inflate(inflater, view?.findViewById(R.id.toast_layout), false)
    toastBinding.toastTitle.visibleOrGone(isTx)
    toastBinding.toastMsg.text = msg

    val toast = Toast(this)
    toast.view = toastBinding.root
    toast.show()
}

fun Context.showToast(view: View?, id: Int, isTx: Boolean) {
    val inflater = LayoutInflater.from(this)
    val toastBinding =
        ItemToastBinding.inflate(inflater, view?.findViewById(R.id.toast_layout), false)
    toastBinding.toastTitle.visibleOrGone(isTx)
    toastBinding.toastMsg.text = this.getString(id)

    val toast = Toast(this)
    toast.view = toastBinding.root
    toast.show()
}

fun String.hexToBigDecimal(): BigDecimal {
    if (this.isEmpty()) { return BigDecimal.ZERO }
    val hex = this.removePrefix("0x")
    return BigDecimal(BigInteger(hex, 16))
}

fun formatCurrentTimeToYear(): String {
    val locale = Locale.getDefault()
    val date = Calendar.getInstance()
    val dateFormat = SimpleDateFormat(
        if (locale.country.isEmpty()) {
            if (Prefs.language == LANGUAGE_ENGLISH) {
                "MMM dd, yyyy"
            } else {
                "yyyy.M.d"
            }
        } else {
            if (locale == Locale.US) {
                "MMM dd, yyyy"
            } else {
                "yyyy.M.d"
            }
        }, locale
    )
    return dateFormat.format(date.time)
}

fun formatTxTime(context: Context, timeString: String): String {
    val locale = Locale.getDefault()
    val inputFormat = SimpleDateFormat(context.getString(R.string.str_tx_time_grpc_format))
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = inputFormat.parse(timeString)
    val outputFormat = SimpleDateFormat(
        if (locale.country.isEmpty()) {
            if (Prefs.language == LANGUAGE_ENGLISH) {
                "MMM dd, yyyy"
            } else {
                "yyyy.M.d"
            }
        } else {
            if (locale == Locale.US) {
                "MMM dd, yyyy"
            } else {
                "yyyy.M.d"
            }
        }, locale
    )
    outputFormat.timeZone = TimeZone.getDefault()
    return outputFormat.format(date)
}

fun formatTxTimeToYear(context: Context, timeString: String): String {
    val locale = Locale.getDefault()
    val inputFormat = SimpleDateFormat(context.getString(R.string.str_tx_time_format))
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = inputFormat.parse(timeString)
    val outputFormat = SimpleDateFormat(
        if (locale.country.isEmpty()) {
            if (Prefs.language == LANGUAGE_ENGLISH) {
                "MMM dd, yyyy"
            } else {
                "yyyy.M.d"
            }
        } else {
            if (locale == Locale.US) {
                "MMM dd, yyyy"
            } else {
                "yyyy.M.d"
            }
        }, locale
    )
    outputFormat.timeZone = TimeZone.getDefault()
    return outputFormat.format(date)
}

fun formatTxTimeStampToHour(context: Context, timeString: String): String {
    val inputFormat = SimpleDateFormat(context.getString(R.string.str_tx_time_grpc_format))
    val outputFormat = SimpleDateFormat(context.getString(R.string.str_dp_time_format2))
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    return outputFormat.format(inputFormat.parse(timeString))
}

fun formatTxTimeToHour(context: Context, timeString: String): String {
    val inputFormat = SimpleDateFormat(context.getString(R.string.str_tx_time_format))
    val outputFormat = SimpleDateFormat(context.getString(R.string.str_dp_time_format2))
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    return outputFormat.format(inputFormat.parse(timeString))
}

fun dateToLong(format: String, rawValue: String?): Long {
    val simpleDateFormat = SimpleDateFormat(format)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return simpleDateFormat.parse(rawValue).time
}

fun dpTime(time: Long): String {
    val locale = Locale.getDefault()
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time

    val outputFormat = SimpleDateFormat(
        if (locale.country.isEmpty()) {
            if (Prefs.language == LANGUAGE_ENGLISH) {
                "MMM dd, yyyy (HH:mm:ss)"
            } else {
                "yyyy-MM-dd HH:mm:ss"
            }
        } else {
            if (locale == Locale.US) {
                "MMM dd, yyyy (HH:mm:ss)"
            } else {
                "yyyy-MM-dd HH:mm:ss"
            }
        }, locale
    )
    return outputFormat.format(calendar.timeInMillis)
}

fun dpTimeToYear(time: Long): String {
    val locale = Locale.getDefault()
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time

    val outputFormat = SimpleDateFormat(
        if (locale.country.isEmpty()) {
            if (Prefs.language == LANGUAGE_ENGLISH) {
                "MMM dd, yyyy"
            } else {
                "yyyy-MM-dd"
            }
        } else {
            if (locale == Locale.US) {
                "MMM dd, yyyy"
            } else {
                "yyyy-MM-dd"
            }
        }, locale
    )
    return outputFormat.format(calendar.timeInMillis)
}

fun voteDpTime(time: Long): String {
    val locale = Locale.getDefault()
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time

    val outputFormat = SimpleDateFormat(
        if (locale.country.isEmpty()) {
            if (Prefs.language == LANGUAGE_ENGLISH) {
                "MMM dd, yyyy HH:mm:ss"
            } else {
                "yyyy-MM-dd HH:mm:ss"
            }
        } else {
            if (locale == Locale.US) {
                "MMM dd, yyyy HH:mm:ss"
            } else {
                "yyyy-MM-dd HH:mm:ss"
            }
        }, locale
    )
    return outputFormat.format(calendar.timeInMillis)
}

fun gapTime(finishTime: Long): String {
    var result = "??"
    val now = Calendar.getInstance().timeInMillis
    val left = finishTime - now

    result = if (left >= CONSTANT_D) {
        "D-" + left / CONSTANT_D
    } else if (left >= BaseConstant.CONSTANT_H) {
        (left / BaseConstant.CONSTANT_H).toString() + " hours ago"
    } else if (left >= BaseConstant.CONSTANT_M) {
        (left / BaseConstant.CONSTANT_M).toString() + " minutes ago"
    } else {
        "0 days"
    }
    return result
}

fun View.visibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.goneOrVisible(visible: Boolean) {
    visibility = if (visible) View.GONE else View.VISIBLE
}

fun View.visibleOrInvisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun Button.updateButtonView(isBtnEnabled: Boolean) {
    if (isBtnEnabled) {
        isEnabled = true
        setTextColor(ContextCompat.getColorStateList(context, R.color.color_base01))
        setBackgroundResource(R.drawable.button_common_bg)
    } else {
        isEnabled = false
        setTextColor(ContextCompat.getColorStateList(context, R.color.color_base03))
        setBackgroundResource(R.drawable.button_disable_bg)
    }
}

fun Button.updateSelectButtonView(isBtnEnabled: Boolean) {
    if (isBtnEnabled) {
        isEnabled = true
        setTextColor(ContextCompat.getColorStateList(context, R.color.color_base01))
        setBackgroundResource(R.drawable.button_enable_select_bg)
    } else {
        isEnabled = false
        setTextColor(ContextCompat.getColorStateList(context, R.color.color_base03))
        setBackgroundResource(R.drawable.button_select_bg)
    }
}

fun ImageButton.updateToggleButtonView(isBtnEnabled: Boolean) {
    if (isBtnEnabled) {
        isEnabled = true
        colorFilter = PorterDuffColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
    } else {
        isEnabled = false
        colorFilter = PorterDuffColorFilter(Color.parseColor("#8A99AE"), PorterDuff.Mode.SRC_IN)
    }
}

fun Activity.historyToMintscan(selectedChain: CosmosLine?, txHash: String?) {
    selectedChain?.explorerTx(txHash)?.let {
        startActivity(Intent(Intent.ACTION_VIEW, it))

    } ?: run {
        return
    }
}

fun BigDecimal.amountHandlerLeft(decimal: Int): BigDecimal {
    return this.movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
}

fun BigDecimal.handlerRight(decimal: Int, scale: Int): BigDecimal {
    return this.movePointRight(decimal).setScale(scale, RoundingMode.HALF_UP)
}

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher, apiCall: suspend () -> T
): NetworkResult<T> {
    return withContext(dispatcher) {
        try {
            val response = apiCall.invoke()

            response?.let {
                NetworkResult.Success(it)
            } ?: run {
                NetworkResult.Error("Response Empty", "No Response")
            }

        } catch (e: Exception) {
            NetworkResult.Error("Unknown Error", e.message ?: "Unknown error occurred.")
        }
    }
}

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): NetworkResult<T> {
    return try {
        val response = apiCall.invoke()

        response?.let {
            NetworkResult.Success(it)
        } ?: run {
            NetworkResult.Error("Response Empty", "No Response")
        }

    } catch (e: Exception) {
        NetworkResult.Error("Unknown Error", e.message ?: "Unknown error occurred.")
    }
}

fun getDecimalFormat(cnt: Int): DecimalFormat {
    val formatter = NumberFormat.getNumberInstance(Locale.US)
    val decimalFormat = formatter as DecimalFormat
    decimalFormat.roundingMode = RoundingMode.DOWN
    when (cnt) {
        0 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0")
        1 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0")
        2 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00")
        3 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000")
        4 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000")
        5 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000")
        6 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000")
        7 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000")
        8 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000")
        9 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000")
        10 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000000")
        11 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000000")
        12 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000000")
        13 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000000000")
        14 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000000000")
        15 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000000000")
        16 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000000000000")
        17 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000000000000")
        18 -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000000000000")
        else -> decimalFormat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000")
    }
    return decimalFormat
}

fun dpToPx(context: Context, dp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics
    ).toInt()
}

fun getChannel(selectedChain: CosmosLine): ManagedChannel {
    return ManagedChannelBuilder.forAddress(selectedChain.getGrpc().first, selectedChain.getGrpc().second)
        .useTransportSecurity().build()
}

// kava
fun QueryProto.QueryRewardsResponse.allIncentiveCoins(): MutableList<CoinProto.Coin> {
    val result: MutableList<CoinProto.Coin> = mutableListOf()

    usdxMintingClaimsList.forEach { claim ->
        if (claim.baseClaim.hasReward()) {
            val usdxReward = claim.baseClaim.reward
            val amount = usdxReward.amount.toBigDecimal()
            if (amount > BigDecimal.ZERO) {
                result.indexOfFirst { it.denom == usdxReward.denom }.let { already ->
                    if (already != -1) {
                        val sumReward = result[already].amount.toBigDecimal().add(amount)
                        result[already] = CoinProto.Coin.newBuilder().setDenom(usdxReward.denom)
                            .setAmount(sumReward.toPlainString()).build()
                    } else {
                        result.add(usdxReward)
                    }
                }
            }
        }
    }

    hardLiquidityProviderClaimsList.forEach { hardIncen ->
        hardIncen.baseClaim.rewardList.forEach { hardReward ->
            val amount = hardReward.amount.toBigDecimal()
            if (amount > BigDecimal.ZERO) {
                result.indexOfFirst { it.denom == hardReward.denom }.let { already ->
                    if (already != -1) {
                        val sumReward = result[already].amount.toBigDecimal().add(amount)
                        result[already] = CoinProto.Coin.newBuilder().setDenom(hardReward.denom)
                            .setAmount(sumReward.toPlainString()).build()
                    } else {
                        result.add(hardReward)
                    }
                }
            }
        }
    }

    delegatorClaimsList.forEach { claim ->
        claim.baseClaim.rewardList.forEach { deleClaim ->
            val amount = deleClaim.amount.toBigDecimal()
            if (amount > BigDecimal.ZERO) {
                result.indexOfFirst { it.denom == deleClaim.denom }.let { already ->
                    if (already != -1) {
                        val sumReward = result[already].amount.toBigDecimal().add(amount)
                        result[already] = CoinProto.Coin.newBuilder().setDenom(deleClaim.denom)
                            .setAmount(sumReward.toPlainString()).build()
                    } else {
                        result.add(deleClaim)
                    }
                }
            }
        }
    }

    swapClaimsList.forEach { claim ->
        claim.baseClaim.rewardList.forEach { swapClaim ->
            val amount = swapClaim.amount.toBigDecimal()
            if (amount > BigDecimal.ZERO) {
                result.indexOfFirst { it.denom == swapClaim.denom }.let { already ->
                    if (already != -1) {
                        val sumReward = result[already].amount.toBigDecimal().add(amount)
                        result[already] = CoinProto.Coin.newBuilder().setDenom(swapClaim.denom)
                            .setAmount(sumReward.toPlainString()).build()
                    } else {
                        result.add(swapClaim)
                    }
                }
            }
        }
    }

    earnClaimsList.forEach { claim ->
        claim.baseClaim.rewardList.forEach { earnClaim ->
            val amount = earnClaim.amount.toBigDecimal()
            if (amount > BigDecimal.ZERO) {
                result.indexOfFirst { it.denom == earnClaim.denom }.let { already ->
                    if (already != -1) {
                        val sumReward = result[already].amount.toBigDecimal().add(amount)
                        result[already] = CoinProto.Coin.newBuilder().setDenom(earnClaim.denom)
                            .setAmount(sumReward.toPlainString()).build()
                    } else {
                        result.add(earnClaim)
                    }
                }
            }
        }
    }
    return result
}

fun QueryProto.QueryRewardsResponse.hasUsdxMinting(): Boolean {
    if (usdxMintingClaimsCount > 0 && usdxMintingClaimsList[0].hasBaseClaim() && usdxMintingClaimsList[0].baseClaim.hasReward() && usdxMintingClaimsList[0].baseClaim.reward.amount != "0") {
        return true
    }
    return false
}

fun QueryProto.QueryRewardsResponse.hardRewardDenoms(): MutableList<String> {
    val result = mutableListOf<String>()
    hardLiquidityProviderClaimsList.forEach { hardClaim ->
        hardClaim.baseClaim.rewardList.forEach { coin ->
            if (!result.contains(coin.denom)) {
                result.add(coin.denom)
            }
        }
    }
    return result
}

fun QueryProto.QueryRewardsResponse.delegatorRewardDenoms(): MutableList<String> {
    val result = mutableListOf<String>()
    delegatorClaimsList.forEach { delegClaim ->
        delegClaim.baseClaim.rewardList.forEach { coin ->
            if (!result.contains(coin.denom)) {
                result.add(coin.denom)
            }
        }
    }
    return result
}

fun QueryProto.QueryRewardsResponse.swapRewardDenoms(): MutableList<String> {
    val result = mutableListOf<String>()
    swapClaimsList.forEach { swapClaim ->
        swapClaim.baseClaim.rewardList.forEach { coin ->
            if (!result.contains(coin.denom)) {
                result.add(coin.denom)
            }
        }
    }
    return result
}

fun QueryProto.QueryRewardsResponse.earnRewardDenoms(): MutableList<String> {
    val result = mutableListOf<String>()
    earnClaimsList.forEach { earnClaim ->
        earnClaim.baseClaim.rewardList.forEach { coin ->
            if (!result.contains(coin.denom)) {
                result.add(coin.denom)
            }
        }
    }
    return result
}

fun CollateralParam.liquidationRatioAmount(): BigDecimal {
    return liquidationRatio.toBigDecimal().movePointLeft(18).setScale(18, RoundingMode.DOWN)
}

fun CollateralParam.expectCollateralUSDXValue(
    collateralAmount: BigDecimal?, priceFeed: QueryPricesResponse?
): BigDecimal {
    val collateralPrice = priceFeed?.kavaOraclePrice(liquidationMarketId)
    val collateralValue =
        collateralAmount?.multiply(collateralPrice)?.movePointLeft(conversionFactor.toInt())
            ?.setScale(6, RoundingMode.DOWN)
    return collateralValue?.movePointRight(6)?.setScale(0, RoundingMode.DOWN) ?: BigDecimal.ZERO
}

fun CollateralParam.expectUSDXLTV(
    collateralAmount: BigDecimal?, priceFeed: QueryPricesResponse?
): BigDecimal {
    return expectCollateralUSDXValue(collateralAmount, priceFeed).divide(
        liquidationRatioAmount(), 0, RoundingMode.DOWN
    )
}

fun CDPResponse.collateralUSDXAmount(): BigDecimal {
    return collateralValue.amount.toBigDecimal().movePointLeft(6).setScale(6, RoundingMode.DOWN)
}

fun CDPResponse.UsdxLTV(collateralParam: CollateralParam): BigDecimal {
    return collateralUSDXAmount().divide(
        collateralParam.liquidationRatioAmount(), 6, RoundingMode.DOWN
    )
}

fun CDPResponse.principalAmount(): BigDecimal {
    return principal.amount.toBigDecimal()
}

fun CDPResponse.debtAmount(): BigDecimal {
    return principalAmount().add(accumulatedFees.amount.toBigDecimal())
}

fun CDPResponse.debtUsdxValue(): BigDecimal {
    return debtAmount().movePointLeft(6).setScale(6, RoundingMode.DOWN)
}

fun CDPResponse.liquidationPrice(collateralParam: CollateralParam): BigDecimal {
    val cDenomDecimal = collateralParam.conversionFactor.toInt()
    val collateralAmount = collateral.amount.toBigDecimal().movePointLeft(cDenomDecimal)
        .setScale(cDenomDecimal, RoundingMode.DOWN)
    val rawDebtAmount =
        debtAmount().multiply(collateralParam.liquidationRatioAmount()).movePointLeft(6)
            .setScale(6, RoundingMode.DOWN)
    return rawDebtAmount.divide(collateralAmount, 6, RoundingMode.DOWN)
}

fun QueryPricesResponse.kavaOraclePrice(marketId: String): BigDecimal {
    pricesList.firstOrNull { it.marketId == marketId }?.let { price ->
        return price.price.toBigDecimal().movePointLeft(18).setScale(6, RoundingMode.DOWN)
    } ?: run {
        return BigDecimal.ZERO
    }
}

fun MutableList<MoneyMarket>.hardMoneyMarket(denom: String?): MoneyMarket? {
    return firstOrNull { it.denom == denom }
}

fun MutableList<MoneyMarket>.getLTV(denom: String?): BigDecimal {
    firstOrNull { it.denom == denom }?.let { market ->
        return market.borrowLimit.loanToValue.toBigDecimal().movePointLeft(18)
    } ?: run {
        return BigDecimal.ZERO
    }
}

fun MutableList<MoneyMarket>.spotMarketId(denom: String?): String {
    firstOrNull { it.denom == denom }?.let { market ->
        return market.spotMarketId
    } ?: run {
        return ""
    }
}

fun DepositResponse.usdxAmount(): BigDecimal {
    return sharesValueList.firstOrNull { it.denom == "usdx" }?.amount?.toBigDecimal()
        ?: BigDecimal.ZERO
}

fun PoolResponse.usdxAmount(): BigDecimal {
    return coinsList.firstOrNull { it.denom == "usdx" }?.amount?.toBigDecimal() ?: BigDecimal.ZERO
}

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}

fun Context.dialogResize(dialog: Dialog, width: Float, height: Float) {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val marginInDp = 8
    val dpi = 160
    val marginRatio = marginInDp.toFloat() / dpi.toFloat()

    if (Build.VERSION.SDK_INT < 30) {
        val display = windowManager.defaultDisplay
        val size = Point()

        display.getSize(size)

        val window = dialog.window

        val margin = (size.x * marginRatio).toInt()
        val x = ((size.x - 2 * margin) * width).toInt()
        val y = (size.y * height).toInt()
        window?.setLayout(x, y)

    } else {
        val rect = windowManager.currentWindowMetrics.bounds

        val window = dialog.window
        val margin = (rect.width() * marginRatio).toInt()
        val x = ((rect.width() - 2 * margin) * width).toInt()
        val y = (rect.height() * height).toInt()
        window?.setLayout(x, y)
    }
}

fun <T : Comparable<T>> MutableList<T>.percentile(value: Double): T? {
    if (isEmpty()) return null

    val normalizedValue = value / 100 * size.toDouble()
    val index = ceil(normalizedValue).toInt()

    val sortedData = sorted()
    if (index >= size) return sortedData[size - 1]
    return sortedData[index]
}

fun soft(array: List<List<BigInteger>>): List<BigInteger> {
    return array.mapNotNull { percentileArray ->
        if (percentileArray.isNotEmpty()) {
            listOf(percentileArray.mean()!!)
        } else {
            null
        }
    }.flatten()
}

fun List<BigInteger>.mean(): BigInteger? {
    if (isEmpty()) return null
    val sum = this.fold(BigInteger.ZERO) { acc, value -> acc + value }
    return sum / size.toString().toBigInteger()
}

fun <T> Sequence<T>.concurrentForEach(operation: suspend (T) -> Unit): Job {
    return CoroutineScope(Dispatchers.Default).launch {
        val jobs = mutableListOf<Job>()
        for (element in this@concurrentForEach) {
            val job = launch {
                operation(element)
            }
            jobs.add(job)
        }
        jobs.forEach { it.join() }
    }
}

fun Activity.hideKeyboard(button: Button) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(button.windowToken, 0)
}

