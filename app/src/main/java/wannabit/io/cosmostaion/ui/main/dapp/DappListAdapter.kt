package wannabit.io.cosmostaion.ui.main.dapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.databinding.ItemDappBinding

class DappListAdapter(
    val context: Context
) : ListAdapter<JsonObject, DappViewHolder>(DappListDiffCallback()) {

    private var onItemClickListener: ((JsonObject) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DappViewHolder {
        val binding = ItemDappBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DappViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: DappViewHolder, position: Int) {
        val ecosystem = currentList[position]
        holder.bind(ecosystem)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(ecosystem)
            }
        }
    }

    private class DappListDiffCallback : DiffUtil.ItemCallback<JsonObject>() {

        override fun areItemsTheSame(oldItem: JsonObject, newItem: JsonObject): Boolean {
            return oldItem["id"].asInt == newItem["id"].asInt
        }

        override fun areContentsTheSame(oldItem: JsonObject, newItem: JsonObject): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (JsonObject) -> Unit) {
        onItemClickListener = listener
    }
}