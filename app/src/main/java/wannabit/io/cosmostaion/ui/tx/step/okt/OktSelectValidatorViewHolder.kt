package wannabit.io.cosmostaion.ui.tx.step.okt

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.OktValidatorResponse
import wannabit.io.cosmostaion.databinding.ItemOktSelectValidatorBinding

class OktSelectValidatorViewHolder(
    val context: Context,
    private val binding: ItemOktSelectValidatorBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(line: ChainOkt60, myValidator: OktValidatorResponse) {
        binding.apply {
            validatorView.setBackgroundResource(R.drawable.cell_bg)

            monikerImg.setMonikerImg(line, myValidator.operatorAddress)
            monikerName.text = myValidator.description?.moniker
            if (myValidator.jailed == true) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_jailed)
            } else if (myValidator.status != 2) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_inactive)
            } else {
                jailedImg.visibility = View.GONE
            }

            val vp = myValidator.delegatorShares
            sharedAmount.text = formatAmount(vp, 2)
        }
    }
}