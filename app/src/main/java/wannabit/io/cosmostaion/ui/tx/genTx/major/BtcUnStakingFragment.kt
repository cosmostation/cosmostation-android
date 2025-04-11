package wannabit.io.cosmostaion.ui.tx.genTx.major

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
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.isHexString
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setProviderImg
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.toHex
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModelProviderFactory
import wannabit.io.cosmostaion.databinding.FragmentBtcUnstakingBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.TxResultType
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ProviderFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ProviderListener
import java.math.BigDecimal
import java.math.RoundingMode

class BtcUnStakingFragment(
    private val selectedChain: BaseChain, private val staked: Pair<JsonObject, FinalityProvider>?
) : BaseTxFragment() {

    private var _binding: FragmentBtcUnstakingBinding? = null
    private val binding get() = _binding!!

    private lateinit var walletViewModel: WalletViewModel

    private var btcActiveStakingData: Pair<JsonObject, FinalityProvider>? = null

    private var selectedFeePosition = 0
    private var availableAmount = BigDecimal.ZERO
    private var btcFeeAmount = BigDecimal.ZERO

    private var txData: String = ""

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBtcUnstakingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initViewModel() {
        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel =
            ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]
    }

    private fun initView() {
        binding.apply {
            btcActiveStakingData = staked

            listOf(validatorView, amountView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }

            BaseData.getAssetWithSymbol(selectedChain.apiName, selectedChain.coinSymbol)
                ?.let { asset ->
                    titleUnstakeImg.setTokenImg(asset)
                    titleUnstake.text = getString(R.string.title_staking, asset.symbol)
                }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)
            updateValidatorView()
            updateAmountView()
            initFee()
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
            val bitGasTitle = listOf(
                "Default"
            )
            for (i in bitGasTitle.indices) {
                val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                feeSegment.addView(
                    segmentView.root,
                    i,
                    LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                )
                segmentView.btnTitle.text = bitGasTitle[i]
            }
            feeSegment.setPosition(0, false)
            selectedFeePosition = 0

            BaseData.getAssetWithSymbol(selectedChain.apiName, selectedChain.coinSymbol)
                ?.let { asset ->
                    feeTokenImg.setTokenImg(asset)
                    feeToken.text = asset.symbol

                    val balanceAmount = (selectedChain as ChainBitCoin86).btcFetcher?.btcBalances
                    btcFeeAmount =
                        (selectedChain.btcFetcher?.btcNetworkInfo?.get("data")?.asJsonObject?.get(
                            "params"
                        )?.asJsonObject?.get("bbn")?.asJsonArray?.last()?.asJsonObject?.get("unbonding_fee_sat")?.asLong
                            ?: 2000).toBigDecimal()
                    availableAmount = balanceAmount?.subtract(btcFeeAmount)

                    updateFeeView()
                }

            if (availableAmount > BigDecimal.ZERO) {
                txSimulate()
            } else {
                requireActivity().makeToast(R.string.error_not_enough_fee)
            }
        }
    }

    private fun updateValidatorView() {
        binding.apply {
            (selectedChain as ChainBitCoin86).apply {
                val apiName = if (selectedChain.isTestnet) "babylon-testnet" else "babylon"
                monikerImg.setProviderImg(
                    this,
                    apiName,
                    btcActiveStakingData?.second?.provider?.btcPk?.toByteArray()?.toHex()
                )
                monikerName.text =
                    btcActiveStakingData?.second?.provider?.description?.moniker ?: "Unknown"

                if (btcActiveStakingData?.second?.provider?.jailed == true) {
                    jailedImg.visibility = View.VISIBLE
                    jailedImg.setImageResource(R.drawable.icon_jailed)
                } else if (btcActiveStakingData?.second?.votingPower == "0") {
                    jailedImg.visibility = View.VISIBLE
                    jailedImg.setImageResource(R.drawable.icon_inactive)
                } else {
                    jailedImg.visibility = View.GONE
                }
            }
        }
    }

    private fun updateAmountView() {
        binding.apply {
            BaseData.getAssetWithSymbol(selectedChain.apiName, selectedChain.coinSymbol)
                ?.let { asset ->
                    val toStakeAmount =
                        btcActiveStakingData?.first?.get("delegation_staking")?.asJsonObject?.get("staking_amount")?.asLong.toString()
                    val amount = toStakeAmount.toBigDecimal().movePointLeft(asset.decimals ?: 8)
                        .setScale(asset.decimals ?: 8, RoundingMode.DOWN)
                    undelegateAmount.text =
                        formatAmount(amount.toPlainString(), asset.decimals ?: 8)
                    undelegateDenom.text = asset.symbol

                    val price = BaseData.getPrice(asset.coinGeckoId)
                    val value = price.multiply(amount)
                    undelegateValue.text = formatAssetValue(value)
                }
        }
    }

    private fun updateFeeView() {
        binding.apply {
            (selectedChain as ChainBitCoin86).apply {
                val coinGeckoId = BaseData.getAssetWithSymbol(apiName, coinSymbol)?.coinGeckoId
                val price = BaseData.getPrice(coinGeckoId)
                val dpFeeAmount = btcFeeAmount.movePointLeft(8).setScale(8, RoundingMode.DOWN)
                val value = price.multiply(dpFeeAmount)

                feeAmount.text = formatAmount(dpFeeAmount.toPlainString(), 9)
                feeValue.text = formatAssetValue(value)
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            validatorView.setOnClickListener {
                handleOneClickWithDelay(
                    ProviderFragment(selectedChain, object : ProviderListener {
                        override fun select(staked: Pair<JsonObject, FinalityProvider>) {
                            btcActiveStakingData = staked
                            updateValidatorView()
                            updateAmountView()
                            txSimulate()
                        }
                    })
                )
            }

            btnUnstake.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    btcDelegateResultLauncher.launch(this)
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
            (selectedChain as ChainBitCoin86).apply {
                btnUnstake.updateButtonView(false)
                backdropLayout.visibility = View.VISIBLE

                txViewModel.btcUnStakeSimulate(
                    btcActiveStakingData, selectedChain
                )
            }
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { data ->
            val response = JsonParser.parseString(data).asJsonObject
            txData = response["transactionHex"].asString
            btcFeeAmount = response["fee"].asString.toBigDecimal()

            updateFeeView()
            isBroadCastTx(true)
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
            return@observe
        }
    }

    private val btcDelegateResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                txViewModel.btcUnStakeBroadcast(selectedChain as ChainBitCoin86, txData)
            }
        }

    private fun setUpBroadcast() {
        txViewModel.bitBroadcast.observe(viewLifecycleOwner) { response ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                if (isHexString(response.toString())) {
                    putExtra("isSuccess", true)
                    putExtra("txHash", response)
                } else {
                    putExtra("isSuccess", false)
                    putExtra("errorMsg", response)
                }
                putExtra("txResultType", TxResultType.BTC_UNSTAKE.toString())
                putExtra("selectedChain", selectedChain.tag)
                startActivity(this)
            }
            dismiss()
        }
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.apply {
            backdropLayout.visibility = View.GONE
            btnUnstake.updateButtonView(isSuccess)
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
            }, 500)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        txViewModel.bitBroadcast.removeObservers(viewLifecycleOwner)
    }
}