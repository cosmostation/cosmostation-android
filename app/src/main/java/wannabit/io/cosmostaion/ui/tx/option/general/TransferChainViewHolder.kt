package wannabit.io.cosmostaion.ui.tx.option.general

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.databinding.ItemTransferChainBinding

class TransferChainViewHolder(
    private val context: Context, private val binding: ItemTransferChainBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(fromChain: BaseChain?, toChain: BaseChain?, chain: BaseChain) {
        binding.apply {
            chainImg.setChainLogo(chain)
            chainName.text = chain.getChainName()
            ibcSendStatus.goneOrVisible(fromChain == chain)

            if (toChain == chain) {
                chainView.visibility = View.VISIBLE
                chainViewLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        context, R.color.color_base08
                    )
                )

            } else {
                chainView.visibility = View.GONE
                chainViewLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        context, R.color.color_transparent
                    )
                )
            }
        }
    }
}