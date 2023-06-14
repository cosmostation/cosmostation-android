package wannabit.io.cosmostaion.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.database.model.Chain
import wannabit.io.cosmostaion.database.model.ChainConfig
import wannabit.io.cosmostaion.network.MintscanService
import wannabit.io.cosmostaion.network.model.Price

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
        Chain.allChains().forEach {
            when (it.chainConfig) {
                is ChainConfig.Cosmos -> {

                }

                is ChainConfig.Ethereum -> {}
                else -> {}
            }
        }
    }
}