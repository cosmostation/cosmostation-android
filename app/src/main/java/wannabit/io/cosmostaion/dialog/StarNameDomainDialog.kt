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
import wannabit.io.cosmostaion.databinding.DialogTemplateRecyclerBinding
import wannabit.io.cosmostaion.databinding.ItemDialogStarnameDomainListBinding
import wannabit.io.cosmostaion.dialog.StarNameDomainDialog.DomainListAdapter.DomainListHolder

class StarNameDomainDialog : DialogFragment() {
    private var dialogTemplateRecyclerBinding: DialogTemplateRecyclerBinding? = null
    private var mStarNameDomain: ArrayList<String>? = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.layout_trans_with_border)
        dialogTemplateRecyclerBinding =
            DialogTemplateRecyclerBinding.inflate(inflater, container, false)
        mStarNameDomain = arguments!!.getStringArrayList("domain")
        dialogTemplateRecyclerBinding!!.dialogTitle.setText(R.string.str_select_starname_domain)
        dialogTemplateRecyclerBinding!!.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        dialogTemplateRecyclerBinding!!.recycler.setHasFixedSize(true)
        dialogTemplateRecyclerBinding!!.recycler.adapter = DomainListAdapter()
        return dialogTemplateRecyclerBinding!!.root
    }

    private inner class DomainListAdapter : RecyclerView.Adapter<DomainListHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DomainListHolder {
            return DomainListHolder(
                ItemDialogStarnameDomainListBinding.inflate(
                    layoutInflater
                )
            )
        }

        override fun onBindViewHolder(holder: DomainListHolder, position: Int) {
            onBindDomainItemViewHolder(holder, position)
        }

        private fun onBindDomainItemViewHolder(holder: DomainListHolder, position: Int) {
            val domain = mStarNameDomain!![position]
            holder.itemDialogStarnameDomainListBinding.domainName.text = domain
            holder.itemDialogStarnameDomainListBinding.rootLayer.setOnClickListener {
                val result = Bundle()
                result.putInt(BaseConstant.POSITION, position)
                parentFragmentManager.setFragmentResult(STAR_NAME_DOMAIN_BUNDLE_KEY, result)
                dismiss()
            }
        }

        override fun getItemCount(): Int {
            return mStarNameDomain!!.size
        }

        inner class DomainListHolder(val itemDialogStarnameDomainListBinding: ItemDialogStarnameDomainListBinding) :
            RecyclerView.ViewHolder(
                itemDialogStarnameDomainListBinding.root
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogTemplateRecyclerBinding = null
    }

    companion object {
        const val STAR_NAME_DOMAIN_BUNDLE_KEY = "starNameDomain"

        @JvmStatic
        fun newInstance(bundle: Bundle?): StarNameDomainDialog {
            val frag = StarNameDomainDialog()
            frag.arguments = bundle
            return frag
        }
    }
}