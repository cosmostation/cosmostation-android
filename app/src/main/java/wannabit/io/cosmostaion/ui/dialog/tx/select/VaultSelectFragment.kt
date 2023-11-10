package wannabit.io.cosmostaion.ui.dialog.tx.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.databinding.FragmentVaultSelectBinding
import wannabit.io.cosmostaion.ui.tx.step.neutron.VaultFragment
import wannabit.io.cosmostaion.ui.tx.step.neutron.VaultType

class VaultSelectFragment(private val selectedChain: CosmosLine) : BottomSheetDialogFragment() {

    private var _binding: FragmentVaultSelectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVaultSelectBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickAction()
    }

    private fun clickAction() {
        binding.apply {
            depositView.setOnClickListener {
                lifecycleScope.launch {
                    delay(300)
                    dismiss()
                    withContext(Dispatchers.Main) {
                        val bottomSheet = VaultFragment(selectedChain as ChainNeutron, VaultType.DEPOSIT)
                        bottomSheet.show(requireActivity().supportFragmentManager, VaultFragment::class.java.name)
                    }
                }
            }

            withdrawView.setOnClickListener {
                lifecycleScope.launch {
                    delay(300)
                    dismiss()
                    withContext(Dispatchers.Main) {
                        val bottomSheet = VaultFragment(selectedChain as ChainNeutron, VaultType.WITHDRAW)
                        bottomSheet.show(requireActivity().supportFragmentManager, VaultFragment::class.java.name)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}