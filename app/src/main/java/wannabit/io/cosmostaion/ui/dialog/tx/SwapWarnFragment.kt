package wannabit.io.cosmostaion.ui.dialog.tx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.databinding.FragmentSwapWarnBinding

class SwapWarnFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSwapWarnBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSwapWarnBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickAction()
    }

    private fun clickAction() {
        binding.apply {
            binding.btnDisplay.setOnClickListener {
                BaseData.setSwapWarn()
                dismiss()
            }

            binding.btnConfirm.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}