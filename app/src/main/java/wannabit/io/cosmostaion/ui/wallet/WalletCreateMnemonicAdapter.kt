package wannabit.io.cosmostaion.ui.wallet

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.databinding.ItemWalletCreateMnemonicBinding

class WalletCreateMnemonicAdapter(private val context: Context, val words: MutableList<String> = mutableListOf(), val hidden: Boolean = false) :
    RecyclerView.Adapter<WalletCreateMnemonicViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletCreateMnemonicViewHolder {
        val binding = ItemWalletCreateMnemonicBinding.inflate(LayoutInflater.from(context), parent, false)
        return WalletCreateMnemonicViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: WalletCreateMnemonicViewHolder, position: Int) {
        val item = words[position]
        viewHolder.binding.apply {
            number.text = "${position + 1}".padStart(2)
            if (hidden) {
                word.text = "****"
            } else {
                word.text = item
            }
        }
    }

    override fun getItemCount(): Int {
        return words.size
    }
}