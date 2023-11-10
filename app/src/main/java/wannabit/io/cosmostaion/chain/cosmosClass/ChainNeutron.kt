package wannabit.io.cosmostaion.chain.cosmosClass

import com.cosmwasm.wasm.v1.QueryGrpc
import com.cosmwasm.wasm.v1.QueryProto
import com.google.common.collect.ImmutableList
import com.google.gson.Gson
import com.google.protobuf.ByteString
import io.grpc.ManagedChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.bitcoinj.crypto.ChildNumber
import org.json.JSONObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.Allocation
import wannabit.io.cosmostaion.data.model.req.AllocationReq
import wannabit.io.cosmostaion.data.model.req.VotingPower
import wannabit.io.cosmostaion.data.model.req.VotingPowerReq
import wannabit.io.cosmostaion.data.model.res.Dao
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Vault
import wannabit.io.cosmostaion.data.model.res.VestingData
import wannabit.io.cosmostaion.database.model.RefAddress
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.TimeUnit

class ChainNeutron : CosmosLine() {

    var vaultList: MutableList<Vault>? = mutableListOf()
    var daoList: MutableList<Dao>? = mutableListOf()

    var neutronDeposited: BigDecimal = BigDecimal.ZERO
    private var neutronVesting: VestingData? = null

    override var chainType: ChainType? = ChainType.COSMOS_TYPE
    override var name: String = "Neutron"
    override var tag: String = "neutron118"
    override var logo: Int = R.drawable.chain_neutron
    override var swipeLogo: Int = R.drawable.chain_swipe_neutron
    override var apiName: String = "neutron"
    override var stakeDenom: String? = "untrn"
    override var supportStaking = false

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true),
        ChildNumber(118, true),
        ChildNumber.ZERO_HARDENED,
        ChildNumber.ZERO
    )
    override var accountPrefix: String? = "neutron"

    override var grpcHost: String = "grpc-neutron.cosmostation.io"

    override fun loadGrpcMoreData(channel: ManagedChannel, id: Long) = runBlocking {
        CoroutineScope(Dispatchers.Default).let {
            loadVaults()
            loadDaos()

            loadBalance(channel)
            loadNeutronVesting(channel)
            if (vaultList?.isNotEmpty() == true) {
                loadNeutronVaultDeposit(channel)
            }

            BaseUtils.onParseVestingAccount(this@ChainNeutron)
            loadDataCallback?.onDataLoaded(true)
            fetched = true

            if (fetched) {
                val refAddress = RefAddress(id, tag, address, allStakingDenomAmount().toString(), allAssetValue().toPlainString(), "0", cosmosBalances.size.toLong())
                BaseData.updateRefAddressesMain(id, tag, address, refAddress)
            }
            it.cancel()
        }
    }

    override fun denomValue(denom: String): BigDecimal {
        return if (denom == stakeDenom) {
            balanceValue(denom).add(neutronVestingValue()).add(neutronDepositedValue())
        } else {
            balanceValue(denom)
        }
    }

    override fun allStakingDenomAmount(): BigDecimal {
        stakeDenom?.let { denom ->
            return balanceAmount(denom).add(neutronVestingAmount()).add(neutronDeposited)
        }
        return BigDecimal.ZERO
    }

    override fun allAssetValue(): BigDecimal {
        return balanceValueSum().add(neutronVestingValue()).add(neutronDepositedValue())
    }

    private suspend fun loadVaults() {
        when (val response = safeApiCall { RetrofitInstance.chainListApi.vaultData(apiName).execute() }) {
            is NetworkResult.Success -> {
                vaultList = response.data.body()
            }

            is NetworkResult.Error -> {
                return
            }
        }
    }

    private suspend fun loadDaos() {
        when (val response = safeApiCall { RetrofitInstance.chainListApi.daoData(apiName).execute() }) {
            is NetworkResult.Success -> {
                daoList = response.data.body()
            }

            is NetworkResult.Error -> {
                return
            }
        }
    }

    private fun loadNeutronVesting(channel: ManagedChannel) {
        val req = AllocationReq(Allocation(address))
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = QueryProto.QuerySmartContractStateRequest.newBuilder()
            .setAddress(NEUTRON_VESTING_CONTRACT_ADDRESS)
            .setQueryData(queryData).build()

        stub.smartContractState(request)?.let { response ->
            neutronVesting = Gson().fromJson(response.data.toStringUtf8(), VestingData::class.java)
        }
    }

    private fun loadNeutronVaultDeposit(channel: ManagedChannel) {
        val req = VotingPowerReq(VotingPower(address))
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = QueryProto.QuerySmartContractStateRequest.newBuilder().setAddress(vaultList?.get(0)?.address).setQueryData(queryData).build()

        stub.smartContractState(request)?.let { response ->
            val json = JSONObject(response.data.toStringUtf8())
            neutronDeposited = json.getString("power").toBigDecimal()
        }
    }

    fun neutronVestingAmount(): BigDecimal? {
        neutronVesting?.let {
            val allocatedAmount = it.allocated_amount?.toBigDecimal()
            val withdrawnAmount = it.withdrawn_amount?.toBigDecimal()
            return allocatedAmount?.subtract(withdrawnAmount)
        }
        return BigDecimal.ZERO
    }

    private fun neutronVestingValue(): BigDecimal {
        stakeDenom?.let { denom ->
            BaseData.getAsset(apiName, denom)?.let { asset ->
                asset.decimals?.let { decimal ->
                    val price = BaseData.getPrice(asset.coinGeckoId)
                    val amount = neutronVestingAmount()
                    return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
                }
            }
        }
        return BigDecimal.ZERO
    }

    private fun neutronDepositedValue(): BigDecimal {
        stakeDenom?.let { denom ->
            BaseData.getAsset(apiName, denom)?.let { asset ->
                asset.decimals?.let { decimal ->
                    val price = BaseData.getPrice(asset.coinGeckoId)
                    val amount = neutronDeposited
                    return price.multiply(amount).movePointLeft(decimal)
                }
            }
        }
        return BigDecimal.ZERO
    }
}

private val NEUTRON_VESTING_CONTRACT_ADDRESS = "neutron1h6828as2z5av0xqtlh4w9m75wxewapk8z9l2flvzc29zeyzhx6fqgp648z"