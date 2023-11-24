package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.databinding.ItemDashBinding


class DashboardViewHolder(
    val context: Context,
    private val binding: ItemDashBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(line: CosmosLine) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_bg)

            when (line.chainType) {
                ChainType.COSMOS_TYPE -> {
                    chainImg.setImageResource(line.logo)
                    chainSwipeImg.setImageResource(line.swipeLogo)
                    chainName.text = line.name.uppercase()

                    if (line.evmCompatible) {
                        chainBadge.visibility = View.VISIBLE
                        chainBadge.text = context.getString(R.string.str_evm)
                        chainBadge.setBackgroundResource(R.drawable.round_box_evm)
                        chainBadge.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.color_base01
                            )
                        )
                    } else if (!line.isDefault) {
                        chainBadge.visibility = View.VISIBLE
                        chainBadge.text = context.getString(R.string.str_legacy)
                        chainBadge.setBackgroundResource(R.drawable.round_box_deprecated)
                        chainBadge.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.color_base02
                            )
                        )
                    } else {
                        chainBadge.visibility = View.GONE
                    }

                    if (line.fetched) {
                        chainValue.text = formatAssetValue(line.allValue())
                        skeletonChainValue.visibility = View.GONE
                    }
                }

                // eth, sui
                else -> {}
            }
        }
    }
}









