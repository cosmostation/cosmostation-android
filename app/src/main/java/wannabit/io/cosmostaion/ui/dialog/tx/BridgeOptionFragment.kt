package wannabit.io.cosmostaion.ui.dialog.tx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.FragmentBridgeOptionBinding
import wannabit.io.cosmostaion.ui.main.chain.BridgeClickListener

class BridgeOptionFragment(
    val selectedChain: CosmosLine, private val denom: String, val listener: BridgeClickListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentBridgeOptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBridgeOptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickAction()
    }

    private fun clickAction() {
        binding.apply {
            bep3Layout.setOnClickListener {
                listener.bep3Transfer(selectedChain, denom)
                dismiss()
            }

            transferLayout.setOnClickListener {
                listener.simpleTransfer(selectedChain, denom)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}