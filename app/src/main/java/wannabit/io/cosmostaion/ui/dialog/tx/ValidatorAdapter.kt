package wannabit.io.cosmostaion.ui.dialog.tx

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cosmos.staking.v1beta1.StakingProto
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.ItemValidatorBinding

class ValidatorAdapter(
    private val selectedChain: CosmosLine
) : ListAdapter<StakingProto.Validator, ValidatorViewHolder>(ValidatorDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValidatorViewHolder {
        val binding = ItemValidatorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ValidatorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ValidatorViewHolder, position: Int) {
        val validator = currentList[position]
        holder.bind(selectedChain, validator)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(validator.operatorAddress)
            }
        }
    }

    private class ValidatorDiffCallback : DiffUtil.ItemCallback<StakingProto.Validator>() {

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
