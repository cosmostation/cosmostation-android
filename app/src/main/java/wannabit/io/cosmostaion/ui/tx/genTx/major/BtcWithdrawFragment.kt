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
import wannabit.io.cosmostaion.common.setProviderImg
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.toHex
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModelProviderFactory
import wannabit.io.cosmostaion.databinding.FragmentBtcWithdrawBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.TxResultType
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import wannabit.io.cosmostaion.ui.tx.info.major.BtcTxType
import wannabit.io.cosmostaion.ui.tx.option.validator.ProviderFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ProviderListener
import java.math.BigDecimal
import java.math.RoundingMode

class BtcWithdrawFragment(
    private val selectedChain: BaseChain, private val staked: Pair<JsonObject, FinalityProvider>?
) : BaseTxFragment() {

    private var _binding: FragmentBtcWithdrawBinding? = null
    private val binding get() = _binding!!

    private lateinit var walletViewModel: WalletViewModel

    private var btcWithdrawAbleData: Pair<JsonObject, FinalityProvider>? = null

    private var selectedFeePosition = 0
    private var availableAmount = BigDecimal.ZERO
    private var btcFeeAmount = BigDecimal(2000)

    private var txData: String = ""

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBtcWithdrawBinding.inflate(layoutInflater, container, false)
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
            btcWithdrawAbleData = staked

            listOf(validatorView, amountView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }

            BaseData.getAssetWithSymbol(selectedChain.apiName, selectedChain.coinSymbol)
                ?.let { asset ->
                    titleWithdrawImg.setTokenImg(asset)
                    titleWithdraw.text = getString(R.string.title_staking, asset.symbol)
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
                    updateFeeView()
                }

            txSimulate()
        }
    }

    private fun updateValidatorView() {
        binding.apply {
            (selectedChain as ChainBitCoin86).apply {
                val apiName = if (selectedChain.isTestnet) "babylon-testnet" else "babylon"
                monikerImg.setProviderImg(
                    this,
                    apiName,
                    btcWithdrawAbleData?.second?.provider?.btcPk?.toByteArray()?.toHex()
                )
                monikerName.text =
                    btcWithdrawAbleData?.second?.provider?.description?.moniker ?: "Unknown"

                if (btcWithdrawAbleData?.second?.provider?.jailed == true) {
                    jailedImg.visibility = View.VISIBLE
                    jailedImg.setImageResource(R.drawable.icon_jailed)
                } else if (btcWithdrawAbleData?.second?.votingPower == "0") {
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
                        btcWithdrawAbleData?.first?.get("delegation_staking")?.asJsonObject?.get("staking_amount")?.asLong.toString()
                    val amount = toStakeAmount.toBigDecimal().movePointLeft(asset.decimals ?: 8)
                        .setScale(asset.decimals ?: 8, RoundingMode.DOWN)
                    withdrawAmount.text = formatAmount(amount.toPlainString(), asset.decimals ?: 8)
                    withdrawDenom.text = asset.symbol

                    val price = BaseData.getPrice(asset.coinGeckoId)
                    val value = price.multiply(amount)
                    withdrawValue.text = formatAssetValue(value)
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

                feeAmount.text = formatAmount(dpFeeAmount.toPlainString(), 8)
                feeValue.text = formatAssetValue(value)
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            validatorView.setOnClickListener {
                handleOneClickWithDelay(
                    ProviderFragment(
                        selectedChain,
                        BtcTxType.BTC_WITHDRAW,
                        object : ProviderListener {
                            override fun select(staked: Pair<JsonObject, FinalityProvider>) {
                                btcWithdrawAbleData = staked
                                updateValidatorView()
                                updateAmountView()
                                txSimulate()
                            }
                        })
                )
            }

            btnWithdraw.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    btcWithdrawResultLauncher.launch(this)
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
                btnWithdraw.updateButtonView(false)
                backdropLayout.visibility = View.VISIBLE

                txViewModel.btcWithdrawSimulate(
                    btcWithdrawAbleData, selectedChain
                )
            }
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { data ->
            val response = JsonParser.parseString(data).asJsonObject
            txData = response["transactionHex"].asString
            btcFeeAmount = response["fee"].asString.toBigDecimal()
            val balanceAmount = (selectedChain as ChainBitCoin86).btcFetcher?.btcBalances
            availableAmount = balanceAmount?.subtract(btcFeeAmount)

            if (availableAmount > BigDecimal.ZERO) {
                isBroadCastTx(true)
            } else {
                isBroadCastTx(false)
            }
            updateFeeView()
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
            return@observe
        }
    }

    private val btcWithdrawResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                txViewModel.btcWithdrawBroadcast(selectedChain as ChainBitCoin86, txData)
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
            btnWithdraw.updateButtonView(isSuccess)
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