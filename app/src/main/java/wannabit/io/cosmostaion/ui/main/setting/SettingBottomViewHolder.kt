package wannabit.io.cosmostaion.ui.main.setting

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemSettingBottomBinding
import wannabit.io.cosmostaion.ui.main.SettingType

class SettingBottomViewHolder(
    val context: Context,
    private val binding: ItemSettingBottomBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String, settingType: Int) {
        binding.apply {
            selectTitle.text = item
            if (settingType == SettingType.LANGUAGE.ordinal) {
                selectImg.visibleOrGone(Prefs.language == adapterPosition)
            } else {
                selectImg.visibleOrGone(Prefs.autoPass == adapterPosition)
            }
        }
    }
}