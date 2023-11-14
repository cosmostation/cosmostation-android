package wannabit.io.cosmostaion.ui.dialog.tx.kava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.FragmentMintOptionBinding
import wannabit.io.cosmostaion.ui.tx.info.kava.ClickListener

class MintOptionFragment(
    val selectedChain: CosmosLine,
    private val mintType: String?,
    val listener: ClickListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentMintOptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMintOptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        clickAction()
    }

    private fun initView() {
        binding.apply {
            deposit.text = getString(R.string.str_deposit_denom, mintType?.split("-")?.firstOrNull()?.uppercase() ?: "")
            withdraw.text = getString(R.string.str_withdraw_denom, mintType?.split("-")?.firstOrNull()?.uppercase() ?: "")
            borrow.text = getString(R.string.str_borrow_denom, "USDX")
            repay.text = getString(R.string.str_repay_denom, "USDX")
        }
    }

    private fun clickAction() {
        binding.apply {
            depositLayout.setOnClickListener {
                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }
                listener.mintDeposit(mintType)
                dismiss()
            }

            withdrawLayout.setOnClickListener {
                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }
                listener.mintWithdraw(mintType)
                dismiss()
            }

            borrowLayout.setOnClickListener {
                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }
                listener.mintBorrow(mintType)
                dismiss()
            }

            repayLayout.setOnClickListener {
                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }
                listener.mintRepay(mintType)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}