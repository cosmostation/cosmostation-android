package wannabit.io.cosmostaion.ui.wallet

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.databinding.ItemMnemonicBinding

class RestoreMnemonicConfirmViewHolder(
    val context: Context,
    private val binding: ItemMnemonicBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(wordList: List<String>, position: Int) {
        binding.apply {
            val word = wordList[position]
            number.text = "${adapterPosition + 1}".padStart(2)
            mnemonic.text = word
        }
    }
}