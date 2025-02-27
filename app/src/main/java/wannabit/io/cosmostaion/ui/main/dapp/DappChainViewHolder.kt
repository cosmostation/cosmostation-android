package wannabit.io.cosmostaion.ui.main.dapp

import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.databinding.ItemDappChainBinding

class DappChainViewHolder(
    private val binding: ItemDappChainBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(supportChain: String, index: Int) {
        binding.apply {
            if (supportChain.uppercase() == "All Network".uppercase()) {
                chainImg.setImageResource(R.drawable.icon_all_network)
                chainName.text = "All Network"

            } else {
                allChains().first { it.apiName == supportChain }.let { chain ->
                    chainImg.setImageResource(chain.logo)
                    chainName.text = supportChain.uppercase()
                }
            }
        }
    }
}