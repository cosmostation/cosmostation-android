package wannabit.io.cosmostaion.ui.viewmodel.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.chain.CosmosLine

class DashboardViewModel() : ViewModel() {
    private var _chainList = MutableLiveData<List<String>>()
    val chainList: LiveData<List<String>> get() = _chainList

    var isInit: Boolean = true

    fun loadChainList() = viewModelScope.launch(Dispatchers.IO) {
        val supportList: MutableList<String> = mutableListOf()
        val cosmosList: MutableList<String> = mutableListOf("Cosmos", "Osmosis", "Neutron", "Akash")
        val ethList: MutableList<String> = mutableListOf("ETH", "Sui", "Aptos", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")

        isInit = false

        supportList.addAll(cosmosList)
        supportList.addAll(ethList)
        _chainList.postValue(supportList)
    }
}