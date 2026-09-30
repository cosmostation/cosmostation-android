package wannabit.io.cosmostaion.ui.tx.option.validator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sui.rpc.v2.SystemStateProto
import wannabit.io.cosmostaion.databinding.ItemValidatorDefaultBinding

class SuiValidatorAdapter :
    ListAdapter<SystemStateProto.Validator, ValidatorDefaultViewHolder>(ValidatorDefaultDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValidatorDefaultViewHolder {
        val binding =
            ItemValidatorDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ValidatorDefaultViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ValidatorDefaultViewHolder, position: Int) {
        val validator = currentList[position]
        holder.suiBind(validator)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(validator.address)
            }
        }
    }

    private class ValidatorDefaultDiffCallback :
        DiffUtil.ItemCallback<SystemStateProto.Validator>() {

        override fun areItemsTheSame(
            oldItem: SystemStateProto.Validator,
            newItem: SystemStateProto.Validator
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SystemStateProto.Validator,
            newItem: SystemStateProto.Validator
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}