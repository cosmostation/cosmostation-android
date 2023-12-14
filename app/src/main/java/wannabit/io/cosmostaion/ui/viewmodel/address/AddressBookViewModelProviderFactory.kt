package wannabit.io.cosmostaion.ui.viewmodel.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.data.repository.address.AddressRepository

class AddressBookViewModelProviderFactory(
    private val addressRepository: AddressRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddressBookViewModel::class.java)) {
            return AddressBookViewModel(addressRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}