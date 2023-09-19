package wannabit.io.cosmostaion.ui.main.chain

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.common.formatTxTimeToYear
import wannabit.io.cosmostaion.data.model.BnbHistory
import wannabit.io.cosmostaion.data.model.CosmosHistory
import wannabit.io.cosmostaion.databinding.ItemHistoryBinding

class HistoryAdapter(
    val context: Context,
    val line: CosmosLine
) : ListAdapter<Any, HistoryViewHolder>(HistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        if (line is ChainBinanceBeacon) {
            val bnbHistoryList = currentList as MutableList<Pair<String, BnbHistory>>
            val historyBnbGroup = bnbHistoryList[position]

            historyBnbGroup.second.let { header ->
                header.timeStamp?.let { timeStamp ->
                    val headerDate = formatTxTimeToYear(context, timeStamp)
                    val headerIndex = bnbHistoryList.indexOfFirst { it.first == headerDate }
                    val headerCnt = bnbHistoryList.filter { it.first == headerDate }.size
                    holder.bindBnbHistory(line, historyBnbGroup, headerIndex, headerCnt, position)
                }
            }

        } else {
            val cosmosHistoryList = currentList as MutableList<Pair<String, CosmosHistory>>
            val historyGroup = cosmosHistoryList[position]

            historyGroup.second.header?.let { header ->
                val headerDate = formatTxTimeToYear(context, header.timestamp)
                val headerIndex = cosmosHistoryList.indexOfFirst { it.first == headerDate }
                val headerCnt = cosmosHistoryList.filter { it.first == headerDate }.size
                holder.bindHistory(line, historyGroup, headerIndex, headerCnt, position)
            }
        }
    }

    private class HistoryDiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return oldItem == newItem
        }
    }
}