package wannabit.io.cosmostaion.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import wannabit.io.cosmostaion.database.model.BaseAccount

@Dao
interface BaseAccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(baseAccount: BaseAccount): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(baseAccounts: List<BaseAccount>): List<Long>

    @Delete
    suspend fun delete(baseAccount: BaseAccount)

    @Query("select * from account")
    fun selectAll(): List<BaseAccount>

    @Query("select * from account where id = :id")
    fun selectAccount(id: Long): BaseAccount?
}