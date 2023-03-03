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
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.databinding.DialogTemplateRecyclerBinding
import wannabit.io.cosmostaion.databinding.ItemDialogAccountBinding
import wannabit.io.cosmostaion.utils.WDp

class HtlcReceivableAccountsDialog : DialogFragment() {
    private var dialogTemplateRecyclerBinding: DialogTemplateRecyclerBinding? = null
    private var mAccounts = ArrayList<Account>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.layout_trans_with_border)
        dialogTemplateRecyclerBinding =
            DialogTemplateRecyclerBinding.inflate(inflater, container, false)
        mAccounts = sActivity!!.baseDao.onSelectAccountsByHtlcClaim(
            BaseChain.getChain(
                arguments!!.getString("chainName")
            )
        )
        dialogTemplateRecyclerBinding!!.dialogTitle.setText(R.string.str_select_account)
        dialogTemplateRecyclerBinding!!.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        dialogTemplateRecyclerBinding!!.recycler.setHasFixedSize(true)
        dialogTemplateRecyclerBinding!!.recycler.adapter =
            AccountListAdapter()
        return dialogTemplateRecyclerBinding!!.root
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
            onBindReceiveAccountItemViewHolder(holder, position)
        }

        private fun onBindReceiveAccountItemViewHolder(holder: AccountHolder, position: Int) {
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
            WDp.setDpCoin(
                sActivity,
                sActivity!!.baseDao,
                chainConfig,
                chainConfig.mainDenom(),
                account.getTokenBalance(chainConfig.mainDenom()).toPlainString(),
                holder.itemDialogAccountBinding.accountDenom,
                holder.itemDialogAccountBinding.accountAvailable
            )
            holder.itemDialogAccountBinding.rootLayer.setOnClickListener {
                val result = Bundle()
                result.putInt(BaseConstant.POSITION, position)
                parentFragmentManager.setFragmentResult(HTLC_ACCOUNT_BUNDLE_KEY, result)
                dismiss()
            }
        }

        override fun getItemCount(): Int {
            return mAccounts.size
        }

        inner class AccountHolder(val itemDialogAccountBinding: ItemDialogAccountBinding) :
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

    companion object {
        const val HTLC_ACCOUNT_BUNDLE_KEY = "htlcaccount"

        @JvmStatic
        fun newInstance(bundle: Bundle?): HtlcReceivableAccountsDialog {
            val frag = HtlcReceivableAccountsDialog()
            frag.arguments = bundle
            return frag
        }
    }
}