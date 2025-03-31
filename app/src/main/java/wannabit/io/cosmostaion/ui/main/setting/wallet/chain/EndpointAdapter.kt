package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemEndpointBinding
import wannabit.io.cosmostaion.databinding.ItemEndpointHeaderViewBinding

class EndpointAdapter(
    private val fromChain: BaseChain?,
    private val grpcEndpoints: MutableList<Any>?,
    private val lcdEndpoints: MutableList<Any>,
    private val endpointType: EndPointType,
    val listener: EndpointListener?
) : ListAdapter<Any, RecyclerView.ViewHolder>(EndpointDiffCallback()) {

    companion object {
        const val VIEW_TYPE_GRPC_HEADER = 0
        const val VIEW_TYPE_GRPC_ITEM = 1
        const val VIEW_TYPE_LCD_HEADER = 2
        const val VIEW_TYPE_LCD_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_GRPC_HEADER, VIEW_TYPE_LCD_HEADER -> {
                val binding = ItemEndpointHeaderViewBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                EndpointHeaderViewHolder(binding)
            }

            VIEW_TYPE_GRPC_ITEM, VIEW_TYPE_LCD_ITEM -> {
                val binding = ItemEndpointBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                EndPointViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_GRPC_HEADER, VIEW_TYPE_LCD_HEADER -> {
                if (holder is EndpointHeaderViewHolder) {
                    holder.bind(getItemViewType(position))
                }
            }

            VIEW_TYPE_GRPC_ITEM, VIEW_TYPE_LCD_ITEM -> {
                when (endpointType) {
                    EndPointType.END_POINT_SUI -> {
                        if (holder is EndPointViewHolder) {
                            val endPoint = currentList[position - 1] as JsonObject
                            if (fromChain is ChainGnoTestnet) {
                                holder.rpcBind(fromChain, endPoint, listener)
                            } else {
                                holder.suiBind(fromChain, endPoint, listener)
                            }
                        }
                    }

                    EndPointType.END_POINT_EVM -> {
                        val endPoint = currentList[position - 1] as JsonObject
                        if (holder is EndPointViewHolder) {
                            holder.evmBind(fromChain, endPoint, listener)
                        }
                    }

                    EndPointType.END_POINT_COSMOS -> {
                        if (holder.itemViewType == VIEW_TYPE_GRPC_ITEM) {
                            val grpcEndpoint = grpcEndpoints?.get(position - 1) as JsonObject
                            if (holder is EndPointViewHolder) {
                                holder.bind(fromChain, grpcEndpoint, listener)
                            }

                        } else {
                            val lcdEndpoint = if (grpcEndpoints?.isEmpty() == true) {
                                lcdEndpoints[position - 1] as JsonObject
                            } else {
                                lcdEndpoints[position - grpcEndpoints!!.size - 2] as JsonObject
                            }
                            if (holder is EndPointViewHolder) {
                                holder.lcdBind(fromChain, lcdEndpoint, listener)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (endpointType == EndPointType.END_POINT_COSMOS) {
            if (grpcEndpoints?.isEmpty() == true) {
                if (position == 0) VIEW_TYPE_LCD_HEADER
                else VIEW_TYPE_LCD_ITEM

            } else if (lcdEndpoints.isEmpty()) {
                if (position == 0) VIEW_TYPE_GRPC_HEADER
                else VIEW_TYPE_GRPC_ITEM

            } else {
                if (position == 0) VIEW_TYPE_GRPC_HEADER
                else if (position < grpcEndpoints!!.size + 1) VIEW_TYPE_GRPC_ITEM
                else if (position < grpcEndpoints.size + 2) VIEW_TYPE_LCD_HEADER
                else VIEW_TYPE_LCD_ITEM
            }

        } else {
            if (position == 0) VIEW_TYPE_GRPC_HEADER
            else VIEW_TYPE_GRPC_ITEM
        }
    }

    override fun getItemCount(): Int {
        return if (endpointType == EndPointType.END_POINT_COSMOS) {
            if (grpcEndpoints?.isEmpty() == true || lcdEndpoints.isEmpty()) {
                currentList.size + 1
            } else {
                currentList.size + 2
            }
        } else {
            currentList.size + 1
        }
    }

    private class EndpointDiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }

    inner class EndpointHeaderViewHolder(
        private val binding: ItemEndpointHeaderViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_GRPC_HEADER) {
                    headerView.visibility = View.GONE
                    headerTitle.text = if (endpointType == EndPointType.END_POINT_EVM) {
                        "EVM RPC"
                    } else if (endpointType == EndPointType.END_POINT_SUI) {
                        "SUI RPC"
                    } else {
                        "GRPC"
                    }
                    apiImg.setImageResource(R.drawable.icon_grpc)
                    headerCnt.text = grpcEndpoints!!.size.toString()

                } else {
                    headerView.visibleOrGone(grpcEndpoints?.isNotEmpty() == true)
                    apiImg.setImageResource(R.drawable.icon_rest)
                    headerTitle.text = "REST"
                    headerCnt.text = lcdEndpoints.size.toString()
                }
            }
        }
    }

    interface EndpointListener {
        fun select(endpoint: String, gapTime: Double?)

        fun rpcSelect(endpoint: String, gapTime: Double?)

        fun lcdSelect(endpoint: String, gapTime: Double?)
    }
}