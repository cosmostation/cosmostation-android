package wannabit.io.cosmostaion.ui.main.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemEditBinding

class ChainEditAdapter(
    val account: BaseAccount,
    private val allCosmosChains: MutableList<CosmosLine>,
    private val displayChainLines: MutableList<String>,
    var listener: SelectListener
) : RecyclerView.Adapter<ChainEditViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChainEditViewHolder {
        val binding = ItemEditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChainEditViewHolder(parent.context, binding)
    }

    override fun getItemCount(): Int {
        return allCosmosChains.size
    }

    override fun onBindViewHolder(holder: ChainEditViewHolder, position: Int) {
        val line = allCosmosChains[position]
        holder.bind(account, line, itemCount, displayChainLines, listener)
    }

    interface SelectListener {
        fun select(displayChainLines: MutableList<String>)
    }
}