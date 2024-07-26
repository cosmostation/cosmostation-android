package wannabit.io.cosmostaion.ui.tx.step

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.google.gson.JsonObject
import com.google.protobuf.Any
import com.google.protobuf.ByteString
import com.ibc.applications.transfer.v1.TxProto.MsgTransfer
import io.grpc.ManagedChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.accountInfos
import wannabit.io.cosmostaion.chain.accountNumber
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.chain.balance
import wannabit.io.cosmostaion.chain.sequence
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
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.Affiliate
import wannabit.io.cosmostaion.data.model.req.SkipMsgReq
import wannabit.io.cosmostaion.data.model.req.SkipRouteReq
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.SkipMsgResponse
import wannabit.io.cosmostaion.data.model.res.SkipRouteResponse
import wannabit.io.cosmostaion.data.model.res.SwapVenue
import wannabit.io.cosmostaion.data.repository.skip.SkipRepositoryImpl
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.DialogBigLossWarnBinding
import wannabit.io.cosmostaion.databinding.FragmentSwapBinding
import wannabit.io.cosmostaion.ui.option.tx.general.ChainFragment
import wannabit.io.cosmostaion.ui.option.tx.general.ChainListType
import wannabit.io.cosmostaion.ui.option.tx.general.ChainSelectListener
import wannabit.io.cosmostaion.ui.option.tx.swap.AssetListener
import wannabit.io.cosmostaion.ui.option.tx.swap.AssetSelectFragment
import wannabit.io.cosmostaion.ui.option.tx.swap.AssetSelectType
import wannabit.io.cosmostaion.ui.option.tx.swap.SlippageFragment
import wannabit.io.cosmostaion.ui.option.tx.swap.SlippageListener
import wannabit.io.cosmostaion.ui.option.tx.swap.SwapWarnFragment
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.TxResultType
import wannabit.io.cosmostaion.ui.viewmodel.skip.SkipViewModel
import wannabit.io.cosmostaion.ui.viewmodel.skip.SkipViewModelProviderFactory
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModelProviderFactory
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.TimeUnit

class SwapFragment : BaseTxFragment() {

    private var _binding: FragmentSwapBinding? = null
    private val binding get() = _binding!!

    private lateinit var skipViewModel: SkipViewModel
    private lateinit var skipTxViewModel: TxViewModel

    private var skipDataJob: Job? = null

    private var allSwapAbleChains: MutableList<BaseChain>? = mutableListOf()

    private var skipChains: MutableList<BaseChain> = mutableListOf()
    private var skipAssets: JsonObject? = null
    private var skipSlippage = "1"

    private var inputCosmosChain: BaseChain? = null
    private var inputAssets: MutableList<Asset> = mutableListOf()
    private var inputAssetSelected: Asset? = null
    private var inputAsset: Asset? = null

    private var outputCosmosChain: BaseChain? = null
    private var outputAssets: MutableList<Asset> = mutableListOf()
    private var outputAssetSelected: Asset? = null
    private var outputAsset: Asset? = null

    private var availableAmount: BigDecimal = BigDecimal.ZERO

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
            allSwapAbleChains = initAllKeyData()
            skipViewModel.loadData()
        }
    }

    private fun initAllKeyData(): MutableList<BaseChain> {
        val result = mutableListOf<BaseChain>()
        BaseData.baseAccount?.let { account ->
            account.apply {
                allChains().filter { it.isDefault && it.supportCosmos() && !it.isTestnet }
                    .forEach { chain ->
                        result.add(chain)
                    }

                if (type == BaseAccountType.MNEMONIC) {
                    result.forEach { chain ->
                        if (chain.publicKey == null) {
                            chain.setInfoWithSeed(seed, chain.setParentPath, lastHDPath)
                        }
                    }

                } else if (type == BaseAccountType.PRIVATE_KEY) {
                    result.forEach { chain ->
                        if (chain.publicKey == null) {
                            chain.setInfoWithPrivateKey(privateKey)
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
            inputCosmosChain?.let { inputLine ->
                fromAddress.text = inputLine.address
                inputChainImg.setImageResource(inputLine.logo)
                inputChain.text = inputLine.name.uppercase()

                inputAssetSelected?.denom?.let { inputDenom ->
                    inputAsset = BaseData.getAsset(inputLine.apiName, inputDenom)
                    inputAsset?.let { asset ->
                        inputTokenImg.setTokenImg(asset.assetImg())
                        inputToken.text = asset.symbol
                    }

                    inputLine.cosmosFetcher?.balanceAmount(inputDenom)?.let { inputBalance ->
                        if (txFee?.getAmount(0)?.denom == inputDenom) {
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
                    }
                    val inputDpAmount = availableAmount.movePointLeft(inputAsset?.decimals ?: 6)
                        .setScale(inputAsset?.decimals ?: 6, RoundingMode.DOWN)
                    inputAvailable.text =
                        formatAmount(inputDpAmount.toPlainString(), inputAsset?.decimals ?: 6)

                    outputCosmosChain?.let { outPutLine ->
                        toAddress.text = outPutLine.address
                        outputChainImg.setImageResource(outPutLine.logo)
                        outputChain.text = outPutLine.name.uppercase()

                        outputAssetSelected?.denom?.let { outputDenom ->
                            outputAsset = BaseData.getAsset(outPutLine.apiName, outputDenom)
                            outputAsset?.let { asset ->
                                outputTokenImg.setTokenImg(asset.assetImg())
                                outputToken.text = asset.symbol
                            }

                            val outputBalance = outPutLine.cosmosFetcher?.balanceAmount(outputDenom)
                            val outputDpAmount =
                                outputBalance?.movePointLeft(outputAsset?.decimals ?: 6)
                                    ?.setScale(outputAsset?.decimals ?: 6, RoundingMode.DOWN)
                            outputAvailable.text = formatAmount(
                                outputDpAmount.toString(), outputAsset?.decimals ?: 6
                            )
                        }

                        Prefs.lastSwapSet = mutableListOf(
                            inputLine.tag,
                            inputAssetSelected?.denom.toString(),
                            outPutLine.tag,
                            outputAssetSelected?.denom.toString()
                        )
                    }
                }
            }
        }
    }

    private fun setUpViewModel() {
        skipViewModel.skipDataResult.observe(viewLifecycleOwner) { response ->
            response?.let { skipData ->
                skipData.skipChains?.chains?.forEach { sChain ->
                    allSwapAbleChains?.firstOrNull { it.chainIdCosmos == sChain.chain_id && it.isDefault }
                        ?.let { skipChain ->
                            skipChains.add(skipChain)
                        }
                }
                skipAssets = skipData.skipAssets

                val chainIds = skipChains.map { chain ->
                    chain.chainIdCosmos
                }
                chainIds.forEach { chainId ->
                    if ((skipAssets?.getAsJsonObject("chain_to_assets_map")
                            ?.getAsJsonObject(chainId)
                            ?.getAsJsonArray("assets")?.asJsonArray?.count() ?: 0) == 0
                    ) {
                        skipChains.removeIf { it.chainIdCosmos == chainId }
                    }
                }

                val lastSwapSet = Prefs.lastSwapSet

                inputCosmosChain = skipChains.firstOrNull { it.tag == lastSwapSet[0] }
                    ?: skipChains.firstOrNull { it.tag == "cosmos118" }
                inputCosmosChain?.let { chain ->
                    skipAssets?.getAsJsonObject("chain_to_assets_map")
                        ?.getAsJsonObject(chain.chainIdCosmos)?.getAsJsonArray("assets")
                        ?.forEach { json ->
                            BaseData.getAsset(
                                chain.apiName, json.asJsonObject.get("denom").asString
                            )?.let { asset ->
                                inputAssets.add(asset)
                            }
                        }
                    inputAssetSelected = inputAssets.firstOrNull { it.denom == lastSwapSet[1] }
                        ?: inputAssets.firstOrNull { it.denom == chain.stakeDenom }
                }

                outputCosmosChain = skipChains.firstOrNull { it.tag == lastSwapSet[2] }
                    ?: skipChains.firstOrNull { it.tag == "neutron118" }
                outputCosmosChain?.let { chain ->
                    skipAssets?.getAsJsonObject("chain_to_assets_map")
                        ?.getAsJsonObject(chain.chainIdCosmos)?.getAsJsonArray("assets")
                        ?.forEach { json ->
                            BaseData.getAsset(
                                chain.apiName, json.asJsonObject.get("denom").asString
                            )?.let { asset ->
                                outputAssets.add(asset)
                            }
                        }
                    outputAssetSelected = outputAssets.firstOrNull { it.denom == lastSwapSet[3] }
                        ?: outputAssets.firstOrNull { it.denom == chain.stakeDenom }
                }
            }

            skipDataJob = lifecycleScope.launch(Dispatchers.IO) {
                inputCosmosChain?.let { chain ->
                    chain.cosmosFetcher()?.let {
                        try {
                            val channel = chain.cosmosFetcher()?.getChannel()
                            loadAuth(channel, chain)
                            val loadInputBalanceDeferred = async { loadBalance(channel, chain) }

                            chain.cosmosFetcher?.cosmosBalances = loadInputBalanceDeferred.await()
                            BaseUtils.onParseVesting(chain)
                        } catch (e: Exception) {
                            if (isAdded) {
                                activity?.makeToast(R.string.str_unknown_error)
                            }
                        }
                    }
                }

                outputCosmosChain?.let { chain ->
                    chain.cosmosFetcher()?.let {
                        try {
                            val channel = chain.cosmosFetcher()?.getChannel()
                            loadAuth(channel, chain)
                            val loadOutputBalanceDeferred = async { loadBalance(channel, chain) }

                            chain.cosmosFetcher?.cosmosBalances = loadOutputBalanceDeferred.await()
                            BaseUtils.onParseVesting(chain)
                        } catch (e: Exception) {
                            if (isAdded) {
                                activity?.makeToast(R.string.str_unknown_error)
                            }
                        }
                    }
                }

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

                inputAsset?.decimals?.let { decimal ->
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
                    outputAsset?.decimals?.let { outputDecimal ->
                        val dpAmount = dpOutputAmount?.movePointLeft(outputDecimal)
                            ?.setScale(6, RoundingMode.DOWN)?.toPlainString()
                        outputAmount.text = formatAmount(dpAmount.toString(), outputDecimal)
                        slippage.text = "$skipSlippage%"

                        val inputText = inputAmountTxt.text.toString().trim()
                        val userInput = inputText.toBigDecimal()
                        inputAsset?.decimals?.let { inputDecimal ->
                            val inputAmount = userInput.movePointRight(inputDecimal)
                            if (inputAmount > BigDecimal.ZERO) {
                                val swapRate =
                                    dpOutputAmount?.divide(inputAmount, 6, RoundingMode.DOWN)
                                        ?.movePointRight(inputDecimal - outputDecimal)

                                inputRateDenom.text = inputAsset?.symbol
                                inputRateAmount.text = formatAmount("1", 6)
                                outputRateDenom.text = outputAsset?.symbol
                                outputRateAmount.text = formatAmount(swapRate.toString(), 6)
                            }

                            inputCosmosChain?.let { chain ->
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

                            val inputPrice = BaseData.getPrice(inputAsset?.coinGeckoId)
                            val inputValue =
                                inputPrice.multiply(inputAmount).movePointLeft(inputDecimal)
                                    .setScale(6, RoundingMode.DOWN)
                            inputAmountValue.text = formatAssetValue(inputValue)

                            val outputPrice = BaseData.getPrice(outputAsset?.coinGeckoId)
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
                    inputAsset?.decimals?.let { decimal ->
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
            inputAsset?.decimals?.let { decimal ->
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

            inputCosmosChain?.let { chain ->
                val skipMsg = msg.msgs[0]
                toMsg = msg
                val innerMsg = JSONObject(skipMsg.msg)

                if (skipMsg.msg_type_url == "/ibc.applications.transfer.v1.MsgTransfer") {
                    skipTxViewModel.simulateSkipIbcSend(
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
                inputCosmosChain?.let { chain ->
                    txFee?.let { fee ->
                        val gasLimit =
                            (gasUsed.toDouble() * chain.gasMultiply()).toLong().toBigDecimal()
                        val baseFeePosition = chain.getFeeBasePosition()
                        val gasRate =
                            chain.getFeeInfos(requireContext())[baseFeePosition].feeDatas.firstOrNull {
                                it.denom == fee.getAmount(0)?.denom
                            }
                        val feeCoinAmount =
                            gasRate?.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)

                        val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0)?.denom)
                            .setAmount(feeCoinAmount.toString()).build()
                        txFee = TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong())
                            .addAmount(feeCoin).build()

                        BaseData.getAsset(chain.apiName, fee.getAmount(0).denom)?.let { feeAsset ->
                            feeAsset.decimals?.let { decimal ->
                                txFee?.getAmount(0)?.amount?.toBigDecimal()?.movePointLeft(decimal)
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

        skipTxViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            binding.apply {
                btnSwap.updateButtonView(false)
                btnToggle.updateToggleButtonView(false)
                requireContext().showToast(view, response, true)
                return@observe
            }
        }
    }

    private fun bindIbcSend(innerMsg: JSONObject): MsgTransfer? {
        val sendCoin =
            CoinProto.Coin.newBuilder().setDenom(innerMsg.getJSONObject("token").getString("denom"))
                .setAmount(innerMsg.getJSONObject("token").getString("amount")).build()

        return MsgTransfer.newBuilder().setSender(innerMsg.getString("sender"))
            .setReceiver(innerMsg.getString("receiver"))
            .setSourceChannel(innerMsg.getString("source_channel"))
            .setSourcePort(innerMsg.getString("source_port"))
            .setTimeoutTimestamp(innerMsg.getString("timeout_timestamp").toLong())
            .setToken(sendCoin).setMemo(innerMsg.optString("memo", "")).build()
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

    private fun setUpClickAction() {
        binding.apply {
            btnSlippage.setOnClickListener {
                toMsg = null
                handleOneClickWithDelay(
                    SlippageFragment.newInstance(object : SlippageListener {
                        override fun slippage(position: Int) {
                            skipSlippage = position.toString()
                            updateAmountView()
                        }
                    })
                )
            }

            inputChainLayout.setOnClickListener {
                handleOneClickWithDelay(
                    ChainFragment.newInstance(skipChains,
                        ChainListType.SELECT_INPUT_SWAP,
                        object : ChainSelectListener {
                            override fun select(chainId: String) {
                                try {
                                    if (inputCosmosChain?.chainIdCosmos != chainId) {
                                        loading.visibility = View.VISIBLE

                                        skipDataJob = lifecycleScope.launch(Dispatchers.IO) {
                                            inputCosmosChain =
                                                skipChains.firstOrNull { it.chainIdCosmos == chainId }
                                            inputAssets.clear()
                                            inputCosmosChain?.let { chain ->
                                                chain.cosmosFetcher()?.let {
                                                    try {
                                                        skipAssets?.getAsJsonObject("chain_to_assets_map")
                                                            ?.getAsJsonObject(chain.chainIdCosmos)
                                                            ?.getAsJsonArray("assets")
                                                            ?.forEach { json ->
                                                                BaseData.getAsset(
                                                                    chain.apiName,
                                                                    json.asJsonObject.get("denom").asString
                                                                )?.let { asset ->
                                                                    inputAssets.add(asset)
                                                                }
                                                            }
                                                        inputAssetSelected =
                                                            inputAssets.firstOrNull { it.denom == chain.stakeDenom }

                                                        val channel =
                                                            chain.cosmosFetcher?.getChannel()
                                                        loadAuth(channel, chain)
                                                        val loadInputBalanceDeferred = async {
                                                            loadBalance(
                                                                channel, chain
                                                            )
                                                        }

                                                        chain.cosmosFetcher?.cosmosBalances =
                                                            loadInputBalanceDeferred.await()
                                                        BaseUtils.onParseVesting(chain)
                                                    } catch (e: Exception) {
                                                        activity?.makeToast(R.string.str_unknown_error)
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
                    AssetSelectFragment.newInstance(inputCosmosChain,
                        inputAssets,
                        inputCosmosChain?.cosmosFetcher?.cosmosBalances,
                        AssetSelectType.SWAP_INPUT,
                        object : AssetListener {
                            override fun select(denom: String) {
                                inputAssetSelected = inputAssets.firstOrNull { it.denom == denom }
                                initView()
                            }
                        })
                )
            }

            btnToggle.setOnClickListener {
                val tempChain = inputCosmosChain
                val tempAssetList = inputAssets
                val tempAssetSelected = inputAssetSelected
                val tempAsset = inputAsset

                inputCosmosChain = outputCosmosChain
                inputAssets = outputAssets
                inputAssetSelected = outputAssetSelected
                inputAsset = outputAsset

                outputCosmosChain = tempChain
                outputAssets = tempAssetList
                outputAssetSelected = tempAssetSelected
                outputAsset = tempAsset

                initView()
            }

            outputChainLayout.setOnClickListener {
                handleOneClickWithDelay(
                    ChainFragment.newInstance(
                        skipChains,
                        ChainListType.SELECT_OUTPUT_SWAP,
                        object : ChainSelectListener {
                            override fun select(chainId: String) {
                                try {
                                    if (outputCosmosChain?.chainIdCosmos != chainId) {
                                        loading.visibility = View.VISIBLE

                                        skipDataJob = lifecycleScope.launch(Dispatchers.IO) {
                                            outputCosmosChain =
                                                skipChains.firstOrNull { it.chainIdCosmos == chainId }
                                            outputAssets.clear()
                                            outputCosmosChain?.let { chain ->
                                                chain.cosmosFetcher()?.let {
                                                    try {
                                                        skipAssets?.getAsJsonObject("chain_to_assets_map")
                                                            ?.getAsJsonObject(chain.chainIdCosmos)
                                                            ?.getAsJsonArray("assets")
                                                            ?.forEach { json ->
                                                                BaseData.getAsset(
                                                                    chain.apiName,
                                                                    json.asJsonObject.get("denom").asString
                                                                )?.let { asset ->
                                                                    outputAssets.add(asset)
                                                                }
                                                            }
                                                        outputAssetSelected =
                                                            outputAssets.firstOrNull { it.denom == chain.stakeDenom }

                                                        val channel = chain.cosmosFetcher?.getChannel()
                                                        loadAuth(channel, chain)
                                                        val loadOutputBalanceDeferred = async {
                                                            loadBalance(
                                                                channel, chain
                                                            )
                                                        }

                                                        chain.cosmosFetcher?.cosmosBalances =
                                                            loadOutputBalanceDeferred.await()
                                                        BaseUtils.onParseVesting(chain)
                                                    } catch (e: Exception) {
                                                        activity?.makeToast(R.string.str_unknown_error)
                                                    }
                                                }
                                            }

                                            withContext(Dispatchers.Main) {
                                                initView()
                                            }
                                        }
                                    }
                                } catch (_: Exception) { }
                            }
                        })
                )
            }

            outputTokenLayout.setOnClickListener {
                handleOneClickWithDelay(
                    AssetSelectFragment.newInstance(outputCosmosChain,
                        outputAssets,
                        outputCosmosChain?.cosmosFetcher?.cosmosBalances,
                        AssetSelectType.SWAP_OUTPUT,
                        object : AssetListener {
                            override fun select(denom: String) {
                                outputAssetSelected = outputAssets.firstOrNull { it.denom == denom }
                                initView()
                            }
                        })
                )
            }

            btnHalf.setOnClickListener {
                val halfAmount = availableAmount.multiply(BigDecimal(0.5))
                    .movePointLeft(inputAsset?.decimals ?: 6)
                    .setScale(inputAsset?.decimals ?: 6, RoundingMode.DOWN)
                inputAmountTxt.setText(halfAmount.toPlainString())
                updateAmountView()
                if (halfAmount > BigDecimal.ZERO) {
                    inputAmountTxt.setSelection(halfAmount.toPlainString().length)
                } else {
                    inputAmountTxt.setSelection(halfAmount.toPlainString().length - 1)
                }
            }

            btnMax.setOnClickListener {
                val maxAmount = availableAmount.movePointLeft(inputAsset?.decimals ?: 6)
                    .setScale(inputAsset?.decimals ?: 6, RoundingMode.DOWN)
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

    private val swapResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.loading.visibility = View.VISIBLE
                val skipMsg = toMsg?.msgs?.get(0)
                skipMsg?.let {
                    val innerMsg = JSONObject(skipMsg.msg)

                    inputCosmosChain?.let { chain ->
                        when (skipMsg.msg_type_url) {
                            "/ibc.applications.transfer.v1.MsgTransfer" -> {
                                skipTxViewModel.broadcastSkipIbcSend(
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
            inputAsset?.denom,
            inputCosmosChain?.chainIdCosmos,
            outputAsset?.denom,
            outputCosmosChain?.chainIdCosmos
        )
    }

    private fun bindSkipMsgReq(route: SkipRouteResponse): SkipMsgReq {
        val addressList = mutableListOf<String>()
        route.chain_ids?.forEach { chainId ->
            allSwapAbleChains?.firstOrNull { it.chainIdCosmos == chainId && it.isDefault }?.address?.let { address ->
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
            affiliate(route.swap_venue)
        )
    }

    private fun baseFee(): TxProto.Fee {
        val minFee = inputCosmosChain?.getDefaultFeeCoins(requireContext())?.firstOrNull()
        minFee?.let {
            val feeCoin =
                CoinProto.Coin.newBuilder().setDenom(it.denom).setAmount(it.amount).build()
            return TxProto.Fee.newBuilder().setGasLimit(BASE_GAS_AMOUNT.toLong()).addAmount(feeCoin)
                .build()
        }
        return TxProto.Fee.newBuilder().build()
    }

    private fun affiliate(venue: SwapVenue?): MutableList<Affiliate> {
        val result: MutableList<Affiliate> = mutableListOf()
        if (venue?.chain_id?.contains("osmosis") == true) {
            result.add(Affiliate("osmo1clpqr4nrk4khgkxj78fcwwh6dl3uw4epasmvnj", "100"))
            return result
        } else if (venue?.chain_id?.contains("neutron") == true) {
            result.add(Affiliate("neutron1clpqr4nrk4khgkxj78fcwwh6dl3uw4ep35p7l8", "100"))
            return result
        } else if (venue?.chain_id?.contains("phoenix") == true) {
            result.add(Affiliate("terra1564j3fq8p8np4yhh4lytnftz33japc03wuejxm", "100"))
            return result
        } else if (venue?.chain_id?.contains("injective") == true) {
            result.add(Affiliate("inj1rvqzf9u2uxttmshn302anlknfgsatrh5mcu6la", "100"))
            return result
        } else if (venue?.chain_id?.contains("pacific") == true) {
            result.add(Affiliate("sei1hnkkqnzwmyw652muh6wfea7xlfgplnyj3edm09", "100"))
            return result
        } else if (venue?.chain_id?.contains("chihuahua") == true) {
            result.add(Affiliate("chihuahua1tgcypttehx3afugys6eq28h0kpmswfkgcuewfw", "100"))
            return result
        }
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
                    putExtra("selectedChain", inputCosmosChain?.tag)
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
        return if (chain.supportCosmos()) {
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
        return if (chain.supportCosmos()) {
            val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(2000).build()
            val stub = newBlockingStub(channel).withDeadlineAfter(8L, TimeUnit.SECONDS)
            val request = QueryAllBalancesRequest.newBuilder().setPagination(pageRequest)
                .setAddress(chain.address).build()
            stub.allBalances(request).balancesList
        } else {
            RetrofitInstance.lcdApi(chain).lcdBalanceInfo(chain.address, "2000").balance()
        }
    }

    override fun onDestroyView() {
        _binding = null
        skipDataJob?.cancel()
        super.onDestroyView()
    }
}