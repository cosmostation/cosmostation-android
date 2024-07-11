package wannabit.io.cosmostaion.ui.option.tx.general

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.databinding.FragmentVaultSelectBinding
import wannabit.io.cosmostaion.ui.tx.step.neutron.VaultFragment
import wannabit.io.cosmostaion.ui.tx.step.neutron.VaultType

class VaultSelectFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentVaultSelectBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: ChainNeutron

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: ChainNeutron): VaultSelectFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = VaultSelectFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVaultSelectBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpClickAction()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", ChainNeutron::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? ChainNeutron)?.let {
                selectedChain = it
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            depositView.setOnClickListener {
                handleOneClickWithDelay(
                    VaultFragment.newInstance(selectedChain, VaultType.DEPOSIT)
                )
            }

            withdrawView.setOnClickListener {
                handleOneClickWithDelay(
                    VaultFragment.newInstance(selectedChain, VaultType.WITHDRAW)
                )
            }
        }
    }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
        dismiss()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}