package wannabit.io.cosmostaion.ui.tx.step

import android.app.Activity
import android.content.Intent
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
import com.cosmos.bank.v1beta1.TxProto.MsgSend
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.allIbcChains
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.RefAddress
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
import java.math.RoundingMode

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
    private var toAddress = ""
    private var toSendAmount = ""
    private var txMemo = ""

    private var selectedFeePosition = 0
    private var cosmosFeeInfos: MutableList<FeeInfo> = mutableListOf()
    private var cosmosTxFee: TxProto.Fee? = null

    private var availableAmount = BigDecimal.ZERO

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: BaseChain, toSendDenom: String, sendAssetType: SendAssetType
        ): CommonTransferFragment {
            val args = Bundle().apply {
                putSerializable("fromChain", fromChain)
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
                    getSerializable(
                        "fromChain", BaseChain::class.java
                    )?.let { fromChain = it }
                    getSerializable(
                        "sendAssetType", SendAssetType::class.java
                    )?.let { sendAssetType = it }

                } else {
                    (getSerializable("fromChain") as? BaseChain)?.let {
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

            if (sendAssetType == SendAssetType.ONLY_EVM_COIN) {
                transferStyle = TransferStyle.WEB3_STYLE
                memoView.visibility = View.GONE
            }

            initFee()

            when (sendAssetType) {
                SendAssetType.COSMOS_EVM_COIN -> {
                    if (transferStyle == TransferStyle.WEB3_STYLE) {

                    } else {
                        toSendAsset = BaseData.getAsset(fromChain.apiName, toSendDenom)
                        availableAmount = (fromChain as CosmosLine).balanceAmount(toSendDenom)
                        if (cosmosTxFee?.getAmount(0)?.denom == toSendDenom) {
                            val feeAmount = cosmosTxFee?.getAmount(0)?.amount?.toBigDecimal()
                            availableAmount = availableAmount.subtract(feeAmount)
                        }
                        sendTitle.text = getString(
                            R.string.title_asset_send, toSendAsset?.symbol
                        )
                    }
                }

                SendAssetType.ONLY_COSMOS_COIN -> {
                    toSendAsset = BaseData.getAsset(fromChain.apiName, toSendDenom)
                    availableAmount = (fromChain as CosmosLine).balanceAmount(toSendDenom)
                    if (cosmosTxFee?.getAmount(0)?.denom == toSendDenom) {
                        val feeAmount = cosmosTxFee?.getAmount(0)?.amount?.toBigDecimal()
                        availableAmount = availableAmount.subtract(feeAmount)
                    }
                    sendTitle.text = getString(
                        R.string.title_asset_send, toSendAsset?.symbol
                    )
                }

                else -> {
                    sendTitle.text = getString(R.string.title_coin_send)
                }
            }
        }
    }

    private fun initFee() {
        binding.apply {
            feeSegment.apply {
                setSelectedBackground(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_accent_purple
                    )
                )
                setRipple(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_accent_purple
                    )
                )
            }

            if (transferStyle == TransferStyle.WEB3_STYLE) {

            } else {
                (fromChain as CosmosLine).apply {
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

                    updateFeeView()
                }
            }
        }
    }

    private fun initData() {
        recipientAbleChains.add(fromChain as CosmosLine)
        if (sendAssetType == SendAssetType.ONLY_COSMOS_COIN || sendAssetType == SendAssetType.COSMOS_EVM_COIN || sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {
            BaseData.assets?.forEach { asset ->
                if (sendAssetType == SendAssetType.ONLY_COSMOS_COIN || sendAssetType == SendAssetType.COSMOS_EVM_COIN) {
                    if (asset.chain == fromChain.apiName && asset.denom?.lowercase() == toSendDenom.lowercase()) {
                        addRecipientChainIfNotExists(asset.beforeChain(fromChain.apiName))

                    } else if (asset.counterParty?.denom?.lowercase() == toSendDenom.lowercase()) {
                        addRecipientChainIfNotExists(asset.chain)
                    }

                } else {
                    if (asset.counterParty?.denom?.lowercase() == toSendDenom.lowercase()) {
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

    private fun updateToChain(chain: BaseChain) {
        binding.apply {
            toChain = chain
            chainImg.setImageResource(chain.logo)
            chainName.text = chain.name.uppercase()
        }
    }

    private fun updateTransferStyle() {

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
        }
        txSimulate()
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            tabMsgTxt.visibility = View.GONE
            toSendAmount = toAmount

            when (sendAssetType) {
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

                else -> {}
            }
        }
        txSimulate()
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

            } else {
                cosmosTxFee?.getAmount(0)?.let { fee ->
                    BaseData.getAsset(fromChain.apiName, fee.denom)?.let { asset ->
                        feeTokenImg.setTokenImg(asset)
                        feeToken.text = asset.symbol
                        feeDenom.text = asset.symbol

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
                                if (toChain.chainId != chainId) {
                                    recipientAbleChains.firstOrNull { it.chainId == chainId }
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
                            override fun selectAddress(
                                refAddress: RefAddress?,
                                addressBook: AddressBook?,
                                addressTxt: String
                            ) {
                                refAddress?.dpAddress?.let {
                                    updateRecipientAddressView(it)
                                    updateMemoView("")

                                } ?: run {
                                    addressBook?.let {
                                        updateRecipientAddressView(it.address)
                                        updateMemoView(it.memo)

                                    } ?: run {
                                        updateRecipientAddressView(addressTxt)
                                        updateMemoView("")
                                    }
                                }
                            }
                        })
                )
            }

            sendAssetView.setOnClickListener {
                handleOneClickWithDelay(
                    TransferAmountFragment.newInstance(fromChain,
                        toSendAsset,
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
                handleOneClickWithDelay(
                    FeeAssetFragment.newInstance(fromChain,
                        cosmosFeeInfos[selectedFeePosition].feeDatas.toMutableList(),
                        sendAssetType,
                        object : AssetSelectListener {
                            override fun select(denom: String) {
                                if (sendAssetType == SendAssetType.ONLY_COSMOS_COIN) {
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
                            }
                        })
                )
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

            } else {
                (fromChain as CosmosLine).apply {
                    if (!isGasSimulable()) {
                        return updateFeeViewWithSimulate(null)
                    }
                    if (chainId == toChain.chainId) {
                        if (sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {

                        } else {
                            txViewModel.simulateSend(
                                getChannel(this),
                                address,
                                onBindSend(),
                                cosmosTxFee,
                                txMemo,
                                fromChain as CosmosLine
                            )
                        }

                    } else {

                    }
                }
            }
            btnSend.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
        }
    }

    private fun updateFeeViewWithSimulate(gasInfo: AbciProto.GasInfo?) {
        cosmosTxFee?.let { fee ->
            (fromChain as CosmosLine).apply {
                val selectedFeeData = cosmosFeeInfos[selectedFeePosition].feeDatas.firstOrNull {
                    it.denom == fee.getAmount(
                        0
                    ).denom
                }
                val gasRate = selectedFeeData?.gasRate

                gasInfo?.let { info ->
                    val gasLimit = (info.gasUsed.toDouble() * gasMultiply()).toLong().toBigDecimal()
                    val feeCoinAmount = gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)

                    val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                        .setAmount(feeCoinAmount.toString()).build()

                    cosmosTxFee =
                        TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin)
                            .build()
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

    private val sendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                (fromChain as CosmosLine).apply {
                    if (chainId == toChain.chainId) {
                        if (sendAssetType == SendAssetType.ONLY_COSMOS_CW20) {

                        } else {
                            txViewModel.broadcastSend(
                                getChannel(this),
                                this.address,
                                onBindSend(),
                                cosmosTxFee,
                                txMemo,
                                this
                            )
                        }

                    } else {

                    }
                }
            }
        }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasInfo ->
            updateFeeViewWithSimulate(gasInfo)
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
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
                val hash = txResponse.txhash
                if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                startActivity(this)
            }
            dismiss()
        }
    }

    private fun addRecipientChainIfNotExists(apiName: String?) {
        allIbcChains().firstOrNull { it.apiName == apiName }?.let { sendAble ->
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class SendAssetType { ONLY_COSMOS_COIN, ONLY_COSMOS_CW20, ONLY_EVM_COIN, COSMOS_EVM_COIN }
enum class TransferStyle { COSMOS_STYLE, WEB3_STYLE }

//public enum SendAssetType: Int {
//    case Only_Cosmos_Coin = 0               // support IBC, bank send                 (staking, ibc, native coins)
//    case Only_Cosmos_CW20 = 1               // support IBC, wasm send                 (cw20 tokens)
//    case Only_EVM_Coin = 2                  // not support IBC, only support Web3 tx  (evm main coin)
//    case Only_EVM_ERC20 = 3                 // not support IBC, only support Web3 tx  (erc20 tokens)
//    case CosmosEVM_Coin = 4                 // support IBC, bank send, Web3 tx        (staking, both tx style)
//    case CosmosEVM_ERC20 = 5                // not support IBC, only support Web3 tx  (erc20 tokens)
//}