package wannabit.io.cosmostaion.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "refAddress")
data class RefAddress(
    @ColumnInfo(name = "accountId") var accountId: Long,
    @ColumnInfo(name = "chainTag") var chainTag: String,
    @ColumnInfo(name = "dpAddress") var dpAddress: String?,
    @ColumnInfo(name = "lastMainValue") var lastMainValue: String? = "0",
    @ColumnInfo(name = "lastMainAmount") var lastMainAmount: String? = "0",
    @ColumnInfo(name = "lastTokenValue") var lastTokenValue: String? = "0",
    @ColumnInfo(name = "lastCoinCnt") var lastCoinCnt: Long? = 0,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}