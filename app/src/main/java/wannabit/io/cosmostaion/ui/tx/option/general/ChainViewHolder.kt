package wannabit.io.cosmostaion.ui.tx.option.general

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.databinding.ItemChainBinding

class ChainViewHolder(
    private val context: Context, private val binding: ItemChainBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, toChain: BaseChain?) {
        binding.apply {

            if (toChain == chain) {
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

            chainImg.setChainLogo(chain)
            chainName.text = chain.getChainName()
        }
    }
}