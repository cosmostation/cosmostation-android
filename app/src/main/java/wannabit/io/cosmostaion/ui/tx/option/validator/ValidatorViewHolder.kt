package wannabit.io.cosmostaion.ui.tx.option.validator

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.initia.mstaking.v1.StakingProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.databinding.ItemValidatorBinding
import java.math.RoundingMode

class ValidatorViewHolder(
    private val binding: ItemValidatorBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, validator: Validator) {
        binding.apply {
            validator.let { validator ->
                monikerImg.setMonikerImg(chain, validator.operatorAddress)
                monikerName.text = validator.description?.moniker?.trim()
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
                        chain.cosmosFetcher?.cosmosDelegations?.firstOrNull { it.delegation.validatorAddress == validator.operatorAddress }
                            ?.let { delegation ->
                                val stakingAmount =
                                    delegation.balance.amount.toBigDecimal().movePointLeft(decimal)
                                        .setScale(decimal, RoundingMode.DOWN)
                                stakedAmount.text =
                                    formatAmount(stakingAmount.toPlainString(), decimal)
                            }
                    }
                }
            }
        }
    }

    fun initiaBind(
        chain: ChainInitiaTestnet, validator: StakingProto.Validator
    ) {
        binding.apply {
            monikerImg.setMonikerImg(chain, validator.operatorAddress)
            monikerName.text = validator.description?.moniker?.trim()
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
                chain.initiaFetcher()?.initiaDelegations?.firstOrNull { it.delegation.validatorAddress == validator.operatorAddress }
                    ?.let { delegation ->
                        val stakingAmount =
                            delegation.balanceList?.firstOrNull { it.denom == chain.stakeDenom }?.amount?.toBigDecimal()
                                ?.movePointLeft(asset.decimals ?: 6)
                                ?.setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                        stakedAmount.text =
                            formatAmount(stakingAmount.toString(), asset.decimals ?: 6)
                    }
            }
        }
    }

    fun zenrockBind(
        chain: ChainZenrock, validator: com.zrchain.validation.HybridValidationProto.ValidatorHV
    ) {
        binding.apply {
            monikerImg.setMonikerImg(chain, validator.operatorAddress)
            monikerName.text = validator.description?.moniker?.trim()
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
                chain.zenrockFetcher()?.zenrockDelegations?.firstOrNull { it.delegation.validatorAddress == validator.operatorAddress }
                    ?.let { delegation ->
                        val stakingAmount = delegation.balance.amount.toBigDecimal()
                            .movePointLeft(asset.decimals ?: 6)
                            .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                        stakedAmount.text =
                            formatAmount(stakingAmount.toPlainString(), asset.decimals ?: 6)
                    }
            }
        }
    }
}