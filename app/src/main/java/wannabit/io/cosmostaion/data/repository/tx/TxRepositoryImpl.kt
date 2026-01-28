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
import com.gno.bank.BankProto.MsgSend
import com.gno.vm.VmProto
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
import org.web3j.ens.EnsResolver
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthGetTransactionCount
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Numeric
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.evmClass.ChainEthereum
import wannabit.io.cosmostaion.chain.fetcher.AptosFetcher
import wannabit.io.cosmostaion.chain.fetcher.FUNGIBLE_FUNCTION_TYPE
import wannabit.io.cosmostaion.chain.fetcher.IotaFetcher
import wannabit.io.cosmostaion.chain.fetcher.SuiFetcher
import wannabit.io.cosmostaion.chain.fetcher.accountInfos
import wannabit.io.cosmostaion.chain.fetcher.accountNumber
import wannabit.io.cosmostaion.chain.fetcher.sequence
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainSolana
import wannabit.io.cosmostaion.chain.majorClass.IOTA_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.BaseConstant.ICNS_OSMOSIS_ADDRESS
import wannabit.io.cosmostaion.common.BaseConstant.NS_ARCHWAY_ADDRESS
import wannabit.io.cosmostaion.common.BaseConstant.NS_STARGZE_ADDRESS
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.bitType
import wannabit.io.cosmostaion.common.formatJsonString
import wannabit.io.cosmostaion.common.getOrNull
import wannabit.io.cosmostaion.common.jsonRpcResponse
import wannabit.io.cosmostaion.common.percentile
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.common.soft
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.api.RetrofitInstance.lcdApi
import wannabit.io.cosmostaion.data.model.req.BroadcastTxReq
import wannabit.io.cosmostaion.data.model.req.EstimateGasParams
import wannabit.io.cosmostaion.data.model.req.ICNSInfoReq
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.req.MoveStakeReq
import wannabit.io.cosmostaion.data.model.req.MoveUnStakeReq
import wannabit.io.cosmostaion.data.model.req.Msg
import wannabit.io.cosmostaion.data.model.req.NSArchwayReq
import wannabit.io.cosmostaion.data.model.req.NSStargazeInfoReq
import wannabit.io.cosmostaion.data.model.req.ResolveRecord
import wannabit.io.cosmostaion.data.model.req.SimulateTxReq
import wannabit.io.cosmostaion.data.model.res.LegacyRes
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.sign.BitcoinJs
import wannabit.io.cosmostaion.sign.Signer
import wannabit.io.cosmostaion.sign.SolanaJs
import wannabit.io.cosmostaion.ui.tx.genTx.SendAssetType
import xyz.mcxross.kaptos.account.Account
import xyz.mcxross.kaptos.core.crypto.Ed25519PrivateKey
import xyz.mcxross.kaptos.extension.toStructTag
import xyz.mcxross.kaptos.model.AccountAddress
import xyz.mcxross.kaptos.model.HexInput
import xyz.mcxross.kaptos.model.InputGenerateTransactionOptions
import xyz.mcxross.kaptos.model.InputSimulateTransactionOptions
import xyz.mcxross.kaptos.model.TypeTagStruct
import xyz.mcxross.kaptos.model.U64
import xyz.mcxross.kaptos.model.entryFunctionData
import xyz.mcxross.kaptos.model.functionArguments
import xyz.mcxross.kaptos.model.typeArguments
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

class TxRepositoryImpl : TxRepository {

    private var duration = 8L

    override suspend fun ensAddress(userInput: String?): String {
        val ethRpcUrl = ChainEthereum().evmRpcFetcher()?.getEvmRpc()
        val web3j = Web3j.build(HttpService(ethRpcUrl))
        val ens = EnsResolver(web3j)

        return try {
            val evmAddress = ens.resolve(userInput)
            return evmAddress
        } catch (e: Exception) {
            ""
        }
    }

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
        return if (chain is ChainGnoTestnet) {
            val authRequest = JsonRpcRequest(
                method = "abci_query",
                params = listOf("auth/accounts/${chain.address}", "", "0", true)
            )
            val authResponse =
                jsonRpcResponse(chain.gnoRpcFetcher()?.gnoRpc() ?: chain.mainUrl, authRequest)
            val jsonResponse = Gson().fromJson(
                authResponse.body?.string(), JsonObject::class.java
            )
            val data =
                jsonResponse["result"].asJsonObject["response"].asJsonObject["ResponseBase"].asJsonObject["Data"].asString
            val decodeData = formatJsonString(String(Base64.decode(data)))
            val dataJson = Gson().fromJson(decodeData, JsonObject::class.java)
            val accountData = dataJson["BaseAccount"].asJsonObject
            chain.gnoRpcFetcher()?.gnoAccountNumber =
                accountData["account_number"].asString.toLong()
            chain.gnoRpcFetcher()?.gnoSequence = accountData["sequence"].asString.toLong()

        } else if (chain.cosmosFetcher()?.endPointType(chain) == CosmosEndPointType.USE_GRPC) {
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

                if (suggestGasValues.isNotEmpty() && selectedChain.isEvmSupportEip1559()) {
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

                    val tip = suggestTipValue[selectedFeeInfo]
                    val baseFee = suggestBaseFee[selectedFeeInfo] ?: 0L
                    val evmGas = baseFee.toLong() + tip.toLong()

                    var rawTransaction: RawTransaction? = null

                    if (sendAssetType == SendAssetType.ONLY_EVM_COIN) {
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

                    if (sendAssetType == SendAssetType.ONLY_EVM_COIN) {
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
            val response = RetrofitInstance.moveApi.unSafeStake(
                MoveStakeReq(
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
            val response = RetrofitInstance.moveApi.unSafeUnStake(
                MoveUnStakeReq(
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
                        fetcher, txBytes.data, Signer.moveSignature(selectedChain, txBytes.data)
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
                        fetcher, txBytes.data, Signer.moveSignature(selectedChain, txBytes.data)
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
                        fetcher, txBytes.data, Signer.moveSignature(selectedChain, txBytes.data)
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
                        fetcher, txBytes.data, Signer.moveSignature(selectedChain, txBytes.data)
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

    // iota
    override suspend fun unSafePayIota(
        fetcher: IotaFetcher,
        sender: String,
        coins: MutableList<String>,
        recipient: MutableList<String>,
        amounts: MutableList<String>,
        gasBudget: String
    ): NetworkResult<String> {
        return try {
            val param = listOf(sender, coins, recipient, amounts, gasBudget)

            val iotaUnSafePayIotaRequest = JsonRpcRequest(
                method = "unsafe_payIota", params = param
            )
            val iotaUnSafePayIotaResponse =
                jsonRpcResponse(fetcher.iotaRpc(), iotaUnSafePayIotaRequest)
            val iotaUnSafePayIotaJsonObject = Gson().fromJson(
                iotaUnSafePayIotaResponse.body?.string(), JsonObject::class.java
            )
            safeApiCall(Dispatchers.IO) {
                iotaUnSafePayIotaJsonObject["result"].asJsonObject["txBytes"].asString
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                ""
            }
        }
    }

    override suspend fun unSafePayEtc(
        fetcher: IotaFetcher,
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
            val suiUnSafePayResponse = jsonRpcResponse(fetcher.iotaRpc(), suiUnSafePayRequest)
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

    override suspend fun unsafeIotaTransferObject(
        fetcher: IotaFetcher, sender: String, objectId: String, recipient: String, gasBudget: String
    ): NetworkResult<String> {
        return try {
            val param = listOf(sender, objectId, null, gasBudget, recipient)

            val iotaUnSafeTransferObjectRequest = JsonRpcRequest(
                method = "unsafe_transferObject", params = param
            )
            val iotaUnSafeTransferObjectResponse =
                jsonRpcResponse(fetcher.iotaRpc(), iotaUnSafeTransferObjectRequest)
            val iotaUnSafeTransferObjectJsonObject = Gson().fromJson(
                iotaUnSafeTransferObjectResponse.body?.string(), JsonObject::class.java
            )
            safeApiCall(Dispatchers.IO) {
                iotaUnSafeTransferObjectJsonObject["result"].asJsonObject["txBytes"].asString
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                ""
            }
        }
    }

    override suspend fun iotaDryRun(
        fetcher: IotaFetcher, txBytes: String
    ): NetworkResult<JsonObject> {
        return try {
            val iotaDryRunRequest = JsonRpcRequest(
                method = "iota_dryRunTransactionBlock", params = listOf(txBytes)
            )
            val iotaDryRunResponse = jsonRpcResponse(fetcher.iotaRpc(), iotaDryRunRequest)
            val iotaDryRunJsonObject = Gson().fromJson(
                iotaDryRunResponse.body?.string(), JsonObject::class.java
            )
            safeApiCall(Dispatchers.IO) {
                iotaDryRunJsonObject
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                JsonObject()
            }
        }
    }

    override suspend fun iotaExecuteTx(
        fetcher: IotaFetcher, txBytes: String, signatures: MutableList<String>
    ): NetworkResult<JsonObject> {
        return try {
            val param =
                listOf(txBytes, signatures, mapOf("showEffects" to true), "WaitForLocalExecution")
            val iotaExecuteRequest = JsonRpcRequest(
                method = "iota_executeTransactionBlock", params = param
            )
            val iotaExecuteResponse = jsonRpcResponse(fetcher.iotaRpc(), iotaExecuteRequest)
            val iotaxecuteJsonObject = Gson().fromJson(
                iotaExecuteResponse.body?.string(), JsonObject::class.java
            )
            safeApiCall(Dispatchers.IO) {
                iotaxecuteJsonObject
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                JsonObject()
            }
        }
    }

    override suspend fun broadcastIotaSend(
        fetcher: IotaFetcher,
        sendDenom: String,
        sender: String,
        coins: MutableList<String>,
        recipient: MutableList<String>,
        amounts: MutableList<String>,
        gasBudget: String,
        selectedChain: BaseChain
    ): JsonObject {
        try {
            val txBytes = if (sendDenom == IOTA_MAIN_DENOM) {
                unSafePayIota(
                    fetcher, sender, coins, recipient, amounts, gasBudget
                )
            } else {
                unSafePayEtc(
                    fetcher, sender, coins, recipient, amounts, gasBudget
                )
            }

            if (txBytes is NetworkResult.Success) {
                val dryRes = iotaDryRun(fetcher, txBytes.data)
                if (dryRes is NetworkResult.Success && dryRes.data["error"] == null) {
                    val broadRes = iotaExecuteTx(
                        fetcher, txBytes.data, Signer.moveSignature(selectedChain, txBytes.data)
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

    override suspend fun simulateIotaSend(
        fetcher: IotaFetcher,
        sendDenom: String,
        sender: String,
        coins: MutableList<String>,
        recipient: MutableList<String>,
        amounts: MutableList<String>,
        gasBudget: String
    ): String {
        try {
            val txBytes = if (sendDenom == IOTA_MAIN_DENOM) {
                unSafePayIota(
                    fetcher, sender, coins, recipient, amounts, gasBudget
                )
            } else {
                unSafePayEtc(
                    fetcher, sender, coins, recipient, amounts, gasBudget
                )
            }

            if (txBytes is NetworkResult.Success) {
                val response = iotaDryRun(fetcher, txBytes.data)
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

    override suspend fun broadcastIotaNftSend(
        fetcher: IotaFetcher,
        sender: String,
        objectId: String,
        recipient: String,
        gasBudget: String,
        selectedChain: BaseChain
    ): JsonObject {
        try {
            val txBytes = unsafeIotaTransferObject(fetcher, sender, objectId, recipient, gasBudget)

            if (txBytes is NetworkResult.Success) {
                val dryRes = iotaDryRun(fetcher, txBytes.data)
                if (dryRes is NetworkResult.Success && dryRes.data["error"] == null) {
                    val broadRes = iotaExecuteTx(
                        fetcher, txBytes.data, Signer.moveSignature(selectedChain, txBytes.data)
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

    override suspend fun simulateIotaNftSend(
        fetcher: IotaFetcher, sender: String, objectId: String, recipient: String, gasBudget: String
    ): String {
        try {
            val txBytes = unsafeIotaTransferObject(fetcher, sender, objectId, recipient, gasBudget)

            if (txBytes is NetworkResult.Success) {
                val response = iotaDryRun(fetcher, txBytes.data)
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

    override suspend fun broadcastIotaStake(
        fetcher: IotaFetcher,
        sender: String,
        validator: String,
        amount: String,
        gasBudget: String,
        selectedChain: BaseChain
    ): JsonObject {
        try {
            val txBytes = unsafeIotaStake(fetcher, sender, validator, amount, gasBudget)

            if (txBytes is NetworkResult.Success) {
                val dryRes = iotaDryRun(fetcher, txBytes.data)
                if (dryRes is NetworkResult.Success && dryRes.data["error"] == null) {
                    val broadRes = iotaExecuteTx(
                        fetcher, txBytes.data, Signer.moveSignature(selectedChain, txBytes.data)
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

    override suspend fun simulateIotaStake(
        fetcher: IotaFetcher, sender: String, amount: String, validator: String, gasBudget: String
    ): String {
        try {
            val txBytes = unsafeIotaStake(fetcher, sender, amount, validator, gasBudget)

            if (txBytes is NetworkResult.Success) {
                val response = iotaDryRun(fetcher, txBytes.data)
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

    override suspend fun broadcastIotaUnStake(
        fetcher: IotaFetcher,
        sender: String,
        objectId: String,
        gasBudget: String,
        selectedChain: BaseChain
    ): JsonObject {
        try {
            val txBytes = unsafeIotaUnStake(fetcher, sender, objectId, gasBudget)

            if (txBytes is NetworkResult.Success) {
                val dryRes = iotaDryRun(fetcher, txBytes.data)
                if (dryRes is NetworkResult.Success && dryRes.data["error"] == null) {
                    val broadRes = iotaExecuteTx(
                        fetcher, txBytes.data, Signer.moveSignature(selectedChain, txBytes.data)
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

    override suspend fun simulateIotaUnStake(
        fetcher: IotaFetcher, sender: String, objectId: String, gasBudget: String
    ): String {
        try {
            val txBytes = unsafeIotaUnStake(fetcher, sender, objectId, gasBudget)

            if (txBytes is NetworkResult.Success) {
                val response = iotaDryRun(fetcher, txBytes.data)
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

    override suspend fun unsafeIotaStake(
        fetcher: IotaFetcher, sender: String, amount: String, validator: String?, gasBudget: String
    ): NetworkResult<String> {
        return try {
            val response = RetrofitInstance.moveApi.unSafeIotaStake(
                MoveStakeReq(
                    sender, validator, gasBudget, amount, fetcher.iotaRpc()
                )
            )
            safeApiCall(Dispatchers.IO) {
                Base64.toBase64String(Utils.hexToBytes(response))
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                e.message.toString()
            }
        }
    }

    override suspend fun unsafeIotaUnStake(
        fetcher: IotaFetcher, sender: String, objectId: String, gasBudget: String
    ): NetworkResult<String> {
        return try {
            val response = RetrofitInstance.moveApi.unSafeIotaUnStake(
                MoveUnStakeReq(
                    sender, objectId, gasBudget, fetcher.iotaRpc()
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


    // bit
    override suspend fun mempoolUtxo(chain: ChainBitCoin86): NetworkResult<MutableList<JsonObject>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.bitApi(chain).bitUtxo(chain.mainAddress)
        }
    }

    override suspend fun estimateSmartFee(chain: ChainBitCoin86): NetworkResult<String> {
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

    override suspend fun broadcastBitSend(chain: ChainBitCoin86, txHex: String): String? {
        val sendRawTransactionRequest = JsonRpcRequest(
            method = "sendrawtransaction", params = listOf(txHex)
        )
        val sendRawTransactionResponse = jsonRpcResponse(chain.mainUrl, sendRawTransactionRequest)
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
        chain: ChainBitCoin86,
        bitcoinJS: BitcoinJs?,
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

                if (chain.accountKeyType.pubkeyType == PubKeyType.BTC_TAPROOT) {
                    val createTxFunction = """function createTxFunction() {
                        const privateKey = '${privateKey}';
                        const publicKey = '${publicKey}';

                        const senderPayment = getPayment('${publicKey}', '${bitType(chain.accountKeyType.pubkeyType)}', '${fetcher.network()}');

                        const inputs = [
                          ${fetcher.txInputString(utxo)}
                        ];

                        const outputs = [
                          ${fetcher.txOutputString(receiver, toAmount, changedValue, opReturn)}
                        ];

                        const txHex = createTaprootTx(inputs, outputs, '${privateKey}', '${fetcher.network()}');
                        return txHex;
                    }""".trimIndent()
                    bitcoinJS?.mergeFunction(createTxFunction)
                    val hex = bitcoinJS?.executeFunction("createTxFunction()").toString()
                    return hex

                } else {
                    val createTxFunction = """function createTxFunction() {
                        const privateKey = '${privateKey}';
                        const publicKey = '${publicKey}';

                        const senderPayment = getPayment('${publicKey}', '${bitType(chain.accountKeyType.pubkeyType)}', '${fetcher.network()}');

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
                }

            } catch (e: Exception) {
                return e.message.toString()
            }
        }
        return ""
    }

    override suspend fun broadcastSendRpcTx(
        msgSend: MsgSend, fee: Fee?, memo: String, selectedChain: BaseChain
    ): TxResponse? {
        return try {
            val broadcastTx = Signer.signRpcSendBroadcastTx(msgSend, fee, memo, selectedChain)
            val txByte = Base64.toBase64String(broadcastTx.toByteArray())
            val broadcastRequest = JsonRpcRequest(
                method = "broadcast_tx_async", params = listOf(txByte)
            )
            val broadcastResponse = jsonRpcResponse(
                selectedChain.gnoRpcFetcher?.gnoRpc() ?: selectedChain.mainUrl, broadcastRequest
            )
            val broadcastJsonObject = Gson().fromJson(
                broadcastResponse.body?.string(), JsonObject::class.java
            )

            val result = broadcastJsonObject["result"].asJsonObject
            return if (result["error"].isJsonNull) {
                TxResponse.newBuilder().setCode(0).setTxhash(result["hash"].asString).build()
            } else {
                TxResponse.newBuilder().setCode(-1).setTxhash(result["hash"].asString).build()
            }

        } catch (e: Exception) {
            null
        }
    }

    override suspend fun simulateSendRpcTx(
        msgSend: MsgSend, fee: Fee?, memo: String, selectedChain: BaseChain
    ): String {
        return try {
            val simulateTx = Signer.signRpcSendSimulateTx(msgSend, fee, memo, selectedChain)
            val txByte = Base64.toBase64String(simulateTx.toByteArray())
            val simulateRequest = JsonRpcRequest(
                method = "abci_query", params = listOf(".app/simulate", txByte, "0", false)
            )
            val simulateResponse = jsonRpcResponse(
                selectedChain.gnoRpcFetcher?.gnoRpc() ?: selectedChain.mainUrl, simulateRequest
            )
            val simulateJsonObject = Gson().fromJson(
                simulateResponse.body?.string(), JsonObject::class.java
            )

            if (simulateResponse.isSuccessful) {
                if (simulateJsonObject["result"].asJsonObject["response"].asJsonObject["Value"].isJsonNull) {
                    "Simulate Error"

                } else {
                    val value =
                        simulateJsonObject["result"].asJsonObject["response"].asJsonObject["Value"].asString
                    val responseBase =
                        com.tm2.abci.AbciProto.ResponseDeliverTx.parseFrom(Base64.decode(value.toByteArray()))

                    if (responseBase.responseBase.hasError()) {
                        responseBase.responseBase.error.typeUrl
                    } else {
                        responseBase.gasUsed.toString()
                    }
                }

            } else {
                "Rpc Error"
            }

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastCallRpcTx(
        msgCall: VmProto.MsgCall, fee: Fee?, memo: String, selectedChain: BaseChain
    ): TxResponse? {
        return try {
            val broadcastTx = Signer.signRpcCallBroadcastTx(msgCall, fee, memo, selectedChain)
            val txByte = Base64.toBase64String(broadcastTx.toByteArray())
            val broadcastRequest = JsonRpcRequest(
                method = "broadcast_tx_async", params = listOf(txByte)
            )
            val broadcastResponse = jsonRpcResponse(
                selectedChain.gnoRpcFetcher?.gnoRpc() ?: selectedChain.mainUrl, broadcastRequest
            )
            val broadcastJsonObject = Gson().fromJson(
                broadcastResponse.body?.string(), JsonObject::class.java
            )

            val result = broadcastJsonObject["result"].asJsonObject
            return if (result["error"].isJsonNull) {
                TxResponse.newBuilder().setCode(0).setTxhash(result["hash"].asString).build()
            } else {
                TxResponse.newBuilder().setCode(-1).setTxhash(result["hash"].asString).build()
            }

        } catch (e: Exception) {
            null
        }
    }

    override suspend fun simulateCallRpcTx(
        msgCall: VmProto.MsgCall, fee: Fee?, memo: String, selectedChain: BaseChain
    ): String {
        return try {
            val simulateTx = Signer.signRpcCallSimulateTx(msgCall, fee, memo, selectedChain)
            val txByte = Base64.toBase64String(simulateTx.toByteArray())
            val simulateRequest = JsonRpcRequest(
                method = "abci_query", params = listOf(".app/simulate", txByte, "0", false)
            )
            val simulateResponse = jsonRpcResponse(
                selectedChain.gnoRpcFetcher?.gnoRpc() ?: selectedChain.mainUrl, simulateRequest
            )
            val simulateJsonObject = Gson().fromJson(
                simulateResponse.body?.string(), JsonObject::class.java
            )

            if (simulateResponse.isSuccessful) {
                if (simulateJsonObject["result"].asJsonObject["response"].asJsonObject["Value"].isJsonNull) {
                    "Simulate Error"

                } else {
                    val value =
                        simulateJsonObject["result"].asJsonObject["response"].asJsonObject["Value"].asString
                    val responseBase =
                        com.tm2.abci.AbciProto.ResponseDeliverTx.parseFrom(Base64.decode(value.toByteArray()))

                    if (responseBase.responseBase.hasError()) {
                        responseBase.responseBase.error.typeUrl
                    } else {
                        responseBase.gasUsed.toString()
                    }
                }

            } else {
                "Rpc Error"
            }

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun mintPhotonRate(
        channel: ManagedChannel?, chain: BaseChain
    ): NetworkResult<String> {
        return if (chain.cosmosFetcher?.endPointType(chain) == CosmosEndPointType.USE_GRPC) {
            val stub = com.atomone.photon.v1.QueryGrpc.newBlockingStub(channel)
                .withDeadlineAfter(8, TimeUnit.SECONDS)
            val request =
                com.atomone.photon.v1.QueryProto.QueryConversionRateRequest.newBuilder().build()
            stub.conversionRate(request).conversionRate
            safeApiCall(Dispatchers.IO) {
                stub.conversionRate(request).conversionRate
            }

        } else {
            safeApiCall(Dispatchers.IO) {
                lcdApi(chain).conversionRate().let { response ->
                    response["conversion_rate"].asString
                }
            }
        }
    }

    override suspend fun minimumRentBalance(
        chain: ChainSolana, dataSize: Int
    ): NetworkResult<String> {
        return try {
            val rentParams = listOf(
                dataSize, mapOf("commitment" to "finalized")
            )
            val minimumRentRequest =
                JsonRpcRequest(method = "getMinimumBalanceForRentExemption", params = rentParams)
            val minimumRentResponse = jsonRpcResponse(
                chain.solanaFetcher()?.solanaRpc() ?: chain.mainUrl, minimumRentRequest
            )

            safeApiCall(Dispatchers.IO) {
                if (minimumRentResponse.isSuccessful) {
                    val minimumRentJsonObject = Gson().fromJson(
                        minimumRentResponse.body?.string(), JsonObject::class.java
                    )

                    if (minimumRentJsonObject.has("error")) {
                        "error"
                    } else {
                        minimumRentJsonObject["result"].asLong.toString()
                    }

                } else {
                    "error"
                }
            }

        } catch (e: Exception) {
            safeApiCall(Dispatchers.IO) {
                "error"
            }
        }
    }

    override suspend fun broadcastSolanaSendTx(
        chain: ChainSolana, solanaJS: SolanaJs?, programTxHex: String
    ): Pair<Boolean, String?> {
        return try {
            val privateKey = chain.privateKey?.toHex()
            val signTransactionFunction = """function signTransactionFunction() {
                        const txHex = signTransaction('${programTxHex}', '${privateKey}');
                         return txHex;
                        }""".trimMargin()
            solanaJS?.mergeFunction(signTransactionFunction)
            val txHex = solanaJS?.executeFunction("signTransactionFunction()")

            val sendParams = listOf(
                txHex, mapOf("encoding" to "base64")
            )
            val sendTransactionRequest =
                JsonRpcRequest(method = "sendTransaction", params = sendParams)
            val sendTransactionResponse = jsonRpcResponse(
                chain.solanaFetcher()?.solanaRpc() ?: chain.mainUrl, sendTransactionRequest
            )

            if (sendTransactionResponse.isSuccessful) {
                val sendTransactionJsonObject = Gson().fromJson(
                    sendTransactionResponse.body?.string(), JsonObject::class.java
                )

                if (sendTransactionJsonObject.has("error")) {
                    Pair(false, sendTransactionJsonObject["error"].asJsonObject["message"].asString)
                } else {
                    Pair(true, sendTransactionJsonObject["result"].asString)
                }

            } else {
                Pair(false, "error")
            }
        } catch (e: Exception) {
            Pair(false, e.message.toString())
        }
    }

    override suspend fun simulateSolSend(
        chain: ChainSolana, solanaJS: SolanaJs?, from: String, to: String, toAmount: String
    ): Pair<String?, Any> {
        return try {
            val params = listOf(
                mapOf("commitment" to "finalized")
            )
            val recentBlockHashRequest =
                JsonRpcRequest(method = "getLatestBlockhash", params = params)
            val recentBlockHashResponse = jsonRpcResponse(
                chain.solanaFetcher()?.solanaRpc() ?: chain.mainUrl, recentBlockHashRequest
            )

            if (recentBlockHashResponse.isSuccessful) {
                val recentBlockHashJsonObject = Gson().fromJson(
                    recentBlockHashResponse.body?.string(), JsonObject::class.java
                )
                if (recentBlockHashJsonObject.has("error") || recentBlockHashJsonObject["result"].asJsonObject.has(
                        "error"
                    )
                ) {
                    Pair("", "error")

                } else {
                    val recentBlockHash =
                        recentBlockHashJsonObject["result"].asJsonObject["value"].asJsonObject["blockhash"].asString

                    val createTransferTransactionFunction =
                        """function createTransferTransactionFunction() {
                    const txHex = createTransferTransactionWithSerialize('${from}', '${to}', '${toAmount}', '${recentBlockHash}');
                         return txHex;
                    }""".trimMargin()
                    solanaJS?.mergeFunction(createTransferTransactionFunction)
                    val serializedTxHex =
                        solanaJS?.executeFunction("createTransferTransactionFunction()")
                    val serializedTxHexJsonData = Gson().fromJson(
                        serializedTxHex, JsonObject::class.java
                    )

                    val txBase64 = serializedTxHexJsonData["serializedTxWithBase64"].asString
                    val txMessageWithBase64 =
                        serializedTxHexJsonData["serializedTxMessageWithBase64"].asString

                    val simulateParams = listOf(
                        txBase64, mapOf(
                            "commitment" to "confirmed",
                            "encoding" to "base64",
                            "replaceRecentBlockhash" to true
                        )
                    )

                    //simulate data
                    val simulationRequest =
                        JsonRpcRequest(method = "simulateTransaction", params = simulateParams)
                    val simulationResponse = jsonRpcResponse(
                        chain.solanaFetcher()?.solanaRpc() ?: chain.mainUrl, simulationRequest
                    )
                    val simulationJsonObject = Gson().fromJson(
                        simulationResponse.body?.string(), JsonObject::class.java
                    )
                    val simulateValue =
                        simulationJsonObject["result"].asJsonObject["value"].asJsonObject

                    if (!simulateValue["err"].isJsonNull) {
                        if (simulateValue["err"] is JsonObject) {
                            Pair("", simulateValue["err"].asJsonObject)
                        } else {
                            Pair("", simulateValue["err"].asString)
                        }

                    } else {
                        val unitsConsumed = simulateValue["unitsConsumed"].asLong

                        // fee data
                        val feeParams = listOf(
                            txMessageWithBase64, mapOf("commitment" to "processed")
                        )
                        val feeForMessageRequest =
                            JsonRpcRequest(method = "getFeeForMessage", params = feeParams)
                        val feeForMessageResponse = jsonRpcResponse(
                            chain.solanaFetcher()?.solanaRpc() ?: chain.mainUrl,
                            feeForMessageRequest
                        )
                        val feeForMessageJsonObject = Gson().fromJson(
                            feeForMessageResponse.body?.string(), JsonObject::class.java
                        )
                        val baseFee = feeForMessageJsonObject["result"].asJsonObject["value"].asLong

                        val prioritizationParams = listOf(
                            chain.mainAddress
                        )
                        val prioritizationFeeRequest = JsonRpcRequest(
                            method = "getRecentPrioritizationFees",
                            params = listOf(prioritizationParams)
                        )
                        val prioritizationFeeResponse = jsonRpcResponse(
                            chain.solanaFetcher()?.solanaRpc() ?: chain.mainUrl,
                            prioritizationFeeRequest
                        )
                        val prioritizationFeeJsonObject = Gson().fromJson(
                            prioritizationFeeResponse.body?.string(), JsonObject::class.java
                        )

                        var sumFee: Long = 0
                        val recentPrioritizationFees =
                            prioritizationFeeJsonObject["result"].asJsonArray
                        if (recentPrioritizationFees.size() > 0) {
                            recentPrioritizationFees.forEach { fee ->
                                sumFee += fee.asJsonObject["prioritizationFee"].asLong
                            }
                        }

                        val tipFee = if (sumFee > 0) {
                            (sumFee / recentPrioritizationFees.size() / 1000000) + baseFee / 10
                        } else {
                            baseFee / 10
                        }

                        val computeUnitLimit = unitsConsumed + baseFee / 10
                        val computeUnitPrice = tipFee.toDouble() / computeUnitLimit.toDouble()

                        val overwriteComputeBudgetProgramFunction =
                            """function overwriteComputeBudgetProgramFunction() {
                            const programTx = overwriteComputeBudgetProgram('${txBase64}', {
                                units: $computeUnitLimit,
                                microLamports: Math.ceil($computeUnitPrice * 1000000)
                            });
                            return programTx;
                        }""".trimMargin()
                        solanaJS?.mergeFunction(overwriteComputeBudgetProgramFunction)
                        val programTxHex =
                            solanaJS?.executeFunction("overwriteComputeBudgetProgramFunction()")
                        val fee = (baseFee + computeUnitLimit * computeUnitPrice).toBigDecimal()
                            .setScale(0, RoundingMode.UP)

                        Pair(programTxHex, fee.toString())
                    }
                }

            } else {
                Pair("", "error")
            }

        } catch (e: Exception) {
            Pair("", e.message.toString())
        }
    }

    override suspend fun simulateSplSend(
        chain: ChainSolana,
        solanaJS: SolanaJs?,
        from: String,
        to: String,
        mint: String,
        toAmount: String
    ): Triple<Boolean?, String?, Any> {
        return try {
            val getAssociatedTokenAddressFunction =
                """function getAssociatedTokenAddressFunction() {
                    const receiverATA = getAssociatedTokenAddress('${mint}', '${to}');
                         return receiverATA;
                    }""".trimMargin()
            solanaJS?.mergeFunction(getAssociatedTokenAddressFunction)
            val receiverATA = solanaJS?.executeFunction("getAssociatedTokenAddressFunction()")

            val accountInfoParams = listOf(
                receiverATA,
                mapOf("commitment" to "finalized", "encoding" to "base64"),
            )

            val accountInfoRequest = JsonRpcRequest(
                method = "getAccountInfo", params = accountInfoParams
            )
            val accountInfoResponse = jsonRpcResponse(
                chain.solanaFetcher?.solanaRpc() ?: chain.mainUrl, accountInfoRequest
            )
            val accountInfoJsonObject = Gson().fromJson(
                accountInfoResponse.body?.string(), JsonObject::class.java
            )

            if (accountInfoResponse.isSuccessful) {
                val isCreateAssociatedTokenAccount =
                    accountInfoJsonObject["result"].asJsonObject["value"].isJsonNull

                val params = listOf(
                    mapOf("commitment" to "finalized")
                )
                val recentBlockHashRequest =
                    JsonRpcRequest(method = "getLatestBlockhash", params = params)
                val recentBlockHashResponse = jsonRpcResponse(
                    chain.solanaFetcher()?.solanaRpc() ?: chain.mainUrl, recentBlockHashRequest
                )

                val recentBlockHashJsonObject = Gson().fromJson(
                    recentBlockHashResponse.body?.string(), JsonObject::class.java
                )
                if (recentBlockHashJsonObject.has("error") || recentBlockHashJsonObject["result"].asJsonObject.has(
                        "error"
                    )
                ) {
                    Triple(null, "", "error")

                } else {
                    val recentBlockHash =
                        recentBlockHashJsonObject["result"].asJsonObject["value"].asJsonObject["blockhash"].asString

                    val createSplTokenTransferTransactionFunction =
                        """function createSplTokenTransferTransactionFunction() {
                    const txHex = createSplTokenTransferTransactionWithSerialize('${from}', '${to}', '${mint}', '${toAmount}', '${recentBlockHash}', ${isCreateAssociatedTokenAccount});
                         return txHex;
                    }""".trimMargin()
                    solanaJS?.mergeFunction(createSplTokenTransferTransactionFunction)
                    val serializedTxHex =
                        solanaJS?.executeFunction("createSplTokenTransferTransactionFunction()")
                    val serializedTxHexJsonData = Gson().fromJson(
                        serializedTxHex, JsonObject::class.java
                    )

                    val txBase64 = serializedTxHexJsonData["serializedTxWithBase64"].asString
                    val txMessageWithBase64 =
                        serializedTxHexJsonData["serializedTxMessageWithBase64"].asString

                    val simulateParams = listOf(
                        txBase64, mapOf(
                            "commitment" to "confirmed",
                            "encoding" to "base64",
                            "replaceRecentBlockhash" to true
                        )
                    )

                    //simulate data
                    val simulationRequest =
                        JsonRpcRequest(method = "simulateTransaction", params = simulateParams)
                    val simulationResponse = jsonRpcResponse(
                        chain.solanaFetcher()?.solanaRpc() ?: chain.mainUrl, simulationRequest
                    )
                    val simulationJsonObject = Gson().fromJson(
                        simulationResponse.body?.string(), JsonObject::class.java
                    )
                    val simulateValue =
                        simulationJsonObject["result"].asJsonObject["value"].asJsonObject

                    if (!simulateValue["err"].isJsonNull) {
                        if (simulateValue["err"] is JsonObject) {
                            Triple(null, "", simulateValue["err"].asJsonObject)
                        } else {
                            Triple(null, "", simulateValue["err"].asString)
                        }

                    } else {
                        val unitsConsumed = simulateValue["unitsConsumed"].asLong

                        // fee data
                        val feeParams = listOf(
                            txMessageWithBase64, mapOf("commitment" to "processed")
                        )
                        val feeForMessageRequest =
                            JsonRpcRequest(method = "getFeeForMessage", params = feeParams)
                        val feeForMessageResponse = jsonRpcResponse(
                            chain.solanaFetcher()?.solanaRpc() ?: chain.mainUrl,
                            feeForMessageRequest
                        )
                        val feeForMessageJsonObject = Gson().fromJson(
                            feeForMessageResponse.body?.string(), JsonObject::class.java
                        )
                        val baseFee = feeForMessageJsonObject["result"].asJsonObject["value"].asLong

                        val prioritizationParams = listOf(
                            chain.mainAddress
                        )
                        val prioritizationFeeRequest = JsonRpcRequest(
                            method = "getRecentPrioritizationFees",
                            params = listOf(prioritizationParams)
                        )
                        val prioritizationFeeResponse = jsonRpcResponse(
                            chain.solanaFetcher()?.solanaRpc() ?: chain.mainUrl,
                            prioritizationFeeRequest
                        )
                        val prioritizationFeeJsonObject = Gson().fromJson(
                            prioritizationFeeResponse.body?.string(), JsonObject::class.java
                        )

                        var sumFee: Long = 0
                        val recentPrioritizationFees =
                            prioritizationFeeJsonObject["result"].asJsonArray
                        if (recentPrioritizationFees.size() > 0) {
                            recentPrioritizationFees.forEach { fee ->
                                sumFee += fee.asJsonObject["prioritizationFee"].asLong
                            }
                        }

                        val tipFee = if (sumFee > 0) {
                            (sumFee / recentPrioritizationFees.size() / 1000000) + baseFee / 10
                        } else {
                            baseFee / 10
                        }

                        val computeUnitLimit = unitsConsumed + baseFee / 10
                        val computeUnitPrice = tipFee.toDouble() / computeUnitLimit.toDouble()

                        val overwriteComputeBudgetProgramFunction =
                            """function overwriteComputeBudgetProgramFunction() {
                            const programTx = overwriteComputeBudgetProgram('${txBase64}', {
                                units: $computeUnitLimit,
                                microLamports: Math.ceil($computeUnitPrice * 1000000)
                            });
                            return programTx;
                        }""".trimMargin()
                        solanaJS?.mergeFunction(overwriteComputeBudgetProgramFunction)
                        val programTxHex =
                            solanaJS?.executeFunction("overwriteComputeBudgetProgramFunction()")
                        val fee = (baseFee + computeUnitLimit * computeUnitPrice).toBigDecimal()
                            .setScale(0, RoundingMode.UP)

                        Triple(isCreateAssociatedTokenAccount, programTxHex, fee.toString())
                    }
                }

            } else {
                Triple(null, "", "error")
            }

        } catch (e: Exception) {
            Triple(null, "", e.message.toString())
        }
    }

    override suspend fun broadcastMoveSend(
        chain: BaseChain,
        fetcher: AptosFetcher,
        from: String,
        to: String,
        toAmount: String,
        toDenom: String,
        maxGasAmount: String
    ): String {
        return try {
            val privateKey = Ed25519PrivateKey(chain.privateKey?.toHex() ?: "")
            val account = Account.from(privateKey)
            val transactionOption =
                InputGenerateTransactionOptions(maxGasAmount = maxGasAmount.toLong())

            BaseData.getAsset(chain.apiName, toDenom)?.let { asset ->
                val transaction = if (asset.type == "fungible") {
                    fetcher.aptosClient().buildTransaction.simple(
                        sender = AccountAddress.fromString(from), data = entryFunctionData {
                            function = FUNGIBLE_FUNCTION_TYPE
                            typeArguments = typeArguments {
                                +TypeTagStruct(type = "0x1::fungible_asset::Metadata".toStructTag())
                            }
                            functionArguments = functionArguments {
                                +HexInput(toDenom)
                                +AccountAddress.fromString(to)
                                +U64(toAmount.toULong())
                            }
                        }, withFeePayer = false, options = transactionOption
                    )

                } else {
                    fetcher.aptosClient().transferCoinTransaction(
                        from = AccountAddress.fromString(from),
                        to = AccountAddress.fromString(to),
                        amount = toAmount.toULong(),
                        coinType = toDenom,
                        withFeePayer = false,
                        options = transactionOption
                    )
                }

                val submit =
                    fetcher.aptosClient().signAndSubmitTransaction(account, transaction)
                submit.getOrNull()?.hash ?: ""

            } ?: run {
                "Not Support Coin"
            }

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun simulateMoveSend(
        chain: BaseChain,
        fetcher: AptosFetcher,
        from: String,
        to: String,
        toAmount: String,
        toDenom: String
    ): String {
        return try {
            val privateKey = Ed25519PrivateKey(chain.privateKey?.toHex() ?: "")
            val account = Account.from(privateKey)
            val transactionOption = InputGenerateTransactionOptions(maxGasAmount = 1000)

            BaseData.getAsset(chain.apiName, toDenom)?.let { asset ->
                val transaction = if (asset.type == "fungible") {
                    fetcher.aptosClient().buildTransaction.simple(
                        sender = AccountAddress.fromString(from), data = entryFunctionData {
                            function = FUNGIBLE_FUNCTION_TYPE
                            typeArguments = typeArguments {
                                +TypeTagStruct(type = "0x1::fungible_asset::Metadata".toStructTag())
                            }
                            functionArguments = functionArguments {
                                +HexInput(toDenom)
                                +AccountAddress.fromString(to)
                                +U64(toAmount.toULong())
                            }
                        }, withFeePayer = false, options = transactionOption
                    )

                } else {
                    fetcher.aptosClient().transferCoinTransaction(
                        from = AccountAddress.fromString(from),
                        to = AccountAddress.fromString(to),
                        amount = toAmount.toULong(),
                        coinType = toDenom,
                        withFeePayer = false,
                        options = transactionOption
                    )
                }

                val opts = InputSimulateTransactionOptions(
                    estimateGasUnitPrice = false,
                    estimateMaxGasAmount = false,
                    estimatePrioritizedGasUnitPrice = false
                )

                val simulateTxs = fetcher.aptosClient().simulateTransaction.simple(
                    signerPublicKey = account.publicKey,
                    transaction = transaction,
                    feePayerPublicKey = null,
                    options = opts
                )
                val simulateResult = simulateTxs.getOrNull().orEmpty()

                if (simulateResult.isNotEmpty()) {
                    val gasUsed = simulateResult[0].gasUsed.toBigDecimal()
                    val gasPrice = simulateResult[0].gasUnitPrice.toBigDecimal()

                    gasUsed.multiply(gasPrice).toString()

                } else {
                    "RPC Error"
                }

            } ?: run {
                "Not Support Coin"
            }

        } catch (e: Exception) {
            e.message.toString()
        }
    }
}