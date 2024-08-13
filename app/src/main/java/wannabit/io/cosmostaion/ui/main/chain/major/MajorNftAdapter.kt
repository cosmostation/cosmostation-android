package wannabit.io.cosmostaion.ui.main.chain.major

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemNftBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.NftViewHolder

class MajorNftAdapter(val chain: BaseChain) :
    ListAdapter<JsonObject, NftViewHolder>(nftDiffCallback()) {

    private var onItemClickListener: ((BaseChain, JsonObject?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NftViewHolder {
        val binding = ItemNftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NftViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: NftViewHolder, position: Int) {
        val info = currentList[position]
        holder.suiBind(info)
//        holder.itemView.setOnClickListener {
//            onItemClickListener?.let {
//                val support = info.get("support")?.asBoolean ?: true
//                if (!support) {
//                    context.makeToast(
//                        context.getString(
//                            R.string.error_not_support_dapp, info["name"].asString ?: ""
//                        )
//                    )
//                    return@setOnClickListener
//                }
//                it(info["link"].asString)
//            }
//        }
    }

    private class nftDiffCallback : DiffUtil.ItemCallback<JsonObject>() {

        override fun areItemsTheSame(oldItem: JsonObject, newItem: JsonObject): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: JsonObject, newItem: JsonObject): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (BaseChain, JsonObject?) -> Unit) {
        onItemClickListener = listener
    }
}