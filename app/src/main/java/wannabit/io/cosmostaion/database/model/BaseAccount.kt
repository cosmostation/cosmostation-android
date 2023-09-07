package wannabit.io.cosmostaion.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.allCosmosLines

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

//    @delegate:Ignore
//    val seed: ByteArray? by lazy {
//        Utils.hexToBytes(CipherHelper.decrypt(encSeed))
//    }

    @Ignore
    lateinit var allCosmosLineChains: MutableList<CosmosLine>

    fun initAllData() {
        allCosmosLineChains = mutableListOf()
        allCosmosLineChains.clear()
        allCosmosLines().forEach { line ->
            allCosmosLineChains.add(line)
        }

        if (type == BaseAccountType.MNEMONIC) {
            allCosmosLineChains.forEach { line ->
                line.loadAuth()
            }

        } else if (type == BaseAccountType.PRIVATE_KEY) {

        }
    }
}

enum class BaseAccountType { MNEMONIC, PRIVATE_KEY, LEDGER, NONE }
