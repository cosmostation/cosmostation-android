package wannabit.io.cosmostaion.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import wannabit.io.cosmostaion.database.model.AddressBook

@Dao
interface AddressBookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(addressBook: AddressBook): Long

    @Delete
    suspend fun delete(addressBook: AddressBook)

    @Query("select * from AddressBook")
    fun selectAll(): MutableList<AddressBook>

    @Query("select * from AddressBook where id = :id")
    fun selectAddressBook(id: Long): AddressBook?

    @Query("update AddressBook set bookName = :bookName, memo = :memo, lastTime = :lastTime where id = :addressBookId")
    suspend fun updateAddressBook(addressBookId: Long, bookName: String, memo: String?, lastTime: Long)
}