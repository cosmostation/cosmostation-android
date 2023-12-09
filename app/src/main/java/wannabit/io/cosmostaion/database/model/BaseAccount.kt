package wannabit.io.cosmostaion.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import net.i2p.crypto.eddsa.Utils
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.CryptoHelper
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
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

    @delegate:Ignore
    val seed: ByteArray? by lazy {
        CryptoHelper.doDecryptData(CosmostationConstants.ENCRYPT_MNEMONIC_KEY + uuid, resource, spec)?.let { hexEntropy ->
            BaseKey.getByteSeedFromWords(BaseKey.getMnemonicWords(Utils.hexToBytes(hexEntropy)))
        }
    }

    @delegate:Ignore
    val privateKey: ByteArray? by lazy {
        Utils.hexToBytes(CryptoHelper.doDecryptData(CosmostationConstants.ENCRYPT_PRIVATE_KEY + uuid, resource, spec))
    }

    @Ignore
    var allCosmosLineChains: MutableList<CosmosLine> = mutableListOf()
    @Ignore
    var displayCosmosLineChains: MutableList<CosmosLine> = mutableListOf()

    fun initAccount() {
        allCosmosLineChains = allCosmosLines()
        sortCosmosLine()
    }

    fun initAllData() {
        if (type == BaseAccountType.MNEMONIC) {
            allCosmosLineChains.forEach { line ->
                if (line.address?.isEmpty() == true) {
                    line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                }
                if (!line.fetched) {
                    line.loadData(id)
                }
            }

        } else if (type == BaseAccountType.PRIVATE_KEY) {
            allCosmosLineChains.forEach { line ->
                if (line.address?.isEmpty() == true) {
                    line.setInfoWithPrivateKey(privateKey)
                }
                if (!line.fetched) {
                    line.loadData(id)
                }
            }
        }
    }

    fun initDisplayData() {
        displayCosmosLineChains.clear()
        val displayNames = Prefs.getDisplayChains(this)
        allCosmosLineChains.forEach { line ->
            val displayChain = displayNames.firstOrNull { it == line.tag }
            if (displayChain != null) {
                displayCosmosLineChains.add(line)
            }
        }

        if (type == BaseAccountType.MNEMONIC) {
            displayCosmosLineChains.forEach { line ->
                if (line.address?.isEmpty() == true) {
                    line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                }
                if (!line.fetched) {
                    line.loadData(id)
                }
            }

        } else if (type == BaseAccountType.PRIVATE_KEY) {
            displayCosmosLineChains.forEach { line ->
                if (line.address?.isEmpty() == true) {
                    line.setInfoWithPrivateKey(privateKey)
                }
                if (!line.fetched) {
                    line.loadData(id)
                }
            }
        }
    }

    fun initTargetChainsData(targetChains: MutableList<CosmosLine>) {
        if (type == BaseAccountType.MNEMONIC) {
            targetChains.forEach { line ->
                if (line.address?.isEmpty() == true) {
                    line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                }
                if (!line.fetched) {
                    line.loadData(id)
                }
            }

        } else if (type == BaseAccountType.PRIVATE_KEY) {
            targetChains.forEach { line ->
                if (line.address?.isEmpty() == true) {
                    line.setInfoWithPrivateKey(privateKey)
                }
                if (!line.fetched) {
                    line.loadData(id)
                }
            }
        }
    }

    fun updateAllValue() {
        displayCosmosLineChains.forEach { line ->
            line.allValue()
        }
    }

    private fun lastValue(tag: String): BigDecimal {
        return AppDatabase.getInstance().refAddressDao().selectRefAddress(id, tag)?.lastUsdValue() ?: BigDecimal.ZERO
    }

    fun sortCosmosLine() {
        allCosmosLineChains.sortWith { o1, o2 ->
            when {
                o1.tag == "cosmos118" -> -1
                o2.tag == "cosmos118" -> 1
                else -> {
                    when {
                        lastValue(o1.tag) > lastValue(o2.tag) -> -1
                        lastValue(o1.tag) < lastValue(o2.tag) -> 1
                        else -> 0
                    }
                }
            }
        }
        allCosmosLineChains.sortWith { o1, o2 ->
            when {
                o1.tag == "cosmos118" -> -1
                o2.tag == "cosmos118" -> 1
                Prefs.getDisplayChains(this).contains(o1.tag) && !Prefs.getDisplayChains(this).contains(o2.tag) -> -1
                else -> 1
            }
        }
    }

    fun reSortCosmosChains() {
        allCosmosLineChains.sortWith { o1, o2 ->
            when {
                o1.tag == "cosmos118" -> -1
                o2.tag == "cosmos118" -> 1
                else -> {
                    when {
                        o1.allAssetValue() > o2.allAssetValue() -> -1
                        else -> 1
                    }
                }
            }
        }
    }

    fun initOnlyKeyData(): MutableList<CosmosLine> {
        if (type == BaseAccountType.MNEMONIC) {
            allCosmosLineChains.forEach { line ->
                if (line.address?.isEmpty() == true) {
                    line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                }
            }


        } else if (type == BaseAccountType.PRIVATE_KEY) {
            allCosmosLineChains.forEach { line ->
                if (line.address?.isEmpty() == true) {
                    line.setInfoWithPrivateKey(privateKey)
                }
            }
        }
        return allCosmosLineChains
    }

    companion object {
        fun createByMnemonic(name: String, mnemonic: String, lastHDPath: String): BaseAccount? {
            val uuid = UUID.randomUUID().toString()
            val encR = CryptoHelper.doEncryptData(CosmostationConstants.ENCRYPT_MNEMONIC_KEY + uuid, mnemonic, false)
            return if (encR != null) {
                BaseAccount(uuid, encR.encDataString!!, encR.ivDataString!!, name, BaseAccountType.MNEMONIC, lastHDPath)
            } else {
                null
            }
        }

        fun createByPrivate(name: String, privateKey: String): BaseAccount? {
            val uuid = UUID.randomUUID().toString()
            val encR = CryptoHelper.doEncryptData(CosmostationConstants.ENCRYPT_PRIVATE_KEY + uuid, privateKey, false)
            return if (encR != null) {
                BaseAccount(uuid, encR.encDataString!!, encR.ivDataString!!, name, BaseAccountType.PRIVATE_KEY, "0")
            } else {
                null
            }
        }
    }
}

enum class BaseAccountType { MNEMONIC, PRIVATE_KEY, LEDGER, NONE }
