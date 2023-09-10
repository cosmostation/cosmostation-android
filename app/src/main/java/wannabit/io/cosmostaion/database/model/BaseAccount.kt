package wannabit.io.cosmostaion.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.i2p.crypto.eddsa.Utils
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.database.CryptoHelper

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

    @delegate:Ignore
    val seed: ByteArray? by lazy {
        CryptoHelper.doDecryptData(CosmostationConstants.ENCRYPT_MNEMONIC_KEY + uuid, resource, spec)?.let { hexEntropy ->
            BaseKey.getByteSeedFromWords(BaseKey.getMnemonicWords(Utils.hexToBytes(hexEntropy)))
        }
    }

    @delegate:Ignore
    val privateKey: ByteArray? by lazy {
        CryptoHelper.doDecryptData(CosmostationConstants.ENCRYPT_PRIVATE_KEY + uuid, resource, spec)?.toByteArray()
    }


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
                line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                line.loadAuth()
            }

        } else if (type == BaseAccountType.PRIVATE_KEY) {
            allCosmosLines().forEach { line ->
                line.setInfoWithPrivateKey(privateKey)
                line.loadAuth()
            }
        }
    }
}

enum class BaseAccountType { MNEMONIC, PRIVATE_KEY, LEDGER, NONE }
