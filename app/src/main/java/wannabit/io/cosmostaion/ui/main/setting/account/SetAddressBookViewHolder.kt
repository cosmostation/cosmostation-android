package wannabit.io.cosmostaion.ui.main.setting.account

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.ItemSetAddressBookBinding
import wannabit.io.cosmostaion.databinding.PopupAddressMenuBinding

class SetAddressBookViewHolder(
    val context: Context,
    private val binding: ItemSetAddressBookBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(addressBook: AddressBook, listener: SetAddressBookAdapter.ClickListener) {
        binding.apply {
            addressBookView.setBackgroundResource(R.drawable.item_bg)

            allCosmosLines().firstOrNull { it.name == addressBook.chainName }?.let { line ->
                chainImg.setImageResource(line.logo)
            }
            addressName.text = addressBook.bookName
            addressMemo.text = addressBook.memo
            address.text = addressBook.address

            selectOption.setOnClickListener {
                val location = IntArray(2)
                it.getLocationOnScreen(location)
                val x = location[0]
                val y = location[1]

                showPopupMenu(it, addressBook, adapterPosition, x, y, listener)
            }
        }
    }

    private fun showPopupMenu(view: View, addressBook: AddressBook, position: Int, x: Int, y: Int, listener: SetAddressBookAdapter.ClickListener) {
        val inflater =
            view.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = PopupAddressMenuBinding.inflate(inflater)
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
            btnEdit.setOnClickListener {
                listener.editAddressBook(addressBook, position)
                popupWindow.dismiss()
            }

            btnDelete.setOnClickListener {
                popupWindow.dismiss()
            }
//            if (account.type == BaseAccountType.MNEMONIC) {
//                btnCheckMnemonic.visibility = View.VISIBLE
//                view2.visibility = View.VISIBLE
//                btnCheckPrivate.text = context.getString(R.string.str_check_private)
//            } else {
//                btnCheckMnemonic.visibility = View.GONE
//                view2.visibility = View.GONE
//                btnCheckPrivate.text = context.getString(R.string.str_only_private)
//            }
//
//            btnChangeName.setOnClickListener {
//                val parentFragmentManager: FragmentManager =
//                    (context as FragmentActivity).supportFragmentManager
//                val bottomSheet = ChangeNameFragment(account)
//                bottomSheet.show(parentFragmentManager, ChangeNameFragment::class.java.name)
//                popupWindow.dismiss()
//            }
//
//            btnDeleteAccount.setOnClickListener {
//                val parentFragmentManager: FragmentManager =
//                    (context as FragmentActivity).supportFragmentManager
//                val bottomSheet = DeleteFragment(account)
//                bottomSheet.show(parentFragmentManager, DeleteFragment::class.java.name)
//                popupWindow.dismiss()
//            }
//
//            btnCheckMnemonic.setOnClickListener {
//                popupWindow.dismiss()
//                val intent = Intent(context, PasswordCheckActivity::class.java)
//                intent.putExtra("checkPwType", BaseConstant.CONST_PW_CONFIRM_MNEMONIC)
//                context.startActivity(intent)
//                (context as Activity).overridePendingTransition(
//                    R.anim.anim_slide_in_bottom,
//                    R.anim.anim_fade_out
//                )
//
//                listener.checkMnemonicAction(account)
//            }
//
//            btnCheckPrivate.setOnClickListener {
//                popupWindow.dismiss()
//                val intent = Intent(context, PasswordCheckActivity::class.java)
//                intent.putExtra("checkPwType", BaseConstant.CONST_PW_CONFIRM_PRIVATE)
//                context.startActivity(intent)
//                (context as Activity).overridePendingTransition(
//                    R.anim.anim_slide_in_bottom,
//                    R.anim.anim_fade_out
//                )
//
//                listener.checkPrivateAction(account)
//            }
        }
    }
}