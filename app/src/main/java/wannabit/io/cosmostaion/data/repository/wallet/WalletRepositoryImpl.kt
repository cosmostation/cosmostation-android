package wannabit.io.cosmostaion.data.repository.wallet

import com.cosmos.auth.v1beta1.QueryProto
import com.cosmos.bank.v1beta1.QueryGrpc
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse
import com.cosmos.base.query.v1beta1.PaginationProto
import com.cosmos.distribution.v1beta1.QueryProto.QueryDelegationTotalRewardsRequest
import com.cosmos.distribution.v1beta1.QueryProto.QueryDelegationTotalRewardsResponse
import com.cosmos.staking.v1beta1.QueryGrpc.newBlockingStub
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorDelegationsRequest
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorDelegationsResponse
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorUnbondingDelegationsRequest
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorUnbondingDelegationsResponse
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.protobuf.ByteString
import io.grpc.ManagedChannel
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Type
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.http.HttpService
import retrofit2.Response
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_VAULT_ADDRESS
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_VESTING_CONTRACT_ADDRESS
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance.baseApi
import wannabit.io.cosmostaion.data.api.RetrofitInstance.ecoApi
import wannabit.io.cosmostaion.data.api.RetrofitInstance.mintscanApi
import wannabit.io.cosmostaion.data.api.RetrofitInstance.mintscanJsonApi
import wannabit.io.cosmostaion.data.api.RetrofitInstance.oktApi
import wannabit.io.cosmostaion.data.api.RetrofitInstance.walletApi
import wannabit.io.cosmostaion.data.model.req.Allocation
import wannabit.io.cosmostaion.data.model.req.AllocationReq
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.model.req.NftInfo
import wannabit.io.cosmostaion.data.model.req.StarCw721TokenIdReq
import wannabit.io.cosmostaion.data.model.req.StarCw721TokenInfoReq
import wannabit.io.cosmostaion.data.model.req.VotingPower
import wannabit.io.cosmostaion.data.model.req.VotingPowerReq
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.AssetResponse
import wannabit.io.cosmostaion.data.model.res.Cw20Balance
import wannabit.io.cosmostaion.data.model.res.MoonPay
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.OktAccountResponse
import wannabit.io.cosmostaion.data.model.res.OktDepositedResponse
import wannabit.io.cosmostaion.data.model.res.OktTokenResponse
import wannabit.io.cosmostaion.data.model.res.OktWithdrawResponse
import wannabit.io.cosmostaion.data.model.res.Price
import wannabit.io.cosmostaion.data.model.res.PushStatus
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.Password
import java.math.BigInteger
import java.util.concurrent.TimeUnit

class WalletRepositoryImpl : WalletRepository {

    private var duration = 8L

    override suspend fun selectPassword(): NetworkResult<MutableList<Password>> {
        return safeApiCall(Dispatchers.IO) {
            AppDatabase.getInstance().passwordDao().selectAll()
        }
    }

    override suspend fun insertPassword(password: Password) {
        AppDatabase.getInstance().passwordDao().insert(password)
    }

    override suspend fun version(): NetworkResult<Response<AppVersion>> {
        return safeApiCall(Dispatchers.IO) {
            walletApi.version()
        }
    }

    override suspend fun price(currency: String): NetworkResult<List<Price>> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.price(currency)
        }
    }

    override suspend fun usdPrice(): NetworkResult<List<Price>> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.price("usd")
        }
    }

    override suspend fun pushStatus(fcmToken: String): NetworkResult<Response<PushStatus>> {
        return safeApiCall(Dispatchers.IO) {
            walletApi.pushStatus(fcmToken)
        }
    }

    override suspend fun asset(): NetworkResult<AssetResponse> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.asset()
        }
    }

    override suspend fun param(): NetworkResult<JsonObject?> {
        return safeApiCall(Dispatchers.IO) {
            mintscanJsonApi.param()
        }
    }

    override suspend fun token(line: CosmosLine): NetworkResult<MutableList<Token>> {
        return safeApiCall(Dispatchers.IO) {
            if (line.supportCw20) {
                mintscanApi.cw20token(line.apiName)
            } else {
                mintscanApi.erc20token(line.apiName)
            }
        }
    }

    override suspend fun auth(
        managedChannel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryProto.QueryAccountResponse?> {
        val stub = com.cosmos.auth.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = QueryProto.QueryAccountRequest.newBuilder().setAddress(line.address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.account(request)
        }
    }

    override suspend fun balance(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryAllBalancesResponse?> {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(2000).build()
        val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(line.address)
                .build()
        return safeApiCall(Dispatchers.IO) {
            stub.allBalances(request)
        }
    }

    override suspend fun delegation(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryDelegatorDelegationsResponse> {
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryDelegatorDelegationsRequest.newBuilder().setDelegatorAddr(line.address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.delegatorDelegations(request)
        }
    }

    override suspend fun unBonding(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryDelegatorUnbondingDelegationsResponse> {
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryDelegatorUnbondingDelegationsRequest.newBuilder().setDelegatorAddr(line.address)
                .build()
        return safeApiCall(Dispatchers.IO) {
            stub.delegatorUnbondingDelegations(request)
        }
    }

    override suspend fun reward(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryDelegationTotalRewardsResponse> {
        val stub = com.cosmos.distribution.v1beta1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryDelegationTotalRewardsRequest.newBuilder().setDelegatorAddress(line.address)
                .build()
        return safeApiCall(Dispatchers.IO) {
            stub.delegationTotalRewards(request)
        }
    }

    override suspend fun rewardAddress(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<String> {
        val stub = com.cosmos.distribution.v1beta1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            com.cosmos.distribution.v1beta1.QueryProto.QueryDelegatorWithdrawAddressRequest.newBuilder()
                .setDelegatorAddress(line.address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.delegatorWithdrawAddress(request).withdrawAddress
        }
    }

    override suspend fun bondedValidator(
        channel: ManagedChannel
    ): NetworkResult<MutableList<StakingProto.Validator>> {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(500).build()
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = com.cosmos.staking.v1beta1.QueryProto.QueryValidatorsRequest.newBuilder()
            .setPagination(pageRequest).setStatus("BOND_STATUS_BONDED").build()
        return safeApiCall(Dispatchers.IO) {
            stub.validators(request).validatorsList
        }
    }

    override suspend fun unBondedValidator(
        channel: ManagedChannel
    ): NetworkResult<MutableList<StakingProto.Validator>> {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(500).build()
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = com.cosmos.staking.v1beta1.QueryProto.QueryValidatorsRequest.newBuilder()
            .setPagination(pageRequest).setStatus("BOND_STATUS_UNBONDED").build()
        return safeApiCall(Dispatchers.IO) {
            stub.validators(request).validatorsList
        }
    }

    override suspend fun unBondingValidator(
        channel: ManagedChannel
    ): NetworkResult<MutableList<StakingProto.Validator>> {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(500).build()
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = com.cosmos.staking.v1beta1.QueryProto.QueryValidatorsRequest.newBuilder()
            .setPagination(pageRequest).setStatus("BOND_STATUS_UNBONDING").build()
        return safeApiCall(Dispatchers.IO) {
            stub.validators(request).validatorsList
        }
    }

    override suspend fun moonPay(data: MoonPayReq): NetworkResult<Response<MoonPay>> {
        return safeApiCall(Dispatchers.IO) {
            walletApi.moonPay(data)
        }
    }

    override suspend fun cw20Balance(
        channel: ManagedChannel, line: CosmosLine, token: Token
    ) {
        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val req = Cw20Balance(line.address)
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val request = com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder()
            .setAddress(token.address).setQueryData(queryData).build()

        try {
            stub.smartContractState(request)?.let { response ->
                val json = JSONObject(response.data.toStringUtf8())
                token.amount = json.get("balance").toString()
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun erc20Balance(line: CosmosLine, token: Token) {
        val web3j = if (line is EthereumLine) {
            line.web3j
        } else {
            Web3j.build(HttpService(line.rpcUrl))
        }
        val ethAddress = if (line is EthereumLine) {
            if (line.supportCosmos) {
                ByteUtils.convertBech32ToEvm(line.address)
            } else {
                line.address.toString()
            }
        } else {
            ByteUtils.convertBech32ToEvm(line.address)
        }
        val params: MutableList<Type<*>> = ArrayList()
        params.add(Address(ethAddress))

        try {
            val returnTypes = listOf<TypeReference<*>>(object : TypeReference<Uint256?>() {})
            val function = Function("balanceOf", params, returnTypes)

            val txData = FunctionEncoder.encode(function)
            val response = web3j?.ethCall(
                Transaction.createEthCallTransaction(ethAddress, token.address, txData),
                DefaultBlockParameterName.LATEST
            )?.sendAsync()?.get()
            val results = FunctionReturnDecoder.decode(response?.value, function.outputParameters)
            if (results.isNotEmpty()) {
                val balance = results[0].value as BigInteger
                token.amount = balance.toString()
            } else {
                token.amount = "0"
            }
        } catch (e: Exception) {
            token.amount = "0"
        }
    }

    override suspend fun vestingData(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QuerySmartContractStateResponse> {
        val req = AllocationReq(Allocation(line.address))
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder()
            .setAddress(NEUTRON_VESTING_CONTRACT_ADDRESS).setQueryData(queryData).build()

        return safeApiCall(Dispatchers.IO) {
            stub.smartContractState(request)
        }
    }

    override suspend fun vaultDeposit(
        channel: ManagedChannel,
        line: ChainNeutron,
    ): NetworkResult<String?> {
        val req = VotingPowerReq(VotingPower(line.address))
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request =
            com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder().setAddress(
                NEUTRON_VAULT_ADDRESS
            ).setQueryData(queryData).build()

        return safeApiCall(Dispatchers.IO) {
            stub.smartContractState(request)?.let { response ->
                val json = JSONObject(response.data.toStringUtf8())
                json.getString("power")
            }
        }
    }

    override suspend fun oktAccountInfo(line: CosmosLine): NetworkResult<OktAccountResponse?> {
        return safeApiCall(Dispatchers.IO) {
            oktApi.oktAccountInfo(line.address)
        }
    }

    override suspend fun oktDeposit(line: CosmosLine): NetworkResult<OktDepositedResponse?> {
        return safeApiCall(Dispatchers.IO) {
            oktApi.oktDepositInfo(line.address)
        }
    }

    override suspend fun oktWithdraw(line: CosmosLine): NetworkResult<OktWithdrawResponse?> {
        return safeApiCall(Dispatchers.IO) {
            oktApi.oktWithdrawInfo(line.address)
        }
    }

    override suspend fun oktToken(line: CosmosLine): NetworkResult<OktTokenResponse?> {
        return safeApiCall(Dispatchers.IO) {
            oktApi.oktTokens()
        }
    }

    override suspend fun evmToken(evmLine: EthereumLine): NetworkResult<MutableList<Token>> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.erc20token(evmLine.apiName)
        }
    }

    override suspend fun evmBalance(evmLine: EthereumLine): NetworkResult<String> {
        return safeApiCall(Dispatchers.IO) {
            val web3j = Web3j.build(HttpService(evmLine.getEvmRpc()))
            val evmAddress = if (evmLine.supportCosmos) {
                ByteUtils.convertBech32ToEvm(evmLine.address)
            } else {
                evmLine.address
            }
            val balance = web3j.ethGetBalance(evmAddress, DefaultBlockParameterName.LATEST).send()
            balance.balance.toString()
        }
    }

    override suspend fun cw721Info(chain: String): NetworkResult<MutableList<JsonObject>> {
        return safeApiCall(Dispatchers.IO) {
            baseApi.cw721Info(chain)
        }
    }

    override suspend fun cw721TokenIds(
        channel: ManagedChannel,
        line: CosmosLine,
        list: JsonObject
    ): NetworkResult<JsonObject?> {
        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val req = StarCw721TokenIdReq(wannabit.io.cosmostaion.data.model.req.Token(line.address))
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val request = com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder()
            .setAddress(list.asJsonObject["contractAddress"].asString).setQueryData(queryData)
            .build()
        return safeApiCall(Dispatchers.IO) {
            stub.smartContractState(request)?.let { response ->
                val tokenIds = Gson().fromJson(response.data.toStringUtf8(), JsonObject::class.java)
                if (tokenIds.asJsonObject["tokens"].asJsonArray.size() > 0) {
                    tokenIds
                } else {
                    JsonObject()
                }
            }
        }
    }

    override suspend fun cw721TokenInfo(
        channel: ManagedChannel,
        line: CosmosLine,
        list: JsonObject,
        tokenId: String
    ): NetworkResult<JsonObject?> {
        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val req = StarCw721TokenInfoReq(NftInfo(tokenId))
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val request = com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder()
            .setAddress(list.asJsonObject["contractAddress"].asString).setQueryData(queryData)
            .build()
        return safeApiCall(Dispatchers.IO) {
            stub.smartContractState(request)?.let { response ->
                Gson().fromJson(response.data.toStringUtf8(), JsonObject::class.java)
            }
        }
    }

    override suspend fun cw721TokenDetail(
        line: CosmosLine,
        contractAddress: String,
        tokenId: String
    ): NetworkResult<JsonObject> {
        return safeApiCall(Dispatchers.IO) {
            mintscanJsonApi.cw721Detail(line.apiName, contractAddress, tokenId)
        }
    }

    override suspend fun ecoSystem(chain: String): NetworkResult<MutableList<JsonObject>> {
        return safeApiCall(Dispatchers.IO) {
            ecoApi.ecoSystemInfo(chain)
        }
    }
}