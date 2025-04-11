package wannabit.io.cosmostaion.ui.tx.info.major

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
import wannabit.io.cosmostaion.common.addWeeksToMillis
import wannabit.io.cosmostaion.common.dateToLong
import wannabit.io.cosmostaion.common.dpTimeNotSecond
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.setProviderImg
import wannabit.io.cosmostaion.databinding.ItemBtcActiveBinding
import wannabit.io.cosmostaion.ui.tx.info.StakingInfoAdapter
import java.math.RoundingMode
import kotlin.math.roundToInt

class BtcActiveViewHolder(
    val context: Context, private val binding: ItemBtcActiveBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        chain: BaseChain,
        staked: Pair<JsonObject, FinalityProvider>,
        listener: BtcActiveInfoAdapter.ClickListener
    ) {
        binding.apply {
            (chain as ChainBitCoin86).apply {
                delegationView.setBackgroundResource(R.drawable.item_bg)
                clickImg.setColorFilter(
                    ContextCompat.getColor(context, R.color.color_base03), PorterDuff.Mode.SRC_IN
                )
                delegationView.setOnClickListener {
                    listener.selectStakingAction(staked)
                }
                val apiName = if (chain.isTestnet) "babylon-testnet" else "babylon"

                monikerImg.setProviderImg(
                    chain,
                    apiName,
                    staked.first["finality_provider_btc_pks_hex"].asJsonArray.first().asString
                )
                moniker.text = staked.second.provider.description.moniker ?: "Unknown"

                if (staked.second.provider.jailed) {
                    jailedImg.visibility = View.VISIBLE
                    jailedImg.setImageResource(R.drawable.icon_jailed)
                } else if (staked.second.votingPower == "0") {
                    jailedImg.visibility = View.VISIBLE
                    jailedImg.setImageResource(R.drawable.icon_inactive)
                } else {
                    jailedImg.visibility = View.GONE
                }

                val commissionRate =
                    staked.second.provider.commission?.toBigDecimal()?.movePointLeft(16)
                        ?.setScale(2, RoundingMode.DOWN)
                commission.text = formatString("$commissionRate%", 3)

                BaseData.getAssetWithSymbol(chain.apiName, chain.coinSymbol)?.let { asset ->
                    val amount =
                        staked.first["delegation_staking"].asJsonObject["staking_amount"].asLong.toBigDecimal()
                            .movePointLeft(asset.decimals ?: 8)
                            .setScale(asset.decimals ?: 8, RoundingMode.DOWN)
                    stakedAmount.text = formatAmount(amount.toPlainString(), asset.decimals ?: 8)
                }

                val blockPerHour = 6.0
                val stakingBlock = btcFetcher?.btcParams?.maxStakingTimeBlocks?.toDouble() ?: 0.0
                val stakingHour = stakingBlock / blockPerHour
                val btcStakingTimeLockWeeks = ((stakingHour / 24 / 7 / 5).roundToInt()) * 5

                try {
                    val inceptionTime =
                        staked.first["delegation_staking"].asJsonObject["bbn_inception_time"].asString
                    if (inceptionTime.isNotEmpty()) {
                        val baseDate = dateToLong(
                            context.getString(R.string.str_tx_time_grpc_format), inceptionTime
                        )
                        val updateDate = addWeeksToMillis(baseDate, btcStakingTimeLockWeeks)
                        timeRock.text = dpTimeNotSecond(updateDate)

                    } else {
                        timeRock.text = "-"
                    }

                } catch (e: Exception) {
                    timeRock.text = "-"
                }
            }
        }
    }
}