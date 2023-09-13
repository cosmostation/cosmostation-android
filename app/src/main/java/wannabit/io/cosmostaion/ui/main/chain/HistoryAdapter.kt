package wannabit.io.cosmostaion.ui.main.chain

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.formatTxTimeToYear
import wannabit.io.cosmostaion.data.model.CosmosHistory
import wannabit.io.cosmostaion.databinding.ItemHistoryBinding

class HistoryAdapter(
    val context: Context,
    val line: CosmosLine
) : ListAdapter<Pair<String, CosmosHistory>, HistoryViewHolder>(HistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyGroup = currentList[position]
        historyGroup.second.header?.let { header ->
            val headerDate = formatTxTimeToYear(context, header.timestamp)
            val headerIndex = currentList.indexOfFirst { it.first == headerDate }
            val headerCnt = currentList.filter { it.first == headerDate }.size
            holder.bind(line, historyGroup, headerIndex, headerCnt, position)
        }
    }

    private class HistoryDiffCallback : DiffUtil.ItemCallback<Pair<String, CosmosHistory>>() {

        override fun areItemsTheSame(
            oldItem: Pair<String, CosmosHistory>,
            newItem: Pair<String, CosmosHistory>
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Pair<String, CosmosHistory>,
            newItem: Pair<String, CosmosHistory>
        ): Boolean {
            return oldItem == newItem
        }
    }
}