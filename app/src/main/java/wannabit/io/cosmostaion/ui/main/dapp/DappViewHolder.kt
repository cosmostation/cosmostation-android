package wannabit.io.cosmostaion.ui.main.dapp

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.squareup.picasso.Picasso
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.CustomImageBinding
import wannabit.io.cosmostaion.databinding.ItemDappBinding

class DappViewHolder(
    val context: Context, private val binding: ItemDappBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        ecosystem: JsonObject, listener: DappListAdapter.PinnedListener
    ) {
        binding.apply {
            val url = ecosystem["thumbnail"].asString
            Picasso.get().load(url).error(R.drawable.icon_default_dapp).into(thumbnailImg)
            thumbnailImg.clipToOutline = true
            updateView(ecosystem)

            dappName.text = ecosystem["name"].asString
            dappTypeBadge.text = ecosystem["type"].asString
            dappDescription.text = ecosystem["description"].asString

            val chains = JsonArray().apply {
                ecosystem["chains"].asJsonArray
                    .map { it.asString }
                    .filter { chainName ->
                        allChains().any { it.apiName == chainName }
                    }
                    .forEach { add(JsonPrimitive(it)) }
            }
            chainContainer.removeAllViews()

            val inflater = LayoutInflater.from(context)
            chains.take(7).forEach { supportChain ->
                val view = CustomImageBinding.inflate(inflater, chainContainer, false)
                allChains().firstOrNull { it.apiName == supportChain.asString }?.let { chain ->
                    view.chainImg.setChainLogo(chain)
                    if (chains.size() == 1) {
                        addInfo.text = chain.name.uppercase()
                    } else if (chains.size() > 7) {
                        addInfo.text = "+" + (chains.size() - 7)
                    } else {
                        addInfo.text = ""
                    }

                } ?: run {
                    view.chainImg.setImageResource(R.drawable.chain_default)
                    if (chains.size() == 1) {
                        addInfo.text = "Unknown"
                    } else if (chains.size() > 7) {
                        addInfo.text = "+" + (chains.size() - 7)
                    } else {
                        addInfo.text = ""
                    }
                }
                chainContainer.addView(view.root)
            }

            btnPinLayout.setOnClickListener {
                val id = ecosystem["id"].asInt
                val pinnedDapps = Prefs.getPinnedDapps()
                if (pinnedDapps.contains(id)) {
                    pinnedDapps.removeIf { it == id }
                } else {
                    pinnedDapps.add(id)
                }
                Prefs.setPinnedDapps(pinnedDapps)
                updateView(ecosystem)
                listener.select(id)
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
                        context, R.color.color_base05
                    ), PorterDuff.Mode.SRC_IN
                )
            }
        }
    }
}