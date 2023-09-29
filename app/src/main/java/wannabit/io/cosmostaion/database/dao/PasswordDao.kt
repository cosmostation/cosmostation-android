package wannabit.io.cosmostaion.database.dao

import androidx.room.*
import wannabit.io.cosmostaion.database.model.Password

@Dao
interface PasswordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(password: Password): Long

    @Delete
    suspend fun delete(password: Password)

    @Query("select * from password")
    fun selectAll(): List<Password>
}