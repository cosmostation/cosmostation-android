package wannabit.io.cosmostaion.data.viewmodel.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.data.repository.address.AddressRepository
import wannabit.io.cosmostaion.database.model.AddressBook

class AddressBookViewModel(private val addressRepository: AddressRepository) : ViewModel() {

    private var _addressBookAllResult = MutableLiveData<MutableList<AddressBook>>()
    val addressBookAllResult: LiveData<MutableList<AddressBook>> get() = _addressBookAllResult
    fun selectAllAddressBook() = viewModelScope.launch(Dispatchers.IO) {
        val addressBook = addressRepository.selectAllAddressBook()
        _addressBookAllResult.postValue(addressBook)
    }

    private var _addressBookUpdateResult = MutableLiveData<Int>()
    val addressBookUpdateResult: LiveData<Int> get() = _addressBookUpdateResult

    fun updateAddressBook(addressBook: AddressBook) = viewModelScope.launch(Dispatchers.IO) {
        if (addressRepository.selectAddressBook(addressBook.id) != null) {
            addressRepository.updateAddressBook(addressBook)
            val updateIndex = addressRepository.selectAllAddressBook().indexOf(addressBook)
            _addressBookUpdateResult.postValue(updateIndex)

        } else {
            addressRepository.insertAddressBook(addressBook)
            _addressBookAllResult.postValue(addressRepository.selectAllAddressBook())
        }
    }

    fun deleteAddressBook(addressBook: AddressBook) = viewModelScope.launch(Dispatchers.IO) {
        addressRepository.deleteAddressBook(addressBook)
        _addressBookAllResult.postValue(addressRepository.selectAllAddressBook())
    }

    fun updateAddressBookWithChainTag() = viewModelScope.launch(Dispatchers.IO) {
        if (addressRepository.selectAllAddressBook().isNotEmpty()) {
            addressRepository.selectAllAddressBook().forEach { addressBook ->
                if (addressBook.chainName.isNotEmpty()) {
                    if (addressBook.address.startsWith("0x")) {
                        if (BaseKey.isValidEthAddress(addressBook.address)) {
                            addressBook.chainName = "EVM-universal"
                        } else {
                            addressBook.chainName = ChainSui().tag
                        }

                    } else if (addressBook.chainName.contains("Bitcoin")) {
                        val chain =
                            allChains().firstOrNull { it.isDefault && it.name == addressBook.chainName }
                        addressBook.chainName = chain?.tag ?: ""

                    } else {
                        val prefix = addressBook.address.substringBefore('1')
                        val chainTag =
                            allChains().firstOrNull { it.isDefault && it.accountPrefix() == prefix }?.tag
                                ?: ""
                        addressBook.chainName = chainTag
                    }

                } else {
                    addressBook.chainName = "EVM-universal"
                }
                addressRepository.updateAddressBookWithChainTag(addressBook)
            }
        }
    }
}