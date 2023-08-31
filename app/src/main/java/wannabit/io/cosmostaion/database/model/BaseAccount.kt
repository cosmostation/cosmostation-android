package wannabit.io.cosmostaion.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "account")
data class BaseAccount(
    @ColumnInfo(name = "uuid") var uuid: String,
    @ColumnInfo(name = "resource") var resource: String,
    @ColumnInfo(name = "spec") var spec: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "type") var type: BaseAccountType = BaseAccountType.NONE,
    @ColumnInfo(name = "lastHDPath") var lastHDPath: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    constructor(
        uuid: String,
        resource: String,
        spec: String,
        name: String,
        type: BaseAccountType,
        lastHDPath: String,
        id: Long
    ) : this(uuid, resource, spec, name, type, lastHDPath) {
        this.id = id
    }

//    @delegate:Ignore
//    val seed: ByteArray? by lazy {
//        Utils.hexToBytes(CipherHelper.decrypt(encSeed))
//    }
}

enum class BaseAccountType { MNEMONIC, PRIVATE_KEY, LEDGER, NONE }
