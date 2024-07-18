package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.databinding.ItemEcoSystemBinding

class EcoSystemViewHolder(
    val context: Context, private val binding: ItemEcoSystemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(info: JsonObject) {
        binding.apply {
            dappView.setBackgroundResource(R.drawable.item_bg)
            val url = info["thumbnail"].asString
            Picasso.get().load(url).into(resourceImg)

            dappTypeBadge.text = info["type"].asString
            dappTitle.text = info["name"].asString
            dappDescription.text = info["description"].asString
            dappNotImg.goneOrVisible(info.get("support")?.asBoolean ?: true)
        }
    }
}