package wannabit.io.cosmostaion.ui.main.setting.account

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ItemAccountListBinding
import wannabit.io.cosmostaion.databinding.PopupAccountMenuBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity


class AccountListViewHolder(
    val context: Context,
    val binding: ItemAccountListBinding,

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(account: BaseAccount, listener: AccountListAdapter.ClickListener) {
        binding.apply {
            accountView.setBackgroundResource(R.drawable.item_bg)
            if (account.type == BaseAccountType.MNEMONIC) {
                accountTypeImg.setImageResource(R.drawable.icon_mnemonic)

            } else {
                accountTypeImg.setImageResource(R.drawable.icon_private)
            }
            accountName.text = account.name

            selectImg.setOnClickListener {
                val location = IntArray(2)
                it.getLocationOnScreen(location)
                val x = location[0]
                val y = location[1]

                showPopupMenu(it, context, account, x, y, listener)
            }
        }
    }

    private fun showPopupMenu(view: View, context: Context, account: BaseAccount, x: Int, y: Int, listener: AccountListAdapter.ClickListener) {
        val inflater =
            view.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = PopupAccountMenuBinding.inflate(inflater)
        val popupView = binding.root

        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        if (y > 1200) {
            popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, x, y - 550)
        } else {
            popupWindow.showAsDropDown(view)
        }

        binding.apply {
            if (account.type == BaseAccountType.MNEMONIC) {
                btnCheckMnemonic.visibility = View.VISIBLE
                view2.visibility = View.VISIBLE
                btnCheckPrivate.text = context.getString(R.string.str_check_private)
            } else {
                btnCheckMnemonic.visibility = View.GONE
                view2.visibility = View.GONE
                btnCheckPrivate.text = context.getString(R.string.str_only_private)
            }

            btnChangeName.setOnClickListener {
                val parentFragmentManager: FragmentManager =
                    (context as FragmentActivity).supportFragmentManager
                val bottomSheet = ChangeNameFragment(account)
                bottomSheet.show(parentFragmentManager, ChangeNameFragment::class.java.name)
                popupWindow.dismiss()
            }

            btnDeleteAccount.setOnClickListener {
                val parentFragmentManager: FragmentManager =
                    (context as FragmentActivity).supportFragmentManager
                val bottomSheet = DeleteFragment(account)
                bottomSheet.show(parentFragmentManager, DeleteFragment::class.java.name)
                popupWindow.dismiss()
            }

            btnCheckMnemonic.setOnClickListener {
                popupWindow.dismiss()
                val intent = Intent(context, PasswordCheckActivity::class.java)
                intent.putExtra("checkPwType", BaseConstant.CONST_PW_CONFIRM_MNEMONIC)
                context.startActivity(intent)
                (context as Activity).overridePendingTransition(
                    R.anim.anim_slide_in_bottom,
                    R.anim.anim_fade_out
                )

                listener.checkMnemonicAction(account)
            }

            btnCheckPrivate.setOnClickListener {
                popupWindow.dismiss()
                val intent = Intent(context, PasswordCheckActivity::class.java)
                intent.putExtra("checkPwType", BaseConstant.CONST_PW_CONFIRM_PRIVATE)
                context.startActivity(intent)
                (context as Activity).overridePendingTransition(
                    R.anim.anim_slide_in_bottom,
                    R.anim.anim_fade_out
                )

                listener.checkPrivateAction(account)
            }
        }
    }
}