package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemDashBinding
import wannabit.io.cosmostaion.databinding.ItemHeaderBinding

class DashboardAdapter(
    val context: Context,
    private val displayMainnetChains: MutableList<BaseChain>,
    private val displayTestnetChains: MutableList<BaseChain>,
    val listener: NodeDownListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_MAINNET_HEADER = 0
        const val VIEW_TYPE_MAINNET_ITEM = 1
        const val VIEW_TYPE_TESTNET_HEADER = 2
        const val VIEW_TYPE_TESTNET_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MAINNET_HEADER, VIEW_TYPE_TESTNET_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                DashboardHeaderViewHolder(binding)
            }

            VIEW_TYPE_MAINNET_ITEM, VIEW_TYPE_TESTNET_ITEM -> {
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
                if (holder.itemViewType == VIEW_TYPE_MAINNET_ITEM) {
                    val chain = displayMainnetChains[position - 1]
                    holder.bind(chain)

                    holder.itemView.setOnClickListener {
                        listener.nodeDown(chain)
                    }
//
//                    holder.itemView.setOnLongClickListener { view ->
//                        if (line.fetched) {
//                            val scaleX = view.scaleX
//                            val scaleY = view.scaleY
//                            val customDialog = if (line.supportCosmos) {
//                                QrEvmDialog(context, line)
//                            } else {
//                                QrDialog(context, line, null)
//                            }
//
//                            if (scaleX == 1.0f && scaleY == 1.0f) {
//                                view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start()
//                                val handler = Handler()
//                                handler.postDelayed({
//                                    customDialog.show()
//                                }, 200)
//                            }
//
//                            customDialog.setOnDismissListener {
//                                view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start()
//                            }
//                            true
//
//                        } else {
//                            false
//                        }
//                    }

                } else {
//                    val line = if (displayEvmLines.isNotEmpty()) {
//                        displayCosmosLines[position - (displayEvmLines.size + 2)]
//                    } else {
//                        displayCosmosLines[position - 1]
//                    }
//                    holder.bind(line)
//                    holder.itemView.setOnClickListener {
//                        listener.nodeDown(line)
//                    }
//
//                    holder.itemView.setOnLongClickListener { view ->
//                        if (line.fetched) {
//                            val scaleX = view.scaleX
//                            val scaleY = view.scaleY
//                            val customDialog = QrDialog(context, null, line)
//
//                            if (scaleX == 1.0f && scaleY == 1.0f) {
//                                view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start()
//                                val handler = Handler()
//                                handler.postDelayed({
//                                    customDialog.show()
//                                }, 200)
//                            }
//
//                            customDialog.setOnDismissListener {
//                                view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start()
//                            }
//                            true
//
//                        } else {
//                            false
//                        }
//                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (displayTestnetChains.isNotEmpty()) {
            if (position == 0) VIEW_TYPE_MAINNET_HEADER
            else if (position < displayMainnetChains.size + 1) VIEW_TYPE_MAINNET_ITEM
            else if (position < displayMainnetChains.size + 2) VIEW_TYPE_TESTNET_HEADER
            else VIEW_TYPE_TESTNET_ITEM
        } else {
            if (position == 0) VIEW_TYPE_MAINNET_HEADER
            else VIEW_TYPE_MAINNET_ITEM
        }
    }

    override fun getItemCount(): Int {
        return if (displayTestnetChains.isNotEmpty()) {
            displayMainnetChains.size + displayTestnetChains.size + 2
        } else {
            displayMainnetChains.size + 1
        }
    }

    inner class DashboardHeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                if (getItemViewType(position) == VIEW_TYPE_MAINNET_HEADER) {
                    headerTitle.text = context.getString(R.string.str_mainnet)
                    headerCnt.text = displayMainnetChains.size.toString()

                } else {
                    headerTitle.text = context.getString(R.string.str_testnet)
                    headerCnt.text = displayTestnetChains.size.toString()
                }
            }
        }
    }

    interface NodeDownListener {
        fun nodeDown(chain: BaseChain)
    }
}