package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.databinding.FragmentChainEndpointResetBinding

interface ResetListener {

    fun reset()
}

class ChainEndpointResetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentChainEndpointResetBinding? = null
    private val binding get() = _binding!!

    companion object {
        @JvmStatic
        fun newInstance(listener: ResetListener?): ChainEndpointResetFragment {
            val fragment = ChainEndpointResetFragment()
            fragment.resetListener = listener
            return fragment
        }
    }

    private var resetListener: ResetListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChainEndpointResetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickAction()
    }

    private fun setUpClickAction() {
        binding.apply {
            btnCancel.setOnClickListener {
                dismiss()
            }

            btnConfirm.setOnClickListener {
                resetListener?.reset()
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}