package wannabit.io.cosmostaion.ui.tx.option.validator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.databinding.ItemValidatorDefaultBinding

class IotaValidatorAdapter :
    ListAdapter<JsonObject, ValidatorDefaultViewHolder>(ValidatorDefaultDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValidatorDefaultViewHolder {
        val binding =
            ItemValidatorDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ValidatorDefaultViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ValidatorDefaultViewHolder, position: Int) {
        val validator = currentList[position]
        holder.iotaBind(validator)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(validator["iotaAddress"].asString)
            }
        }
    }

    private class ValidatorDefaultDiffCallback : DiffUtil.ItemCallback<JsonObject>() {

        override fun areItemsTheSame(oldItem: JsonObject, newItem: JsonObject): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: JsonObject, newItem: JsonObject): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}