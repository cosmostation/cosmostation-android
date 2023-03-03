package wannabit.io.cosmostaion.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.databinding.DialogTemplateRecyclerBinding
import wannabit.io.cosmostaion.databinding.ItemDialogPriceColorOptionBinding

class PriceColorChangeDialog : DialogFragment() {
    private var dialogTemplateRecyclerBinding: DialogTemplateRecyclerBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.layout_trans_with_border)
        dialogTemplateRecyclerBinding =
            DialogTemplateRecyclerBinding.inflate(inflater, container, false)
        dialogTemplateRecyclerBinding!!.dialogTitle.setText(R.string.str_price_color_option_title)
        dialogTemplateRecyclerBinding!!.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        dialogTemplateRecyclerBinding!!.recycler.setHasFixedSize(true)
        dialogTemplateRecyclerBinding!!.recycler.adapter = PriceColorOptionListAdapter()
        return dialogTemplateRecyclerBinding!!.root
    }

    private inner class PriceColorOptionListAdapter :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(
            viewGroup: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder {
            return PriceColorChangeHolder(
                ItemDialogPriceColorOptionBinding.inflate(
                    layoutInflater
                )
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            onBindSelectedOptionItemViewHolder(holder, position)
        }

        private fun onBindSelectedOptionItemViewHolder(
            viewHolder: RecyclerView.ViewHolder,
            position: Int
        ) {
            val holder = viewHolder as PriceColorChangeHolder
            holder.itemDialogPriceColorOptionBinding.optionNumber.text = (position + 1).toString()
            if (position == 0) {
                holder.itemDialogPriceColorOptionBinding.iconPriceColorUp.setImageResource(R.drawable.icon_pricegreen)
                holder.itemDialogPriceColorOptionBinding.iconPriceColorDown.setImageResource(R.drawable.icon_pricered)
                holder.itemDialogPriceColorOptionBinding.rootLayer.setOnClickListener {
                    val result = Bundle()
                    result.putInt(BaseConstant.POSITION, position + 1)
                    parentFragmentManager.setFragmentResult(BaseConstant.PRE_PRICE_COLOR, result)
                    sActivity!!.baseDao.priceColorOption = position + 1
                    dismiss()
                }
            } else {
                holder.itemDialogPriceColorOptionBinding.iconPriceColorUp.setImageResource(R.drawable.icon_pricered)
                holder.itemDialogPriceColorOptionBinding.iconPriceColorDown.setImageResource(R.drawable.icon_pricegreen)
                holder.itemDialogPriceColorOptionBinding.rootLayer.setOnClickListener {
                    val result = Bundle()
                    result.putInt(BaseConstant.POSITION, position + 1)
                    parentFragmentManager.setFragmentResult(BaseConstant.PRE_PRICE_COLOR, result)
                    sActivity!!.baseDao.priceColorOption = position + 1
                    dismiss()
                }
            }
        }

        override fun getItemCount(): Int {
            return 2
        }

        inner class PriceColorChangeHolder(val itemDialogPriceColorOptionBinding: ItemDialogPriceColorOptionBinding) :
            RecyclerView.ViewHolder(
                itemDialogPriceColorOptionBinding.root
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogTemplateRecyclerBinding = null
    }

    private val sActivity: BaseActivity?
        get() = activity as BaseActivity?

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?): PriceColorChangeDialog {
            val frag = PriceColorChangeDialog()
            frag.arguments = bundle
            return frag
        }
    }
}