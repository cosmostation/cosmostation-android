package wannabit.io.cosmostaion.ui.tx.info.babylon

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto.Validator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.BabylonFetcher
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.databinding.ItemBabylonStakingPendingInfoBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BabylonStakingPendingViewHolder(
    val context: Context, private val binding: ItemBabylonStakingPendingInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        chain: BaseChain,
        epoch: Long?,
        validator: Validator,
        babylonEpochData: BabylonFetcher.BabylonEpochTxType
    ) {
        binding.apply {
            delegationView.setBackgroundResource(R.drawable.item_bg)
            pendingImg.setColorFilter(
                ContextCompat.getColor(context, R.color.color_blue), PorterDuff.Mode.SRC_IN
            )
            cautionImg.setColorFilter(
                ContextCompat.getColor(context, R.color.color_base03), PorterDuff.Mode.SRC_IN
            )

            monikerImg.setMonikerImg(chain, validator.operatorAddress)
            moniker.text = validator.description?.moniker?.trim()
            if (validator.jailed) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_jailed)
            } else if (!validator.isActiveValidator(chain)) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_inactive)
            } else {
                jailedImg.visibility = View.GONE
            }

            val epochType = when {
                babylonEpochData.type.contains("MsgWrappedDelegate") -> "Staking"
                babylonEpochData.type.contains("MsgWrappedUndelegate") -> "Unstaking"
                babylonEpochData.type.contains("MsgWrappedBeginRedelegate") -> "Switch Validator"
                else -> "Unstaking Cancel"
            }
            epochMsg1.text = context.getString(R.string.str_epoch_type_msg1, epochType)
            epochMsg2.text = context.getString(R.string.str_epoch_type_msg2, "#${epoch?.plus(1)}")

            BaseData.getAsset(chain.apiName, chain.getMainAssetDenom())?.let { asset ->
                val commissionRate =
                    validator.commission?.commissionRates?.rate?.toBigDecimal()?.movePointLeft(16)
                        ?.setScale(2, RoundingMode.DOWN)
                commission.text = formatString("$commissionRate%", 3)

                val stakedAmount = babylonEpochData.coin?.amount?.toBigDecimal()
                    ?.movePointLeft(asset.decimals ?: 6)
                    ?.setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                staked.text = formatAmount(stakedAmount.toString(), asset.decimals ?: 6)

                rewardAmount.text = formatAmount(
                    BigDecimal.ZERO.movePointLeft(asset.decimals ?: 6).toPlainString(),
                    asset.decimals ?: 6
                )

                val apr =
                    chain.getChainParam()?.getAsJsonObject("params")?.get("apr")?.asString ?: "0"
                val staked = babylonEpochData.coin?.amount?.toBigDecimal()
                val comm = BigDecimal.ONE.subtract(
                    validator.commission?.commissionRates?.rate?.toBigDecimal()?.movePointLeft(18)
                        ?.setScale(18, RoundingMode.DOWN)
                )
                val est = staked?.multiply(apr.toBigDecimal())?.multiply(comm)
                    ?.setScale(0, RoundingMode.DOWN)?.divide(BigDecimal("12"), 0, RoundingMode.DOWN)
                    ?.movePointLeft(asset.decimals ?: 6)
                    ?.setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                estimateReward.text = formatAmount(est.toString(), asset.decimals ?: 6)
            }
        }
    }
}