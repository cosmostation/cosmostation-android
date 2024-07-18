package wannabit.io.cosmostaion.ui.main.dapp.option

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bitcoinj.core.ECKey
import org.web3j.crypto.Credentials
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.RawTransaction
import org.web3j.crypto.Sign
import org.web3j.crypto.TransactionEncoder
import org.web3j.crypto.WalletUtils
import org.web3j.crypto.transaction.type.TransactionType
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Numeric
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.jsonRpcResponse
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.percentile
import wannabit.io.cosmostaion.common.soft
import wannabit.io.cosmostaion.data.model.req.EstimateGasParams
import wannabit.io.cosmostaion.data.model.req.EstimateGasParamsWithValue
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.databinding.FragmentWcEvmSignBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.evm.StructuredDataEncode
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

class PopUpEvmSignFragment(
    private val selectedEvmChain: BaseChain?,
    private val id: Long,
    private val data: String,
    private val method: String?,
    val listener: WcSignRawDataListener
) : BaseTxFragment() {

    private var _binding: FragmentWcEvmSignBinding? = null
    private val binding get() = _binding!!

    private var web3j: Web3j? = null
    private var chainId: Long? = null
    private var nonce: BigInteger? = null

    private var evmTxType: TransactionType? = null

    private var evmBalance = BigDecimal.ZERO
    private var checkedGas = BigInteger.ZERO

    private var paramFrom: String? = ""
    private var paramTo: String? = ""
    private var paramDataString: String? = ""
    private var paramGas: String? = ""
    private var paramGasPrice: String? = ""
    private var paramValue: String? = ""
    private var paramMaxPriorityFeePerGas: String? = ""
    private var paramMaxFeePerGas: String? = ""
    private var paramType: String? = ""

    private var selectFeePosition = 1
    private var addedFeePosition = 1
    private var evmGasTitle: MutableList<String> = mutableListOf()
    private var evmGas: MutableList<Triple<BigInteger?, BigInteger?, BigInteger?>> = mutableListOf(
        Triple(
            BigInteger.valueOf(500000000), BigInteger.valueOf(1000000000), BigInteger.valueOf(21000)
        ), Triple(
            BigInteger.valueOf(500000000), BigInteger.valueOf(1000000000), BigInteger.valueOf(21000)
        ), Triple(
            BigInteger.valueOf(500000000), BigInteger.valueOf(1000000000), BigInteger.valueOf(21000)
        )
    )
    private var updateData: String? = null

    private var isChanged = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWcEvmSignBinding.inflate(layoutInflater, container, false)
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

            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)
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
        }
    }

    private fun parsingRequest() {
        lifecycleScope.launch(Dispatchers.IO) {
            evmGasTitle = mutableListOf(
                getString(R.string.str_low),
                getString(R.string.str_average),
                getString(R.string.str_high)
            )
            val rpcUrl = selectedEvmChain?.evmRpcFetcher?.getEvmRpc() ?: selectedEvmChain?.evmRpcURL
            web3j = Web3j.build(HttpService(rpcUrl))
            chainId = web3j?.ethChainId()?.sendAsync()?.get()?.chainId?.toLong()

            if (method == "eth_sendTransaction") {
                val txJsonObject = JsonParser.parseString(data).asJsonObject
                paramType = txJsonObject["type"]?.asString
                paramFrom = txJsonObject["from"]?.asString
                paramFrom?.let {
                    val ethGetTransactionCount = web3j?.ethGetTransactionCount(
                        it, DefaultBlockParameterName.LATEST
                    )?.sendAsync()?.get()
                    nonce = ethGetTransactionCount?.transactionCount
                }
                paramTo = txJsonObject["to"]?.asString
                paramDataString = txJsonObject["data"]?.asString
                paramGas = txJsonObject["gas"]?.asString
                paramGasPrice = txJsonObject["gasPrice"]?.asString
                paramValue = txJsonObject["value"]?.asString
                paramMaxPriorityFeePerGas = txJsonObject["maxPriorityFeePerGas"]?.asString
                paramMaxFeePerGas = txJsonObject["maxFeePerGas"]?.asString

            } else if (method == "eth_signTypedData") {
                val txJsonArray = JsonParser.parseString(data).asJsonArray
                val signTypedData = txJsonArray[1].asString
                withContext(Dispatchers.Main) {
                    if (signTypedData.contains("to") || signTypedData.contains("gas")) {
                        binding.warnMsg.text = getString(R.string.str_affect_danger_msg)
                        binding.warnMsg.setTextColor(
                            ContextCompat.getColorStateList(
                                requireContext(),
                                R.color.color_accent_red
                            )
                        )
                    } else {
                        binding.warnMsg.text = getString(R.string.str_affect_safe_msg)
                        binding.warnMsg.setTextColor(
                            ContextCompat.getColorStateList(
                                requireContext(),
                                R.color.color_accent_green
                            )
                        )
                    }
                }

            } else {
                val txJsonArray = JsonParser.parseString(data).asJsonArray
                if (txJsonArray.count() < 2) return@launch
            }
            initData()
        }
    }

    private fun initData() {
        lifecycleScope.launch(Dispatchers.IO) {
            if (method == "eth_sendTransaction") {
                val checkBalance = async { checkEvmBalance() }
                val checkGas = async { checkGasPrice() }

                checkBalance.await()
                checkGas.await()
            }
            withContext(Dispatchers.Main) {
                binding.apply {
                    loading.visibility = View.GONE
                    initView()
                }
            }
        }
    }

    private fun initView() {
        binding.apply {
            when (method) {
                "eth_signTypedData" -> {
                    dialogTitle.text = getString(R.string.str_permit_request)
                    feeView.visibility = View.INVISIBLE
                    btnConfirm.isEnabled = true
                    initSignTypeData()
                }

                "personal_sign" -> {
                    dialogTitle.text = getString(R.string.str_permit_request)
                    feeView.visibility = View.INVISIBLE
                    warnMsg.text = getString(R.string.str_affect_safe_msg)
                    warnMsg.setTextColor(
                        ContextCompat.getColorStateList(
                            requireContext(),
                            R.color.color_accent_green
                        )
                    )
                    btnConfirm.isEnabled = true
                    initPersonalDataView()
                }

                "eth_sendTransaction" -> {
                    dialogTitle.text = getString(R.string.str_tx_request)
                    warnMsg.text = getString(R.string.str_affect_danger_msg)
                    warnMsg.setTextColor(
                        ContextCompat.getColorStateList(
                            requireContext(),
                            R.color.color_accent_red
                        )
                    )
                    feeView.visibility = View.VISIBLE
                    initFee()
                    initTxData()
                }
            }
            viewLayout.visibility = View.VISIBLE
        }
        isCancelable = false
    }

    private fun initSignTypeData() {
        binding.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                val txJsonArray = JsonParser.parseString(data).asJsonArray
                val txJsonObject = Gson().fromJson(txJsonArray[1].asString, JsonObject::class.java)

                val encoder = StructuredDataEncode(Gson().toJson(txJsonObject))
                val hashStructuredData = encoder.hashStructuredData()

                val signature = Sign.signMessage(
                    hashStructuredData, ECKeyPair.create(selectedEvmChain?.privateKey), false
                )

                val r = Numeric.toHexString(signature.r)
                val s = Numeric.toHexString(signature.s)
                val v = Numeric.toHexString(signature.v)

                updateData = r + s.substring(2) + v.substring(2)

                withContext(Dispatchers.Main) {
                    signData.text = GsonBuilder().setPrettyPrinting().create()
                        .toJson(txJsonObject.asJsonObject["message"])
                }
            }
        }
    }

    private fun initPersonalDataView() {
        binding.apply {
            selectedEvmChain?.let { chain ->
                lifecycleScope.launch(Dispatchers.IO) {
                    val txJsonArray = JsonParser.parseString(data).asJsonArray
                    val firstParameter = txJsonArray[0].asString
                    val secondParameter = txJsonArray[1].asString
                    val messageBytes =
                        if (WalletUtils.isValidAddress(firstParameter) && firstParameter == selectedEvmChain.evmAddress) {
                            secondParameter.toByteArray()
                        } else {
                            firstParameter.toByteArray()
                        }
                    val messageHash = Sign.getEthereumMessageHash(messageBytes)
                    val signature =
                        Sign.signMessage(messageHash, ECKeyPair.create(chain.privateKey), false)

                    val r = Numeric.toHexString(signature.r)
                    val s = Numeric.toHexString(signature.s)
                    val v = Numeric.toHexString(signature.v)

                    updateData = r + s.substring(2) + v.substring(2)

                    withContext(Dispatchers.Main) {
                        signData.text = "Message : " + txJsonArray[1].asString
                    }
                }
            }
        }
    }

    private fun checkEvmBalance() {
        val balance =
            web3j?.ethGetBalance(selectedEvmChain?.evmAddress, DefaultBlockParameterName.LATEST)
                ?.send()
        evmBalance = balance?.balance?.toBigDecimal()
    }

    private fun checkGasPrice() {
        val ethGasRequest = try {
            JsonRpcRequest(
                method = "eth_estimateGas", params = listOf(
                    EstimateGasParamsWithValue(
                        paramFrom, paramTo, paramDataString, paramValue
                    )
                )
            )

        } catch (e: Exception) {
            JsonRpcRequest(
                method = "eth_estimateGas", params = listOf(
                    EstimateGasParams(
                        paramFrom, paramTo, paramDataString
                    )
                )
            )
        }
        selectedEvmChain?.evmRpcFetcher?.getEvmRpc() ?: selectedEvmChain?.evmRpcURL?.let { rpcUrl ->
            val ethGasResponse =
                jsonRpcResponse(rpcUrl, ethGasRequest)
            checkedGas = if (ethGasResponse.isSuccessful) {
                val gasJsonObject =
                    Gson().fromJson(ethGasResponse.body?.string(), JsonObject::class.java)
                val gasAmount = BigInteger(
                    gasJsonObject.asJsonObject["result"].asString.removePrefix("0x"), 16
                )
                gasAmount.multiply(selectedEvmChain.evmGasMultiply() ?: BigInteger("13")).divide(
                    BigInteger("10")
                )

            } else {
                BigInteger.valueOf(21000L)
            }

            val ethFeeHistoryRequest = JsonRpcRequest(
                method = "eth_feeHistory", params = listOf(
                    20, "latest", listOf(25, 50, 75)
                )
            )
            val ethFeeHistoryResponse =
                jsonRpcResponse(rpcUrl, ethFeeHistoryRequest)
            val historyJsonObject = Gson().fromJson(
                ethFeeHistoryResponse.body?.string(), JsonObject::class.java
            )

            val feeHistoryFeePerGas = try {
                historyJsonObject.asJsonObject["result"].asJsonObject["baseFeePerGas"].asJsonArray
            } catch (e: Exception) {
                mutableListOf()
            }

            val suggestGasValues = try {
                feeHistoryFeePerGas.map {
                    BigInteger(
                        it.asString.removePrefix("0x"), 16
                    )
                }.toMutableList()
            } catch (e: Exception) {
                mutableListOf()
            }

            if (suggestGasValues.isNotEmpty()) {
                val suggestBaseFee = listOf(25.0, 50.0, 75.0).map {
                    suggestGasValues.percentile(it)
                }

                val reward =
                    historyJsonObject.asJsonObject["result"].asJsonObject["reward"].asJsonArray
                val rearrangedArray: MutableList<MutableList<BigInteger>> = ArrayList()
                reward.forEach {
                    val percentiles = it.asJsonArray.map { percentile ->
                        BigInteger(
                            percentile.asString.removePrefix("0x"), 16
                        )
                    }.toMutableList()

                    percentiles.forEachIndexed { index, percentile ->
                        if (rearrangedArray.size <= index) {
                            rearrangedArray.add(mutableListOf(percentile))
                        } else {
                            rearrangedArray[index].add(percentile)
                        }
                    }
                }

                val suggestTipValue = soft(rearrangedArray)
                if (selectedEvmChain.evmSupportEip1559()) {
                    for (i in 0 until 3) {
                        val baseFee = suggestBaseFee[i]
                        val tip = suggestTipValue[i]
                        evmGas[i] = Triple(baseFee, tip, checkedGas)
                    }

                } else {
                    for (i in 0 until 3) {
                        val baseFee =
                            if (suggestBaseFee[i]!! > BigInteger.valueOf(500000000L)) suggestBaseFee[i] else BigInteger.valueOf(
                                500000000L
                            )
                        val tip =
                            if (suggestTipValue[i] > BigInteger.valueOf(1000000000L)) suggestTipValue[i] else BigInteger.valueOf(
                                1000000000L
                            )
                        evmGas[i] = Triple(baseFee, tip, checkedGas)
                    }
                }

                if (paramMaxFeePerGas != null && paramMaxPriorityFeePerGas != null) {
                    val maxFeePerGas = BigInteger(paramMaxFeePerGas!!.removePrefix("0x"), 16)
                    val maxPriorityFeePerGas = BigInteger(paramMaxPriorityFeePerGas!!.removePrefix("0x"), 16)
                    val baseFee = maxFeePerGas.subtract(maxPriorityFeePerGas)
                    evmGas.add(Triple(baseFee, maxPriorityFeePerGas, paramGas?.removePrefix("0x")?.let { BigInteger(it, 16) } ?: checkedGas))
                    evmGasTitle.add("From dapp")
                    addedFeePosition = 3
                }
                evmTxType = TransactionType.EIP1559

            } else {
                web3j?.ethGasPrice()?.send()?.gasPrice?.let { gasPrice ->
                    evmGas[0] = Triple(gasPrice, BigInteger.ZERO, checkedGas)
                    evmGas[1] = Triple(gasPrice.multiply(BigInteger.valueOf(12)).divide(BigInteger.valueOf(10)), BigInteger.ZERO, checkedGas)
                    evmGas[2] = Triple(gasPrice.multiply(BigInteger.valueOf(20)).divide(BigInteger.valueOf(10)), BigInteger.ZERO, checkedGas)
                }

                if (paramGas != null) {
                    evmGas.add(Triple(BigInteger(paramGasPrice!!.removePrefix("0x"), 16),
                        BigInteger.ZERO,
                        paramGas?.removePrefix("0x")?.let { BigInteger(it, 16) } ?: checkedGas))
                    evmGasTitle.add(getString(R.string.str_origin))
                    addedFeePosition = 3
                }
                evmTxType = TransactionType.LEGACY
            }
        }
    }

    private fun initTxData() {
        binding.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                val txJsonObject = JsonParser.parseString(data).asJsonObject
                withContext(Dispatchers.Main) {
                    signData.text = GsonBuilder().setPrettyPrinting().create().toJson(txJsonObject)
                }
            }
        }
    }

    private fun initFee() {
        binding.apply {
            for (i in evmGasTitle.indices) {
                val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                feeSegment.addView(
                    segmentView.root,
                    i,
                    LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                )
                segmentView.btnTitle.text = evmGasTitle[i]
            }
            feeSegment.setPosition(addedFeePosition, false)
            selectFeePosition = addedFeePosition
            feeDenom.text = selectedEvmChain?.coinSymbol
            updateFeeView()
        }
    }

    private fun updateFeeView() {
        binding.apply {
            val feePrice = BaseData.getPrice(selectedEvmChain?.coinGeckoId)
            val totalGasPrice =
                evmGas[selectFeePosition].first?.add(evmGas[selectFeePosition].second!!)
            val feeAmountBigInt = totalGasPrice?.multiply(evmGas[selectFeePosition].third!!)
            val feeDpAmount =
                feeAmountBigInt?.toBigDecimal()?.movePointLeft(18)?.setScale(18, RoundingMode.DOWN)
            val feeDpValue = feePrice.multiply(feeDpAmount).setScale(6, RoundingMode.DOWN)
            feeAmount.text = formatAmount(feeDpAmount.toString(), 18)
            feeValue.text = formatAssetValue(feeDpValue)

            val value =
                paramValue?.removePrefix("0x")?.let { BigInteger(it, 16) } ?: BigInteger.ZERO
            feeAmountBigInt?.let { value.add(it) }?.let { totalSpend ->
                if (totalSpend > evmBalance.toBigInteger()) {
                    requireActivity().makeToast(R.string.error_not_enough_fee)
                    btnConfirm.isEnabled = false
                } else {
                    btnConfirm.isEnabled = true
                }
                isChanged = true
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            feeSegment.setOnPositionChangedListener { position ->
                if (isChanged) {
                    selectFeePosition = position
                    updateFeeView()
                }
            }

            btnCancel.setOnClickListener {
                if (!loading.isVisible) {
                    listener.cancel(id)
                    dismiss()
                }
            }

            btnConfirm.setOnClickListener {
                if (!loading.isVisible && btnConfirm.isEnabled) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        selectedEvmChain?.let { chain ->
                            val ecKey = ECKey.fromPrivate(chain.privateKey)
                            val credentials: Credentials = Credentials.create(ecKey.privateKeyAsHex)

                            if (method == "eth_sendTransaction") {
                                val evmGas = evmGas[selectFeePosition]
                                if (evmTxType == TransactionType.EIP1559) {
                                    val rawTransaction = try {
                                        RawTransaction.createTransaction(chainId!!,
                                            nonce,
                                            evmGas.third,
                                            paramTo,
                                            BigInteger(paramValue!!.removePrefix("0x"), 16),
                                            paramDataString,
                                            evmGas.second,
                                            evmGas.second?.let { tip -> evmGas.first?.add(tip) })

                                    } catch (e: Exception) {
                                        RawTransaction.createTransaction(chainId!!,
                                            nonce,
                                            evmGas.third,
                                            paramTo,
                                            BigInteger.ZERO,
                                            paramDataString,
                                            evmGas.second,
                                            evmGas.second?.let { tip -> evmGas.first?.add(tip) })
                                    }

                                    val signedMessage = TransactionEncoder.signMessage(
                                        rawTransaction, chainId!!, credentials
                                    )
                                    val hexValue = Numeric.toHexString(signedMessage)
                                    updateData = hexValue

                                } else {
                                    val rawTransaction = try {
                                        RawTransaction.createTransaction(
                                            nonce,
                                            evmGas.first,
                                            evmGas.third,
                                            paramTo,
                                            BigInteger(paramValue!!.removePrefix("0x"), 16),
                                            paramDataString
                                        )

                                    } catch (e: Exception) {
                                        RawTransaction.createTransaction(
                                            nonce,
                                            evmGas.first,
                                            evmGas.third,
                                            paramTo,
                                            BigInteger.ZERO,
                                            paramDataString
                                        )
                                    }

                                    val signedMessage = TransactionEncoder.signMessage(
                                        rawTransaction, chainId!!, credentials
                                    )
                                    val hexValue = Numeric.toHexString(signedMessage)
                                    updateData = hexValue
                                }
                            }
                        }

                        withContext(Dispatchers.Main) {
                            listener.sign(id, updateData.toString())
                            dismiss()
                        }
                    }
                }
            }
        }
    }

    interface WcSignRawDataListener {
        fun sign(id: Long, data: String)
        fun cancel(id: Long)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}