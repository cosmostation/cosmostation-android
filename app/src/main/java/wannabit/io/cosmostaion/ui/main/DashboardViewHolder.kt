package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemDashBinding


class DashboardViewHolder(
    val context: Context, private val binding: ItemDashBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun evmBind(line: EthereumLine) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_bg)

            chainImg.setImageResource(line.logo)
            chainSwipeImg.setImageResource(line.swipeLogo)
            chainName.text = line.name.uppercase()
            chainBadge.visibility = View.GONE
            chainTypeBadge.visibility = View.GONE
            chainCw20Badge.visibleOrGone(line.supportCw20)
            chainNftBadge.visibleOrGone(line.supportNft)

            if (line.fetched) {
                skeletonChainValue.visibility = View.GONE
                if (line !is ChainOktEvm) {
                    if (line.supportCosmos && line.cosmosBalances == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        return
                    }
                }

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        line.web3j()?.web3ClientVersion()?.sendAsync()?.get()?.web3ClientVersion
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            respondLayout.visibility = View.VISIBLE
                            chainValue.visibility = View.GONE
                            return@withContext
                        }
                    }
                }

                if (Prefs.hideValue) {
                    chainValue.text = "✱✱✱✱"
                    chainValue.textSize = 10f
                } else {
                    chainValue.text = formatAssetValue(line.allValue(false))
                    chainValue.textSize = 14f
                }
            }
        }
    }

    fun bind(line: CosmosLine) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_bg)

            chainImg.setImageResource(line.logo)
            chainSwipeImg.setImageResource(line.swipeLogo)
            chainName.text = line.name.uppercase()

            if (!line.isDefault) {
                chainBadge.visibility = View.VISIBLE
                chainBadge.setBackgroundResource(R.drawable.round_box_deprecated)
                chainBadge.setTextColor(
                    ContextCompat.getColor(
                        context, R.color.color_base02
                    )
                )
                chainBadge.text = context.getString(R.string.str_legacy)
                when (line.tag) {
                    "okt996_Keccak" -> {
                        chainTypeBadge.visibility = View.VISIBLE
                        chainTypeBadge.text = context.getString(R.string.str_ethsecp256k1)
                    }
                    "okt996_Secp" -> {
                        chainTypeBadge.visibility = View.VISIBLE
                        chainTypeBadge.text = context.getString(R.string.str_secp256k1)
                    }
                    else -> {
                        chainTypeBadge.visibility = View.GONE
                    }
                }

            } else {
                chainBadge.visibility = View.GONE
                chainTypeBadge.visibility = View.GONE
            }
            chainCw20Badge.visibleOrGone(line.supportCw20)
            chainNftBadge.visibleOrGone(line.supportNft)

            if (line.fetched) {
                skeletonChainValue.visibility = View.GONE

                if (line !is ChainOkt996Keccak && line !is ChainBinanceBeacon) {
                    if (line.cosmosBalances == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        return
                    }
                }

                if (Prefs.hideValue) {
                    chainValue.text = "✱✱✱✱"
                    chainValue.textSize = 10f
                } else {
                    chainValue.text = formatAssetValue(line.allValue(false))
                    chainValue.textSize = 14f
                }
            }
        }
    }
}









