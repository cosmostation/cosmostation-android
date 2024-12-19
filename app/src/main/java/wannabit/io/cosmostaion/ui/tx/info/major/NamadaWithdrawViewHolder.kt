package wannabit.io.cosmostaion.ui.tx.info.major

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.NAMADA_MAIN_DENOM
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.setImageFromSvg
import wannabit.io.cosmostaion.common.setImg
import wannabit.io.cosmostaion.databinding.ItemNamadaWithdrawBinding
import java.math.BigDecimal

class NamadaWithdrawViewHolder(
    val context: Context, private val binding: ItemNamadaWithdrawBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        chain: BaseChain,
        validator: JsonObject,
        withdraw: JsonObject,
        listener: NamadaBondingInfoAdapter.ClickListener
    ) {
        binding.apply {
            withdrawView.setBackgroundResource(R.drawable.item_bg)
            withdrawView.setOnClickListener {

            }

            if (validator["avatar"].isJsonNull) {
                monikerImg.setImg(R.drawable.icon_default_vaildator)
            } else {
                monikerImg.setImageFromSvg(
                    validator["avatar"].asString, R.drawable.icon_default_vaildator
                )
            }
            moniker.text = if (!validator["name"].isJsonNull) {
                validator["name"].asString
            } else {
                validator["address"].asString
            }
            if (validator["state"].asString == "consensus") {
                jailedImg.visibility = View.GONE
            } else {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_inactive)
            }

            BaseData.getAsset(chain.apiName, NAMADA_MAIN_DENOM)?.let { asset ->
                val stakedAmount = withdraw["minDenomAmount"].asString.toBigDecimal()
                    .movePointLeft(asset.decimals ?: 6) ?: BigDecimal.ZERO
                withdrawAmount.text =
                    formatAmount(stakedAmount.toPlainString(), asset.decimals ?: 6)
            }
        }
    }
}