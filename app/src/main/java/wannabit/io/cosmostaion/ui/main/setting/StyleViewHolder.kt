package wannabit.io.cosmostaion.ui.main.setting

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemStyleBinding

class StyleViewHolder(
    val context: Context, private val binding: ItemStyleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(style: String) {
        binding.apply {
            styleView.setBackgroundResource(R.drawable.item_bg)
            title.text = style
            if (style.contains("Simple")) {
                content.text = context.getString(R.string.str_simple_style_msg)
                styleImg.setImageResource(R.drawable.simple_img)
            } else {
                content.text = context.getString(R.string.str_pro_style_msg)
                styleImg.setImageResource(R.drawable.pro_img)
            }

            if (Prefs.style == adapterPosition) {
                styleView.setBackgroundResource(R.drawable.item_select_bg2)
            } else {
                styleView.setBackgroundResource(R.drawable.item_bg)
            }
        }
    }
}