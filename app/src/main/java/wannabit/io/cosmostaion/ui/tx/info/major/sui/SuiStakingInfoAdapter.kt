package wannabit.io.cosmostaion.ui.tx.info.major.sui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemSuiStakingInfoBinding

class SuiStakingInfoAdapter(
    private val selectedChain: BaseChain
) : ListAdapter<Pair<String, JsonObject>, SuiStakingInfoViewHolder>(SuiStakingInfoDiffCallback()) {

    private var onItemClickListener: ((Pair<String, JsonObject>) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuiStakingInfoViewHolder {
        val binding =
            ItemSuiStakingInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuiStakingInfoViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: SuiStakingInfoViewHolder, position: Int) {
        val staked = currentList[position]
        holder.bind(selectedChain, staked)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(staked)
            }
        }
    }

    private class SuiStakingInfoDiffCallback : DiffUtil.ItemCallback<Pair<String, JsonObject>>() {

        override fun areItemsTheSame(
            oldItem: Pair<String, JsonObject>, newItem: Pair<String, JsonObject>
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Pair<String, JsonObject>, newItem: Pair<String, JsonObject>
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (Pair<String, JsonObject>) -> Unit) {
        onItemClickListener = listener
    }
}