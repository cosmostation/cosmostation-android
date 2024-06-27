package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.formatTxTime
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.databinding.ItemHistoryBinding

class HistoryAdapter(
    val context: Context, val chain: BaseChain
) : ListAdapter<Any, HistoryViewHolder>(HistoryDiffCallback()) {

    private var onItemClickListener: ((CosmosLine, CosmosHistory?, String?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        when (chain) {
//            is ChainOkt996Keccak -> {
//                val oktHistoryList = currentList as MutableList<Pair<String, TransactionList>>
//                val historyOktGroup = oktHistoryList[position]
//
//                historyOktGroup.second.let { header ->
//                    val headerDate = dpTimeToYear(header.transactionTime.toLong())
//                    val headerIndex = oktHistoryList.indexOfFirst { it.first == headerDate }
//                    val headerCnt = oktHistoryList.filter { it.first == headerDate }.size
//                    holder.bindOktHistory(historyOktGroup, headerIndex, headerCnt, position)
//
//                    holder.itemView.setOnClickListener {
//                        onItemClickListener?.let {
//                            it(line, null, historyOktGroup.second.txId)
//                        }
//                    }
//                }
//            }
//
//            is ChainOktEvm -> {
//                val oktHistoryList = currentList as MutableList<Pair<String, TransactionList>>
//                val historyOktGroup = oktHistoryList[position]
//
//                historyOktGroup.second.let { header ->
//                    val headerDate = dpTimeToYear(header.transactionTime.toLong())
//                    val headerIndex = oktHistoryList.indexOfFirst { it.first == headerDate }
//                    val headerCnt = oktHistoryList.filter { it.first == headerDate }.size
//                    holder.bindOktHistory(historyOktGroup, headerIndex, headerCnt, position)
//
//                    holder.itemView.setOnClickListener {
//                        onItemClickListener?.let {
//                            it(line, null, historyOktGroup.second.txId)
//                        }
//                    }
//                }
//            }

            else -> {
                val cosmosHistoryList = currentList as MutableList<Pair<String, CosmosHistory>>
                val historyGroup = cosmosHistoryList[position]

//                historyGroup.second.header?.let { header ->
//                    val headerDate = formatTxTime(context, header.timestamp)
//                    val headerIndex = cosmosHistoryList.indexOfFirst { it.first == headerDate }
//                    val headerCnt = cosmosHistoryList.filter { it.first == headerDate }.size
//                    holder.bindHistory(line, historyGroup, headerIndex, headerCnt, position)
//
//                    holder.itemView.setOnClickListener {
//                        onItemClickListener?.let {
//                            it(line, historyGroup.second, historyGroup.second.data?.txhash)
//                        }
//                    }
//                }
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

    fun setOnItemClickListener(listener: (CosmosLine, CosmosHistory?, String?) -> Unit) {
        onItemClickListener = listener
    }
}