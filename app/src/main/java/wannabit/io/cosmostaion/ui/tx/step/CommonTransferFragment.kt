package wannabit.io.cosmostaion.ui.tx.step

import android.app.Activity
import android.content.Intent
import android.os.Build
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
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.protobuf.Any
import com.google.protobuf.ByteString
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.ChainSui
import wannabit.io.cosmostaion.chain.EVM_BASE_FEE
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getdAmount
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.req.WasmIbcSendMsg
import wannabit.io.cosmostaion.data.model.req.WasmIbcSendReq
import wannabit.io.cosmostaion.data.model.req.WasmSendReq
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.AssetPath
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.databinding.FragmentCommonTransferBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.option.tx.address.AddressListener
import wannabit.io.cosmostaion.ui.option.tx.address.TransferAddressFragment
import wannabit.io.cosmostaion.ui.option.tx.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.AssetSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.BaseFeeAssetFragment
import wannabit.io.cosmostaion.ui.option.tx.general.BaseFeeAssetSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.ChainFragment
import wannabit.io.cosmostaion.ui.option.tx.general.ChainListType
import wannabit.io.cosmostaion.ui.option.tx.general.ChainSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.FeeAssetFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoListener
import wannabit.io.cosmostaion.ui.option.tx.general.TransferAmountFragment
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TransferTxResultActivity
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.nio.charset.StandardCharsets

class CommonTransferFragment : BaseTxFragment() {

    private var _binding: FragmentCommonTransferBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: BaseChain
    private lateinit var toSendDenom: String
    private lateinit var sendAssetType: SendAssetType

    private var recipientAbleChains: MutableList<BaseChain> = mutableListOf()
    private lateinit var toChain: BaseChain
    private var transferStyle = TransferStyle.COSMOS_STYLE
    private var toSendAsset: Asset? = null
    private var toSendToken: Token? = null
    private var assetPath: AssetPath? = null
    private var toAddress = ""
    private var toSendAmount = ""
    private var txMemo = ""

    private var selectedFeePosition = 0
    private var cosmosFeeInfos: MutableList<FeeInfo> = mutableListOf()
    private var cosmosTxFee: TxProto.Fee? = null

    private val evmGasPrices: List<BigInteger> = listOf(
        BigInteger.valueOf(1500000000),
        BigInteger.valueOf(1500000000),
        BigInteger.valueOf(1500000000)
    )
    private var evmFeeAmount: BigInteger? = null
    private val evmGasLimit = BigInteger.valueOf(21000)
    private var evmHexValue = ""

    private var suiFeeBudget = BigDecimal.ZERO
    private var suiGasPrice = BigDecimal.ZERO

    private var availableAmount = BigDecimal.ZERO

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: BaseChain, toSendDenom: String, sendAssetType: SendAssetType
        ): CommonTransferFragment {
            val args = Bundle().apply {
                putParcelable("fromChain", fromChain)
                putString("toSendDenom", toSendDenom)
                putSerializable("sendAssetType", sendAssetType)
            }
            val fragment = CommonTransferFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonTransferBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initData()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelable(
                        "fromChain", BaseChain::class.java
                    )?.let { fromChain = it }
                    getSerializable(
                        "sendAssetType", SendAssetType::class.java
                    )?.let { sendAssetType = it }

                } else {
                    (getParcelable("fromChain") as? BaseChain)?.let {
                        fromChain = it
                    }
                    (getSerializable("sendAssetType") as? SendAssetType)?.let {
                        sendAssetType = it
                    }
                }
                getString("toSendDenom")?.let { toSendDenom = it }
            }

            listOf(
                recipientChainView, addressView, sendAssetView, memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }
            chainImg.alpha = 0.2f
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            initTransferStyle()
            initFee()
            initToChain()

            when (sendAssetType) {
                SendAssetType.ONLY_EVM_COIN -> {
                    availableAmount = if (EVM_BASE_FEE >= fromChain.evmRpcFetcher?.evmBalance) {
                        BigDecimal.ZERO
                    } else {
                        fromChain.evmRpcFetcher?.evmBalance?.subtract(EVM_BASE_FEE)
                    }
                    transferImg.setImageResource(fromChain.coinLogo)
                    sendTitle.text = getString(
                        R.string.title_asset_send, fromChain.coinSymbol
                    )
                }

                SendAssetType.COSMOS_EVM_COIN -> {
                    if (transferStyle == TransferStyle.WEB3_STYLE) {
                        availableAmount = if (EVM_BASE_FEE >= fromChain.evmRpcFetcher?.evmBalance) {
                            BigDecimal.ZERO
                        } else {
                            fromChain.evmRpcFetcher?.evmBalance?.subtract(EVM_BASE_FEE)
                        }
                        transferImg.setImageResource(fromChain.coinLogo)
                        sendTitle.text = getString(
                            R.string.title_asset_send, fromChain.coinSymbol
                        )

                    } else {
                        toSendAsset = BaseData.getAsset(fromChain.apiName, toSendDenom)
                        availableAmount = fromChain.cosmosFetcher?.balanceAmount(toSendDenom)
                        if (cosmosTxFee?.getAmount(0)?.denom == toSendDenom) {
                            cosmosTxFee?.getAmount(0)?.amount?.toBigDecimal()?.let { feeAmount ->
                                val totalFeeAmount = feeAmount
                                availableAmount = if (totalFeeAmount >= availableAmount) {
                                    BigDecimal.ZERO
                                } else {
                                    availableAmount.subtract(totalFeeAmount)
                                }
                            }
                        }
                        transferImg.setTokenImg(toSendAsset?.assetImg() ?: "")
                        sendTitle.text = getString(
                            R.string.title_asset_send, toSendAsset?.symbol
                        )
                    }
                }

                SendAssetType.ONLY_COSMOS_COIN -> {
                    toSendAsset = BaseData.getAsset(fromChain.apiName, toSendDenom)
                    availableAmount = fromChain.cosmosFetcher?.balanceAmount(toSendDenom)

                    if (cosmosTxFee?.getAmount(0)?.denom == toSendDenom) {
                        cosmosTxFee?.getAmount(0)?.amount?.toBigDecimal()?.let { feeAmount ->
                            val totalFeeAmount = feeAmount
                            availableAmount = if (totalFeeAmount >= availableAmount) {
                                BigDecimal.ZERO
                            } else {
                                availableAmount.subtract(totalFeeAmount)
                            }
                        }
                    }
                    transferImg.setTokenImg(toSendAsset?.assetImg() ?: "")
                    sendTitle.text = getString(
                        R.string.title_asset_send, toSendAsset?.symbol
                    )
                }

                SendAssetType.ONLY_COSMOS_CW20, SendAssetType.ONLY_EVM_ERC20 -> {
                    fromChain.cosmosFetcher?.let { grpc ->
                        grpc.tokens.firstOrNull { it.address == toSendDenom }?.let { token ->
                            toSendToken = token
                        }
                    }

                    fromChain.evmRpcFetcher?.let { evmRpc ->
                        evmRpc.evmTokens.firstOrNull { it.address == toSendDenom }?.let { token ->
                            toSendToken = token
                        }
                    }
                    availableAmount = toSendToken?.amount?.toBigDecimal()
                    transferImg.setTokenImg(toSendToken?.assetImg() ?: "")
                    sendTitle.text = getString(
                        R.string.title_asset_send, toSendToken?.symbol
                    )
                }

                SendAssetType.SUI_COIN -> {
                    (fromChain as ChainSui).apply {
                        availableAmount = suiFetcher()?.suiBalanceAmount(toSendDenom)
                        if (fromChain.stakeDenom == toSendDenom) {
                            availableAmount = availableAmount.subtract(suiFeeBudget)
                        }
                        if (availableAmount <= BigDecimal.ZERO) {
                            availableAmount = BigDecimal.ZERO
                        }
                        transferImg.setTokenImg(fromChain.assetImg(toSendDenom))
                        sendTitle.text = getString(
                            R.string.title_asset_send, assetSymbol(toSendDenom)
                        )
                    }
                }

                else -> {}
            }
        }
    }

    private fun initFee() {
        binding.apply {
            if (isAdded) {
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
                evmFeeSegment.setSelectedBackground(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_accent_purple
                    )
                )
                evmFeeSegment.setRipple(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_accent_purple
                    )
                )

                when (transferStyle) {
                    TransferStyle.WEB3_STYLE -> {
                        feeSegment.visibility = View.GONE
                        evmFeeSegment.visibility = View.VISIBLE
                        val evmGasTitle = listOf(
                            getString(R.string.str_low),
                            getString(R.string.str_average),
                            getString(R.string.str_high)
                        )
                        for (i in evmGasTitle.indices) {
                            val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                            evmFeeSegment.addView(
                                segmentView.root,
                                i,
                                LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                            )
                            segmentView.btnTitle.text = evmGasTitle[i]
                        }
                        evmFeeSegment.setPosition(1, false)
                        selectedFeePosition = 1

                    }

                    TransferStyle.SUI_STYLE -> {
                        feeSegment.visibility = View.VISIBLE
                        evmFeeSegment.visibility = View.GONE
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

                        suiFeeBudget =
                            (fromChain as ChainSui).suiFetcher()
                                ?.suiBaseFee(SuiTxType.SUI_SEND_COIN)

                    }

                    else -> {
                        if (fromChain.supportEvm) {
                            val evmGasTitle = listOf(
                                getString(R.string.str_low),
                                getString(R.string.str_average),
                                getString(R.string.str_high)
                            )
                            for (i in evmGasTitle.indices) {
                                val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                                evmFeeSegment.addView(
                                    segmentView.root,
                                    i,
                                    LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                                )
                                segmentView.btnTitle.text = evmGasTitle[i]
                            }
                            evmFeeSegment.setPosition(1, false)
                        }

                        fromChain.apply {
                            feeSegment.visibility = View.VISIBLE
                            evmFeeSegment.visibility = View.GONE

                            if (fromChain.cosmosFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                                val tipTitle = listOf(
                                    "Default", "Fast", "Faster", "Instant"
                                )
                                for (i in tipTitle.indices) {
                                    val segmentView =
                                        ItemSegmentedFeeBinding.inflate(layoutInflater)
                                    feeSegment.addView(
                                        segmentView.root,
                                        i,
                                        LinearLayout.LayoutParams(
                                            0,
                                            dpToPx(requireContext(), 32),
                                            1f
                                        )
                                    )
                                    segmentView.btnTitle.text = tipTitle[i]
                                }
                                feeSegment.setPosition(selectedFeePosition, false)
                                val baseFee = fromChain.cosmosFetcher?.cosmosBaseFees?.get(0)
                                val gasAmount = fromChain.getFeeBaseGasAmount().toBigDecimal()
                                val feeDenom = baseFee?.denom
                                val feeAmount = baseFee?.getdAmount()?.multiply(gasAmount)
                                    ?.setScale(0, RoundingMode.DOWN)
                                cosmosTxFee =
                                    TxProto.Fee.newBuilder().setGasLimit(gasAmount.toLong())
                                        .addAmount(
                                            CoinProto.Coin.newBuilder().setDenom(feeDenom)
                                                .setAmount(feeAmount.toString()).build()
                                        ).build()

                            } else {
                                cosmosFeeInfos = fromChain.getFeeInfos(requireContext())
                                for (i in cosmosFeeInfos.indices) {
                                    val segmentView =
                                        ItemSegmentedFeeBinding.inflate(layoutInflater)
                                    feeSegment.addView(
                                        segmentView.root,
                                        i,
                                        LinearLayout.LayoutParams(
                                            0,
                                            dpToPx(requireContext(), 32),
                                            1f
                                        )
                                    )
                                    segmentView.btnTitle.text = cosmosFeeInfos[i].title
                                }
                                feeSegment.setPosition(fromChain.getFeeBasePosition(), false)
                                selectedFeePosition = fromChain.getFeeBasePosition()
                                cosmosTxFee = fromChain.getInitFee(requireContext())
                            }
                        }
                    }
                }
            }
            updateFeeView()
            if (!fromChain.supportCosmos()) {
                btnFee.visibility = View.GONE
            }
        }
    }

    private fun initData() {
        recipientAbleChains.add(fromChain)
        if (sendAssetType == SendAssetType.ONLY_COSMOS_COIN || sendAssetType == SendAssetType.COSMOS_EVM_COIN || sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {
            BaseData.assets?.forEach { asset ->
                if (sendAssetType == SendAssetType.ONLY_COSMOS_COIN || sendAssetType == SendAssetType.COSMOS_EVM_COIN) {
                    if (asset.chain == fromChain.apiName && asset.denom?.lowercase() == toSendDenom.lowercase()) {
                        addRecipientChainIfNotExists(asset.beforeChain(fromChain.apiName))

                    } else if (asset.justBeforeChain() == fromChain.apiName && asset.counter_party?.denom?.lowercase() == toSendDenom.lowercase()) {
                        addRecipientChainIfNotExists(asset.chain)
                    }

                } else {
                    if (asset.origin_chain == fromChain.apiName && asset.counter_party?.denom?.lowercase() == toSendDenom.lowercase()) {
                        addRecipientChainIfNotExists(asset.chain)
                    }
                }
            }
            recipientAbleChains.sortWith { o1, o2 ->
                when {
                    o1.name == fromChain.name -> -1
                    o2.name == fromChain.name -> 1
                    o1.name == "Cosmos" -> -1
                    o2.name == "Cosmos" -> 1
                    else -> 0
                }
            }

            binding.recipientChainView.setOnClickListener {
                handleOneClickWithDelay(
                    ChainFragment.newInstance(recipientAbleChains,
                        ChainListType.SELECT_TRANSFER,
                        object : ChainSelectListener {
                            override fun select(chainName: String) {
                                if (toChain.name != chainName) {
                                    recipientAbleChains.firstOrNull { it.name == chainName }
                                        ?.let { chain ->
                                            updateToChain(chain)
                                            updateRecipientAddressView("")
                                        }
                                }
                            }
                        })
                )
            }
        }
        updateToChain(recipientAbleChains[0])
    }

    private fun initToChain() {
        binding.apply {
            toChain = fromChain
            chainImg.setImageResource(toChain.logo)
            chainName.text = toChain.name.uppercase()
        }
    }

    private fun initTransferStyle() {
        binding.apply {
            if (sendAssetType == SendAssetType.ONLY_EVM_COIN || sendAssetType == SendAssetType.ONLY_EVM_ERC20) {
                transferStyle = TransferStyle.WEB3_STYLE
                memoView.visibility = View.GONE
            } else if (sendAssetType == SendAssetType.SUI_COIN) {
                transferStyle = TransferStyle.SUI_STYLE
                memoView.visibility = View.GONE
            }
        }
    }

    private fun updateToChain(chain: BaseChain) {
        binding.apply {
            if (chain.tag != toChain.tag) {
                toChain = chain
                chainImg.setImageResource(chain.logo)
                chainName.text = chain.name.uppercase()
                updateRecipientAddressView("")
            }

            if (sendAssetType == SendAssetType.COSMOS_EVM_COIN && fromChain.tag != toChain.tag) {
                updateTransferStyle(TransferStyle.COSMOS_STYLE)
            }
        }
    }

    private fun updateTransferStyle(transferStyle: TransferStyle) {
        binding.apply {
            if (sendAssetType == SendAssetType.COSMOS_EVM_COIN && transferStyle != this@CommonTransferFragment.transferStyle) {
                updateAmountView("")
                this@CommonTransferFragment.transferStyle = transferStyle

                if (this@CommonTransferFragment.transferStyle == TransferStyle.WEB3_STYLE) {
                    availableAmount = fromChain.evmRpcFetcher?.evmBalance?.subtract(EVM_BASE_FEE)
                    memoView.visibility = View.GONE
                    feeSegment.visibility = View.GONE
                    evmFeeSegment.visibility = View.VISIBLE
                    selectedFeePosition = 1
                    evmFeeSegment.setPosition(selectedFeePosition, false)

                } else {
                    toSendAsset = BaseData.getAsset(fromChain.apiName, toSendDenom)
                    availableAmount = fromChain.cosmosFetcher?.balanceAmount(toSendDenom)
                    if (cosmosTxFee?.getAmount(0)?.denom == toSendDenom) {
                        val feeAmount = cosmosTxFee?.getAmount(0)?.amount?.toBigDecimal()
                        availableAmount = availableAmount.subtract(feeAmount)
                    }
                    memoView.visibility = View.VISIBLE
                    feeSegment.visibility = View.VISIBLE
                    evmFeeSegment.visibility = View.GONE
                    selectedFeePosition = fromChain.getFeeBasePosition()
                    feeSegment.setPosition(selectedFeePosition, false)
                    cosmosTxFee = fromChain.getInitFee(requireContext())
                }
                updateFeeView()
            }
        }
    }

    private fun updateRecipientAddressView(address: String) {
        binding.apply {
            toAddress = address
            if (address.isEmpty()) {
                recipientAddressMsg.text = getString(R.string.str_tap_for_add_address_msg)
            } else {
                recipientAddress.text = address
            }
            recipientAddressMsg.visibleOrGone(address.isEmpty())
            recipientAddress.visibleOrGone(address.isNotEmpty())
            if (sendAssetType == SendAssetType.COSMOS_EVM_COIN) {
                if (toAddress.startsWith("0x")) {
                    updateTransferStyle(TransferStyle.WEB3_STYLE)
                } else {
                    updateTransferStyle(TransferStyle.COSMOS_STYLE)
                }
            }
        }
        txSimulate()
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            if (toSendAmount == toAmount) {
                return
            }

            if (toAmount.isEmpty()) {
                tabMsgTxt.visibility = View.VISIBLE
                sendAmount.text = ""
                sendValue.text = ""
                sendDenom.text = ""
                toSendAmount = toAmount
                isBroadCastTx(false)

            } else {
                tabMsgTxt.visibility = View.GONE
                toSendAmount = toAmount

                when (sendAssetType) {
                    SendAssetType.ONLY_EVM_COIN -> {
                        val price = BaseData.getPrice(fromChain.coinGeckoId)
                        val dpAmount = toAmount.toBigDecimal().amountHandlerLeft(18)
                        val value = price.multiply(dpAmount)
                        sendAmount.text = formatAmount(dpAmount.toPlainString(), 18)
                        sendDenom.text = fromChain.coinSymbol
                        sendValue.text = formatAssetValue(value)
                    }

                    SendAssetType.COSMOS_EVM_COIN -> {
                        var dpAmount = BigDecimal.ZERO
                        val price = BaseData.getPrice(fromChain.coinGeckoId)
                        if (transferStyle == TransferStyle.WEB3_STYLE) {
                            dpAmount = toAmount.toBigDecimal().amountHandlerLeft(18)
                            sendAmount.text = formatAmount(dpAmount.toPlainString(), 18)
                            sendDenom.text = fromChain.coinSymbol

                        } else {
                            toSendAsset?.let { asset ->
                                dpAmount =
                                    toAmount.toBigDecimal().amountHandlerLeft(asset.decimals ?: 6)
                                sendAmount.text =
                                    formatAmount(dpAmount.toPlainString(), asset.decimals ?: 6)
                                sendDenom.text = asset.symbol?.uppercase()
                                sendDenom.setTextColor(asset.assetColor())
                            }
                        }
                        val value = price.multiply(dpAmount)
                        sendValue.text = formatAssetValue(value)
                    }

                    SendAssetType.ONLY_COSMOS_COIN -> {
                        toSendAsset?.let { asset ->
                            val dpAmount =
                                toAmount.toBigDecimal().amountHandlerLeft(asset.decimals ?: 6)
                            val price = BaseData.getPrice(asset.coinGeckoId)
                            val value = price.multiply(dpAmount)
                            sendAmount.text =
                                formatAmount(dpAmount.toPlainString(), asset.decimals ?: 6)
                            sendDenom.text = asset.symbol?.uppercase()
                            sendDenom.setTextColor(asset.assetColor())
                            sendValue.text = formatAssetValue(value)
                        }
                    }

                    SendAssetType.ONLY_COSMOS_CW20, SendAssetType.ONLY_EVM_ERC20 -> {
                        toSendToken?.let { token ->
                            val price = BaseData.getPrice(token.coinGeckoId)
                            val dpAmount = toAmount.toBigDecimal().amountHandlerLeft(token.decimals)
                            val value = price.multiply(dpAmount)
                            sendAmount.text = formatAmount(dpAmount.toPlainString(), token.decimals)
                            sendDenom.text = token.symbol
                            sendValue.text = formatAssetValue(value)
                        }
                    }

                    SendAssetType.SUI_COIN -> {
                        (fromChain as ChainSui).apply {
                            val price = BaseData.getPrice(assetGeckoId(toSendDenom))
                            val dpAmount =
                                toAmount.toBigDecimal().amountHandlerLeft(assetDecimal(toSendDenom))
                            val value = price.multiply(dpAmount)
                            sendAmount.text =
                                formatAmount(dpAmount.toPlainString(), assetDecimal(toSendDenom))
                            sendDenom.text = assetSymbol(toSendDenom)
                            sendValue.text = formatAssetValue(value)
                        }
                    }

                     else -> {}
                }
                txSimulate()
            }
        }
    }

    private fun updateMemoView(memo: String) {
        binding.apply {
            txMemo = memo
            val message = txMemo.ifEmpty {
                getString(R.string.str_tap_for_add_memo_msg)
            }
            tabMemoMsg.text = message
            tabMemoMsg.setTextColor(
                ContextCompat.getColorStateList(
                    requireContext(),
                    if (txMemo.isEmpty()) R.color.color_base03 else R.color.color_base01
                )
            )
        }
        txSimulate()
    }

    private fun updateFeeView() {
        binding.apply {
            when (transferStyle) {
                TransferStyle.WEB3_STYLE -> {
                    fromChain.apply {
                        feeTokenImg.setImageResource(coinLogo)
                        feeToken.text = coinSymbol

                        if (evmFeeAmount == null) {
                            evmFeeAmount = evmGasPrices[selectedFeePosition].multiply(evmGasLimit)
                        }
                        val price = BaseData.getPrice(fromChain.coinGeckoId)
                        val dpAmount = evmFeeAmount?.toBigDecimal()?.movePointLeft(18)
                            ?.setScale(18, RoundingMode.DOWN)
                        val value = price.multiply(dpAmount)

                        dpAmount?.let { amount ->
                            feeAmount.text = formatAmount(amount.toPlainString(), 18)
                            feeValue.text = formatAssetValue(value)
                        }
                    }
                }

                TransferStyle.SUI_STYLE -> {
                    (fromChain as ChainSui).apply {
                        feeTokenImg.setImageResource(coinLogo)
                        feeToken.text = coinSymbol

                        val price = BaseData.getPrice(fromChain.coinGeckoId)
                        val dpBudget = suiFeeBudget.movePointLeft(9).setScale(9, RoundingMode.DOWN)
                        val value = price.multiply(dpBudget)

                        feeAmount.text = formatAmount(dpBudget.toPlainString(), 9)
                        feeValue.text = formatAssetValue(value)
                    }
                }

                else -> {
                    cosmosTxFee?.getAmount(0)?.let { fee ->
                        BaseData.getAsset(fromChain.apiName, fee.denom)?.let { asset ->
                            feeTokenImg.setTokenImg(asset)
                            feeToken.text = asset.symbol

                            val amount =
                                fee.amount.toBigDecimal().amountHandlerLeft(asset.decimals ?: 6)
                            val price = BaseData.getPrice(asset.coinGeckoId)
                            val value = price.multiply(amount)

                            feeAmount.text =
                                formatAmount(amount.toPlainString(), asset.decimals ?: 6)
                            feeValue.text = formatAssetValue(value)
                        }
                    }
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            addressView.setOnClickListener {
                handleOneClickWithDelay(
                    TransferAddressFragment.newInstance(fromChain,
                        toChain,
                        toAddress,
                        sendAssetType,
                        object : AddressListener {
                            override fun selectAddress(address: String, memo: String) {
                                if (memo.isNotEmpty()) {
                                    txMemo = memo
                                    tabMemoMsg.text = txMemo
                                    tabMemoMsg.setTextColor(
                                        ContextCompat.getColorStateList(
                                            requireContext(), R.color.color_base01
                                        )
                                    )
                                }
                                updateRecipientAddressView(address)
                            }
                        })
                )
            }

            sendAssetView.setOnClickListener {
                handleOneClickWithDelay(
                    TransferAmountFragment.newInstance(fromChain,
                        toSendDenom,
                        toSendAsset,
                        toSendToken,
                        availableAmount.toString(),
                        toSendAmount,
                        sendAssetType,
                        transferStyle,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }
                        })
                )
            }

            feeTokenLayout.setOnClickListener {
                cosmosTxFee?.let { fee ->
                    if (transferStyle == TransferStyle.COSMOS_STYLE) {
                        if (fromChain.cosmosFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                            handleOneClickWithDelay(
                                BaseFeeAssetFragment(fromChain,
                                    fromChain.cosmosFetcher?.cosmosBaseFees,
                                    object : BaseFeeAssetSelectListener {
                                        override fun select(denom: String) {
                                            fromChain.cosmosFetcher?.cosmosBaseFees?.firstOrNull { it.denom == denom }
                                                ?.let { baseFee ->
                                                    val feeAmount = baseFee.getdAmount()
                                                        .multiply(fee.gasLimit.toBigDecimal())
                                                        ?.setScale(0, RoundingMode.DOWN)
                                                    val updateFeeCoin =
                                                        CoinProto.Coin.newBuilder().setDenom(denom)
                                                            .setAmount(feeAmount.toString()).build()
                                                    cosmosTxFee = TxProto.Fee.newBuilder()
                                                        .setGasLimit(fee.gasLimit)
                                                        .addAmount(updateFeeCoin).build()
                                                    cosmosTxFee = Signer.setFee(
                                                        selectedFeePosition, cosmosTxFee
                                                    )

                                                    updateFeeView()
                                                    txSimulate()
                                                }
                                        }
                                    })
                            )

                        } else {
                            handleOneClickWithDelay(
                                FeeAssetFragment.newInstance(fromChain,
                                    cosmosFeeInfos[selectedFeePosition].feeDatas.toMutableList(),
                                    sendAssetType,
                                    object : AssetSelectListener {
                                        override fun select(denom: String) {
                                            fromChain.apply {
                                                cosmosFeeInfos[selectedFeePosition].feeDatas.firstOrNull { it.denom == denom }
                                                    ?.let { feeCoin ->
                                                        val gasAmount =
                                                            getFeeBaseGasAmount().toBigDecimal()
                                                        val updateFeeCoin =
                                                            CoinProto.Coin.newBuilder()
                                                                .setDenom(denom).setAmount(
                                                                    feeCoin.gasRate?.multiply(
                                                                        gasAmount
                                                                    )?.setScale(0, RoundingMode.UP)
                                                                        .toString()
                                                                ).build()

                                                        cosmosTxFee =
                                                            TxProto.Fee.newBuilder().setGasLimit(
                                                                getFeeBaseGasAmount()
                                                            ).addAmount(updateFeeCoin).build()

                                                        updateFeeView()
                                                        txSimulate()
                                                    }
                                            }
                                        }
                                    })
                            )
                        }
                    }
                }
            }

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeePosition = position
                if (transferStyle == TransferStyle.COSMOS_STYLE) {
                    fromChain.apply {
                        cosmosTxFee =
                            if (fromChain.cosmosFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                                val baseFee = fromChain.cosmosFetcher?.cosmosBaseFees?.firstOrNull {
                                    it.denom == cosmosTxFee?.getAmount(0)?.denom
                                }
                                val gasAmount = cosmosTxFee?.gasLimit?.toBigDecimal()
                                val feeDenom = baseFee?.denom
                                val feeAmount = baseFee?.getdAmount()?.multiply(gasAmount)
                                    ?.setScale(0, RoundingMode.DOWN)
                                cosmosTxFee =
                                    TxProto.Fee.newBuilder().setGasLimit(gasAmount!!.toLong())
                                        .addAmount(
                                            CoinProto.Coin.newBuilder().setDenom(feeDenom)
                                                .setAmount(feeAmount.toString()).build()
                                        ).build()
                                Signer.setFee(selectedFeePosition, cosmosTxFee)

                            } else {
                                fromChain.getBaseFee(
                                    requireContext(),
                                    selectedFeePosition,
                                    cosmosTxFee?.getAmount(0)?.denom
                                )
                            }
                    }
                }
                updateFeeView()
                txSimulate()
            }

            evmFeeSegment.setOnPositionChangedListener { position ->
                selectedFeePosition = position
                updateFeeView()
                txSimulate()
            }

            memoView.setOnClickListener {
                handleOneClickWithDelay(
                    MemoFragment.newInstance(txMemo, object : MemoListener {
                        override fun memo(memo: String) {
                            updateMemoView(memo)
                        }
                    })
                )
            }

            btnSend.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    sendResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private fun txSimulate() {
        binding.apply {
            if (toSendAmount.isEmpty() || toAddress.isEmpty()) {
                return
            }
            if (toSendAmount.toBigDecimal() <= BigDecimal.ZERO) {
                return
            }

            when (transferStyle) {
                TransferStyle.WEB3_STYLE -> {
                    txViewModel.simulateEvmSend(
                        toAddress,
                        toSendAmount,
                        toSendToken,
                        sendAssetType,
                        fromChain,
                        selectedFeePosition
                    )

                }

                TransferStyle.SUI_STYLE -> {
                    (fromChain as ChainSui).apply {
                        suiFetcher?.let { fetcher ->
                            txViewModel.suiSimulate(
                                fetcher,
                                toSendDenom,
                                mainAddress,
                                suiInputs(),
                                mutableListOf(toAddress),
                                mutableListOf(
                                    toSendAmount
                                ),
                                suiFeeBudget.toString()
                            )
                        }
                    }

                }

                else -> {
                    fromChain.apply {
                        if (!isGasSimulable()) {
                            if (chainIdCosmos != toChain.chainIdCosmos) {
                                assetPath = assetPath(
                                    fromChain, toChain, toSendDenom
                                )
                            }
                            return updateFeeViewWithSimulate(null)
                        }
                        if (chainIdCosmos == toChain.chainIdCosmos) {
                            if (sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {
                                txViewModel.simulate(
                                    fromChain.cosmosFetcher?.getChannel(),
                                    onBindWasmSendMsg(),
                                    cosmosTxFee,
                                    txMemo,
                                    this
                                )

                            } else {
                                txViewModel.simulate(
                                    fromChain.cosmosFetcher?.getChannel(),
                                    onBindSendMsg(),
                                    cosmosTxFee,
                                    txMemo,
                                    this
                                )
                            }

                        } else {
                            assetPath = assetPath(
                                fromChain, toChain, toSendDenom
                            )
                            if (sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {
                                txViewModel.simulate(
                                    fromChain.cosmosFetcher?.getChannel(),
                                    onBindWasmIbcSendMsg(),
                                    cosmosTxFee,
                                    txMemo,
                                    this
                                )

                            } else {
                                txViewModel.simulateIbcSend(
                                    cosmosFetcher?.getChannel(),
                                    toChain,
                                    toAddress,
                                    assetPath,
                                    toSendDenom,
                                    toSendAmount,
                                    cosmosTxFee,
                                    txMemo,
                                    this
                                )
                            }
                        }
                    }
                }
            }
            btnSend.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
        }
    }

    private fun updateFeeViewWithSimulate(gasUsed: String?) {
        if (transferStyle == TransferStyle.SUI_STYLE) {
            suiFeeBudget = gasUsed?.toBigDecimal()

        } else if (transferStyle == TransferStyle.COSMOS_STYLE) {
            cosmosTxFee?.let { fee ->
                fromChain.apply {
                    gasUsed?.toLong()?.let { gas ->
                        val gasLimit = (gas.toDouble() * gasMultiply()).toLong().toBigDecimal()
                        if (fromChain.cosmosFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                            fromChain.cosmosFetcher?.cosmosBaseFees?.firstOrNull {
                                it.denom == fee.getAmount(
                                    0
                                ).denom
                            }?.let { baseFee ->
                                val feeCoinAmount = baseFee.getdAmount().multiply(gasLimit)
                                    .setScale(0, RoundingMode.UP)
                                val feeCoin =
                                    CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                                        .setAmount(feeCoinAmount.toString()).build()
                                cosmosTxFee =
                                    TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong())
                                        .addAmount(feeCoin).build()
                                cosmosTxFee = Signer.setFee(selectedFeePosition, cosmosTxFee)
                            }

                        } else {
                            val selectedFeeData =
                                cosmosFeeInfos[selectedFeePosition].feeDatas.firstOrNull {
                                    it.denom == fee.getAmount(
                                        0
                                    ).denom
                                }
                            val gasRate = selectedFeeData?.gasRate
                            val feeCoinAmount =
                                gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)
                            val feeCoin =
                                CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                                    .setAmount(feeCoinAmount.toString()).build()

                            cosmosTxFee = TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong())
                                .addAmount(feeCoin).build()
                        }
                    }
                }
            }
        }
        updateFeeView()
        isBroadCastTx(true)
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.apply {
            backdropLayout.visibility = View.GONE
            btnSend.updateButtonView(isSuccess)
        }
    }

    private fun suiInputs(): MutableList<String> {
        val result: MutableList<String> = mutableListOf()
        (fromChain as ChainSui).suiFetcher()?.let { fetcher ->
            fetcher.suiObjects.forEach { suiObject ->
                if (suiObject["data"].asJsonObject["type"].asString.contains(toSendDenom)) {
                    result.add(suiObject["data"].asJsonObject["objectId"].asString)
                }
            }
        }
        return result
    }

    private fun onBindSendMsg(): MutableList<Any> {
        val sendCoin =
            CoinProto.Coin.newBuilder().setAmount(toSendAmount).setDenom(toSendDenom).build()
        val msgSend = MsgSend.newBuilder().setFromAddress(fromChain.address).setToAddress(toAddress)
            .addAmount(sendCoin).build()
        return Signer.sendMsg(msgSend)
    }

    private fun onBindWasmSendMsg(): MutableList<Any> {
        val wasmMsgs = mutableListOf<MsgExecuteContract?>()
        val wasmSendReq = WasmSendReq(toAddress, toSendAmount)
        val jsonData = Gson().toJson(wasmSendReq)
        val msg = ByteString.copyFromUtf8(jsonData)
        wasmMsgs.add(
            MsgExecuteContract.newBuilder().setSender(fromChain.address)
                .setContract(toSendToken?.address).setMsg(msg).build()
        )
        return Signer.wasmMsg(wasmMsgs)
    }

    private fun onBindWasmIbcSendMsg(): MutableList<Any> {
        val wasmMsgs = mutableListOf<MsgExecuteContract?>()
        val wasmIbcReq = WasmIbcSendMsg(assetPath?.channel, toAddress, 900)
        val msgData = Gson().toJson(wasmIbcReq).toByteArray(StandardCharsets.UTF_8)
        val encodeMsg = Base64.encodeToString(msgData, Base64.NO_WRAP)

        val wasmIbcSendReq = WasmIbcSendReq(assetPath?.ibcContract(), toSendAmount, encodeMsg)
        val jsonData = Gson().toJson(wasmIbcSendReq)
        val msg = ByteString.copyFromUtf8(jsonData)
        wasmMsgs.add(
            MsgExecuteContract.newBuilder().setSender(fromChain.address)
                .setContract(toSendToken?.address).setMsg(msg).build()
        )
        return Signer.wasmMsg(wasmMsgs)
    }

    private val sendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                when (transferStyle) {
                    TransferStyle.WEB3_STYLE -> {
                        val web3j = Web3j.build(
                            HttpService(
                                fromChain.evmRpcFetcher?.getEvmRpc() ?: fromChain.evmRpcURL
                            )
                        )
                        txViewModel.broadcastEvmSend(web3j, evmHexValue)

                    }

                    TransferStyle.SUI_STYLE -> {
                        (fromChain as ChainSui).apply {
                            suiFetcher?.let { fetcher ->
                                txViewModel.suiBroadcast(
                                    fetcher,
                                    toSendDenom,
                                    mainAddress,
                                    suiInputs(),
                                    mutableListOf(toAddress),
                                    mutableListOf(
                                        toSendAmount
                                    ),
                                    suiFeeBudget.toString(),
                                    this
                                )
                            }
                        }
                    }

                    else -> {
                        fromChain.apply {
                            if (chainIdCosmos == toChain.chainIdCosmos) {
                                if (sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {
                                    txViewModel.broadcast(
                                        cosmosFetcher?.getChannel(),
                                        onBindWasmSendMsg(),
                                        cosmosTxFee,
                                        txMemo,
                                        this
                                    )

                                } else {
                                    txViewModel.broadcast(
                                        cosmosFetcher?.getChannel(),
                                        onBindSendMsg(),
                                        cosmosTxFee,
                                        txMemo,
                                        this
                                    )
                                }

                            } else {
                                if (sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {
                                    txViewModel.broadcast(
                                        cosmosFetcher?.getChannel(),
                                        onBindWasmIbcSendMsg(),
                                        cosmosTxFee,
                                        txMemo,
                                        this
                                    )

                                } else {
                                    txViewModel.broadcastIbcSend(
                                        cosmosFetcher?.getChannel(),
                                        toChain,
                                        toAddress,
                                        assetPath,
                                        toSendDenom,
                                        toSendAmount,
                                        cosmosTxFee,
                                        txMemo,
                                        this
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasUsed ->
            updateFeeViewWithSimulate(gasUsed)
        }

        txViewModel.simulateEvmSend.observe(viewLifecycleOwner) { response ->
            response.first?.let { hexValue ->
                evmHexValue = hexValue
                response.second?.let { evmFeeAmount ->
                    this.evmFeeAmount = evmFeeAmount.toBigInteger()
                    updateFeeViewWithSimulate(null)
                }
            }
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
            return@observe
        }

        txViewModel.erc20ErrorMessage.observe(viewLifecycleOwner) {
            isBroadCastTx(false)
            requireContext().makeToast(R.string.error_evm_simul)
            return@observe
        }
    }

    private fun setUpBroadcast() {
        txViewModel.suiBroadcast.observe(viewLifecycleOwner) { response ->
            val status =
                response["result"].asJsonObject["effects"].asJsonObject["status"].asJsonObject["status"].asString
            Intent(requireContext(), TransferTxResultActivity::class.java).apply {
                if (status != "success") {
                    putExtra("isSuccess", false)
                } else {
                    putExtra("isSuccess", true)
                }
                putExtra("txHash", response["result"].asJsonObject["digest"].asString)
                putExtra("fromChainTag", fromChain.tag)
                putExtra("toChainTag", toChain.tag)
                putExtra("recipientAddress", toAddress)
                putExtra("transferStyle", transferStyle.ordinal)
                putExtra("suiResult", response.toString())
                startActivity(this)
            }
            dismiss()
        }

        txViewModel.broadcast.observe(viewLifecycleOwner) { response ->
            Intent(requireContext(), TransferTxResultActivity::class.java).apply {
                response?.let { txResponse ->
                    if (txResponse.code > 0) {
                        putExtra("isSuccess", false)
                    } else {
                        putExtra("isSuccess", true)
                    }
                    putExtra("errorMsg", txResponse.rawLog)
                    putExtra("fromChainTag", fromChain.tag)
                    putExtra("toChainTag", toChain.tag)
                    putExtra("recipientAddress", toAddress)
                    putExtra("memo", txMemo)
                    putExtra("transferStyle", transferStyle.ordinal)
                    val hash = txResponse.txhash
                    if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                    startActivity(this)
                }
            }
            dismiss()
        }

        txViewModel.broadcastEvmSendTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TransferTxResultActivity::class.java).apply {
                if (txResponse?.isNotEmpty() == true) {
                    putExtra("isSuccess", true)
                    putExtra("txHash", txResponse)
                } else {
                    putExtra("isSuccess", false)
                    putExtra("errorMsg", txResponse)
                }
                putExtra("fromChainTag", fromChain.tag)
                putExtra("toChainTag", toChain.tag)
                putExtra("transferStyle", transferStyle.ordinal)
                startActivity(this)
            }
            dismiss()
        }
    }

    private fun addRecipientChainIfNotExists(apiName: String?) {
        allChains().filter { !it.isTestnet && it.supportCosmos() }
            .firstOrNull { it.apiName == apiName }?.let { sendAble ->
                if (recipientAbleChains.none { it.apiName == sendAble.apiName }) {
                    recipientAbleChains.add(sendAble)
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

    private fun assetPath(fromChain: BaseChain, toChain: BaseChain, denom: String): AssetPath? {
        val msAsset = BaseData.assets?.firstOrNull { it.denom?.lowercase() == denom.lowercase() }
        val msToken = fromChain.cosmosFetcher?.tokens?.firstOrNull { it.address == denom }

        BaseData.assets?.forEach { asset ->
            if (msAsset != null) {
                if (asset.chain == fromChain.apiName && asset.beforeChain(fromChain.apiName) == toChain.apiName && asset.denom.equals(
                        denom, true
                    )
                ) {
                    return AssetPath(asset.channel, asset.port)
                }
                if (asset.chain == toChain.apiName && asset.beforeChain(toChain.apiName) == fromChain.apiName && asset.counter_party?.denom?.equals(
                        denom, true
                    ) == true
                ) {
                    return AssetPath(asset.counter_party.channel, asset.counter_party.port)
                }
            } else {
                if (msToken != null && asset.chain == toChain.apiName && asset.beforeChain(toChain.apiName) == fromChain.apiName && asset.counter_party?.denom.equals(
                        msToken.address, true
                    )
                ) {
                    return AssetPath(asset.counter_party?.channel, asset.counter_party?.port)
                }
            }
        }
        return null
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class SendAssetType { ONLY_EVM_COIN, COSMOS_EVM_COIN, ONLY_COSMOS_COIN, ONLY_COSMOS_CW20, ONLY_EVM_ERC20, SUI_COIN, SUI_NFT }
enum class TransferStyle { COSMOS_STYLE, WEB3_STYLE, SUI_STYLE }
enum class SuiTxType { SUI_SEND_COIN, SUI_SEND_NFT, SUI_STAKE, SUI_UNSTAKE }