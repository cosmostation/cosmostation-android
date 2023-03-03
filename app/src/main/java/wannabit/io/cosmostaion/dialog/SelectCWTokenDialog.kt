package wannabit.io.cosmostaion.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.common.collect.Sets
import com.squareup.picasso.Picasso
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.dao.MintscanToken
import wannabit.io.cosmostaion.databinding.DialogSelectDisplayTokenBinding
import wannabit.io.cosmostaion.databinding.ItemDialogContractBinding
import wannabit.io.cosmostaion.dialog.SelectCWTokenDialog.ContractListAdapter.ContractHolder

class SelectCWTokenDialog : BottomSheetDialogFragment(), View.OnClickListener {
    private var dialogSelectDisplayTokenBinding: DialogSelectDisplayTokenBinding? = null
    private val mContractAssets = ArrayList<MintscanToken>()
    private var checkedContractSet: MutableSet<String> = Sets.newHashSet()
    private var mAccount: Account? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialogSelectDisplayTokenBinding =
            DialogSelectDisplayTokenBinding.inflate(inflater, container, false)
        mAccount = sActivity!!.baseDao.onSelectAccount(sActivity!!.baseDao.lastUser)
        dialogSelectDisplayTokenBinding!!.dialogTitle.text =
            getString(R.string.str_select_contract_token)
        val mintScanTokenList: ArrayList<MintscanToken> = if (sActivity!!.mChainConfig.baseChain() == BaseChain.JUNO_MAIN) sActivity!!.baseDao.mCw20Tokens else sActivity!!.baseDao.mErc20Tokens
        for (asset in mintScanTokenList) {
            if (!asset.default_show) {
                mContractAssets.add(asset)
            }
        }
        checkedContractSet = sActivity!!.baseDao.getUserFavoTokens(mAccount!!.address)
        dialogSelectDisplayTokenBinding!!.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        dialogSelectDisplayTokenBinding!!.recycler.setHasFixedSize(true)
        dialogSelectDisplayTokenBinding!!.recycler.adapter = ContractListAdapter()
        dialogSelectDisplayTokenBinding!!.btnCancel.setOnClickListener(this)
        dialogSelectDisplayTokenBinding!!.btnConfirm.setOnClickListener(this)
        return dialogSelectDisplayTokenBinding!!.root
    }

    override fun onClick(v: View) {
        if (v == dialogSelectDisplayTokenBinding!!.btnCancel) {
            dismiss()
        } else if (v == dialogSelectDisplayTokenBinding!!.btnConfirm) {
            sActivity!!.baseDao.setUserFavoTokens(mAccount!!.address, checkedContractSet)
            parentFragmentManager.setFragmentResult(SELECT_CW_TOKEN_BUNDLE_KEY, Bundle())
            dismiss()
        }
    }

    private inner class ContractListAdapter : RecyclerView.Adapter<ContractHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ContractHolder {
            return ContractHolder(
                ItemDialogContractBinding.inflate(
                    layoutInflater
                )
            )
        }

        override fun onBindViewHolder(holder: ContractHolder, position: Int) {
            val asset = mContractAssets[position]
            holder.itemDialogContractBinding.switchDisplayToken.setOnCheckedChangeListener(null)
            Picasso.get().load(asset.assetImg()).fit().placeholder(R.drawable.token_default)
                .error(R.drawable.token_default).into(holder.itemDialogContractBinding.chainImg)
            holder.itemDialogContractBinding.chainName.text = asset.symbol
            holder.itemDialogContractBinding.switchDisplayToken.isChecked =
                checkedContractSet.contains(asset.address)
            holder.itemDialogContractBinding.switchDisplayToken.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
                if (isChecked) {
                    checkedContractSet.add(asset.address)
                } else {
                    checkedContractSet.remove(asset.address)
                }
            }
        }

        override fun getItemCount(): Int {
            return mContractAssets.size
        }

        inner class ContractHolder(val itemDialogContractBinding: ItemDialogContractBinding) :
            RecyclerView.ViewHolder(
                itemDialogContractBinding.root
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogSelectDisplayTokenBinding = null
    }

    private val sActivity: BaseActivity?
        get() = activity as BaseActivity?

    companion object {
        const val SELECT_CW_TOKEN_BUNDLE_KEY = "selectCWToken"

        @JvmStatic
        fun newInstance(bundle: Bundle?): SelectCWTokenDialog {
            val frag = SelectCWTokenDialog()
            frag.arguments = bundle
            return frag
        }
    }
}