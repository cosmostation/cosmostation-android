package wannabit.io.cosmostaion.dialog

import android.graphics.PorterDuff
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.databinding.DialogTemplateRecyclerBinding
import wannabit.io.cosmostaion.databinding.ItemDialogAccountBinding
import wannabit.io.cosmostaion.utils.WDp

class WcAccountDialog : DialogFragment() {
    private var dialogTemplateRecyclerBinding: DialogTemplateRecyclerBinding? = null
    private var mAccounts = ArrayList<Account>()
    private var mOnSelectListener: OnDialogSelectListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.layout_trans_with_border)
        dialogTemplateRecyclerBinding =
            DialogTemplateRecyclerBinding.inflate(inflater, container, false)
        dialogTemplateRecyclerBinding!!.dialogTitle.setText(R.string.str_select_account)
        mAccounts = sActivity!!.baseDao.onSelectAllAccountsByChainWithKey(
            WDp.getChainTypeByChainId(
                arguments!!.getString("chainName")
            )
        )
        dialogTemplateRecyclerBinding!!.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        dialogTemplateRecyclerBinding!!.recycler.setHasFixedSize(true)
        dialogTemplateRecyclerBinding!!.recycler.adapter = AccountListAdapter()
        dialog!!.setOnDismissListener {
            if (mOnSelectListener != null) {
                mOnSelectListener!!.onCancel()
            }
        }
        return dialogTemplateRecyclerBinding!!.root
    }

    fun setOnSelectListener(mOnSelectListener: OnDialogSelectListener?) {
        this.mOnSelectListener = mOnSelectListener
    }

    private inner class AccountListAdapter :
        RecyclerView.Adapter<AccountListAdapter.AccountHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AccountHolder {
            return AccountHolder(
                ItemDialogAccountBinding.inflate(
                    layoutInflater
                )
            )
        }

        override fun onBindViewHolder(holder: AccountHolder, position: Int) {
            val account = mAccounts[position]
            val baseChain = BaseChain.getChain(account.baseChain)
            val chainConfig = ChainFactory.getChain(baseChain)
            holder.itemDialogAccountBinding.accountKeyState.setColorFilter(
                ContextCompat.getColor(
                    sActivity!!, chainConfig.chainColor()
                ), PorterDuff.Mode.SRC_IN
            )
            holder.itemDialogAccountBinding.accountAddress.text = account.address
            if (TextUtils.isEmpty(account.nickName)) holder.itemDialogAccountBinding.accountName.text =
                getString(R.string.str_my_wallet) + account.id else holder.itemDialogAccountBinding.accountName.text =
                account.nickName
            WDp.setDpSymbol(
                sActivity,
                sActivity!!.baseDao,
                chainConfig,
                chainConfig.mainDenom(),
                holder.itemDialogAccountBinding.accountDenom
            )
            holder.itemDialogAccountBinding.accountAvailable.text = account.getLastTotal(baseChain)
            holder.itemDialogAccountBinding.rootLayer.setOnClickListener {
                if (mOnSelectListener != null) {
                    mOnSelectListener!!.onSelect(account)
                }
                dismiss()
            }
        }

        override fun getItemCount(): Int {
            return mAccounts.size
        }

        inner class AccountHolder(var itemDialogAccountBinding: ItemDialogAccountBinding) :
            RecyclerView.ViewHolder(
                itemDialogAccountBinding.root
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogTemplateRecyclerBinding = null
    }

    private val sActivity: BaseActivity?
        get() = activity as BaseActivity?

    interface OnDialogSelectListener {
        fun onSelect(account: Account)
        fun onCancel()
    }

    companion object {
        fun newInstance(bundle: Bundle?): WcAccountDialog {
            val dialog = WcAccountDialog()
            dialog.arguments = bundle
            dialog.isCancelable = true
            return dialog
        }
    }
}