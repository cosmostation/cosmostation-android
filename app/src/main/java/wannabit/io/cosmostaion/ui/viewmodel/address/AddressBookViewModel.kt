package wannabit.io.cosmostaion.ui.viewmodel.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.data.repository.address.AddressRepository
import wannabit.io.cosmostaion.database.model.AddressBook

class AddressBookViewModel(private val addressRepository: AddressRepository): ViewModel() {

    private var _addressBookAllResult = MutableLiveData<MutableList<AddressBook>>()
    val addressBookAllResult: LiveData<MutableList<AddressBook>> get() = _addressBookAllResult
    fun selectAllAddressBook() = viewModelScope.launch(Dispatchers.IO) {
        val addressBook = addressRepository.selectAllAddressBook()
        _addressBookAllResult.postValue(addressBook)
    }

    fun updateAddressBook(addressBook: AddressBook) = viewModelScope.launch(Dispatchers.IO) {
        if (addressRepository.selectAddressBook(addressBook.id) != null) {
            addressRepository.updateAddressBook(addressBook)
        } else {
            addressRepository.insertAddressBook(addressBook)
        }
        _addressBookAllResult.postValue(addressRepository.selectAllAddressBook())
    }
}