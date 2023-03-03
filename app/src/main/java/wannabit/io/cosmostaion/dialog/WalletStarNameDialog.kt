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
import wannabit.io.cosmostaion.dialog.WalletStarNameDialog.WalletForStarNameAdapter.WalletForStarNameHolder
import wannabit.io.cosmostaion.utils.StarnameAssets
import wannabit.io.cosmostaion.utils.WDp

class WalletStarNameDialog : DialogFragment() {
    private var dialogTemplateRecyclerBinding: DialogTemplateRecyclerBinding? = null
    private var mStarNameAsset: StarnameAssets? = null
    private var mUri: String? = null
    private var mWalletList = ArrayList<Account>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.layout_trans_with_border)
        dialogTemplateRecyclerBinding =
            DialogTemplateRecyclerBinding.inflate(inflater, container, false)
        try {
            mStarNameAsset = arguments!!.getParcelable(STAR_NAME_ASSET_BUNDLE_KEY)
            mWalletList = sActivity!!.baseDao.onSelectAccountsByChain(
                BaseChain.getChain(
                    mStarNameAsset!!.chainName
                )
            )
        } catch (e: Exception) {
            mUri = arguments!!.getString(STAR_NAME_URI_BUNDLE_KEY)
            mWalletList = sActivity!!.baseDao.onSelectAccountsByChain(
                BaseChain.getChain(
                    StarnameAssets.getStarNameGetChain(mUri)
                )
            )
        }
        dialogTemplateRecyclerBinding!!.dialogTitle.setText(R.string.str_select_wallet_for_address)
        dialogTemplateRecyclerBinding!!.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        dialogTemplateRecyclerBinding!!.recycler.setHasFixedSize(true)
        dialogTemplateRecyclerBinding!!.recycler.adapter = WalletForStarNameAdapter()
        return dialogTemplateRecyclerBinding!!.root
    }

    private inner class WalletForStarNameAdapter : RecyclerView.Adapter<WalletForStarNameHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): WalletForStarNameHolder {
            return WalletForStarNameHolder(
                ItemDialogAccountBinding.inflate(
                    layoutInflater
                )
            )
        }

        override fun onBindViewHolder(holder: WalletForStarNameHolder, position: Int) {
            val account = mWalletList[position]
            val baseChain = BaseChain.getChain(account.baseChain)
            val chainConfig = ChainFactory.getChain(baseChain)
            WDp.setDpSymbol(
                sActivity,
                sActivity!!.baseDao,
                chainConfig,
                chainConfig.mainDenom(),
                holder.itemDialogAccountBinding.accountDenom
            )
            holder.itemDialogAccountBinding.accountAddress.text = account.address
            holder.itemDialogAccountBinding.accountAvailable.text = account.getLastTotal(baseChain)
            holder.itemDialogAccountBinding.accountKeyState.setColorFilter(
                ContextCompat.getColor(
                    sActivity!!, R.color.colorGray0
                ), PorterDuff.Mode.SRC_IN
            )
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
            if (TextUtils.isEmpty(account.nickName)) {
                holder.itemDialogAccountBinding.accountName.text =
                    getString(R.string.str_my_wallet) + account.id
            } else {
                holder.itemDialogAccountBinding.accountName.text = account.nickName
            }
            holder.itemDialogAccountBinding.rootLayer.setOnClickListener {
                (activity as BaseActivity?)!!.onChoiceStarnameResourceAddress(account.address)
                dismiss()
            }
        }

        override fun getItemCount(): Int {
            return mWalletList.size
        }

        inner class WalletForStarNameHolder(var itemDialogAccountBinding: ItemDialogAccountBinding) :
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
        const val STAR_NAME_ASSET_BUNDLE_KEY = "asset"
        const val STAR_NAME_URI_BUNDLE_KEY = "chainUri"

        @JvmStatic
        fun newInstance(bundle: Bundle?): WalletStarNameDialog {
            val frag = WalletStarNameDialog()
            frag.arguments = bundle
            return frag
        }
    }
}