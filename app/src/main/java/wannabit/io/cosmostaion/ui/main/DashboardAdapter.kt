package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.databinding.ItemDashBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding
import wannabit.io.cosmostaion.ui.qr.QrDialog

class DashboardAdapter(
    val context: Context,
    private val displayEvmLines: MutableList<EthereumLine>,
    private val displayCosmosLines: MutableList<CosmosLine>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ETHEREUM_HEADER = 0
        const val VIEW_TYPE_ETHEREUM_ITEM = 1
        const val VIEW_TYPE_COSMOS_HEADER = 2
        const val VIEW_TYPE_COSMOS_ITEM = 3
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ETHEREUM_HEADER, VIEW_TYPE_COSMOS_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                DashboardHeaderViewHolder(binding)
            }

            VIEW_TYPE_ETHEREUM_ITEM, VIEW_TYPE_COSMOS_ITEM -> {
                val binding =
                    ItemDashBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
                if (holder.itemViewType == VIEW_TYPE_ETHEREUM_ITEM) {
                    val line = displayEvmLines[position - 1]
                    holder.evmBind(line)

                    holder.itemView.setOnClickListener {
                        if (line.fetched) onItemClickListener?.let { it(line.tag) }
                        else return@setOnClickListener
                    }

                    holder.itemView.setOnLongClickListener { view ->
                        if (line.fetched) {
                            val scaleX = view.scaleX
                            val scaleY = view.scaleY
                            val customDialog = QrDialog(context, line, null)

                            if (scaleX == 1.0f && scaleY == 1.0f) {
                                view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start()
                                val handler = Handler()
                                handler.postDelayed({
                                    customDialog.show()
                                }, 200)
                            }

                            customDialog.setOnDismissListener {
                                view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start()
                            }
                            true

                        } else {
                            false
                        }
                    }

                } else {
                    val line = displayCosmosLines[position - (displayEvmLines.size + 2)]
                    holder.bind(line)

                    holder.itemView.setOnClickListener {
                        if (line.fetched) onItemClickListener?.let { it(line.tag) }
                        else return@setOnClickListener
                    }

                    holder.itemView.setOnLongClickListener { view ->
                        if (line.fetched) {
                            val scaleX = view.scaleX
                            val scaleY = view.scaleY
                            val customDialog = QrDialog(context, null, line)

                            if (scaleX == 1.0f && scaleY == 1.0f) {
                                view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start()
                                val handler = Handler()
                                handler.postDelayed({
                                    customDialog.show()
                                }, 200)
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
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_ETHEREUM_HEADER
        else if (position < displayEvmLines.size + 1) VIEW_TYPE_ETHEREUM_ITEM
        else if (position < displayEvmLines.size + 2) VIEW_TYPE_COSMOS_HEADER
        else VIEW_TYPE_COSMOS_ITEM
    }

    override fun getItemCount(): Int {
        return displayEvmLines.size + displayCosmosLines.size + 2
    }

    inner class DashboardHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                if (getItemViewType(position) == VIEW_TYPE_ETHEREUM_HEADER) {
                    headerTitle.text = context.getString(R.string.str_ethereum_class)
                    headerCnt.text = displayEvmLines.size.toString()

                } else {
                    headerTitle.text = context.getString(R.string.str_cosmos_class)
                    headerCnt.text = displayCosmosLines.size.toString()
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}