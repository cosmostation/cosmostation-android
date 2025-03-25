package wannabit.io.cosmostaion.ui.tx.genTx.major

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.suiValidatorCommission
import wannabit.io.cosmostaion.chain.fetcher.suiValidatorImg
import wannabit.io.cosmostaion.chain.fetcher.suiValidatorName
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_MIN_STAKE
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setImageFromSvg
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.databinding.FragmentStakingBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.TxType
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TransferTxResultActivity
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import wannabit.io.cosmostaion.ui.tx.genTx.SuiTxType
import wannabit.io.cosmostaion.ui.tx.genTx.TransferStyle
import wannabit.io.cosmostaion.ui.tx.option.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.InsertAmountFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorDefaultFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorDefaultListener
import java.math.BigDecimal
import java.math.RoundingMode

class SuiStakingFragment : BaseTxFragment() {

    private var _binding: FragmentStakingBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private var selectedFeePosition = 0
    private var suiFeeBudget = BigDecimal.ZERO
    private var availableAmount = BigDecimal.ZERO
    private var toStakeAmount = ""
    private var toValidator: JsonObject? = null

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): SuiStakingFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = SuiStakingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStakingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }
            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }

            BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)?.let { asset ->
                titleStakeImg.setTokenImg(asset)
                titleStake.text = getString(R.string.title_staking, asset.symbol)
            }

            listOf(validatorView, amountView, memoView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }
            memoView.visibility = View.GONE
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)
            btnFee.visibility = View.GONE

            initFee()
            (selectedChain as ChainSui).suiFetcher?.let { fetcher ->
                if (fetcher.suiValidators.isNotEmpty()) {
                    toValidator = fetcher.suiValidators[0]
                    updateValidatorView()
                }
                availableAmount = fetcher.suiBalanceAmount(SUI_MAIN_DENOM)?.subtract(suiFeeBudget)
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

            feeSegment.visibility = View.VISIBLE
            val suiGasTitle = listOf(
                "Default"
            )
            for (i in suiGasTitle.indices) {
                val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                feeSegment.addView(
                    segmentView.root,
                    i,
                    LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                )
                segmentView.btnTitle.text = suiGasTitle[i]
            }
            feeSegment.setPosition(0, false)
            selectedFeePosition = 0

            feeTokenImg.setImageResource(selectedChain.coinLogo)
            feeToken.text = selectedChain.coinSymbol
            suiFeeBudget = (selectedChain as ChainSui).suiFetcher()?.suiBaseFee(SuiTxType.SUI_STAKE)
            updateFeeView()
        }
    }

    private fun updateValidatorView() {
        binding.apply {
            jailedImg.visibility = View.GONE
            monikerImg.setImageFromSvg(
                toValidator?.suiValidatorImg(), R.drawable.icon_default_vaildator
            )
            monikerName.text = toValidator?.suiValidatorName()
            commissionPercent.text = formatString("${toValidator?.suiValidatorCommission()}%", 3)
            txSimulate()
        }
    }

    private fun updateFeeView() {
        binding.apply {
            (selectedChain as ChainSui).apply {
                val coinGeckoId = BaseData.getAsset(apiName, stakeDenom)?.coinGeckoId
                val price = BaseData.getPrice(coinGeckoId)
                val dpBudget = suiFeeBudget.movePointLeft(9).setScale(9, RoundingMode.DOWN)
                val value = price.multiply(dpBudget)

                feeAmount.text = formatAmount(dpBudget.toPlainString(), 9)
                feeValue.text = formatAssetValue(value)
            }
        }
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            toStakeAmount = toAmount

            (selectedChain as ChainSui).apply {
                val coinGeckoId = BaseData.getAsset(apiName, stakeDenom)?.coinGeckoId
                val price = BaseData.getPrice(coinGeckoId)
                val dpAmount =
                    toStakeAmount.toBigDecimal().movePointLeft(9).setScale(9, RoundingMode.DOWN)
                val value = price.multiply(dpAmount)

                delegateAmountMsg.visibility = View.GONE
                delegateAmount.text = formatAmount(dpAmount.toPlainString(), 9)
                delegateAmount.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_base01
                    )
                )
                delegateDenom.visibility = View.VISIBLE
                delegateDenom.text = (selectedChain as ChainSui).coinSymbol
                delegateValue.text = formatAssetValue(value)
                txSimulate()

                if (toStakeAmount.toBigDecimal() < SUI_MIN_STAKE.toBigDecimal()) {
                    requireActivity().makeToast(R.string.error_staking_min_sui)
                }
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun setUpClickAction() {
        binding.apply {
            validatorView.setOnClickListener {
                handleOneClickWithDelay(
                    ValidatorDefaultFragment(selectedChain,
                        suiFromValidator = (selectedChain as ChainSui).suiFetcher()?.suiValidators
                            ?: mutableListOf(),
                        listener = object : ValidatorDefaultListener {
                            override fun select(validatorAddress: String) {
                                toValidator =
                                    (selectedChain as ChainSui).suiFetcher()?.suiValidators?.firstOrNull { it["suiAddress"].asString == validatorAddress }
                                updateValidatorView()
                            }
                        })
                )
            }

            amountView.setOnClickListener {
                handleOneClickWithDelay(
                    InsertAmountFragment.newInstance(TxType.SUI_DELEGATE,
                        availableAmount.toString(),
                        toStakeAmount,
                        BaseData.getAsset(
                            selectedChain.apiName, selectedChain.stakeDenom
                        ),
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }
                        })
                )
            }

            btnStake.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    suiDelegateResultLauncher.launch(this)
                    if (Build.VERSION.SDK_INT >= 34) {
                        requireActivity().overrideActivityTransition(
                            Activity.OVERRIDE_TRANSITION_OPEN,
                            R.anim.anim_slide_in_bottom,
                            R.anim.anim_fade_out
                        )
                    } else {
                        requireActivity().overridePendingTransition(
                            R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                        )
                    }
                }
            }
        }
    }

    private fun txSimulate() {
        binding.apply {
            if (toValidator == null) return
            if (toStakeAmount.isEmpty() || toStakeAmount.toBigDecimal() < SUI_MIN_STAKE.toBigDecimal()) return
            btnStake.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            (selectedChain as ChainSui).apply {
                suiFetcher()?.let { fetcher ->
                    toValidator?.get("suiAddress")?.asString?.let { validator ->
                        txViewModel.suiStakeSimulate(
                            fetcher, mainAddress, toStakeAmount, validator, suiFeeBudget.toString()
                        )
                    }
                }
            }
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasUsed ->
            suiFeeBudget = gasUsed?.toBigDecimal()
            updateFeeView()
            isBroadCastTx(true)
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
            return@observe
        }
    }

    private val suiDelegateResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                (selectedChain as ChainSui).apply {
                    suiFetcher()?.let { fetcher ->
                        toValidator?.get("suiAddress")?.asString?.let { validator ->
                            txViewModel.suiStakeBroadcast(
                                fetcher,
                                mainAddress,
                                toStakeAmount,
                                validator,
                                suiFeeBudget.toString(),
                                this
                            )
                        }
                    }
                }
            }
        }

    private fun setUpBroadcast() {
        txViewModel.suiBroadcast.observe(viewLifecycleOwner) { response ->
            if (response["result"] != null) {
                val status =
                    response["result"].asJsonObject["effects"].asJsonObject["status"].asJsonObject["status"].asString
                Intent(requireContext(), TransferTxResultActivity::class.java).apply {
                    if (status != "success") {
                        putExtra("isSuccess", false)
                    } else {
                        putExtra("isSuccess", true)
                    }
                    putExtra("txHash", response["result"].asJsonObject["digest"].asString)
                    putExtra("fromChainTag", selectedChain.tag)
                    putExtra("transferStyle", TransferStyle.SUI_ETC_STYLE.ordinal)
                    putExtra("suiResult", response.toString())
                    startActivity(this)
                }
                dismiss()
            }
        }
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.apply {
            backdropLayout.visibility = View.GONE
            btnStake.updateButtonView(isSuccess)
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
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        txViewModel.suiBroadcast.removeObservers(viewLifecycleOwner)
    }
}