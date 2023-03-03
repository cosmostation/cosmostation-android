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

class IBCReceiveAccountsDialog : DialogFragment() {
    private var dialogTemplateRecyclerBinding: DialogTemplateRecyclerBinding? = null
    private var mAccounts: ArrayList<Account>? = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.color.colorTrans)
        dialogTemplateRecyclerBinding =
            DialogTemplateRecyclerBinding.inflate(inflater, container, false)
        mAccounts = arguments!!.getSerializable(ACCOUNTS_BUNDLE_KEY) as ArrayList<Account>?
        dialogTemplateRecyclerBinding!!.dialogTitle.setText(R.string.str_select_account)
        dialogTemplateRecyclerBinding!!.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        dialogTemplateRecyclerBinding!!.recycler.setHasFixedSize(true)
        dialogTemplateRecyclerBinding!!.recycler.adapter =
            AccountListAdapter()
        return dialogTemplateRecyclerBinding!!.root
    }

    private inner class AccountListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
            return AccountHolder(
                ItemDialogAccountBinding.inflate(
                    layoutInflater
                )
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            onBindReceiveAccountItemViewHolder(holder, position)
        }

        fun onBindReceiveAccountItemViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
            val holder = viewHolder as AccountHolder
            val account = mAccounts!![position]
            val baseChain = BaseChain.getChain(account.baseChain)
            val chainConfig = ChainFactory.getChain(baseChain)
            holder.itemDialogAccountBinding.accountAddress.text = account.address
            if (TextUtils.isEmpty(account.nickName)) holder.itemDialogAccountBinding.accountName.text =
                getString(R.string.str_my_wallet) + account.id else holder.itemDialogAccountBinding.accountName.text =
                account.nickName
            if (account.hasPrivateKey) {
                holder.itemDialogAccountBinding.accountKeyState.setImageResource(R.drawable.key_off)
                holder.itemDialogAccountBinding.accountKeyState.setColorFilter(
                    ContextCompat.getColor(
                        sActivity!!, chainConfig.chainColor()
                    ), PorterDuff.Mode.SRC_IN
                )
            } else {
                if (account.isLedger) {
                    holder.itemDialogAccountBinding.accountKeyState.setImageResource(R.drawable.icon_ledger_wallet)
                    holder.itemDialogAccountBinding.accountKeyState.setColorFilter(
                        ContextCompat.getColor(
                            sActivity!!, chainConfig.chainColor()
                        ), PorterDuff.Mode.SRC_IN
                    )
                } else {
                    holder.itemDialogAccountBinding.accountKeyState.setImageResource(R.drawable.watchmode)
                    holder.itemDialogAccountBinding.accountKeyState.colorFilter = null
                }
            }
            WDp.setDpSymbol(
                sActivity,
                sActivity!!.baseDao,
                chainConfig,
                chainConfig.mainDenom(),
                holder.itemDialogAccountBinding.accountDenom
            )
            holder.itemDialogAccountBinding.accountAvailable.text = account.getLastTotal(baseChain)
            holder.itemDialogAccountBinding.rootLayer.setOnClickListener {
                val result = Bundle()
                result.putInt(BaseConstant.POSITION, position)
                parentFragmentManager.setFragmentResult(IBC_RECEIVE_ACCOUNTS_BUNDLE_KEY, result)
                dismiss()
            }
        }

        override fun getItemCount(): Int {
            return mAccounts!!.size
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
        const val IBC_RECEIVE_ACCOUNTS_BUNDLE_KEY = "ibcReceiveAccounts"
        const val ACCOUNTS_BUNDLE_KEY = "accounts"

        @JvmStatic
        fun newInstance(bundle: Bundle?): IBCReceiveAccountsDialog {
            val frag = IBCReceiveAccountsDialog()
            frag.arguments = bundle
            return frag
        }
    }
}