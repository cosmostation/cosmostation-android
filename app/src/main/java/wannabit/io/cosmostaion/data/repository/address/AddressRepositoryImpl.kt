package wannabit.io.cosmostaion.data.repository.address

import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.AddressBook

class AddressRepositoryImpl : AddressRepository {

    override suspend fun insertAddressBook(addressBook: AddressBook) {
        AppDatabase.getInstance().addressBookDao().insert(addressBook)
    }

    override suspend fun selectAllAddressBook(): MutableList<AddressBook> =
        AppDatabase.getInstance().addressBookDao().selectAll()

    override suspend fun selectAddressBook(addressBookId: Long): AddressBook? =
        AppDatabase.getInstance().addressBookDao().selectAddressBook(addressBookId)

    override suspend fun deleteAddressBook(addressBook: AddressBook) {
        AppDatabase.getInstance().addressBookDao().delete(addressBook)
    }

    override suspend fun updateAddressBook(addressBook: AddressBook) {
        AppDatabase.getInstance().addressBookDao().updateAddressBook(
            addressBook.id,
            addressBook.bookName,
            addressBook.memo,
            addressBook.lastTime
        )
    }

    override suspend fun updateAddressBookWithChainTag(addressBook: AddressBook) {
        AppDatabase.getInstance().addressBookDao()
            .updateAddressBookWithChainTag(addressBook.id, addressBook.chainName)
    }
}