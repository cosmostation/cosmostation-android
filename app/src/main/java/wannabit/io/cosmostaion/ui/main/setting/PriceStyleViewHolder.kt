package wannabit.io.cosmostaion.ui.main.setting

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ItemPriceStyleBinding

class PriceStyleViewHolder(
    val context: Context,
    private val binding: ItemPriceStyleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(style: String) {
        binding.apply {
            selectTitle.text = style
            if (adapterPosition == 0) {
                priceUpImg.setImageResource(R.drawable.icon_price_up)
                priceDownImg.setImageResource(R.drawable.icon_price_down)

            } else {
                priceUpImg.setImageResource(R.drawable.icon_price_up_reverse)
                priceDownImg.setImageResource(R.drawable.icon_price_down_reverse)
            }
        }
    }
}