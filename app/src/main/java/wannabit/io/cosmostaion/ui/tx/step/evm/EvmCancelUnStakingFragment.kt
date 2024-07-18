package wannabit.io.cosmostaion.ui.tx.step.evm

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.databinding.FragmentCancelUnBondingBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TransferTxResultActivity
import wannabit.io.cosmostaion.ui.tx.info.UnBondingEntry
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import wannabit.io.cosmostaion.ui.tx.step.TransferStyle
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

class EvmCancelUnStakingFragment : BaseTxFragment() {

    private var _binding: FragmentCancelUnBondingBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private lateinit var unBondingEntry: UnBondingEntry

    private var selectedFeePosition = 0

    private val evmGasPrices: List<BigInteger> = listOf(
        BigInteger.valueOf(1500000000),
        BigInteger.valueOf(1500000000),
        BigInteger.valueOf(1500000000)
    )
    private var evmFeeAmount: BigInteger? = null
    private val evmGasLimit = BigInteger.valueOf(21000)
    private var evmHexValue = ""

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: BaseChain, unBondingEntry: UnBondingEntry?
        ): EvmCancelUnStakingFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putParcelable("unBondingEntry", unBondingEntry)
            }
            val fragment = EvmCancelUnStakingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCancelUnBondingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        updateFeeView()
        txSimulate()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.apply {
                    getParcelable("selectedChain", BaseChain::class.java)?.let {
                        selectedChain = it
                    }
                    getParcelable("unBondingEntry", UnBondingEntry::class.java)?.let {
                        unBondingEntry = it
                    }
                }

            } else {
                arguments?.apply {
                    (getParcelable("selectedChain") as? BaseChain)?.let {
                        selectedChain = it
                    }
                    (getParcelable("unBondingEntry") as? UnBondingEntry)?.let {
                        unBondingEntry = it
                    }
                }
            }

            listOf(
                cancelUnstakingView, memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }
            memoView.visibility = View.GONE
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            selectedChain.grpcFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress == unBondingEntry.validatorAddress }
                ?.let { validator ->
                    validatorName.text = validator.description.moniker
                }

            BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)?.let { asset ->
                val unBondingAmount = unBondingEntry.entry?.balance?.toBigDecimal()
                    ?.movePointLeft(asset.decimals ?: 6) ?: BigDecimal.ZERO
                cancelAmount.text =
                    formatAmount(unBondingAmount.toPlainString(), asset.decimals ?: 6)
                cancelDenom.text = asset.symbol
                cancelDenom.setTextColor(asset.assetColor())
            }
        }
    }

    private fun initFee() {
        binding.apply {
            feeSegment.setSelectedBackground(
                ContextCompat.getColor(
                    requireContext(), R.color.color_accent_purple
                )
            )
            feeSegment.setRipple(
                ContextCompat.getColor(
                    requireContext(), R.color.color_accent_purple
                )
            )
            val evmGasTitle = listOf(
                getString(R.string.str_low),
                getString(R.string.str_average),
                getString(R.string.str_high)
            )
            for (i in evmGasTitle.indices) {
                val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                feeSegment.addView(
                    segmentView.root,
                    i,
                    LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                )
                segmentView.btnTitle.text = evmGasTitle[i]
            }
            feeSegment.setPosition(1, false)
            selectedFeePosition = 1
            feeTokenImg.setImageResource(selectedChain.coinLogo)
            feeToken.text = selectedChain.coinSymbol

            val feePrice = BaseData.getPrice(selectedChain.coinGeckoId)
            val totalGasPrice = evmGasPrices[selectedFeePosition]
            val amount = totalGasPrice.multiply(evmGasLimit).toBigDecimal()
            val dpAmount = amount.movePointLeft(18).setScale(18, RoundingMode.DOWN)
            val value = feePrice.multiply(dpAmount)
            feeAmount.text = formatAmount(dpAmount.toPlainString(), 18)
            feeValue.text = formatAssetValue(value)
        }
    }

    private fun updateFeeView() {
        binding.apply {
            val feePrice = BaseData.getPrice(selectedChain.coinGeckoId)
            if (evmFeeAmount == null) {
                evmFeeAmount = evmGasPrices[selectedFeePosition].multiply(evmGasLimit)
            }
            val dpAmount =
                evmFeeAmount?.toBigDecimal()?.movePointLeft(18)?.setScale(18, RoundingMode.DOWN)
            val value = feePrice.multiply(dpAmount)
            dpAmount?.let { amount ->
                feeAmount.text = formatAmount(amount.toPlainString(), 18)
                feeValue.text = formatAssetValue(value)
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            feeSegment.setOnPositionChangedListener { position ->
                selectedFeePosition = position
                updateFeeView()
                txSimulate()
            }

            btnCancelUnstake.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    cancelUnBondingResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private val cancelUnBondingResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
//                val web3j = Web3j.build(HttpService(selectedChain.getEvmRpc()))
//                txViewModel.broadcastEvmCancelUnStaking(web3j, evmHexValue)
            }
        }

    private fun txSimulate() {
        binding.apply {
            btnCancelUnstake.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulateEvmCancelUnStaking(
                ByteUtils.convertBech32ToEvm(unBondingEntry.validatorAddress),
                unBondingEntry.entry?.balance,
                unBondingEntry.entry?.creationHeight!!,
                selectedChain,
                selectedFeePosition
            )
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulateEvmCancelUnStaking.observe(viewLifecycleOwner) { response ->
            response.first?.let { hexValue ->
                evmHexValue = hexValue
                response.second?.let { evmFeeAmount ->
                    this.evmFeeAmount = evmFeeAmount.toBigInteger()
                    updateFeeView()
                    isBroadCastTx(true)
                }
            }
        }

        txViewModel.erc20ErrorMessage.observe(viewLifecycleOwner) {
            isBroadCastTx(false)
            requireContext().makeToast(R.string.error_evm_simul)
            return@observe
        }
    }

    private fun setUpBroadcast() {
        txViewModel.broadcastEvmCancelUnStakingTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TransferTxResultActivity::class.java).apply {
                if (txResponse?.isNotEmpty() == true) {
                    putExtra("isSuccess", true)
                    putExtra("txHash", txResponse)
                } else {
                    putExtra("isSuccess", false)
                    putExtra("errorMsg", txResponse)
                }
                putExtra("fromChainTag", selectedChain.tag)
                putExtra("toChainTag", selectedChain.tag)
                putExtra("transferStyle", TransferStyle.WEB3_STYLE.ordinal)
                startActivity(this)
            }
            dismiss()
        }
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnCancelUnstake.updateButtonView(isSuccess)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        txViewModel.broadcastEvmCancelUnStakingTx.removeObservers(viewLifecycleOwner)
    }
}