package wannabit.io.cosmostaion.ui.tx.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemRewardAddressBinding
import wannabit.io.cosmostaion.databinding.ItemStakingInfoBinding
import wannabit.io.cosmostaion.databinding.ItemUnstakingInfoBinding

class StakingInfoAdapter(
    val selectedChain: BaseChain,
    private val rewardAddress: String?,
    private val validators: MutableList<StakingProto.Validator>,
    private val delegations: MutableList<StakingProto.DelegationResponse>,
    private val unBondings: MutableList<UnBondingEntry>,
    private var listener: ClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_REWARD_ADDRESS = 0
        const val VIEW_TYPE_STAKING = 1
        const val VIEW_TYPE_UNSTAKING = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_REWARD_ADDRESS -> {
                val binding = ItemRewardAddressBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                RewardAddressViewHolder(parent.context, binding)
            }

            VIEW_TYPE_STAKING -> {
                val binding = ItemStakingInfoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                StakingViewHolder(parent.context, binding)
            }

            VIEW_TYPE_UNSTAKING -> {
                val binding = ItemUnstakingInfoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                UnstakingViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_REWARD_ADDRESS -> {
                if (holder is RewardAddressViewHolder) {
                    holder.bind(rewardAddress)
                }
            }

            VIEW_TYPE_STAKING -> {
                if (holder is StakingViewHolder) {
                    val delegationPosition: Int = if (rewardAddress != selectedChain.address) {
                        position - 1
                    } else {
                        position
                    }
                    val delegation = delegations[delegationPosition]
                    validators.firstOrNull { it.operatorAddress == delegation.delegation.validatorAddress }
                        ?.let { validator ->
                            holder.bind(
                                selectedChain,
                                validator,
                                delegation,
                                delegations.size,
                                delegationPosition,
                                listener
                            )
                        }
                }
            }

            VIEW_TYPE_UNSTAKING -> {
                if (holder is UnstakingViewHolder) {
                    val unStakingPosition: Int = if (rewardAddress != selectedChain.address) {
                        position - 1 - delegations.size
                    } else {
                        position - delegations.size
                    }
                    val entry = unBondings[unStakingPosition]
                    validators.firstOrNull { it.operatorAddress == entry.validatorAddress }
                        ?.let { validator ->
                            holder.bind(
                                selectedChain,
                                validator,
                                entry,
                                unBondings.size,
                                unStakingPosition,
                                listener
                            )
                        }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val isRewardAddress = rewardAddress != selectedChain.address

        return when {
            isRewardAddress && position == 0 -> VIEW_TYPE_REWARD_ADDRESS
            isRewardAddress && position < delegations.size + 1 -> VIEW_TYPE_STAKING
            !isRewardAddress && position < delegations.size -> VIEW_TYPE_STAKING
            else -> VIEW_TYPE_UNSTAKING
        }
    }

    override fun getItemCount(): Int {
        return if (rewardAddress != selectedChain.address) {
            delegations.size + unBondings.size + 1

        } else {
            delegations.size + unBondings.size
        }
    }

    interface ClickListener {
        fun selectStakingAction(validator: StakingProto.Validator?)
        fun selectUnStakingCancelAction(unBondingEntry: UnBondingEntry?)
    }
}