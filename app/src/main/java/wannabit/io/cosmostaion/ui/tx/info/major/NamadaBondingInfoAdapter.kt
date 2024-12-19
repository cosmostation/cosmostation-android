package wannabit.io.cosmostaion.ui.tx.info.major

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainNamada
import wannabit.io.cosmostaion.databinding.ItemNamadaUnstakingBinding
import wannabit.io.cosmostaion.databinding.ItemNamadaWithdrawBinding
import wannabit.io.cosmostaion.databinding.ItemStakingInfoBinding
import wannabit.io.cosmostaion.databinding.ItemUnstakingHeaderBinding
import wannabit.io.cosmostaion.ui.tx.info.OptionType
import wannabit.io.cosmostaion.ui.tx.info.StakingViewHolder

class NamadaBondingInfoAdapter(
    val selectedChain: BaseChain,
    private val validators: MutableList<JsonObject>,
    private val delegations: MutableList<JsonElement>,
    private val unbondings: MutableList<JsonObject>,
    private val withdraws: MutableList<JsonObject>,
    private val optionType: OptionType,
    private var listener: ClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_WITHDRAW_HEADER = 0
        const val VIEW_TYPE_WITHDRAW_ITEM = 1
        const val VIEW_TYPE_UNBONDING_HEADER = 2
        const val VIEW_TYPE_UNBONDING_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (optionType) {
            OptionType.STAKE -> {
                val binding = ItemStakingInfoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                StakingViewHolder(parent.context, binding)
            }

            OptionType.UNSTAKE -> {
                when (viewType) {
                    VIEW_TYPE_WITHDRAW_HEADER, VIEW_TYPE_UNBONDING_HEADER -> {
                        val binding = ItemUnstakingHeaderBinding.inflate(
                            LayoutInflater.from(parent.context), parent, false
                        )
                        UnStakingHeaderViewHolder(binding)
                    }

                    VIEW_TYPE_WITHDRAW_ITEM -> {
                        val binding = ItemNamadaWithdrawBinding.inflate(
                            LayoutInflater.from(parent.context), parent, false
                        )
                        NamadaWithdrawViewHolder(parent.context, binding)
                    }

                    VIEW_TYPE_UNBONDING_ITEM -> {
                        val binding = ItemNamadaUnstakingBinding.inflate(
                            LayoutInflater.from(parent.context), parent, false
                        )
                        NamadaUnstakingViewHolder(parent.context, binding)
                    }

                    else -> throw IllegalArgumentException("Invalid view type")
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (optionType) {
            OptionType.STAKE -> {
                if (holder is StakingViewHolder) {
                    val delegation = delegations[position].asJsonObject
                    validators.firstOrNull { it["address"].asString == delegation["validator"].asJsonObject["address"].asString }
                        ?.let { validator ->
                            holder.namadaBind(
                                selectedChain as ChainNamada, validator, delegation, listener
                            )
                        }
                }
            }

            OptionType.UNSTAKE -> {
                when (holder) {
                    is UnStakingHeaderViewHolder -> {
                        holder.bind(position)
                    }

                    is NamadaWithdrawViewHolder -> {
                        val withdraw = withdraws[position - 1]
                        validators.firstOrNull { it["address"].asString == withdraw["validator"].asJsonObject["address"].asString }
                            ?.let { validator ->
                                holder.bind(selectedChain, validator, withdraw, listener)
                            }
                    }

                    is NamadaUnstakingViewHolder -> {
                        val index = if (withdraws.isNotEmpty()) {
                            position - withdraws.size - 2
                        } else {
                            position - 1
                        }
                        val unBond = unbondings[index]
                        validators.firstOrNull { it["address"].asString == unBond["validator"].asJsonObject["address"].asString }
                            ?.let { validator ->
                                holder.bind(selectedChain, validator, unBond)
                            }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (optionType == OptionType.UNSTAKE) {
            return if (withdraws.isNotEmpty() && unbondings.isEmpty()) {
                if (position == 0) VIEW_TYPE_WITHDRAW_HEADER
                else VIEW_TYPE_WITHDRAW_ITEM
            } else if (withdraws.isEmpty() && unbondings.isNotEmpty()) {
                if (position == 0) VIEW_TYPE_UNBONDING_HEADER
                else VIEW_TYPE_UNBONDING_ITEM
            } else {
                if (position == 0) VIEW_TYPE_WITHDRAW_HEADER
                else if (position < withdraws.size + 1) VIEW_TYPE_WITHDRAW_ITEM
                else if (position < withdraws.size + 2) VIEW_TYPE_UNBONDING_HEADER
                else VIEW_TYPE_UNBONDING_ITEM
            }
        }
        return -1
    }

    override fun getItemCount(): Int {
        return if (optionType == OptionType.STAKE) {
            delegations.size
        } else {
            if (withdraws.isNotEmpty() && unbondings.isEmpty()) {
                withdraws.size + 1
            } else if (withdraws.isEmpty() && unbondings.isNotEmpty()) {
                unbondings.size + 1
            } else {
                withdraws.size + unbondings.size + 2
            }
        }
    }

    inner class UnStakingHeaderViewHolder(
        private val binding: ItemUnstakingHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_WITHDRAW_HEADER) {
                    headerTitle.text = "Withdraw"
                    headerCnt.text = withdraws.size.toString()
                } else {
                    headerTitle.text = "UnStaking"
                    headerCnt.text = unbondings.size.toString()
                }
            }
        }
    }

    interface ClickListener {
        fun selectStakingAction()
        fun selectUnStakingCancelAction()
    }
}