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
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainSolana
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatJsonString
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.databinding.FragmentSolanaSignBinding
import wannabit.io.cosmostaion.sign.SolanaJs
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class PopUpSolanaSignFragment(
    var selectedChain: BaseChain?,
    private val id: Long,
    private val data: String,
    private val method: String?,
    val listener: WcSignRawDataListener
) : BaseTxFragment() {

    private var _binding: FragmentSolanaSignBinding? = null
    private val binding get() = _binding!!

    private var updateData: String? = null
    private var signature: String? = null
    private var allData: MutableList<String>? = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSolanaSignBinding.inflate(layoutInflater, container, false)
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
            listOf(signView, feeView, swapSignView, changeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }

            if (method == "solana_signMessage") {
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

    private fun initChangedSolView(expectedAmount: String) {
        binding.apply {
            selectedChain?.let { chain ->
                BaseData.getAsset(chain.apiName, chain.getGasAssetDenom())?.let { asset ->
                    val coinGeckoId = BaseData.getAsset(
                        chain.apiName, chain.getGasAssetDenom()
                    )?.coinGeckoId
                    val price = BaseData.getPrice(coinGeckoId)
                    val dpSolAmount = expectedAmount.toBigDecimal()
                        .setScale(asset.decimals ?: 9, RoundingMode.DOWN)
                    val value = price.multiply(dpSolAmount).abs()

                    solAmount.text = formatAmount(dpSolAmount.toPlainString(), asset.decimals ?: 9)
                    solDenom.text = asset.symbol
                    solValue.text = formatAssetValue(value)
                }
            }
        }
    }

    private fun initFeeView(baseFee: Long, isChanged: Boolean) {
        binding.apply {
            selectedChain?.let { chain ->
                BaseData.getAsset(chain.apiName, chain.getGasAssetDenom())?.let { asset ->
                    val coinGeckoId = BaseData.getAsset(
                        chain.apiName, chain.getGasAssetDenom()
                    )?.coinGeckoId
                    val price = BaseData.getPrice(coinGeckoId)
                    val fee = baseFee.toBigDecimal()
                    val dpFee =
                        fee.movePointLeft(asset.decimals ?: 9)
                            .setScale(asset.decimals ?: 9, RoundingMode.DOWN)
                    val value = price.multiply(dpFee)

                    if (isChanged) {
                        txFeeAmount.text = formatAmount(dpFee.toPlainString(), asset.decimals ?: 9)
                        txFeeValue.text = formatAssetValue(value)
                        txFeeDenom.text = asset.symbol

                    } else {
                        dappFeeTokenImg.setTokenImg(asset)
                        dappFeeToken.text = chain.getGasAssetDenom().uppercase()

                        feeAmount.text = formatAmount(dpFee.toPlainString(), asset.decimals ?: 9)
                        feeValue.text = formatAssetValue(value)
                    }
                }
            }
        }
    }

    private fun parsingRequest() {
        lifecycleScope.launch(Dispatchers.IO) {
            (selectedChain as ChainSolana).apply {
                if (!SolanaJs.isInitialized()) {
                    SolanaJs.initialize(requireContext()).await()
                }

                solanaFetcher()?.let { fetcher ->
                    when (method) {
                        "solana_signMessage" -> {
                            val txJsonObject = JsonParser.parseString(data).asJsonObject
                            val message = txJsonObject["message"].asString

                            val parseMessage = fetcher.parseMessage(SolanaJs, message)

                            val serializedSignMessageJsonData =
                                fetcher.signMessage(SolanaJs, message)

                            updateData = serializedSignMessageJsonData["publicKey"].asString
                            signature = serializedSignMessageJsonData["signature"].asString

                            withContext(Dispatchers.Main) {
                                binding.apply {
                                    btnConfirm.isEnabled = true
                                    loading.visibility = View.GONE
                                    signData.text = parseMessage
                                }
                            }
                        }

                        "solana_signTransaction", "solana_signAllTransactions" -> {
                            val parsingResult = mutableListOf<String>()
                            val dataResult = mutableListOf<String>()
                            var viewData = ""
                            var totalExpectedSolAmount = 0.0
                            var totalFee: Long = 0

                            val txJsonArray = JsonParser.parseString(data).asJsonArray
                            txJsonArray.forEach { json ->
                                val serializedTx = json.asJsonObject["serializedTx"].asString
                                val parseInstruction =
                                    fetcher.parseInstructionsFromTx(SolanaJs, serializedTx)
                                parsingResult += parseInstruction.toString()

                                viewData = if (parsingResult.size > 1) {
                                    buildString {
                                        parsingResult.forEachIndexed { idx, raw ->
                                            if (idx > 0) appendLine("\n")
                                            appendLine("Transaction #${idx + 1}")
                                            appendLine(formatJsonString(raw))
                                        }
                                    }
                                } else {
                                    formatJsonString(parsingResult.toString())
                                }

                                val accounts = fetcher.accountsToTrack(SolanaJs, serializedTx)
                                val serializedTxMessage =
                                    fetcher.serializedTxMessageFromTx(SolanaJs, serializedTx)
                                val baseFee = fetcher.baseFee(serializedTxMessage)
                                totalFee += baseFee

                                dataResult += fetcher.signTransaction(SolanaJs, serializedTx)
                                    ?: ""

                                if (!accounts.isNullOrEmpty()) {
                                    val typeToken = object : TypeToken<List<String>>() {}.type
                                    val accountList: List<String> =
                                        Gson().fromJson(accounts, typeToken)

                                    val simulateValue =
                                        fetcher.simulateValue(serializedTx, accountList)

                                    if (simulateValue["err"].isJsonNull) {
                                        val multiAccountsValue =
                                            fetcher.multiAccountsValue(accountList)
                                        val dpMultiAccounts =
                                            GsonBuilder().setPrettyPrinting().create()
                                                .toJson(multiAccountsValue)

                                        val changesData = fetcher.analyzeTokenChanges(
                                            SolanaJs,
                                            accounts,
                                            dpMultiAccounts,
                                            simulateValue
                                        )
                                        val solChangeAmount =
                                            JsonParser.parseString(changesData).asJsonArray[0].asJsonObject["amount"].asDouble
                                        totalExpectedSolAmount += solChangeAmount
                                    }
                                }
                            }
                            allData = dataResult

                            withContext(Dispatchers.Main) {
                                binding.apply {
                                    if (totalExpectedSolAmount.toBigDecimal().abs() > BigDecimal.ZERO) {
                                        btnConfirm.isEnabled = true
                                        loading.visibility = View.GONE
                                        signView.visibility = View.GONE
                                        feeView.visibility = View.GONE
                                        swapSignView.visibility = View.VISIBLE
                                        changeView.visibility = View.VISIBLE

                                        swapSignData.text = viewData
                                        initChangedSolView(totalExpectedSolAmount.toString())
                                        initFeeView(totalFee, true)

                                    } else {
                                        btnConfirm.isEnabled = true
                                        loading.visibility = View.GONE
                                        signView.visibility = View.VISIBLE
                                        feeView.visibility = View.VISIBLE
                                        swapSignView.visibility = View.GONE
                                        changeView.visibility = View.GONE

                                        signData.text = viewData
                                        initFeeView(totalFee, false)
                                    }
                                }
                            }
                        }

                        "solana_signAndSendTransaction" -> {
                            val txJsonObject = JsonParser.parseString(data).asJsonObject
                            val serializedTx = txJsonObject["serializedTx"].asString

                            val parseInstruction =
                                fetcher.parseInstructionsFromTx(SolanaJs, serializedTx)

                            val accounts = fetcher.accountsToTrack(SolanaJs, serializedTx)
                            val serializedTxMessage =
                                fetcher.serializedTxMessageFromTx(SolanaJs, serializedTx)
                            val baseFee = fetcher.baseFee(serializedTxMessage)

                            if (!accounts.isNullOrEmpty()) {
                                val typeToken = object : TypeToken<List<String>>() {}.type
                                val accountList: List<String> =
                                    Gson().fromJson(accounts, typeToken)

                                val simulateValue =
                                    fetcher.simulateValue(serializedTx, accountList)

                                if (simulateValue["err"].isJsonNull) {
                                    val multiAccountsValue =
                                        fetcher.multiAccountsValue(accountList)
                                    val dpMultiAccounts =
                                        GsonBuilder().setPrettyPrinting().create()
                                            .toJson(multiAccountsValue)

                                    val changesData = fetcher.analyzeTokenChanges(
                                        SolanaJs,
                                        accounts,
                                        dpMultiAccounts,
                                        simulateValue
                                    )
                                    val solChangeAmount =
                                        JsonParser.parseString(changesData).asJsonArray[0].asJsonObject["amount"].asDouble

                                    withContext(Dispatchers.Main) {
                                        binding.apply {
                                            btnConfirm.isEnabled = true
                                            loading.visibility = View.GONE
                                            signView.visibility = View.GONE
                                            feeView.visibility = View.GONE
                                            swapSignView.visibility = View.VISIBLE
                                            changeView.visibility = View.VISIBLE

                                            swapSignData.text =
                                                formatJsonString(parseInstruction.toString())
                                            initChangedSolView(solChangeAmount.toString())
                                            initFeeView(baseFee, true)
                                        }
                                    }

                                } else {
                                    withContext(Dispatchers.Main) {
                                        binding.apply {
                                            btnConfirm.isEnabled = true
                                            loading.visibility = View.GONE
                                            signView.visibility = View.VISIBLE
                                            feeView.visibility = View.VISIBLE
                                            swapSignView.visibility = View.GONE
                                            changeView.visibility = View.GONE

                                            signData.text =
                                                formatJsonString(parseInstruction.toString())
                                            initFeeView(baseFee, false)
                                        }
                                    }
                                }
                            }
                        }

                        else -> {}
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
                    listener.sign(id, updateData.toString(), signature.toString(), allData)
                    dismiss()
                }
            }
        }
    }

    interface WcSignRawDataListener {
        fun sign(
            id: Long,
            data: String,
            signature: String,
            allData: MutableList<String>? = mutableListOf()
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
