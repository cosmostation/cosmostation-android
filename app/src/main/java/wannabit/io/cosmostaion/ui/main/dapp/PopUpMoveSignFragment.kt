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
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainAptos
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatJsonString
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.databinding.FragmentWcMoveSignBinding
import wannabit.io.cosmostaion.sign.AptosJs
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import java.math.RoundingMode

class PopUpMoveSignFragment(
    var selectedChain: BaseChain?,
    private val id: Long,
    private val data: String,
    private val method: String?,
    private val wcUrl: String?,
    val listener: WcSignRawDataListener
) : BaseTxFragment() {

    private var _binding: FragmentWcMoveSignBinding? = null
    private val binding get() = _binding!!

    private var updateData: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWcMoveSignBinding.inflate(layoutInflater, container, false)
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
            listOf(signView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }

            if (method == "aptos_signMessage") {
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
            }
        }
    }

    private fun initFeeView(fee: Long) {
        binding.apply {
            selectedChain?.let { chain ->
                BaseData.getAsset(chain.apiName, chain.getGasAssetDenom())?.let { asset ->
                    dappFeeToken.text = asset.symbol
                    dappFeeTokenImg.setTokenImg(asset)

                    val coinGeckoId = BaseData.getAsset(
                        chain.apiName, chain.getGasAssetDenom()
                    )?.coinGeckoId
                    val price = BaseData.getPrice(coinGeckoId)
                    val dpFee = fee.toBigDecimal().movePointLeft(asset.decimals ?: 9)
                        .setScale(asset.decimals ?: 9, RoundingMode.DOWN)
                    val value = price.multiply(dpFee)

                    feeAmount.text = formatAmount(dpFee.toPlainString(), asset.decimals ?: 9)
                    feeValue.text = formatAssetValue(value)
                }
            }
        }
    }

    private fun parsingRequest() {
        lifecycleScope.launch(Dispatchers.IO) {
            (selectedChain as ChainAptos).apply {
                if (!AptosJs.isInitialized()) {
                    AptosJs.initialize(requireContext()).await()
                }

                aptosFetcher()?.let { fetcher ->
                    val txJsonObject = JsonParser.parseString(data).asJsonObject

                    when (method) {
                        "aptos_signMessage" -> {
                            val messageResult =
                                fetcher.signAptosMessage(AptosJs, txJsonObject, wcUrl)
                            updateData = messageResult.toString()

                            withContext(Dispatchers.Main) {
                                binding.apply {
                                    loading.visibility = View.GONE
                                    btnConfirm.isEnabled = true
                                    signData.text = txJsonObject["message"].asString
                                }
                            }
                        }

                        "aptos_signTransaction" -> {
                            val serializedTxHex = txJsonObject["serializedTxHex"].asString

                            val originalTx = fetcher.originalTx(AptosJs, serializedTxHex)
                            val originalTxJsonObject =
                                JsonParser.parseString(originalTx).asJsonObject
                            val rawTransaction = originalTxJsonObject["rawTransaction"].asJsonObject
                            val gasPrice =
                                rawTransaction["gas_unit_price"].asString.replace("n", "").toLong()
                            val maxGasAmount =
                                rawTransaction["max_gas_amount"].asString.replace("n", "").toLong()
                            val feeAmount = gasPrice * maxGasAmount

                            val signTx = fetcher.signAptosTx(AptosJs, serializedTxHex)
                            updateData = signTx

                            withContext(Dispatchers.Main) {
                                binding.apply {
                                    initFeeView(feeAmount)
                                    loading.visibility = View.GONE
                                    btnConfirm.isEnabled = true
                                    signData.text = formatJsonString(originalTx.toString())
                                }
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
                    listener.sign(id, updateData.toString())
                    dismiss()
                }
            }
        }
    }

    interface WcSignRawDataListener {
        fun sign(
            id: Long,
            data: String
        )

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