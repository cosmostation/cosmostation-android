package wannabit.io.cosmostaion.ui.main.setting

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemThemeBinding

class ThemeViewHolder(
    val context: Context, private val binding: ItemThemeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(theme: String) {
        binding.apply {
            themeView.setBackgroundResource(R.drawable.item_bg)
            styleImg.setBackgroundResource(R.drawable.item_bg)

            title.text = theme
            if (theme.contains("Dark")) {
                content.text = context.getString(R.string.str_dark_theme_msg)
                styleImg.setImageResource(R.drawable.icon_dark_theme)
            } else {
                content.text = context.getString(R.string.str_cosmic_theme_msg)
                styleImg.setImageResource(R.drawable.icon_cosmic_theme)
            }

            if (Prefs.theme == adapterPosition) {
                themeView.setBackgroundResource(R.drawable.item_select_bg2)
            } else {
                themeView.setBackgroundResource(R.drawable.item_bg)
            }
        }
    }
}