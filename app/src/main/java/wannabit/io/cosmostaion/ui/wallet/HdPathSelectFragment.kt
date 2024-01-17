package wannabit.io.cosmostaion.ui.wallet

import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.FragmentHdPathSelectBinding
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment

class HdPathSelectFragment(
    private val onHdPathSelected: (Int) -> Unit
) : BaseTxFragment() {

    private var _binding: FragmentHdPathSelectBinding? = null
    private val binding get() = _binding!!

    var selectedNumber = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHdPathSelectBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            pathView.setBackgroundResource(R.drawable.cell_bg)
            numberPicker.minValue = 0
            numberPicker.maxValue = 9
            numberPicker.wrapSelectorWheel = false
            numberPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

            setNumberPickerTextColor(numberPicker)

            updateView(selectedNumber)
            numberPicker.setOnValueChangedListener { _, _, newVal ->
                updateView(newVal)
            }
        }
    }

    private fun setNumberPickerTextColor(numberPicker: NumberPicker) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            val count = numberPicker.childCount
            for (i in 0..count) {
                val child = numberPicker.getChildAt(i)
                if (child is EditText) {
                    child.setTextColor(Color.WHITE)
                    numberPicker.invalidate()
                    val selectorWheelPaintField =
                        numberPicker.javaClass.getDeclaredField("mSelectorWheelPaint")
                    var accessible = selectorWheelPaintField.isAccessible
                    selectorWheelPaintField.isAccessible = true
                    (selectorWheelPaintField.get(numberPicker) as Paint).color = Color.WHITE
                    selectorWheelPaintField.isAccessible = accessible
                    numberPicker.invalidate()
                    val selectionDividerField =
                        numberPicker.javaClass.getDeclaredField("mSelectionDivider")
                    accessible = selectionDividerField.isAccessible
                    selectionDividerField.isAccessible = true
                    selectionDividerField.set(numberPicker, null)
                    selectionDividerField.isAccessible = accessible
                    numberPicker.invalidate()
                }
            }
        } else {
            numberPicker.textColor = Color.WHITE
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnConfirm.setOnClickListener {
                onHdPathSelected(numberPicker.value)
                dismiss()
            }
        }
    }

    private fun updateView(numberValue: Int) {
        binding.apply {
            numberPicker.value = numberValue
            updateHdPath("m/44'/118'/0'/0/X", cosmosLastHdPath, numberValue)
            updateHdPath("m/44'/60'/0'/0/X", etherLastHdPath, numberValue)
            updateHdPath("m/44'/459'/0'/0'/X", kavaLastHdPath, numberValue)
        }
    }

    private fun updateHdPath(basePath: String, hdPath: TextView, numberValue: Int) {
        val dpHdPath = basePath.replace("X", numberValue.toString())
        val spannable = SpannableString(dpHdPath)
        val splitHdPath = dpHdPath.split("/").last()

        val startIndexOfSplit = dpHdPath.lastIndexOf(splitHdPath)

        if (startIndexOfSplit != -1) {
            spannable.setSpan(
                ForegroundColorSpan(Color.WHITE),
                startIndexOfSplit,
                startIndexOfSplit + splitHdPath.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        hdPath.text = spannable
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}