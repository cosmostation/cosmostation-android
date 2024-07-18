package wannabit.io.cosmostaion.ui.main.chain.evm

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.ItemEcoSystemBinding

class EvmEcoSystemAdapter(val context: Context) :
    ListAdapter<JsonObject, EvmEcoSystemViewHolder>(EcoSystemDiffCallback()) {

    private var onItemClickListener: ((String?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvmEcoSystemViewHolder {
        val binding =
            ItemEcoSystemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EvmEcoSystemViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: EvmEcoSystemViewHolder, position: Int) {
        val info = currentList[position]
        holder.bind(info)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                val support = info.get("support")?.asBoolean ?: true
                if (!support) {
                    context.makeToast(context.getString(R.string.error_not_support_dapp, info["name"].asString ?: ""))
                    return@setOnClickListener
                }
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