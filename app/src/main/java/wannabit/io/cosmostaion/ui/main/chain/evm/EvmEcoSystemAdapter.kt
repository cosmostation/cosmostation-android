package wannabit.io.cosmostaion.ui.main.chain.evm

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.evmClass.ChainEthereum
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.ItemEcoHeaderBinding
import wannabit.io.cosmostaion.databinding.ItemEcoSystemBinding

class EvmEcoSystemAdapter(val context: Context, private val selectedEvmChain: BaseChain) :
    ListAdapter<JsonObject, RecyclerView.ViewHolder>(EcoSystemDiffCallback()) {

    private var onItemClickListener: ((String?) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_INJECT_HEADER = 0
        const val VIEW_TYPE_INJECT_ITEM = 1
        const val VIEW_TYPE_DAPP_HEADER = 2
        const val VIEW_TYPE_DAPP_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_INJECT_HEADER, VIEW_TYPE_DAPP_HEADER -> {
                val binding = ItemEcoHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                InjectTitleViewHolder(binding)
            }

            VIEW_TYPE_INJECT_ITEM, VIEW_TYPE_DAPP_ITEM -> {
                val binding =
                    ItemEcoSystemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EvmEcoSystemViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InjectTitleViewHolder -> {
                holder.bind(position)
            }

            is EvmEcoSystemViewHolder -> {
                if (selectedEvmChain is ChainEthereum) {
                    if (holder.itemViewType == VIEW_TYPE_INJECT_ITEM) {
                        val info = currentList[position - 1]
                        holder.bind(info)
                        holder.itemView.setOnClickListener {
                            onItemClickListener?.let {
                                it(info["link"].asString)
                            }
                        }

                    } else {
                        val info = currentList[position - 2]
                        holder.bind(info)
                        holder.itemView.setOnClickListener {
                            onItemClickListener?.let {
                                val support = info.get("support")?.asBoolean ?: true
                                if (!support) {
                                    context.makeToast(
                                        context.getString(
                                            R.string.error_not_support_dapp,
                                            info["name"].asString ?: ""
                                        )
                                    )
                                    return@setOnClickListener
                                }
                                it(info["link"].asString)
                            }
                        }
                    }

                } else {
                    val info = currentList[position]
                    holder.bind(info)
                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            val support = info.get("support")?.asBoolean ?: true
                            if (!support) {
                                context.makeToast(
                                    context.getString(
                                        R.string.error_not_support_dapp,
                                        info["name"].asString ?: ""
                                    )
                                )
                                return@setOnClickListener
                            }
                            it(info["link"].asString)
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (selectedEvmChain is ChainEthereum) {
            currentList.size + 2
        } else {
            currentList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (selectedEvmChain is ChainEthereum) {
            when (position) {
                0 -> VIEW_TYPE_INJECT_HEADER
                1, 2 -> VIEW_TYPE_INJECT_ITEM
                3 -> VIEW_TYPE_DAPP_HEADER
                else -> VIEW_TYPE_DAPP_ITEM
            }
        } else {
            VIEW_TYPE_DAPP_ITEM
        }
    }

    inner class InjectTitleViewHolder(
        private val binding: ItemEcoHeaderBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                if (getItemViewType(position) == VIEW_TYPE_INJECT_HEADER) {
                    headerTitle.text = "Injection Example Guide"
                    headerCnt.text = "(2)"
                } else {
                    headerTitle.text = "Dapp"
                    headerCnt.text = "(${currentList.size - 2})"
                }
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