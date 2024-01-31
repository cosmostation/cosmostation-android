package wannabit.io.cosmostaion.ui.option.tx.swap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.staking.v1beta1.StakingProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kava.incentive.v1beta1.QueryProto
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.FragmentSlippageBinding
import wannabit.io.cosmostaion.ui.option.tx.kava.EarnClickListener
import wannabit.io.cosmostaion.ui.option.tx.kava.EarnOptionFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.ClaimIncentiveFragment

interface SlippageListener {
    fun slippage(position: Int)
}

class SlippageFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSlippageBinding? = null
    private val binding get() = _binding!!

    companion object {
        @JvmStatic
        fun newInstance(
            listener: SlippageListener
        ): SlippageFragment {
            val fragment = SlippageFragment()
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

        setUpClickAction()
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