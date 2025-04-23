package wannabit.io.cosmostaion.ui.tx.info.major.iota

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemSuiStakingInfoBinding

class IotaStakingInfoAdapter(
    private val selectedChain: BaseChain
) : ListAdapter<Pair<String, JsonObject>, IotaStakingInfoViewHolder>(SuiStakingInfoDiffCallback()) {

    private var onItemClickListener: ((Pair<String, JsonObject>) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IotaStakingInfoViewHolder {
        val binding =
            ItemSuiStakingInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IotaStakingInfoViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: IotaStakingInfoViewHolder, position: Int) {
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