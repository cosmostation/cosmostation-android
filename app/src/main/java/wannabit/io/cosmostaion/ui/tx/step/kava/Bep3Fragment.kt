package wannabit.io.cosmostaion.ui.tx.step.kava

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
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
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentBep3Binding
import wannabit.io.cosmostaion.ui.option.tx.address.Bep3AddressFragment
import wannabit.io.cosmostaion.ui.option.tx.address.Bep3AddressListener
import wannabit.io.cosmostaion.ui.option.tx.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.option.tx.kava.Bep3InsertAmountFragment
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModelProviderFactory
import java.math.BigDecimal
import java.math.RoundingMode

class Bep3Fragment : BaseTxFragment() {

    private var _binding: FragmentBep3Binding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: CosmosLine
    private lateinit var denom: String

    private lateinit var kavaViewModel: KavaViewModel

    private var toChains: MutableList<CosmosLine>? = mutableListOf()

    private var availableAmount: BigDecimal = BigDecimal.ZERO
    private var minAvailableAmount: BigDecimal = BigDecimal.ZERO
    private var toSendAmount = ""
    private var existedAddress = ""

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(fromChain: CosmosLine, denom: String): Bep3Fragment {
            val args = Bundle().apply {
                putParcelable("fromChain", fromChain)
                putString("denom", denom)
            }
            val fragment = Bep3Fragment()
            fragment.arguments = args
            return fragment
        }
    }

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
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("fromChain", CosmosLine::class.java)
                    ?.let { fromChain = it }

            } else {
                (arguments?.getParcelable("fromChain") as? CosmosLine)?.let {
                    fromChain = it
                }
            }
            arguments?.getString("denom")?.let { denom = it }

            listOf(
                recipientView, sendAssetView, addressView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }

            BaseData.baseAccount?.let { account ->
                if (fromChain is ChainBinanceBeacon) {
                    toChains?.addAll(account.allEvmLineChains.filter { it.name == "Kava" }.toMutableList())
                    toChains?.addAll(account.allCosmosLineChains.filter { it.name == "Kava" }.toMutableList())

                    fromChainImg.setImageResource(R.drawable.chain_binance)
                    fromChainName.text = "BNB Beacon"

                    toChainImg.setImageResource(R.drawable.chain_kava)
                    toChainName.text = "Kava"

                    fromChain.lcdBeaconTokens.firstOrNull { it.symbol == denom }
                        ?.let { bnbTokenInfo ->
                            val originalSymbol = bnbTokenInfo.original_symbol
                            tokenImg.setTokenImg(
                                (fromChain as ChainBinanceBeacon).assetImg(
                                    originalSymbol
                                )
                            )
                            tokenName.text = originalSymbol.uppercase()

                            val available =
                                (fromChain as ChainBinanceBeacon).lcdBalanceAmount(denom)
                            availableAmount = if (denom == fromChain.stakeDenom) {
                                available.subtract(BigDecimal(BNB_BEACON_BASE_FEE))
                                    .movePointRight(8)
                            } else {
                                available.movePointRight(8)
                            }
                        }

                } else {
                    toChains = account.allCosmosLineChains.filter { it.name == "BNB Beacon" }
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
            }

            toChains?.let { chains -> initToChainsData(chains) }
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
                lifecycleScope.launch(Dispatchers.IO) {
                    if (type == BaseAccountType.MNEMONIC) {
                        for (chain in toChains) {
                            if (chain.address?.isEmpty() == true) {
                                chain.setInfoWithSeed(seed, chain.setParentPath, lastHDPath)
                            }
                            if (!chain.fetched) {
                                if (chain is EthereumLine) {
                                    ApplicationViewModel.shared.loadEvmChainData(chain, id, false)
                                } else {
                                    ApplicationViewModel.shared.loadChainData(chain, id, false)
                                }
                            }
                        }

                    } else if (type == BaseAccountType.PRIVATE_KEY) {
                        for (chain in toChains) {
                            if (chain.address?.isEmpty() == true) {
                                chain.setInfoWithPrivateKey(privateKey)
                            }
                            if (!chain.fetched) {
                                if (chain is EthereumLine) {
                                    ApplicationViewModel.shared.loadEvmChainData(chain, id, false)
                                } else {
                                    ApplicationViewModel.shared.loadChainData(chain, id, false)
                                }
                            }
                        }
                    }

                    kavaViewModel.bep3Data(getChannel(ChainKava459()))
                }
            }
        }
    }

    private fun setupLoadedData() {
        ApplicationViewModel.shared.fetchedResult.observe(viewLifecycleOwner) {
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
                    Bep3InsertAmountFragment.newInstance(fromChain,
                        denom,
                        minAvailableAmount.toString(),
                        availableAmount.toString(),
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
                    Bep3AddressFragment.newInstance(toChains, object : Bep3AddressListener {
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
            }, 300)
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
            if (toSendAmount.isEmpty() || recipientAddress.text.isEmpty()) { return }
            if (BigDecimal(toSendAmount) == BigDecimal.ZERO) { return }
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