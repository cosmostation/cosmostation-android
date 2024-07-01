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
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.protobuf.ByteString
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EVM_BASE_FEE
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.makeToast
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
import wannabit.io.cosmostaion.databinding.FragmentCommonTransferBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.option.tx.address.AddressListener
import wannabit.io.cosmostaion.ui.option.tx.address.TransferAddressFragment
import wannabit.io.cosmostaion.ui.option.tx.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.AssetSelectListener
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

    private var recipientAbleChains: MutableList<CosmosLine> = mutableListOf()
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

            if (sendAssetType == SendAssetType.ONLY_EVM_COIN || sendAssetType == SendAssetType.ONLY_EVM_ERC20) {
                transferStyle = TransferStyle.WEB3_STYLE
                memoView.visibility = View.GONE
            }

            initFee()
            initToChain()
            initTransferStyle()

//            when (sendAssetType) {
//                SendAssetType.ONLY_EVM_COIN -> {
//                    availableAmount = if (EVM_BASE_FEE >= (fromChain as EthereumLine).evmBalance) {
//                        BigDecimal.ZERO
//                    } else {
//                        (fromChain as EthereumLine).evmBalance.subtract(
//                            EVM_BASE_FEE
//                        )
//                    }
//                    sendTitle.text = getString(
//                        R.string.title_asset_send, (fromChain as EthereumLine).coinSymbol
//                    )
//                }
//
//                SendAssetType.COSMOS_EVM_COIN -> {
//                    if (transferStyle == TransferStyle.WEB3_STYLE) {
//                        availableAmount =
//                            if (EVM_BASE_FEE >= (fromChain as EthereumLine).evmBalance) {
//                                BigDecimal.ZERO
//                            } else {
//                                (fromChain as EthereumLine).evmBalance.subtract(
//                                    EVM_BASE_FEE
//                                )
//                            }
//                        sendTitle.text = getString(
//                            R.string.title_asset_send, (fromChain as EthereumLine).coinSymbol
//                        )
//
//                    } else {
//                        toSendAsset = BaseData.getAsset(fromChain.apiName, toSendDenom)
//                        availableAmount = (fromChain as CosmosLine).balanceAmount(toSendDenom)
//                        if (cosmosTxFee?.getAmount(0)?.denom == toSendDenom) {
//                            cosmosTxFee?.getAmount(0)?.amount?.toBigDecimal()?.let { feeAmount ->
//                                availableAmount = if (feeAmount >= availableAmount) {
//                                    BigDecimal.ZERO
//                                } else {
//                                    availableAmount.subtract(feeAmount)
//                                }
//                            }
//                        }
//                        sendTitle.text = getString(
//                            R.string.title_asset_send, toSendAsset?.symbol
//                        )
//                    }
//                }
//
//                SendAssetType.ONLY_COSMOS_COIN -> {
//                    toSendAsset = BaseData.getAsset(fromChain.apiName, toSendDenom)
//                    availableAmount = (fromChain as CosmosLine).balanceAmount(toSendDenom)
//                    if (cosmosTxFee?.getAmount(0)?.denom == toSendDenom) {
//                        cosmosTxFee?.getAmount(0)?.amount?.toBigDecimal()?.let { feeAmount ->
//                            availableAmount = if (feeAmount >= availableAmount) {
//                                BigDecimal.ZERO
//                            } else {
//                                availableAmount.subtract(feeAmount)
//                            }
//                        }
//                    }
//                    sendTitle.text = getString(
//                        R.string.title_asset_send, toSendAsset?.symbol
//                    )
//                }
//
//                SendAssetType.ONLY_COSMOS_CW20, SendAssetType.ONLY_EVM_ERC20 -> {
//                    (fromChain as CosmosLine).tokens.firstOrNull { it.address == toSendDenom }
//                        ?.let { token ->
//                            toSendToken = token
//                        } ?: run {
//                        (fromChain as EthereumLine).evmTokens.firstOrNull { it.address == toSendDenom }
//                            ?.let { token ->
//                                toSendToken = token
//                            }
//                    }
//                    availableAmount = toSendToken?.amount?.toBigDecimal()
//                    sendTitle.text = getString(
//                        R.string.title_asset_send, toSendToken?.symbol
//                    )
//                }
//            }
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

                if (transferStyle == TransferStyle.WEB3_STYLE) {
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

                } else {
                    if (fromChain is EthereumLine) {
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

                    (fromChain as CosmosLine).apply {
                        feeSegment.visibility = View.VISIBLE
                        evmFeeSegment.visibility = View.GONE
                        cosmosFeeInfos = getFeeInfos(requireContext())
                        for (i in cosmosFeeInfos.indices) {
                            val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                            feeSegment.addView(
                                segmentView.root,
                                i,
                                LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                            )
                            segmentView.btnTitle.text = cosmosFeeInfos[i].title
                        }
                        feeSegment.setPosition(getFeeBasePosition(), false)
                        selectedFeePosition = getFeeBasePosition()
                        cosmosTxFee = getInitFee(requireContext())
                    }
                }
            }
            updateFeeView()
        }
    }

    private fun initData() {
        recipientAbleChains.add(fromChain as CosmosLine)
        if (sendAssetType == SendAssetType.ONLY_COSMOS_COIN || sendAssetType == SendAssetType.COSMOS_EVM_COIN || sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {
            BaseData.assets?.forEach { asset ->
                if (sendAssetType == SendAssetType.ONLY_COSMOS_COIN || sendAssetType == SendAssetType.COSMOS_EVM_COIN) {
                    if (asset.chain == fromChain.apiName && asset.denom?.lowercase() == toSendDenom.lowercase()) {
                        addRecipientChainIfNotExists(asset.beforeChain(fromChain.apiName))

                    } else if (asset.counter_party?.denom?.lowercase() == toSendDenom.lowercase()) {
                        addRecipientChainIfNotExists(asset.chain)
                    }

                } else {
                    if (asset.counter_party?.denom?.lowercase() == toSendDenom.lowercase()) {
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
            if (sendAssetType == SendAssetType.ONLY_EVM_COIN) {
                transferStyle = TransferStyle.WEB3_STYLE
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
//            if (sendAssetType == SendAssetType.COSMOS_EVM_COIN && transferStyle != this@CommonTransferFragment.transferStyle) {
//                updateAmountView("")
//                this@CommonTransferFragment.transferStyle = transferStyle
//
//                if (this@CommonTransferFragment.transferStyle == TransferStyle.WEB3_STYLE) {
//                    availableAmount = (fromChain as EthereumLine).evmBalance.subtract(
//                        EVM_BASE_FEE
//                    )
//                    memoView.visibility = View.GONE
//                    feeSegment.visibility = View.GONE
//                    evmFeeSegment.visibility = View.VISIBLE
//                    selectedFeePosition = 1
//                    evmFeeSegment.setPosition(selectedFeePosition, false)
//
//                } else {
//                    toSendAsset = BaseData.getAsset(fromChain.apiName, toSendDenom)
//                    availableAmount = (fromChain as CosmosLine).balanceAmount(toSendDenom)
//                    if (cosmosTxFee?.getAmount(0)?.denom == toSendDenom) {
//                        val feeAmount = cosmosTxFee?.getAmount(0)?.amount?.toBigDecimal()
//                        availableAmount = availableAmount.subtract(feeAmount)
//                    }
//                    memoView.visibility = View.VISIBLE
//                    feeSegment.visibility = View.VISIBLE
//                    evmFeeSegment.visibility = View.GONE
//                    selectedFeePosition = (fromChain as CosmosLine).getFeeBasePosition()
//                    feeSegment.setPosition(selectedFeePosition, false)
//                    cosmosTxFee = (fromChain as CosmosLine).getInitFee(requireContext())
//                }
//                updateFeeView()
//            }
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
                        val price = BaseData.getPrice((fromChain as EthereumLine).coinGeckoId)
                        val dpAmount = toAmount.toBigDecimal().amountHandlerLeft(18)
                        val value = price.multiply(dpAmount)
                        sendAmount.text = formatAmount(dpAmount.toPlainString(), 18)
                        sendDenom.text = (fromChain as EthereumLine).coinSymbol
                        sendValue.text = formatAssetValue(value)
                    }

                    SendAssetType.COSMOS_EVM_COIN -> {
                        var dpAmount = BigDecimal.ZERO
                        val price = BaseData.getPrice((fromChain as EthereumLine).coinGeckoId)
                        if (transferStyle == TransferStyle.WEB3_STYLE) {
                            dpAmount = toAmount.toBigDecimal().amountHandlerLeft(18)
                            sendAmount.text = formatAmount(dpAmount.toPlainString(), 18)
                            sendDenom.text = (fromChain as EthereumLine).coinSymbol

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
            if (transferStyle == TransferStyle.WEB3_STYLE) {
                if (fromChain is EthereumLine) {
                    (fromChain as EthereumLine).apply {
                        feeTokenImg.setImageResource(coinLogo)
                        feeToken.text = coinSymbol

                        if (evmFeeAmount == null) {
                            evmFeeAmount = evmGasPrices[selectedFeePosition].multiply(evmGasLimit)
                        }
                        val price = BaseData.getPrice((fromChain as EthereumLine).coinGeckoId)
                        val dpAmount = evmFeeAmount?.toBigDecimal()?.movePointLeft(18)
                            ?.setScale(18, RoundingMode.DOWN)
                        val value = price.multiply(dpAmount)

                        dpAmount?.let { amount ->
                            feeAmount.text = formatAmount(amount.toPlainString(), 18)
                            feeValue.text = formatAssetValue(value)
                        }
                    }

                } else {
//                    (fromChain as ChainOkt996Keccak).apply {
//                        stakeDenom?.let { denom ->
//                            feeTokenImg.setTokenImg((fromChain as ChainOkt996Keccak).assetImg(denom))
//                            feeToken.text = denom.uppercase()
//                        }
//                    }
//                    if (evmFeeAmount == null) {
//                        evmFeeAmount = evmGasPrices[selectedFeePosition].multiply(evmGasLimit)
//                    }
//                    val price = BaseData.getPrice(OKT_GECKO_ID)
//                    val dpAmount = evmFeeAmount?.toBigDecimal()?.movePointLeft(18)
//                        ?.setScale(18, RoundingMode.DOWN)
//                    val value = price.multiply(dpAmount)
//
//                    dpAmount?.let { amount ->
//                        feeAmount.text = formatAmount(amount.toPlainString(), 18)
//                        feeValue.text = formatAssetValue(value)
//                    }
                }

            } else {
                cosmosTxFee?.getAmount(0)?.let { fee ->
                    BaseData.getAsset(fromChain.apiName, fee.denom)?.let { asset ->
                        feeTokenImg.setTokenImg(asset)
                        feeToken.text = asset.symbol

                        val amount =
                            fee.amount.toBigDecimal().amountHandlerLeft(asset.decimals ?: 6)
                        val price = BaseData.getPrice(asset.coinGeckoId)
                        val value = price.multiply(amount)

                        feeAmount.text = formatAmount(amount.toPlainString(), asset.decimals ?: 6)
                        feeValue.text = formatAssetValue(value)
                    }
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            recipientChainView.setOnClickListener {
                handleOneClickWithDelay(
                    ChainFragment.newInstance(recipientAbleChains,
                        ChainListType.SELECT_TRANSFER,
                        object : ChainSelectListener {
                            override fun select(chainId: String) {
                                if (toChain.chainIdCosmos != chainId) {
                                    recipientAbleChains.firstOrNull { it.chainIdCosmos == chainId }
                                        ?.let { chain ->
                                            updateToChain(chain)
                                            updateRecipientAddressView("")
                                        }
                                }
                            }
                        })
                )
            }

            addressView.setOnClickListener {
                handleOneClickWithDelay(
                    TransferAddressFragment.newInstance(
                        fromChain,
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
                                            requireContext(),
                                            R.color.color_base01
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
                if (sendAssetType == SendAssetType.ONLY_COSMOS_COIN) {
                    if (cosmosFeeInfos.isEmpty()) {
                        activity?.makeToast(R.string.str_unknown_error)
                        return@setOnClickListener
                    }
                    handleOneClickWithDelay(
                        FeeAssetFragment.newInstance(fromChain,
                            cosmosFeeInfos[selectedFeePosition].feeDatas.toMutableList(),
                            sendAssetType,
                            object : AssetSelectListener {
                                override fun select(denom: String) {
                                    (fromChain as CosmosLine).apply {
                                        getDefaultFeeCoins(requireContext()).firstOrNull { it.denom == denom }
                                            ?.let { feeCoin ->
                                                val updateFeeCoin =
                                                    CoinProto.Coin.newBuilder().setDenom(denom)
                                                        .setAmount(feeCoin.amount).build()

                                                val updateTxFee =
                                                    TxProto.Fee.newBuilder().setGasLimit(
                                                        BaseConstant.BASE_GAS_AMOUNT.toLong()
                                                    ).addAmount(updateFeeCoin).build()

                                                cosmosTxFee = updateTxFee
                                                updateFeeView()
                                                txSimulate()
                                            }
                                    }
                                }
                            })
                    )
                }
            }

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeePosition = position
                if (transferStyle == TransferStyle.COSMOS_STYLE) {
                    (fromChain as CosmosLine).apply {
                        cosmosTxFee = getBaseFee(
                            requireContext(), selectedFeePosition, cosmosTxFee?.getAmount(0)?.denom
                        )
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

            if (transferStyle == TransferStyle.WEB3_STYLE) {
//                if (fromChain is ChainOkt996Keccak) {
//                    txViewModel.simulateEvmSend(
//                        ByteUtils.convertBech32ToEvm(toAddress),
//                        toSendAmount,
//                        toSendToken,
//                        sendAssetType,
//                        fromChain as ChainOkt996Keccak,
//                        selectedFeePosition
//                    )
//
//                } else {
//                    txViewModel.simulateEvmSend(
//                        toAddress,
//                        toSendAmount,
//                        toSendToken,
//                        sendAssetType,
//                        fromChain as EthereumLine,
//                        selectedFeePosition
//                    )
//                }

            } else {
                (fromChain as CosmosLine).apply {
                    if (!isGasSimulable()) {
                        if (chainIdCosmos != toChain.chainIdCosmos) {
                            assetPath = assetPath(
                                (fromChain as CosmosLine), (toChain as CosmosLine), toSendDenom
                            )
                        }
                        return updateFeeViewWithSimulate(null)
                    }
                    if (chainIdCosmos == toChain.chainIdCosmos) {
                        if (sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {
                            txViewModel.simulateWasm(
                                getChannel(this),
                                address,
                                onBindWasmSend(),
                                cosmosTxFee,
                                txMemo,
                                this
                            )

                        } else {
                            txViewModel.simulateSend(
                                getChannel(this), address, onBindSend(), cosmosTxFee, txMemo, this
                            )
                        }

                    } else {
                        assetPath = assetPath(
                            (fromChain as CosmosLine), (toChain as CosmosLine), toSendDenom
                        )
                        if (sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {
                            txViewModel.simulateWasm(
                                getChannel(this),
                                address,
                                onBindWasmIbcSend(),
                                cosmosTxFee,
                                txMemo,
                                this
                            )

                        } else {
                            txViewModel.simulateIbcSend(
                                getChannel(this),
                                getRecipientChannel(),
                                address,
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
            btnSend.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
        }
    }

    private fun updateFeeViewWithSimulate(gasInfo: AbciProto.GasInfo?) {
        if (transferStyle == TransferStyle.COSMOS_STYLE) {
            cosmosTxFee?.let { fee ->
                (fromChain as CosmosLine).apply {
                    val selectedFeeData = cosmosFeeInfos[selectedFeePosition].feeDatas.firstOrNull {
                        it.denom == fee.getAmount(
                            0
                        ).denom
                    }
                    val gasRate = selectedFeeData?.gasRate

                    gasInfo?.let { info ->
                        val gasLimit =
                            (info.gasUsed.toDouble() * gasMultiply()).toLong().toBigDecimal()
                        val feeCoinAmount =
                            gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)

                        val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                            .setAmount(feeCoinAmount.toString()).build()

                        cosmosTxFee =
                            TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong())
                                .addAmount(feeCoin)
                                .build()
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

    private fun onBindSend(): MsgSend {
        val sendCoin =
            CoinProto.Coin.newBuilder().setAmount(toSendAmount).setDenom(toSendDenom).build()
        return MsgSend.newBuilder().setFromAddress(fromChain.address).setToAddress(toAddress)
            .addAmount(sendCoin).build()
    }

    private fun onBindWasmSend(): MutableList<MsgExecuteContract?> {
        val result: MutableList<MsgExecuteContract?> = mutableListOf()
        val wasmSendReq = WasmSendReq(toAddress, toSendAmount)
        val jsonData = Gson().toJson(wasmSendReq)
        val msg = ByteString.copyFromUtf8(jsonData)
        result.add(
            MsgExecuteContract.newBuilder().setSender(fromChain.address)
                .setContract(toSendToken?.address).setMsg(msg).build()
        )
        return result
    }

    private fun onBindWasmIbcSend(): MutableList<MsgExecuteContract?> {
        val result: MutableList<MsgExecuteContract?> = mutableListOf()
        val wasmIbcReq = WasmIbcSendMsg(assetPath?.channel, toAddress, 900)
        val msgData = Gson().toJson(wasmIbcReq).toByteArray(StandardCharsets.UTF_8)
        val encodeMsg = Base64.encodeToString(msgData, Base64.NO_WRAP)

        val wasmIbcSendReq = WasmIbcSendReq(assetPath?.ibcContract(), toSendAmount, encodeMsg)
        val jsonData = Gson().toJson(wasmIbcSendReq)
        val msg = ByteString.copyFromUtf8(jsonData)
        result.add(
            MsgExecuteContract.newBuilder().setSender(fromChain.address)
                .setContract(toSendToken?.address).setMsg(msg).build()
        )
        return result
    }

    private val sendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
//                if (transferStyle == TransferStyle.WEB3_STYLE) {
//                    val web3j = if (fromChain is ChainOkt996Keccak) {
//                        Web3j.build(HttpService((fromChain as ChainOkt996Keccak).rpcUrl))
//                    } else {
//                        Web3j.build(HttpService((fromChain as EthereumLine).getEvmRpc()))
//                    }
//                    txViewModel.broadcastEvmSend(web3j, evmHexValue)
//
//                } else {
//                    (fromChain as CosmosLine).apply {
//                        if (chainIdCosmos == toChain.chainIdCosmos) {
//                            if (sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {
//                                txViewModel.broadcastWasm(
//                                    getChannel(this), onBindWasmSend(), cosmosTxFee, txMemo, this
//                                )
//
//                            } else {
//                                txViewModel.broadcastSend(
//                                    getChannel(this),
//                                    this.address,
//                                    onBindSend(),
//                                    cosmosTxFee,
//                                    txMemo,
//                                    this
//                                )
//                            }
//
//                        } else {
//                            if (sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {
//                                txViewModel.broadcastWasm(
//                                    getChannel(this), onBindWasmIbcSend(), cosmosTxFee, txMemo, this
//                                )
//
//                            } else {
//                                txViewModel.broadcastIbcSend(
//                                    getChannel(this),
//                                    getRecipientChannel(),
//                                    toAddress,
//                                    assetPath,
//                                    toSendDenom,
//                                    toSendAmount,
//                                    cosmosTxFee,
//                                    txMemo,
//                                    this
//                                )
//                            }
//                        }
//                    }
//                }
            }
        }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasInfo ->
            updateFeeViewWithSimulate(gasInfo)
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
        txViewModel.broadcastTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TransferTxResultActivity::class.java).apply {
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
//        allIbcChains().firstOrNull { it.apiName == apiName }?.let { sendAble ->
//            if (recipientAbleChains.none { it.apiName == sendAble.apiName }) {
//                recipientAbleChains.add(sendAble)
//            }
//        }
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

    private fun getRecipientChannel(): ManagedChannel? {
        return (toChain as CosmosLine).run {
            ManagedChannelBuilder.forAddress(getGrpc().first, getGrpc().second)
                .useTransportSecurity().build()
        }
    }

    private fun assetPath(fromChain: CosmosLine, toChain: CosmosLine, denom: String): AssetPath? {
        val msAsset = BaseData.assets?.firstOrNull { it.denom?.lowercase() == denom.lowercase() }
        val msToken = fromChain.tokens.firstOrNull { it.address == denom }

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

enum class SendAssetType { ONLY_EVM_COIN, COSMOS_EVM_COIN, ONLY_COSMOS_COIN, ONLY_COSMOS_CW20, ONLY_EVM_ERC20 }
enum class TransferStyle { COSMOS_STYLE, WEB3_STYLE }