package wannabit.io.cosmostaion.ui.tx.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.databinding.ItemStakingInfoBinding
import wannabit.io.cosmostaion.databinding.ItemUnstakingInfoBinding

class StakingInfoAdapter(
    val selectedChain: BaseChain,
    private val validators: MutableList<StakingProto.Validator>? = mutableListOf(),
    private val delegations: MutableList<StakingProto.DelegationResponse>? = mutableListOf(),
    private val unBondings: MutableList<UnBondingEntry>? = mutableListOf(),
    private val initiaValidators: MutableList<com.initia.mstaking.v1.StakingProto.Validator>? = mutableListOf(),
    private val initiaDelegations: MutableList<com.initia.mstaking.v1.StakingProto.DelegationResponse>? = mutableListOf(),
    private val initiaUnBondings: MutableList<InitiaUnBondingEntry>? = mutableListOf(),
    private val zenrockValidators: MutableList<com.zrchain.validation.HybridValidationProto.ValidatorHV>? = mutableListOf(),
    private val zenrockDelegations: MutableList<com.zrchain.validation.StakingProto.DelegationResponse>? = mutableListOf(),
    private val zenrockUnBondings: MutableList<ZenrockUnBondingEntry>? = mutableListOf(),
    private val optionType: OptionType,
    private var listener: ClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (optionType) {
            OptionType.STAKE -> {
                val binding = ItemStakingInfoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                StakingViewHolder(parent.context, binding)
            }

            OptionType.UNSTAKE -> {
                val binding = ItemUnstakingInfoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                UnstakingViewHolder(parent.context, binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (optionType) {
            OptionType.STAKE -> {
                if (holder is StakingViewHolder) {
                    when (selectedChain) {
                        is ChainInitiaTestnet -> {
                            initiaDelegations?.get(position)?.let { delegation ->
                                initiaValidators?.firstOrNull { it.operatorAddress == delegation.delegation.validatorAddress }
                                    ?.let { validator ->
                                        holder.initiaBind(
                                            selectedChain, validator, delegation, listener
                                        )
                                    }
                            }
                        }

                        is ChainZenrock -> {
                            zenrockDelegations?.get(position)?.let { delegation ->
                                zenrockValidators?.firstOrNull { it.operatorAddress == delegation.delegation.validatorAddress }
                                    ?.let { validator ->
                                        holder.zenrockBind(
                                            selectedChain, validator, delegation, listener
                                        )
                                    }
                            }
                        }

                        else -> {
                            delegations?.get(position)?.let { delegation ->
                                validators?.firstOrNull { it.operatorAddress == delegation.delegation.validatorAddress }
                                    ?.let { validator ->
                                        holder.bind(
                                            selectedChain, validator, delegation, listener
                                        )
                                    }
                            }
                        }
                    }
                }
            }

            OptionType.UNSTAKE -> {
                if (holder is UnstakingViewHolder) {
                    when (selectedChain) {
                        is ChainInitiaTestnet -> {
                            initiaUnBondings?.get(position)?.let { entry ->
                                initiaValidators?.firstOrNull { it.operatorAddress == entry.validatorAddress }
                                    ?.let { validator ->
                                        holder.initiaBind(
                                            selectedChain, validator, entry, listener
                                        )
                                    } ?: run {
                                    holder.notBind()
                                }
                            }
                        }

                        is ChainZenrock -> {
                            zenrockUnBondings?.get(position)?.let { entry ->
                                zenrockValidators?.firstOrNull { it.operatorAddress == entry.validatorAddress }
                                    ?.let { validator ->
                                        holder.zenrockBind(
                                            selectedChain, validator, entry, listener
                                        )
                                    } ?: run {
                                    holder.notBind()
                                }
                            }
                        }

                        else -> {
                            unBondings?.get(position)?.let { entry ->
                                validators?.firstOrNull { it.operatorAddress == entry.validatorAddress }
                                    ?.let { validator ->
                                        holder.bind(
                                            selectedChain, validator, entry, listener
                                        )
                                    } ?: run {
                                    holder.notBind()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (optionType == OptionType.STAKE) {
            when (selectedChain) {
                is ChainInitiaTestnet -> initiaDelegations?.size ?: 0
                is ChainZenrock -> zenrockDelegations?.size ?: 0
                else -> delegations?.size ?: 0
            }

        } else {
            when (selectedChain) {
                is ChainInitiaTestnet -> initiaUnBondings?.size ?: 0
                is ChainZenrock -> zenrockUnBondings?.size ?: 0
                else -> unBondings?.size ?: 0
            }
        }
    }

    interface ClickListener {
        fun selectStakingAction(validator: StakingProto.Validator?)
        fun selectInitiaStakingAction(validator: com.initia.mstaking.v1.StakingProto.Validator?)
        fun selectZenrockStakingAction(validator: com.zrchain.validation.HybridValidationProto.ValidatorHV?)
        fun selectUnStakingCancelAction(unBondingEntry: UnBondingEntry?)
        fun selectInitiaUnStakingCancelAction(initiaUnBondingEntry: InitiaUnBondingEntry?)
        fun selectZenrockUnStakingCancelAction(zenrockUnBondingEntry: ZenrockUnBondingEntry?)
    }
}