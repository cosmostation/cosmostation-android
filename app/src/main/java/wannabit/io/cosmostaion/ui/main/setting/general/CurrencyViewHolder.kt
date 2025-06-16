package wannabit.io.cosmostaion.ui.main.setting.general

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.setImg
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemCurrencyBinding

class CurrencyViewHolder(
    val context: Context,
    private val binding: ItemCurrencyBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(currency: String) {
        binding.apply {
            currencyName.text = currency

            val country =
                context.resources.getStringArray(R.array.currency_country_array)[adapterPosition]
            currencyCountry.text = country

            val imageResources = listOf(
                R.drawable.icon_usd,
                R.drawable.icon_euro,
                R.drawable.icon_krw,
                R.drawable.icon_jpy,
                R.drawable.icon_cny,
                R.drawable.icon_rub,
                R.drawable.icon_baht,
                R.drawable.icon_gbp,
                R.drawable.icon_inr,
                R.drawable.icon_brl,
                R.drawable.icon_idr,
                R.drawable.icon_dkk,
                R.drawable.icon_nok,
                R.drawable.icon_sek,
                R.drawable.icon_chf,
                R.drawable.icon_aud,
                R.drawable.icon_cad,
                R.drawable.icon_myr
            )
            currencyImg.setImg(imageResources[adapterPosition])

            if (Prefs.currency == adapterPosition) {
                currencyView.visibility = View.VISIBLE
                currencyViewLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        context, R.color.color_base08
                    )
                )
                selectImg.visibility = View.VISIBLE

            } else {
                currencyView.visibility = View.GONE
                currencyViewLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        context, R.color.color_transparent
                    )
                )
                selectImg.visibility = View.GONE
            }
        }
    }
}