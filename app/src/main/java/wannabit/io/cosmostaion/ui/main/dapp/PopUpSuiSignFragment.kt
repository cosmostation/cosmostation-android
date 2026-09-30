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
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.protobuf.FieldMask
import com.sui.rpc.v2.TransactionExecutionServiceGrpc
import com.sui.rpc.v2.TransactionExecutionServiceProto
import com.sui.rpc.v2.TransactionProto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bouncycastle.util.encoders.Base64
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.sign.mapProgrammableTransactionKind
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatJsonString
import wannabit.io.cosmostaion.common.setImg
import wannabit.io.cosmostaion.databinding.FragmentSuiSignBinding
import wannabit.io.cosmostaion.sign.Signer
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.TimeUnit


class PopUpSuiSignFragment(
    var selectedChain: BaseChain?,
    private val id: Long,
    private val data: String,
    private val method: String?,
    val listener: WcSignRawDataListener
) : BaseTxFragment() {

    private var _binding: FragmentSuiSignBinding? = null
    private val binding get() = _binding!!

    private var updateData: String? = null
    private var signature: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuiSignBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewResource()
        parsingRequest()
        setUpClickAction()
    }

    private fun initViewResource() {
        binding.apply {
            signView.setBackgroundResource(R.drawable.cell_bg)
            feeView.setBackgroundResource(R.drawable.cell_bg)
            if (method == "sui_signMessage") {
                dialogTitle.text = getString(R.string.str_permit_request)
                warnMsg.visibility = View.GONE
                feeView.visibility = View.INVISIBLE

            } else {
                dialogTitle.text = getString(R.string.str_tx_request)
                warnMsg.visibility = View.VISIBLE
                warnMsg.text = getString(R.string.str_affect_danger_msg)
                warnMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(), R.color.color_accent_red
                    )
                )
                dappFeeTokenImg.setImg(R.drawable.token_sui)
                dappFeeToken.text = "SUI"
            }
        }
    }

    private fun parsingRequest() {
        lifecycleScope.launch(Dispatchers.IO) {
            val txJsonObject = JsonParser.parseString(data).asJsonObject
            (selectedChain as ChainSui).apply {
                suiFetcher()?.let { fetcher ->
                    if (method == "sui_signMessage") {
                        val messageBytes = Base64.decode(txJsonObject["message"].asString)
                        updateData = txJsonObject["message"].asString
                        signature = Signer.messageMoveSignature(
                            selectedChain as ChainSui, txJsonObject["message"].asString
                        )[0]

                        withContext(Dispatchers.Main) {
                            binding.apply {
                                btnConfirm.isEnabled = true
                                loading.visibility = View.GONE
                                signData.text = formatJsonString(String(messageBytes))
                            }
                        }

                    } else {
                        val txSerialized = Gson().fromJson(
                            txJsonObject["transactionBlockSerialized"].asString,
                            JsonObject::class.java
                        )

                        var format = ""
                        var gasCost = BigDecimal.ZERO

                        try {
                            val kind = mapProgrammableTransactionKind(txSerialized)

                            val request =
                                TransactionExecutionServiceProto.SimulateTransactionRequest.newBuilder()
                                    .setTransaction(
                                        TransactionProto.Transaction.newBuilder()
                                            .setSender(selectedChain?.mainAddress ?: "")
                                            .setKind(kind)
                                    )
                                    .setDoGasSelection(true)
                                    .setReadMask(
                                        FieldMask.newBuilder()
                                            .addPaths("transaction.effects")
                                            .addPaths("transaction.transaction")
                                    ).build()

                            val stub =
                                TransactionExecutionServiceGrpc.newBlockingStub(fetcher.getChannel())
                                    .withDeadlineAfter(15L, TimeUnit.SECONDS)
                            val response = stub.simulateTransaction(request)
                            val resolvedTx = response.transaction.transaction
                            val gasPayment = resolvedTx.gasPayment

                            txSerialized.addProperty("sender", selectedChain?.mainAddress)
                            if (txSerialized["gasData"] != null) {
                                txSerialized["gasData"].asJsonObject?.let { gasData ->
                                    gasData.addProperty("budget", gasPayment.budget.toString())
                                    gasData.addProperty("price", gasPayment.price.toString())
                                    gasData.addProperty("owner", gasPayment.owner)
                                    gasData.add("payment", JsonArray().apply {
                                        gasPayment.objectsList.forEach { ref ->
                                            add(JsonObject().apply {
                                                addProperty("objectId", ref.objectId)
                                                addProperty("version", ref.version)
                                                addProperty("digest", ref.digest)
                                            })
                                        }
                                    })
                                }
                            }
                            format = formatJsonString(txSerialized.toString())

                            val gasUsed = response.transaction.effects.gasUsed
                            val computationCost = gasUsed.computationCost.toBigDecimal()
                            val storageCost = gasUsed.storageCost.toBigDecimal()
                            val storageRebate = gasUsed.storageRebate.toBigDecimal()

                            val cost = storageCost.subtract(storageRebate)
                            val dpCost = if (cost > BigDecimal.ZERO) {
                                cost
                            } else {
                                BigDecimal.ZERO
                            }
                            gasCost = computationCost.add(dpCost).setScale(0, RoundingMode.DOWN)

                            val txBytes =
                                Base64.toBase64String(resolvedTx.bcs.value.toByteArray())
                            updateData = txBytes
                            signature = Signer.moveSignature(selectedChain as ChainSui, txBytes)[0]

                        } catch (e: Exception) {
                            format = e.message ?: "Failed to build transaction"
                        }

                        withContext(Dispatchers.Main) {
                            binding.apply {
                                loading.visibility = View.GONE
                                binding.btnConfirm.isEnabled = true
                                signData.text = format
                                val coinGeckoId = BaseData.getAsset(
                                    apiName, (selectedChain as ChainSui).getStakeAssetDenom()
                                )?.coinGeckoId
                                val price = BaseData.getPrice(coinGeckoId)
                                val dpBudget =
                                    gasCost.movePointLeft(9).setScale(9, RoundingMode.DOWN)
                                val value = price.multiply(dpBudget)
                                feeAmount.text = formatAmount(dpBudget.toPlainString(), 9)
                                feeValue.text = formatAssetValue(value)
                            }
                        }
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
                    listener.sign(id, updateData.toString(), signature.toString())
                    dismiss()
                }
            }
        }
    }

    interface WcSignRawDataListener {
        fun sign(id: Long, data: String, signature: String)
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
