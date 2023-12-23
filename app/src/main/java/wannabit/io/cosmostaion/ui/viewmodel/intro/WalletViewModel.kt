package wannabit.io.cosmostaion.ui.viewmodel.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse
import com.cosmos.distribution.v1beta1.QueryProto.QueryDelegationTotalRewardsResponse
import com.cosmos.staking.v1beta1.QueryProto
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse
import com.google.gson.Gson
import io.grpc.ManagedChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.Dao
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Param
import wannabit.io.cosmostaion.data.model.res.TokenResponse
import wannabit.io.cosmostaion.data.model.res.Vault
import wannabit.io.cosmostaion.data.model.res.VestingData
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepository
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.CryptoHelper
import wannabit.io.cosmostaion.database.model.RefAddress
import java.util.concurrent.TimeUnit

class WalletViewModel(private val walletRepository: WalletRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage


    private var _walletAppVersionResult = MutableLiveData<AppVersion>()
    val walletAppVersionResult: LiveData<AppVersion> get() = _walletAppVersionResult

    fun walletAppVersion() = viewModelScope.launch(Dispatchers.IO) {
        when (val response = walletRepository.version()) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        _walletAppVersionResult.postValue(data.body())
                    } else {
                        _errorMessage.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    fun chain() = viewModelScope.launch(Dispatchers.IO) {
        when (val response = walletRepository.chain()) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        BaseData.chains = data.body()?.chains
                    } else {
                        _errorMessage.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    fun price(currency: String, force: Boolean? = false) = viewModelScope.launch(Dispatchers.IO) {
        if (!BaseData.priceUpdateIfNeed() && force == false) {
            return@launch
        }
        when (val response = walletRepository.price(currency)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        BaseData.prices = data.body()
                        BaseData.setLastPriceTime()
                        BaseData.baseAccount?.updateAllValue()

                    } else {
                        _errorMessage.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    fun asset() = viewModelScope.launch(Dispatchers.IO) {
        when (val response = walletRepository.asset()) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        BaseData.assets = data.body()?.assets
                    } else {
                        _errorMessage.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    fun loadChainData(
        line: CosmosLine, baseAccountId: Long
    ) = viewModelScope.launch(Dispatchers.IO) {
        line.apply {
            val loadParamDeferred = async { walletRepository.param(this@apply) }
            val tokenDeferred = async { walletRepository.token(this@apply) }

            if (supportCw20 || supportErc20) {
                val responses = awaitAll(loadParamDeferred, tokenDeferred)
                responses.forEach { response ->
                    when (response) {
                        is NetworkResult.Success -> {
                            when (response.data) {
                                is Param -> {
                                    line.param = response.data
                                }

                                is TokenResponse -> {
                                    line.tokens = response.data.assets
                                }
                            }
                        }

                        is NetworkResult.Error -> {
                            _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                        }
                    }
                }

            } else {
                when (val paramResponse = loadParamDeferred.await()) {
                    is NetworkResult.Success -> {
                        param = paramResponse.data
                    }

                    is NetworkResult.Error -> {
                        _errorMessage.postValue("error type : ${paramResponse.errorType}  error message : ${paramResponse.errorMessage}")
                    }
                }
            }
        }

        if (line is ChainBinanceBeacon || line is ChainOkt60) {

        } else {
            loadGrpcAuthData(line, baseAccountId)
        }
    }

    private val _fetchedResult = MutableLiveData<String>()
    val fetchedResult: LiveData<String> get() = _fetchedResult
    private fun loadGrpcAuthData(
        line: CosmosLine, baseAccountId: Long
    ) = viewModelScope.launch(Dispatchers.IO) {
        line.apply {
            val channel = getChannel(line)
            when (val response = walletRepository.auth(channel, this)) {
                is NetworkResult.Success -> {
                    cosmosAuth = response.data?.account
                    loadGrpcMoreData(channel, baseAccountId, line)
                }

                is NetworkResult.Error -> {
                    fetched = true
                    if (fetched) {
                        val refAddress =
                            RefAddress(baseAccountId, tag, address, "0", "0", "0", 0)
                        BaseData.updateRefAddressesMain(refAddress)
                        _fetchedResult.postValue(tag)
                    }
                }
            }
        }
    }

    private fun loadGrpcMoreData(
        channel: ManagedChannel, id: Long, line: CosmosLine
    ) = viewModelScope.launch(Dispatchers.IO) {
        line.apply {
            try {
                val loadBalanceDeferred = async { walletRepository.balance(channel, line) }
                if (line.supportStaking) {
                    val loadDelegationDeferred =
                        async { walletRepository.delegation(channel, line) }
                    val loadUnBondingDeferred = async { walletRepository.unBonding(channel, line) }
                    val loadRewardDeferred = async { walletRepository.reward(channel, line) }

                    val responses = awaitAll(
                        loadBalanceDeferred,
                        loadDelegationDeferred,
                        loadUnBondingDeferred,
                        loadRewardDeferred
                    )

                    responses.forEach { response ->
                        when (response) {
                            is NetworkResult.Success -> {
                                when (response.data) {
                                    is QueryAllBalancesResponse -> {
                                        response.data.balancesList?.let { cosmosBalances = it }
                                    }

                                    is QueryProto.QueryDelegatorDelegationsResponse -> {
                                        cosmosDelegations.clear()
                                        response.data.delegationResponsesList.forEach { delegation ->
                                            if (delegation.balance.amount != "0") {
                                                cosmosDelegations.add(delegation)
                                            }
                                        }
                                    }

                                    is QueryProto.QueryDelegatorUnbondingDelegationsResponse -> {
                                        response.data.unbondingResponsesList?.let {
                                            cosmosUnbondings = it
                                        }
                                    }

                                    is QueryDelegationTotalRewardsResponse -> {
                                        response.data.rewardsList?.let { cosmosRewards = it }
                                    }
                                }
                            }

                            is NetworkResult.Error -> {
                                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                            }
                        }
                    }

                } else {
                    if (line is ChainNeutron) {
                        val loadDaoDataDeferred = async { walletRepository.daoData(line) }
                        val loadVaultDataDeferred = async { walletRepository.vaultData(line) }

                        val loadVestingDeferred =
                            async { walletRepository.vestingData(channel, line) }

                        val responses = awaitAll(
                            loadBalanceDeferred,
                            loadVestingDeferred,
                            loadDaoDataDeferred,
                            loadVaultDataDeferred
                        )

                        responses.forEach { response ->
                            when (response) {
                                is NetworkResult.Success -> {
                                    when (response.data) {
                                        is QueryAllBalancesResponse -> {
                                            response.data.balancesList?.let { cosmosBalances = it }
                                        }

                                        is QuerySmartContractStateResponse -> {
                                            line.neutronVesting = Gson().fromJson(
                                                response.data.data.toStringUtf8(),
                                                VestingData::class.java
                                            )
                                        }

                                        is MutableList<*> -> {
                                            if (response.data.all { it is Dao }) {
                                                line.daoList = response.data as? MutableList<Dao>
                                            } else {
                                                line.vaultList =
                                                    response.data as? MutableList<Vault>
                                                if (line.vaultList?.isNotEmpty() == true) {
                                                    walletRepository.vaultDeposit(
                                                        channel,
                                                        line,
                                                        line.vaultList
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }

                                is NetworkResult.Error -> {
                                    _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                                }
                            }
                        }

                    } else {
                        when (val balanceResponse = loadBalanceDeferred.await()) {
                            is NetworkResult.Success -> {
                                balanceResponse.data?.balancesList?.let { cosmosBalances = it }
                            }

                            is NetworkResult.Error -> {
                                _errorMessage.postValue("error type : ${balanceResponse.errorType}  error message : ${balanceResponse.errorMessage}")
                            }
                        }
                    }
                }
                BaseUtils.onParseVestingAccount(line)
                fetched = true
                if (fetched) {
                    val refAddress = RefAddress(
                        id,
                        tag,
                        address,
                        allAssetValue().toPlainString(),
                        allStakingDenomAmount().toString(),
                        "0",
                        cosmosBalances.size.toLong()
                    )
                    BaseData.updateRefAddressesMain(refAddress)
                    _fetchedResult.postValue(tag)
                }

            } finally {
                channel.shutdown()
                try {
                    if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                        channel.shutdownNow()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private val _fetchedTokenResult = MutableLiveData<Unit>()
    val fetchedTokenResult: LiveData<Unit> get() = _fetchedTokenResult
    fun loadAllTokenBalance(
        line: CosmosLine,
        baseAccountId: Long
    ) = CoroutineScope(Dispatchers.Default).launch {
        val channel = getChannel(line)
        line.apply {
            try {
                val deferredList = mutableListOf<Deferred<Unit>>()
                tokens.forEach { token ->
                    if (supportCw20) {
                        val deferred = async { walletRepository.cw20Balance(channel, line, token) }
                        deferredList.add(deferred)
                    } else {
                        val deferred = async { walletRepository.erc20Balance(channel, line, token) }
                        deferredList.add(deferred)
                    }
                }

                runBlocking {
                    deferredList.awaitAll()

                    val refAddress = RefAddress(
                        baseAccountId,
                        tag,
                        address,
                        "0",
                        "0",
                        allTokenValue().toPlainString(),
                        0
                    )
                    val updatedResult = BaseData.updateRefAddressesToken(refAddress)
                    _fetchedTokenResult.postValue(updatedResult)
                }

            } finally {
                channel.shutdown()
                try {
                    if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                        channel.shutdownNow()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun loadGrpcStakeData(
        line: CosmosLine
    ) = viewModelScope.launch(Dispatchers.IO) {
        if (line.cosmosValidators.size > 0) {
            return@launch
        }

        val channel = getChannel(line)
        try {
            val loadRewardAddrDeferred = async { walletRepository.rewardAddress(channel, line) }
            val loadBondedDeferred = async { walletRepository.bondedValidator(channel) }
            val loadUnBondedDeferred = async { walletRepository.unBondedValidator(channel) }
            val loadUnBondingDeferred = async { walletRepository.unBondedValidator(channel) }

            val responses = awaitAll(
                loadRewardAddrDeferred,
                loadBondedDeferred,
                loadUnBondedDeferred,
                loadUnBondingDeferred
            )

            responses.forEach { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        when (response.data) {
                            is String -> {
                                line.rewardAddress = response.data
                            }

                            else -> {
                                line.cosmosValidators.addAll(response.data as Collection<StakingProto.Validator>)
                            }
                        }
                        val tempValidators = line.cosmosValidators.toMutableList()
                        tempValidators.sortWith { o1, o2 ->
                            when {
                                o1.description.moniker == "Cosmostation" -> -1
                                o2.description.moniker == "Cosmostation" -> 1
                                o1.jailed && !o2.jailed -> 1
                                !o1.jailed && o2.jailed -> -1
                                o1.tokens.toDouble() > o2.tokens.toDouble() -> -1
                                o1.tokens.toDouble() < o2.tokens.toDouble() -> 1
                                else -> 0
                            }
                        }
                        line.cosmosValidators = tempValidators
                    }

                    is NetworkResult.Error -> {
                        _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                    }
                }
            }

        } finally {
            channel.shutdown()
            try {
                if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                    channel.shutdownNow()
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private var _pwCheckResult = MutableLiveData<String>()
    val pwCheckResult: LiveData<String> get() = _pwCheckResult
    fun checkPassword(input: String) = viewModelScope.launch(Dispatchers.IO) {
        val checkPw = AppDatabase.getInstance().passwordDao().selectAll()
        checkPw.forEach { password ->
            if (!CryptoHelper.verifyData(
                    input,
                    password.resource,
                    CosmostationConstants.ENCRYPT_PASSWORD_KEY
                )
            ) {
                _pwCheckResult.postValue(BaseConstant.FAIL)
            } else {
                _pwCheckResult.postValue(BaseConstant.SUCCESS)
            }
        }
    }

    private var _evmTxHashResult = MutableLiveData<String>()
    val evmTxHashResult: LiveData<String> get() = _evmTxHashResult
    fun evmTxHash(chain: String?, evmTxHash: String?) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = walletRepository.evmTxHash(chain, evmTxHash)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        _evmTxHashResult.postValue(response.data.body())
                    } else {
                        _errorMessage.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private var _moonPayResult = MutableLiveData<String>()
    val moonPayResult: LiveData<String> get() = _moonPayResult
    fun moonPay(data: MoonPayReq) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = walletRepository.moonPay(data)) {
            is NetworkResult.Success -> {
                _moonPayResult.postValue(response.data.body()?.signature)
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }
}