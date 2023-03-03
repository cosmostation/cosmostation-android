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
import wannabit.io.cosmostaion.databinding.DialogTemplateChoiceBinding
import wannabit.io.cosmostaion.databinding.ItemDialogCurrencySetBinding
import wannabit.io.cosmostaion.dialog.CurrencySetDialog.CurrencySetAdapter.CurrencyHolder
import wannabit.io.cosmostaion.utils.SimpleDividerItemDecoration

class CurrencySetDialog : DialogFragment() {
    private var dialogTemplateChoiceBinding: DialogTemplateChoiceBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.layout_trans_with_border)
        dialogTemplateChoiceBinding =
            DialogTemplateChoiceBinding.inflate(inflater, container, false)
        dialogTemplateChoiceBinding!!.dialogTitle.visibility = View.GONE
        dialogTemplateChoiceBinding!!.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        dialogTemplateChoiceBinding!!.recycler.setHasFixedSize(true)
        dialogTemplateChoiceBinding!!.recycler.addItemDecoration(
            SimpleDividerItemDecoration(
                activity
            )
        )
        dialogTemplateChoiceBinding!!.recycler.adapter = CurrencySetAdapter()
        return dialogTemplateChoiceBinding!!.root
    }

    private inner class CurrencySetAdapter : RecyclerView.Adapter<CurrencyHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CurrencyHolder {
            return CurrencyHolder(
                ItemDialogCurrencySetBinding.inflate(
                    layoutInflater
                )
            )
        }

        override fun onBindViewHolder(viewHolder: CurrencyHolder, position: Int) {
            onCurrencyItem(viewHolder, position)
        }

        private fun onCurrencyItem(holder: CurrencyHolder, position: Int) {
            val mUnitList = resources.getStringArray(R.array.currency_unit_array)
            holder.itemDialogCurrencySetBinding.currencyName.text = mUnitList[position]
            holder.itemDialogCurrencySetBinding.rootLayer.setOnClickListener {
                val result = Bundle()
                result.putInt(BaseConstant.POSITION, position)
                parentFragmentManager.setFragmentResult(CURRENCY_SET_BUNDLE_KEY, result)
                dismiss()
            }
        }

        override fun getItemCount(): Int {
            return resources.getStringArray(R.array.currency_unit_array).size
        }

        inner class CurrencyHolder(val itemDialogCurrencySetBinding: ItemDialogCurrencySetBinding) :
            RecyclerView.ViewHolder(
                itemDialogCurrencySetBinding.root
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogTemplateChoiceBinding = null
    }

    companion object {
        const val CURRENCY_SET_BUNDLE_KEY = "currency"

        @JvmStatic
        fun newInstance(bundle: Bundle?): CurrencySetDialog {
            val frag = CurrencySetDialog()
            frag.arguments = bundle
            return frag
        }
    }
}