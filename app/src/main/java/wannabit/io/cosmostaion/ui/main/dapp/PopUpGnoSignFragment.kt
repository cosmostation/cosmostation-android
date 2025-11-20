package wannabit.io.cosmostaion.ui.main.dapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.tm2.tx.TxProto.Tx
import com.tm2.tx.TxProto.TxFee
import com.tm2.tx.TxProto.TxSignature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bitcoinj.core.ECKey
import org.bouncycastle.util.encoders.Base64
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainGno
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatJsonString
import wannabit.io.cosmostaion.common.jsonRpcResponse
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.databinding.FragmentSuiSignBinding
import wannabit.io.cosmostaion.sign.Signer
import wannabit.io.cosmostaion.sign.Signer.generateGrpcPubKeyFromPriv
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class PopUpGnoSignFragment(
    var selectedChain: BaseChain?,
    private val id: Long,
    private val data: String,
    val listener: WcSignRawDataListener
) : BaseTxFragment() {

    private var _binding: FragmentSuiSignBinding? = null
    private val binding get() = _binding!!

    private var updateData: String? = null
    private var isErrorTx = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuiSignBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewResource()
        setUpClickAction()
    }

    private fun initViewResource() {
        binding.apply {
            signView.setBackgroundResource(R.drawable.cell_bg)
            feeView.setBackgroundResource(R.drawable.cell_bg)
            dialogTitle.text = getString(R.string.str_tx_request)
            warnMsg.visibility = View.VISIBLE
            warnMsg.text = getString(R.string.str_affect_danger_msg)
            warnMsg.setTextColor(
                ContextCompat.getColorStateList(
                    requireContext(), R.color.color_accent_red
                )
            )
            signView.visibility = View.GONE
            feeView.visibility = View.INVISIBLE

            parsingRequest()
        }
    }

    private fun parsingRequest() {
        lifecycleScope.launch(Dispatchers.IO) {
            (selectedChain as ChainGno).let { chain ->
                chain.gnoRpcFetcher()?.let { fetcher ->
                    val txJsonObject = JsonParser.parseString(data).asJsonObject
                    val defaultFee = chain.getInitFee(requireContext())

                    val memo = if (txJsonObject.has("memo")) txJsonObject["memo"].asString else ""
                    val gasFee =
                        if (txJsonObject.has("gasFee")) txJsonObject["gasFee"].asLong.toString() else defaultFee?.getAmount(
                            0
                        )?.amount
                    val gasWanted =
                        if (txJsonObject.has("gasWanted")) txJsonObject["gasWanted"].asLong else 3000000000L

                    val txFee = TxFee.newBuilder().setGasWanted(gasWanted)
                        .setGasFee(gasFee + defaultFee?.getAmount(0)?.denom)
                        .build()
                    val pubKey =
                        generateGrpcPubKeyFromPriv(
                            chain,
                            ECKey.fromPrivate(chain.privateKey).privateKeyAsHex
                        )

                    val signMsgs = Signer.gnoDappSignedMsg(txJsonObject)
                    val builder = Tx.newBuilder()
                    signMsgs.forEach { msgAny ->
                        builder.addMessages(msgAny)
                    }

                    val simulateTx = builder.setFee(txFee).setMemo(memo)
                        .addSignatures(TxSignature.newBuilder().setPubKey(pubKey).build())
                        .build()
                    val txByte = Base64.toBase64String(simulateTx.toByteArray())
                    val simulateRequest = JsonRpcRequest(
                        method = "abci_query", params = listOf(".app/simulate", txByte, "0", false)
                    )
                    val simulateResponse = jsonRpcResponse(
                        fetcher.gnoRpc(), simulateRequest
                    )
                    val simulateJsonObject = Gson().fromJson(
                        simulateResponse.body?.string(), JsonObject::class.java
                    )

                    val simulateUsedData = if (simulateResponse.isSuccessful) {
                        if (simulateJsonObject["result"].asJsonObject["response"].asJsonObject["Value"].isJsonNull) {
                            isErrorTx = true
                            "Simulate Error"

                        } else {
                            val value =
                                simulateJsonObject["result"].asJsonObject["response"].asJsonObject["Value"].asString
                            val responseBase =
                                com.tm2.abci.AbciProto.ResponseDeliverTx.parseFrom(
                                    Base64.decode(
                                        value.toByteArray()
                                    )
                                )

                            if (responseBase.responseBase.hasError()) {
                                isErrorTx = true
                                responseBase.responseBase.error.typeUrl
                            } else {
                                isErrorTx = false
                                responseBase.gasUsed.toString()
                            }
                        }

                    } else {
                        isErrorTx = true
                        "Rpc Error"
                    }

                    withContext(Dispatchers.Main) {
                        binding.apply {
                            loading.visibility = View.GONE
                            binding.btnConfirm.isEnabled = true
                            signView.visibility = View.VISIBLE
                            updateFeeView(simulateUsedData)
                        }
                    }
                }
            }
        }
    }

    private fun updateFeeView(simulateData: String) {
        binding.apply {
            val txJsonObject = JsonParser.parseString(data).asJsonObject

            if (isErrorTx) {
                invalidMsg.visibility = View.VISIBLE
                invalidMsg.text = simulateData
                btnConfirm.updateButtonView(false)
                signData.text = formatJsonString(txJsonObject.toString())

            } else {
                feeView.visibility = View.VISIBLE
                invalidMsg.visibility = View.GONE

                selectedChain?.let { chain ->
                    BaseData.getAsset(chain.apiName, chain.getStakeAssetDenom())?.let { asset ->
                        dappFeeTokenImg.setTokenImg(asset)
                        dappFeeToken.text = asset.symbol

                        val gasLimit =
                            (simulateData.toDouble() * chain.simulatedGasMultiply()).toLong()
                                .toBigDecimal()

                        val feeCoinAmount = gasLimit.multiply(BigDecimal("1.1")).movePointLeft(3)
                            .movePointLeft(asset.decimals ?: 8)
                            .setScale(asset.decimals ?: 8, RoundingMode.UP)
                        val price = BaseData.getPrice(asset.coinGeckoId)
                        val value = price.multiply(feeCoinAmount)

                        feeAmount.text =
                            formatAmount(feeCoinAmount.toPlainString(), asset.decimals ?: 8)
                        feeValue.text = formatAssetValue(value)

                        txJsonObject.addProperty(
                            "gasFee",
                            feeCoinAmount.movePointRight(asset.decimals ?: 8)
                                .setScale(
                                    0,
                                    RoundingMode.UP
                                )
                        )
                        txJsonObject.addProperty("gasWanted", gasLimit)

                        signData.text = formatJsonString(txJsonObject.toString())
                        updateData = txJsonObject.toString()
                    }
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnCancel.setOnClickListener {
                if (!loading.isVisible) {
                    listener.cancel(id)
                    dismiss()
                }
            }

            btnConfirm.setOnClickListener {
                if (!loading.isVisible && btnConfirm.isEnabled) {
                    listener.sign(id, updateData.toString())
                    dismiss()
                }
            }
        }
    }

    interface WcSignRawDataListener {
        fun sign(id: Long, data: String)
        fun cancel(id: Long)
    }

    override fun onStart() {
        super.onStart()

        val bottomSheetDialog = dialog as BottomSheetDialog
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

        bottomSheet?.let { sheet ->
            val behavior = BottomSheetBehavior.from(sheet)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = false
            behavior.skipCollapsed = true
            behavior.isHideable = false
        }

        bottomSheetDialog.setCanceledOnTouchOutside(false)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}