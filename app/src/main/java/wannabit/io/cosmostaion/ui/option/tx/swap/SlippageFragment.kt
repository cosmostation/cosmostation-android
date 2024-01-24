package wannabit.io.cosmostaion.ui.option.tx.swap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.databinding.FragmentSlippageBinding

class SlippageFragment(
    val listener: SlippageListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentSlippageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlippageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickAction()
    }

    private fun clickAction() {
        binding.apply {
            slippage1.setOnClickListener {
                listener.slippage(1)
                dismiss()
            }

            slippage3.setOnClickListener {
                listener.slippage(3)
                dismiss()
            }

            slippage5.setOnClickListener {
                listener.slippage(5)
                dismiss()
            }
        }
    }
}

interface SlippageListener {
    fun slippage(position: Int)
}