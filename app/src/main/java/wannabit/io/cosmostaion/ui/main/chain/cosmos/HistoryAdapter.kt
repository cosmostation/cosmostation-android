package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.majorClass.ChainAptos
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainMovement
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.dpMicroTimeToYear
import wannabit.io.cosmostaion.common.dpTimeToYear
import wannabit.io.cosmostaion.common.formatTxTime
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.databinding.ItemHistoryBinding

class HistoryAdapter(
    val context: Context, val chain: BaseChain
) : ListAdapter<Any, HistoryViewHolder>(HistoryDiffCallback()) {

    private var onItemClickListener: ((BaseChain, CosmosHistory?, String?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        when (chain) {
            is ChainBitCoin86 -> {
                val bitHistoryList = currentList as MutableList<Pair<String, JsonObject>>
                val historyBitGroup = bitHistoryList[position]

                historyBitGroup.second.let { header ->
                    val headerDate = if (header["status"].asJsonObject["block_time"] != null) {
                        dpTimeToYear(header["status"].asJsonObject["block_time"].asLong * 1000)
                    } else {
                        "Mempool"
                    }
                    val headerIndex = bitHistoryList.indexOfFirst { it.first == headerDate }
                    val headerCnt = bitHistoryList.filter { it.first == headerDate }.size
                    holder.bindBitHistory(chain, historyBitGroup, headerIndex, headerCnt, position)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it(chain, null, historyBitGroup.second["txid"].asString)
                        }
                    }
                }
            }

            is ChainSui -> {
                val suiHistoryList = currentList as MutableList<Pair<String, JsonObject>>
                val historySuiGroup = suiHistoryList[position]

                historySuiGroup.second.let { header ->
                    val headerDate = dpTimeToYear(header["timestampMs"].asString.toLong())
                    val headerIndex = suiHistoryList.indexOfFirst { it.first == headerDate }
                    val headerCnt = suiHistoryList.filter { it.first == headerDate }.size
                    holder.bindSuiHistory(chain, historySuiGroup, headerIndex, headerCnt, position)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it(chain, null, historySuiGroup.second["digest"].asString)
                        }
                    }
                }
            }

            is ChainIota -> {
                val iotaHistoryList = currentList as MutableList<Pair<String, JsonObject>>
                val historySuiGroup = iotaHistoryList[position]

                historySuiGroup.second.let { header ->
                    val headerDate = dpTimeToYear(header["timestampMs"].asString.toLong())
                    val headerIndex = iotaHistoryList.indexOfFirst { it.first == headerDate }
                    val headerCnt = iotaHistoryList.filter { it.first == headerDate }.size
                    holder.bindIotaHistory(chain, historySuiGroup, headerIndex, headerCnt, position)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it(chain, null, historySuiGroup.second["digest"].asString)
                        }
                    }
                }
            }

            is ChainAptos, is ChainMovement -> {
                val moveHistoryList = currentList as MutableList<Pair<String, JsonObject>>
                val historyMoveGroup = moveHistoryList[position]

                historyMoveGroup.second.let { header ->
                    val headerDate = dpMicroTimeToYear(header["timestamp"].asString.toLong())
                    val headerIndex = moveHistoryList.indexOfFirst { it.first == headerDate }
                    val headerCnt = moveHistoryList.filter { it.first == headerDate }.size
                    holder.bindMoveHistory(chain, historyMoveGroup, headerIndex, headerCnt, position)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it(chain, null, historyMoveGroup.second["hash"].asString)
                        }
                    }
                }
            }

            else -> {
                if (chain.cosmosEndPointType == CosmosEndPointType.UNKNOWN && chain.supportEvm || chain is ChainOktEvm || chain is ChainOkt996Keccak) {
                    val ethHistoryList = currentList as MutableList<Pair<String, JsonObject>>
                    val historyGroup = ethHistoryList[position]

                    val headerDate =
                        dpTimeToYear(historyGroup.second.asJsonObject["txTime"].asString.toLong())
                    val headerIndex = ethHistoryList.indexOfFirst { it.first == headerDate }
                    val headerCnt = ethHistoryList.filter { it.first == headerDate }.size

                    holder.bindEthHistory(chain, historyGroup, headerIndex, headerCnt, position)

                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it(chain, null, historyGroup.second["txHash"].asString)
                        }
                    }

                } else {
                    val cosmosHistoryList = currentList as MutableList<Pair<String, CosmosHistory>>
                    val historyGroup = cosmosHistoryList[position]

                    historyGroup.second.header?.let { header ->
                        val headerDate = formatTxTime(context, header.timestamp)
                        val headerIndex = cosmosHistoryList.indexOfFirst { it.first == headerDate }
                        val headerCnt = cosmosHistoryList.filter { it.first == headerDate }.size
                        holder.bindHistory(chain, historyGroup, headerIndex, headerCnt, position)

                        holder.itemView.setOnClickListener {
                            onItemClickListener?.let {
                                it(chain, historyGroup.second, historyGroup.second.data?.txhash)
                            }
                        }
                    }
                }
            }
        }
    }

    private class HistoryDiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(
            oldItem: Any, newItem: Any
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: Any, newItem: Any
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (BaseChain, CosmosHistory?, String?) -> Unit) {
        onItemClickListener = listener
    }
}