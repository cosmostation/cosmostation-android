package wannabit.io.cosmostaion.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import wannabit.io.cosmostaion.database.model.RefAddress

@Dao
interface RefAddressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(refAddress: RefAddress): Long

    @Query("update refAddress set lastMainValue = :lastMainValue, lastMainAmount = :lastMainAmount, lastCoinCnt = :lastCoinCnt where accountId = :accountId and chainTag = :chainTag and dpAddress = :dpAddress")
    suspend fun updateMain(
        lastMainValue: String?,
        lastMainAmount: String?,
        lastCoinCnt: Long?,
        accountId: Long?,
        chainTag: String?,
        dpAddress: String?
    )

    @Query("update refAddress set lastTokenValue = :lastTokenValue where accountId = :accountId and chainTag = :chainTag and dpAddress = :dpAddress")
    suspend fun updateToken(lastTokenValue: String?,
                            accountId: Long?,
                            chainTag: String?,
                            dpAddress: String?)

    @Query("delete from refAddress where accountId = :accountId")
    suspend fun delete(accountId: Long)

    @Query("select * from refAddress")
    fun selectAll(): List<RefAddress>

    @Query("select * from refAddress where accountId = :accountId")
    fun selectRefAddress(accountId: Long): RefAddress?

    @Query("select * from refAddress where accountId = :accountId and chainTag = :chainTag")
    fun selectRefAddress(accountId: Long, chainTag: String?): RefAddress?

    @Query("select * from refAddress where accountId = :accountId and chainTag = :chainTag and dpAddress = :dpAddress")
    fun getRefAddress(accountId: Long?, chainTag: String?, dpAddress: String?): RefAddress?

}