package wannabit.io.cosmostaion.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType

@Dao
interface BaseAccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(baseAccount: BaseAccount): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(baseAccounts: List<BaseAccount>): List<Long>

    @Delete
    suspend fun delete(baseAccount: BaseAccount)

    @Query("select * from account where (uuid is not null and uuid != '') and (resource is not null and resource != '') and (spec is not null and spec != '') order by type, sortOrder")
    fun selectAll(): List<BaseAccount>

    @Query("select * from account where id = :id AND (uuid is not null and uuid != '') and (resource is not null and resource != '') and (spec is not null and spec != '')")
    fun selectAccount(id: Long): BaseAccount?

    @Query("update account set name = :name, sortOrder = :sortOrder where id = :id")
    suspend fun updateAccount(id: Long, name: String, sortOrder: Long)

    @Query("select * from account where type = :type AND (uuid is not null and uuid != '') and (resource is not null and resource != '') and (spec is not null and spec != '') order by sortOrder")
    fun selectsAccount(type: BaseAccountType): List<BaseAccount>
}