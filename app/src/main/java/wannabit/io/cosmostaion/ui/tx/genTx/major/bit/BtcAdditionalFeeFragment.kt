package wannabit.io.cosmostaion.ui.tx.genTx.major.bit

import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.FragmentBtcAdditionalFeeBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BtcAdditionalFeeFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBtcAdditionalFeeBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private lateinit var babylonChain: BaseChain
    private var babylonFeeAmount: BigDecimal = BigDecimal.ZERO

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: BaseChain, babylonChain: BaseChain?, babylonFeeAmount: String
        ): BtcAdditionalFeeFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putParcelable("babylonChain", babylonChain)
                putString("babylonFeeAmount", babylonFeeAmount)
            }
            val fragment = BtcAdditionalFeeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBtcAdditionalFeeBinding.inflate(layoutInflater, container, false)
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
            arguments?.getParcelable("babylonChain", BaseChain::class.java)
                ?.let { babylonChain = it }

        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
            (arguments?.getParcelable("babylonChain") as? BaseChain)?.let {
                babylonChain = it
            }
        }
        babylonFeeAmount =
            arguments?.getString("babylonFeeAmount")?.toBigDecimal() ?: BigDecimal.ZERO

        binding.apply {
            feeView.setBackgroundResource(R.drawable.cell_bg)
            walletImg.setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.color_base02),
                PorterDuff.Mode.SRC_IN
            )

            val denom = if (selectedChain.isTestnet) "tBABY" else "BABY"
            val address = babylonChain.address.take(8) + "..." + babylonChain.address.takeLast(8)

            dialogTitle.text = getString(R.string.title_additional_fee, denom)
            val htmlString = if (selectedChain.isTestnet) {
                getString(R.string.str_additional_sbtc_fee_msg, address)
            } else {
                getString(R.string.str_additional_btc_fee_msg, address)
            }
            dialogMsg.text = Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY)
            insufficientAvailable.text = getString(R.string.str_insufficient_balance, denom)

            val babylonBalance =
                babylonChain.cosmosFetcher()?.availableAmount(babylonChain.getMainAssetDenom())
                    ?: BigDecimal.ZERO

            insufficientAvailable.visibleOrGone(babylonBalance < babylonFeeAmount)

            BaseData.getAsset(babylonChain.apiName, babylonChain.getMainAssetDenom())?.let { asset ->
                val dpBabylonBalance = babylonBalance.movePointLeft(asset.decimals ?: 8)
                    ?.setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                myAvailable.text = formatAmount(dpBabylonBalance.toString(), asset.decimals ?: 8)
                myAvailableDenom.text = denom

                feeTokenImg.setTokenImg(asset)
                feeToken.text = asset.symbol

                val price = BaseData.getPrice(asset.coinGeckoId)
                val dpAmount = babylonFeeAmount.movePointLeft(asset.decimals ?: 8)
                    ?.setScale(asset.decimals ?: 8, RoundingMode.DOWN)
                val value = price.multiply(dpAmount)

                feeAmount.text = formatAmount(dpAmount.toString(), asset.decimals ?: 8)
                feeValue.text = formatAssetValue(value)
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnCancel.setOnClickListener {
                dismiss()
            }

            btnStake.setOnClickListener {
                val bundle = Bundle()
                requireActivity().supportFragmentManager.setFragmentResult("tx", bundle)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}