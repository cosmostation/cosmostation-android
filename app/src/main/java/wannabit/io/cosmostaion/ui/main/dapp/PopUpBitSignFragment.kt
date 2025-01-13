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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setImg
import wannabit.io.cosmostaion.databinding.FragmentBitSignBinding
import wannabit.io.cosmostaion.sign.BitCoinJS
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import java.math.RoundingMode

class PopUpBitSignFragment(
    var selectedChain: BaseChain?,
    private val bitCoinJS: BitCoinJS?,
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
        parsingRequest()
        setUpObserve()
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
            dappFeeTokenImg.setImg(R.drawable.token_btc)
            dappFeeToken.text = "BIT"
        }
    }

    private fun parsingRequest() {
        lifecycleScope.launch(Dispatchers.IO) {
            val txJsonObject = JsonParser.parseString(data).asJsonObject
            (selectedChain as ChainBitCoin84).apply {
                bitToAddress = txJsonObject["to"].asString
                bitSatAmount = txJsonObject["satAmount"].asLong.toString()
                if (method == "bit_sendBitcoin") {
                    message = JSONObject(
                        mapOf(
                            "From" to selectedChain?.mainAddress,
                            "To" to bitToAddress,
                            "Amount" to bitSatAmount.toBigDecimal().movePointLeft(8)
                                .setScale(8, RoundingMode.DOWN).toPlainString()
                        )
                    ).toString(4)
                    withContext(Dispatchers.Main) {
                        txViewModel.bitTxData(this@apply)
                    }
                }
            }
        }
    }

    private fun setUpObserve() {
        txViewModel.bitTxDataResult.observe(this) { bitData ->
            (selectedChain as ChainBitCoin84).apply {
                lifecycleScope.launch(Dispatchers.IO) {
                    val bitVByteFee = btcFetcher()?.bitVBytesFee(bitData.first, "")
                    val bitFee =
                        bitData.second.toBigDecimal().multiply(bitVByteFee).movePointRight(5)
                            .setScale(0, RoundingMode.UP)

                    withContext(Dispatchers.Main) {
                        binding.apply {
                            val price = BaseData.getPrice(coinGeckoId)
                            val amount = bitFee.movePointLeft(8).setScale(8, RoundingMode.UP)
                            val value = price.multiply(amount)

                            btnConfirm.isEnabled = true
                            loading.visibility = View.GONE
                            signData.text = message
                            feeAmount.text = formatAmount(amount.toPlainString(), 8)
                            feeValue.text = formatAssetValue(value)
                        }

                        txViewModel.bitSendSimulate(
                            this@apply,
                            bitCoinJS,
                            mainAddress,
                            bitToAddress,
                            bitSatAmount,
                            btcFetcher()?.btcBalances?.subtract(bitSatAmount.toBigDecimal())
                                ?.subtract(bitFee).toString(),
                            "",
                            bitData.first
                        )
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
    }

    interface WcSignRawDataListener {
        fun sign(id: Long, txHex: String)
        fun cancel(id: Long)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}