package wannabit.io.cosmostaion.ui.tx.info.kava

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kava.cdp.v1beta1.GenesisProto
import com.kava.cdp.v1beta1.QueryProto.CDPResponse
import com.kava.pricefeed.v1beta1.QueryProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.UsdxLTV
import wannabit.io.cosmostaion.common.collateralUSDXAmount
import wannabit.io.cosmostaion.common.debtUsdxValue
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.kavaOraclePrice
import wannabit.io.cosmostaion.common.liquidationPrice
import wannabit.io.cosmostaion.databinding.ItemMyMintBinding
import java.math.RoundingMode

class MintMyViewHolder(
    val context: Context,
    private val binding: ItemMyMintBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        collateralParam: GenesisProto.CollateralParam?,
        priceFeed: QueryProto.QueryPricesResponse?,
        myCdp: CDPResponse?,
        listener: MintListAdapter.ClickListener
    ) {
        binding.apply {
            myMintView.setBackgroundResource(R.drawable.item_bg)
            if (collateralParam != null && priceFeed != null && myCdp != null) {
                myMintView.setOnClickListener {
                    listener.myMintClick(collateralParam.type)
                }

//                Picasso.get().load(KAVA_MINT_IMG_URL + collateralParam.type + ".png").fit()
//                    .into(marketImg)
                marketType.text = collateralParam.type.uppercase()
                mintType.text = collateralParam.spotMarketId.uppercase()

                currentPriceTitle.text =
                    context.getString(R.string.str_current_price, collateralParam.denom.uppercase())
                liquidationTitle.text = context.getString(
                    R.string.str_liquidation_price, collateralParam.denom.uppercase()
                )

                val collateralValue = myCdp.collateralUSDXAmount()
                val ltv = myCdp.UsdxLTV(collateralParam)
                val borrowedValue = myCdp.debtUsdxValue()
                myCollateral.text = formatAssetValue(collateralValue)
                myLtv.text = formatAssetValue(ltv)
                myDebt.text = formatAssetValue(borrowedValue)

                val currentPrice = priceFeed.kavaOraclePrice(collateralParam.liquidationMarketId)
                val liquidationPrice = myCdp.liquidationPrice(collateralParam)
                currentPriceTxt.text = formatAssetValue(currentPrice)
                liquidation.text = formatAssetValue(liquidationPrice)

                borrowedValue.divide(ltv, 18, RoundingMode.HALF_UP)?.movePointRight(2)
                    ?.setScale(2, RoundingMode.HALF_UP)?.let { riskRate ->
                        risk.text = formatAmount(riskRate.toPlainString(), 2)
                        if (riskRate.toFloat() <= 60) {
                            risk.setTextColor(
                                ContextCompat.getColorStateList(
                                    context, R.color.color_accent_green
                                )
                            )
                            liquidation.setTextColor(
                                ContextCompat.getColorStateList(
                                    context, R.color.color_accent_green
                                )
                            )
                        } else {
                            risk.setTextColor(
                                ContextCompat.getColorStateList(
                                    context, R.color.color_accent_red
                                )
                            )
                            liquidation.setTextColor(
                                ContextCompat.getColorStateList(
                                    context, R.color.color_accent_red
                                )
                            )
                        }
                    }
            }
        }
    }
}