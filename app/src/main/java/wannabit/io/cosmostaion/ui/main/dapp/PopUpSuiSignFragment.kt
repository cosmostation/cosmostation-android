package wannabit.io.cosmostaion.ui.main.dapp

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.i2p.crypto.eddsa.Utils
import org.bouncycastle.util.encoders.Base64
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatJsonString
import wannabit.io.cosmostaion.common.jsonRpcResponse
import wannabit.io.cosmostaion.common.setImg
import wannabit.io.cosmostaion.sign.Signer
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.data.model.req.SuiTransactionBlock
import wannabit.io.cosmostaion.databinding.FragmentSuiSignBinding
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode


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
                        signature = Signer.suiSignature(
                            selectedChain as ChainSui,
                            txJsonObject["message"].asString
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

                        val response = RetrofitInstance.suiApi.transactionBlock(
                            SuiTransactionBlock(
                                txJsonObject["transactionBlockSerialized"].asString,
                                mainAddress,
                                fetcher.suiRpc()
                            )
                        )

                        if (response.isSuccessful) {
                            val txBytes = Base64.toBase64String(Utils.hexToBytes(response.body()))
                            val suiDryRunRequest = JsonRpcRequest(
                                method = "sui_dryRunTransactionBlock", params = listOf(txBytes)
                            )
                            val suiDryRunResponse =
                                jsonRpcResponse(fetcher.suiRpc(), suiDryRunRequest)
                            val suiDryRunJsonObject = Gson().fromJson(
                                suiDryRunResponse.body?.string(), JsonObject::class.java
                            )

                            txSerialized.addProperty("sender", selectedChain?.mainAddress)
                            if (txSerialized["gasData"] != null) {
                                txSerialized["gasData"].asJsonObject?.let { gasData ->
                                    gasData.addProperty(
                                        "budget",
                                        suiDryRunJsonObject["result"].asJsonObject["input"].asJsonObject["gasData"].asJsonObject["budget"].asString
                                    )
                                    gasData.addProperty(
                                        "price",
                                        suiDryRunJsonObject["result"].asJsonObject["input"].asJsonObject["gasData"].asJsonObject["price"].asString
                                    )
                                    gasData.addProperty(
                                        "owner",
                                        suiDryRunJsonObject["result"].asJsonObject["input"].asJsonObject["gasData"].asJsonObject["owner"].asString
                                    )
                                    gasData.add(
                                        "payment",
                                        suiDryRunJsonObject["result"].asJsonObject["input"].asJsonObject["gasData"].asJsonObject["payment"].asJsonArray
                                    )
                                }
                            }
                            format = formatJsonString(txSerialized.toString())
                            val computationCost =
                                suiDryRunJsonObject["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["computationCost"].asString.toBigDecimal()
                            val storageCost =
                                suiDryRunJsonObject["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["storageCost"].asString.toBigDecimal()
                            val storageRebate =
                                suiDryRunJsonObject["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["storageRebate"].asString.toBigDecimal()

                            val cost = storageCost.subtract(storageRebate)
                            val dpCost = if (cost > BigDecimal.ZERO) {
                                cost
                            } else {
                                BigDecimal.ZERO
                            }
                            gasCost = computationCost.add(dpCost).setScale(0, RoundingMode.DOWN)
                            updateData = txBytes
                            signature = Signer.suiSignature(selectedChain as ChainSui, txBytes)[0]
                        }

                        withContext(Dispatchers.Main) {
                            binding.apply {
                                loading.visibility = View.GONE
                                binding.btnConfirm.isEnabled = true
                                signData.text = format
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

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        binding.apply {
            if (!loading.isVisible) {
                listener.cancel(id)
            }
        }
    }

    interface WcSignRawDataListener {
        fun sign(id: Long, data: String, signature: String)
        fun cancel(id: Long)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
