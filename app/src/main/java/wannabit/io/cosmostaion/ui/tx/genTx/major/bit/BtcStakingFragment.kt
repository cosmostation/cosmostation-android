package wannabit.io.cosmostaion.ui.tx.genTx.major.bit

import android.app.Activity
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBabylon
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.setProviderImg
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.toHex
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModelProviderFactory
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentBtcStakingBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.sign.BitcoinJs
import wannabit.io.cosmostaion.ui.main.chain.cosmos.TxType
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.TxResultType
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import wannabit.io.cosmostaion.ui.tx.option.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.InsertAmountFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorDefaultFragment
import wannabit.io.cosmostaion.ui.tx.option.validator.ValidatorDefaultListener
import java.math.BigDecimal
import java.math.RoundingMode

class BtcStakingFragment : BaseTxFragment() {

    private var _binding: FragmentBtcStakingBinding? = null
    private val binding get() = _binding!!

    private lateinit var walletViewModel: WalletViewModel

    private lateinit var selectedChain: BaseChain
    private var babylonChain: BaseChain? = null
    private var provider: String? = ""
    private var finalityProvider: FinalityProvider? = null

    private var selectedFeePosition = 0
    private var availableAmount = BigDecimal.ZERO
    private var btcFeeAmount = BigDecimal.ZERO
    private var babylonFeeAmount = BigDecimal.ZERO
    private var toStakeAmount = ""
    private var babylonTxFee: TxProto.Fee? = null

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain, provider: String? = ""): BtcStakingFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putString("provider", provider)
            }
            val fragment = BtcStakingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBtcStakingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        setUpData()
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }
            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }
            provider = arguments?.getString("provider") ?: ""
            initBabylonKeyData { chain ->
                if (chain != null) {
                    val denom = if (selectedChain.isTestnet) "tBABY" else "BABY"
                    val targetChain = if (selectedChain.isTestnet) "BABYLON-TESTNET" else "BABYLON"

                    babylonChain = chain
                    babylonTxFee = chain.getInitFee(requireContext())
                    rewardTitle.text = getString(R.string.str_reward_msg, denom)
                    rewardDescription.text = getString(R.string.str_reward_msg1, denom, targetChain)
                    rewardAddress.text = babylonChain?.address
                }
            }

            BaseData.getAssetWithSymbol(selectedChain.apiName, selectedChain.getMainAssetSymbol())
                ?.let { asset ->
                    titleStakeImg.setTokenImg(asset)
                    titleStake.text = getString(R.string.title_staking, asset.symbol)
                }

            listOf(validatorView, amountView, rewardView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)
            infoImg.setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.color_sub_blue),
                PorterDuff.Mode.SRC_IN
            )

            if (provider?.isEmpty() == true) {
                (selectedChain as ChainBitCoin86).btcFetcher?.btcFinalityProviders?.firstOrNull { it.provider.description.moniker == "Cosmostation" }
                    ?.let { provider ->
                        finalityProvider = provider
                    } ?: run {
                    finalityProvider =
                        (selectedChain as ChainBitCoin86).btcFetcher?.btcFinalityProviders?.first()
                }

            } else {
                (selectedChain as ChainBitCoin86).btcFetcher?.btcFinalityProviders?.firstOrNull {
                    it.provider.btcPk.toByteArray().toHex() == provider
                }?.let { provider ->
                    finalityProvider = provider
                } ?: run {
                    finalityProvider =
                        (selectedChain as ChainBitCoin86).btcFetcher?.btcFinalityProviders?.first()
                }
            }

            updateValidatorView()
            initFee()
        }
    }

    private fun initBabylonKeyData(callback: (BaseChain?) -> Unit) {
        val result = allChains()

        lifecycleScope.launch(Dispatchers.IO) {
            BaseData.baseAccount?.let { account ->
                val targetChain = if (selectedChain.isTestnet) {
                    result.firstOrNull { it.apiName == "babylon-testnet" }
                } else {
                    result.firstOrNull { it.apiName == "babylon" }
                }

                targetChain?.let { chain ->
                    when (account.type) {
                        BaseAccountType.MNEMONIC -> {
                            chain.setInfoWithSeed(
                                requireContext(),
                                account.seed,
                                chain.setParentPath,
                                account.lastHDPath
                            )
                        }

                        BaseAccountType.PRIVATE_KEY -> {
                            chain.setInfoWithPrivateKey(requireContext(), account.privateKey)
                        }

                        else -> {}
                    }

                    withContext(Dispatchers.Main) {
                        walletViewModel.balance(chain)
                        callback(targetChain)
                    }
                }
            }
        }
    }

    private fun setUpData() {
        walletViewModel.balanceResult.observe(viewLifecycleOwner) {
            walletViewModel.loadBtcDelegateData(selectedChain as ChainBitCoin86)
        }

        walletViewModel.btcDataLoadDone.observe(viewLifecycleOwner) { done ->
            if (done) {
                walletViewModel.btcEstimateFee(
                    selectedChain as ChainBitCoin86, BitcoinJs, finalityProvider
                )
            }
        }

        walletViewModel.btcEstimateFee.observe(viewLifecycleOwner) { fee ->
            binding.apply {
                try {
                    val balanceAmount = (selectedChain as ChainBitCoin86).btcFetcher?.btcBalances
                    btcFeeAmount = fee.toBigDecimal()
                    availableAmount = balanceAmount?.subtract(btcFeeAmount)
                    if (availableAmount <= BigDecimal.ZERO) {
                        availableAmount = BigDecimal.ZERO
                    }

                    loading.visibility = View.GONE
                    validatorView.visibility = View.VISIBLE
                    amountView.visibility = View.VISIBLE
                    rewardView.visibility = View.VISIBLE
                    feeView.visibility = View.VISIBLE

                    updateFeeView()

                } catch (e: Exception) {

                }
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

            BaseData.getAsset(selectedChain.apiName, selectedChain.getMainAssetSymbol())?.let { asset ->
                feeTokenImg.setTokenImg(asset)
                feeToken.text = asset.symbol
            }
        }
    }

    private fun updateValidatorView() {
        binding.apply {
            (selectedChain as ChainBitCoin86).apply {
                val apiName = if (selectedChain.isTestnet) "babylon-testnet" else "babylon"
                monikerImg.setProviderImg(
                    this, apiName, finalityProvider?.provider?.btcPk?.toByteArray()?.toHex()
                )
                monikerName.text = finalityProvider?.provider?.description?.moniker ?: "Unknown"

                if (finalityProvider?.provider?.jailed == true) {
                    jailedImg.visibility = View.VISIBLE
                    jailedImg.setImageResource(R.drawable.icon_jailed)
                } else if (finalityProvider?.votingPower == "0") {
                    jailedImg.visibility = View.VISIBLE
                    jailedImg.setImageResource(R.drawable.icon_inactive)
                } else {
                    jailedImg.visibility = View.GONE
                }
                val commissionRate =
                    finalityProvider?.provider?.commission?.toBigDecimal()?.movePointLeft(16)
                        ?.setScale(2, RoundingMode.DOWN)
                commissionPercent.text = formatString("${commissionRate}%", 3)
            }
            txSimulate()
        }
    }

    private fun updateFeeView() {
        binding.apply {
            (selectedChain as ChainBitCoin86).apply {
                val coinGeckoId = BaseData.getAssetWithSymbol(apiName, getMainAssetSymbol())?.coinGeckoId
                val price = BaseData.getPrice(coinGeckoId)
                val dpFeeAmount = btcFeeAmount.movePointLeft(8).setScale(8, RoundingMode.DOWN)
                val value = price.multiply(dpFeeAmount)

                feeAmount.text = formatAmount(dpFeeAmount.toPlainString(), 8)
                feeValue.text = formatAssetValue(value)
            }
        }
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            toStakeAmount = toAmount

            (selectedChain as ChainBitCoin86).apply {
                val coinGeckoId = BaseData.getAssetWithSymbol(apiName, getMainAssetSymbol())?.coinGeckoId
                val price = BaseData.getPrice(coinGeckoId)
                val dpAmount =
                    toStakeAmount.toBigDecimal().movePointLeft(8).setScale(8, RoundingMode.DOWN)
                val value = price.multiply(dpAmount)

                delegateAmountMsg.visibility = View.GONE
                delegateAmount.text = formatAmount(dpAmount.toPlainString(), 6)
                delegateAmount.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_base01
                    )
                )
                delegateDenom.visibility = View.VISIBLE
                delegateDenom.text = getMainAssetSymbol()
                delegateValue.text = formatAssetValue(value)
                txSimulate()
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            validatorView.setOnClickListener {
                handleOneClickWithDelay(
                    ValidatorDefaultFragment(selectedChain,
                        finalityProvider = (selectedChain as ChainBitCoin86).btcFetcher?.btcFinalityProviders
                            ?: mutableListOf(),
                        listener = object : ValidatorDefaultListener {
                            override fun select(validatorAddress: String) {
                                finalityProvider =
                                    (selectedChain as ChainBitCoin86).btcFetcher?.btcFinalityProviders?.firstOrNull {
                                        it.provider.btcPk.toByteArray().toHex() == validatorAddress
                                    }
                                updateValidatorView()
                            }
                        })
                )
            }

            amountView.setOnClickListener {
                handleOneClickWithDelay(
                    InsertAmountFragment.newInstance(selectedChain, TxType.BTC_DELEGATE,
                        availableAmount.toString(),
                        toStakeAmount,
                        BaseData.getAssetWithSymbol(
                            selectedChain.apiName, selectedChain.getMainAssetSymbol()
                        ),
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }
                        })
                )
            }

            btnStake.setOnClickListener {
                handleOneClickWithDelay(
                    BtcAdditionalFeeFragment.newInstance(
                        selectedChain, babylonChain, babylonFeeAmount.toString()
                    )
                )

                requireActivity().supportFragmentManager.setFragmentResultListener(
                    "tx", this@BtcStakingFragment
                ) { _, _ ->
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
    }

    private fun txSimulate() {
        binding.apply {
            (selectedChain as ChainBitCoin86).apply {
                if (finalityProvider == null) return
                if (toStakeAmount.isEmpty() || toStakeAmount.toBigDecimal() < btcFetcher?.btcParams?.minStakingValueSat?.toBigDecimal()) return
                btnStake.updateButtonView(false)
                backdropLayout.visibility = View.VISIBLE

                txViewModel.btcStakeSimulate(
                    babylonChain?.cosmosFetcher?.getChannel(),
                    finalityProvider,
                    toStakeAmount,
                    babylonTxFee,
                    "",
                    this,
                    babylonChain ?: ChainBabylon(),
                    BitcoinJs
                )
            }
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasUsed ->
            gasUsed?.toLong()?.let { gas ->
                babylonChain?.let { chain ->
                    val gasLimit =
                        (gas.toDouble() * chain.simulatedGasMultiply()).toLong().toBigDecimal()
                    val feeInfos = chain.getFeeInfos(requireContext())
                    val selectedFeeInfo = chain.getFeeBasePosition()

                    val selectedFeeData = feeInfos[selectedFeeInfo].feeDatas.firstOrNull {
                        it.denom == babylonTxFee?.getAmount(
                            0
                        )?.denom
                    }
                    val gasRate = selectedFeeData?.gasRate

                    val feeCoinAmount = gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)
                    babylonFeeAmount = feeCoinAmount
                    val feeCoin =
                        CoinProto.Coin.newBuilder().setDenom(babylonTxFee?.getAmount(0)?.denom)
                            .setAmount(feeCoinAmount.toString()).build()
                    babylonTxFee =
                        TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin)
                            .build()
                }
            }
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

                val fragment = requireActivity().supportFragmentManager.findFragmentByTag(
                    BtcAdditionalFeeFragment::class.java.name
                ) as? BottomSheetDialogFragment
                fragment?.dismiss()

                txViewModel.btcStakeBroadcast(
                    babylonChain?.cosmosFetcher?.getChannel(),
                    finalityProvider,
                    toStakeAmount,
                    babylonTxFee,
                    "",
                    selectedChain as ChainBitCoin86,
                    babylonChain ?: ChainBabylon(),
                    BitcoinJs
                )
            }
        }

    private fun setUpBroadcast() {
        txViewModel.btcStakeBroadcast.observe(viewLifecycleOwner) { response ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                response.first?.let { txResponse ->
                    if (txResponse.code > 0) {
                        putExtra("isSuccess", false)
                    } else {
                        putExtra("isSuccess", true)
                    }
                    putExtra("errorMsg", txResponse.rawLog)
                    putExtra("selectedChain", babylonChain?.tag)

                    putExtra("txResultType", TxResultType.BTC_STAKE.toString())
                    putExtra("selectedBitChain", selectedChain.tag)
                    putExtra("stakeTx", response.second)
                    putExtra("toStakeAmount", toStakeAmount)
                    putExtra("provider", finalityProvider?.provider?.btcPk?.toByteArray()?.toHex())
                    val hash = txResponse.txhash
                    if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                    startActivity(this)
                }
            }
            dismiss()
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
        txViewModel.btcStakeBroadcast.removeObservers(viewLifecycleOwner)
    }
}