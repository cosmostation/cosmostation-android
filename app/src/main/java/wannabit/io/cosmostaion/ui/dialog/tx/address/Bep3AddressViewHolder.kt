package wannabit.io.cosmostaion.ui.dialog.tx.address

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.databinding.ItemBep3AddressBinding

class Bep3AddressViewHolder(
    val context: Context,
    private val binding: ItemBep3AddressBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(line: CosmosLine) {
        binding.apply {
            if (line is ChainBinanceBeacon) {
                val availableAmount = line.lcdBalanceAmount(line.stakeDenom)
                toSendAmount.text = formatAmount(availableAmount.toString(), 8)
                toSendDenom.text = "BNB"

            } else {
                line.stakeDenom?.let { denom ->
                    val availableAmount = line.balanceAmount(denom)
                }
                toSendDenom.text = "KAVA"
            }
            chainImg.setImageResource(line.logo)
            toAddress.text = line.address
        }
    }
}