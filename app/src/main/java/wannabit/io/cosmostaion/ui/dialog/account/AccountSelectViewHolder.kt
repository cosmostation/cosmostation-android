package wannabit.io.cosmostaion.ui.dialog.account

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ItemAccountSelectBinding

class AccountSelectViewHolder(
    val context: Context,
    private val binding: ItemAccountSelectBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(baseAccount: BaseAccount) {
        binding.apply {
            accountName.text = baseAccount.name
            if (baseAccount.type == BaseAccountType.MNEMONIC) {
                accountType.text = context.getString(R.string.title_mnemonic_account)
                accountTypeImg.setImageResource(R.drawable.icon_mnemonic)
            } else if (baseAccount.type == BaseAccountType.PRIVATE_KEY) {
                accountType.text = context.getString(R.string.title_private_account)
                accountTypeImg.setImageResource(R.drawable.icon_private)
            }
            selectImg.visibleOrGone(BaseData.baseAccount?.name == baseAccount.name)
        }
    }
}