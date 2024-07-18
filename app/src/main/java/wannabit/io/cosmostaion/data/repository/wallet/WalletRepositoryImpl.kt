package wannabit.io.cosmostaion.data.repository.wallet

import com.cosmos.auth.v1beta1.QueryProto
import com.cosmos.bank.v1beta1.QueryGrpc
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse
import com.cosmos.base.query.v1beta1.PaginationProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.distribution.v1beta1.QueryProto.QueryDelegationTotalRewardsRequest
import com.cosmos.distribution.v1beta1.QueryProto.QueryDelegationTotalRewardsResponse
import com.cosmos.staking.v1beta1.QueryGrpc.newBlockingStub
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorDelegationsRequest
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorDelegationsResponse
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorUnbondingDelegationsRequest
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorUnbondingDelegationsResponse
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest
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
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_VESTING_CONTRACT_ADDRESS
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
import wannabit.io.cosmostaion.data.model.res.Price
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

    override suspend fun token(chain: BaseChain): NetworkResult<MutableList<Token>> {
        return safeApiCall(Dispatchers.IO) {
            if (chain.supportCw20) {
                mintscanApi.cw20token(chain.apiName)
            } else if (chain.supportEvm) {
                mintscanApi.erc20token(chain.apiName)
            } else {
                mutableListOf()
            }
        }
    }

    override suspend fun auth(
        managedChannel: ManagedChannel, chain: BaseChain
    ): NetworkResult<QueryProto.QueryAccountResponse?> {
        val stub = com.cosmos.auth.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = QueryProto.QueryAccountRequest.newBuilder().setAddress(chain.address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.account(request)
        }
    }

    override suspend fun balance(
        channel: ManagedChannel, chain: BaseChain
    ): NetworkResult<QueryAllBalancesResponse?> {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(2000).build()
        val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = QueryAllBalancesRequest.newBuilder().setPagination(pageRequest)
            .setAddress(chain.address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.allBalances(request)
        }
    }

    override suspend fun delegation(
        channel: ManagedChannel, chain: BaseChain
    ): NetworkResult<QueryDelegatorDelegationsResponse> {
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryDelegatorDelegationsRequest.newBuilder().setDelegatorAddr(chain.address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.delegatorDelegations(request)
        }
    }

    override suspend fun unBonding(
        channel: ManagedChannel, chain: BaseChain
    ): NetworkResult<QueryDelegatorUnbondingDelegationsResponse> {
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryDelegatorUnbondingDelegationsRequest.newBuilder().setDelegatorAddr(chain.address)
                .build()
        return safeApiCall(Dispatchers.IO) {
            stub.delegatorUnbondingDelegations(request)
        }
    }

    override suspend fun reward(
        channel: ManagedChannel, chain: BaseChain
    ): NetworkResult<QueryDelegationTotalRewardsResponse> {
        val stub = com.cosmos.distribution.v1beta1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryDelegationTotalRewardsRequest.newBuilder().setDelegatorAddress(chain.address)
                .build()
        return safeApiCall(Dispatchers.IO) {
            stub.delegationTotalRewards(request)
        }
    }

    override suspend fun rewardAddress(
        channel: ManagedChannel, chain: BaseChain
    ): NetworkResult<String> {
        val stub = com.cosmos.distribution.v1beta1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            com.cosmos.distribution.v1beta1.QueryProto.QueryDelegatorWithdrawAddressRequest.newBuilder()
                .setDelegatorAddress(chain.address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.delegatorWithdrawAddress(request).withdrawAddress
        }
    }

    override suspend fun baseFee(
        channel: ManagedChannel, chain: BaseChain
    ): NetworkResult<MutableList<CoinProto.DecCoin>>? {
        return if (chain.supportFeeMarket() == true) {
            val stub = com.feemarket.feemarket.v1.QueryGrpc.newBlockingStub(channel)
                .withDeadlineAfter(duration, TimeUnit.SECONDS)
            val request =
                com.feemarket.feemarket.v1.QueryProto.GasPricesRequest.newBuilder().build()
            safeApiCall(Dispatchers.IO) {
                stub.gasPrices(request).pricesList
            }
        } else {
            null
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
        channel: ManagedChannel, chain: BaseChain, token: Token
    ) {
        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val req = Cw20Balance(chain.address)
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val request = QuerySmartContractStateRequest.newBuilder().setAddress(token.address)
            .setQueryData(queryData).build()

        try {
            stub.smartContractState(request)?.let { response ->
                val json = JSONObject(response.data.toStringUtf8())
                token.amount = json.get("balance").toString()
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun erc20Balance(chain: BaseChain, token: Token) {
        val params: MutableList<Type<*>> = ArrayList()
        params.add(Address(chain.evmAddress))

        try {
            val returnTypes = listOf<TypeReference<*>>(object : TypeReference<Uint256?>() {})
            val function = Function("balanceOf", params, returnTypes)

            val txData = FunctionEncoder.encode(function)
            val response = chain.web3j?.ethCall(
                Transaction.createEthCallTransaction(chain.evmAddress, token.address, txData),
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
        channel: ManagedChannel, chain: BaseChain
    ): NetworkResult<QuerySmartContractStateResponse> {
        val req = AllocationReq(Allocation(chain.address))
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QuerySmartContractStateRequest.newBuilder().setAddress(NEUTRON_VESTING_CONTRACT_ADDRESS)
                .setQueryData(queryData).build()

        return safeApiCall(Dispatchers.IO) {
            stub.smartContractState(request)
        }
    }

    override suspend fun vaultDeposit(
        channel: ManagedChannel,
        chain: BaseChain,
    ): NetworkResult<String?> {
        val req = VotingPowerReq(VotingPower(chain.address))
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = QuerySmartContractStateRequest.newBuilder().setAddress(
            chain.getChainListParam()
                ?.get("vaults")?.asJsonArray?.get(0)?.asJsonObject?.get("address")?.asString
        ).setQueryData(queryData).build()

        return safeApiCall(Dispatchers.IO) {
            stub.smartContractState(request)?.let { response ->
                val json = JSONObject(response.data.toStringUtf8())
                json.getString("power")
            }
        }
    }

    override suspend fun oktAccountInfo(chain: BaseChain): NetworkResult<JsonObject?> {
        return safeApiCall(Dispatchers.IO) {
            oktApi.oktAccountInfo(chain.address)
        }
    }

    override suspend fun oktDeposit(chain: BaseChain): NetworkResult<JsonObject?> {
        return safeApiCall(Dispatchers.IO) {
            oktApi.oktDepositInfo(chain.address)
        }
    }

    override suspend fun oktWithdraw(chain: BaseChain): NetworkResult<JsonObject?> {
        return safeApiCall(Dispatchers.IO) {
            oktApi.oktWithdrawInfo(chain.address)
        }
    }

    override suspend fun oktToken(chain: BaseChain): NetworkResult<JsonObject?> {
        return safeApiCall(Dispatchers.IO) {
            oktApi.oktTokens()
        }
    }

    override suspend fun evmToken(chain: BaseChain): NetworkResult<MutableList<Token>> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.erc20token(chain.apiName)
        }
    }

    override suspend fun evmBalance(chain: BaseChain): NetworkResult<String> {
        return safeApiCall(Dispatchers.IO) {
            val web3j = Web3j.build(HttpService(chain.evmRpcFetcher?.getEvmRpc()))
            val balance =
                web3j.ethGetBalance(chain.evmAddress, DefaultBlockParameterName.LATEST).send()
            balance.balance.toString()
        }
    }

    override suspend fun cw721Info(chain: String): NetworkResult<MutableList<JsonObject>> {
        return safeApiCall(Dispatchers.IO) {
            baseApi.cw721Info(chain)
        }
    }

    override suspend fun cw721TokenIds(
        channel: ManagedChannel, chain: BaseChain, list: JsonObject
    ): NetworkResult<JsonObject?> {
        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val req = StarCw721TokenIdReq(wannabit.io.cosmostaion.data.model.req.Token(chain.address))
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val request = QuerySmartContractStateRequest.newBuilder()
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
        channel: ManagedChannel, chain: BaseChain, list: JsonObject, tokenId: String
    ): NetworkResult<JsonObject?> {
        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val req = StarCw721TokenInfoReq(NftInfo(tokenId))
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val request = QuerySmartContractStateRequest.newBuilder()
            .setAddress(list.asJsonObject["contractAddress"].asString).setQueryData(queryData)
            .build()
        return safeApiCall(Dispatchers.IO) {
            stub.smartContractState(request)?.let { response ->
                Gson().fromJson(response.data.toStringUtf8(), JsonObject::class.java)
            }
        }
    }

    override suspend fun cw721TokenDetail(
        chain: BaseChain, contractAddress: String, tokenId: String
    ): NetworkResult<JsonObject> {
        return safeApiCall(Dispatchers.IO) {
            mintscanJsonApi.cw721Detail(chain.apiName, contractAddress, tokenId)
        }
    }

    override suspend fun ecoSystem(chain: String): NetworkResult<MutableList<JsonObject>> {
        return safeApiCall(Dispatchers.IO) {
            ecoApi.ecoSystemInfo(chain)
        }
    }
}