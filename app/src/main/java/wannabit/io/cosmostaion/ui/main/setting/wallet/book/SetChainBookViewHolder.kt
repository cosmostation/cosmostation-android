package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.databinding.ItemSetChainBookBinding

class SetChainBookViewHolder(
    private val context: Context, private val binding: ItemSetChainBookBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain?, toChain: BaseChain?) {
        binding.apply {
            if (chain == null) {
                chainImg.setImageResource(R.drawable.evm_universal)
                chainName.text = "EVM Networks (Universal)"
            } else {
                chainImg.setChainLogo(chain)
                chainName.text = chain.getChainName()
            }

            if (chain == toChain) {
                chainView.visibility = View.VISIBLE
                chainViewLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        context, R.color.color_base08
                    )
                )
                checkImg.visibility = View.VISIBLE

            } else {
                chainView.visibility = View.GONE
                chainViewLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        context, R.color.color_transparent
                    )
                )
                checkImg.visibility = View.GONE
            }
        }
    }
}