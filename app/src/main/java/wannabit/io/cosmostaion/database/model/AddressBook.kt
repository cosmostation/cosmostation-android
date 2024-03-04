package wannabit.io.cosmostaion.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Entity(tableName = "AddressBook")
@Parcelize
data class AddressBook(
    @ColumnInfo(name = "bookName") var bookName: String,
    @ColumnInfo(name = "chainName") var chainName: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "memo") var memo: String = "",
    @ColumnInfo(name = "lastTime") var lastTime: Long
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}