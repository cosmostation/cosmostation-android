package wannabit.io.cosmostaion.ui.main.setting

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemSettingBottomBinding

class SettingBottomViewHolder(
    val context: Context, private val binding: ItemSettingBottomBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.apply {
            selectTitle.text = item
            selectImg.visibleOrGone(Prefs.language == adapterPosition)
        }
    }
}