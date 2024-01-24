package wannabit.io.cosmostaion.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.ItemDashBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding
import wannabit.io.cosmostaion.ui.qr.QrDialog

class DashboardAdapter(
    val context: Context
) : ListAdapter<CosmosLine, RecyclerView.ViewHolder>(DashboardDiffCallback()) {

    companion object {
        const val VIEW_TYPE_COSMOS_HEADER = 0
        const val VIEW_TYPE_COSMOS_ITEM = 1
        const val VIEW_TYPE_COSMOS_ETHEREUM_ITEM = 2
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_COSMOS_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                DashboardHeaderViewHolder(binding)
            }

            VIEW_TYPE_COSMOS_ITEM -> {
                val binding = ItemDashBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DashboardViewHolder(context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DashboardHeaderViewHolder -> {
                holder.bind(position)
            }

            is DashboardViewHolder -> {
                val line = currentList[position - 1]
                holder.bind(line)

                holder.itemView.setOnClickListener {
                    if (line.fetched) onItemClickListener?.let { it(line.tag) }
                    else return@setOnClickListener
                }

                holder.itemView.setOnLongClickListener { view ->
                    if (line.fetched) {
                        val scaleX = view.scaleX
                        val scaleY = view.scaleY
                        val customDialog = QrDialog(context, line)

                        if (scaleX == 1.0f && scaleY == 1.0f) {
                            view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start()
                            val handler = Handler()
                            handler.postDelayed({
                                customDialog.show()
                            },200)
                        }

                        customDialog.setOnDismissListener {
                            view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start()
                        }
                        true

                    } else {
                        false
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_COSMOS_HEADER else VIEW_TYPE_COSMOS_ITEM
    }

    override fun getItemCount(): Int {
        return currentList.size + 1
    }

    class DashboardDiffCallback : DiffUtil.ItemCallback<CosmosLine>() {

        override fun areItemsTheSame(oldItem: CosmosLine, newItem: CosmosLine): Boolean {
            return oldItem.tag == newItem.tag
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CosmosLine, newItem: CosmosLine): Boolean {
            return oldItem == newItem
        }
    }

    inner class DashboardHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                if (getItemViewType(position) == VIEW_TYPE_COSMOS_HEADER) {
                    headerTitle.text = context.getString(R.string.str_cosmos_class)
                    headerCnt.text = currentList.size.toString()
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}