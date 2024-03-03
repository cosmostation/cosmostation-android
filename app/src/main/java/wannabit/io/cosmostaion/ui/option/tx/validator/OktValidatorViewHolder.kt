package wannabit.io.cosmostaion.ui.option.tx.validator

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.OktValidatorResponse
import wannabit.io.cosmostaion.databinding.ItemOktValidatorBinding

class OktValidatorViewHolder(
    val context: Context,
    private val binding: ItemOktValidatorBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        line: ChainOkt996Keccak,
        allValidator: OktValidatorResponse,
        myValidators: MutableList<OktValidatorResponse>
    ) {
        binding.apply {
            monikerImg.setMonikerImg(line, allValidator.operatorAddress)
            monikerName.text = allValidator.description?.moniker
            if (allValidator.jailed == true) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_jailed)
            } else if (allValidator.status != 2) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_inactive)
            } else {
                jailedImg.visibility = View.GONE
            }

            selectImg.visibleOrGone(myValidators.map { it.operatorAddress }
                .contains(allValidator.operatorAddress))
        }
    }
}