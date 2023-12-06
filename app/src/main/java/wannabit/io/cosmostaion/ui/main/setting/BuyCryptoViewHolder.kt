package wannabit.io.cosmostaion.ui.main.setting

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ItemBuyCryptoBinding

class BuyCryptoViewHolder(
    val context: Context,
    private val binding: ItemBuyCryptoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(buy: String) {
        binding.apply {
            cryptoName.text = buy
            when (buy) {
                "MOONPAY" -> {
                    cryptoImg.setImageResource(R.drawable.icon_service_moonpay)
                    cryptoDescription.text = "Buy asset with moonpay"
                }
                "KADO" -> {
                    cryptoImg.setImageResource(R.drawable.icon_service_kado)
                    cryptoDescription.visibility = View.GONE
                }
                else -> {
                    cryptoImg.setImageResource(R.drawable.icon_service_binance)
                    cryptoDescription.visibility = View.GONE
                }
            }
        }
    }
}