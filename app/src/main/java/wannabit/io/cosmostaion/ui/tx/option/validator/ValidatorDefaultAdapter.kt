package wannabit.io.cosmostaion.ui.tx.option.validator

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.initia.mstaking.v1.StakingProto
import com.zrchain.validation.HybridValidationProto.ValidatorHV
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInitia
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.databinding.ItemValidatorDefaultBinding

class ValidatorDefaultAdapter(
    private val selectedChain: BaseChain
) : ListAdapter<Any, ValidatorDefaultViewHolder>(ValidatorDefaultDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValidatorDefaultViewHolder {
        val binding =
            ItemValidatorDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ValidatorDefaultViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ValidatorDefaultViewHolder, position: Int) {
        val validator = currentList[position]
        when (selectedChain) {
            is ChainInitia -> {
                holder.initiaBind(selectedChain, validator as StakingProto.Validator)
                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(validator.operatorAddress)
                    }
                }
            }

            is ChainZenrock -> {
                holder.zenrockBind(selectedChain, validator as ValidatorHV)
                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(validator.operatorAddress)
                    }
                }
            }

            else -> {
                holder.bind(selectedChain, validator as Validator)
                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(validator.operatorAddress)
                    }
                }
            }
        }
    }

    private class ValidatorDefaultDiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}
