package wannabit.io.cosmostaion.ui.tx.info.kava

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.cosmos.staking.v1beta1.StakingProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.databinding.ItemEarnBinding
import java.math.RoundingMode

class EarnViewHolder(
    private val binding: ItemEarnBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(selectedChain: BaseChain?, deposit: Coin) {
        binding.apply {
            earningView.setBackgroundResource(R.drawable.item_bg)
            val valOpAddress = deposit.denom.replace("bkava-", "")
            selectedChain?.cosmosFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress == valOpAddress }
                ?.let { validator ->
                    monikerImg.setMonikerImg(selectedChain, validator.operatorAddress)
                    moniker.text = validator.description.moniker?.trim()
                    if (validator.jailed) {
                        jailedImg.visibility = View.VISIBLE
                        jailedImg.setImageResource(R.drawable.icon_jailed)
                    } else if (!validator.isActiveValidator(selectedChain)) {
                        jailedImg.visibility = View.VISIBLE
                        jailedImg.setImageResource(R.drawable.icon_inactive)
                    } else {
                        jailedImg.visibility = View.GONE
                    }

                    depositAvailable.text = formatAmount(
                        deposit.amount.toBigDecimal().movePointLeft(6)
                            .setScale(6, RoundingMode.DOWN).toPlainString(), 6
                    )
                }
        }
    }
}