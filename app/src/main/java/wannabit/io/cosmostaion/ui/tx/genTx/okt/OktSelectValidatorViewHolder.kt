package wannabit.io.cosmostaion.ui.tx.genTx.okt

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.databinding.ItemOktSelectValidatorBinding

class OktSelectValidatorViewHolder(
    val context: Context, private val binding: ItemOktSelectValidatorBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, myValidator: JsonObject) {
        binding.apply {
            validatorView.setBackgroundResource(R.drawable.cell_bg)

            monikerImg.setMonikerImg(chain, myValidator["operator_address"].asString)
            monikerName.text = myValidator["description"].asJsonObject["moniker"].asString
            if (myValidator["jailed"].asBoolean) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_jailed)
            } else if (myValidator["status"].asInt != 2) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_inactive)
            } else {
                jailedImg.visibility = View.GONE
            }
            val vp = myValidator["delegator_shares"].asString
            sharedAmount.text = formatAmount(vp, 2)
        }
    }
}