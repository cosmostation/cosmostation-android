package wannabit.io.cosmostaion.ui.main.chain.evm

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ItemEcoSystemBinding

class EvmEcoSystemViewHolder(
    val context: Context, private val binding: ItemEcoSystemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(info: JsonObject) {
        binding.apply {
            dappView.setBackgroundResource(R.drawable.item_bg)
            val url = info["thumbnail"].asString
            Picasso.get().load(url).into(resourceImg)

            dappTitle.text = info["name"].asString
            dappDescription.text = info["description"].asString
        }
    }
}