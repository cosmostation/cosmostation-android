package wannabit.io.cosmostaion.ui.main.setting.wallet.account

import android.content.Context
import android.graphics.PorterDuff
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ItemAccountListBinding


class AccountListViewHolder(
    val context: Context,
    val binding: ItemAccountListBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(account: BaseAccount) {
        binding.apply {
            accountView.setBackgroundResource(R.drawable.item_bg)
            if (account.type == BaseAccountType.MNEMONIC) {
                accountTypeImg.setImageResource(R.drawable.icon_mnemonic)
            } else {
                accountTypeImg.setImageResource(R.drawable.icon_private)
            }
            accountName.text = account.name
            orderImg.setColorFilter(
                ContextCompat.getColor(context, R.color.color_base02),
                PorterDuff.Mode.SRC_IN
            )
        }
    }
}