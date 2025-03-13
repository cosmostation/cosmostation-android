package wannabit.io.cosmostaion.ui.tx.info.babylon

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.BabylonFetcher
import wannabit.io.cosmostaion.databinding.ItemBabylonStakingPendingInfoBinding
import wannabit.io.cosmostaion.databinding.ItemStakingInfoBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding

class BabylonStakingAdapter(
    val context: Context,
    private val selectedChain: BaseChain,
    private val epoch: Long?,
    private val babylonEpochTxType: MutableList<BabylonFetcher.BabylonEpochTxType>? = mutableListOf(),
    private val validators: MutableList<StakingProto.Validator>? = mutableListOf(),
    private val delegations: MutableList<StakingProto.DelegationResponse>? = mutableListOf(),
    var listener: ClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_PENDING_HEADER = 0
        const val VIEW_TYPE_PENDING_ITEM = 1
        const val VIEW_TYPE_ACTIVE_HEADER = 2
        const val VIEW_TYPE_ACTIVE_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_PENDING_HEADER, VIEW_TYPE_ACTIVE_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                StakingHeaderViewHolder(binding)
            }

            VIEW_TYPE_PENDING_ITEM -> {
                val binding = ItemBabylonStakingPendingInfoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                BabylonStakingPendingViewHolder(context, binding)
            }

            VIEW_TYPE_ACTIVE_ITEM -> {
                val binding = ItemStakingInfoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                BabylonStakingViewHolder(context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StakingHeaderViewHolder -> {
                holder.bind(holder.itemViewType)
            }

            is BabylonStakingPendingViewHolder -> {
                if (babylonEpochTxType?.isNotEmpty() == true) {
                    val epochTxType = babylonEpochTxType[position - 1]
                    validators?.firstOrNull { it.operatorAddress == epochTxType.validator }
                        ?.let { validator ->
                            holder.bind(
                                selectedChain, epoch, validator, epochTxType
                            )
                        }
                }
            }

            is BabylonStakingViewHolder -> {
                if (delegations?.isNotEmpty() == true) {
                    val delegation = if (babylonEpochTxType?.isNotEmpty() == true) {
                        delegations[position - babylonEpochTxType.size - 2]
                    } else {
                        delegations[position - 1]
                    }
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

    override fun getItemViewType(position: Int): Int {
        val hasEpochTx = !babylonEpochTxType.isNullOrEmpty()
        val hasDelegations = !delegations.isNullOrEmpty()

        return when {
            hasEpochTx -> when {
                hasDelegations -> when (position) {
                    0 -> VIEW_TYPE_PENDING_HEADER
                    in 1 until (babylonEpochTxType?.size ?: 0) + 1 -> VIEW_TYPE_PENDING_ITEM
                    (babylonEpochTxType?.size ?: 0) + 1 -> VIEW_TYPE_ACTIVE_HEADER
                    else -> VIEW_TYPE_ACTIVE_ITEM
                }

                else -> if (position == 0) VIEW_TYPE_PENDING_HEADER else VIEW_TYPE_PENDING_ITEM
            }

            else -> if (position == 0) VIEW_TYPE_ACTIVE_HEADER else VIEW_TYPE_ACTIVE_ITEM
        }
    }

    override fun getItemCount(): Int {
        val hasEpochTx = !babylonEpochTxType.isNullOrEmpty()
        val hasDelegations = !delegations.isNullOrEmpty()

        return when {
            hasEpochTx -> if (hasDelegations) (babylonEpochTxType?.size ?: 0) + (delegations?.size
                ?: 0) + 2
            else (babylonEpochTxType?.size ?: 0) + 1

            else -> (delegations?.size ?: 0) + 1
        }
    }

    inner class StakingHeaderViewHolder(
        private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_PENDING_HEADER) {
                    headerTitle.text = "Pending State"
                    headerCnt.text = babylonEpochTxType?.size.toString()
                } else {
                    headerTitle.text = "Active State"
                    headerCnt.text = delegations?.size.toString()
                }
            }
        }
    }

    interface ClickListener {
        fun selectStakingAction(validator: StakingProto.Validator?)
    }
}