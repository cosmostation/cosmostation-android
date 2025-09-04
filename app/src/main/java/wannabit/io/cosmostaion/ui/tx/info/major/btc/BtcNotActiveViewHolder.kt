package wannabit.io.cosmostaion.ui.tx.info.major.btc

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.setProviderImg
import wannabit.io.cosmostaion.databinding.ItemBtcNotActiveBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BtcNotActiveViewHolder(
    val context: Context, private val binding: ItemBtcNotActiveBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        chain: BaseChain,
        staked: Pair<JsonObject, FinalityProvider>?,
        listener: BtcNotActiveInfoAdapter.ClickListener
    ) {
        binding.apply {
            (chain as ChainBitCoin86).apply {
                delegationView.setBackgroundResource(R.drawable.item_bg)
                clickImg.setColorFilter(
                    ContextCompat.getColor(context, R.color.color_base03), PorterDuff.Mode.SRC_IN
                )
                val apiName = if (chain.isTestnet) "babylon-testnet" else "babylon"

                monikerImg.setProviderImg(
                    chain,
                    apiName,
                    staked?.first?.get("finality_provider_btc_pks_hex")?.asJsonArray?.first()?.asString
                )
                moniker.text = staked?.second?.provider?.description?.moniker ?: "Unknown"

                if (staked?.second?.provider?.jailed == true) {
                    jailedImg.visibility = View.VISIBLE
                    jailedImg.setImageResource(R.drawable.icon_jailed)
                } else if (staked?.second?.votingPower == "0") {
                    jailedImg.visibility = View.VISIBLE
                    jailedImg.setImageResource(R.drawable.icon_inactive)
                } else {
                    jailedImg.visibility = View.GONE
                }

                BaseData.getAsset(chain.apiName, chain.getMainAssetDenom())?.let { asset ->
                    val amount =
                        staked?.first?.get("delegation_staking")?.asJsonObject?.get("staking_amount")?.asLong?.toBigDecimal()
                            ?.movePointLeft(asset.decimals ?: 8)
                            ?.setScale(asset.decimals ?: 8, RoundingMode.DOWN) ?: BigDecimal.ZERO
                    stakedAmount.text = formatAmount(amount.toPlainString(), asset.decimals ?: 8)

                    val state = staked?.first?.get("state")?.asString
                    if (state == "EARLY_UNBONDING" || state == "TIMELOCK_UNBONDING") {
                        status.text = "Unstaking"
                        remainTime.text = "Takes around 7 days"
                        clickImg.visibility = View.GONE
                        statusImg.visibility = View.GONE

                    } else {
                        status.text = "Withdrawable"
                        remainTime.text = "Ready to Withdraw"
                        clickImg.visibility = View.VISIBLE
                        statusImg.visibility = View.VISIBLE

                        delegationView.setOnClickListener {
                            listener.selectWithdrawAction(staked)
                        }
                    }
                }
            }
        }
    }
}