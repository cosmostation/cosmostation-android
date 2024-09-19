package wannabit.io.cosmostaion.data.repository.tx

import com.cosmos.auth.v1beta1.QueryGrpc
import com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.abci.v1beta1.AbciProto.TxResponse
import com.cosmos.tx.v1beta1.ServiceGrpc.newBlockingStub
import com.cosmos.tx.v1beta1.ServiceProto
import com.cosmos.tx.v1beta1.ServiceProto.BroadcastMode
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.protobuf.ByteString
import com.trustwallet.walletconnect.extensions.toHex
import io.grpc.ManagedChannel
import kotlinx.coroutines.Dispatchers
import net.i2p.crypto.eddsa.Utils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.bitcoinj.core.ECKey
import org.bouncycastle.util.encoders.Base64
import org.json.JSONObject
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Type
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.crypto.Credentials
import org.web3j.crypto.RawTransaction
import org.web3j.crypto.TransactionEncoder
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthGetTransactionCount
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Numeric
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.SuiFetcher
import wannabit.io.cosmostaion.chain.accountInfos
import wannabit.io.cosmostaion.chain.accountNumber
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.chain.sequence
import wannabit.io.cosmostaion.common.BaseConstant.ICNS_OSMOSIS_ADDRESS
import wannabit.io.cosmostaion.common.BaseConstant.NS_ARCHWAY_ADDRESS
import wannabit.io.cosmostaion.common.BaseConstant.NS_STARGZE_ADDRESS
import wannabit.io.cosmostaion.common.jsonRpcResponse
import wannabit.io.cosmostaion.common.percentile
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.common.soft
import wannabit.io.cosmostaion.cosmos.BitCoinJS
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.BroadcastTxReq
import wannabit.io.cosmostaion.data.model.req.EstimateGasParams
import wannabit.io.cosmostaion.data.model.req.ICNSInfoReq
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.req.Msg
import wannabit.io.cosmostaion.data.model.req.NSArchwayReq
import wannabit.io.cosmostaion.data.model.req.NSStargazeInfoReq
import wannabit.io.cosmostaion.data.model.req.ResolveRecord
import wannabit.io.cosmostaion.data.model.req.SimulateTxReq
import wannabit.io.cosmostaion.data.model.req.SuiStakeReq
import wannabit.io.cosmostaion.data.model.req.SuiUnStakeReq
import wannabit.io.cosmostaion.data.model.res.LegacyRes
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

class TxRepositoryImpl : TxRepository {

    private var duration = 8L

    override suspend fun osIcnsAddress(
        managedChannel: ManagedChannel?, userInput: String?, prefix: String
    ): String? {
        return try {
            val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(managedChannel)
                .withDeadlineAfter(duration, TimeUnit.SECONDS)
            val infoReq = ICNSInfoReq("$userInput.$prefix")
            val queryData = ByteString.copyFromUtf8(Gson().toJson(infoReq))
            val request =
                com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder()
                    .setAddress(ICNS_OSMOSIS_ADDRESS).setQueryData(queryData).build()
            stub.smartContractState(request)?.let {
                val json = JSONObject(it.data.toStringUtf8())
                json.get("bech32_address").toString()
            }
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun sgIcnsAddress(
        managedChannel: ManagedChannel?, userInput: String?
    ): String? {
        return try {
            val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(managedChannel)
                .withDeadlineAfter(duration, TimeUnit.SECONDS)
            val infoReq = NSStargazeInfoReq(userInput)
            val queryData = ByteString.copyFromUtf8(Gson().toJson(infoReq))
            val request =
                com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder()
                    .setAddress(NS_STARGZE_ADDRESS).setQueryData(queryData).build()

            stub.smartContractState(request)?.let {
                it.data.toStringUtf8().replace("\"".toRegex(), "")
            }
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun archIcnsAddress(
        managedChannel: ManagedChannel?, userInput: String?
    ): String? {
        return try {
            val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(managedChannel)
                .withDeadlineAfter(duration, TimeUnit.SECONDS)
            val infoReq = NSArchwayReq(ResolveRecord(userInput))
            val queryData = ByteString.copyFromUtf8(Gson().toJson(infoReq))
            val request =
                com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder()
                    .setAddress(NS_ARCHWAY_ADDRESS).setQueryData(queryData).build()

            stub.smartContractState(request)?.let {
                val json = JSONObject(it.data.toStringUtf8())
                json.getString("address")
            }
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun auth(
        managedChannel: ManagedChannel?, chain: BaseChain
    ) {
        return if (chain.cosmosFetcher()?.endPointType(chain) == CosmosEndPointType.USE_GRPC) {
            val stub = QueryGrpc.newBlockingStub(managedChannel)
                .withDeadlineAfter(duration, TimeUnit.SECONDS)
            val request = QueryAccountRequest.newBuilder().setAddress(chain.address).build()
            chain.cosmosFetcher()?.cosmosAuth = stub.account(request).account
            chain.cosmosFetcher()?.cosmosAccountNumber =
                stub.account(request).account.accountInfos().second
            chain.cosmosFetcher()?.cosmosSequence =
                stub.account(request).account.accountInfos().third

        } else {
            val response = RetrofitInstance.lcdApi(chain)
                .lcdAuthInfo(chain.address).asJsonObject["account"].asJsonObject
            chain.cosmosFetcher()?.cosmosLcdAuth = response
            chain.cosmosFetcher()?.cosmosAccountNumber = response.accountNumber()
            chain.cosmosFetcher()?.cosmosSequence = response.sequence()
        }
    }

    override suspend fun broadcastEvmSendTx(web3j: Web3j, hexValue: String): String? {
        return try {
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()
            ethSendTransaction.transactionHash
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun simulateEvmSendTx(
        toEthAddress: String?,
        toSendAmount: String?,
        selectedToken: Token?,
        sendAssetType: SendAssetType,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ): Pair<String?, String?> {
        return try {
            val ecKey = ECKey.fromPrivate(selectedChain.privateKey)
            val rpcUrl = selectedChain.evmRpcFetcher?.getEvmRpc() ?: selectedChain.evmRpcURL
            val web3j = Web3j.build(HttpService(rpcUrl))
            val credentials: Credentials = Credentials.create(ecKey.privateKeyAsHex)

            val ethGetTransactionCount: EthGetTransactionCount =
                web3j.ethGetTransactionCount(credentials.address, DefaultBlockParameterName.LATEST)
                    .sendAsync().get()
            val chainID = web3j.ethChainId().sendAsync().get().chainId.toLong()
            val nonce = ethGetTransactionCount.transactionCount
            val fromAddress = selectedChain.evmAddress

            val txData = selectedToken?.let {
                val params: MutableList<Type<*>> = java.util.ArrayList()
                params.add(Address(toEthAddress))
                params.add(Uint256(toSendAmount?.toBigInteger()))

                val returnTypes = emptyList<TypeReference<*>>()
                val function = Function(
                    "transfer", params, returnTypes
                )
                FunctionEncoder.encode(function)
            }

            val gasLimit = selectedToken?.let { token ->
                val estimateGasRequest = JsonRpcRequest(
                    method = "eth_estimateGas", params = listOf(
                        EstimateGasParams(
                            fromAddress, token.address, txData
                        )
                    )
                )
                val estimateGasJsonRequest = ObjectMapper().writeValueAsString(estimateGasRequest)
                val estimateGasRpcRequest = Request.Builder().url(rpcUrl)
                    .post(estimateGasJsonRequest.toRequestBody("application/json".toMediaTypeOrNull()))
                    .build()

                val estimateGasResponse = OkHttpClient().newCall(estimateGasRpcRequest).execute()
                if (estimateGasResponse.isSuccessful) {
                    val jsonResponse = estimateGasResponse.body?.string()

                    val jsonObject = Gson().fromJson(jsonResponse, JsonObject::class.java)
                    BigInteger(jsonObject.asJsonObject["result"].asString.removePrefix("0x"), 16)

                } else {
                    BigInteger.valueOf(21000L)
                }

            } ?: run {
                val transaction = Transaction(
                    fromAddress,
                    nonce,
                    BigInteger.ZERO,
                    BigInteger.ZERO,
                    toEthAddress,
                    BigInteger.ZERO,
                    txData
                )
                web3j.ethEstimateGas(transaction).send().amountUsed
            }

            val request = JsonRpcRequest(
                method = "eth_feeHistory", params = listOf(
                    20, "latest", listOf(25, 50, 75)
                )
            )
            val jsonRequest = ObjectMapper().writeValueAsString(request)
            val rpcRequest = Request.Builder().url(rpcUrl)
                .post(jsonRequest.toRequestBody("application/json".toMediaTypeOrNull())).build()

            val response = OkHttpClient().newCall(rpcRequest).execute()
            if (response.isSuccessful) {
                val jsonResponse = response.body?.string()
                val jsonObject = Gson().fromJson(jsonResponse, JsonObject::class.java)

                val feeHistoryFeePerGas = try {
                    jsonObject.asJsonObject["result"].asJsonObject["baseFeePerGas"].asJsonArray
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
                        jsonObject.asJsonObject["result"].asJsonObject["reward"].asJsonArray
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

                    val tip: BigInteger
                    val baseFee: BigInteger?
                    val evmGas: Long

                    if (selectedChain.evmSupportEip1559()) {
                        tip = suggestTipValue[selectedFeeInfo]
                        baseFee = suggestBaseFee[selectedFeeInfo]
                        evmGas = baseFee!!.toLong() + tip.toLong()

                    } else {
                        tip =
                            if (suggestTipValue[selectedFeeInfo] < BigInteger.valueOf(1000000000L)) {
                                BigInteger.valueOf(1000000000L)
                            } else {
                                suggestTipValue[selectedFeeInfo]
                            }
                        baseFee =
                            if (suggestBaseFee[selectedFeeInfo] == null || suggestBaseFee[selectedFeeInfo]!! < BigInteger.valueOf(
                                    500000000L
                                )
                            ) {
                                BigInteger.valueOf(500000000L)

                            } else {
                                suggestBaseFee[selectedFeeInfo]
                            }
                        evmGas = baseFee!!.toLong() + tip.toLong()
                    }

                    var rawTransaction: RawTransaction? = null

                    if (sendAssetType == SendAssetType.ONLY_EVM_COIN || sendAssetType == SendAssetType.COSMOS_EVM_COIN) {
                        rawTransaction = RawTransaction.createEtherTransaction(
                            chainID,
                            nonce,
                            gasLimit,
                            toEthAddress,
                            toSendAmount?.toBigInteger(),
                            tip,
                            evmGas.toBigInteger()
                        )

                    } else if (sendAssetType == SendAssetType.ONLY_EVM_ERC20) {
                        rawTransaction = RawTransaction.createTransaction(
                            chainID,
                            nonce,
                            gasLimit,
                            selectedToken?.address,
                            BigInteger.ZERO,
                            txData,
                            tip,
                            evmGas.toBigInteger()
                        )
                    }

                    val signedMessage = TransactionEncoder.signMessage(
                        rawTransaction, chainID, credentials
                    )
                    val hexValue = Numeric.toHexString(signedMessage)
                    val feeAmount = gasLimit.multiply(evmGas.toBigInteger())

                    Pair(hexValue, feeAmount.toString())

                } else {
                    var rawTransaction: RawTransaction? = null

                    if (sendAssetType == SendAssetType.ONLY_EVM_COIN || sendAssetType == SendAssetType.COSMOS_EVM_COIN) {
                        rawTransaction = RawTransaction.createEtherTransaction(
                            nonce,
                            web3j.ethGasPrice().send().gasPrice,
                            gasLimit,
                            toEthAddress,
                            toSendAmount?.toBigInteger()
                        )

                    } else if (sendAssetType == SendAssetType.ONLY_EVM_ERC20) {
                        rawTransaction = RawTransaction.createTransaction(
                            nonce,
                            web3j.ethGasPrice().send().gasPrice,
                            gasLimit,
                            selectedToken?.address,
                            BigInteger.ZERO,
                            txData
                        )
                    }

                    val signedMessage = TransactionEncoder.signMessage(
                        rawTransaction, chainID, credentials
                    )
                    val hexValue = Numeric.toHexString(signedMessage)
                    val feeAmount =
                        gasLimit.multiply(web3j.ethGasPrice().sendAsync().get().gasPrice)

                    Pair(hexValue, feeAmount.toString())
                }

            } else {
                Pair("", "")
            }

        } catch (e: Exception) {
            Pair("", "")
        }
    }

    override suspend fun broadcastEvmDelegateTx(web3j: Web3j, hexValue: String): String? {
        return try {
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()
            ethSendTransaction.transactionHash
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun simulateEvmDelegateTx(
        toValidatorAddress: String?,
        toDelegateAmount: String?,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ): Pair<String?, String?> {
//        try {
//            val ecKey = ECKey.fromPrivate(selectedChain.privateKey)
//            val web3j = Web3j.build(HttpService(selectedChain.getEvmRpc()))
//            val credentials: Credentials = Credentials.create(ecKey.privateKeyAsHex)
//
//            val ethGetTransactionCount: EthGetTransactionCount =
//                web3j.ethGetTransactionCount(credentials.address, DefaultBlockParameterName.LATEST)
//                    .sendAsync().get()
//            val chainID = web3j.ethChainId().sendAsync().get().chainId.toLong()
//            val nonce = ethGetTransactionCount.transactionCount
//
//            val params: MutableList<Type<*>> = java.util.ArrayList()
//            params.add(Address(toValidatorAddress))
//            params.add(Uint256(toDelegateAmount?.toBigInteger()))
//
//            val function = Function(
//                "delegate", params, emptyList<TypeReference<*>>()
//            )
//            val txData = FunctionEncoder.encode(function)
//
//            val ethGasRequest = JsonRpcRequest(
//                method = "eth_estimateGas", params = listOf(
////                    EstimateGasParams(
////                        ByteUtils.convertBech32ToEvm(selectedChain.address),
////                        BERA_CONT_STAKING,
////                        txData
////                    )
//                    EstimateGasParams(
//                        ByteUtils.convertBech32ToEvm(selectedChain.address),
//                        "",
//                        txData
//                    )
//                )
//            )
//            val ethGasResponse = jsonRpcResponse(selectedChain.getEvmRpc(), ethGasRequest)
//            val gasLimit = if (ethGasResponse.isSuccessful) {
//                val gasJsonObject = Gson().fromJson(
//                    ethGasResponse.body?.string(), JsonObject::class.java
//                )
//                BigInteger(gasJsonObject.asJsonObject["result"].asString.removePrefix("0x"), 16)
//            } else {
//                BigInteger.valueOf(21000L)
//            }
//
//            val ethFeeHistoryRequest = JsonRpcRequest(
//                method = "eth_feeHistory", params = listOf(
//                    20, "pending", listOf(25, 50, 75)
//                )
//            )
//            val ethFeeHistoryResponse =
//                jsonRpcResponse(selectedChain.getEvmRpc(), ethFeeHistoryRequest)
//            if (ethFeeHistoryResponse.isSuccessful) {
//                val historyJsonObject = Gson().fromJson(
//                    ethFeeHistoryResponse.body?.string(), JsonObject::class.java
//                )
//
//                val feeHistoryFeePerGas = try {
//                    historyJsonObject.asJsonObject["result"].asJsonObject["baseFeePerGas"].asJsonArray
//                } catch (e: Exception) {
//                    mutableListOf()
//                }
//
//                val suggestGasValues = try {
//                    feeHistoryFeePerGas.map {
//                        BigInteger(
//                            it.asString.removePrefix("0x"), 16
//                        )
//                    }.toMutableList()
//                } catch (e: Exception) {
//                    mutableListOf()
//                }
//
//                if (suggestGasValues.isNotEmpty()) {
//                    val suggestBaseFee = listOf(25.0, 50.0, 75.0).map {
//                        suggestGasValues.percentile(it)
//                    }
//
//                    val reward =
//                        historyJsonObject.asJsonObject["result"].asJsonObject["reward"].asJsonArray
//                    val rearrangedArray: MutableList<MutableList<BigInteger>> = ArrayList()
//                    reward.forEach {
//                        val percentiles = it.asJsonArray.map { percentile ->
//                            BigInteger(
//                                percentile.asString.removePrefix("0x"), 16
//                            )
//                        }.toMutableList()
//
//                        percentiles.forEachIndexed { index, percentile ->
//                            if (rearrangedArray.size <= index) {
//                                rearrangedArray.add(mutableListOf(percentile))
//                            } else {
//                                rearrangedArray[index].add(percentile)
//                            }
//                        }
//                    }
//                    val suggestTipValue = soft(rearrangedArray)
//                    val tip =
//                        if (suggestTipValue[selectedFeeInfo] < BigInteger.valueOf(1000000000L)) {
//                            BigInteger.valueOf(1000000000L)
//                        } else {
//                            suggestTipValue[selectedFeeInfo]
//                        }
//
//                    val totalPerGas =
//                        if (suggestBaseFee[selectedFeeInfo] == null || suggestBaseFee[selectedFeeInfo]!! < BigInteger.valueOf(
//                                500000000L
//                            )
//                        ) {
//                            500000000L + tip.toLong()
//                        } else {
//                            suggestBaseFee[1]!!.toLong() + tip.toLong()
//                        }
//
////                    val rawTransaction = RawTransaction.createTransaction(
////                        chainID,
////                        nonce,
////                        gasLimit,
////                        BERA_CONT_STAKING,
////                        BigInteger.ZERO,
////                        txData,
////                        tip,
////                        totalPerGas.toBigInteger()
////                    )
//                    val rawTransaction = RawTransaction.createTransaction(
//                        chainID,
//                        nonce,
//                        gasLimit,
//                        "",
//                        BigInteger.ZERO,
//                        txData,
//                        tip,
//                        totalPerGas.toBigInteger()
//                    )
//
//                    val signedMessage = TransactionEncoder.signMessage(
//                        rawTransaction, chainID, credentials
//                    )
//                    val hexValue = Numeric.toHexString(signedMessage)
//                    val feeAmount = gasLimit.multiply(totalPerGas.toBigInteger())
//                    return Pair(hexValue, feeAmount.toString())
//
//                } else {
//                    return Pair("", "")
//                }
//
//            } else {
//                return Pair("", "")
//            }
//
//        } catch (e: Exception) {
//            return Pair("", "")
//        }
        return Pair("", "")
    }

    override suspend fun broadcastEvmUnDelegateTx(web3j: Web3j, hexValue: String): String? {
        return try {
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()
            ethSendTransaction.transactionHash
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun simulateEvmUnDelegateTx(
        validatorAddress: String?,
        toUnDelegateAmount: String?,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ): Pair<String?, String?> {
//        try {
//            val ecKey = ECKey.fromPrivate(selectedChain.privateKey)
//            val web3j = Web3j.build(HttpService(selectedChain.getEvmRpc()))
//            val credentials: Credentials = Credentials.create(ecKey.privateKeyAsHex)
//
//            val ethGetTransactionCount: EthGetTransactionCount =
//                web3j.ethGetTransactionCount(credentials.address, DefaultBlockParameterName.LATEST)
//                    .sendAsync().get()
//            val chainID = web3j.ethChainId().sendAsync().get().chainId.toLong()
//            val nonce = ethGetTransactionCount.transactionCount
//
//            val params: MutableList<Type<*>> = java.util.ArrayList()
//            params.add(Address(validatorAddress))
//            params.add(Uint256(toUnDelegateAmount?.toBigInteger()))
//
//            val function = Function(
//                "undelegate", params, emptyList<TypeReference<*>>()
//            )
//            val txData = FunctionEncoder.encode(function)
//
//            val ethGasRequest = JsonRpcRequest(
//                method = "eth_estimateGas", params = listOf(
////                    EstimateGasParams(
////                        ByteUtils.convertBech32ToEvm(selectedChain.address),
////                        BERA_CONT_STAKING,
////                        txData
////                    )
//                    EstimateGasParams(
//                        ByteUtils.convertBech32ToEvm(selectedChain.address),
//                        "",
//                        txData
//                    )
//                )
//            )
//            val ethGasResponse = jsonRpcResponse(selectedChain.getEvmRpc(), ethGasRequest)
//            val gasLimit = if (ethGasResponse.isSuccessful) {
//                val gasJsonObject = Gson().fromJson(
//                    ethGasResponse.body?.string(), JsonObject::class.java
//                )
//                BigInteger(gasJsonObject.asJsonObject["result"].asString.removePrefix("0x"), 16)
//            } else {
//                BigInteger.valueOf(21000L)
//            }
//
//            val ethFeeHistoryRequest = JsonRpcRequest(
//                method = "eth_feeHistory", params = listOf(
//                    20, "pending", listOf(25, 50, 75)
//                )
//            )
//            val ethFeeHistoryResponse =
//                jsonRpcResponse(selectedChain.getEvmRpc(), ethFeeHistoryRequest)
//            if (ethFeeHistoryResponse.isSuccessful) {
//                val historyJsonObject = Gson().fromJson(
//                    ethFeeHistoryResponse.body?.string(), JsonObject::class.java
//                )
//
//                val feeHistoryFeePerGas = try {
//                    historyJsonObject.asJsonObject["result"].asJsonObject["baseFeePerGas"].asJsonArray
//                } catch (e: Exception) {
//                    mutableListOf()
//                }
//
//                val suggestGasValues = try {
//                    feeHistoryFeePerGas.map {
//                        BigInteger(
//                            it.asString.removePrefix("0x"), 16
//                        )
//                    }.toMutableList()
//                } catch (e: Exception) {
//                    mutableListOf()
//                }
//
//                if (suggestGasValues.isNotEmpty()) {
//                    val suggestBaseFee = listOf(25.0, 50.0, 75.0).map {
//                        suggestGasValues.percentile(it)
//                    }
//
//                    val reward =
//                        historyJsonObject.asJsonObject["result"].asJsonObject["reward"].asJsonArray
//                    val rearrangedArray: MutableList<MutableList<BigInteger>> = ArrayList()
//                    reward.forEach {
//                        val percentiles = it.asJsonArray.map { percentile ->
//                            BigInteger(
//                                percentile.asString.removePrefix("0x"), 16
//                            )
//                        }.toMutableList()
//
//                        percentiles.forEachIndexed { index, percentile ->
//                            if (rearrangedArray.size <= index) {
//                                rearrangedArray.add(mutableListOf(percentile))
//                            } else {
//                                rearrangedArray[index].add(percentile)
//                            }
//                        }
//                    }
//                    val suggestTipValue = soft(rearrangedArray)
//                    val tip =
//                        if (suggestTipValue[selectedFeeInfo] < BigInteger.valueOf(1000000000L)) {
//                            BigInteger.valueOf(1000000000L)
//                        } else {
//                            suggestTipValue[selectedFeeInfo]
//                        }
//
//                    val totalPerGas =
//                        if (suggestBaseFee[selectedFeeInfo] == null || suggestBaseFee[selectedFeeInfo]!! < BigInteger.valueOf(
//                                500000000L
//                            )
//                        ) {
//                            500000000L + tip.toLong()
//                        } else {
//                            suggestBaseFee[1]!!.toLong() + tip.toLong()
//                        }
//
////                    val rawTransaction = RawTransaction.createTransaction(
////                        chainID,
////                        nonce,
////                        gasLimit,
////                        BERA_CONT_STAKING,
////                        BigInteger.ZERO,
////                        txData,
////                        tip,
////                        totalPerGas.toBigInteger()
////                    )
//
//                    val rawTransaction = RawTransaction.createTransaction(
//                        chainID,
//                        nonce,
//                        gasLimit,
//                        "",
//                        BigInteger.ZERO,
//                        txData,
//                        tip,
//                        totalPerGas.toBigInteger()
//                    )
//
//                    val signedMessage = TransactionEncoder.signMessage(
//                        rawTransaction, chainID, credentials
//                    )
//                    val hexValue = Numeric.toHexString(signedMessage)
//                    val feeAmount = gasLimit.multiply(totalPerGas.toBigInteger())
//                    return Pair(hexValue, feeAmount.toString())
//
//                } else {
//                    return Pair("", "")
//                }
//
//            } else {
//                return Pair("", "")
//            }
//
//        } catch (e: Exception) {
//            return Pair("", "")
//        }
        return Pair("", "")
    }

    override suspend fun broadcastEvmReDelegateTx(web3j: Web3j, hexValue: String): String? {
        return try {
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()
            ethSendTransaction.transactionHash
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun simulateEvmReDelegateTx(
        fromValidatorAddress: String?,
        toValidatorAddress: String?,
        toReDelegateAmount: String?,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ): Pair<String?, String?> {
//        try {
//            val ecKey = ECKey.fromPrivate(selectedChain.privateKey)
//            val web3j = Web3j.build(HttpService(selectedChain.getEvmRpc()))
//            val credentials: Credentials = Credentials.create(ecKey.privateKeyAsHex)
//
//            val ethGetTransactionCount: EthGetTransactionCount =
//                web3j.ethGetTransactionCount(credentials.address, DefaultBlockParameterName.LATEST)
//                    .sendAsync().get()
//            val chainID = web3j.ethChainId().sendAsync().get().chainId.toLong()
//            val nonce = ethGetTransactionCount.transactionCount
//
//            val params: MutableList<Type<*>> = java.util.ArrayList()
//            params.add(Address(fromValidatorAddress))
//            params.add(Address(toValidatorAddress))
//            params.add(Uint256(toReDelegateAmount?.toBigInteger()))
//
//            val function = Function(
//                "beginRedelegate", params, emptyList<TypeReference<*>>()
//            )
//            val txData = FunctionEncoder.encode(function)
//
//            val ethGasRequest = JsonRpcRequest(
//                method = "eth_estimateGas", params = listOf(
////                    EstimateGasParams(
////                        ByteUtils.convertBech32ToEvm(selectedChain.address),
////                        BERA_CONT_STAKING,
////                        txData
////                    )
//                    EstimateGasParams(
//                        ByteUtils.convertBech32ToEvm(selectedChain.address),
//                        "",
//                        txData
//                    )
//                )
//            )
//            val ethGasResponse = jsonRpcResponse(selectedChain.getEvmRpc(), ethGasRequest)
//            val gasLimit = if (ethGasResponse.isSuccessful) {
//                val gasJsonObject = Gson().fromJson(
//                    ethGasResponse.body?.string(), JsonObject::class.java
//                )
//                BigInteger(gasJsonObject.asJsonObject["result"].asString.removePrefix("0x"), 16)
//            } else {
//                BigInteger.valueOf(21000L)
//            }
//
//            val ethFeeHistoryRequest = JsonRpcRequest(
//                method = "eth_feeHistory", params = listOf(
//                    20, "pending", listOf(25, 50, 75)
//                )
//            )
//            val ethFeeHistoryResponse =
//                jsonRpcResponse(selectedChain.getEvmRpc(), ethFeeHistoryRequest)
//            if (ethFeeHistoryResponse.isSuccessful) {
//                val historyJsonObject = Gson().fromJson(
//                    ethFeeHistoryResponse.body?.string(), JsonObject::class.java
//                )
//
//                val feeHistoryFeePerGas = try {
//                    historyJsonObject.asJsonObject["result"].asJsonObject["baseFeePerGas"].asJsonArray
//                } catch (e: Exception) {
//                    mutableListOf()
//                }
//
//                val suggestGasValues = try {
//                    feeHistoryFeePerGas.map {
//                        BigInteger(
//                            it.asString.removePrefix("0x"), 16
//                        )
//                    }.toMutableList()
//                } catch (e: Exception) {
//                    mutableListOf()
//                }
//
//                if (suggestGasValues.isNotEmpty()) {
//                    val suggestBaseFee = listOf(25.0, 50.0, 75.0).map {
//                        suggestGasValues.percentile(it)
//                    }
//
//                    val reward =
//                        historyJsonObject.asJsonObject["result"].asJsonObject["reward"].asJsonArray
//                    val rearrangedArray: MutableList<MutableList<BigInteger>> = ArrayList()
//                    reward.forEach {
//                        val percentiles = it.asJsonArray.map { percentile ->
//                            BigInteger(
//                                percentile.asString.removePrefix("0x"), 16
//                            )
//                        }.toMutableList()
//
//                        percentiles.forEachIndexed { index, percentile ->
//                            if (rearrangedArray.size <= index) {
//                                rearrangedArray.add(mutableListOf(percentile))
//                            } else {
//                                rearrangedArray[index].add(percentile)
//                            }
//                        }
//                    }
//                    val suggestTipValue = soft(rearrangedArray)
//                    val tip =
//                        if (suggestTipValue[selectedFeeInfo] < BigInteger.valueOf(1000000000L)) {
//                            BigInteger.valueOf(1000000000L)
//                        } else {
//                            suggestTipValue[selectedFeeInfo]
//                        }
//
//                    val totalPerGas =
//                        if (suggestBaseFee[selectedFeeInfo] == null || suggestBaseFee[selectedFeeInfo]!! < BigInteger.valueOf(
//                                500000000L
//                            )
//                        ) {
//                            500000000L + tip.toLong()
//                        } else {
//                            suggestBaseFee[1]!!.toLong() + tip.toLong()
//                        }
//
////                    val rawTransaction = RawTransaction.createTransaction(
////                        chainID,
////                        nonce,
////                        gasLimit,
////                        BERA_CONT_STAKING,
////                        BigInteger.ZERO,
////                        txData,
////                        tip,
////                        totalPerGas.toBigInteger()
////                    )
//
//                    val rawTransaction = RawTransaction.createTransaction(
//                        chainID,
//                        nonce,
//                        gasLimit,
//                        "",
//                        BigInteger.ZERO,
//                        txData,
//                        tip,
//                        totalPerGas.toBigInteger()
//                    )
//
//                    val signedMessage = TransactionEncoder.signMessage(
//                        rawTransaction, chainID, credentials
//                    )
//                    val hexValue = Numeric.toHexString(signedMessage)
//                    val feeAmount = gasLimit.multiply(totalPerGas.toBigInteger())
//                    return Pair(hexValue, feeAmount.toString())
//
//                } else {
//                    return Pair("", "")
//                }
//
//            } else {
//                return Pair("", "")
//            }
//
//        } catch (e: Exception) {
//            return Pair("", "")
//        }
        return Pair("", "")
    }

    override suspend fun broadcastEvmCancelUnStakingTx(web3j: Web3j, hexValue: String): String? {
        return try {
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()
            ethSendTransaction.transactionHash
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun simulateEvmCancelUnStakingTx(
        validatorAddress: String?,
        unDelegateAmount: String?,
        height: Long,
        selectedChain: BaseChain,
        selectedFeeInfo: Int
    ): Pair<String?, String?> {
//        try {
//            val ecKey = ECKey.fromPrivate(selectedChain.privateKey)
//            val web3j = Web3j.build(HttpService(selectedChain.getEvmRpc()))
//            val credentials: Credentials = Credentials.create(ecKey.privateKeyAsHex)
//
//            val ethGetTransactionCount: EthGetTransactionCount =
//                web3j.ethGetTransactionCount(credentials.address, DefaultBlockParameterName.LATEST)
//                    .sendAsync().get()
//            val chainID = web3j.ethChainId().sendAsync().get().chainId.toLong()
//            val nonce = ethGetTransactionCount.transactionCount
//
//            val params: MutableList<Type<*>> = java.util.ArrayList()
//            params.add(Address(validatorAddress))
//            params.add(Uint256(unDelegateAmount?.toBigInteger()))
//            params.add(Int64(height))
//
//            val function = Function(
//                "cancelUnbondingDelegation", params, emptyList<TypeReference<*>>()
//            )
//            val txData = FunctionEncoder.encode(function)
//
//            val ethGasRequest = JsonRpcRequest(
//                method = "eth_estimateGas", params = listOf(
////                    EstimateGasParams(
////                        ByteUtils.convertBech32ToEvm(selectedChain.address),
////                        BERA_CONT_STAKING,
////                        txData
////                    )
//                    EstimateGasParams(
//                        ByteUtils.convertBech32ToEvm(selectedChain.address),
//                        "",
//                        txData
//                    )
//                )
//            )
//            val ethGasResponse = jsonRpcResponse(selectedChain.getEvmRpc(), ethGasRequest)
//            val gasLimit = if (ethGasResponse.isSuccessful) {
//                val gasJsonObject = Gson().fromJson(
//                    ethGasResponse.body?.string(), JsonObject::class.java
//                )
//                BigInteger(gasJsonObject.asJsonObject["result"].asString.removePrefix("0x"), 16)
//            } else {
//                BigInteger.valueOf(21000L)
//            }
//
//            val ethFeeHistoryRequest = JsonRpcRequest(
//                method = "eth_feeHistory", params = listOf(
//                    20, "pending", listOf(25, 50, 75)
//                )
//            )
//            val ethFeeHistoryResponse =
//                jsonRpcResponse(selectedChain.getEvmRpc(), ethFeeHistoryRequest)
//            if (ethFeeHistoryResponse.isSuccessful) {
//                val historyJsonObject = Gson().fromJson(
//                    ethFeeHistoryResponse.body?.string(), JsonObject::class.java
//                )
//
//                val feeHistoryFeePerGas = try {
//                    historyJsonObject.asJsonObject["result"].asJsonObject["baseFeePerGas"].asJsonArray
//                } catch (e: Exception) {
//                    mutableListOf()
//                }
//
//                val suggestGasValues = try {
//                    feeHistoryFeePerGas.map {
//                        BigInteger(
//                            it.asString.removePrefix("0x"), 16
//                        )
//                    }.toMutableList()
//                } catch (e: Exception) {
//                    mutableListOf()
//                }
//
//                if (suggestGasValues.isNotEmpty()) {
//                    val suggestBaseFee = listOf(25.0, 50.0, 75.0).map {
//                        suggestGasValues.percentile(it)
//                    }
//
//                    val reward =
//                        historyJsonObject.asJsonObject["result"].asJsonObject["reward"].asJsonArray
//                    val rearrangedArray: MutableList<MutableList<BigInteger>> = ArrayList()
//                    reward.forEach {
//                        val percentiles = it.asJsonArray.map { percentile ->
//                            BigInteger(
//                                percentile.asString.removePrefix("0x"), 16
//                            )
//                        }.toMutableList()
//
//                        percentiles.forEachIndexed { index, percentile ->
//                            if (rearrangedArray.size <= index) {
//                                rearrangedArray.add(mutableListOf(percentile))
//                            } else {
//                                rearrangedArray[index].add(percentile)
//                            }
//                        }
//                    }
//                    val suggestTipValue = soft(rearrangedArray)
//                    val tip =
//                        if (suggestTipValue[selectedFeeInfo] < BigInteger.valueOf(1000000000L)) {
//                            BigInteger.valueOf(1000000000L)
//                        } else {
//                            suggestTipValue[selectedFeeInfo]
//                        }
//
//                    val totalPerGas =
//                        if (suggestBaseFee[selectedFeeInfo] == null || suggestBaseFee[selectedFeeInfo]!! < BigInteger.valueOf(
//                                500000000L
//                            )
//                        ) {
//                            500000000L + tip.toLong()
//                        } else {
//                            suggestBaseFee[1]!!.toLong() + tip.toLong()
//                        }
//
////                    val rawTransaction = RawTransaction.createTransaction(
////                        chainID,
////                        nonce,
////                        gasLimit,
////                        BERA_CONT_STAKING,
////                        BigInteger.ZERO,
////                        txData,
////                        tip,
////                        totalPerGas.toBigInteger()
////                    )
//
//                    val rawTransaction = RawTransaction.createTransaction(
//                        chainID,
//                        nonce,
//                        gasLimit,
//                        "",
//                        BigInteger.ZERO,
//                        txData,
//                        tip,
//                        totalPerGas.toBigInteger()
//                    )
//
//
//                    val signedMessage = TransactionEncoder.signMessage(
//                        rawTransaction, chainID, credentials
//                    )
//                    val hexValue = Numeric.toHexString(signedMessage)
//                    val feeAmount = gasLimit.multiply(totalPerGas.toBigInteger())
//                    return Pair(hexValue, feeAmount.toString())
//
//                } else {
//                    return Pair("", "")
//                }
//
//            } else {
//                return Pair("", "")
//            }
//
//        } catch (e: Exception) {
//            return Pair("", "")
//        }
        return Pair("", "")
    }

    override suspend fun broadcastEvmRVoteTx(web3j: Web3j, hexValue: String): String? {
        return try {
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()
            ethSendTransaction.transactionHash
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun simulateEvmVoteTx(
        proposalId: Long, proposalOption: Long, selectedChain: BaseChain, selectedFeeInfo: Int
    ): Pair<String?, String?> {
//        try {
//            val ecKey = ECKey.fromPrivate(selectedChain.privateKey)
//            val web3j = Web3j.build(HttpService(selectedChain.getEvmRpc()))
//            val credentials: Credentials = Credentials.create(ecKey.privateKeyAsHex)
//
//            val ethGetTransactionCount: EthGetTransactionCount =
//                web3j.ethGetTransactionCount(credentials.address, DefaultBlockParameterName.LATEST)
//                    .sendAsync().get()
//            val chainID = web3j.ethChainId().sendAsync().get().chainId.toLong()
//            val nonce = ethGetTransactionCount.transactionCount
//
//            val params: MutableList<Type<*>> = java.util.ArrayList()
//            params.add(Uint64(proposalId))
//            params.add(Int32(proposalOption))
//            params.add(Utf8String(""))
//
//            val function = Function(
//                "vote", params, emptyList<TypeReference<*>>()
//            )
//            val txData = FunctionEncoder.encode(function)
//
//            val ethGasRequest = JsonRpcRequest(
//                method = "eth_estimateGas", params = listOf(
////                    EstimateGasParams(
////                        ByteUtils.convertBech32ToEvm(selectedChain.address),
////                        BERA_CONT_GOVERNANCE,
////                        txData
////                    )
//                    EstimateGasParams(
//                        ByteUtils.convertBech32ToEvm(selectedChain.address),
//                        "",
//                        txData
//                    )
//                )
//            )
//            val ethGasResponse = jsonRpcResponse(selectedChain.getEvmRpc(), ethGasRequest)
//            val gasLimit = if (ethGasResponse.isSuccessful) {
//                val gasJsonObject = Gson().fromJson(
//                    ethGasResponse.body?.string(), JsonObject::class.java
//                )
//                BigInteger(gasJsonObject.asJsonObject["result"].asString.removePrefix("0x"), 16)
//            } else {
//                BigInteger.valueOf(21000L)
//            }
//
//            val ethFeeHistoryRequest = JsonRpcRequest(
//                method = "eth_feeHistory", params = listOf(
//                    20, "pending", listOf(25, 50, 75)
//                )
//            )
//            val ethFeeHistoryResponse =
//                jsonRpcResponse(selectedChain.getEvmRpc(), ethFeeHistoryRequest)
//            if (ethFeeHistoryResponse.isSuccessful) {
//                val historyJsonObject = Gson().fromJson(
//                    ethFeeHistoryResponse.body?.string(), JsonObject::class.java
//                )
//
//                val feeHistoryFeePerGas = try {
//                    historyJsonObject.asJsonObject["result"].asJsonObject["baseFeePerGas"].asJsonArray
//                } catch (e: Exception) {
//                    mutableListOf()
//                }
//
//                val suggestGasValues = try {
//                    feeHistoryFeePerGas.map {
//                        BigInteger(
//                            it.asString.removePrefix("0x"), 16
//                        )
//                    }.toMutableList()
//                } catch (e: Exception) {
//                    mutableListOf()
//                }
//
//                if (suggestGasValues.isNotEmpty()) {
//                    val suggestBaseFee = listOf(25.0, 50.0, 75.0).map {
//                        suggestGasValues.percentile(it)
//                    }
//
//                    val reward =
//                        historyJsonObject.asJsonObject["result"].asJsonObject["reward"].asJsonArray
//                    val rearrangedArray: MutableList<MutableList<BigInteger>> = ArrayList()
//                    reward.forEach {
//                        val percentiles = it.asJsonArray.map { percentile ->
//                            BigInteger(
//                                percentile.asString.removePrefix("0x"), 16
//                            )
//                        }.toMutableList()
//
//                        percentiles.forEachIndexed { index, percentile ->
//                            if (rearrangedArray.size <= index) {
//                                rearrangedArray.add(mutableListOf(percentile))
//                            } else {
//                                rearrangedArray[index].add(percentile)
//                            }
//                        }
//                    }
//                    val suggestTipValue = soft(rearrangedArray)
//                    val tip =
//                        if (suggestTipValue[selectedFeeInfo] < BigInteger.valueOf(1000000000L)) {
//                            BigInteger.valueOf(1000000000L)
//                        } else {
//                            suggestTipValue[selectedFeeInfo]
//                        }
//
//                    val totalPerGas =
//                        if (suggestBaseFee[selectedFeeInfo] == null || suggestBaseFee[selectedFeeInfo]!! < BigInteger.valueOf(
//                                500000000L
//                            )
//                        ) {
//                            500000000L + tip.toLong()
//                        } else {
//                            suggestBaseFee[1]!!.toLong() + tip.toLong()
//                        }
//
////                    val rawTransaction = RawTransaction.createTransaction(
////                        chainID,
////                        nonce,
////                        gasLimit,
////                        BERA_CONT_GOVERNANCE,
////                        BigInteger.ZERO,
////                        txData,
////                        tip,
////                        totalPerGas.toBigInteger()
////                    )
//
//                    val rawTransaction = RawTransaction.createTransaction(
//                        chainID,
//                        nonce,
//                        gasLimit,
//                        "",
//                        BigInteger.ZERO,
//                        txData,
//                        tip,
//                        totalPerGas.toBigInteger()
//                    )
//
//                    val signedMessage = TransactionEncoder.signMessage(
//                        rawTransaction, chainID, credentials
//                    )
//                    val hexValue = Numeric.toHexString(signedMessage)
//                    val feeAmount = gasLimit.multiply(totalPerGas.toBigInteger())
//                    return Pair(hexValue, feeAmount.toString())
//
//                } else {
//                    return Pair("", "")
//                }
//
//            } else {
//                return Pair("", "")
//            }
//
//        } catch (e: Exception) {
//            return Pair("", "")
//        }
        return Pair("", "")
    }

    override suspend fun broadcastTx(
        managedChannel: ManagedChannel?,
        msgs: MutableList<com.google.protobuf.Any>,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain
    ): TxResponse? {
        return try {
            val broadcastTx = Signer.genBroadcast(msgs, fee, memo, selectedChain)
            if (selectedChain.cosmosFetcher?.endPointType(selectedChain) == CosmosEndPointType.USE_GRPC) {
                val txStub =
                    newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
                txStub.broadcastTx(broadcastTx).txResponse

            } else {
                val txByte = Base64.toBase64String(broadcastTx?.txBytes?.toByteArray())
                val mode = BroadcastMode.BROADCAST_MODE_SYNC.number
                val response = RetrofitInstance.lcdApi(selectedChain)
                    .lcdBroadcastTx(BroadcastTxReq(mode, txByte))
                if (!response["tx_response"].isJsonNull && !response["tx_response"].asJsonObject.isJsonNull) {
                    val result = response["tx_response"].asJsonObject
                    TxResponse.newBuilder().setTxhash(result["txhash"].asString)
                        .setCode(result["code"].asInt).setRawLog(result["raw_log"].asString).build()
                } else {
                    null
                }
            }

        } catch (e: Exception) {
            null
        }
    }

    override suspend fun simulateTx(
        managedChannel: ManagedChannel?,
        msgs: MutableList<com.google.protobuf.Any>,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain
    ): String {
        return try {
            val simulateTx = Signer.genSimulate(msgs, fee, memo, selectedChain)
            if (selectedChain.cosmosFetcher?.endPointType(selectedChain) == CosmosEndPointType.USE_GRPC) {
                val simulStub =
                    newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
                simulStub.simulate(simulateTx).gasInfo.gasUsed.toString()
            } else {
                val txByte = Base64.toBase64String(simulateTx?.txBytes?.toByteArray())
                val response =
                    RetrofitInstance.lcdApi(selectedChain).lcdSimulateTx(SimulateTxReq(txByte))
                if (response.isSuccessful) {
                    response.body()?.getAsJsonObject("gas_info")
                        ?.get("gas_used")?.asString.toString()
                } else {
                    val errorMessageJsonObject = Gson().fromJson(
                        response.errorBody()?.string(), JsonObject::class.java
                    )
                    errorMessageJsonObject["message"].asString
                }
            }
        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastOktTx(
        msgs: MutableList<Msg>, fee: LFee, memo: String, selectedChain: BaseChain
    ): LegacyRes? {
        return try {
            val reqBroadCast = Signer.oktBroadcast(msgs, fee, memo, selectedChain)
            RetrofitInstance.lcdApi(selectedChain).broadTx(reqBroadCast)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun broadcastIbcSendTx(
        managedChannel: ManagedChannel?,
        msgTransfer: com.ibc.applications.transfer.v1.TxProto.MsgTransfer?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain
    ): TxResponse? {
        return try {
            val broadcastTx = Signer.genIbcSendBroadcast(msgTransfer, fee, memo, selectedChain)
            if (selectedChain.cosmosFetcher?.endPointType(selectedChain) == CosmosEndPointType.USE_GRPC) {
                val txStub =
                    newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
                txStub.broadcastTx(broadcastTx).txResponse

            } else {
                val txByte = Base64.toBase64String(broadcastTx?.txBytes?.toByteArray())
                val mode = BroadcastMode.BROADCAST_MODE_SYNC.number
                val response = RetrofitInstance.lcdApi(selectedChain)
                    .lcdBroadcastTx(BroadcastTxReq(mode, txByte))
                if (!response["tx_response"].isJsonNull && !response["tx_response"].asJsonObject.isJsonNull) {
                    val result = response["tx_response"].asJsonObject
                    TxResponse.newBuilder().setTxhash(result["txhash"].asString)
                        .setCode(result["code"].asInt).setRawLog(result["raw_log"].asString).build()
                } else {
                    null
                }
            }

        } catch (e: Exception) {
            null
        }
    }

    override suspend fun simulateIbcSendTx(
        managedChannel: ManagedChannel?,
        msgTransfer: com.ibc.applications.transfer.v1.TxProto.MsgTransfer?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain
    ): String {
        return try {
            val simulateTx = Signer.genIbcSendSimulate(msgTransfer, fee, memo, selectedChain)
            if (selectedChain.cosmosFetcher?.endPointType(selectedChain) == CosmosEndPointType.USE_GRPC) {
                val simulStub =
                    newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
                simulStub.simulate(simulateTx).gasInfo.gasUsed.toString()
            } else {
                val txByte = Base64.toBase64String(simulateTx?.txBytes?.toByteArray())
                val response =
                    RetrofitInstance.lcdApi(selectedChain).lcdSimulateTx(SimulateTxReq(txByte))
                if (response.isSuccessful) {
                    val gasUsed = response.body()?.getAsJsonObject("gas_info")
                        ?.get("gas_used")?.asString?.toLong()
                    val gasInfo = gasUsed?.let { used ->
                        AbciProto.GasInfo.newBuilder().setGasUsed(used).build()
                    }
                    ServiceProto.SimulateResponse.newBuilder().setGasInfo(gasInfo)
                        .build().gasInfo.gasUsed.toString()

                } else {
                    val errorMessageJsonObject = Gson().fromJson(
                        response.errorBody()?.string(), JsonObject::class.java
                    )
                    errorMessageJsonObject["message"].asString
                }
            }

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun unSafePaySui(
        fetcher: SuiFetcher,
        sender: String,
        coins: MutableList<String>,
        recipient: MutableList<String>,
        amounts: MutableList<String>,
        gasBudget: String
    ): NetworkResult<String> {
        return try {
            val param = listOf(sender, coins, recipient, amounts, gasBudget)

            val suiUnSafePaySuiRequest = JsonRpcRequest(
                method = "unsafe_paySui", params = param
            )
            val suiUnSafePaySuiResponse = jsonRpcResponse(fetcher.suiRpc(), suiUnSafePaySuiRequest)
            val suiUnSafePaySuiJsonObject = Gson().fromJson(
                suiUnSafePaySuiResponse.body?.string(), JsonObject::class.java
            )
            safeApiCall(Dispatchers.IO) {
                suiUnSafePaySuiJsonObject["result"].asJsonObject["txBytes"].asString
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                ""
            }
        }
    }

    override suspend fun unSafePay(
        fetcher: SuiFetcher,
        sender: String,
        coins: MutableList<String>,
        recipient: MutableList<String>,
        amounts: MutableList<String>,
        gasBudget: String
    ): NetworkResult<String> {
        return try {
            val param = listOf(sender, coins, recipient, amounts, null, gasBudget)

            val suiUnSafePayRequest = JsonRpcRequest(
                method = "unsafe_pay", params = param
            )
            val suiUnSafePayResponse = jsonRpcResponse(fetcher.suiRpc(), suiUnSafePayRequest)
            val suiUnSafePayJsonObject = Gson().fromJson(
                suiUnSafePayResponse.body?.string(), JsonObject::class.java
            )
            safeApiCall(Dispatchers.IO) {
                suiUnSafePayJsonObject["result"].asJsonObject["txBytes"].asString
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                ""
            }
        }
    }

    override suspend fun unsafeTransferObject(
        fetcher: SuiFetcher, sender: String, objectId: String, recipient: String, gasBudget: String
    ): NetworkResult<String> {
        return try {
            val param = listOf(sender, objectId, null, gasBudget, recipient)

            val suiUnSafeTransferObjectRequest = JsonRpcRequest(
                method = "unsafe_transferObject", params = param
            )
            val suiUnSafeTransferObjectResponse =
                jsonRpcResponse(fetcher.suiRpc(), suiUnSafeTransferObjectRequest)
            val suiUnSafeTransferObjectJsonObject = Gson().fromJson(
                suiUnSafeTransferObjectResponse.body?.string(), JsonObject::class.java
            )
            safeApiCall(Dispatchers.IO) {
                suiUnSafeTransferObjectJsonObject["result"].asJsonObject["txBytes"].asString
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                ""
            }
        }
    }

    override suspend fun unsafeStake(
        fetcher: SuiFetcher, sender: String, amount: String, validator: String?, gasBudget: String
    ): NetworkResult<String> {
        return try {
            val response = RetrofitInstance.suiApi.unSafeStake(
                SuiStakeReq(
                    sender, validator, gasBudget, amount, fetcher.suiRpc()
                )
            )
            safeApiCall(Dispatchers.IO) {
                Base64.toBase64String(Utils.hexToBytes(response))
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                ""
            }
        }
    }

    override suspend fun unsafeUnStake(
        fetcher: SuiFetcher, sender: String, objectId: String, gasBudget: String
    ): NetworkResult<String> {
        return try {
            val response = RetrofitInstance.suiApi.unSafeUnStake(
                SuiUnStakeReq(
                    sender, objectId, gasBudget, fetcher.suiRpc()
                )
            )
            safeApiCall(Dispatchers.IO) {
                Base64.toBase64String(Utils.hexToBytes(response))
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                ""
            }
        }
    }

    override suspend fun suiDryRun(
        fetcher: SuiFetcher, txBytes: String
    ): NetworkResult<JsonObject> {
        return try {
            val suiDryRunRequest = JsonRpcRequest(
                method = "sui_dryRunTransactionBlock", params = listOf(txBytes)
            )
            val suiDryRunResponse = jsonRpcResponse(fetcher.suiRpc(), suiDryRunRequest)
            val suiDryRunJsonObject = Gson().fromJson(
                suiDryRunResponse.body?.string(), JsonObject::class.java
            )
            safeApiCall(Dispatchers.IO) {
                suiDryRunJsonObject
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                JsonObject()
            }
        }
    }

    override suspend fun suiExecuteTx(
        fetcher: SuiFetcher, txBytes: String, signatures: MutableList<String>
    ): NetworkResult<JsonObject> {
        return try {
            val param =
                listOf(txBytes, signatures, mapOf("showEffects" to true), "WaitForLocalExecution")
            val suiExecuteRequest = JsonRpcRequest(
                method = "sui_executeTransactionBlock", params = param
            )
            val suiExecuteResponse = jsonRpcResponse(fetcher.suiRpc(), suiExecuteRequest)
            val suiExecuteJsonObject = Gson().fromJson(
                suiExecuteResponse.body?.string(), JsonObject::class.java
            )
            safeApiCall(Dispatchers.IO) {
                suiExecuteJsonObject
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                JsonObject()
            }
        }
    }

    override suspend fun broadcastSuiSend(
        fetcher: SuiFetcher,
        sendDenom: String,
        sender: String,
        coins: MutableList<String>,
        recipient: MutableList<String>,
        amounts: MutableList<String>,
        gasBudget: String,
        selectedChain: BaseChain
    ): JsonObject {
        try {
            val txBytes = if (sendDenom == SUI_MAIN_DENOM) {
                unSafePaySui(
                    fetcher, sender, coins, recipient, amounts, gasBudget
                )
            } else {
                unSafePay(
                    fetcher, sender, coins, recipient, amounts, gasBudget
                )
            }

            if (txBytes is NetworkResult.Success) {
                val dryRes = suiDryRun(fetcher, txBytes.data)
                if (dryRes is NetworkResult.Success && dryRes.data["error"] == null) {
                    val broadRes = suiExecuteTx(
                        fetcher, txBytes.data, Signer.suiSignature(selectedChain, txBytes.data)
                    )
                    if (broadRes is NetworkResult.Success) {
                        return broadRes.data
                    }
                }
            }

        } catch (e: Exception) {
            return JsonObject()
        }
        return JsonObject()
    }

    override suspend fun simulateSuiSend(
        fetcher: SuiFetcher,
        sendDenom: String,
        sender: String,
        coins: MutableList<String>,
        recipient: MutableList<String>,
        amounts: MutableList<String>,
        gasBudget: String
    ): String {
        try {
            val txBytes = if (sendDenom == SUI_MAIN_DENOM) {
                unSafePaySui(
                    fetcher, sender, coins, recipient, amounts, gasBudget
                )
            } else {
                unSafePay(
                    fetcher, sender, coins, recipient, amounts, gasBudget
                )
            }

            if (txBytes is NetworkResult.Success) {
                val response = suiDryRun(fetcher, txBytes.data)
                if (response is NetworkResult.Success) {
                    if (response.data["error"] != null) {
                        return response.data["error"].asJsonObject["message"].asString

                    } else {
                        val computationCost =
                            response.data["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["computationCost"].asString.toBigDecimal()
                        val storageCost =
                            response.data["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["storageCost"].asString.toBigDecimal()
                        val storageRebate =
                            response.data["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["storageRebate"].asString.toBigDecimal()

                        val gasCost = if (storageCost > storageRebate) {
                            computationCost.add(storageCost).subtract(storageRebate).multiply(
                                BigDecimal("1.3")
                            ).setScale(0, RoundingMode.DOWN)
                        } else {
                            computationCost.multiply(
                                BigDecimal("1.3")
                            ).setScale(0, RoundingMode.DOWN)
                        }
                        return gasCost.toString()
                    }
                }
            }

        } catch (e: Exception) {
            return e.message.toString()
        }
        return ""
    }

    override suspend fun broadcastSuiNftSend(
        fetcher: SuiFetcher,
        sender: String,
        objectId: String,
        recipient: String,
        gasBudget: String,
        selectedChain: BaseChain
    ): JsonObject {
        try {
            val txBytes = unsafeTransferObject(fetcher, sender, objectId, recipient, gasBudget)

            if (txBytes is NetworkResult.Success) {
                val dryRes = suiDryRun(fetcher, txBytes.data)
                if (dryRes is NetworkResult.Success && dryRes.data["error"] == null) {
                    val broadRes = suiExecuteTx(
                        fetcher, txBytes.data, Signer.suiSignature(selectedChain, txBytes.data)
                    )
                    if (broadRes is NetworkResult.Success) {
                        return broadRes.data
                    }
                }
            }

        } catch (e: Exception) {
            return JsonObject()
        }
        return JsonObject()
    }

    override suspend fun simulateSuiNftSend(
        fetcher: SuiFetcher, sender: String, objectId: String, recipient: String, gasBudget: String
    ): String {
        try {
            val txBytes = unsafeTransferObject(fetcher, sender, objectId, recipient, gasBudget)

            if (txBytes is NetworkResult.Success) {
                val response = suiDryRun(fetcher, txBytes.data)
                if (response is NetworkResult.Success) {
                    if (response.data["error"] != null) {
                        return response.data["error"].asJsonObject["message"].asString

                    } else {
                        val computationCost =
                            response.data["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["computationCost"].asString.toBigDecimal()
                        val storageCost =
                            response.data["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["storageCost"].asString.toBigDecimal()
                        val storageRebate =
                            response.data["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["storageRebate"].asString.toBigDecimal()

                        val gasCost = if (storageCost > storageRebate) {
                            computationCost.add(storageCost).subtract(storageRebate).multiply(
                                BigDecimal("1.3")
                            ).setScale(0, RoundingMode.DOWN)
                        } else {
                            computationCost.multiply(
                                BigDecimal("1.3")
                            ).setScale(0, RoundingMode.DOWN)
                        }
                        return gasCost.toString()
                    }
                }
            }

        } catch (e: Exception) {
            return e.message.toString()
        }
        return ""
    }

    override suspend fun broadcastSuiStake(
        fetcher: SuiFetcher,
        sender: String,
        validator: String,
        amount: String,
        gasBudget: String,
        selectedChain: BaseChain
    ): JsonObject {
        try {
            val txBytes = unsafeStake(fetcher, sender, validator, amount, gasBudget)

            if (txBytes is NetworkResult.Success) {
                val dryRes = suiDryRun(fetcher, txBytes.data)
                if (dryRes is NetworkResult.Success && dryRes.data["error"] == null) {
                    val broadRes = suiExecuteTx(
                        fetcher, txBytes.data, Signer.suiSignature(selectedChain, txBytes.data)
                    )
                    if (broadRes is NetworkResult.Success) {
                        return broadRes.data
                    }
                }
            }

        } catch (e: Exception) {
            return JsonObject()
        }
        return JsonObject()
    }

    override suspend fun simulateSuiStake(
        fetcher: SuiFetcher, sender: String, amount: String, validator: String, gasBudget: String
    ): String {
        try {
            val txBytes = unsafeStake(fetcher, sender, amount, validator, gasBudget)

            if (txBytes is NetworkResult.Success) {
                val response = suiDryRun(fetcher, txBytes.data)
                if (response is NetworkResult.Success) {
                    if (response.data["error"] != null) {
                        return response.data["error"].asJsonObject["message"].asString

                    } else {
                        val computationCost =
                            response.data["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["computationCost"].asString.toBigDecimal()
                        val storageCost =
                            response.data["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["storageCost"].asString.toBigDecimal()
                        val storageRebate =
                            response.data["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["storageRebate"].asString.toBigDecimal()

                        val gasCost = if (storageCost > storageRebate) {
                            computationCost.add(storageCost).subtract(storageRebate).multiply(
                                BigDecimal("1.3")
                            ).setScale(0, RoundingMode.DOWN)
                        } else {
                            computationCost.multiply(
                                BigDecimal("1.3")
                            ).setScale(0, RoundingMode.DOWN)
                        }
                        return gasCost.toString()
                    }
                }
            }

        } catch (e: Exception) {
            return e.message.toString()
        }
        return ""
    }

    override suspend fun broadcastSuiUnStake(
        fetcher: SuiFetcher,
        sender: String,
        objectId: String,
        gasBudget: String,
        selectedChain: BaseChain
    ): JsonObject {
        try {
            val txBytes = unsafeUnStake(fetcher, sender, objectId, gasBudget)

            if (txBytes is NetworkResult.Success) {
                val dryRes = suiDryRun(fetcher, txBytes.data)
                if (dryRes is NetworkResult.Success && dryRes.data["error"] == null) {
                    val broadRes = suiExecuteTx(
                        fetcher, txBytes.data, Signer.suiSignature(selectedChain, txBytes.data)
                    )
                    if (broadRes is NetworkResult.Success) {
                        return broadRes.data
                    }
                }
            }

        } catch (e: Exception) {
            return JsonObject()
        }
        return JsonObject()
    }

    override suspend fun simulateSuiUnStake(
        fetcher: SuiFetcher, sender: String, objectId: String, gasBudget: String
    ): String {
        try {
            val txBytes = unsafeUnStake(fetcher, sender, objectId, gasBudget)

            if (txBytes is NetworkResult.Success) {
                val response = suiDryRun(fetcher, txBytes.data)
                if (response is NetworkResult.Success) {
                    if (response.data["error"] != null) {
                        return response.data["error"].asJsonObject["message"].asString

                    } else {
                        val computationCost =
                            response.data["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["computationCost"].asString.toBigDecimal()
                        val storageCost =
                            response.data["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["storageCost"].asString.toBigDecimal()
                        val storageRebate =
                            response.data["result"].asJsonObject["effects"].asJsonObject["gasUsed"].asJsonObject["storageRebate"].asString.toBigDecimal()

                        val gasCost = if (storageCost > storageRebate) {
                            computationCost.add(storageCost).subtract(storageRebate).multiply(
                                BigDecimal("1.3")
                            ).setScale(0, RoundingMode.DOWN)
                        } else {
                            computationCost.multiply(
                                BigDecimal("1.3")
                            ).setScale(0, RoundingMode.DOWN)
                        }
                        return gasCost.toString()
                    }
                }
            }

        } catch (e: Exception) {
            return e.message.toString()
        }
        return ""
    }

    override suspend fun mempoolUtxo(chain: ChainBitCoin84): NetworkResult<MutableList<JsonObject>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.bitApi(chain).bitUtxo(chain.mainAddress)
        }
    }

    override suspend fun estimateSmartFee(chain: ChainBitCoin84): NetworkResult<String> {
        return try {
            val estimateSmartFeeRequest = JsonRpcRequest(
                method = "estimatesmartfee", params = listOf(2)
            )
            val estimateSmartFeeResponse = jsonRpcResponse(chain.mainUrl, estimateSmartFeeRequest)
            val estimateSmartFeeJsonObject = Gson().fromJson(
                estimateSmartFeeResponse.body?.string(), JsonObject::class.java
            )
            safeApiCall(Dispatchers.IO) {
                val fee = estimateSmartFeeJsonObject["result"].asJsonObject["feerate"].asDouble
                val df = DecimalFormat("#.#############")
                df.format(fee)
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                ""
            }
        }
    }

    override suspend fun broadcastBitSend(chain: ChainBitCoin84, txHex: String): String? {
        val sendRawTransactionRequest = JsonRpcRequest(
            method = "sendrawtransaction", params = listOf(txHex)
        )
        val sendRawTransactionResponse =
            jsonRpcResponse(chain.mainUrl, sendRawTransactionRequest)
        val sendRawTransactionJsonObject = Gson().fromJson(
            sendRawTransactionResponse.body?.string(), JsonObject::class.java
        )
        return try {
            sendRawTransactionJsonObject["result"].asString
        } catch (e: Exception) {
            sendRawTransactionJsonObject["error"].asJsonObject["message"].asString
        }
    }

    override suspend fun simulateBitSend(
        chain: ChainBitCoin84,
        bitcoinJS: BitCoinJS?,
        sender: String,
        receiver: String,
        toAmount: String,
        changedValue: String,
        opReturn: String?,
        utxo: MutableList<JsonObject>?
    ): String? {
        chain.btcFetcher()?.let { fetcher ->
            try {
                val privateKey = chain.privateKey?.toHex()
                val publicKey = chain.publicKey?.toHex()

                val createTxFunction = """function createTxFunction() {
                        const privateKey = '${privateKey}';
                        const publicKey = '${publicKey}';

                        const senderPayment = getPayment('${publicKey}', '${fetcher.bitType()}', '${fetcher.network()}');

                        const inputs = [
                          ${fetcher.txInputString(utxo)}
                        ];

                        const outputs = [
                          ${fetcher.txOutputString(receiver, toAmount, changedValue, opReturn)}
                        ];

                        const txHex = createTx(inputs, outputs, '${privateKey}', '${fetcher.network()}');
                        return txHex;
                    }""".trimIndent()
                bitcoinJS?.mergeFunction(createTxFunction)
                return bitcoinJS?.executeFunction("createTxFunction()")

            } catch (e: Exception) {
                return e.message.toString()
            }
        }
        return ""
    }
}