package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.ItemEcoSystemBinding

class EcoSystemAdapter(
    val line: CosmosLine
) : ListAdapter<JsonObject, EcoSystemViewHolder>(EcoSystemDiffCallback()) {

    private var onItemClickListener: ((String?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EcoSystemViewHolder {
        val binding =
            ItemEcoSystemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EcoSystemViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: EcoSystemViewHolder, position: Int) {
        val info = currentList[position]
        holder.bind(info)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(info["link"].asString)
            }
        }
    }

    private class EcoSystemDiffCallback : DiffUtil.ItemCallback<JsonObject>() {

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

    fun setOnItemClickListener(listener: (String?) -> Unit) {
        onItemClickListener = listener
    }
}