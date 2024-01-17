package wannabit.io.cosmostaion.ui.viewmodel.intro

import SingleLiveEvent
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
import wannabit.io.cosmostaion.data.model.res.AccountResponse
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.BnbToken
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.OktAccountResponse
import wannabit.io.cosmostaion.data.model.res.OktDepositedResponse
import wannabit.io.cosmostaion.data.model.res.OktTokenResponse
import wannabit.io.cosmostaion.data.model.res.OktWithdrawResponse
import wannabit.io.cosmostaion.data.model.res.Param
import wannabit.io.cosmostaion.data.model.res.TokenResponse
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

    fun supportConfig() = viewModelScope.launch(Dispatchers.IO) {
        when (val response = walletRepository.supportConfig()) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        BaseData.supportConfig = data.body()
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

    private val _chainDataErrorMessage = MutableLiveData<String>()
    val chainDataErrorMessage: LiveData<String> get() = _chainDataErrorMessage

    fun loadChainData(
        line: CosmosLine, baseAccountId: Long, isEdit: Boolean
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
                            _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                        }
                    }
                }

            } else {
                when (val paramResponse = loadParamDeferred.await()) {
                    is NetworkResult.Success -> {
                        param = paramResponse.data
                    }

                    is NetworkResult.Error -> {
                        _chainDataErrorMessage.postValue("error type : ${paramResponse.errorType}  error message : ${paramResponse.errorMessage}")
                    }
                }
            }

            if (this is ChainBinanceBeacon || this is ChainOkt60) {
                loadLcdData(this, baseAccountId, isEdit)
            } else {
                loadGrpcAuthData(this, baseAccountId, isEdit)
            }
        }
    }

    private val _fetchedResult = MutableLiveData<String>()
    val fetchedResult: LiveData<String> get() = _fetchedResult

    var editFetchedResult = SingleLiveEvent<String>()

    private fun loadGrpcAuthData(
        line: CosmosLine, baseAccountId: Long, isEdit: Boolean
    ) = viewModelScope.launch(Dispatchers.IO) {
        line.apply {
            val channel = getChannel(line)
            when (val response = walletRepository.auth(channel, this)) {
                is NetworkResult.Success -> {
                    cosmosAuth = response.data?.account
                    loadGrpcMoreData(channel, baseAccountId, line, isEdit)
                }

                is NetworkResult.Error -> {
                    fetched = true
                    if (fetched) {
                        val refAddress = RefAddress(baseAccountId, tag, address, "0", "0", "0", 0)
                        BaseData.updateRefAddressesMain(refAddress)
                        if (isEdit) {
                            editFetchedResult.postValue(tag)
                        } else {
                            _fetchedResult.postValue(tag)
                        }
                    }
                }
            }
        }
    }

    private fun loadLcdData(
        line: CosmosLine, baseAccountId: Long, isEdit: Boolean
    ) = viewModelScope.launch(Dispatchers.IO) {
        line.apply {
            if (this is ChainBinanceBeacon) {
                val loadAccountInfoDeferred = async { walletRepository.binanceAccountInfo(line) }
                val loadBeaconTokenInfoDeferred = async { walletRepository.beaconTokenInfo() }

                val responses = awaitAll(
                    loadAccountInfoDeferred, loadBeaconTokenInfoDeferred
                )

                responses.forEach { response ->
                    when (response) {
                        is NetworkResult.Success -> {
                            when (response.data) {
                                is AccountResponse -> {
                                    lcdAccountInfo = response.data
                                }

                                is MutableList<*> -> {
                                    if (response.data.all { it is BnbToken }) {
                                        lcdBeaconTokens = response.data as MutableList<BnbToken>
                                    }
                                }
                            }

                            fetched = true
                            if (fetched) {
                                val refAddress = RefAddress(
                                    baseAccountId,
                                    tag,
                                    address,
                                    allAssetValue().toString(),
                                    lcdBalanceAmount(stakeDenom).toString(),
                                    "0",
                                    lcdAccountInfo?.balances?.size?.toLong() ?: 0
                                )
                                BaseData.updateRefAddressesMain(refAddress)
                                if (isEdit) {
                                    editFetchedResult.postValue(tag)
                                } else {
                                    _fetchedResult.postValue(tag)
                                }
                            }
                        }

                        is NetworkResult.Error -> {
                            fetched = true
                            if (fetched) {
                                val refAddress =
                                    RefAddress(baseAccountId, tag, address, "0", "0", "0", 0)
                                BaseData.updateRefAddressesMain(refAddress)
                                if (isEdit) {
                                    editFetchedResult.postValue(tag)
                                } else {
                                    _fetchedResult.postValue(tag)
                                }
                            }
                        }
                    }
                }

            } else if (this is ChainOkt60) {
                val loadAccountInfoDeferred = async { walletRepository.oktAccountInfo(line) }
                val loadDepositDeferred = async { walletRepository.oktDeposit(line) }
                val loadWithdrawDeferred = async { walletRepository.oktWithdraw(line) }
                val loadTokenDeferred = async { walletRepository.oktToken(line) }

                val responses = awaitAll(
                    loadAccountInfoDeferred,
                    loadDepositDeferred,
                    loadWithdrawDeferred,
                    loadTokenDeferred
                )

                responses.forEach { response ->
                    when (response) {
                        is NetworkResult.Success -> {
                            when (response.data) {
                                is OktAccountResponse -> {
                                    oktLcdAccountInfo = response.data
                                }

                                is OktDepositedResponse -> {
                                    oktDepositedInfo = response.data
                                }

                                is OktWithdrawResponse -> {
                                    oktWithdrawInfo = response.data
                                }

                                is OktTokenResponse -> {
                                    oktTokenInfo = response.data
                                }
                            }

                            fetched = true
                            if (fetched) {
                                val refAddress = RefAddress(
                                    baseAccountId,
                                    tag,
                                    address,
                                    allAssetValue().toString(),
                                    lcdBalanceAmount(stakeDenom).toString(),
                                    "0",
                                    oktLcdAccountInfo?.value?.coins?.size?.toLong() ?: 0
                                )
                                BaseData.updateRefAddressesMain(refAddress)
                                if (isEdit) {
                                    editFetchedResult.postValue(tag)
                                } else {
                                    _fetchedResult.postValue(tag)
                                }
                            }
                        }

                        is NetworkResult.Error -> {
                            fetched = true
                            if (fetched) {
                                val refAddress =
                                    RefAddress(baseAccountId, tag, address, "0", "0", "0", 0)
                                BaseData.updateRefAddressesMain(refAddress)
                                if (isEdit) {
                                    editFetchedResult.postValue(tag)
                                } else {
                                    _fetchedResult.postValue(tag)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadGrpcMoreData(
        channel: ManagedChannel, id: Long, line: CosmosLine, isEdit: Boolean
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
                                _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                            }
                        }
                    }

                } else {
                    if (line is ChainNeutron) {
                        val loadVaultDepositDeferred =
                            async { walletRepository.vaultDeposit(channel, line) }
                        val loadVestingDeferred =
                            async { walletRepository.vestingData(channel, line) }

                        val responses = awaitAll(
                            loadBalanceDeferred, loadVestingDeferred, loadVaultDepositDeferred
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

                                        else -> {
                                            line.neutronDeposited =
                                                response.data.toString().toBigDecimal()
                                        }
                                    }
                                }

                                is NetworkResult.Error -> {
                                    _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                                }
                            }
                        }

                    } else {
                        when (val balanceResponse = loadBalanceDeferred.await()) {
                            is NetworkResult.Success -> {
                                balanceResponse.data?.balancesList?.let { cosmosBalances = it }
                            }

                            is NetworkResult.Error -> {
                                _chainDataErrorMessage.postValue("error type : ${balanceResponse.errorType}  error message : ${balanceResponse.errorMessage}")
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
                        cosmosBalances?.size?.toLong() ?: 0
                    )
                    BaseData.updateRefAddressesMain(refAddress)
                    if (isEdit) {
                        editFetchedResult.postValue(tag)
                    } else {
                        _fetchedResult.postValue(tag)
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
    }

    private val _fetchedTokenResult = MutableLiveData<Unit>()
    val fetchedTokenResult: LiveData<Unit> get() = _fetchedTokenResult
    fun loadAllTokenBalance(
        line: CosmosLine, baseAccountId: Long
    ) = CoroutineScope(Dispatchers.Default).launch {
        line.apply {
            val deferredList = mutableListOf<Deferred<Unit>>()
            tokens.forEach { token ->
                if (supportCw20) {
                    val channel = getChannel(line)
                    val deferred = async { walletRepository.cw20Balance(channel, line, token) }
                    deferredList.add(deferred)
                } else {
                    val deferred = async { walletRepository.erc20Balance(line, token) }
                    deferredList.add(deferred)
                }
            }

            runBlocking {
                deferredList.awaitAll()

                val refAddress = RefAddress(
                    baseAccountId, tag, address, "0", "0", allTokenValue().toPlainString(), 0
                )
                val updatedResult = BaseData.updateRefAddressesToken(refAddress)
                _fetchedTokenResult.postValue(updatedResult)
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
            val loadUnBondingDeferred = async { walletRepository.unBondingValidator(channel) }

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
                        _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
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

    private var _balanceResult = MutableLiveData<String>()
    val balanceResult: LiveData<String> get() = _balanceResult
    fun balance(line: CosmosLine) = viewModelScope.launch(Dispatchers.IO) {
        when (line) {
            is ChainBinanceBeacon -> {
                when (val response = walletRepository.binanceAccountInfo(line)) {
                    is NetworkResult.Success -> {
                        line.lcdAccountInfo = response.data
                        line.fetched = true
                        _balanceResult.postValue(line.tag)
                    }

                    is NetworkResult.Error -> {
                        line.lcdAccountInfo = null
                        line.fetched = true
                        _balanceResult.postValue("null")
                    }
                }

            }

            is ChainOkt60 -> {
                when (val response = walletRepository.oktAccountInfo(line)) {
                    is NetworkResult.Success -> {
                        line.oktLcdAccountInfo = response.data
                        line.fetched = true
                        _balanceResult.postValue(line.tag)
                    }

                    is NetworkResult.Error -> {
                        line.oktLcdAccountInfo = null
                        line.fetched = true
                        _balanceResult.postValue("null")
                    }
                }

            }

            else -> {
                val channel = getChannel(line)
                when (val response = walletRepository.balance(channel, line)) {
                    is NetworkResult.Success -> {
                        response.data?.balancesList?.let {
                            line.cosmosBalances = it
                            line.fetched = true
                            _balanceResult.postValue(line.tag)
                        }
                    }

                    is NetworkResult.Error -> {
                        line.cosmosBalances = null
                        line.fetched = true
                        _balanceResult.postValue("null")
                    }
                }
            }
        }
    }

    private var _pwCheckResult = MutableLiveData<String>()
    val pwCheckResult: LiveData<String> get() = _pwCheckResult
    fun checkPassword(input: String) = viewModelScope.launch(Dispatchers.IO) {
        val checkPw = AppDatabase.getInstance().passwordDao().selectAll()
        checkPw.forEach { password ->
            if (!CryptoHelper.verifyData(
                    input, password.resource, CosmostationConstants.ENCRYPT_PASSWORD_KEY
                )
            ) {
                _pwCheckResult.postValue(BaseConstant.FAIL)
            } else {
                _pwCheckResult.postValue(BaseConstant.SUCCESS)
            }
        }
    }

    private val _evmTxHashErrorMessage = MutableLiveData<String>()
    val evmTxHashErrorMessage: LiveData<String> get() = _evmTxHashErrorMessage

    private var _evmTxHashResult = MutableLiveData<String>()
    val evmTxHashResult: LiveData<String> get() = _evmTxHashResult
    fun evmTxHash(chain: String?, evmTxHash: String?) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = walletRepository.evmTxHash(chain, evmTxHash)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        _evmTxHashResult.postValue(response.data.body())
                    } else {
                        _evmTxHashResult.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _evmTxHashResult.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private val _moonPayDataErrorMessage = MutableLiveData<String>()
    val moonPayDataErrorMessage: LiveData<String> get() = _moonPayDataErrorMessage

    private var _moonPayResult = MutableLiveData<String>()
    val moonPayResult: LiveData<String> get() = _moonPayResult
    fun moonPay(data: MoonPayReq) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = walletRepository.moonPay(data)) {
            is NetworkResult.Success -> {
                _moonPayResult.postValue(response.data.body()?.signature)
            }

            is NetworkResult.Error -> {
                _moonPayDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }
}