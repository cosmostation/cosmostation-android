package wannabit.io.cosmostaion.ui.tx.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemStakingInfoBinding
import wannabit.io.cosmostaion.databinding.ItemUnstakingInfoBinding

class StakingInfoAdapter(
    val selectedChain: BaseChain,
    private val validators: MutableList<StakingProto.Validator>,
    private val delegations: MutableList<StakingProto.DelegationResponse>,
    private val unBondings: MutableList<UnBondingEntry>,
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
                    val delegation = delegations[position]
                    validators.firstOrNull { it.operatorAddress == delegation.delegation.validatorAddress }
                        ?.let { validator ->
                            holder.bind(
                                selectedChain,
                                validator,
                                delegation,
                                listener
                            )
                        }
                }
            }

            OptionType.UNSTAKE -> {
                if (holder is UnstakingViewHolder) {
                    val entry = unBondings[position]
                    validators.firstOrNull { it.operatorAddress == entry.validatorAddress }
                        ?.let { validator ->
                            holder.bind(
                                selectedChain,
                                validator,
                                entry,
                                listener
                            )

                        } ?: run {
                        holder.notBind()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (optionType == OptionType.STAKE) {
            delegations.size
        } else {
            unBondings.size
        }
    }

    interface ClickListener {
        fun selectStakingAction(validator: StakingProto.Validator?)
        fun selectUnStakingCancelAction(unBondingEntry: UnBondingEntry?)
    }
}