package wannabit.io.cosmostaion.ui.option.tx.validator

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmos.staking.v1beta1.StakingProto.Validator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.databinding.ItemValidatorBinding

class ValidatorViewHolder(
    private val binding: ItemValidatorBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, validator: Validator) {
        binding.apply {
            validator.let { validator ->
                monikerImg.setMonikerImg(chain, validator.operatorAddress)
                monikerName.text = validator.description?.moniker
                if (validator.jailed) {
                    jailedImg.visibility = View.VISIBLE
                    jailedImg.setImageResource(R.drawable.icon_jailed)
                } else if (!validator.isActiveValidator(chain)) {
                    jailedImg.visibility = View.VISIBLE
                    jailedImg.setImageResource(R.drawable.icon_inactive)
                } else {
                    jailedImg.visibility = View.GONE
                }

                BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                    asset.decimals?.let { decimal ->
                        chain.cosmosFetcher?.cosmosDelegations?.firstOrNull { it.delegation.validatorAddress == validator.operatorAddress }?.let { delegation ->
                            val stakingAmount = delegation.balance.amount.toBigDecimal().movePointLeft(decimal)
                            stakedAmount.text = formatAmount(stakingAmount.toPlainString(), decimal)
                        }
                    }
                }
            }
        }
    }
}