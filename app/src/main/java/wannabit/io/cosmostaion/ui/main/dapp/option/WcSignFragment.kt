package wannabit.io.cosmostaion.ui.main.dapp.option

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.i2p.crypto.eddsa.Utils
import org.bitcoinj.core.ECKey
import org.web3j.crypto.Credentials
import org.web3j.crypto.RawTransaction
import org.web3j.crypto.Sign
import org.web3j.crypto.StructuredDataEncoder
import org.web3j.crypto.TransactionEncoder
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.response.EthGetTransactionCount
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Numeric
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.chain.evmClass.ChainKavaEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.jsonRpcResponse
import wannabit.io.cosmostaion.common.percentile
import wannabit.io.cosmostaion.common.soft
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.req.EstimateGasParams
import wannabit.io.cosmostaion.data.model.req.EstimateGasParamsWithValue
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.databinding.FragmentWcSignBinding
import wannabit.io.cosmostaion.ui.main.dapp.EvmMethod
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

class WcSignFragment(
    private val selectedChain: CosmosLine?,
    private val id: Long,
    private val data: String,
    private val url: String?,
    val listener: WcSignRawDataListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentWcSignBinding? = null
    private val binding get() = _binding!!

    private var updateData: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWcSignBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            signView.setBackgroundResource(R.drawable.cell_bg)
            if (id == EvmMethod.SIGN.type) {
                initEvmDataView(data)
            } else if (id == EvmMethod.PERMIT.type) {
                initEvmPermitData(data)
            } else if (id == EvmMethod.PERSONAL.type) {
                initEvmPersonalData(data)
            } else {
                if (selectedChain is ChainKavaEvm || selectedChain is ChainKava459) {
                    initWc1DataView(data)
                } else {
                    try {
                        initAminoDataView(data)
                    } catch (e: Exception) {
                        initInjectionDataView(data)
                    }
                }
            }
            isCancelable = false
        }
    }

    private fun initAminoDataView(data: String) {
        binding.apply {
            selectedChain?.let { chain ->
                lifecycleScope.launch(Dispatchers.IO) {
                    val txJsonObject = JsonParser.parseString(data).asJsonObject
                    val updateJsonData = updateFeeInfoInAminoMessage(txJsonObject)
                    val fee = updateJsonData.get("fee").asJsonObject
                    val feeAmount =
                        fee.get("amount").asJsonArray.get(0).asJsonObject.get("amount").asString
                    val feeDenom =
                        fee.get("amount").asJsonArray.get(0).asJsonObject.get("denom").asString

                    BaseData.getAsset(chain.apiName, feeDenom ?: "")?.let { asset ->
                        val dpFeeAmount =
                            feeAmount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                                .setScale(asset.decimals ?: 6, RoundingMode.DOWN)

                        withContext(Dispatchers.Main) {
                            signData.text =
                                GsonBuilder().setPrettyPrinting().create().toJson(updateJsonData)
                            dappUrl.text = url
                            dappAddress.text = chain.address
                            dappFeeAmount.text = formatAmount(
                                dpFeeAmount.toPlainString(), asset.decimals ?: 6
                            )
                            dappFeeDenom.text = asset.symbol
                        }
                    }
                    updateData = updateJsonData.toString()
                }
            }
        }
    }

    private fun initWc1DataView(data: String) {
        binding.apply {
            selectedChain?.let { chain ->
                val txJsonObject = JsonParser.parseString(data).asJsonObject
                signData.text = GsonBuilder().setPrettyPrinting().create().toJson(txJsonObject)
                dappUrl.text = url
                dappAddress.text = chain.address

                val fee = txJsonObject.get("fee").asJsonObject
                val amounts = fee.get("amounts").asJsonArray
                val feeAmount = amounts.get(0).asJsonObject.get("amount").asString
                val feeDenom = amounts.get(0).asJsonObject.get("denom").asString

                BaseData.getAsset(chain.apiName, feeDenom ?: "")?.let { asset ->
                    val dpFeeAmount = feeAmount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                        .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                    dappFeeAmount.text = formatAmount(
                        dpFeeAmount.toPlainString(), asset.decimals ?: 6
                    )
                    dappFeeDenom.text = asset.symbol
                }
                updateData = data
            }
        }
    }

    private fun initInjectionDataView(data: String) {
        binding.apply {
            signView.setBackgroundResource(R.drawable.cell_bg)
            loading.visibility = View.VISIBLE
            selectedChain?.let { chain ->
                lifecycleScope.launch(Dispatchers.IO) {
                    val txJsonObject = JsonParser.parseString(data).asJsonObject
                    val updateJsonData = updateFeeInfoInDirectMessage(txJsonObject)
                    val authInfo =
                        TxProto.AuthInfo.parseFrom(Utils.hexToBytes(updateJsonData["auth_info_bytes"].asString))

                    BaseData.getAsset(chain.apiName, authInfo.fee.getAmount(0).denom ?: "")
                        ?.let { asset ->
                            val dpFeeAmount = authInfo.fee.getAmount(0).amount.toBigDecimal()
                                .movePointLeft(asset.decimals ?: 6)
                                .setScale(asset.decimals ?: 6, RoundingMode.DOWN)

                            withContext(Dispatchers.Main) {
                                loading.visibility = View.GONE
                                signData.text =
                                    GsonBuilder().setPrettyPrinting().create()
                                        .toJson(updateJsonData)
                                dappUrl.text = url
                                dappAddress.text = chain.address
                                dappFeeAmount.text = formatAmount(
                                    dpFeeAmount.toPlainString(), asset.decimals ?: 6
                                )
                                dappFeeDenom.text = asset.symbol
                            }
                        }
                    updateData = updateJsonData.toString()
                }
            }
        }
    }

    private fun initEvmDataView(data: String) {
        binding.apply {
            signView.setBackgroundResource(R.drawable.cell_bg)
            selectedChain?.let { chain ->
                loading.visibility = View.VISIBLE
                lifecycleScope.launch(Dispatchers.IO) {
                    val feeAmount: BigDecimal?
                    val txJsonObject = JsonParser.parseString(data).asJsonObject
                    val ecKey = ECKey.fromPrivate(selectedChain.privateKey)
                    val rpcUrl = if (selectedChain is EthereumLine) {
                        selectedChain.getEvmRpc()
                    } else {
                        selectedChain.rpcUrl
                    }
                    val web3j = Web3j.build(HttpService(rpcUrl))
                    val credentials: Credentials = Credentials.create(ecKey.privateKeyAsHex)

                    val ethGetTransactionCount: EthGetTransactionCount =
                        web3j.ethGetTransactionCount(
                            credentials.address, DefaultBlockParameterName.LATEST
                        ).sendAsync().get()

                    val chainID = web3j.ethChainId().sendAsync().get().chainId.toLong()
                    val nonce = ethGetTransactionCount.transactionCount

                    // json data
                    val to = txJsonObject["to"].asString
                    val dataString = txJsonObject["data"].asString
                    val maxPriorityFeePerGas =
                        txJsonObject["maxPriorityFeePerGas"] ?: null // be or not be
                    val maxFeePerGas = txJsonObject["maxFeePerGas"] ?: null // be or not be

                    val ethGasRequest = try {
                        // with value
                        JsonRpcRequest(
                            method = "eth_estimateGas", params = listOf(
                                EstimateGasParamsWithValue(
                                    selectedChain.address,
                                    to,
                                    dataString,
                                    txJsonObject["value"].asString
                                )
                            )
                        )

                    } catch (e: Exception) {
                        // not value
                        JsonRpcRequest(
                            method = "eth_estimateGas", params = listOf(
                                EstimateGasParams(selectedChain.address, to, dataString)
                            )
                        )
                    }
                    val ethGasResponse = jsonRpcResponse(rpcUrl, ethGasRequest)
                    val gasJsonObject =
                        Gson().fromJson(ethGasResponse.body?.string(), JsonObject::class.java)
                    val gasLimit = BigInteger(
                        gasJsonObject.asJsonObject["result"].asString.removePrefix("0x"), 16
                    )

                    val ethFeeHistoryRequest = JsonRpcRequest(
                        method = "eth_feeHistory", params = listOf(
                            20, "pending", listOf(25, 50, 75)
                        )
                    )
                    val ethFeeHistoryResponse = jsonRpcResponse(rpcUrl, ethFeeHistoryRequest)
                    if (ethFeeHistoryResponse.isSuccessful) {
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
                            val tip = if (suggestTipValue[1] < BigInteger.valueOf(1000000000L)) {
                                BigInteger.valueOf(1000000000L)
                            } else {
                                suggestTipValue[1]
                            }

                            val totalPerGas =
                                if (suggestBaseFee[1] == null || suggestBaseFee[1]!! < BigInteger.valueOf(
                                        500000000L
                                    )
                                ) {
                                    500000000L + tip.toLong()
                                } else {
                                    suggestBaseFee[1]!!.toLong() + tip.toLong()
                                }

                            val rawTransaction = try {
                                // with value
                                val value = BigInteger(
                                    txJsonObject["value"].asString.removePrefix("0x"), 16
                                )

                                // maxFeePerGas & maxPriorityFeePerGas be
                                if (maxFeePerGas != null && maxPriorityFeePerGas != null) {
                                    val maxPriorityFeePerGasBigInteger = BigInteger(
                                        maxPriorityFeePerGas.asString.removePrefix("0x"), 16
                                    )
                                    val maxFeePerGasBigInteger =
                                        BigInteger(maxFeePerGas.asString.removePrefix("0x"), 16)
                                    RawTransaction.createTransaction(
                                        chainID,
                                        nonce,
                                        gasLimit,
                                        to,
                                        value,
                                        dataString,
                                        maxPriorityFeePerGasBigInteger,
                                        maxFeePerGasBigInteger
                                    )

                                } else {
                                    RawTransaction.createTransaction(
                                        chainID,
                                        nonce,
                                        gasLimit,
                                        to,
                                        value,
                                        dataString,
                                        tip,
                                        totalPerGas.toBigInteger()
                                    )
                                }

                            } catch (e: Exception) {
                                // not value
                                RawTransaction.createTransaction(
                                    chainID,
                                    nonce,
                                    gasLimit,
                                    to,
                                    BigInteger.ZERO,
                                    dataString,
                                    tip,
                                    totalPerGas.toBigInteger()
                                )
                            }

                            val signedMessage = TransactionEncoder.signMessage(
                                rawTransaction, chainID, credentials
                            )
                            val hexValue = Numeric.toHexString(signedMessage)

                            feeAmount = if (maxFeePerGas != null) {
                                gasLimit.multiply(
                                    BigInteger(
                                        maxFeePerGas.asString.removePrefix("0x"),
                                        16
                                    )
                                ).toBigDecimal()
                            } else {
                                gasLimit.multiply(totalPerGas.toBigInteger()).toBigDecimal()
                            }
                            updateData = hexValue

                        } else {
                            // legacy
                            val rawTransaction = try {
                                // with value
                                val value = BigInteger(
                                    txJsonObject["value"].asString.removePrefix("0x"), 16
                                )
                                RawTransaction.createTransaction(
                                    nonce,
                                    web3j.ethGasPrice().send().gasPrice,
                                    gasLimit,
                                    to,
                                    value,
                                    dataString
                                )

                            } catch (e: Exception) {
                                // no value
                                RawTransaction.createTransaction(
                                    nonce,
                                    web3j.ethGasPrice().send().gasPrice,
                                    gasLimit,
                                    to,
                                    BigInteger.ZERO,
                                    dataString
                                )
                            }
                            val signedMessage = TransactionEncoder.signMessage(
                                rawTransaction, chainID, credentials
                            )
                            val hexValue = Numeric.toHexString(signedMessage)
                            feeAmount =
                                gasLimit.multiply(web3j.ethGasPrice().sendAsync().get().gasPrice)
                                    .toBigDecimal()
                            updateData = hexValue
                        }

                        withContext(Dispatchers.Main) {
                            loading.visibility = View.GONE
                            signData.text =
                                GsonBuilder().setPrettyPrinting().create().toJson(txJsonObject)
                            dappUrl.text = url
                            dappAddress.text = if (chain.address?.startsWith("0x") == true) {
                                chain.address
                            } else {
                                ByteUtils.convertBech32ToEvm(chain.address)
                            }
                            feeAmount.movePointLeft(18)?.setScale(18, RoundingMode.DOWN)
                                ?.let { dpAmount ->
                                    dappFeeAmount.text = formatAmount(
                                        dpAmount.toPlainString(), 18
                                    )
                                    dappFeeDenom.text = (selectedChain as EthereumLine).coinSymbol
                                }
                        }
                    }
                }
            }
        }
    }

    // token permit view
    // signTypedData
    private fun initEvmPermitData(data: String) {
        binding.apply {
            signView.setBackgroundResource(R.drawable.cell_bg)
            dataView.visibility = View.GONE
            lifecycleScope.launch(Dispatchers.IO) {
                val txJsonArray = JsonParser.parseString(data).asJsonArray
                val txJsonObject = Gson().fromJson(txJsonArray[1].asString, JsonObject::class.java)
                val salt = txJsonObject["domain"].asJsonObject["salt"] ?: null
                val encoder = if (salt != null) {
                    val saltJson = JsonObject().apply {
                        addProperty("type", "Buffer")
                        val dataArray = JsonArray()
                        dataArray.add(48)
                        add("data", dataArray)
                    }
                    txJsonObject["domain"].asJsonObject.add("salt", saltJson)
                    StructuredDataEncoder(Gson().toJson(txJsonObject).trimIndent())
                } else {
                    StructuredDataEncoder(Gson().toJson(txJsonObject).trimIndent())
                }
                val encodedMessage = encoder.hashStructuredData()

                val ecKey = ECKey.fromPrivate(selectedChain?.privateKey)
                val credentials: Credentials = Credentials.create(ecKey.privateKeyAsHex)
                val signature = Sign.signMessage(encodedMessage, credentials.ecKeyPair, false)

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

    private fun initEvmPersonalData(data: String) {
        binding.apply {
            signView.setBackgroundResource(R.drawable.cell_bg)
            feeView.visibility = View.GONE
            selectedChain?.let { chain ->
                lifecycleScope.launch(Dispatchers.IO) {
                    val txJsonObject = JsonParser.parseString(data).asJsonArray
                    val firstParameter = txJsonObject[0].asString
                    val secondParameter = txJsonObject[1].asString
                    val currentAddress = if (chain.address?.startsWith("0x") == true) {
                        chain.address
                    } else {
                        ByteUtils.convertBech32ToEvm(chain.address)
                    }
                    val messageBytes = if (WalletUtils.isValidAddress(firstParameter) && firstParameter == currentAddress) {
                        secondParameter.toByteArray()
                    } else {
                        firstParameter.toByteArray()
                    }
                    val messageHash = Sign.getEthereumMessageHash(messageBytes)

                    val ecKey = ECKey.fromPrivate(chain.privateKey)
                    val credentials: Credentials = Credentials.create(ecKey.privateKeyAsHex)
                    val signature = Sign.signMessage(messageHash, credentials.ecKeyPair, false)

                    val r = Numeric.toHexString(signature.r)
                    val s = Numeric.toHexString(signature.s)
                    val v = Numeric.toHexString(signature.v)

                    updateData = r + s.substring(2) + v.substring(2)

                    withContext(Dispatchers.Main) {
                        signData.text = "Message : " + txJsonObject[1].asString
                        dappUrl.text = url
                        dappAddress.text = if (chain.address?.startsWith("0x") == true) {
                            chain.address
                        } else {
                            ByteUtils.convertBech32ToEvm(chain.address)
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
                if (!loading.isVisible) {
                    listener.sign(id, updateData.toString())
                    dismiss()
                }
            }
        }
    }

    private fun updateFeeInfoInAminoMessage(txJsonObject: JsonObject): JsonObject {
        val txJsonSignDoc =
            txJsonObject.getAsJsonObject("signDoc") ?: txJsonObject.getAsJsonObject("doc")
        val isEditFee: Boolean = txJsonObject.getAsJsonPrimitive("isEditFee")?.asBoolean ?: true
        val fee = txJsonSignDoc.get("fee").asJsonObject
        val gas = fee.get("gas").asString
        val amounts = fee.get("amount").asJsonArray

        if (isEditFee || amounts.size() <= 0) {
            val chainId = txJsonSignDoc.get("chain_id").asString
            BaseData.baseAccount?.let { account ->
                account.allEvmLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                    ?: run {
                        account.allCosmosLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                    }?.let { chain ->
                        chain.getFeeInfos(requireContext())
                            .first().feeDatas.firstOrNull { it.denom == chain.stakeDenom }
                            ?.let { gasRate ->
                                val gasLimit =
                                    (gas.toDouble() * chain.gasMultiply()).toLong().toBigDecimal()
                                val feeCoinAmount =
                                    gasRate.gasRate?.multiply(gasLimit)
                                        ?.setScale(0, RoundingMode.UP)

                                if (amounts.size() == 0) {
                                    val jsonObject = JsonObject()
                                    jsonObject.addProperty(
                                        "amount", feeCoinAmount.toString()
                                    )
                                    jsonObject.addProperty("denom", chain.stakeDenom)
                                    amounts.add(jsonObject)

                                } else {
                                    val mainDenomFee =
                                        amounts.firstOrNull { it.asJsonObject["denom"].asString == chain.stakeDenom }
                                    mainDenomFee?.asJsonObject?.addProperty(
                                        "amount", feeCoinAmount.toString()
                                    )
                                }
                            }
                    }
            }
        }
        return txJsonSignDoc
    }

    private fun updateFeeInfoInDirectMessage(txJsonObject: JsonObject): JsonObject {
        val doc = txJsonObject["doc"].asJsonObject
        var authInfo =
            TxProto.AuthInfo.parseFrom(Utils.hexToBytes(doc["auth_info_bytes"].asString))
        val txBody = TxProto.TxBody.parseFrom(Utils.hexToBytes(doc["body_bytes"].asString))
        val isEditFee: Boolean = txJsonObject.getAsJsonPrimitive("isEditFee")?.asBoolean ?: true
        val fee = authInfo.fee

        if (isEditFee || fee.amountList.isEmpty()) {
            val chainId = doc["chain_id"].asString
            BaseData.baseAccount?.let { account ->
                account.allEvmLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                    ?: run {
                        account.allCosmosLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                    }?.let { chain ->
                        val simulateGas = Signer.dAppSimulateGas(chain, txBody, authInfo)
                        val simulateGasLimit =
                            (simulateGas.gasUsed.toDouble() * chain.gasMultiply()).toLong()
                        val updateFee = updateFeeWithSimulate(
                            chain,
                            simulateGasLimit.toBigDecimal(),
                            fee.gasLimit.toBigDecimal(),
                            fee
                        )
                        authInfo = authInfo.toBuilder().setFee(updateFee).build()
                    }
            }
        }
        return txJsonObject["doc"].asJsonObject.apply {
            addProperty("auth_info_bytes", Utils.bytesToHex(authInfo.toByteArray()))
        }
    }

    private fun updateFeeWithSimulate(
        chain: CosmosLine,
        simulateGasLimit: BigDecimal,
        originalGasLimit: BigDecimal,
        fee: Fee
    ): Fee? {
        chain.getFeeInfos(requireContext())[chain.getFeeBasePosition()].feeDatas.firstOrNull { it.denom == chain.stakeDenom }
            ?.let { gasRate ->
                if (simulateGasLimit > originalGasLimit) {
                    val feeCoinAmount =
                        gasRate.gasRate?.multiply(simulateGasLimit)?.setScale(0, RoundingMode.UP)
                    val updateFeeCoin =
                        CoinProto.Coin.newBuilder().setDenom(chain.stakeDenom)
                            .setAmount(feeCoinAmount.toString()).build()
                    return Fee.newBuilder().setGasLimit(simulateGasLimit.toLong())
                        .addAmount(updateFeeCoin)
                        .setPayer(fee.payer).build()

                } else {
                    val feeCoinAmount =
                        gasRate.gasRate?.multiply(originalGasLimit)?.setScale(0, RoundingMode.UP)
                    val updateFeeCoin =
                        CoinProto.Coin.newBuilder().setDenom(chain.stakeDenom)
                            .setAmount(feeCoinAmount.toString()).build()
                    return Fee.newBuilder().setGasLimit(fee.gasLimit).addAmount(updateFeeCoin)
                        .setPayer(fee.payer).build()
                }
            }
        return null
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