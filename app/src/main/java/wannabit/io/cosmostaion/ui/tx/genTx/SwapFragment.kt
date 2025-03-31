package wannabit.io.cosmostaion.ui.tx.genTx

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cosmos.auth.v1beta1.QueryGrpc
import com.cosmos.auth.v1beta1.QueryProto
import com.cosmos.bank.v1beta1.QueryGrpc.newBlockingStub
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest
import com.cosmos.base.query.v1beta1.PaginationProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.JsonArray
import com.google.protobuf.Any
import com.google.protobuf.ByteString
import com.ibc.applications.transfer.v1.TxProto.MsgTransfer
import io.grpc.ManagedChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize
import org.json.JSONObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.chain.fetcher.accountInfos
import wannabit.io.cosmostaion.chain.fetcher.accountNumber
import wannabit.io.cosmostaion.chain.fetcher.balance
import wannabit.io.cosmostaion.chain.fetcher.sequence
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.BaseConstant.BASE_GAS_AMOUNT
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.handlerRight
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.updateToggleButtonView
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.Affiliate
import wannabit.io.cosmostaion.data.model.req.ChainInfo
import wannabit.io.cosmostaion.data.model.req.SkipMsgReq
import wannabit.io.cosmostaion.data.model.req.SkipRouteReq
import wannabit.io.cosmostaion.data.model.res.SkipMsgResponse
import wannabit.io.cosmostaion.data.model.res.SkipRouteResponse
import wannabit.io.cosmostaion.data.repository.skip.SkipRepositoryImpl
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.data.viewmodel.skip.SkipViewModel
import wannabit.io.cosmostaion.data.viewmodel.skip.SkipViewModelProviderFactory
import wannabit.io.cosmostaion.data.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.data.viewmodel.tx.TxViewModelProviderFactory
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.DialogBigLossWarnBinding
import wannabit.io.cosmostaion.databinding.FragmentSwapBinding
import wannabit.io.cosmostaion.sign.Signer
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.TxResultType
import wannabit.io.cosmostaion.ui.tx.option.general.ChainFragment
import wannabit.io.cosmostaion.ui.tx.option.general.ChainListType
import wannabit.io.cosmostaion.ui.tx.option.general.ChainSelectListener
import wannabit.io.cosmostaion.ui.tx.option.swap.AssetListener
import wannabit.io.cosmostaion.ui.tx.option.swap.AssetSelectFragment
import wannabit.io.cosmostaion.ui.tx.option.swap.AssetSelectType
import wannabit.io.cosmostaion.ui.tx.option.swap.SlippageFragment
import wannabit.io.cosmostaion.ui.tx.option.swap.SlippageListener
import wannabit.io.cosmostaion.ui.tx.option.swap.SwapWarnFragment
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.TimeUnit

class SwapFragment : BaseTxFragment() {

    private var _binding: FragmentSwapBinding? = null
    private val binding get() = _binding!!

    private lateinit var skipViewModel: SkipViewModel
    private lateinit var skipTxViewModel: TxViewModel

    private var skipDataJob: Job? = null

    private var allChains: MutableList<BaseChain>? = mutableListOf()
    private var skipChains: MutableList<BaseChain> = mutableListOf()
    private var skipInputAssets: JsonArray? = JsonArray()
    private var skipOutputAssets: JsonArray? = JsonArray()

    private var targetChains: MutableList<BaseChain> = mutableListOf()
    private var targetInputAssets: MutableList<TargetAsset> = mutableListOf()
    private var targetOutputAssets: MutableList<TargetAsset> = mutableListOf()

    private var inputChain: BaseChain? = null
    private lateinit var inputAsset: TargetAsset
    private var outputChain: BaseChain? = null
    private lateinit var outputAsset: TargetAsset

    private var availableAmount: BigDecimal = BigDecimal.ZERO

    private var skipSlippage = "1"
    private var route: SkipRouteResponse? = null
    private var toMsg: SkipMsgResponse? = null
    private var txFee: TxProto.Fee? = null

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSwapBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        setUpViewModel()
        setUpSimulate()
        setUpBroadcast()
        initData()
        textChange()
        setUpClickAction()
    }

    private fun initViewModel() {
        val skipRepository = SkipRepositoryImpl()
        val skipViewModelProviderFactory = SkipViewModelProviderFactory(skipRepository)
        skipViewModel =
            ViewModelProvider(this, skipViewModelProviderFactory)[SkipViewModel::class.java]

        val skipTxRepository = TxRepositoryImpl()
        val skipTxViewModelProviderFactory = TxViewModelProviderFactory(skipTxRepository)
        skipTxViewModel =
            ViewModelProvider(this, skipTxViewModelProviderFactory)[TxViewModel::class.java]
    }

    private fun initData() {
        skipDataJob = lifecycleScope.launch(Dispatchers.IO) {
            allChains = initAllKeyData()
            skipViewModel.skipChains()
        }
    }

    private fun initAllKeyData(): MutableList<BaseChain> {
        val result = mutableListOf<BaseChain>()
        lifecycleScope.launch(Dispatchers.IO) {
            BaseData.baseAccount?.let { account ->
                account.apply {
                    allChains().filter { it.isDefault && it.supportCosmos() && !it.isTestnet }
                        .forEach { chain ->
                            result.add(chain)
                        }

                    if (type == BaseAccountType.MNEMONIC) {
                        result.forEach { chain ->
                            if (chain.publicKey == null && isAdded) {
                                chain.setInfoWithSeed(
                                    requireContext(), seed, chain.setParentPath, lastHDPath
                                )
                            }
                        }

                    } else if (type == BaseAccountType.PRIVATE_KEY) {
                        result.forEach { chain ->
                            if (chain.publicKey == null) {
                                chain.setInfoWithPrivateKey(requireContext(), privateKey)
                            }
                        }
                    }
                }
            }
        }
        return result
    }

    private fun updateView() {
        binding.apply {
            swapLayout.visibility = View.VISIBLE
            initView()

            if (BaseData.getSwapWarn()) {
                SwapWarnFragment().show(
                    requireActivity().supportFragmentManager, SwapWarnFragment::class.java.name
                )
            }
        }
    }

    private fun initView() {
        binding.apply {
            toMsg = null
            listOf(inputView, outputView, feeView).forEach {
                it.setBackgroundResource(R.drawable.cell_bg)
            }

            errorView.setBackgroundResource(R.drawable.cell_error_bg)
            loading.visibility = View.GONE
            btnSwap.updateButtonView(false)
            btnToggle.updateToggleButtonView(true)

            feeView.visibility = View.GONE
            errorView.visibility = View.GONE
            inputAmountTxt.setText("")
            outputAmount.text = ""
            inputAmountValue.text = ""
            outputAmountValue.text = ""

            txFee = baseFee()
            inputChain?.let { chain ->
                fromAddress.text = chain.address
                inputChainImg.setImageResource(chain.logo)
                inputChainName.text = chain.name
                BaseData.getAsset(chain.apiName, inputAsset.denom)?.let { inputMsAsset ->
                    inputTokenImg.setTokenImg(inputMsAsset.image ?: "")
                    inputToken.text = inputMsAsset.symbol
                } ?: run {
                    inputTokenImg.setTokenImg(inputAsset.image)
                    inputToken.text = inputAsset.symbol
                }

                val inputBalance = inputAsset.balance
                if (txFee?.getAmount(0)?.denom == inputAsset.denom) {
                    txFee?.getAmount(0)?.amount?.toBigDecimal()?.let { txFeeAmount ->
                        availableAmount = if (txFeeAmount >= inputBalance) {
                            BigDecimal.ZERO
                        } else {
                            inputBalance.subtract(txFeeAmount)
                        }
                    }
                } else {
                    availableAmount = inputBalance
                }

                val inputDpAmount = availableAmount.movePointLeft(inputAsset.decimals ?: 6)
                    .setScale(inputAsset.decimals ?: 6, RoundingMode.DOWN)
                inputAvailable.text =
                    formatAmount(inputDpAmount.toPlainString(), inputAsset.decimals ?: 6)
            }

            outputChain?.let { chain ->
                toAddress.text = chain.address
                outputChainImg.setImageResource(chain.logo)
                outputChainName.text = chain.name
                BaseData.getAsset(chain.apiName, outputAsset.denom)?.let { outputMsAsset ->
                    outputTokenImg.setTokenImg(outputMsAsset.image ?: "")
                    outputToken.text = outputMsAsset.symbol
                } ?: run {
                    outputTokenImg.setTokenImg(outputAsset.image)
                    outputToken.text = outputAsset.symbol
                }

                val outputDpAmount = outputAsset.balance.movePointLeft(outputAsset.decimals ?: 6)
                    .setScale(outputAsset.decimals ?: 6, RoundingMode.DOWN)
                outputAvailable.text =
                    formatAmount(outputDpAmount.toPlainString(), inputAsset.decimals ?: 6)
            }

            Prefs.lastSwapSet = mutableListOf(
                inputChain!!.tag, "", outputChain!!.tag, ""
            )
        }
    }

    private fun setUpViewModel() {
        skipViewModel.skipChainsResult.observe(viewLifecycleOwner) { response ->
            response?.let { skipChainList ->
                skipChainList.chains?.forEach { sChain ->
                    allChains?.firstOrNull { it.chainIdCosmos == sChain.chain_id }
                        ?.let { skipCosmosChain ->
                            skipChains.add(skipCosmosChain)
                        }
                }
            }

            targetChains.addAll(skipChains)
            targetChains.sortWith { o1, o2 ->
                when {
                    o1.tag == "cosmos118" -> -1
                    o2.tag == "cosmos118" -> 1
                    o1.tag == "osmosis118" -> -1
                    o2.tag == "osmosis118" -> 1
                    o1.name < o2.name -> -1
                    o1.name > o2.name -> 1
                    else -> 0
                }
            }

            val lastSwapSet = Prefs.lastSwapSet
            inputChain = targetChains.firstOrNull { it.tag == lastSwapSet[0] } ?: targetChains[0]
            outputChain = targetChains.firstOrNull { it.tag == lastSwapSet[2] } ?: targetChains[1]

            skipDataJob = lifecycleScope.launch(Dispatchers.IO) {
                inputChain?.let { chain ->
                    chain.cosmosFetcher()?.let {
                        try {
                            val channel = chain.cosmosFetcher()?.getChannel()
                            loadAuth(channel, chain)
                            val loadInputBalanceDeferred = async { loadBalance(channel, chain) }

                            chain.cosmosFetcher?.cosmosBalances = loadInputBalanceDeferred.await()
                            BaseUtils.onParseVesting(chain)

                        } catch (e: Exception) {
                        }
                    }
                }
                outputChain?.let { chain ->
                    chain.cosmosFetcher()?.let {
                        try {
                            val channel = chain.cosmosFetcher()?.getChannel()
                            loadAuth(channel, chain)
                            val loadOutputBalanceDeferred = async { loadBalance(channel, chain) }

                            chain.cosmosFetcher?.cosmosBalances = loadOutputBalanceDeferred.await()
                            BaseUtils.onParseVesting(chain)

                        } catch (e: Exception) {
                        }
                    }
                }

                loadInputAssets()
                loadOutputAssets()

                inputAsset = targetInputAssets[0]
                outputAsset = targetOutputAssets[0]

                loadInputAssetBalance()
                loadOutputAssetBalance()

                withContext(Dispatchers.Main) {
                    updateView()
                }
            }
        }

        skipViewModel.skipRouteResult.observe(viewLifecycleOwner) { response ->
            binding.apply {
                route = response
                val inputText = inputAmountTxt.text.toString().trim()
                if (inputText.isEmpty()) {
                    return@observe
                }
                val userInput = inputText.toBigDecimal()

                inputAsset.decimals?.let { decimal ->
                    val inputAmount = userInput.movePointRight(decimal)
                    if (response.amount_in == inputAmount.toPlainString()) {
                        errorView.visibility = View.GONE
                        skipViewModel.skipMsg(bindSkipMsgReq(response))
                    }
                }
            }
        }

        skipViewModel.skipMsgResult.observe(viewLifecycleOwner) { response ->
            binding.apply {
                if (response.msgs.size == 1) {
                    val dpSlippage = BigDecimal("100").subtract(skipSlippage.toBigDecimal())
                    val dpOutputAmount =
                        route?.amount_out?.toBigDecimal()?.multiply(dpSlippage)?.movePointLeft(2)
                            ?.setScale(0, RoundingMode.DOWN)
                    outputAsset.decimals?.let { outputDecimal ->
                        val dpAmount = dpOutputAmount?.movePointLeft(outputDecimal)
                            ?.setScale(outputDecimal, RoundingMode.DOWN)?.toPlainString()
                        outputAmount.text = formatAmount(dpAmount.toString(), outputDecimal)
                        slippage.text = "$skipSlippage%"

                        val inputText = inputAmountTxt.text.toString().trim()
                        val userInput = inputText.toBigDecimal()
                        inputAsset.decimals?.let { inputDecimal ->
                            val inputAmount = userInput.movePointRight(inputDecimal)
                            if (inputAmount > BigDecimal.ZERO) {
                                val swapRate =
                                    dpOutputAmount?.divide(inputAmount, 6, RoundingMode.DOWN)
                                        ?.movePointRight(inputDecimal - outputDecimal)

                                inputRateDenom.text = inputAsset.symbol
                                inputRateAmount.text = formatAmount("1", 6)
                                outputRateDenom.text = outputAsset.symbol
                                outputRateAmount.text = formatAmount(swapRate.toString(), 6)
                            }

                            inputChain?.let { chain ->
                                txFee?.let { fee ->
                                    BaseData.getAsset(chain.apiName, fee.getAmount(0).denom)
                                        ?.let { feeAsset ->
                                            feeAsset.decimals?.let { decimal ->
                                                val dpFeeAmount =
                                                    fee.getAmount(0).amount.toBigDecimal()
                                                        .movePointLeft(decimal)
                                                        .setScale(decimal, RoundingMode.DOWN)
                                                txFeeAmount.text = formatAmount(
                                                    dpFeeAmount.toPlainString(), decimal
                                                )
                                                txFeeDenom.text = feeAsset.symbol
                                            }
                                        }
                                }
                            }
                            swapVenue.text = route?.swap_venue?.name

                            val inputPrice = BaseData.getPrice(inputAsset.geckoId)
                            val inputValue =
                                inputPrice.multiply(inputAmount).movePointLeft(inputDecimal)
                                    .setScale(6, RoundingMode.DOWN)
                            inputAmountValue.text = formatAssetValue(inputValue)

                            val outputPrice = BaseData.getPrice(outputAsset.geckoId)
                            val outputValue =
                                outputPrice.multiply(dpOutputAmount).movePointLeft(outputDecimal)
                                    .setScale(6, RoundingMode.DOWN)
                            outputAmountValue.text = formatAssetValue(outputValue)

                            feeView.visibility = View.VISIBLE
                            txSimulate(response)

                            val inValue = (route?.usd_amount_in ?: "0").toBigDecimal()
                            val outValue = (route?.usd_amount_out ?: "0").toBigDecimal()
                            if (inValue.multiply(BigDecimal("0.9")) > outValue) {
                                showBigLossPopup()
                            }
                        }
                    }

                } else {
                    feeView.visibility = View.GONE
                    errorView.visibility = View.VISIBLE
                    errorMsg.text = "No Route"
                }
            }
        }

        skipViewModel.skipRouteErrorResult.observe(viewLifecycleOwner) { response ->
            binding.apply {
                feeView.visibility = View.GONE
                errorView.visibility = View.VISIBLE
                errorMsg.text = response.message ?: getString(R.string.error_swap_msg)
                btnSwap.updateButtonView(false)
                btnToggle.updateToggleButtonView(false)
            }
        }

        skipViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            binding.apply {
                btnSwap.updateButtonView(false)
                btnToggle.updateToggleButtonView(false)
                requireContext().showToast(view, response, true)
                return@observe
            }
        }
    }

    private fun textChange() {
        binding.inputAmountTxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val userInput = s.toString()
                if (userInput.startsWith(".")) {
                    binding.inputAmountTxt.setText("")
                    return

                } else if (userInput.contains(".")) {
                    val decimalPlaces: Int = userInput.length - userInput.indexOf(".") - 1
                    inputAsset.decimals?.let { decimal ->
                        if (decimalPlaces == decimal) {
                            if (userInput.toBigDecimal()
                                    .handlerRight(decimal, 0) == BigDecimal.ZERO
                            ) {
                                s?.delete(s.length - 1, s.length)
                            }
                        } else if (decimalPlaces > decimal) {
                            s?.delete(s.length - 1, s.length)
                        }
                    }
                }
                updateAmountView()
            }
        })
    }

    private fun updateAmountView() {
        binding.apply {
            toMsg = null
            btnSwap.updateButtonView(false)
            btnToggle.updateToggleButtonView(false)
            val inputText = inputAmountTxt.text.toString().trim()

            if (inputText.isEmpty()) {
                outputAmount.text = ""
                feeView.visibility = View.GONE
                btnToggle.updateToggleButtonView(true)
                return
            }

            val userInput = inputText.toBigDecimal()
            inputAsset.decimals?.let { decimal ->
                val inputAmount = userInput.movePointRight(decimal)
                if (inputAmount == BigDecimal.ZERO || availableAmount < inputAmount) {
                    outputAmount.text = ""
                    invalidMsg.visibility = View.VISIBLE
                    feeView.visibility = View.GONE
                    return
                }
                skipViewModel.skipRoute(bindSkipRouteReq(inputAmount.toPlainString()))
            }
            invalidMsg.visibility = View.INVISIBLE
        }
    }

    private fun txSimulate(msg: SkipMsgResponse) {
        binding.apply {
            btnSwap.updateButtonView(false)
            btnToggle.updateToggleButtonView(false)

            inputChain?.let { chain ->
                val skipMsg = msg.txs[0].cosmos_tx.msgs[0]
                toMsg = msg
                val innerMsg = JSONObject(skipMsg.msg.toString())
                if (skipMsg.msg_type_url == "/ibc.applications.transfer.v1.MsgTransfer") {
                    skipTxViewModel.simulate(
                        chain.cosmosFetcher?.getChannel(), bindIbcSend(innerMsg), txFee, "", chain
                    )
                } else if (skipMsg.msg_type_url == "/cosmwasm.wasm.v1.MsgExecuteContract") {
                    txViewModel.simulate(
                        chain.cosmosFetcher?.getChannel(), onBindWasm(innerMsg), txFee, "", chain
                    )
                }
            }
        }
    }

    private fun setUpSimulate() {
        skipTxViewModel.simulate.observe(viewLifecycleOwner) { gasUsed ->
            binding.apply {
                inputChain?.let { chain ->
                    txFee?.let { fee ->
                        gasUsed?.let {
                            val gasLimit = (it.toDouble() * chain.simulatedGasMultiply()).toLong()
                                .toBigDecimal()
                            val baseFeePosition = chain.getFeeBasePosition()
                            val gasRate =
                                chain.getFeeInfos(requireContext())[baseFeePosition].feeDatas.firstOrNull { feeData ->
                                    feeData.denom == fee.getAmount(0)?.denom
                                }
                            val feeCoinAmount =
                                gasRate?.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)

                            val feeCoin =
                                CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0)?.denom)
                                    .setAmount(feeCoinAmount.toString()).build()
                            txFee = TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong())
                                .addAmount(feeCoin).build()

                            BaseData.getAsset(chain.apiName, fee.getAmount(0).denom)
                                ?.let { feeAsset ->
                                    feeAsset.decimals?.let { decimal ->
                                        txFee?.getAmount(0)?.amount?.toBigDecimal()
                                            ?.movePointLeft(decimal)
                                            ?.setScale(decimal, RoundingMode.DOWN)?.let { amount ->
                                                txFeeAmount.text =
                                                    formatAmount(amount.toPlainString(), decimal)
                                                txFeeDenom.text = feeAsset.symbol
                                            }
                                    }
                                }
                            btnToggle.updateToggleButtonView(true)
                            btnSwap.updateButtonView(true)
                        }
                    }
                }
            }
        }

        skipTxViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            binding.apply {
                btnSwap.updateButtonView(false)
                btnToggle.updateToggleButtonView(false)
                requireContext().showToast(view, response, true)
                return@observe
            }
        }
    }

    private fun bindIbcSend(innerMsg: JSONObject): MutableList<Any> {
        val sendCoin =
            CoinProto.Coin.newBuilder().setDenom(innerMsg.getJSONObject("token").getString("denom"))
                .setAmount(innerMsg.getJSONObject("token").getString("amount")).build()

        val ibcTransferMsg = MsgTransfer.newBuilder().setSender(innerMsg.getString("sender"))
            .setReceiver(innerMsg.getString("receiver"))
            .setSourceChannel(innerMsg.getString("source_channel"))
            .setSourcePort(innerMsg.getString("source_port"))
            .setTimeoutTimestamp(innerMsg.getString("timeout_timestamp").toLong())
            .setToken(sendCoin).setMemo(innerMsg.optString("memo", "")).build()
        return Signer.ibcSendMsg(ibcTransferMsg)
    }

    private fun onBindWasm(innerMsg: JSONObject): MutableList<Any> {
        val wasmMsgs: MutableList<MsgExecuteContract?> = mutableListOf()
        val jsonDataMsg = ByteString.copyFromUtf8(innerMsg.getJSONObject("msg").toString())
        val fundCoin = CoinProto.Coin.newBuilder()
            .setDenom(innerMsg.getJSONArray("funds").getJSONObject(0).getString("denom"))
            .setAmount(innerMsg.getJSONArray("funds").getJSONObject(0).getString("amount")).build()

        val msgExecuteContract =
            MsgExecuteContract.newBuilder().setSender(innerMsg.getString("sender"))
                .setContract(innerMsg.getString("contract")).setMsg(jsonDataMsg).addFunds(fundCoin)
                .build()
        wasmMsgs.add(msgExecuteContract)
        return Signer.wasmMsg(wasmMsgs)
    }

    @SuppressLint("WrongConstant")
    private fun setUpClickAction() {
        binding.apply {
            btnSlippage.setOnClickListener {
                toMsg = null
                handleOneClickWithDelay(
                    SlippageFragment.newInstance(skipSlippage, object : SlippageListener {
                        override fun slippage(position: Int) {
                            skipSlippage = position.toString()
                            updateAmountView()
                        }
                    })
                )
            }

            inputChainLayout.setOnClickListener {
                handleOneClickWithDelay(
                    ChainFragment.newInstance(null, null, targetChains,
                        ChainListType.SELECT_INPUT_SWAP,
                        object : ChainSelectListener {
                            override fun select(chainName: String) {
                                try {
                                    if (inputChain?.name != chainName) {
                                        loading.visibility = View.VISIBLE

                                        skipDataJob = lifecycleScope.launch(Dispatchers.IO) {
                                            inputChain =
                                                targetChains.firstOrNull { it.name == chainName }
                                            inputChain?.let { chain ->
                                                chain.cosmosFetcher()?.let { fetcher ->
                                                    try {
                                                        val channel = fetcher.getChannel()
                                                        loadAuth(channel, chain)
                                                        val loadInputBalanceDeferred = async {
                                                            loadBalance(
                                                                channel, chain
                                                            )
                                                        }
                                                        fetcher.cosmosBalances =
                                                            loadInputBalanceDeferred.await()
                                                        BaseUtils.onParseVesting(chain)
                                                        loadInputAssets()
                                                        inputAsset = targetInputAssets[0]
                                                        loadInputAssetBalance()

                                                    } catch (e: Exception) {
                                                        withContext(Dispatchers.Main) {
                                                            loading.visibility = View.GONE
                                                        }
                                                        return@launch
                                                    }
                                                }
                                            }

                                            withContext(Dispatchers.Main) {
                                                initView()
                                            }
                                        }
                                    }
                                } catch (e: Exception) {
                                    activity?.makeToast(R.string.str_unknown_error)
                                }
                            }
                        })
                )
            }

            inputTokenLayout.setOnClickListener {
                handleOneClickWithDelay(
                    AssetSelectFragment.newInstance(inputChain,
                        targetInputAssets.filter { it.balance > BigDecimal.ZERO }.toMutableList(),
                        inputChain?.cosmosFetcher?.cosmosBalances,
                        AssetSelectType.SWAP_INPUT,
                        object : AssetListener {
                            override fun select(denom: String) {
                                if (inputAsset.denom != denom) {
                                    lifecycleScope.launch(Dispatchers.IO) {
                                        inputAsset =
                                            targetInputAssets.firstOrNull { it.denom == denom }!!
                                        loadInputAssetBalance()

                                        withContext(Dispatchers.Main) {
                                            initView()
                                        }
                                    }
                                }
                            }
                        })
                )
            }

            btnToggle.setOnClickListener {
                val tempChain = inputChain
                val tempAssetList = targetInputAssets
                val tempAssetSelected = inputAsset

                inputChain = outputChain
                targetInputAssets = targetOutputAssets
                inputAsset = outputAsset

                outputChain = tempChain
                targetOutputAssets = tempAssetList
                outputAsset = tempAssetSelected

                initView()
            }

            outputChainLayout.setOnClickListener {
                handleOneClickWithDelay(
                    ChainFragment.newInstance(null, null, targetChains,
                        ChainListType.SELECT_OUTPUT_SWAP,
                        object : ChainSelectListener {
                            override fun select(chainName: String) {
                                try {
                                    if (outputChain?.name != chainName) {
                                        loading.visibility = View.VISIBLE

                                        skipDataJob = lifecycleScope.launch(Dispatchers.IO) {
                                            outputChain =
                                                targetChains.firstOrNull { it.name == chainName }
                                            outputChain?.let { chain ->
                                                chain.cosmosFetcher()?.let { fetcher ->
                                                    try {
                                                        val channel = fetcher.getChannel()
                                                        val loadOutputBalanceDeferred = async {
                                                            loadBalance(
                                                                channel, chain
                                                            )
                                                        }
                                                        fetcher.cosmosBalances =
                                                            loadOutputBalanceDeferred.await()
                                                        BaseUtils.onParseVesting(chain)
                                                        loadOutputAssets()
                                                        outputAsset = targetOutputAssets[0]
                                                        loadOutputAssetBalance()

                                                    } catch (e: Exception) {
                                                        withContext(Dispatchers.Main) {
                                                            loading.visibility = View.GONE
                                                        }
                                                        return@launch
                                                    }
                                                }
                                            }

                                            withContext(Dispatchers.Main) {
                                                initView()
                                            }
                                        }
                                    }
                                } catch (_: Exception) {
                                }
                            }
                        })
                )
            }

            outputTokenLayout.setOnClickListener {
                handleOneClickWithDelay(
                    AssetSelectFragment.newInstance(outputChain,
                        targetOutputAssets,
                        outputChain?.cosmosFetcher?.cosmosBalances,
                        AssetSelectType.SWAP_OUTPUT,
                        object : AssetListener {
                            override fun select(denom: String) {
                                if (outputAsset.denom != denom) {
                                    lifecycleScope.launch(Dispatchers.IO) {
                                        outputAsset =
                                            targetOutputAssets.firstOrNull { it.denom == denom }!!
                                        loadOutputAssetBalance()
                                        withContext(Dispatchers.Main) {
                                            initView()
                                        }
                                    }
                                }
                            }
                        })
                )
            }

            btnHalf.setOnClickListener {
                val halfAmount = availableAmount.multiply(BigDecimal(0.5))
                    .movePointLeft(inputAsset.decimals ?: 6)
                    .setScale(inputAsset.decimals ?: 6, RoundingMode.DOWN)
                inputAmountTxt.setText(halfAmount.toPlainString())
                updateAmountView()
                if (halfAmount > BigDecimal.ZERO) {
                    inputAmountTxt.setSelection(halfAmount.toPlainString().length)
                } else {
                    inputAmountTxt.setSelection(halfAmount.toPlainString().length - 1)
                }
            }

            btnMax.setOnClickListener {
                val maxAmount = availableAmount.movePointLeft(inputAsset.decimals ?: 6)
                    .setScale(inputAsset.decimals ?: 6, RoundingMode.DOWN)
                inputAmountTxt.setText(maxAmount.toPlainString())
                updateAmountView()
                if (maxAmount > BigDecimal.ZERO) {
                    inputAmountTxt.setSelection(maxAmount.toPlainString().length)
                } else {
                    inputAmountTxt.setSelection(maxAmount.toPlainString().length - 1)
                }
            }

            btnSwap.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    swapResultLauncher.launch(this)
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

    private val swapResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.loading.visibility = View.VISIBLE
                val skipMsg = toMsg?.txs?.get(0)?.cosmos_tx?.msgs?.get(0)
                skipMsg?.let {
                    val innerMsg = JSONObject(skipMsg.msg.toString())
                    inputChain?.let { chain ->
                        when (skipMsg.msg_type_url) {
                            "/ibc.applications.transfer.v1.MsgTransfer" -> {
                                skipTxViewModel.broadcast(
                                    chain.cosmosFetcher?.getChannel(),
                                    bindIbcSend(innerMsg),
                                    txFee,
                                    "",
                                    chain
                                )
                            }

                            "/cosmwasm.wasm.v1.MsgExecuteContract" -> {
                                skipTxViewModel.broadcast(
                                    chain.cosmosFetcher?.getChannel(),
                                    onBindWasm(innerMsg),
                                    txFee,
                                    "",
                                    chain
                                )
                            }

                            else -> {
                                return@let
                            }
                        }
                    }
                }
            }
        }

    private fun bindSkipRouteReq(amount: String): SkipRouteReq {
        return SkipRouteReq(
            amount,
            inputAsset.denom,
            inputChain?.chainIdCosmos,
            outputAsset.denom,
            outputChain?.chainIdCosmos,
            inputChain?.skipAffiliate()
        )
    }

    private fun bindSkipMsgReq(route: SkipRouteResponse): SkipMsgReq {
        val addressList = mutableListOf<String>()
        route.required_chain_addresses?.forEach { chainId ->
            allChains?.firstOrNull { it.chainIdCosmos == chainId && it.isDefault }?.address?.let { address ->
                addressList.add(address)
            }
        }
        return SkipMsgReq(
            addressList,
            skipSlippage,
            route.amount_in,
            route.source_asset_denom,
            route.source_asset_chain_id,
            route.amount_out,
            route.dest_asset_denom,
            route.dest_asset_chain_id,
            route.operations,
            affiliate()
        )
    }

    private fun baseFee(): TxProto.Fee {
        val minFee = inputChain?.getDefaultFeeCoins(requireContext())?.firstOrNull()
        minFee?.let {
            val feeCoin =
                CoinProto.Coin.newBuilder().setDenom(it.denom).setAmount(it.amount).build()
            return TxProto.Fee.newBuilder().setGasLimit(BASE_GAS_AMOUNT.toLong()).addAmount(feeCoin)
                .build()
        }
        return TxProto.Fee.newBuilder().build()
    }

    private fun affiliate(): Map<String, ChainInfo> {
        val fee = inputChain?.skipAffiliate()
        val result: MutableMap<String, ChainInfo> = mutableMapOf()
        result["osmosis-1"] = ChainInfo(
            listOf(Affiliate("osmo1clpqr4nrk4khgkxj78fcwwh6dl3uw4epasmvnj", fee))
        )
        result["neutron-1"] = ChainInfo(
            listOf(Affiliate("neutron1clpqr4nrk4khgkxj78fcwwh6dl3uw4ep35p7l8", fee))
        )
        result["phoenix-1"] = ChainInfo(
            listOf(Affiliate("terra1564j3fq8p8np4yhh4lytnftz33japc03wuejxm", fee))
        )
        result["pacific-1"] = ChainInfo(
            listOf(Affiliate("sei1hnkkqnzwmyw652muh6wfea7xlfgplnyj3edm09", fee))
        )
        result["injective-1"] = ChainInfo(
            listOf(Affiliate("inj1rvqzf9u2uxttmshn302anlknfgsatrh5mcu6la", fee))
        )
        result["chihuahua-1"] = ChainInfo(
            listOf(Affiliate("chihuahua1tgcypttehx3afugys6eq28h0kpmswfkgcuewfw", fee))
        )
        result["core-1"] = ChainInfo(
            listOf(Affiliate("persistence1rq598kexpsdmhxq63qq74v3tf22u6yvl2a47xk", fee))
        )
        return result
    }

    private fun showBigLossPopup() {
        val binding = DialogBigLossWarnBinding.inflate(layoutInflater)
        val alertDialog = AlertDialog.Builder(requireContext(), R.style.AppTheme_AlertDialogTheme)
            .setView(binding.root)

        val dialog = alertDialog.create()
        dialog.show()
        dialog.setCancelable(false)

        binding.apply {
            btnConfirm.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    private fun setUpBroadcast() {
        skipTxViewModel.broadcast.observe(viewLifecycleOwner) { response ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                response?.let { txResponse ->
                    if (txResponse.code > 0) {
                        putExtra("isSuccess", false)
                    } else {
                        putExtra("isSuccess", true)
                    }
                    putExtra("txResultType", TxResultType.SKIP.toString())
                    putExtra("errorMsg", txResponse.rawLog)
                    putExtra("selectedChain", inputChain?.tag)
                    val hash = txResponse.txhash
                    if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                    startActivity(this)
                }
            }
            dismiss()
        }
    }

    private suspend fun loadAuth(
        managedChannel: ManagedChannel?, chain: BaseChain
    ) {
        return if (chain.cosmosFetcher?.endPointType(chain) == CosmosEndPointType.USE_GRPC) {
            val stub =
                QueryGrpc.newBlockingStub(managedChannel).withDeadlineAfter(8L, TimeUnit.SECONDS)
            val request =
                QueryProto.QueryAccountRequest.newBuilder().setAddress(chain.address).build()
            chain.cosmosFetcher()?.cosmosAuth = stub.account(request).account
            chain.cosmosFetcher()?.cosmosAccountNumber =
                stub.account(request).account.accountInfos().second
            chain.cosmosFetcher()?.cosmosSequence =
                stub.account(request).account.accountInfos().third

        } else {
            val response = RetrofitInstance.lcdApi(chain)
                .lcdAuthInfo(chain.address).asJsonObject["account"].asJsonObject
            chain.cosmosFetcher()?.cosmosLcdAuth = response
            chain.cosmosFetcher()?.cosmosAccountNumber = response.accountNumber()
            chain.cosmosFetcher()?.cosmosSequence = response.sequence()
        }
    }

    private suspend fun loadBalance(
        channel: ManagedChannel?, chain: BaseChain
    ): MutableList<CoinProto.Coin> {
        return if (chain.cosmosFetcher?.endPointType(chain) == CosmosEndPointType.USE_GRPC) {
            val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(2000).build()
            val stub = newBlockingStub(channel).withDeadlineAfter(8L, TimeUnit.SECONDS)
            val request = QueryAllBalancesRequest.newBuilder().setPagination(pageRequest)
                .setAddress(chain.address).build()
            stub.allBalances(request).balancesList

        } else {
            RetrofitInstance.lcdApi(chain).lcdBalanceInfo(chain.address, "2000").balance()
        }
    }

    private suspend fun loadInputAssets() {
        skipInputAssets?.removeAll { true }
        if (skipChains.any { it.tag == inputChain?.tag }) {
            val skipAssets = skipViewModel.skipAssets(inputChain)
            skipInputAssets =
                skipAssets?.get("chain_to_assets_map")?.asJsonObject?.get(inputChain?.chainIdForSwap())?.asJsonObject?.get(
                    "assets"
                )?.asJsonArray
        }

        val tempInputAssets: MutableList<TargetAsset> = mutableListOf()
        skipInputAssets?.forEach { skipInput ->
            skipInput?.asJsonObject?.let {
                val tempTarget = TargetAsset(
                    it["denom"].asString,
                    it["recommended_symbol"].asString,
                    it["decimals"].asInt,
                    it["logo_uri"].asString,
                    if (it.has("coingecko_id")) {
                        it["coingecko_id"].asString
                    } else {
                        ""
                    },
                    if (it.has("description")) {
                        it["description"].asString
                    } else {
                        ""
                    }
                )
                if (!tempInputAssets.any { asset -> asset.denom.lowercase() == tempTarget.denom.lowercase() }) {
                    tempInputAssets.add(tempTarget)
                }
            }
        }

        targetInputAssets.clear()
        BaseData.assets?.filter { it.chain == inputChain?.apiName }?.let { assets ->
            for (index in tempInputAssets.indices) {
                assets.firstOrNull { it.denom == tempInputAssets[index].denom }?.let { asset ->
                    tempInputAssets[index].geckoId = asset.coinGeckoId
                    tempInputAssets[index].description = asset.name
                    tempInputAssets[index].image = asset.image ?: ""
                    if (inputChain is ChainGnoTestnet) {
                        tempInputAssets[index].balance =
                            (inputChain as ChainGnoTestnet).gnoRpcFetcher()
                                ?.balanceAmount(tempInputAssets[index].denom) ?: BigDecimal.ZERO

                    } else if (inputChain?.supportCosmos() == false && inputChain?.supportEvm == true) {
                        tempInputAssets[index].balance =
                            inputChain?.evmRpcFetcher()?.evmBalance ?: BigDecimal.ZERO

                    } else {
                        tempInputAssets[index].balance =
                            inputChain?.cosmosFetcher()?.balanceAmount(tempInputAssets[index].denom)
                                ?: BigDecimal.ZERO
                    }
                    targetInputAssets.add(tempInputAssets[index])
                }
            }
        }

        targetInputAssets.sortWith { o1, o2 ->
            when {
                o1.denom == inputChain?.stakeDenom -> -1
                o2.denom == inputChain?.stakeDenom -> 1
                o1.symbol == inputChain?.coinSymbol -> -1
                o2.symbol == inputChain?.coinSymbol -> 1
                o1.type.ordinal < o2.type.ordinal -> -1
                o1.type.ordinal > o2.type.ordinal -> 1
                else -> o1.symbol!!.compareTo(o2.symbol!!)
            }
        }
    }

    private suspend fun loadOutputAssets() {
        skipOutputAssets?.removeAll { true }
        if (skipChains.any { it.tag == outputChain?.tag }) {
            val skipAssets = skipViewModel.skipAssets(outputChain)
            skipOutputAssets =
                skipAssets?.get("chain_to_assets_map")?.asJsonObject?.get(outputChain?.chainIdForSwap())?.asJsonObject?.get(
                    "assets"
                )?.asJsonArray
        }

        val tempOutputAssets: MutableList<TargetAsset> = mutableListOf()
        skipOutputAssets?.forEach { skipOutput ->
            skipOutput?.asJsonObject?.let {
                val tempTarget = TargetAsset(
                    it["denom"].asString,
                    it["recommended_symbol"].asString,
                    it["decimals"].asInt,
                    it["logo_uri"].asString,
                    if (it.has("coingecko_id")) {
                        it["coingecko_id"].asString
                    } else {
                        ""
                    },
                    if (it.has("description")) {
                        it["description"].asString
                    } else {
                        ""
                    }
                )
                if (!tempOutputAssets.any { asset -> asset.denom.lowercase() == tempTarget.denom.lowercase() }) {
                    tempOutputAssets.add(tempTarget)
                }
            }
        }

        targetOutputAssets.clear()
        BaseData.assets?.filter { it.chain == outputChain?.apiName }?.let { assets ->
            for (index in tempOutputAssets.indices) {
                assets.firstOrNull { it.denom == tempOutputAssets[index].denom }?.let { asset ->
                    tempOutputAssets[index].geckoId = asset.coinGeckoId
                    tempOutputAssets[index].description = asset.name
                    tempOutputAssets[index].image = asset.image ?: ""
                    if (outputChain is ChainGnoTestnet) {
                        tempOutputAssets[index].balance =
                            (outputChain as ChainGnoTestnet).gnoRpcFetcher()
                                ?.balanceAmount(tempOutputAssets[index].denom) ?: BigDecimal.ZERO

                    } else if (outputChain?.supportCosmos() == false && outputChain?.supportEvm == true) {
                        tempOutputAssets[index].balance =
                            outputChain?.evmRpcFetcher()?.evmBalance ?: BigDecimal.ZERO

                    } else {
                        tempOutputAssets[index].balance = outputChain?.cosmosFetcher()
                            ?.balanceAmount(tempOutputAssets[index].denom) ?: BigDecimal.ZERO
                    }
                    targetOutputAssets.add(tempOutputAssets[index])
                }
            }
        }

        targetOutputAssets.sortWith { o1, o2 ->
            when {
                o1.denom == outputChain?.stakeDenom -> -1
                o2.denom == outputChain?.stakeDenom -> 1
                o1.symbol == outputChain?.coinSymbol -> -1
                o2.symbol == outputChain?.coinSymbol -> 1
                o1.type.ordinal < o2.type.ordinal -> -1
                o1.type.ordinal > o2.type.ordinal -> 1
                else -> o1.symbol!!.compareTo(o2.symbol!!)
            }
        }
    }

    private fun loadInputAssetBalance() {
        if (inputAsset.type == TargetAssetType.CW20) {

        } else if (inputAsset.type == TargetAssetType.ERC20) {

        } else {
            if (inputChain is ChainGnoTestnet) {
                inputAsset.balance =
                    (inputChain as ChainGnoTestnet).gnoRpcFetcher()?.balanceAmount(inputAsset.denom)
                        ?: BigDecimal.ZERO
            } else if (inputChain?.supportCosmos() == false && inputChain?.supportEvm == true) {
                inputAsset.balance = inputChain?.evmRpcFetcher()?.evmBalance ?: BigDecimal.ZERO
            } else {
                inputAsset.balance =
                    inputChain?.cosmosFetcher()?.balanceAmount(inputAsset.denom) ?: BigDecimal.ZERO
            }
        }
    }

    private fun loadOutputAssetBalance() {
        if (outputAsset.type == TargetAssetType.CW20) {

        } else if (outputAsset.type == TargetAssetType.ERC20) {

        } else {
            if (inputChain is ChainGnoTestnet) {
                outputAsset.balance = (outputChain as ChainGnoTestnet).gnoRpcFetcher()
                    ?.balanceAmount(outputAsset.denom) ?: BigDecimal.ZERO
            } else if (outputChain?.supportCosmos() == false && outputChain?.supportEvm == true) {
                outputAsset.balance = outputChain?.evmRpcFetcher()?.evmBalance ?: BigDecimal.ZERO
            } else {
                outputAsset.balance = outputChain?.cosmosFetcher()?.balanceAmount(outputAsset.denom)
                    ?: BigDecimal.ZERO
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        skipDataJob?.cancel()
        super.onDestroyView()
    }
}

@Parcelize
class TargetAsset(
    val denom: String,
    val symbol: String?,
    val decimals: Int?,
    var image: String = "",
    var geckoId: String?,
    var description: String?
) : Parcelable {
    var balance: BigDecimal = BigDecimal.ZERO
    var type: TargetAssetType = TargetAssetType.NATIVE

    init {
        when {
            denom.lowercase().startsWith("0x") -> {
                type = TargetAssetType.ERC20
            }

            denom.lowercase().startsWith("cw20:") -> {
                type = TargetAssetType.CW20
            }

            denom.lowercase().startsWith("ibc/") -> {
                type = TargetAssetType.IBC
            }
        }
    }
}

enum class TargetAssetType { NATIVE, IBC, CW20, ERC20 }