package wannabit.io.cosmostaion.ui.dialog.tx

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.ItemAddressBinding

class AddressBookViewHolder(
    val context: Context,
    private val binding: ItemAddressBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(refAddress: RefAddress) {
        binding.apply {
            CoroutineScope(Dispatchers.IO).launch {
                val account = AppDatabase.getInstance().baseAccountDao().selectAccount(refAddress.accountId)
                withContext(Dispatchers.Main) {
                    accountName.text = account?.name

                    allCosmosLines().firstOrNull { it.tag == refAddress.chainTag }?.let { line ->
                        if (line.evmCompatible) {
                            chainBadge.visibility = View.VISIBLE
                            chainBadge.text = context.getString(R.string.str_evm)
                            chainBadge.setBackgroundResource(R.drawable.round_box_evm)
                            chainBadge.setTextColor(ContextCompat.getColor(context, R.color.color_base01))

                        } else if (!line.isDefault) {
                            chainBadge.visibility = View.VISIBLE
                            chainBadge.text = context.getString(R.string.str_deprecated)
                            chainBadge.setBackgroundResource(R.drawable.round_box_deprecated)
                            chainBadge.setTextColor(ContextCompat.getColor(context, R.color.color_base02))

                        } else {
                            chainBadge.visibility = View.GONE
                        }
                    }
                }
            }
            accountAddress.text = refAddress.dpAddress
        }
    }
}