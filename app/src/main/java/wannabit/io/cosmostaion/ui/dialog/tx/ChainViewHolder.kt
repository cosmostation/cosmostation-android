package wannabit.io.cosmostaion.ui.dialog.tx

import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.ItemChainBinding

class ChainViewHolder(
    private val binding: ItemChainBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(line: CosmosLine) {
        binding.apply {
            chainImg.setImageResource(line.logo)
            chainName.text = line.name.uppercase()
        }
    }
}