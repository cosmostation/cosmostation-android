package wannabit.io.cosmostaion.ui.tx.info.kava

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.kava.cdp.v1beta1.GenesisProto
import com.squareup.picasso.Picasso
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.KAVA_MINT_IMG_URL
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.databinding.ItemOtherMintBinding
import java.math.BigDecimal
import java.math.RoundingMode

class MintOtherViewHolder(
    val context: Context,
    private val binding: ItemOtherMintBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        collateralParam: GenesisProto.CollateralParam?, listener: MintListAdapter.ClickListener
    ) {
        binding.apply {
            otherMintView.setBackgroundResource(R.drawable.item_bg)
            if (collateralParam != null) {
                otherMintView.setOnClickListener {
                    listener.otherMintClick(collateralParam.type)
                }

                Picasso.get().load(KAVA_MINT_IMG_URL + collateralParam.type + ".png").fit()
                    .into(marketImg)
                marketType.text = collateralParam.type.uppercase()
                mintType.text = collateralParam.spotMarketId.uppercase()

                val liquidationRatio =
                    collateralParam.liquidationRatio.toBigDecimal().movePointLeft(16)
                        .setScale(2, RoundingMode.DOWN)
                val dpStabilityFee = dpStabilityFee(collateralParam).setScale(2, RoundingMode.DOWN)
                val dpLiquidationPenalty =
                    collateralParam.liquidationPenalty.toBigDecimal().movePointLeft(16)
                        .setScale(2, RoundingMode.DOWN)

                minCollateralRate.text = formatString(liquidationRatio.toPlainString() + "%", 3)
                stabilityFee.text = formatString(dpStabilityFee.toPlainString() + "%", 3)
                liquidationPenalty.text =
                    formatString(dpLiquidationPenalty.toPlainString() + "%", 3)
            }
        }
    }
}

fun dpStabilityFee(collateralParam: GenesisProto.CollateralParam): BigDecimal {
    return collateralParam.stabilityFee.toBigDecimal().movePointLeft(18).subtract(BigDecimal.ONE)
        .multiply(BigDecimal("31536000")).movePointRight(2)
}