package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemEcoSystemBinding

class EcoSystemViewHolder(
    val context: Context, private val binding: ItemEcoSystemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(info: JsonObject) {
        binding.apply {
            dappView.setBackgroundResource(R.drawable.item_bg)
            val url = info["thumbnail"].asString
            Picasso.get().load(url).error(R.drawable.icon_default_dapp).into(resourceImg)

            dappTypeBadge.text = info["type"].asString
            dappTitle.text = info["name"].asString
            dappDescription.text = info["description"].asString
            dappNotImg.goneOrVisible(info.get("support")?.asBoolean ?: true)

            if (info.has("id")) {
                updateView(info)

                btnPinLayout.setOnClickListener {
                    val id = info["id"].asInt
                    val pinnedDapps = Prefs.getPinnedDapps()
                    if (pinnedDapps.contains(id)) {
                        pinnedDapps.removeIf { it == id }
                    } else {
                        pinnedDapps.add(id)
                    }
                    Prefs.setPinnedDapps(pinnedDapps)
                    updateView(info)
                }

            } else {
                btnPinLayout.visibility = View.GONE
            }
        }
    }

    private fun updateView(ecosystem: JsonObject) {
        binding.apply {
            if (Prefs.getPinnedDapps().contains(ecosystem["id"].asInt)) {
                btnPin.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        context, R.color.color_accent_yellow
                    ), PorterDuff.Mode.SRC_IN
                )

            } else {
                btnPin.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        context, R.color.color_base04
                    ), PorterDuff.Mode.SRC_IN
                )
            }
        }
    }
}