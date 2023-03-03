package wannabit.io.cosmostaion.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.dao.NameService
import wannabit.io.cosmostaion.dao.NameService.NameServiceType
import wannabit.io.cosmostaion.databinding.DialogTemplateRecyclerBinding
import wannabit.io.cosmostaion.databinding.ItemDialogIcnsBinding

class NameConfirmDialog : DialogFragment() {
    private var dialogTemplateRecyclerBinding: DialogTemplateRecyclerBinding? = null
    private var mKeyValue = 0
    private var mNameServices: ArrayList<NameService>? = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.color.colorTrans)
        dialogTemplateRecyclerBinding =
            DialogTemplateRecyclerBinding.inflate(inflater, container, false)
        mKeyValue = arguments!!.getInt(SELECT_NAME_SERVICE_BUNDLE_KEY)
        mNameServices =
            arguments!!.getSerializable(MATCH_NAME_SERVICE_BUNDLE_KEY) as ArrayList<NameService>?
        if (mKeyValue == SELECT_NAME_SERVICE_NAME) {
            dialogTemplateRecyclerBinding!!.dialogTitle.text =
                getString(R.string.str_icns_confirm_title)
        } else {
            dialogTemplateRecyclerBinding!!.dialogTitle.text = mNameServices!![0].name
        }
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
                ItemDialogIcnsBinding.inflate(
                    layoutInflater
                )
            )
        }

        override fun onBindViewHolder(holder: AccountHolder, position: Int) {
            onBindReceiveAccountItemViewHolder(holder, position)
        }

        fun onBindReceiveAccountItemViewHolder(holder: AccountHolder, position: Int) {
            val nameService = mNameServices!![position]
            if (nameService.name.contains("*")) {
                holder.itemDialogIcnsBinding.icnsImg.setImageResource(R.drawable.icon_iov_icns)
                holder.itemDialogIcnsBinding.icnsAddress.text = nameService.address
            } else {
                when (nameService.type) {
                    NameServiceType.ICNS -> {
                        holder.itemDialogIcnsBinding.icnsImg.setImageResource(R.drawable.icon_icns)
                    }
                    NameServiceType.STARGAZE -> {
                        holder.itemDialogIcnsBinding.icnsImg.setImageResource(R.drawable.icon_stargaze_ns)
                    }
                    else -> {
                        holder.itemDialogIcnsBinding.icnsImg.setImageResource(R.drawable.icon_ns)
                    }
                }
                if (mKeyValue == SELECT_NAME_SERVICE_NAME) {
                    holder.itemDialogIcnsBinding.icnsAddress.text = nameService.name
                } else {
                    holder.itemDialogIcnsBinding.icnsAddress.text = nameService.address
                }
            }
            holder.itemDialogIcnsBinding.rootLayer.setOnClickListener {
                val result = Bundle()
                result.putInt(BaseConstant.POSITION, position)
                parentFragmentManager.setFragmentResult(CONFIRM_BUNDLE_KEY, result)
                dismiss()
            }
        }

        override fun getItemCount(): Int {
            return mNameServices!!.size
        }

        inner class AccountHolder(val itemDialogIcnsBinding: ItemDialogIcnsBinding) :
            RecyclerView.ViewHolder(
                itemDialogIcnsBinding.root
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogTemplateRecyclerBinding = null
    }

    companion object {
        const val SELECT_NAME_SERVICE_NAME = 8500
        const val SELECT_NAME_SERVICE_BUNDLE_KEY = "nameserviceType"
        const val MATCH_NAME_SERVICE_BUNDLE_KEY = "nameservice"
        const val CONFIRM_BUNDLE_KEY = "confirm"

        @JvmStatic
        fun newInstance(bundle: Bundle?): NameConfirmDialog {
            val frag = NameConfirmDialog()
            frag.arguments = bundle
            return frag
        }
    }
}