package wannabit.io.cosmostaion.ui.dialog.path

import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.FragmentHdPathSelectBinding

class HdPathSelectDialog(private val onValueSelected: (Int) -> Unit) : DialogFragment() {

    private var _binding: FragmentHdPathSelectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHdPathSelectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        clickAction()
    }

    private fun initView() {
        isCancelable = false
        dialog?.let {
            it.window?.setBackgroundDrawableResource(R.color.color_transparent)
        }
        binding.apply {
            numberPicker.minValue = 0
            numberPicker.maxValue = 9
            numberPicker.wrapSelectorWheel = false
            numberPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

            setNumberPickerTextColor(numberPicker, Color.WHITE)
        }
    }

    private fun clickAction() {
        binding.apply {
            btnCancel.setOnClickListener {
                dismiss()
            }

            btnConfirm.setOnClickListener {
                onValueSelected(numberPicker.value)
                dismiss()
            }
        }
    }

    private fun setNumberPickerTextColor(numberPicker: NumberPicker, color: Int){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            val count = numberPicker.childCount
            for (i in 0..count) {
                val child = numberPicker.getChildAt(i)
                if (child is EditText) {
                    child.setTextColor(color)
                    numberPicker.invalidate()
                    val selectorWheelPaintField = numberPicker.javaClass.getDeclaredField("mSelectorWheelPaint")
                    var accessible = selectorWheelPaintField.isAccessible
                    selectorWheelPaintField.isAccessible = true
                    (selectorWheelPaintField.get(numberPicker) as Paint).color = color
                    selectorWheelPaintField.isAccessible = accessible
                    numberPicker.invalidate()
                    val selectionDividerField = numberPicker.javaClass.getDeclaredField("mSelectionDivider")
                    accessible = selectionDividerField.isAccessible
                    selectionDividerField.isAccessible = true
                    selectionDividerField.set(numberPicker, null)
                    selectionDividerField.isAccessible = accessible
                    numberPicker.invalidate()
                }
            }
        } else {
            numberPicker.textColor = color
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}