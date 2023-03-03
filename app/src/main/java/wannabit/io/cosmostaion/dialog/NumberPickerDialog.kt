package wannabit.io.cosmostaion.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.DialogNumberPickerBinding

class NumberPickerDialog : DialogFragment() {
    @JvmField
    var selectListener: SelectListener? = null
    private var numberPickerBinding: DialogNumberPickerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.color.colorTrans)
        numberPickerBinding = DialogNumberPickerBinding.inflate(inflater, container, false)
        numberPickerBinding!!.btnNega.setOnClickListener { dialog!!.dismiss() }
        numberPickerBinding!!.btnPosi.setOnClickListener {
            if (selectListener != null) {
                selectListener!!.onSelectValue(numberPickerBinding!!.numberPicker.value)
            }
            dismiss()
        }
        numberPickerBinding!!.numberPicker.minValue = 0
        numberPickerBinding!!.numberPicker.maxValue = 9
        numberPickerBinding!!.numberPicker.wrapSelectorWheel = false
        numberPickerBinding!!.numberPicker.descendantFocusability =
            NumberPicker.FOCUS_BLOCK_DESCENDANTS
        return numberPickerBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        numberPickerBinding = null
    }

    fun interface SelectListener {
        fun onSelectValue(value: Int)
    }

    companion object {
        @JvmStatic
        fun newInstance(): NumberPickerDialog {
            return NumberPickerDialog()
        }
    }
}