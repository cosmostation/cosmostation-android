package wannabit.io.cosmostaion.ui.tx.info.kava

import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto.Coin
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.databinding.ItemEarnStatusBinding
import java.math.BigDecimal
import java.math.RoundingMode

class EarnStatusViewHolder(
    private val binding: ItemEarnStatusBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(selectedChain: BaseChain?, myDeposits: MutableList<Coin>) {
        binding.apply {
            earnStatusView.setBackgroundResource(R.drawable.item_bg)
            var sum = BigDecimal.ZERO
            myDeposits.forEach { coin ->
                sum = sum.add(coin.amount.toBigDecimal())
            }
            selectedChain?.let { line ->
                myTotalLiquidity.text = formatAmount(
                    sum.movePointLeft(6).setScale(6, RoundingMode.DOWN).toPlainString(), 6
                )
                val availableAmount = line.cosmosFetcher?.availableAmount("ukava")
                myAvailable.text = formatAmount(
                    availableAmount?.movePointLeft(6)?.setScale(6, RoundingMode.DOWN).toString(), 6
                )
            }
        }
    }
}