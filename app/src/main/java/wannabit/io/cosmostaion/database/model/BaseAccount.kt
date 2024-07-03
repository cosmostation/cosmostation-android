package wannabit.io.cosmostaion.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import net.i2p.crypto.eddsa.Utils
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.CryptoHelper
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.util.UUID

@Entity(tableName = "account")
@Parcelize
data class BaseAccount(
    @ColumnInfo(name = "uuid") var uuid: String,
    @ColumnInfo(name = "resource") var resource: String,
    @ColumnInfo(name = "spec") var spec: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "type") var type: BaseAccountType = BaseAccountType.NONE,
    @ColumnInfo(name = "lastHDPath") var lastHDPath: String,
    @ColumnInfo(name = "sortOrder") var sortOrder: Long = 999
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @IgnoredOnParcel
    @delegate:Ignore
    val seed: ByteArray? by lazy {
        CryptoHelper.doDecryptData(
            CosmostationConstants.ENCRYPT_MNEMONIC_KEY + uuid, resource, spec
        )?.let { hexEntropy ->
            BaseKey.getHDSeed(Utils.hexToBytes(hexEntropy))
        }
    }

    @IgnoredOnParcel
    @delegate:Ignore
    val privateKey: ByteArray? by lazy {
        Utils.hexToBytes(
            CryptoHelper.doDecryptData(
                CosmostationConstants.ENCRYPT_PRIVATE_KEY + uuid, resource, spec
            )
        )
    }

    @IgnoredOnParcel
    @Ignore
    var allChains: MutableList<BaseChain> = mutableListOf()

    @IgnoredOnParcel
    @Ignore
    var allEvmLineChains: MutableList<EthereumLine> = mutableListOf()

    @IgnoredOnParcel
    @Ignore
    var allCosmosLineChains: MutableList<CosmosLine> = mutableListOf()

    fun initAccount() {
        allChains = allChains()

//        allEvmLineChains = allEvmLines()
//        allCosmosLineChains = allCosmosLines()
//        if (type == BaseAccountType.PRIVATE_KEY) {
//            allCosmosLineChains =
//                allCosmosLines().filter { it.isDefault || it.tag == "okt996_Secp" }.toMutableList()
//        }
        sortLine()
    }

//    fun sortedDisplayEvmLines(): MutableList<EthereumLine> {
//        val displayNames = Prefs.getDisplayEvmChains(this)
//        return allEvmLineChains.associateBy { line ->
//            displayNames.firstOrNull { it == line.tag }
//        }.filterKeys { it != null }.map { it.value }.toMutableList()
//    }

    fun sortLine() {
        val displayChains = Prefs.getDisplayChains(this)
        allChains.sortWith { o1, o2 ->
            when {
                o1.tag == "cosmos118" -> -1
                o2.tag == "cosmos118" -> 1
                lastValue(o1.tag) > lastValue(o2.tag) -> -1
                lastValue(o1.tag) < lastValue(o2.tag) -> 1
                else -> 0
            }
        }

        allChains.sortWith { o1, o2 ->
            when {
                o1.tag == "cosmos118" -> -1
                o2.tag == "cosmos118" -> 1
                displayChains.contains(o1.tag) && !displayChains.contains(o2.tag) -> -1
                displayChains.contains(o2.tag) && !displayChains.contains(o1.tag) -> 1
                else -> 0
            }
        }
    }

    fun sortedDisplayChains(): MutableList<BaseChain> {
        val displayNames = Prefs.getDisplayChains(this)
        return allChains.associateBy { line ->
            displayNames.firstOrNull { it == line.tag }
        }.filterKeys { it != null }.map { it.value }.toMutableList()
    }

    fun updateAllValue() {
        sortedDisplayChains().forEach { line ->
            line.allValue(false)
        }
    }

    private fun lastValue(tag: String): BigDecimal {
        return AppDatabase.getInstance().refAddressDao().selectRefAddress(id, tag)?.lastUsdValue()
            ?: BigDecimal.ZERO
    }

//    fun reSortEvmChains() {
//        val allValue = allEvmLineChains.associateWith { it.allValue(true) }
//        allEvmLineChains.sortWith(compareBy<EthereumLine> { it.tag != "ethereum60" }.thenByDescending { allValue[it] })
//    }

    fun reSortChains() {
        val allValue = allChains.associateWith { it.allValue(true) }
        allChains.sortWith(compareBy<BaseChain> { it.tag != "cosmos118" }.thenByDescending { allValue[it] })
    }

    companion object {
        fun createByMnemonic(name: String, mnemonic: String, lastHDPath: String): BaseAccount? {
            val uuid = UUID.randomUUID().toString()
            val encR = CryptoHelper.doEncryptData(
                CosmostationConstants.ENCRYPT_MNEMONIC_KEY + uuid, mnemonic, false
            )
            return if (encR != null) {
                BaseAccount(
                    uuid,
                    encR.encDataString!!,
                    encR.ivDataString!!,
                    name,
                    BaseAccountType.MNEMONIC,
                    lastHDPath
                )
            } else {
                null
            }
        }

        fun createByPrivate(name: String, privateKey: String): BaseAccount? {
            val uuid = UUID.randomUUID().toString()
            val encR = CryptoHelper.doEncryptData(
                CosmostationConstants.ENCRYPT_PRIVATE_KEY + uuid, privateKey, false
            )
            return if (encR != null) {
                BaseAccount(
                    uuid,
                    encR.encDataString!!,
                    encR.ivDataString!!,
                    name,
                    BaseAccountType.PRIVATE_KEY,
                    "0"
                )
            } else {
                null
            }
        }
    }
}

enum class BaseAccountType { MNEMONIC, PRIVATE_KEY, LEDGER, NONE }
