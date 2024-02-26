package wannabit.io.cosmostaion.data.repository.tx

import com.binance.dex.api.client.BinanceDexApiClientFactory
import com.binance.dex.api.client.BinanceDexEnvironment
import com.binance.dex.api.client.Wallet
import com.binance.dex.api.client.domain.TransactionMetadata
import com.binance.dex.api.client.domain.broadcast.HtltReq
import com.binance.dex.api.client.domain.broadcast.TransactionOption
import com.binance.dex.api.client.domain.broadcast.Transfer
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
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainEvmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.common.BaseConstant.ICNS_OSMOSIS_ADDRESS
import wannabit.io.cosmostaion.common.BaseConstant.NS_ARCHWAY_ADDRESS
import wannabit.io.cosmostaion.common.BaseConstant.NS_STARGZE_ADDRESS
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.percentile
import wannabit.io.cosmostaion.common.soft
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.ICNSInfoReq
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.req.Msg
import wannabit.io.cosmostaion.data.model.req.NSArchwayReq
import wannabit.io.cosmostaion.data.model.req.NSStargazeInfoReq
import wannabit.io.cosmostaion.data.model.req.ResolveRecord
import wannabit.io.cosmostaion.data.model.res.LegacyRes
import wannabit.io.cosmostaion.data.model.res.Token
import java.math.BigDecimal
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
        selectedEvmChain: EthereumLine,
        selectedFeeInfo: Int
    ): Pair<String?, String?> {
        return try {
            val ecKey = ECKey.fromPrivate(selectedEvmChain.privateKey)
            val rpcUrl = selectedEvmChain.rpcURL

            val web3j = Web3j.build(HttpService(rpcUrl))
            val credentials: Credentials = Credentials.create(ecKey.privateKeyAsHex)
            val ethGetTransactionCount: EthGetTransactionCount =
                web3j.ethGetTransactionCount(credentials.address, DefaultBlockParameterName.LATEST)
                    .sendAsync().get()
            val chainID = web3j.ethChainId().send().chainId.toLong()
            val nonce = ethGetTransactionCount.transactionCount
            val gasLimit = web3j.ethEstimateGas(
                Transaction.createEthCallTransaction(
                    selectedEvmChain.address, toEthAddress, "0x"
                )
            ).sendAsync().get().amountUsed

            val request = JsonRpcRequest(
                method = "eth_feeHistory", params = listOf(
                    20, "pending", listOf(10, 30, 50, 70, 90)
                )
            )
            val jsonRequest = ObjectMapper().writeValueAsString(request)
            val rpcRequest = Request.Builder().url(selectedEvmChain.rpcURL)
                .post(jsonRequest.toRequestBody("application/json".toMediaTypeOrNull())).build()

            val response = OkHttpClient().newCall(rpcRequest).execute()
            if (response.isSuccessful) {
                val jsonResponse = response.body?.string()
                val jsonObject = Gson().fromJson(jsonResponse, JsonObject::class.java)

                val feeHistoryFeePerGas =
                    jsonObject.asJsonObject["result"].asJsonObject["baseFeePerGas"].asJsonArray
                val suggestGasValues = feeHistoryFeePerGas.map {
                    BigInteger(
                        it.asString.removePrefix("0x"), 16
                    )
                }.toMutableList()
                val suggestBaseFee = listOf(25.0, 50.0, 75.0).map {
                    suggestGasValues.percentile(it)
                }

                val reward = jsonObject.asJsonObject["result"].asJsonObject["reward"].asJsonArray
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

                if (suggestBaseFee.isNotEmpty() && suggestTipValue.isNotEmpty()) {
                    val baseFee = suggestBaseFee[selectedFeeInfo] ?: 27500000000
                    val tip = suggestTipValue[selectedFeeInfo]
                    val totalPerGas = baseFee.toLong() + tip.toLong()

                    val rawTransaction = RawTransaction.createEtherTransaction(
                        chainID,
                        nonce,
                        gasLimit,
                        toEthAddress,
                        toSendAmount?.toBigInteger(),
                        tip,
                        totalPerGas.toBigInteger()
                    )

                    val signedMessage = TransactionEncoder.signMessage(
                        rawTransaction, credentials
                    )
                    val hexValue = Numeric.toHexString(signedMessage)
                    val feeAmount =
                        gasLimit.multiply(totalPerGas.toBigInteger()).toBigDecimal().toString()

                    Pair(hexValue, feeAmount)
                } else {
                    Pair("", "")
                }
            } else {
                Pair("", "")
            }

        } catch (e: Exception) {
            Pair("", "")
        }
    }

    override suspend fun broadcastSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSend: TxProto.MsgSend?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genSendSimulate(account, msgSend, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun simulateSendTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgSend: TxProto.MsgSend?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
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

    override suspend fun broadcastBnbSendTx(
        transfer: Transfer, wallet: Wallet, options: TransactionOption
    ): MutableList<TransactionMetadata>? {
        return try {
            val client = BinanceDexApiClientFactory.newInstance()
                .newRestClient(BinanceDexEnvironment.PROD.baseUrl)
            client.transfer(transfer, wallet, options, true)

        } catch (_: Exception) {
            mutableListOf()
        }
    }

    override suspend fun broadcastOktTx(
        msgs: MutableList<Msg>, fee: LFee, memo: String, selectedChain: ChainOkt60
    ): LegacyRes? {
        return try {
            val reqBroadCast = Signer.oktBroadcast(msgs, fee, memo, selectedChain)
            RetrofitInstance.oktApi.broadTx(reqBroadCast)

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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genIbcSendSimulate(account, msgTransfer, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genWasmSimulate(account, msgWasms, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastErcSendTx(
        web3j: Web3j, hexValue: String
    ): String? {
        return try {
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()
            ethSendTransaction.transactionHash
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun simulateErcSendTx(
        toEthAddress: String?,
        toSendAmount: String?,
        selectedToken: Token?,
        selectedChain: CosmosLine
    ): Pair<String?, String?> {
        return try {
            val ecKey = ECKey.fromPrivate(selectedChain.privateKey)
            val rpcUrl = selectedChain.rpcUrl
            val fromEthAddress = ByteUtils.convertBech32ToEvm(selectedChain.address)

            val value = toSendAmount?.toBigInteger()

            val web3j = Web3j.build(HttpService(rpcUrl))
            val credentials: Credentials = Credentials.create(ecKey.privateKeyAsHex)
            val ethGetTransactionCount: EthGetTransactionCount =
                web3j.ethGetTransactionCount(credentials.address, DefaultBlockParameterName.LATEST)
                    .sendAsync().get()
            val nonce = ethGetTransactionCount.transactionCount

            val params: MutableList<Type<*>> = ArrayList()
            params.add(Address(toEthAddress))
            params.add(Uint256(value))

            val returnTypes = emptyList<TypeReference<*>>()

            val function = Function("transfer", params, returnTypes)
            val txData = FunctionEncoder.encode(function)

            val chainID: Long = web3j.ethChainId().send().chainId.toLong()
            val transaction = Transaction(
                fromEthAddress,
                nonce,
                BigInteger.ZERO,
                BigInteger.ZERO,
                toEthAddress,
                BigInteger.ZERO,
                txData
            )
            val rawTransaction: RawTransaction = if (selectedChain is ChainEvmos) {
                RawTransaction.createTransaction(
                    chainID,
                    nonce,
                    BigInteger.valueOf(900000L),
                    selectedToken?.address,
                    BigInteger.ZERO,
                    transaction.data,
                    BigInteger.valueOf(500000000L),
                    BigInteger.valueOf(27500000000L)
                )
            } else {
                RawTransaction.createTransaction(
                    nonce,
                    web3j.ethGasPrice().send().gasPrice,
                    BigInteger.valueOf(900000L),
                    selectedToken?.address,
                    BigInteger.ZERO,
                    transaction.data
                )
            }

            val signedMessage = TransactionEncoder.signMessage(rawTransaction, chainID, credentials)
            val hexValue = Numeric.toHexString(signedMessage)
            val newGasLimit = if (selectedChain is ChainEvmos) {
                rawTransaction.gasLimit.toBigDecimal()
            } else {
                web3j.ethEstimateGas(transaction).send().amountUsed.toString().toBigDecimal()
                    .multiply(
                        BigDecimal("1.1")
                    )
            }
            val gasPrice = web3j.ethGasPrice().send().gasPrice.toString().toBigDecimal()
            Pair(newGasLimit.multiply(gasPrice).toPlainString(), hexValue)

        } catch (e: Exception) {
            Pair(e.message, "")
        }
    }

    override suspend fun broadcastDelegateTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgDelegate: com.cosmos.staking.v1beta1.TxProto.MsgDelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genDelegateSimulate(account, msgDelegate, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genUnDelegateSimulate(account, msgUnDelegate, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genReDelegateSimulate(account, msgReDelegate, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genCancelUnbondingSimulate(account, msgCancelUnbondingDelegation, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genClaimRewardsSimulate(account, rewards, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genCompoundingSimulate(account, rewards, stakingDenom, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx =
                Signer.genChangeRewardAddressSimulate(account, msgSetWithdrawAddress, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genVoteSimulate(account, msgVotes, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genClaimIncentiveSimulate(account, incentive, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genMintCreateSimulate(account, msgCreateCDP, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genMintDepositSimulate(account, msgDeposit, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genMintWithdrawSimulate(account, msgWithdraw, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genMintBorrowSimulate(account, msgDrawDebt, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genMintRepaySimulate(account, msgRepayDebt, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genLendDepositSimulate(account, msgDeposit, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genLendWithdrawSimulate(account, msgWithdraw, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genLendBorrowSimulate(account, msgBorrow, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genLendRepaySimulate(account, msgRepay, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genPoolDepositSimulate(account, msgDeposit, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genPoolWithdrawSimulate(account, msgWithdraw, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genEarnDepositSimulate(account, msgDeposit, fee, memo)
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
        selectedChain: CosmosLine?
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
        memo: String
    ): Any? {
        return try {
            val simulStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val simulateTx = Signer.genEarnWithdrawSimulate(account, msgWithdraw, fee, memo)
            simulStub.simulate(simulateTx)

        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun broadcastCreateSwapTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgCreateAtomicSwap: com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx = Signer.genCreateSwapBroadcast(
                account, msgCreateAtomicSwap, fee, memo, selectedChain
            )
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun broadcastClaimSwapTx(
        managedChannel: ManagedChannel?,
        account: QueryAccountResponse?,
        msgClaimAtomicSwap: com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ): ServiceProto.BroadcastTxResponse? {
        return try {
            val txStub =
                newBlockingStub(managedChannel).withDeadlineAfter(duration, TimeUnit.SECONDS)
            val broadcastTx =
                Signer.genClaimSwapBroadcast(account, msgClaimAtomicSwap, fee, memo, selectedChain)
            txStub.broadcastTx(broadcastTx)

        } catch (_: Exception) {
            null
        }
    }

    override suspend fun broadcastBnbCreateSwapTx(
        htltReq: HtltReq, wallet: Wallet, options: TransactionOption
    ): MutableList<TransactionMetadata>? {
        return try {
            val client = BinanceDexApiClientFactory.newInstance()
                .newRestClient(BinanceDexEnvironment.PROD.baseUrl)
            client.htlt(htltReq, wallet, options, true)

        } catch (_: Exception) {
            mutableListOf()
        }
    }
}