package wannabit.io.cosmostaion.data.repository.address

import wannabit.io.cosmostaion.database.model.AddressBook

interface AddressRepository {

    suspend fun insertAddressBook(addressBook: AddressBook)

    suspend fun selectAllAddressBook(): MutableList<AddressBook>

    suspend fun selectAddressBook(addressBookId: Long): AddressBook?

    suspend fun deleteAddressBook(addressBook: AddressBook)

    suspend fun updateAddressBook(addressBook: AddressBook)
}