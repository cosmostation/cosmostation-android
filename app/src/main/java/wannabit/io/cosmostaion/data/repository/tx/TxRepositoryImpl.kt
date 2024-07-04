package wannabit.io.cosmostaion.data.repository.tx

import com.cosmos.auth.v1beta1.QueryGrpc
import com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse
import com.cosmos.bank.v1beta1.TxProto
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.gov.v1beta1.TxProto.MsgVote
import com.cosmos.tx.v1beta1.ServiceGrpc.newBlockingStub
import com.cosmos.tx.v1beta1.ServiceProto
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.protobuf.ByteString
import com.kava.cdp.v1beta1.TxProto.MsgCreateCDP
import com.kava.cdp.v1beta1.TxProto.MsgDeposit
import com.kava.cdp.v1beta1.TxProto.MsgDrawDebt
import com.kava.cdp.v1beta1.TxProto.MsgRepayDebt
import com.kava.cdp.v1beta1.TxProto.MsgWithdraw
import com.kava.incentive.v1beta1.QueryProto
import io.grpc.ManagedChannel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.bitcoinj.core.ECKey
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
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.BaseConstant.ICNS_OSMOSIS_ADDRESS
import wannabit.io.cosmostaion.common.BaseConstant.NS_ARCHWAY_ADDRESS
import wannabit.io.cosmostaion.common.BaseConstant.NS_STARGZE_ADDRESS
import wannabit.io.cosmostaion.common.percentile
import wannabit.io.cosmostaion.common.soft
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.req.EstimateGasParams
import wannabit.io.cosmostaion.data.model.req.ICNSInfoReq
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.req.Msg
import wannabit.io.cosmostaion.data.model.req.NSArchwayReq
import wannabit.io.cosmostaion.data.model.req.NSStargazeInfoReq
import wannabit.io.cosmostaion.data.model.req.ResolveRecord
import wannabit.io.cosmostaion.data.model.res.LegacyRes
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import java.math.BigInteger
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
        managedChannel: ManagedChannel?, address: String?
    ): QueryAccountResponse? {
        return try {
            val authStub = QueryGrpc.newBlockingStub(managedChannel)
            val request = com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest.newBuilder()
                .setAddress(address).build()
            authStub.account(request)
        } catch (e: Exception) {
            null
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
                    20, "pending", listOf(10, 30, 50, 70, 90)
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
                    val resultArray = soft(rearrangedArray)
                    val suggestTipValue = if (resultArray.size > 3) {
                        val indexToRemove = setOf(3, 4)
                        resultArray.filterIndexed { index, _ -> index !in indexToRemove }
                    } else {
                        resultArray
                    }

                    val tip =
                        if (suggestTipValue[selectedFeeInfo] < BigInteger.valueOf(1000000000L)) {
                            BigInteger.valueOf(1000000000L)
                        } else {
                            suggestTipValue[selectedFeeInfo]
                        }

                    val totalPerGas =
                        if (suggestBaseFee[selectedFeeInfo] == null || suggestBaseFee[selectedFeeInfo]!! < BigInteger.valueOf(
                                500000000L
                            )
                        ) {
                            500000000L + tip.toLong()
                        } else {
                            suggestBaseFee[selectedFeeInfo]!!.toLong() + tip.toLong()
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
                            totalPerGas.toBigInteger()
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
                            totalPerGas.toBigInteger()
                        )
                    }

                    val signedMessage = TransactionEncoder.signMessage(
                        rawTransaction, chainID, credentials
                    )
                    val hexValue = Numeric.toHexString(signedMessage)
                    val feeAmount = gasLimit.multiply(totalPerGas.toBigInteger())

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
        return Pair("", "")
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
        selectedChain: EthereumLine,
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
        selectedChain: EthereumLine,
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
        selectedChain: EthereumLine,
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
        selectedChain: EthereumLine,
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
        proposalId: Long, proposalOption: Long, selectedChain: EthereumLine, selectedFeeInfo: Int
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

    override suspend fun broadcastSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSend: TxProto.MsgSend?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genSendBroadcast(account, msgSend, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (e: Exception) {
            null
        }
    }

    override suspend fun simulateSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSend: TxProto.MsgSend?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genSendSimulate(account, msgSend, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastOktTx(
        msgs: MutableList<Msg>, fee: LFee, memo: String, selectedChain: CosmosLine
    ): LegacyRes? {
        return try {
//            val reqBroadCast = Signer.oktBroadcast(msgs, fee, memo, selectedChain)
//            RetrofitInstance.oktApi.broadTx(reqBroadCast)
            null

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun broadcastIbcSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgTransfer: com.ibc.applications.transfer.v1.TxProto.MsgTransfer?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genIbcSendBroadcast(account, msgTransfer, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateIbcSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgTransfer: com.ibc.applications.transfer.v1.TxProto.MsgTransfer?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genIbcSendSimulate(account, msgTransfer, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastWasmTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWasms: MutableList<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract?>?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genWasmBroadcast(account, msgWasms, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateWasmTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWasms: MutableList<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract?>?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genWasmSimulate(account, msgWasms, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDelegate: com.cosmos.staking.v1beta1.TxProto.MsgDelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genDelegateBroadcast(account, msgDelegate, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDelegate: com.cosmos.staking.v1beta1.TxProto.MsgDelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genDelegateSimulate(account, msgDelegate, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastUnDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgUnDelegate: com.cosmos.staking.v1beta1.TxProto.MsgUndelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genUnDelegateBroadcast(account, msgUnDelegate, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateUnDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgUnDelegate: com.cosmos.staking.v1beta1.TxProto.MsgUndelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genUnDelegateSimulate(account, msgUnDelegate, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastReDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgReDelegate: com.cosmos.staking.v1beta1.TxProto.MsgBeginRedelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genReDelegateBroadcast(account, msgReDelegate, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateReDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgReDelegate: com.cosmos.staking.v1beta1.TxProto.MsgBeginRedelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genReDelegateSimulate(account, msgReDelegate, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastCancelUnbondingTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgCancelUnbondingDelegation: com.cosmos.staking.v1beta1.TxProto.MsgCancelUnbondingDelegation?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genCancelUnbondingBroadcast(
                account, msgCancelUnbondingDelegation, fee, memo, selectedChain
            )
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateCancelUnbondingTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgCancelUnbondingDelegation: com.cosmos.staking.v1beta1.TxProto.MsgCancelUnbondingDelegation?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genCancelUnbondingSimulate(
                account, msgCancelUnbondingDelegation, fee, memo, selectedChain
            )
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastGetRewardsTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        rewards: MutableList<DelegationDelegatorReward?>,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genClaimRewardsBroadcast(account, rewards, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateGetRewardsTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        rewards: MutableList<DelegationDelegatorReward?>,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genClaimRewardsSimulate(account, rewards, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastCompoundingTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        rewards: MutableList<DelegationDelegatorReward?>,
        stakingDenom: String?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genCompoundingBroadcast(
                account, rewards, stakingDenom, fee, memo, selectedChain
            )
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateCompoundingTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        rewards: MutableList<DelegationDelegatorReward?>,
        stakingDenom: String?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genCompoundingSimulate(
                account, rewards, stakingDenom, fee, memo, selectedChain
            )
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastChangeRewardAddressTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSetWithdrawAddress: com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genChangeRewardAddressBroadcast(
                account, msgSetWithdrawAddress, fee, memo, selectedChain
            )
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateChangeRewardAddressTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSetWithdrawAddress: com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genChangeRewardAddressSimulate(
                account, msgSetWithdrawAddress, fee, memo, selectedChain
            )
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastVoteTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgVotes: MutableList<MsgVote?>?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genVoteBroadcast(account, msgVotes, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateVoteTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgVotes: MutableList<MsgVote?>?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genVoteSimulate(account, msgVotes, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastClaimIncentiveTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        incentive: QueryProto.QueryRewardsResponse,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genClaimIncentiveBroadcast(account, incentive, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateClaimIncentiveTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        incentive: QueryProto.QueryRewardsResponse,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genClaimIncentiveSimulate(account, incentive, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastMintCreateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgCreateCDP: MsgCreateCDP?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genMintCreateBroadcast(account, msgCreateCDP, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateMintCreateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgCreateCDP: MsgCreateCDP?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genMintCreateSimulate(account, msgCreateCDP, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastMintDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genMintDepositBroadcast(account, msgDeposit, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateMintDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genMintDepositSimulate(account, msgDeposit, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastMintWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genMintWithdrawBroadcast(account, msgWithdraw, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateMintWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genMintWithdrawSimulate(account, msgWithdraw, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastMintBorrowTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDrawDebt: MsgDrawDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genMintBorrowBroadcast(account, msgDrawDebt, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateMintBorrowTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDrawDebt: MsgDrawDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genMintBorrowSimulate(account, msgDrawDebt, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastMintRepayTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgRepayDebt: MsgRepayDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genMintRepayBroadcast(account, msgRepayDebt, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateMintRepayTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgRepayDebt: MsgRepayDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genMintRepaySimulate(account, msgRepayDebt, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastLendDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.hard.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genLendDepositBroadcast(account, msgDeposit, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateLendDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.hard.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genLendDepositSimulate(account, msgDeposit, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastLendWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.hard.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genLendWithdrawBroadcast(account, msgWithdraw, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateLendWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.hard.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genLendWithdrawSimulate(account, msgWithdraw, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastLendBorrowTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgBorrow: com.kava.hard.v1beta1.TxProto.MsgBorrow?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genLendBorrowBroadcast(account, msgBorrow, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateLendBorrowTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgBorrow: com.kava.hard.v1beta1.TxProto.MsgBorrow?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genLendBorrowSimulate(account, msgBorrow, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastLendRepayTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgRepay: com.kava.hard.v1beta1.TxProto.MsgRepay?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genLendRepayBroadcast(account, msgRepay, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateLendRepayTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgRepay: com.kava.hard.v1beta1.TxProto.MsgRepay?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genLendRepaySimulate(account, msgRepay, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastPoolDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.swap.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genPoolDepositBroadcast(account, msgDeposit, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulatePoolDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.swap.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genPoolDepositSimulate(account, msgDeposit, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastPoolWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.swap.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genPoolWithdrawBroadcast(account, msgWithdraw, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulatePoolWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.swap.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genPoolWithdrawSimulate(account, msgWithdraw, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastEarnDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genEarnDepositBroadcast(account, msgDeposit, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateEarnDepositTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDeposit: com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genEarnDepositSimulate(account, msgDeposit, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastEarnWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.router.v1beta1.TxProto.MsgWithdrawBurn?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genEarnWithdrawBroadcast(account, msgWithdraw, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun simulateEarnWithdrawTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgWithdraw: com.kava.router.v1beta1.TxProto.MsgWithdrawBurn?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genEarnWithdrawSimulate(account, msgWithdraw, fee, memo, selectedChain)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }
}