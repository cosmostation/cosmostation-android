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
import androidx.lifecycle.ViewModelProvider
import com.cosmos.auth.v1beta1.QueryGrpc
import com.cosmos.auth.v1beta1.QueryProto
import com.cosmos.bank.v1beta1.QueryGrpc.newBlockingStub
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse
import com.cosmos.base.query.v1beta1.PaginationProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.protobuf.ByteString
import com.ibc.applications.transfer.v1.TxProto.MsgTransfer
import io.grpc.ManagedChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseConstant.BASE_GAS_AMOUNT
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.handlerRight
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.updateToggleButtonView
import wannabit.io.cosmostaion.data.model.req.Affiliate
import wannabit.io.cosmostaion.data.model.req.SkipMsgReq
import wannabit.io.cosmostaion.data.model.req.SkipRouteReq
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.SkipMsgResponse
import wannabit.io.cosmostaion.data.model.res.SkipRouteResponse
import wannabit.io.cosmostaion.data.model.res.SwapVenue
import wannabit.io.cosmostaion.data.repository.skip.SkipRepositoryImpl
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentSwapBinding
import wannabit.io.cosmostaion.ui.dialog.tx.AssetSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.ChainFragment
import wannabit.io.cosmostaion.ui.dialog.tx.ChainListType
import wannabit.io.cosmostaion.ui.dialog.tx.ChainSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.SlippageFragment
import wannabit.io.cosmostaion.ui.dialog.tx.SlippageListener
import wannabit.io.cosmostaion.ui.dialog.tx.SwapWarnFragment
import wannabit.io.cosmostaion.ui.dialog.tx.select.AssetSelectFragment
import wannabit.io.cosmostaion.ui.dialog.tx.select.AssetSelectType
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

    private var allCosmosLines: MutableList<CosmosLine>? = mutableListOf()
    private var skipChains: MutableList<CosmosLine> = mutableListOf()
    private var skipAssets: JsonObject? = null
    private var skipSlippage = "1"

    private var inputCosmosLine: CosmosLine? = null
    private var inputAssets: MutableList<Asset> = mutableListOf()
    private var inputAssetSelected: Asset? = null
    private var inputAsset: Asset? = null

    private var outputCosmosLine: CosmosLine? = null
    private var outputAssets: MutableList<Asset> = mutableListOf()
    private var outputAssetSelected: Asset? = null
    private var outputAsset: Asset? = null

    private var availableAmount: BigDecimal = BigDecimal.ZERO

    private var route: SkipRouteResponse? = null
    private var toMsg: SkipMsgResponse? = null
    private var txFee: TxProto.Fee? = null

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
        clickAction()
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
        val baseAccount = BaseData.baseAccount
        skipDataJob = CoroutineScope(Dispatchers.IO).launch {
            allCosmosLines = baseAccount?.initOnlyKeyData()
            skipViewModel.loadData()
        }
    }

    private fun updateView() {
        binding.apply {
            swapLayout.visibility = View.VISIBLE
            initView()

            if (BaseData.getSwapWarn()){
                val bottomSheet = SwapWarnFragment()
                bottomSheet.show(requireActivity().supportFragmentManager, SwapWarnFragment::class.java.name)
            }
        }
    }

    private fun initView() {
        binding.apply {
            toMsg = null
            inputView.setBackgroundResource(R.drawable.cell_bg)
            outputView.setBackgroundResource(R.drawable.cell_bg)
            feeView.setBackgroundResource(R.drawable.cell_bg)
            errorView.setBackgroundResource(R.drawable.cell_error_bg)
            loading.visibility = View.GONE
            btnSwap.updateButtonView(false)
            btnToggle.updateToggleButtonView(true)

            feeView.visibility = View.GONE
            errorView.visibility = View.GONE
            inputAmountTxt.setText("")
            outputAmount.text = ""

            txFee = baseFee()
            inputCosmosLine?.let { line ->
                fromAddress.text = line.address
                inputChainImg.setImageResource(line.logo)
                inputChain.text = line.name.uppercase()

                inputAssetSelected?.denom?.let { inputDenom ->
                    inputAsset = BaseData.getAsset(line.apiName, inputDenom)
                    inputAsset?.let { asset ->
                        inputTokenImg.setTokenImg(asset.assetImg())
                        inputToken.text = asset.symbol
                    }

                    val inputBalance = line.balanceAmount(inputDenom)
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
                    inputAsset?.decimals?.let { decimal ->
                        val dpAmount = availableAmount.movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                        inputAvailable.text = formatAmount(dpAmount.toPlainString(), decimal)
                    }

                    outputCosmosLine?.let { line ->
                        toAddress.text = line.address
                        outputChainImg.setImageResource(line.logo)
                        outputChain.text = line.name.uppercase()

                        outputAssetSelected?.denom?.let { outputDenom ->
                            outputAsset = BaseData.getAsset(line.apiName, outputDenom)
                            outputAsset?.let { asset ->
                                outputTokenImg.setTokenImg(asset.assetImg())
                                outputToken.text = asset.symbol
                            }

                            val outputBalance = line.balanceAmount(outputDenom)
                            outputAsset?.decimals?.let { decimal ->
                                val dpAmount = outputBalance.movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                                outputAvailable.text = formatAmount(dpAmount.toPlainString(), decimal)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setUpViewModel() {
        skipViewModel.skipDataResult.observe(viewLifecycleOwner) { response ->
            response?.let { skipData ->
                skipData.skipChains?.chains?.forEach { sChain ->
                    allCosmosLines?.firstOrNull { it.chainId == sChain.chain_id && it.isDefault }?.let { skipChain ->
                            skipChains.add(skipChain)
                        }
                }
                skipAssets = skipData.skipAssets

                inputCosmosLine = skipChains.firstOrNull { it.tag == "cosmos118" }
                inputCosmosLine?.let { line ->
                    skipAssets?.getAsJsonObject("chain_to_assets_map")
                        ?.getAsJsonObject(line.chainId)?.getAsJsonArray("assets")?.forEach { json ->
                            BaseData.getAsset(line.apiName, json.asJsonObject.get("denom").asString)?.let { asset ->
                                inputAssets.add(asset)
                            }
                        }
                    inputAssetSelected = inputAssets.firstOrNull { it.denom == line.stakeDenom }
                }

                outputCosmosLine = skipChains.firstOrNull { it.tag == "neutron118" }
                outputCosmosLine?.let { line ->
                    skipAssets?.getAsJsonObject("chain_to_assets_map")
                        ?.getAsJsonObject(line.chainId)?.getAsJsonArray("assets")?.forEach { json ->
                            BaseData.getAsset(line.apiName, json.asJsonObject.get("denom").asString)?.let { asset ->
                                outputAssets.add(asset)
                            }
                        }
                    outputAssetSelected = outputAssets.firstOrNull { it.denom == line.stakeDenom }
                }
            }

            skipDataJob = CoroutineScope(Dispatchers.IO).launch {
                inputCosmosLine?.let { line ->
                    val channel = getChannel(line)
                    try {
                        val loadInputAuthDeferred = async { loadAuth(channel, line.address) }
                        val loadInputBalanceDeferred = async { loadBalance(channel, line.address) }
                        val loadInputParamDeferred = async { line.loadParam() }

                        line.cosmosAuth = loadInputAuthDeferred.await().account
                        line.cosmosBalances = loadInputBalanceDeferred.await().balancesList
                        line.param = loadInputParamDeferred.await()
                        BaseUtils.onParseVestingAccount(line)

                    } finally {
                        channel.shutdown()
                        try {
                            if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                                channel.shutdownNow()
                            }
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                }

                outputCosmosLine?.let { line ->
                    val channel = getChannel(line)
                    try {
                        val loadOutputAuthDeferred = async { loadAuth(channel, line.address) }
                        val loadOutputBalanceDeferred = async { loadBalance(channel, line.address) }
                        val loadOutputParamDeferred = async { line.loadParam() }

                        line.cosmosAuth = loadOutputAuthDeferred.await().account
                        line.cosmosBalances = loadOutputBalanceDeferred.await().balancesList
                        line.param = loadOutputParamDeferred.await()
                        BaseUtils.onParseVestingAccount(line)

                    } finally {
                        channel.shutdown()
                        try {
                            if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                                channel.shutdownNow()
                            }
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
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
                if (inputText.isEmpty()) { return@observe }
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
                    val dpOutputAmount = route?.amount_out?.toBigDecimal()?.multiply(dpSlippage)?.movePointLeft(2)?.setScale(0, RoundingMode.DOWN)
                    outputAsset?.decimals?.let { outputDecimal ->
                        val dpAmount = dpOutputAmount?.movePointLeft(outputDecimal)?.setScale(6, RoundingMode.DOWN)?.toPlainString()
                        outputAmount.text = formatAmount(dpAmount.toString(), outputDecimal)
                        slippage.text = "$skipSlippage%"

                        val inputText = inputAmountTxt.text.toString().trim()
                        val userInput = inputText.toBigDecimal()
                        inputAsset?.decimals?.let { inputDecimal ->
                            val inputAmount = userInput.movePointRight(inputDecimal)
                            if (inputAmount > BigDecimal.ZERO) {
                                val swapRate = dpOutputAmount?.divide(inputAmount, 6, RoundingMode.DOWN)?.movePointRight(inputDecimal - outputDecimal)

                                inputRateDenom.text = inputAsset?.symbol
                                inputRateAmount.text = formatAmount("1", 6)
                                outputRateDenom.text = outputAsset?.symbol
                                outputRateAmount.text = formatAmount(swapRate.toString(), 6)
                            }

                            inputCosmosLine?.let { line ->
                                txFee?.let { fee ->
                                    BaseData.getAsset(line.apiName, fee.getAmount(0).denom)?.let { feeAsset ->
                                        feeAsset.decimals?.let { decimal ->
                                            val dpFeeAmount = fee.getAmount(0).amount.toBigDecimal().movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                                            txFeeAmount.text = formatAmount(dpFeeAmount.toPlainString(), decimal)
                                            txFeeDenom.text = feeAsset.symbol
                                        }
                                    }
                                }
                            }
                            swapVenue.text = route?.swap_venue?.name

                            val inputPrice = BaseData.getPrice(inputAsset?.coinGeckoId)
                            val inputValue = inputPrice.multiply(inputAmount).movePointLeft(inputDecimal).setScale(6, RoundingMode.DOWN)
                            inputAmountValue.text = formatAssetValue(inputValue)

                            val outputPrice = BaseData.getPrice(outputAsset?.coinGeckoId)
                            val outputValue = outputPrice.multiply(dpOutputAmount).movePointLeft(outputDecimal).setScale(6, RoundingMode.DOWN)
                            outputAmountValue.text = formatAssetValue(outputValue)

                            feeView.visibility = View.VISIBLE
                            txSimul(response)
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
                            if (userInput.toBigDecimal().handlerRight(decimal, 0) == BigDecimal.ZERO) {
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

    private fun txSimul(msg: SkipMsgResponse) {
        binding.apply {
            btnSwap.updateButtonView(false)
            btnToggle.updateToggleButtonView(false)

            inputCosmosLine?.let { line ->
                val skipMsg = msg.msgs[0]
                toMsg = msg
                val innerMsg = JSONObject(skipMsg.msg)

                if (skipMsg.msg_type_url == "/ibc.applications.transfer.v1.MsgTransfer") {
                    skipTxViewModel.simulateSkipIbcSend(getChannel(line), line.address, bindIbcSend(innerMsg), txFee, "")
                } else if (skipMsg.msg_type_url == "/cosmwasm.wasm.v1.MsgExecuteContract") {
                    skipTxViewModel.simulateWasm(getChannel(line), line.address, bindWasm(innerMsg), txFee, "")
                }
            }
        }
    }

    private fun setUpSimulate() {
        skipTxViewModel.simulate.observe(viewLifecycleOwner) { gasInfo ->
            binding.apply {
                inputCosmosLine?.let { line ->
                    txFee?.let { fee ->
                        val gasLimit =
                            (gasInfo.gasUsed.toDouble() * line.gasMultiply()).toLong().toBigDecimal()
                        val baseFeePosition = line.getFeeBasePosition()
                        val gasRate =
                            line.getFeeInfos(requireContext())[baseFeePosition].feeDatas.firstOrNull {
                                it.denom == fee.getAmount(0)?.denom
                            }
                        val feeCoinAmount =
                            gasRate?.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)

                        val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0)?.denom)
                            .setAmount(feeCoinAmount.toString()).build()
                        txFee =
                            TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin)
                                .build()

                        BaseData.getAsset(line.apiName, fee.getAmount(0).denom)?.let { feeAsset ->
                            feeAsset.decimals?.let { decimal ->
                                txFee?.getAmount(0)?.amount?.toBigDecimal()?.
                                movePointLeft(decimal)?.setScale(decimal, RoundingMode.DOWN)?.let { amount ->
                                    txFeeAmount.text = formatAmount(amount.toPlainString(), decimal)
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
        val sendCoin = CoinProto.Coin.newBuilder()
            .setDenom(innerMsg.getJSONObject("token").getString("denom"))
            .setAmount(innerMsg.getJSONObject("token").getString("amount"))
            .build()

        return MsgTransfer.newBuilder()
            .setSender(innerMsg.getString("sender"))
            .setReceiver(innerMsg.getString("receiver"))
            .setSourceChannel(innerMsg.getString("source_channel"))
            .setSourcePort(innerMsg.getString("source_port"))
            .setTimeoutTimestamp(innerMsg.getString("timeout_timestamp").toLong())
            .setToken(sendCoin)
            .setMemo(innerMsg.getString("memo"))
            .build()
    }

    private fun bindWasm(innerMsg: JSONObject): MutableList<MsgExecuteContract?> {
        val result: MutableList<MsgExecuteContract?> = mutableListOf()
        val jsonData = Gson().toJson(innerMsg.getJSONObject("msg"))
        val fundCoin = CoinProto.Coin.newBuilder()
            .setDenom(innerMsg.getJSONArray("funds").getJSONObject(0).getString("denom"))
            .setAmount(innerMsg.getJSONArray("funds").getJSONObject(0).getString("amount"))
            .build()

        val msgExecuteContract = MsgExecuteContract.newBuilder()
            .setSender(innerMsg.getString("sender"))
            .setContract(innerMsg.getString("contract"))
            .setMsg(ByteString.copyFromUtf8(jsonData))
            .addFunds(fundCoin)
            .build()
        result.add(msgExecuteContract)

        return result
    }

    private fun clickAction() {
        var isClickable = true
        binding.apply {
            btnSlippage.setOnClickListener {
                toMsg = null
                if (isClickable) {
                    isClickable = false
                    SlippageFragment(object : SlippageListener {
                        override fun slippage(position: Int) {
                            skipSlippage = position.toString()
                            updateAmountView()
                        }

                    }).show(
                        requireActivity().supportFragmentManager, SlippageFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            inputChainLayout.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    ChainFragment(skipChains, ChainListType.SELECT_INPUT_SWAP, object : ChainSelectListener {
                        override fun select(chainId: String) {
                            if (inputCosmosLine?.chainId != chainId) {
                                loading.visibility = View.VISIBLE

                                skipDataJob = CoroutineScope(Dispatchers.IO).launch {
                                    inputCosmosLine = skipChains.firstOrNull { it.chainId == chainId }
                                    inputAssets.clear()
                                    inputCosmosLine?.let { line ->
                                        skipAssets?.getAsJsonObject("chain_to_assets_map")
                                            ?.getAsJsonObject(line.chainId)?.getAsJsonArray("assets")?.forEach { json ->
                                                BaseData.getAsset(line.apiName, json.asJsonObject.get("denom").asString)?.let { asset ->
                                                    inputAssets.add(asset)
                                                }
                                            }
                                        inputAssetSelected = inputAssets.firstOrNull { it.denom == line.stakeDenom }

                                        val channel = getChannel(line)
                                        try {
                                            val loadInputAuthDeferred = async { loadAuth(channel, line.address) }
                                            val loadInputBalanceDeferred = async { loadBalance(channel, line.address) }
                                            val loadInputParamDeferred = async { line.loadParam() }

                                            line.cosmosAuth = loadInputAuthDeferred.await().account
                                            line.cosmosBalances = loadInputBalanceDeferred.await().balancesList
                                            line.param = loadInputParamDeferred.await()
                                            BaseUtils.onParseVestingAccount(line)

                                        } finally {
                                            channel.shutdown()
                                            try {
                                                if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                                                    channel.shutdownNow()
                                                }
                                            } catch (e: InterruptedException) {
                                                e.printStackTrace()
                                            }
                                        }
                                    }

                                    withContext(Dispatchers.Main) {
                                        initView()
                                    }
                                }
                            }
                        }

                    }).show(
                        requireActivity().supportFragmentManager, ChainFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            inputTokenLayout.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    AssetSelectFragment(
                        inputAssets,
                        inputCosmosLine?.cosmosBalances,
                        AssetSelectType.SWAP_INPUT,
                        object : AssetSelectListener {
                            override fun select(denom: String) {
                                inputAssetSelected = inputAssets.firstOrNull { it.denom == denom }
                                initView()
                            }

                        }).show(
                        requireActivity().supportFragmentManager, AssetSelectFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            btnToggle.setOnClickListener {
                val tempChain = inputCosmosLine
                val tempAssetList = inputAssets
                val tempAssetSelected = inputAssetSelected
                val tempAsset = inputAsset

                inputCosmosLine = outputCosmosLine
                inputAssets = outputAssets
                inputAssetSelected = outputAssetSelected
                inputAsset = outputAsset

                outputCosmosLine = tempChain
                outputAssets = tempAssetList
                outputAssetSelected = tempAssetSelected
                outputAsset = tempAsset

                initView()
            }

            outputChainLayout.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    ChainFragment(skipChains, ChainListType.SELECT_OUTPUT_SWAP, object : ChainSelectListener {
                        override fun select(chainId: String) {
                            if (outputCosmosLine?.chainId != chainId) {
                                loading.visibility = View.VISIBLE

                                skipDataJob = CoroutineScope(Dispatchers.IO).launch {
                                    outputCosmosLine = skipChains.firstOrNull { it.chainId == chainId }
                                    outputAssets.clear()
                                    outputCosmosLine?.let { line ->
                                        skipAssets?.getAsJsonObject("chain_to_assets_map")
                                            ?.getAsJsonObject(line.chainId)?.getAsJsonArray("assets")?.forEach { json ->
                                                BaseData.getAsset(line.apiName, json.asJsonObject.get("denom").asString)?.let { asset ->
                                                    outputAssets.add(asset)
                                                }
                                            }
                                        outputAssetSelected = outputAssets.firstOrNull { it.denom == line.stakeDenom }

                                        val channel = getChannel(line)
                                        try {
                                            val loadOutputAuthDeferred = async { loadAuth(channel, line.address) }
                                            val loadOutputBalanceDeferred = async { loadBalance(channel, line.address) }
                                            val loadOutputParamDeferred = async { line.loadParam() }

                                            line.cosmosAuth = loadOutputAuthDeferred.await().account
                                            line.cosmosBalances = loadOutputBalanceDeferred.await().balancesList
                                            line.param = loadOutputParamDeferred.await()
                                            BaseUtils.onParseVestingAccount(line)

                                        } finally {
                                            channel.shutdown()
                                            try {
                                                if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                                                    channel.shutdownNow()
                                                }
                                            } catch (e: InterruptedException) {
                                                e.printStackTrace()
                                            }
                                        }
                                    }

                                    withContext(Dispatchers.Main) {
                                        initView()
                                    }
                                }
                            }
                        }
                    }).show(
                        requireActivity().supportFragmentManager, ChainFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            outputTokenLayout.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    AssetSelectFragment(
                        outputAssets,
                        outputCosmosLine?.cosmosBalances,
                        AssetSelectType.SWAP_OUTPUT,
                        object : AssetSelectListener {
                            override fun select(denom: String) {
                                outputAssetSelected = outputAssets.firstOrNull { it.denom == denom }
                                initView()
                            }

                        }).show(
                        requireActivity().supportFragmentManager, AssetSelectFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            btnHalf.setOnClickListener {
                inputAsset?.decimals?.let { decimal ->
                    val halfAmount = availableAmount.multiply(BigDecimal(0.5))
                        .movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                    inputAmountTxt.setText(halfAmount.toPlainString())
                    updateAmountView()
                    inputAmountTxt.setSelection(halfAmount.toPlainString().length)
                }
            }

            btnMax.setOnClickListener {
                inputAsset?.decimals?.let { decimal ->
                    val halfAmount = availableAmount.movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                    inputAmountTxt.setText(halfAmount.toPlainString())
                    updateAmountView()
                    inputAmountTxt.setSelection(halfAmount.toPlainString().length)
                }
            }

            btnSwap.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    swapResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom,
                        R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private val swapResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.loading.visibility = View.VISIBLE
                val skipMsg = toMsg?.msgs?.get(0)
                skipMsg?.let {
                    val innerMsg = JSONObject(skipMsg.msg)

                    inputCosmosLine?.let { line ->
                        when (skipMsg.msg_type_url) {
                            "/ibc.applications.transfer.v1.MsgTransfer" -> {
                                skipTxViewModel.broadcastSkipIbcSend(
                                    getChannel(line),
                                    bindIbcSend(innerMsg),
                                    txFee,
                                    "",
                                    line
                                )
                            }

                            "/cosmwasm.wasm.v1.MsgExecuteContract" -> {
                                skipTxViewModel.broadcastWasm(
                                    getChannel(line),
                                    bindWasm(innerMsg),
                                    txFee,
                                    "",
                                    line
                                )

                            }

                            else -> { return@let }
                        }
                    }
                }
            }
        }

    private fun bindSkipRouteReq(amount :String): SkipRouteReq {
        return SkipRouteReq(amount, inputAsset?.denom, inputCosmosLine?.chainId, outputAsset?.denom, outputCosmosLine?.chainId)
    }

    private fun bindSkipMsgReq(route: SkipRouteResponse): SkipMsgReq {
        val addressList = mutableListOf<String>()
        route.chain_ids?.forEach { chainId ->
            allCosmosLines?.firstOrNull { it.chainId == chainId && it.isDefault }?.address?.let { address ->
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
            affiliate(route.swap_venue))
    }

    private fun baseFee(): TxProto.Fee {
        val minFee = inputCosmosLine?.getDefaultFeeCoins(requireContext())?.get(0)
        val feeCoin = CoinProto.Coin.newBuilder().setDenom(minFee?.denom).setAmount(minFee?.amount).build()
        return TxProto.Fee.newBuilder().setGasLimit(BASE_GAS_AMOUNT.toLong()).addAmount(feeCoin).build()
    }

    private fun affiliate(venue: SwapVenue?): MutableList<Affiliate>? {
        val result: MutableList<Affiliate> = mutableListOf()
        if (venue?.chain_id?.contains("osmosis") == true) {
            result.add(Affiliate("osmo1clpqr4nrk4khgkxj78fcwwh6dl3uw4epasmvnj", "100"))
            return result
        } else if (venue?.chain_id?.contains("neutron") == true) {
            result.add(Affiliate("neutron1clpqr4nrk4khgkxj78fcwwh6dl3uw4ep35p7l8", "100"))
            return result
        }
        return result
    }

    private fun setUpBroadcast() {
        skipTxViewModel.broadcastTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                if (txResponse.code > 0) {
                    putExtra("isSuccess", false)
                } else {
                    putExtra("isSuccess", true)
                }
                putExtra("txResultType", TxResultType.SKIP.toString())
                putExtra("errorMsg", txResponse.rawLog)
                putExtra("selectedChain", inputCosmosLine?.tag)
                val hash = txResponse.txhash
                if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                startActivity(this)
            }
            dismiss()
        }
    }

    private fun loadAuth(managedChannel: ManagedChannel, address: String?): QueryProto.QueryAccountResponse {
        val stub = QueryGrpc.newBlockingStub(managedChannel).withDeadlineAfter(8L, TimeUnit.SECONDS)
        val request = QueryProto.QueryAccountRequest.newBuilder().setAddress(address).build()
        return stub.account(request)
    }

    private fun loadBalance(managedChannel: ManagedChannel, address: String?): QueryAllBalancesResponse {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(2000).build()
        val stub = newBlockingStub(managedChannel).withDeadlineAfter(8L, TimeUnit.SECONDS)
        val request = QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(address).build()
        return stub.allBalances(request)
    }

    override fun onDestroyView() {
        _binding = null
        skipDataJob?.cancel()
        super.onDestroyView()
    }
}