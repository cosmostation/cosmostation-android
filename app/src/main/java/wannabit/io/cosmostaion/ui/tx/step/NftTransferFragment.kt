package wannabit.io.cosmostaion.ui.tx.step

import android.app.Activity
import android.content.Intent
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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.protobuf.ByteString
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.getdAmount
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.Cw721TokenModel
import wannabit.io.cosmostaion.data.model.req.WasmCw721SendReq
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentNftTransferBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.option.tx.address.AddressListener
import wannabit.io.cosmostaion.ui.option.tx.address.TransferAddressFragment
import wannabit.io.cosmostaion.ui.option.tx.general.AssetFragment
import wannabit.io.cosmostaion.ui.option.tx.general.AssetSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.BaseFeeAssetFragment
import wannabit.io.cosmostaion.ui.option.tx.general.BaseFeeAssetSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.MemoFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoListener
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.TxResultType
import java.math.RoundingMode

class NftTransferFragment(
    val fromChain: BaseChain, val info: JsonObject?, val token: Cw721TokenModel?
) : BaseTxFragment() {

    private var _binding: FragmentNftTransferBinding? = null
    private val binding get() = _binding!!

    private val sendAssetType = SendAssetType.ONLY_COSMOS_CW20

    private lateinit var toChain: BaseChain

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null
    private var txTip: TxProto.Tip? = null

    private var toAddress = ""
    private var txMemo = ""

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNftTransferBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            listOf(
                recipientChainView, addressView, sendAssetView, memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }
            chainImg.alpha = 0.2f
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            initToChain()
            initNft()
        }
    }

    private fun initToChain() {
        binding.apply {
            toChain = fromChain
            chainImg.setImageResource(toChain.logo)
            chainName.text = toChain.name.uppercase()
        }
    }

    private fun initNft() {
        binding.apply {
            token?.tokenDetail?.let {
                nftImg.clipToOutline = true
                it.asJsonObject["url"].asString?.let { url ->
                    Glide.with(requireActivity()).load(url).diskCacheStrategy(
                        DiskCacheStrategy.ALL
                    ).placeholder(R.drawable.icon_nft_default).error(R.drawable.icon_nft_default)
                        .into(nftImg)

                } ?: run {
                    nftImg.setImageResource(R.drawable.icon_nft_default_alpha)
                }
            }
            nftId.text = "#" + token?.tokenId
            info?.let {
                nftTitle.text = it.asJsonObject["name"].asString
            }
        }
    }

    private fun initFee() {
        binding.apply {
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

            if (fromChain.grpcFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                val tipTitle = listOf(
                    "No Tip", "20% Tip", "50% Tip", "100% Tip"
                )
                for (i in tipTitle.indices) {
                    val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                    feeSegment.addView(
                        segmentView.root,
                        i,
                        LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                    )
                    segmentView.btnTitle.text = tipTitle[i]
                }
                feeSegment.setPosition(selectedFeeInfo, false)
                val baseFee = fromChain.grpcFetcher?.cosmosBaseFees?.get(0)
                val gasAmount = fromChain.getFeeBaseGasAmount().toBigDecimal()
                val feeDenom = baseFee?.denom
                val feeAmount =
                    baseFee?.getdAmount()?.multiply(gasAmount)?.setScale(0, RoundingMode.DOWN)
                txFee = TxProto.Fee.newBuilder().setGasLimit(gasAmount.toLong()).addAmount(
                    CoinProto.Coin.newBuilder().setDenom(feeDenom).setAmount(feeAmount.toString())
                        .build()
                ).build()

            } else {
                feeInfos = fromChain.getFeeInfos(requireContext())
                for (i in feeInfos.indices) {
                    val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                    feeSegment.addView(
                        segmentView.root,
                        i,
                        LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                    )
                    segmentView.btnTitle.text = feeInfos[i].title
                }
                feeSegment.setPosition(fromChain.getFeeBasePosition(), false)
                selectedFeeInfo = fromChain.getFeeBasePosition()
                txFee = fromChain.getInitFee(requireContext())
            }
        }
        updateFeeView()
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

    private fun updateMemoView(memo: String) {
        binding.apply {
            txMemo = memo
            if (txMemo.isEmpty()) {
                tabMemoMsg.text = getString(R.string.str_tap_for_add_memo_msg)
                tabMemoMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(), R.color.color_base03
                    )
                )
            } else {
                tabMemoMsg.text = txMemo
                tabMemoMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(), R.color.color_base01
                    )
                )
            }
        }
        txSimulate()
    }

    private fun updateFeeView() {
        binding.apply {
            txFee?.getAmount(0)?.let { fee ->
                BaseData.getAsset(fromChain.apiName, fee.denom)?.let { asset ->
                    feeTokenImg.setTokenImg(asset)
                    feeToken.text = asset.symbol

                    val amount = fee.amount.toBigDecimal().amountHandlerLeft(asset.decimals ?: 6)
                    val price = BaseData.getPrice(asset.coinGeckoId)
                    val value = price.multiply(amount)

                    feeAmount.text = formatAmount(amount.toPlainString(), asset.decimals ?: 6)
                    feeValue.text = formatAssetValue(value)
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
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
                                            requireContext(), R.color.color_base01
                                        )
                                    )
                                }
                                updateRecipientAddressView(address)
                            }
                        })
                )
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

            feeTokenLayout.setOnClickListener {
                txFee?.let { fee ->
                    if (fromChain.grpcFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                        handleOneClickWithDelay(
                            BaseFeeAssetFragment(fromChain,
                                fromChain.grpcFetcher?.cosmosBaseFees,
                                object : BaseFeeAssetSelectListener {
                                    override fun select(denom: String) {
                                        fromChain.grpcFetcher?.cosmosBaseFees?.firstOrNull { it.denom == denom }
                                            ?.let { baseFee ->
                                                val feeAmount = baseFee.getdAmount()
                                                    .multiply(fee.gasLimit.toBigDecimal())
                                                    ?.setScale(0, RoundingMode.DOWN)
                                                val updateFeeCoin =
                                                    CoinProto.Coin.newBuilder().setDenom(denom)
                                                        .setAmount(feeAmount.toString()).build()
                                                txFee = TxProto.Fee.newBuilder()
                                                    .setGasLimit(fee.gasLimit)
                                                    .addAmount(updateFeeCoin).build()

                                                updateFeeView()
                                                txSimulate()
                                            }
                                    }
                                })
                        )

                    } else {
                        handleOneClickWithDelay(
                            AssetFragment.newInstance(fromChain,
                                feeInfos[selectedFeeInfo].feeDatas.toMutableList(),
                                object : AssetSelectListener {
                                    override fun select(denom: String) {
                                        fromChain.getDefaultFeeCoins(requireContext())
                                            .firstOrNull { it.denom == denom }?.let { feeCoin ->
                                                val updateFeeCoin =
                                                    CoinProto.Coin.newBuilder().setDenom(denom)
                                                        .setAmount(feeCoin.amount).build()
                                                txFee = TxProto.Fee.newBuilder()
                                                    .setGasLimit(fee.gasLimit)
                                                    .addAmount(updateFeeCoin).build()

                                                updateFeeView()
                                                txSimulate()
                                            }
                                    }
                                })
                        )
                    }
                }
            }

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeeInfo = position
                txFee = if (fromChain.grpcFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                    Signer.setFee(selectedFeeInfo, txFee)

                } else {
                    fromChain.getBaseFee(
                        requireContext(), selectedFeeInfo, txFee?.getAmount(0)?.denom
                    )
                }
                updateFeeView()
                txSimulate()
            }

            btnNftSend.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    nftSendResultLauncher.launch(this)
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

    private val nftSendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                txViewModel.broadcastWasm(
                    getChannel(fromChain),
                    onBindWasmNftSend(),
                    txFee,
                    txTip,
                    txMemo,
                    fromChain
                )
            }
        }

    private fun txSimulate() {
        binding.apply {
            if (toAddress.isEmpty()) {
                return
            }
            if (!fromChain.isGasSimulable()) {
                return updateFeeViewWithSimulate(null)
            }
            btnNftSend.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulateWasm(
                getChannel(fromChain),
                fromChain.address,
                onBindWasmNftSend(),
                txFee,
                txTip,
                txMemo,
                fromChain
            )
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

    private fun updateFeeViewWithSimulate(gasInfo: AbciProto.GasInfo?) {
        txFee?.let { fee ->
            gasInfo?.let { info ->
                val gasLimit =
                    (info.gasUsed.toDouble() * fromChain.gasMultiply()).toLong().toBigDecimal()
                if (fromChain.grpcFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                    fromChain.grpcFetcher?.cosmosBaseFees?.firstOrNull {
                        it.denom == fee.getAmount(
                            0
                        ).denom
                    }?.let { baseFee ->
                        val feeCoinAmount =
                            baseFee.getdAmount().multiply(gasLimit).setScale(0, RoundingMode.UP)
                        val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                            .setAmount(feeCoinAmount.toString()).build()
                        txFee = TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong())
                            .addAmount(feeCoin).build()
                        txFee = Signer.setFee(selectedFeeInfo, txFee)
                    }

                } else {
                    val selectedFeeData =
                        feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == fee.getAmount(0).denom }
                    val gasRate = selectedFeeData?.gasRate

                    val feeCoinAmount = gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)
                    val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                        .setAmount(feeCoinAmount.toString()).build()
                    txFee =
                        TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin)
                            .build()
                }
            }
        }
        updateFeeView()
        isBroadCastTx(true)
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnNftSend.updateButtonView(isSuccess)
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
                putExtra("selectedChain", fromChain.tag)
                putExtra("txResultType", TxResultType.NFT.toString())
                val hash = txResponse.txhash
                if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                startActivity(this)
            }
            dismiss()
        }
    }

    private fun onBindWasmNftSend(): MutableList<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract?> {
        val result: MutableList<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract?> = mutableListOf()
        token?.let { tokenModel ->
            info?.let { info ->
                val wasmSendReq = WasmCw721SendReq(toAddress, tokenModel.tokenId)
                val jsonData = Gson().toJson(wasmSendReq)
                val msg = ByteString.copyFromUtf8(jsonData)
                result.add(
                    com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract.newBuilder()
                        .setSender(fromChain.address)
                        .setContract(info.asJsonObject["contractAddress"]?.asString).setMsg(msg)
                        .build()
                )
                return result
            }
        }
        return result
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}