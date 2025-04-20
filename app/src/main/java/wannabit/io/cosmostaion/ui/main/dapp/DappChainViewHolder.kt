package wannabit.io.cosmostaion.ui.main.dapp

import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.common.setView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemDappChainBinding

class DappChainViewHolder(
    private val binding: ItemDappChainBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(supportChain: String, selectChain: String?) {
        binding.apply {
            if (supportChain.uppercase() == "All Network".uppercase()) {
                chainImg.setImageResource(R.drawable.icon_all_network)
                chainName.text = "All Network"
                dapps.text = "${BaseData.ecosystems?.size}"

            } else {
                allChains().first { it.apiName == supportChain }.let { chain ->
                    chainImg.setChainLogo(chain)
                    chainName.text = chain.name.uppercase()
                    dapps.text = "${
                        BaseData.ecosystems?.map { it["chains"].asJsonArray }?.flatten()
                            ?.count { it.asString.uppercase() == chain.apiName.uppercase() }
                    }"
                }
            }

            chainView.setView(supportChain.uppercase() != selectChain?.uppercase())
            checkImg.visibleOrGone(supportChain.uppercase() == selectChain?.uppercase())
            checkView.visibleOrGone(supportChain.uppercase() == selectChain?.uppercase())
        }
    }
}