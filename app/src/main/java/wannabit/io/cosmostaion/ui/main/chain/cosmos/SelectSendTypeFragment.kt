package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.FragmentSelectSendTypeBinding
import wannabit.io.cosmostaion.ui.tx.genTx.CommonTransferFragment
import wannabit.io.cosmostaion.ui.tx.genTx.SendAssetType

class SelectSendTypeFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSelectSendTypeBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private lateinit var denom: String

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain, denom: String): SelectSendTypeFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putString("denom", denom)
            }
            val fragment = SelectSendTypeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectSendTypeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpClickAction()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
        }
        denom = arguments?.getString("denom") ?: ""

        binding.dialogMsg0.text =
            Html.fromHtml(getString(R.string.str_send_to_evm), Html.FROM_HTML_MODE_LEGACY)
        binding.dialogMsg1.text = Html.fromHtml(
            getString(R.string.str_send_to_cosmos, selectedChain.accountPrefix + 1),
            Html.FROM_HTML_MODE_LEGACY
        )
    }

    private fun setUpClickAction() {
        binding.apply {
            evmLayout.setOnClickListener {
                handleOneClickWithDelay(
                    CommonTransferFragment.newInstance(
                        selectedChain, denom, SendAssetType.ONLY_EVM_COIN
                    )
                )
            }

            cosmosLayout.setOnClickListener {
                handleOneClickWithDelay(
                    CommonTransferFragment.newInstance(
                        selectedChain, denom, SendAssetType.ONLY_COSMOS_COIN
                    )
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
