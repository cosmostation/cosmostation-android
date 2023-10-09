package wannabit.io.cosmostaion.ui.dialog.account

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.ItemMnemonicBinding

class MnemonicViewHolder(
    val context: Context,
    private val binding: ItemMnemonicBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(wordList: List<String>, position: Int) {
        binding.apply {
            val word = wordList[position]
            number.text = "${adapterPosition + 1}".padStart(2)
            mnemonic.text = word

            mnemonicLayout.setOnClickListener {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val listString = wordList.joinToString(" ")
                val clip = ClipData.newPlainText("wordList", listString)
                clipboard.setPrimaryClip(clip)
                context.makeToast(R.string.str_msg_mnemonic_copied)
            }
        }
    }
}