package wannabit.io.cosmostaion.ui.option.tx.validator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cosmos.staking.v1beta1.StakingProto
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.ItemValidatorDefaultBinding

class ValidatorDefaultAdapter(
    private val selectedChain: CosmosLine
) : ListAdapter<StakingProto.Validator, ValidatorDefaultViewHolder>(ValidatorDefaultDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValidatorDefaultViewHolder {
        val binding = ItemValidatorDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ValidatorDefaultViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ValidatorDefaultViewHolder, position: Int) {
        val validator = currentList[position]
        holder.bind(selectedChain, validator)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(validator.operatorAddress)
            }
        }
    }

    private class ValidatorDefaultDiffCallback : DiffUtil.ItemCallback<StakingProto.Validator>() {

        override fun areItemsTheSame(oldItem: StakingProto.Validator, newItem: StakingProto.Validator): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: StakingProto.Validator, newItem: StakingProto.Validator): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}
