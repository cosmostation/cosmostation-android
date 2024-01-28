package wannabit.io.cosmostaion.ui.tx.step.kava

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.BNB_BEACON_BASE_FEE
import wannabit.io.cosmostaion.chain.cosmosClass.BNB_GECKO_ID
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.repository.chain.KavaRepositoryImpl
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentBep3Binding
import wannabit.io.cosmostaion.ui.option.tx.address.Bep3AddressFragment
import wannabit.io.cosmostaion.ui.option.tx.address.Bep3AddressListener
import wannabit.io.cosmostaion.ui.option.tx.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.option.tx.kava.Bep3InsertAmountFragment
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModelProviderFactory
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory
import java.math.BigDecimal
import java.math.RoundingMode

class Bep3Fragment(
    private val fromChain: CosmosLine, private val denom: String
) : BaseTxFragment() {

    private var _binding: FragmentBep3Binding? = null
    private val binding get() = _binding!!

    private lateinit var kavaViewModel: KavaViewModel
    private lateinit var walletViewModel: WalletViewModel

    private var toChains: MutableList<CosmosLine>? = mutableListOf()

    private var availableAmount: BigDecimal = BigDecimal.ZERO
    private var minAvailableAmount: BigDecimal = BigDecimal.ZERO
    private var toSendAmount = ""
    private var existedAddress = ""

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBep3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initData()
        initView()
        setUpClickAction()
        setupLoadedData()
    }

    private fun initViewModel() {
        val kavaRepository = KavaRepositoryImpl()
        val kavaViewModelProviderFactory = KavaViewModelProviderFactory(kavaRepository)
        kavaViewModel =
            ViewModelProvider(this, kavaViewModelProviderFactory)[KavaViewModel::class.java]

        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel =
            ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]
    }

    private fun initView() {
        binding.apply {
            listOf(
                recipientView, sendAssetView, addressView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }

            BaseData.baseAccount?.let { baseAccount ->
                if (fromChain is ChainBinanceBeacon) {
                    toChains =
                        baseAccount.allCosmosLineChains.filter { it.name == "Kava" }.toMutableList()
                    fromChainImg.setImageResource(R.drawable.chain_binance)
                    fromChainName.text = "BNB Beacon"

                    toChainImg.setImageResource(R.drawable.chain_kava)
                    toChainName.text = "Kava"

                    fromChain.lcdBeaconTokens.firstOrNull { it.symbol == denom }
                        ?.let { bnbTokenInfo ->
                            val originalSymbol = bnbTokenInfo.originalSymbol
                            tokenImg.setTokenImg(fromChain.assetImg(originalSymbol))
                            tokenName.text = originalSymbol.uppercase()

                            val available = fromChain.lcdBalanceAmount(denom)
                            availableAmount = if (denom == fromChain.stakeDenom) {
                                available.subtract(BigDecimal(BNB_BEACON_BASE_FEE))
                                    .movePointRight(8)
                            } else {
                                available.movePointRight(8)
                            }
                        }

                } else {
                    toChains = baseAccount.allCosmosLineChains.filter { it.name == "BNB Beacon" }
                        .toMutableList()
                    fromChainImg.setImageResource(R.drawable.chain_kava)
                    fromChainName.text = "KAVA"

                    toChainImg.setImageResource(R.drawable.chain_binance)
                    toChainName.text = "BNB Beacon"

                    BaseData.getAsset(fromChain.apiName, denom)?.let { asset ->
                        tokenImg.setTokenImg(asset)
                        tokenName.text = asset.symbol
                    }
                    availableAmount = fromChain.balanceAmount(denom)
                }

                toChains?.let { lines ->
                    if (lines[0].fetched) {
                        kavaViewModel.bep3Data(getChannel(ChainKava459()))
                    } else {
                        CoroutineScope(Dispatchers.IO).launch {
                            initToChainsData(lines)
                        }
                    }
                }
            }
        }
    }

    private fun initData() {
        binding.apply {
            kavaViewModel.bep3Data.observe(viewLifecycleOwner) { response ->
                response?.bep3Param?.let { params ->
                    params.forEach { param ->
                        if (fromChain is ChainBinanceBeacon) {
                            if (denom.lowercase()
                                    .startsWith(param.denom.lowercase()) || denom.lowercase()
                                    .startsWith("xrp") && param.denom.lowercase().startsWith("xrp")
                            ) {
                                val limit = param.supplyLimit.limit.toBigDecimal()
                                response.bep3Supplies?.forEach { supply ->
                                    if (denom.lowercase()
                                            .startsWith(supply.incomingSupply.denom.lowercase()) || denom.lowercase()
                                            .startsWith("xrp") && supply.incomingSupply.denom.lowercase()
                                            .startsWith("xrp")
                                    ) {
                                        val remain =
                                            limit.subtract(supply.currentSupply.amount.toBigDecimal())
                                                .subtract(supply.incomingSupply.amount.toBigDecimal())
                                        if (availableAmount > remain) {
                                            availableAmount = remain
                                        }
                                        if (availableAmount > param.maxSwapAmount.toBigDecimal()) {
                                            availableAmount = param.maxSwapAmount.toBigDecimal()
                                        }
                                        minAvailableAmount = param.minSwapAmount.toBigDecimal()
                                            .add(param.fixedFee.toBigDecimal())
                                    }
                                }
                            }

                        } else {
                            if (denom.lowercase()
                                    .startsWith(param.denom.lowercase()) || denom.lowercase()
                                    .startsWith("xrp") && param.denom.lowercase().startsWith("xrp")
                            ) {
                                if (availableAmount > param.maxSwapAmount.toBigDecimal()) {
                                    availableAmount = param.maxSwapAmount.toBigDecimal()
                                }
                                minAvailableAmount = param.minSwapAmount.toBigDecimal()
                                    .add(param.fixedFee.toBigDecimal())
                            }
                        }
                    }
                    loading.visibility = View.GONE
                }
            }
        }
    }

    private fun initToChainsData(toChains: MutableList<CosmosLine>) {
        BaseData.baseAccount?.let { account ->
            account.apply {
                CoroutineScope(Dispatchers.IO).launch {
                    if (type == BaseAccountType.MNEMONIC) {
                        toChains.forEach { line ->
                            if (line.address?.isEmpty() == true) {
                                line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                            }
                            if (!line.fetched) {
                                walletViewModel.loadChainData(line, id, false)
                            }
                        }

                    } else if (type == BaseAccountType.PRIVATE_KEY) {
                        toChains.forEach { line ->
                            if (line.address?.isEmpty() == true) {
                                line.setInfoWithPrivateKey(privateKey)
                            }
                            if (!line.fetched) {
                                walletViewModel.loadChainData(line, id, false)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupLoadedData() {
        walletViewModel.fetchedResult.observe(viewLifecycleOwner) {
            kavaViewModel.bep3Data(getChannel(ChainKava459()))
        }
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            tabMsgTxt.visibility = View.GONE
            amountLayout.visibility = View.VISIBLE

            toSendAmount = toAmount.toBigDecimal().movePointLeft(8).toString()
            if (fromChain is ChainBinanceBeacon) {
                sendAmount.text = formatAmount(toSendAmount, 8)

                if (denom == fromChain.stakeDenom) {
                    sendValue.visibility = View.VISIBLE
                    val price = BaseData.getPrice(BNB_GECKO_ID)
                    val toSendValue =
                        price.multiply(toSendAmount.toBigDecimal()).setScale(6, RoundingMode.DOWN)
                    sendValue.text = formatAssetValue(toSendValue)
                } else {
                    sendValue.visibility = View.GONE
                }

            } else {
                BaseData.getAsset(fromChain.apiName, denom)?.let { asset ->
                    asset.decimals?.let { decimal ->
                        val dpAmount = BigDecimal(toAmount).movePointLeft(decimal)
                            .setScale(decimal, RoundingMode.DOWN)
                        val price = BaseData.getPrice(asset.coinGeckoId)
                        val value = price.multiply(dpAmount)

                        sendAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                        sendValue.text = formatAssetValue(value)
                    }
                }
            }
        }
        txValidate()
    }

    private fun updateAddressView(address: String) {
        existedAddress = address
        binding.apply {
            if (address.isEmpty()) {
                recipientAddressMsg.text = getString(R.string.str_tap_for_add_address_msg)
            } else {
                recipientAddress.text = address
            }
            recipientAddressMsg.visibleOrGone(address.isEmpty())
            recipientAddress.visibleOrGone(address.isNotEmpty())
        }
        txValidate()
    }

    private fun setUpClickAction() {
        binding.apply {
            sendAssetView.setOnClickListener {
                handleOneClickWithDelay(
                    Bep3InsertAmountFragment(fromChain,
                        denom,
                        minAvailableAmount,
                        availableAmount,
                        toSendAmount,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }

                        })
                )
            }

            addressView.setOnClickListener {
                handleOneClickWithDelay(
                    Bep3AddressFragment(toChains, object : Bep3AddressListener {
                        override fun address(address: String) {
                            updateAddressView(address)
                        }
                    })
                )
            }

            btnSend.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    bep3SendResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
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
            }, 1000)
        }
    }

    private val bep3SendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                Handler(Looper.getMainLooper()).postDelayed({
                    setUpBroadcast()
                }, 500)
            }
        }

    private fun txValidate() {
        binding.apply {
            if (toSendAmount.isEmpty() || recipientAddress.text.isEmpty()) {
                return
            }
            if (BigDecimal(toSendAmount) == BigDecimal.ZERO) {
                return
            }
            binding.btnSend.updateButtonView(true)
        }
    }

    private fun setUpBroadcast() {
        Intent(requireContext(), Bep3ResultActivity::class.java).apply {
            putExtra("fromChain", fromChain.tag)
            putExtra(
                "toChain", toChains?.firstOrNull {
                    it.address == binding.recipientAddress.text.toString().trim()
                }?.tag
            )
            putExtra("toAddress", binding.recipientAddress.text.toString().trim())
            putExtra("toSendDenom", denom)
            putExtra("toSendAmount", toSendAmount)
            startActivity(this)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}