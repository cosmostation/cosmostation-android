package wannabit.io.cosmostaion.ui.main.dapp

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.setView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemDappSortOptionBinding

class DappSortOptionViewHolder(
    val context: Context, private val binding: ItemDappSortOptionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(option: String, index: Int) {
        binding.apply {
            selectImg.visibleOrGone(Prefs.dappFilter == index)
            checkView.visibleOrGone(Prefs.dappFilter == index)
            optionView.setView(Prefs.dappFilter != index)

            when (option) {
                "Alphabet" -> {
                    optionType.text = context.getString(R.string.str_sort_alphabet)
                    optionDescription.text =
                        context.getString(R.string.str_sort_alphabet_description)
                }

                else -> {
                    optionType.text = context.getString(R.string.str_sort_multi)
                    optionDescription.text = context.getString(R.string.str_sort_multi_description)
                }
            }
        }
    }
}