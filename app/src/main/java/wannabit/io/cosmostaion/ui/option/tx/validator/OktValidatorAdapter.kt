package wannabit.io.cosmostaion.ui.option.tx.validator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.data.model.res.OktValidatorResponse
import wannabit.io.cosmostaion.databinding.ItemOktValidatorBinding

class OktValidatorAdapter(
    private val selectedChain: ChainOkt60,
    private val myValidators: MutableList<OktValidatorResponse>
) : ListAdapter<OktValidatorResponse, OktValidatorViewHolder>(OkValidatorDefaultDiffCallback()) {

    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OktValidatorViewHolder {
        val binding = ItemOktValidatorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OktValidatorViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: OktValidatorViewHolder, position: Int) {
        val validator = currentList[position]
        holder.bind(selectedChain, validator, myValidators)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(position) }
        }
    }

    private class OkValidatorDefaultDiffCallback : DiffUtil.ItemCallback<OktValidatorResponse>() {

        override fun areItemsTheSame(oldItem: OktValidatorResponse, newItem: OktValidatorResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: OktValidatorResponse, newItem: OktValidatorResponse): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}