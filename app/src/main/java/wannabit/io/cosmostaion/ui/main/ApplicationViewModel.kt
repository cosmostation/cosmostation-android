package wannabit.io.cosmostaion.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cosmos.bank.v1beta1.QueryGrpc
import com.cosmos.bank.v1beta1.QueryProto
import com.cosmos.base.query.v1beta1.PaginationProto
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.Chain
import wannabit.io.cosmostaion.database.model.ChainConfig
import wannabit.io.cosmostaion.network.MintscanService
import wannabit.io.cosmostaion.network.model.Price
import java.util.concurrent.TimeUnit

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        val shared
            get() = CosmostationApp.instance.applicationViewModel
    }

    var pricesLiveData = MutableLiveData<List<Price>>()

    fun loadPrices() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val result = MintscanService.create().price()
            pricesLiveData.postValue(result)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadBalances() = CoroutineScope(Dispatchers.IO).launch {
        val wallet = AppDatabase.getInstance().walletDao().selectById(Prefs.lastUserId)!!


        Chain.allChains().forEach {
            when (it.chainConfig) {
                is ChainConfig.Cosmos -> {
                    val address = it.chainConfig.getAddress(wallet.seed!!)
                    val channel = ManagedChannelBuilder.forAddress(it.chainConfig.grpcUrl, 443).useTransportSecurity().build()
                    val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(5, TimeUnit.SECONDS)
                    val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(2000).build()
                    val request = QueryProto.QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(address).build()
                    val response = stub.allBalances(request)
                    response.balancesList.forEach {
                        it.denom
                        it.amount
                    }
                }

                is ChainConfig.Ethereum -> {
                    val web3 = Web3j.build(HttpService("https://rpc.flashbots.net"))
                    val address = it.chainConfig.getAddress(wallet.seed!!)
                    val response = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get()
                    response.balance
                }

                else -> {}
            }
        }
    }

}