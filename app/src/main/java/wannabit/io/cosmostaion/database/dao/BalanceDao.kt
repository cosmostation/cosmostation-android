package wannabit.io.cosmostaion.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import wannabit.io.cosmostaion.database.model.Balance

@Dao
interface BalanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(balance: Balance): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(balances: List<Balance>): List<Long>

    @Delete
    suspend fun delete(balance: Balance)

    @Query("select * from Balance")
    suspend fun selectAll(): List<Balance>
}