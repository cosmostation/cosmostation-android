package wannabit.io.cosmostaion.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import wannabit.io.cosmostaion.database.model.RefAddress

@Dao
interface RefAddressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(refAddress: RefAddress): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(refAddresses: List<RefAddress>): List<Long>

    @Update
    suspend fun update(refAddress: RefAddress): Int

    @Query("delete from refAddress where accountId = :accountId")
    suspend fun delete(accountId: Long)

    @Query("select * from refAddress")
    fun selectAll(): List<RefAddress>

    @Query("select * from refAddress where accountId = :accountId")
    fun selectRefAddress(accountId: Long): RefAddress?

    @Query("select * from refAddress where accountId = :accountId and chainTag = :chainTag and dpAddress = :dpAddress")
    fun getRefAddress(accountId: Long?, chainTag: String?, dpAddress: String?): RefAddress?

}