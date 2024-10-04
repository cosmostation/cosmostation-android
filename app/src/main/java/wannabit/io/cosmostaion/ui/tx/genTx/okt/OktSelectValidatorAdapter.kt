package wannabit.io.cosmostaion.ui.tx.genTx.okt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemOktSelectValidatorBinding

class OktSelectValidatorAdapter(
    private val selectedChain: BaseChain
): ListAdapter<JsonObject, OktSelectValidatorViewHolder>(OktSelectValidatorDiffCallback()) {

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

    private class OktSelectValidatorDiffCallback : DiffUtil.ItemCallback<JsonObject>() {

        override fun areItemsTheSame(oldItem: JsonObject, newItem: JsonObject): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: JsonObject, newItem: JsonObject): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}