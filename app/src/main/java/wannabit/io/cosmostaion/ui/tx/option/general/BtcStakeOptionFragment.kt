package wannabit.io.cosmostaion.ui.tx.option.general

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.common.toHex
import wannabit.io.cosmostaion.databinding.FragmentBtcStakeOptionBinding
import wannabit.io.cosmostaion.ui.tx.genTx.major.BtcStakingFragment
import wannabit.io.cosmostaion.ui.tx.genTx.major.BtcUnStakingFragment

class BtcStakeOptionFragment(
    private val selectedChain: BaseChain, private val staked: Pair<JsonObject, FinalityProvider>?
) : BottomSheetDialogFragment() {

    private var _binding: FragmentBtcStakeOptionBinding? = null
    private val binding get() = _binding!!

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBtcStakeOptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickAction()
    }

    private fun setUpClickAction() {
        binding.apply {
            stakeLayout.setOnClickListener {
                handleOneClickWithDelay(
                    BtcStakingFragment.newInstance(
                        selectedChain, staked?.second?.provider?.btcPk?.toByteArray()?.toHex()
                    )
                )
            }

            unstakeLayout.setOnClickListener {
                handleOneClickWithDelay(
                    BtcUnStakingFragment(
                        selectedChain, staked
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