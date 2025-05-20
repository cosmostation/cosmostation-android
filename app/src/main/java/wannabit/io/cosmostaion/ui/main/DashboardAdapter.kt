package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemDashBinding
import wannabit.io.cosmostaion.databinding.ItemDefaultBottomHeaderBinding
import wannabit.io.cosmostaion.ui.qr.QrDialog
import wannabit.io.cosmostaion.ui.qr.QrEvmDialog

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
                val binding = ItemDefaultBottomHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                DashboardHeaderViewHolder(binding)
            }

            VIEW_TYPE_MAINNET_ITEM, VIEW_TYPE_TESTNET_ITEM -> {
                val binding =
                    ItemDashBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DashboardViewHolder(parent.context, binding)
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
                    if (Prefs.style == 0) {
                        holder.bind(chain)
                    } else {
                        holder.proBind(chain)
                    }

                    holder.itemView.setOnClickListener {
                        listener.nodeDown(chain)
                    }

                    holder.itemView.setOnLongClickListener { view ->
                        val scaleX = view.scaleX
                        val scaleY = view.scaleY
                        if (chain.fetchState == FetchState.SUCCESS) {
                            val dialog = if (chain.isEvmCosmos()) {
                                QrEvmDialog(context, chain)
                            } else {
                                QrDialog(context, chain)
                            }

                            if (scaleX == 1.0f && scaleY == 1.0f) {
                                view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start()
                                val handler = Handler()
                                handler.postDelayed({
                                    dialog.show()
                                }, 200)
                            }

                            dialog.setOnDismissListener {
                                view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start()
                            }
                            true

                        } else {
                            false
                        }
                    }

                } else {
                    val testnet = displayTestnetChains[position - (displayMainnetChains.size + 2)]
                    if (Prefs.style == 0) {
                        holder.testnetBind(testnet)
                    } else {
                        holder.testnetProBind(testnet)
                    }
                    holder.itemView.setOnClickListener {
                        listener.nodeDown(testnet)
                    }

                    holder.itemView.setOnLongClickListener { view ->
                        if (testnet.fetchState == FetchState.SUCCESS) {
                            val scaleX = view.scaleX
                            val scaleY = view.scaleY
                            val dialog = if (testnet.isEvmCosmos()) {
                                QrEvmDialog(context, testnet)
                            } else {
                                QrDialog(context, testnet)
                            }

                            if (scaleX == 1.0f && scaleY == 1.0f) {
                                view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start()
                                val handler = Handler()
                                handler.postDelayed({
                                    dialog.show()
                                }, 200)
                            }

                            dialog.setOnDismissListener {
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
        private val binding: ItemDefaultBottomHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                if (getItemViewType(position) == VIEW_TYPE_MAINNET_HEADER) {
                    headerTitle.text = "Mainnet"
                    headerCnt.text = displayMainnetChains.size.toString()

                } else {
                    headerTitle.text = "Testnet"
                    headerCnt.text = displayTestnetChains.size.toString()
                }
            }
        }
    }

    interface NodeDownListener {
        fun nodeDown(chain: BaseChain)
    }
}