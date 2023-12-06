package wannabit.io.cosmostaion.ui.tx.step.okt

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.data.model.res.OktValidatorResponse
import wannabit.io.cosmostaion.databinding.ItemOktSelectValidatorBinding

class OktSelectValidatorAdapter(
    private val selectedChain: ChainOkt60
): ListAdapter<OktValidatorResponse, OktSelectValidatorViewHolder>(OktSelectValidatorDiffCallback()) {

    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OktSelectValidatorViewHolder {
        val binding = ItemOktSelectValidatorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OktSelectValidatorViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: OktSelectValidatorViewHolder, position: Int) {
        val myValidator = currentList[position]
        holder.bind(selectedChain, myValidator)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(position) }
        }
    }

    private class OktSelectValidatorDiffCallback : DiffUtil.ItemCallback<OktValidatorResponse>() {

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