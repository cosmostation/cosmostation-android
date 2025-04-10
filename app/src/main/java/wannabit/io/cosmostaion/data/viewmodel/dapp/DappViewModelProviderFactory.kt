package wannabit.io.cosmostaion.data.viewmodel.dapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DappViewModelProviderFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DappViewModel::class.java)) {
            return DappViewModel() as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}