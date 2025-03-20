package wannabit.io.cosmostaion.ui.tx.info.babylon

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.BabylonFetcher
import wannabit.io.cosmostaion.databinding.ItemBabylonUnstakingBinding
import wannabit.io.cosmostaion.databinding.ItemBabylonUnstakingPendingBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding
import wannabit.io.cosmostaion.ui.tx.info.UnBondingEntry

class BabylonUnStakingAdapter(
    val context: Context,
    private val selectedChain: BaseChain,
    private val epoch: Long?,
    private val babylonEpochTxType: MutableList<BabylonFetcher.BabylonEpochTxType>? = mutableListOf(),
    private val validators: MutableList<StakingProto.Validator>? = mutableListOf(),
    private val unBondings: MutableList<UnBondingEntry>? = mutableListOf(),
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
                UnStakingHeaderViewHolder(binding)
            }

            VIEW_TYPE_PENDING_ITEM -> {
                val binding = ItemBabylonUnstakingPendingBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                BabylonUnStakingPendingViewHolder(context, binding)
            }

            VIEW_TYPE_ACTIVE_ITEM -> {
                val binding = ItemBabylonUnstakingBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                BabylonUnStakingViewHolder(context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UnStakingHeaderViewHolder -> {
                holder.bind(holder.itemViewType)
            }

            is BabylonUnStakingPendingViewHolder -> {
                if (babylonEpochTxType?.isNotEmpty() == true) {
                    val epochTxType = babylonEpochTxType[position - 1]
                    validators?.firstOrNull { it.operatorAddress == epochTxType.validator }
                        ?.let { validator ->
                            holder.bind(
                                selectedChain, epoch, validator, epochTxType
                            )
                        } ?: run {
                        holder.notBind()
                    }
                }
            }

            is BabylonUnStakingViewHolder -> {
                if (unBondings?.isNotEmpty() == true) {
                    val unBonding = if (babylonEpochTxType?.isNotEmpty() == true) {
                        unBondings[position - babylonEpochTxType.size - 2]
                    } else {
                        unBondings[position - 1]
                    }

                    validators?.firstOrNull { it.operatorAddress == unBonding.validatorAddress }
                        ?.let { validator ->
                            holder.bind(
                                selectedChain, validator, unBonding, listener
                            )
                        } ?: run {
                        holder.notBind()
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val hasEpochTx = !babylonEpochTxType.isNullOrEmpty()
        val hasDelegations = !unBondings.isNullOrEmpty()

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
        val hasDelegations = !unBondings.isNullOrEmpty()

        return when {
            hasEpochTx -> if (hasDelegations) (babylonEpochTxType?.size ?: 0) + (unBondings?.size
                ?: 0) + 2
            else (babylonEpochTxType?.size ?: 0) + 1

            else -> (unBondings?.size ?: 0) + 1
        }
    }

    inner class UnStakingHeaderViewHolder(
        private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_PENDING_HEADER) {
                    headerTitle.text = "Pending State"
                    headerCnt.text = babylonEpochTxType?.size.toString()
                } else {
                    headerTitle.text = "Active State"
                    headerCnt.text = unBondings?.size.toString()
                }
            }
        }
    }

    interface ClickListener {
        fun selectUnStakingAction(unBondingEntry: UnBondingEntry?)
    }
}