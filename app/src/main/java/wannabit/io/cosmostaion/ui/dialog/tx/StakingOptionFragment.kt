package wannabit.io.cosmostaion.ui.dialog.tx

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cosmos.staking.v1beta1.StakingProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.FragmentStakingOptionBinding
import wannabit.io.cosmostaion.ui.tx.step.UnStakingFragment

class StakingOptionFragment(
    val selectedChain: CosmosLine,
    val validator: StakingProto.Validator?
) : BottomSheetDialogFragment() {

    private var _binding: FragmentStakingOptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStakingOptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        var isClickable = true
        binding.apply {
            unstakeLayout.setOnClickListener {
                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }

                val bottomSheet = UnStakingFragment(selectedChain, validator)
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(requireActivity().supportFragmentManager, StakingOptionFragment(selectedChain, validator)::class.java.name)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
                dismiss()
            }

            switchValidatorLayout.setOnClickListener {

            }

            claimRewardsLayout.setOnClickListener {

            }

            compoundingLayout.setOnClickListener {

            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}