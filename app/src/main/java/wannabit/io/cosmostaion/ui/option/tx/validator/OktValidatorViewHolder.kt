package wannabit.io.cosmostaion.ui.option.tx.validator

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemOktValidatorBinding

class OktValidatorViewHolder(
    val context: Context, private val binding: ItemOktValidatorBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        chain: BaseChain, allValidator: JsonObject, myValidators: MutableList<JsonObject>
    ) {
        binding.apply {
            monikerImg.setMonikerImg(chain, allValidator["operator_address"].asString)
            monikerName.text = allValidator["description"].asJsonObject["moniker"].asString
            if (allValidator["jailed"].asBoolean) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_jailed)
            } else if (allValidator["status"].asInt != 2) {
                jailedImg.visibility = View.VISIBLE
                jailedImg.setImageResource(R.drawable.icon_inactive)
            } else {
                jailedImg.visibility = View.GONE
            }

            selectImg.visibleOrGone(myValidators.map { it["operator_address"].asString }
                .contains(allValidator["operator_address"].asString))
        }
    }
}