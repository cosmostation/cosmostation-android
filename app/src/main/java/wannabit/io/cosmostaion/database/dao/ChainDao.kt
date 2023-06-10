package wannabit.io.cosmostaion.database.dao

import androidx.room.*
import wannabit.io.cosmostaion.database.model.Wallet

@Dao
interface ChainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wallet: Wallet): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(wallets: List<Wallet>): List<Long>

    @Delete
    suspend fun delete(wallet: Wallet)

    @Query("select * from wallet")
    suspend fun selectAll(): List<Wallet>

    @Query("select * from wallet where id = :id")
    suspend fun selectById(id: Long): Wallet?
}