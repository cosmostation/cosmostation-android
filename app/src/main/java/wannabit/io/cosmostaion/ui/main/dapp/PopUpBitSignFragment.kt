package wannabit.io.cosmostaion.ui.main.dapp

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonParser
import com.walletconnect.util.bytesToHex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.testnetClass.ChainBitcoin84Testnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatJsonString
import wannabit.io.cosmostaion.common.setImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.databinding.FragmentBitSignBinding
import wannabit.io.cosmostaion.sign.BitcoinJs
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class PopUpBitSignFragment(
    var selectedChain: BaseChain?,
    private val id: Long,
    private val data: String,
    private val method: String?,
    val listener: WcSignRawDataListener
) : BaseTxFragment() {

    private var _binding: FragmentBitSignBinding? = null
    private val binding get() = _binding!!

    private var bitToAddress: String = ""
    private var bitSatAmount: String = ""
    private var message: String = ""

    private var updateData: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBitSignBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewResource()
    }

    private fun initViewResource() {
        binding.apply {
            BitcoinJs.initialize(requireContext(), lifecycleScope) {
                if (BitcoinJs.isInitialized()) {
                    signView.setBackgroundResource(R.drawable.cell_bg)
                    feeView.setBackgroundResource(R.drawable.cell_bg)
                    if (method == "bit_signMessage") {
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
                        dappFeeTokenImg.setImg(R.drawable.token_btc)
                        dappFeeToken.text = selectedChain?.coinSymbol
                    }
                    parsingRequest()
                    setUpObserve()
                    setUpClickAction()

                } else {
                    initViewResource()
                }
            }
        }
    }

    private fun parsingRequest() {
        (selectedChain as ChainBitCoin84).apply {
            when (method) {
                "bit_sendBitcoin" -> {
                    val txJsonObject = JsonParser.parseString(data).asJsonObject
                    bitToAddress = txJsonObject["to"].asString
                    bitSatAmount = txJsonObject["satAmount"].asLong.toString()
                    message = JSONObject(
                        mapOf(
                            "From" to selectedChain?.mainAddress,
                            "To" to bitToAddress,
                            "Amount" to bitSatAmount.toBigDecimal().movePointLeft(8)
                                .setScale(8, RoundingMode.DOWN).toPlainString()
                        )
                    ).toString(4)
                    txViewModel.bitTxData(this@apply)
                }

                else -> {
                    txViewModel.bitTxData(this@apply)
                }
            }
        }
    }

    private fun setUpObserve() {
        txViewModel.bitTxDataResult.observe(this) { bitData ->
            (selectedChain as ChainBitCoin84).apply {
                lifecycleScope.launch(Dispatchers.IO) {
                    when (method) {
                        "bit_sendBitcoin" -> {
                            val bitVByteFee = btcFetcher()?.bitVBytesFee(bitData.first, "")
                            val bitFee = bitData.second.toBigDecimal().multiply(bitVByteFee)
                                .movePointRight(5).setScale(0, RoundingMode.UP)
                            var btcBalance = BigDecimal.ZERO
                            bitData.first?.forEach { uTxo ->
                                btcBalance = btcBalance.add(uTxo["value"].asLong.toBigDecimal())
                            }

                            withContext(Dispatchers.Main) {
                                binding.apply {
                                    val price = BaseData.getPrice(coinGeckoId)
                                    val amount =
                                        bitFee.movePointLeft(8).setScale(8, RoundingMode.UP)
                                    val value = price.multiply(amount)

                                    loading.visibility = View.GONE
                                    signData.text = message
                                    feeAmount.text = formatAmount(amount.toPlainString(), 8)
                                    feeValue.text = formatAssetValue(value)

                                    if (bitFee.add(bitSatAmount.toBigDecimal()) > btcBalance) {
                                        btnConfirm.updateButtonView(false)
                                        noTxTxt.visibility = View.VISIBLE

                                    } else {
                                        btnConfirm.updateButtonView(true)
                                        noTxTxt.visibility = View.GONE

                                        txViewModel.bitSendSimulate(
                                            (selectedChain as ChainBitCoin84),
                                            BitcoinJs,
                                            mainAddress,
                                            bitToAddress,
                                            bitSatAmount,
                                            btcBalance.subtract(bitSatAmount.toBigDecimal())
                                                ?.subtract(bitFee).toString(),
                                            "",
                                            bitData.first
                                        )
                                    }
                                }
                            }
                        }

                        "bit_signMessage" -> {
                            val txJsonObject = JsonParser.parseString(data).asJsonObject
                            val message = txJsonObject["message"].asString
                            val type = txJsonObject["type"].asString
                            val privateKey = selectedChain?.privateKey?.bytesToHex()

                            try {
                                if (type == "ecdsa") {
                                    val signMessageFunction = """function signMessageFunction() {
                                            const signMessage = signMessageECDSA('${message}', '${privateKey}');
                                            return signMessage;
                                        }""".trimMargin()
                                    BitcoinJs.mergeFunction(signMessageFunction)
                                    updateData = BitcoinJs.executeFunction("signMessageFunction()")

                                } else {
                                    val signMessageFunction = """function signMessageFunction() {
                                            const signMessage = signMessageBIP322('${message}', '${privateKey}', '${selectedChain?.mainAddress}');
                                            return signMessage;
                                        }""".trimMargin()
                                    BitcoinJs.mergeFunction(signMessageFunction)
                                    updateData = BitcoinJs.executeFunction("signMessageFunction()")
                                }

                                withContext(Dispatchers.Main) {
                                    binding.apply {
                                        btnConfirm.isEnabled = true
                                        loading.visibility = View.GONE
                                        signData.text = message
                                    }
                                }

                            } catch (e: Exception) {
                                withContext(Dispatchers.Main) {
                                    binding.apply {
                                        loading.visibility = View.GONE
                                        btnConfirm.updateButtonView(false)
                                        signData.text = ""
                                        noTxTxt.visibility = View.VISIBLE
                                        noTxTxt.text = getString(R.string.error_js)
                                    }
                                }
                            }
                        }

                        "bit_signPsbt" -> {
                            val privateKey = selectedChain?.privateKey?.bytesToHex()
                            val network = if (selectedChain is ChainBitcoin84Testnet) {
                                "testnet"
                            } else {
                                "mainnet"
                            }
                            val bitVByteFee = btcFetcher()?.bitVBytesFee(bitData.first, "")
                            val bitFee = bitData.second.toBigDecimal().multiply(bitVByteFee)
                                .movePointRight(5).setScale(0, RoundingMode.UP)
                            var btcBalance = BigDecimal.ZERO
                            bitData.first?.forEach { uTxo ->
                                btcBalance = btcBalance.add(uTxo["value"].asLong.toBigDecimal())
                            }

                            try {
                                val signPsbtFunction = """function signPsbtFunction() {
                                            const txHex = signPsbt('${data}', '${privateKey}');
                                            return txHex;
                                        }""".trimMargin()
                                BitcoinJs.mergeFunction(signPsbtFunction)
                                updateData = BitcoinJs.executeFunction("signPsbtFunction()")

                                val getInOutPutsFunction = """function getInOutPutsFunction() {
                                            const inputOutput = getInOutPuts('${data}', '${network}');
                                            return inputOutput;
                                        }""".trimMargin()
                                BitcoinJs.mergeFunction(getInOutPutsFunction)
                                val messageData =
                                    BitcoinJs.executeFunction("getInOutPutsFunction()").toString()
                                val messageJsonObject = JsonParser.parseString(messageData).asJsonObject
                                val outputs = messageJsonObject["outputs"].asJsonArray
                                var outputBalance = BigDecimal.ZERO
                                outputs.forEach { output ->
                                    if (output.asJsonObject["address"].asString != selectedChain?.mainAddress) {
                                        outputBalance =
                                            outputBalance.add(output.asJsonObject["value"].asString.toBigDecimal())
                                    }
                                }

                                withContext(Dispatchers.Main) {
                                    binding.apply {
                                        val price = BaseData.getPrice(coinGeckoId)
                                        val amount =
                                            bitFee.movePointLeft(8).setScale(8, RoundingMode.UP)
                                        val value = price.multiply(amount)

                                        loading.visibility = View.GONE
                                        signData.text = formatJsonString(messageData)
                                        feeAmount.text = formatAmount(amount.toPlainString(), 8)
                                        feeValue.text = formatAssetValue(value)

                                        if (bitFee.add(outputBalance) > btcBalance) {
                                            btnConfirm.updateButtonView(false)
                                            noTxTxt.visibility = View.VISIBLE
                                        } else {
                                            btnConfirm.updateButtonView(true)
                                            noTxTxt.visibility = View.GONE
                                        }
                                    }
                                }

                            } catch (e: Exception) {
                                withContext(Dispatchers.Main) {
                                    binding.apply {
                                        val price = BaseData.getPrice(coinGeckoId)
                                        val amount =
                                            bitFee.movePointLeft(8).setScale(8, RoundingMode.UP)
                                        val value = price.multiply(amount)

                                        loading.visibility = View.GONE
                                        signData.text = ""
                                        feeAmount.text = formatAmount(amount.toPlainString(), 8)
                                        feeValue.text = formatAssetValue(value)

                                        btnConfirm.updateButtonView(false)
                                        noTxTxt.visibility = View.VISIBLE
                                        noTxTxt.text = getString(R.string.error_js)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        txViewModel.simulate.observe(this) { txHex ->
            updateData = txHex
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

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        binding.apply {
            if (!loading.isVisible) {
                listener.cancel(id)
            }
        }
        BitcoinJs.unbindService()
    }

    interface WcSignRawDataListener {
        fun sign(id: Long, txHex: String)
        fun cancel(id: Long)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        BitcoinJs.unbindService()
    }
}