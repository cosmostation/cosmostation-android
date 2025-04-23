package wannabit.io.cosmostaion.ui.tx.info.major.btc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.databinding.ItemBtcActiveBinding

class BtcActiveInfoAdapter(
    private val selectedChain: BaseChain, private var listener: ClickListener
) : ListAdapter<Pair<JsonObject, FinalityProvider>, BtcActiveViewHolder>(BtcStakingInfoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BtcActiveViewHolder {
        val binding =
            ItemBtcActiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BtcActiveViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: BtcActiveViewHolder, position: Int) {
        val staked = currentList[position]
        holder.bind(selectedChain, staked, listener)
    }

    private class BtcStakingInfoDiffCallback :
        DiffUtil.ItemCallback<Pair<JsonObject, FinalityProvider>>() {

        override fun areItemsTheSame(
            oldItem: Pair<JsonObject, FinalityProvider>, newItem: Pair<JsonObject, FinalityProvider>
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Pair<JsonObject, FinalityProvider>, newItem: Pair<JsonObject, FinalityProvider>
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface ClickListener {
        fun selectStakingAction(staked: Pair<JsonObject, FinalityProvider>)
    }
}