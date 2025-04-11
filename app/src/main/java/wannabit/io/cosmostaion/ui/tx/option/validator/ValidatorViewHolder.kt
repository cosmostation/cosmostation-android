package wannabit.io.cosmostaion.ui.tx.option.validator

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.google.gson.JsonObject
import com.initia.mstaking.v1.StakingProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.setProviderImg
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

    fun bitBind(
        chain: ChainBitCoin86, staked: Pair<JsonObject, FinalityProvider>
    ) {
        binding.apply {
            val apiName = if (chain.isTestnet) "babylon-testnet" else "babylon"
            monikerImg.setProviderImg(
                chain,
                apiName,
                staked.first["finality_provider_btc_pks_hex"].asJsonArray.first().asString
            )
            monikerName.text = staked.second.provider.description.moniker ?: "Unknown"

            if (staked.second.provider.jailed) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_jailed)
            } else if (staked.second.votingPower == "0") {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_inactive)
            } else {
                jailedImg.visibility = View.GONE
            }

            BaseData.getAssetWithSymbol(chain.apiName, chain.coinSymbol)?.let { asset ->
                val amount =
                    staked.first["delegation_staking"].asJsonObject["staking_amount"].asLong.toBigDecimal()
                        .movePointLeft(asset.decimals ?: 8)
                        .setScale(asset.decimals ?: 8, RoundingMode.DOWN)
                stakedAmount.text = formatAmount(amount.toPlainString(), asset.decimals ?: 8)
            }
        }
    }
}