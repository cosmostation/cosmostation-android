package wannabit.io.cosmostaion.ui.tx.option.general

import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.databinding.ItemChainBinding

class ChainViewHolder(
    private val binding: ItemChainBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain) {
        binding.apply {
            chainImg.setChainLogo(chain)
            chainName.text = chain.getChainName()
        }
    }
}