package wannabit.io.cosmostaion.ui.tx.option.swap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.FragmentSlippageBinding

interface SlippageListener {
    fun slippage(position: Int)
}

class SlippageFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSlippageBinding? = null
    private val binding get() = _binding!!

    private var skipSlippage = "1"

    companion object {
        @JvmStatic
        fun newInstance(
            skipSlippage: String, listener: SlippageListener
        ): SlippageFragment {
            val args = Bundle().apply {
                putString("skipSlippage", skipSlippage)
            }
            val fragment = SlippageFragment()
            fragment.arguments = args
            fragment.slippageListener = listener
            return fragment
        }
    }

    private var slippageListener: SlippageListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlippageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        arguments?.getString("skipSlippage")?.let { skipSlippage = it }
        binding.apply {
            slippage1SelectImg.visibleOrGone(skipSlippage == "1")
            slippage3SelectImg.visibleOrGone(skipSlippage == "3")
            slippage5SelectImg.visibleOrGone(skipSlippage == "5")
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            slippage1.setOnClickListener {
                slippageListener?.slippage(1)
                dismiss()
            }

            slippage3.setOnClickListener {
                slippageListener?.slippage(3)
                dismiss()
            }

            slippage5.setOnClickListener {
                slippageListener?.slippage(5)
                dismiss()
            }
        }
    }
}