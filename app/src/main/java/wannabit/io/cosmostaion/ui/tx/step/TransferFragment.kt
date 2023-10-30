package wannabit.io.cosmostaion.ui.tx.step

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.cosmos.bank.v1beta1.TxProto.MsgSend
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.databinding.FragmentTransferBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.dialog.tx.AddressFragment
import wannabit.io.cosmostaion.ui.dialog.tx.AddressListener
import wannabit.io.cosmostaion.ui.dialog.tx.AmountSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.AssetFragment
import wannabit.io.cosmostaion.ui.dialog.tx.AssetSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.ChainFragment
import wannabit.io.cosmostaion.ui.dialog.tx.ChainSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.InsertAmountFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoListener
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.viewmodel.tx.SendViewModel
import java.math.BigDecimal
import java.math.RoundingMode

class TransferFragment(
    private val selectedChain: CosmosLine,
    private val toSendDenom: String
) : BottomSheetDialogFragment() {

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

    private var availableAmount = BigDecimal.ZERO

    private val sendViewModel: SendViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
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
            BaseData.assets?.first { it.denom?.lowercase() == toSendDenom.lowercase() }
                ?.let { asset ->
                    selectedAsset = asset
                    transferAssetType = TransferAssetType.COIN_TRANSFER

                } ?: run {
                selectedChain.tokens.first { it.address == toSendDenom }.let { token ->
                    transferAssetType = if (toSendDenom.startsWith("0x")) {
                        TransferAssetType.ERC20_TRANSFER
                    } else {
                        TransferAssetType.CW20_TRANSFER
                    }
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
                selectedChain.tokens.first { it.address == toSendDenom }.let { token ->
                    tokenImg.setTokenImg(token.assetImg())
                    tokenName.text = token.symbol
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
            toSendAmount = toAmount
            if (transferAssetType == TransferAssetType.COIN_TRANSFER) {
                tabMsgTxt.visibility = View.GONE
                amountLayout.visibility = View.VISIBLE

                BaseData.getAsset(selectedChain.apiName, toSendDenom)?.let {
                    it.decimals?.let { decimal ->
                        val dpAmount = BigDecimal(toAmount).movePointLeft(decimal).setScale(decimal, RoundingMode.HALF_DOWN)
                        val price = BaseData.getPrice(selectedAsset?.coinGeckoId)
                        val value = price.multiply(dpAmount)

                        sendAmount.text = formatString(dpAmount.toPlainString(), decimal)
                        sendValue.text = formatAssetValue(value)
                    }
                }

            } else {

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
                        val dpAmount = amount.movePointLeft(decimal).setScale(decimal, RoundingMode.HALF_DOWN)
                        feeAmount.text = formatString(dpAmount.toPlainString(), decimal)
                        val value = price.multiply(amount).movePointLeft(decimal).setScale(decimal, RoundingMode.HALF_DOWN)
                        feeValue.text = formatAssetValue(value)
                    }
                }
            }

            availableAmount = if (transferAssetType == TransferAssetType.COIN_TRANSFER) {
                val balanceAmount = selectedChain.balanceAmount(toSendDenom)
                if (txFee?.getAmount(0)?.denom == toSendDenom) {
                    val feeAmount = BigDecimal(txFee?.getAmount(0)?.amount)
                    if (feeAmount > balanceAmount) { }
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
                tabMemoMsg.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.color_base03))
            } else {
                tabMemoMsg.text = txMemo
                tabMemoMsg.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.color_base01))
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
                            selectedRecipientChain = recipientAbleChains.firstOrNull { it.chainId == chainId }
                            updateChainView()
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
                        transferAssetType,
                        availableAmount,
                        toSendAmount,
                        selectedAsset,
                        selectedToken,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }

                        }).show(requireActivity().supportFragmentManager, InsertAmountFragment::class.java.name)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            addressView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    AddressFragment(selectedChain, selectedRecipientChain, existedAddress, object : AddressListener {
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
                    AssetFragment(selectedChain, feeInfos[selectedFeeInfo].feeDatas, object : AssetSelectListener {
                        override fun select(denom: String) {
                            var tempCoin: CoinProto.Coin? = null
                            selectedChain.getDefaultFeeCoins(requireContext()).forEach { feeCoin ->
                                if (feeCoin.denom == denom) {
                                    tempCoin = CoinProto.Coin.newBuilder().setDenom(denom).setAmount(feeCoin.amount).build()
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
                txFee = selectedChain.getBaseFee(requireContext(), selectedFeeInfo, txFee?.getAmount(0)?.denom)
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
                    requireActivity().overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_fade_out)
                }
            }
        }
    }

    private val sendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                sendViewModel.broadcastSend(getChannel(), selectedChain.address, onBindSend(), txFee, txMemo, selectedChain)
            }
        }

    private fun txSimul() {
        binding.apply {
            if (toSendAmount.isEmpty() || recipientAddress.text.isEmpty()) { return }
            if (BigDecimal(toSendAmount) == BigDecimal.ZERO) { return }

            backdropLayout.visibility = View.VISIBLE

            if (selectedChain.chainId == selectedRecipientChain?.chainId) {
                if (transferAssetType == TransferAssetType.COIN_TRANSFER) {
                    sendViewModel.simulateSend(getChannel(), selectedChain.address, onBindSend(), txFee, txMemo)

                } else {

                }
            }
        }
    }

    private fun setUpSimulate() {
        sendViewModel.simulate.observe(viewLifecycleOwner) { gasInfo ->
            isBroadCastTx(true)
            updateFeeViewWithSimul(gasInfo)
        }

        sendViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().makeToast(response)
            return@observe
        }
    }

    private fun updateFeeViewWithSimul(gasInfo: AbciProto.GasInfo) {
        txFee?.let { fee ->
            feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == fee.getAmount(0).denom }?.let { gasRate ->
                val gasLimit = (gasInfo.gasUsed.toDouble() * selectedChain.gasMultiply()).toLong().toBigDecimal()
                val feeCoinAmount = gasRate.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.HALF_DOWN)

                val feeCoin =  CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom).setAmount(feeCoinAmount.toString()).build()
                txFee = TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin).build()
            }
        }
        updateFeeView()
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnSend.updateButtonView(isSuccess)
    }

    private fun setUpBroadcast() {
        sendViewModel.broadcastTx.observe(viewLifecycleOwner) { txResponse ->
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

    private fun getChannel(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(selectedChain.grpcHost, selectedChain.grpcPort).useTransportSecurity().build()
    }

    private fun onBindSend(): MsgSend {
        val sendCoin = CoinProto.Coin.newBuilder().setAmount(toSendAmount).setDenom(toSendDenom).build()
        return MsgSend.newBuilder().setFromAddress(selectedChain.address).setToAddress(existedAddress).addAmount(sendCoin).build()
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 19 / 20
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onStart() {
        super.onStart()

        val bottomSheetDialog = dialog as BottomSheetDialog
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

        bottomSheet?.let { sheet ->
            val behavior = BottomSheetBehavior.from(sheet)
            behavior.isHideable = true

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> dismiss()
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            behavior.state = BottomSheetBehavior.STATE_EXPANDED
                        }

                        else -> {}
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class TransferAssetType { COIN_TRANSFER, CW20_TRANSFER, ERC20_TRANSFER }