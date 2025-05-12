package wannabit.io.cosmostaion.ui.tx.info.major.btc

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.databinding.FragmentBtcInfoBinding
import kotlin.math.roundToInt

class BtcInfoFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBtcInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): BtcInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = BtcInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBtcInfoBinding.inflate(layoutInflater, container, false)
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

        val blockPerHour = 6.0
        val stakingBlock =
            (selectedChain as ChainBitCoin86).btcFetcher?.btcParams?.maxStakingTimeBlocks?.toDouble()
                ?: 0.0
        val stakingHour = stakingBlock / blockPerHour
        val btcStakingTimeLockWeeks = ((stakingHour / 24 / 7 / 5).roundToInt()) * 5
        binding.dialogMsg0.text = Html.fromHtml(
            getString(
                R.string.str_time_lock_msg,
                selectedChain.coinSymbol,
                btcStakingTimeLockWeeks.toString()
            ), Html.FROM_HTML_MODE_LEGACY
        )
        binding.dialogMsg1.text = Html.fromHtml(getString(R.string.str_unstaking_msg), Html.FROM_HTML_MODE_LEGACY)
    }

    private fun setUpClickAction() {
        binding.apply {
            btnConfirm.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}