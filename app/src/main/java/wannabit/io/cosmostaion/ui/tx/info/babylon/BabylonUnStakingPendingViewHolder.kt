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
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.databinding.ItemBabylonUnstakingPendingBinding
import java.math.RoundingMode

class BabylonUnStakingPendingViewHolder(
    val context: Context, private val binding: ItemBabylonUnstakingPendingBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        chain: BaseChain,
        epoch: Long?,
        validator: Validator,
        babylonEpochData: BabylonFetcher.BabylonEpochTxType
    ) {
        binding.apply {
            unstakingView.setBackgroundResource(R.drawable.item_bg)
            pendingImg.setColorFilter(
                ContextCompat.getColor(context, R.color.color_blue), PorterDuff.Mode.SRC_IN
            )
            cautionImg.setColorFilter(
                ContextCompat.getColor(context, R.color.color_base03), PorterDuff.Mode.SRC_IN
            )

            monikerImg.setMonikerImg(chain, validator.operatorAddress)
            moniker.text = validator.description?.moniker
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

            BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                val unBondingAmount = babylonEpochData.coin?.amount?.toBigDecimal()
                    ?.movePointLeft(asset.decimals ?: 6)
                    ?.setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                unstaked.text = formatAmount(unBondingAmount.toString(), asset.decimals ?: 6)
            }
        }
    }

    fun notBind() {
        binding.apply {
            unstakingView.setBackgroundResource(R.drawable.item_bg)
            moniker.text = "Unknown"
        }
    }
}