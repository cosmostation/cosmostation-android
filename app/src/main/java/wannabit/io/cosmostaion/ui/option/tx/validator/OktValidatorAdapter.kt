package wannabit.io.cosmostaion.ui.option.tx.validator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemOktValidatorBinding

class OktValidatorAdapter(
    private val selectedChain: BaseChain, private val myValidators: MutableList<JsonObject>
) : ListAdapter<JsonObject, OktValidatorViewHolder>(OkValidatorDefaultDiffCallback()) {

    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OktValidatorViewHolder {
        val binding =
            ItemOktValidatorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OktValidatorViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: OktValidatorViewHolder, position: Int) {
        val validator = currentList[position]
        holder.bind(selectedChain, validator, myValidators)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(position) }
        }
    }

    private class OkValidatorDefaultDiffCallback : DiffUtil.ItemCallback<JsonObject>() {

        override fun areItemsTheSame(
            oldItem: JsonObject, newItem: JsonObject
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: JsonObject, newItem: JsonObject
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}