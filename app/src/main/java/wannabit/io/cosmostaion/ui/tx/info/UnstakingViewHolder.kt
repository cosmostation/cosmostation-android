package wannabit.io.cosmostaion.ui.tx.info

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.zrchain.validation.HybridValidationProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInitia
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpTime
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.gapTime
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.databinding.ItemUnstakingInfoBinding

class UnstakingViewHolder(
    val context: Context, private val binding: ItemUnstakingInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        chain: BaseChain,
        validator: Validator,
        entry: UnBondingEntry,
        listener: StakingInfoAdapter.ClickListener
    ) {
        binding.apply {
            unstakingView.setBackgroundResource(R.drawable.item_bg)
            clickImg.setColorFilter(
                ContextCompat.getColor(context, R.color.color_base03), PorterDuff.Mode.SRC_IN
            )
            unstakingView.setOnClickListener {
                listener.selectUnStakingCancelAction(entry)
            }

            monikerImg.setMonikerImg(chain, validator.operatorAddress)
            moniker.text = validator.description?.moniker?.trim()
            val statusImage = when {
                validator.jailed -> R.drawable.icon_jailed
                !validator.isActiveValidator(chain) -> R.drawable.icon_inactive
                else -> 0
            }
            jailedImg.visibility = if (statusImage != 0) View.VISIBLE else View.GONE
            jailedImg.setImageResource(statusImage)

            BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                asset.decimals?.let { decimal ->
                    val unBondingAmount =
                        entry.entry?.balance?.toBigDecimal()?.movePointLeft(decimal)
                    unstaked.text = formatAmount(unBondingAmount.toString(), decimal)

                    entry.entry?.completionTime?.let {
                        remainDay.text = gapTime(it.seconds * 1000)
                        unstakingDay.text = dpTime(it.seconds * 1000)
                    }
                }
            }
        }
    }

    fun initiaBind(
        chain: ChainInitia,
        validator: com.initia.mstaking.v1.StakingProto.Validator,
        entry: InitiaUnBondingEntry,
        listener: StakingInfoAdapter.ClickListener
    ) {
        binding.apply {
            unstakingView.setBackgroundResource(R.drawable.item_bg)
            unstakingView.setOnClickListener {
                listener.selectInitiaUnStakingCancelAction(entry)
            }

            monikerImg.setMonikerImg(chain, validator.operatorAddress)
            moniker.text = validator.description?.moniker?.trim()
            val statusImage = when {
                validator.jailed -> R.drawable.icon_jailed
                !validator.isActiveValidator(chain) -> R.drawable.icon_inactive
                else -> 0
            }
            jailedImg.visibility = if (statusImage != 0) View.VISIBLE else View.GONE
            jailedImg.setImageResource(statusImage)

            BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                asset.decimals?.let { decimal ->
                    val unBondingAmount =
                        entry.entry?.balanceList?.firstOrNull { it.denom == chain.stakeDenom }?.amount?.toBigDecimal()
                            ?.movePointLeft(decimal)
                    unstaked.text = formatAmount(unBondingAmount.toString(), decimal)

                    entry.entry?.completionTime?.let {
                        remainDay.text = gapTime(it.seconds * 1000)
                        unstakingDay.text = dpTime(it.seconds * 1000)
                    }
                }
            }
        }
    }

    fun zenrockBind(
        chain: ChainZenrock,
        validator: HybridValidationProto.ValidatorHV,
        entry: ZenrockUnBondingEntry,
        listener: StakingInfoAdapter.ClickListener
    ) {
        binding.apply {
            unstakingView.setBackgroundResource(R.drawable.item_bg)
            unstakingView.setOnClickListener {
                listener.selectZenrockUnStakingCancelAction(entry)
            }

            monikerImg.setMonikerImg(chain, validator.operatorAddress)
            moniker.text = validator.description?.moniker?.trim()
            val statusImage = when {
                validator.jailed -> R.drawable.icon_jailed
                !validator.isActiveValidator(chain) -> R.drawable.icon_inactive
                else -> 0
            }
            jailedImg.visibility = if (statusImage != 0) View.VISIBLE else View.GONE
            jailedImg.setImageResource(statusImage)

            BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                val unBondingAmount =
                    entry.entry?.balance?.toBigDecimal()?.movePointLeft(asset.decimals ?: 6)
                unstaked.text = formatAmount(unBondingAmount.toString(), asset.decimals ?: 6)

                entry.entry?.completionTime?.let {
                    remainDay.text = gapTime(it.seconds * 1000)
                    unstakingDay.text = dpTime(it.seconds * 1000)
                }
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