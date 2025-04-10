package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBabylon
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.databinding.FragmentBabylonBtcStatusBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BabylonBtcStatusFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBabylonBtcStatusBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): BabylonBtcStatusFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = BabylonBtcStatusFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBabylonBtcStatusBinding.inflate(layoutInflater, container, false)
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

        val denom = if (selectedChain.isTestnet) {
            "sbtc"
        } else {
            "btc"
        }
        val network = if (selectedChain.isTestnet) {
            "bitcoin-testnet"
        } else {
            "bitcoin"
        }

        binding.apply {
            BaseData.getAsset(network, denom)?.let { asset ->
                title.text = getString(R.string.title_staked_btc, asset.symbol)

                var btcStakedAmount = BigDecimal.ZERO
                (selectedChain as ChainBabylon).babylonFetcher?.btcStakedStatus?.forEach { status ->
                    if (status["status_desc"].asString == "ACTIVE") {
                        btcStakedAmount =
                            btcStakedAmount.add(status["total_sat"].asString.toBigDecimal())
                    }
                }
                val dpBtcStakedAmount = btcStakedAmount.movePointLeft(asset.decimals ?: 6)
                    .setScale(asset.decimals ?: 6, RoundingMode.DOWN)

                tokenImg.setTokenImg(asset)
                btcStakedStatusAmount.text =
                    formatAmount(dpBtcStakedAmount.toString(), asset.decimals ?: 6)
                btcStakedDenom.text = asset.symbol
            }
        }
    }

    private fun setUpClickAction() {
        binding.btnConfirm.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}