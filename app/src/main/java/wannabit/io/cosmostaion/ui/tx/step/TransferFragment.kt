package wannabit.io.cosmostaion.ui.tx.step

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.cosmos.bank.v1beta1.TxProto.MsgSend
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract
import com.google.gson.Gson
import com.google.protobuf.ByteString
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.req.WasmIbcSendMsg
import wannabit.io.cosmostaion.data.model.req.WasmIbcSendReq
import wannabit.io.cosmostaion.data.model.req.WasmSendReq
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.AssetPath
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.databinding.FragmentTransferBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.dialog.tx.AmountSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.AssetFragment
import wannabit.io.cosmostaion.ui.dialog.tx.AssetSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.ChainFragment
import wannabit.io.cosmostaion.ui.dialog.tx.ChainSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.InsertAmountFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoListener
import wannabit.io.cosmostaion.ui.dialog.tx.address.AddressFragment
import wannabit.io.cosmostaion.ui.dialog.tx.address.AddressListener
import wannabit.io.cosmostaion.ui.dialog.tx.address.AddressType
import wannabit.io.cosmostaion.ui.main.chain.TxType
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import java.math.BigDecimal
import java.math.RoundingMode
import java.nio.charset.StandardCharsets

class TransferFragment(
    private val selectedChain: CosmosLine,
    private val toSendDenom: String
) : BaseTxFragment() {

    private var _binding: FragmentTransferBinding? = null
    private val binding get() = _binding!!

    private var recipientAbleChains: MutableList<CosmosLine> = mutableListOf()
    private var transferAssetType: TransferAssetType? = null
    private var selectedAsset: Asset? = null
    private var selectedToken: Token? = null
    private var selectedRecipientChain: CosmosLine? = null

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null

    private var toSendAmount = ""
    private var existedAddress = ""
    private var txMemo = ""
    private var assetPath: AssetPath? = null

    private var availableAmount = BigDecimal.ZERO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        initData()
        clickAction()
        updateFeeView()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            recipientView.setBackgroundResource(R.drawable.cell_bg)
            sendAssetView.setBackgroundResource(R.drawable.cell_bg)
            addressView.setBackgroundResource(R.drawable.cell_bg)
            memoView.setBackgroundResource(R.drawable.cell_bg)
            feeView.setBackgroundResource(R.drawable.cell_bg)

            chainImg.setImageResource(selectedChain.logo)
            chainName.text = selectedChain.name.uppercase()

            BaseData.getAsset(selectedChain.apiName, toSendDenom)?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenName.text = asset.symbol?.uppercase()
            }
        }
    }

    private fun initFee() {
        binding.apply {
            feeInfos = selectedChain.getFeeInfos(requireContext())
            feeSegment.setSelectedBackground(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_accent_purple
                )
            )
            feeSegment.setRipple(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_accent_purple
                )
            )

            for (i in feeInfos.indices) {
                val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                feeSegment.addView(
                    segmentView.root,
                    i,
                    LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                )
                segmentView.btnTitle.text = feeInfos[i].title
            }
            selectedFeeInfo = selectedChain.getFeeBasePosition()
            txFee = selectedChain.getInitFee(requireContext())
        }
    }

    private fun initData() {
        binding.apply {
            BaseData.assets?.firstOrNull { it.denom?.lowercase() == toSendDenom.lowercase() }
                ?.let { asset ->
                    selectedAsset = asset
                    transferAssetType = TransferAssetType.COIN_TRANSFER

                } ?: run {
                selectedChain.cw20tokens.firstOrNull { it.address == toSendDenom }.let { token ->
                    selectedToken = token
                    transferAssetType = TransferAssetType.CW20_TRANSFER
                }
            }

            recipientAbleChains.add(selectedChain)
            BaseData.assets?.forEach { asset ->
                if (transferAssetType == TransferAssetType.COIN_TRANSFER) {
                    if (asset.chain == selectedChain.apiName && asset.denom?.lowercase() == toSendDenom.lowercase()) {
                        allCosmosLines().firstOrNull { it.apiName == asset.beforeChain(selectedChain.apiName) }
                            ?.let { sendAble ->
                                if (recipientAbleChains.none { it.apiName == sendAble.apiName }) {
                                    recipientAbleChains.add(sendAble)
                                }
                            }

                    } else if (asset.counterParty?.denom?.lowercase() == toSendDenom.lowercase()) {
                        allCosmosLines().firstOrNull { it.apiName == asset.chain }
                            ?.let { sendAble ->
                                if (recipientAbleChains.none { it.apiName == sendAble.apiName }) {
                                    recipientAbleChains.add(sendAble)
                                }
                            }
                    }

                } else {
                    if (asset.counterParty?.denom?.lowercase() == toSendDenom.lowercase()) {
                        allCosmosLines().firstOrNull { it.apiName == asset.chain }
                            ?.let { sendAble ->
                                if (recipientAbleChains.none { it.apiName == sendAble.apiName }) {
                                    recipientAbleChains.add(sendAble)
                                }
                            }
                    }
                }
            }
            recipientAbleChains.sortWith { o1, o2 ->
                when {
                    o1.name == selectedChain.name -> -1
                    o2.name == selectedChain.name -> 1
                    o1.name == "Cosmos" -> -1
                    o2.name == "Cosmos" -> 1
                    else -> 0
                }
            }
            selectedRecipientChain = recipientAbleChains[0]

            if (transferAssetType == TransferAssetType.COIN_TRANSFER) {
                BaseData.getAsset(selectedChain.apiName, toSendDenom)?.let { asset ->
                    tokenImg.setTokenImg(asset)
                    tokenName.text = asset.symbol
                }
            } else {
                selectedChain.cw20tokens.firstOrNull { it.address == toSendDenom }.let { token ->
                    token?.let {
                        tokenImg.setTokenImg(it.assetImg())
                        tokenName.text = it.symbol
                    }
                }
            }
        }
    }

    private fun updateChainView() {
        binding.apply {
            selectedRecipientChain?.let {
                chainImg.setImageResource(it.logo)
                chainName.text = it.name.uppercase()
            }
            if (selectedChain.chainId == selectedRecipientChain?.chainId) {
                sendTitle.text = getString(R.string.title_coin_send)
            } else {
                sendTitle.text = getString(R.string.title_ibc_send)
            }
        }
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            tabMsgTxt.visibility = View.GONE
            amountLayout.visibility = View.VISIBLE
            toSendAmount = toAmount

            if (transferAssetType == TransferAssetType.COIN_TRANSFER) {
                BaseData.getAsset(selectedChain.apiName, toSendDenom)?.let {
                    it.decimals?.let { decimal ->
                        val dpAmount = BigDecimal(toAmount).movePointLeft(decimal)
                            .setScale(decimal, RoundingMode.DOWN)
                        val price = BaseData.getPrice(selectedAsset?.coinGeckoId)
                        val value = price.multiply(dpAmount)

                        sendAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                        sendValue.text = formatAssetValue(value)
                    }
                }

            } else {
                selectedToken?.let { token ->
                    val dpAmount = BigDecimal(toAmount).movePointLeft(token.decimals)
                        .setScale(token.decimals, RoundingMode.DOWN)
                    val price = BaseData.getPrice(selectedToken?.coinGeckoId)
                    val value = price.multiply(dpAmount)

                    sendAmount.text = formatAmount(dpAmount.toPlainString(), token.decimals)
                    sendValue.text = formatAssetValue(value)
                }
            }
        }
        txSimul()
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
        txSimul()
    }

    private fun updateFeeView() {
        binding.apply {
            txFee?.getAmount(0)?.let { fee ->
                BaseData.getAsset(selectedChain.apiName, fee.denom)?.let { asset ->
                    feeTokenImg.setTokenImg(asset)
                    feeToken.text = asset.symbol

                    val amount = fee.amount.toBigDecimal()
                    val price = BaseData.getPrice(asset.coinGeckoId)

                    asset.decimals?.let { decimal ->
                        val dpAmount =
                            amount.movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                        feeAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                        feeDenom.text = asset.symbol
                        val value = price.multiply(amount).movePointLeft(decimal)
                            .setScale(decimal, RoundingMode.DOWN)
                        feeValue.text = formatAssetValue(value)
                    }
                }
            }

            availableAmount = if (transferAssetType == TransferAssetType.COIN_TRANSFER) {
                val balanceAmount = selectedChain.balanceAmount(toSendDenom)
                if (txFee?.getAmount(0)?.denom == toSendDenom) {
                    val feeAmount = BigDecimal(txFee?.getAmount(0)?.amount)
                    if (feeAmount > balanceAmount) {
                    }
                    balanceAmount.subtract(feeAmount)
                } else {
                    balanceAmount
                }

            } else {
                BigDecimal(selectedToken?.amount)
            }
        }
    }

    private fun updateMemoView(memo: String) {
        binding.apply {
            txMemo = memo
            if (txMemo.isEmpty()) {
                tabMemoMsg.text = getString(R.string.str_tap_for_add_memo_msg)
                tabMemoMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(),
                        R.color.color_base03
                    )
                )
            } else {
                tabMemoMsg.text = txMemo
                tabMemoMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(),
                        R.color.color_base01
                    )
                )
            }
        }
        txSimul()
    }

    private fun clickAction() {
        var isClickable = true
        binding.apply {
            btnQr.setOnClickListener {

            }

            recipientView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    ChainFragment(recipientAbleChains, object : ChainSelectListener {
                        override fun select(chainId: String) {
                            if (chainId != selectedRecipientChain?.chainId) {
                                selectedRecipientChain =
                                    recipientAbleChains.firstOrNull { it.chainId == chainId }
                                recipientAddress.text = ""
                                updateChainView()
                                updateAddressView(recipientAddress.text.toString().trim())
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

            sendAssetView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    InsertAmountFragment(
                        TxType.TRANSFER,
                        transferAssetType,
                        availableAmount,
                        toSendAmount,
                        selectedAsset,
                        selectedToken,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }

                        }).show(
                        requireActivity().supportFragmentManager,
                        InsertAmountFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            addressView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    AddressFragment(
                        selectedChain,
                        selectedRecipientChain,
                        existedAddress,
                        AddressType.DEFAULT_TRANSFER,
                        object : AddressListener {
                            override fun address(address: String) {
                                updateAddressView(address)
                            }

                        }).show(
                        requireActivity().supportFragmentManager, AddressFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            feeTokenLayout.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    AssetFragment(
                        selectedChain,
                        feeInfos[selectedFeeInfo].feeDatas,
                        object : AssetSelectListener {
                            override fun select(denom: String) {
                                var tempCoin: CoinProto.Coin? = null
                                selectedChain.getDefaultFeeCoins(requireContext())
                                    .forEach { feeCoin ->
                                        if (feeCoin.denom == denom) {
                                            tempCoin = CoinProto.Coin.newBuilder().setDenom(denom)
                                                .setAmount(feeCoin.amount).build()
                                        }
                                    }
                                val tempTxFee = TxProto.Fee.newBuilder()
                                    .setGasLimit(BaseConstant.BASE_GAS_AMOUNT.toLong())
                                    .addAmount(tempCoin).build()
                                txFee = tempTxFee
                                updateFeeView()
                                txSimul()
                            }

                        }).show(
                        requireActivity().supportFragmentManager, ChainFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeeInfo = position
                txFee = selectedChain.getBaseFee(
                    requireContext(),
                    selectedFeeInfo,
                    txFee?.getAmount(0)?.denom
                )
                updateFeeView()
                txSimul()
            }

            memoView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    MemoFragment(txMemo, object : MemoListener {
                        override fun memo(memo: String) {
                            updateMemoView(memo)
                        }

                    }).show(
                        requireActivity().supportFragmentManager, MemoFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            btnSend.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    sendResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom,
                        R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private val sendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                if (selectedChain.chainId == selectedRecipientChain?.chainId) {
                    if (transferAssetType == TransferAssetType.COIN_TRANSFER) {
                        txViewModel.broadcastSend(
                            getChannel(selectedChain),
                            selectedChain.address,
                            onBindSend(),
                            txFee,
                            txMemo,
                            selectedChain
                        )

                    } else if (transferAssetType == TransferAssetType.CW20_TRANSFER) {
                        txViewModel.broadcastWasm(
                            getChannel(selectedChain),
                            selectedChain.address,
                            onBindWasmSend(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }

                } else {
                    if (transferAssetType == TransferAssetType.COIN_TRANSFER) {
                        txViewModel.broadcastIbcSend(
                            getChannel(selectedChain),
                            getRecipientChannel(),
                            existedAddress,
                            assetPath,
                            toSendDenom,
                            toSendAmount,
                            txFee,
                            txMemo,
                            selectedChain
                        )

                    } else if (transferAssetType == TransferAssetType.CW20_TRANSFER) {
                        txViewModel.broadcastWasm(
                            getChannel(selectedChain),
                            selectedChain.address,
                            onBindWasmIbcSend(),
                            txFee,
                            txMemo,
                            selectedChain
                        )
                    }
                }
            }
        }

    private fun txSimul() {
        binding.apply {
            if (toSendAmount.isEmpty() || recipientAddress.text.isEmpty()) { return }
            if (BigDecimal(toSendAmount) == BigDecimal.ZERO) { return }

            backdropLayout.visibility = View.VISIBLE

            if (selectedChain.chainId == selectedRecipientChain?.chainId) {
                if (transferAssetType == TransferAssetType.COIN_TRANSFER) {
                    txViewModel.simulateSend(
                        getChannel(selectedChain),
                        selectedChain.address,
                        onBindSend(),
                        txFee,
                        txMemo
                    )

                } else if (transferAssetType == TransferAssetType.CW20_TRANSFER) {
                    txViewModel.simulateWasm(
                        getChannel(selectedChain),
                        selectedChain.address,
                        onBindWasmSend(),
                        txFee,
                        txMemo
                    )
                }

            } else {
                selectedRecipientChain?.let { toChain ->
                    assetPath = assetPath(selectedChain, toChain, toSendDenom)
                    when (transferAssetType) {
                        TransferAssetType.COIN_TRANSFER -> {
                            txViewModel.simulateIbcSend(
                                getChannel(selectedChain),
                                getRecipientChannel(),
                                selectedChain.address,
                                existedAddress,
                                assetPath,
                                toSendDenom,
                                toSendAmount,
                                txFee,
                                txMemo
                            )
                        }

                        TransferAssetType.CW20_TRANSFER -> {
                            txViewModel.simulateWasm(
                                getChannel(selectedChain),
                                selectedChain.address,
                                onBindWasmIbcSend(),
                                txFee,
                                txMemo
                            )

                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasInfo ->
            isBroadCastTx(true)
            updateFeeViewWithSimul(gasInfo)
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
            return@observe
        }
    }

    private fun updateFeeViewWithSimul(gasInfo: AbciProto.GasInfo) {
        txFee?.let { fee ->
            feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == fee.getAmount(0).denom }
                ?.let { gasRate ->
                    val gasLimit =
                        (gasInfo.gasUsed.toDouble() * selectedChain.gasMultiply()).toLong()
                            .toBigDecimal()
                    val feeCoinAmount =
                        gasRate.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)

                    val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                        .setAmount(feeCoinAmount.toString()).build()
                    txFee =
                        TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin)
                            .build()
                }
        }
        updateFeeView()
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnSend.updateButtonView(isSuccess)
    }

    private fun setUpBroadcast() {
        txViewModel.broadcastTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                if (txResponse.code > 0) {
                    putExtra("isSuccess", false)
                } else {
                    putExtra("isSuccess", true)
                }
                putExtra("errorMsg", txResponse.rawLog)
                putExtra("selectedChain", selectedChain.tag)
                val hash = txResponse.txhash
                if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                startActivity(this)
            }
        }
    }

    private fun getRecipientChannel(): ManagedChannel? {
        selectedRecipientChain?.let {
            return ManagedChannelBuilder.forAddress(it.grpcHost, it.grpcPort).useTransportSecurity().build()
        } ?: run {
            return null
        }
    }

    private fun onBindSend(): MsgSend {
        val sendCoin =
            CoinProto.Coin.newBuilder().setAmount(toSendAmount).setDenom(toSendDenom).build()
        return MsgSend.newBuilder().setFromAddress(selectedChain.address)
            .setToAddress(existedAddress).addAmount(sendCoin).build()
    }

    private fun onBindWasmSend(): MutableList<MsgExecuteContract?>? {
        val result: MutableList<MsgExecuteContract?> = mutableListOf()
        val wasmSendReq = WasmSendReq(existedAddress, toSendAmount)
        val jsonData = Gson().toJson(wasmSendReq)
        val msg = ByteString.copyFromUtf8(jsonData)
        result.add(MsgExecuteContract.newBuilder().setSender(selectedChain.address).setContract(selectedToken?.address).setMsg(msg).build())
        return result
    }

    private fun onBindWasmIbcSend(): MutableList<MsgExecuteContract?> {
        val result: MutableList<MsgExecuteContract?> = mutableListOf()
        val wasmIbcReq = WasmIbcSendMsg(assetPath?.channel, existedAddress, 900)
        val msgData = Gson().toJson(wasmIbcReq).toByteArray(StandardCharsets.UTF_8)
        val encodeMsg = Base64.encodeToString(msgData, Base64.NO_WRAP)

        val wasmIbcSendReq = WasmIbcSendReq(assetPath?.ibcContract(), toSendAmount, encodeMsg)
        val jsonData = Gson().toJson(wasmIbcSendReq)
        val msg = ByteString.copyFromUtf8(jsonData)
        result.add(MsgExecuteContract.newBuilder().setSender(selectedChain.address).setContract(selectedToken?.address).setMsg(msg).build())
        return result
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class TransferAssetType { COIN_TRANSFER, CW20_TRANSFER, ERC20_TRANSFER }

fun assetPath(fromChain: CosmosLine, toChain: CosmosLine, denom: String): AssetPath? {
    val msAsset = BaseData.assets?.firstOrNull { it.denom?.lowercase() == denom.lowercase() }
    val msToken = fromChain.cw20tokens.firstOrNull { it.address == denom }
    var result: AssetPath? = null

    BaseData.assets?.forEach { asset ->
        if (msAsset != null) {
            if (asset.chain == fromChain.apiName &&
                asset.beforeChain(fromChain.apiName) == toChain.apiName &&
                asset.denom.equals(denom, true)
            ) {
                return AssetPath(asset.channel, asset.port)
            }
            if (asset.chain == toChain.apiName &&
                asset.beforeChain(toChain.apiName) == fromChain.apiName &&
                asset.counterParty?.denom?.equals(denom, true) == true
            ) {
                result = AssetPath(asset.counterParty.channel, asset.counterParty.port)
            }

        } else {
            if (msToken != null) {
                if (asset.chain == toChain.apiName &&
                    asset.beforeChain(toChain.apiName) == fromChain.apiName &&
                    asset.counterParty?.denom.equals(msToken.address, true)
                ) {
                    result = AssetPath(asset.counterParty?.channel, asset.counterParty?.port)
                }
            }
        }
    }
    return result
}