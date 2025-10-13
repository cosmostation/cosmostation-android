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
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBabylon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSolana
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
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

    fun initAccount() {
        allChains = allChains()
        sortLine(Prefs.chainFilter)
    }

    fun sortLine(isValueFilter: Boolean) {
        val displayChains = Prefs.getDisplayChains(this)
        synchronized(allChains) {
            if (isValueFilter) {
                allChains.sortWith(compareBy(
                    { if (it.tag == "cosmos118") 0 else 1 },
                    { it.name.lowercase() }
                ))

                allChains.sortWith { o1, o2 ->
                    when {
                        o1.tag == "cosmos118" -> -1
                        o2.tag == "cosmos118" -> 1
                        displayChains.contains(o1.tag) && !displayChains.contains(o2.tag) -> -1
                        displayChains.contains(o2.tag) && !displayChains.contains(o1.tag) -> 1
                        else -> 0
                    }
                }

            } else {
                allChains.sortWith { o1, o2 ->
                    when {
                        o1.tag == "cosmos118" && o2.tag != "cosmos118" -> -1
                        o1.tag != "cosmos118" && o2.tag == "cosmos118" -> 1
                        else -> lastValue(o2.tag).compareTo(lastValue(o1.tag))
                    }
                }

                allChains.sortWith { o1, o2 ->
                    when {
                        o1.tag == "cosmos118" && o2.tag != "cosmos118" -> -1
                        o1.tag != "cosmos118" && o2.tag == "cosmos118" -> 1
                        displayChains.contains(o1.tag) && !displayChains.contains(o2.tag) -> -1
                        displayChains.contains(o2.tag) && !displayChains.contains(o1.tag) -> 1
                        else -> 0
                    }
                }
            }
        }
    }

    fun sortedDisplayChains(): MutableList<BaseChain> {
        val displayNames = Prefs.getDisplayChains(this)
        val filteredChains = allChains.filter { chain ->
            displayNames.contains(chain.tag)
        }
        return filteredChains.toMutableList()
    }

    fun updateAllValue() {
        val chains = allChains.toList()

        chains.forEach { chain ->
            when (chain) {
                is ChainBitCoin86 -> {
                    chain.coinValue = chain.btcFetcher()?.allAssetValue()
                }

                is ChainSui -> {
                    chain.coinValue = chain.suiFetcher()?.allAssetValue()
                }

                is ChainIota -> {
                    chain.coinValue = chain.iotaFetcher()?.allAssetValue()
                }

                is ChainSolana -> {
                    chain.coinValue = chain.solanaFetcher()?.allAssetValue()
                }

                is ChainOkt996Keccak -> {
                    chain.coinValue = chain.oktFetcher()?.allAssetValue()
                }

                is ChainOktEvm -> {
                    chain.coinValue = chain.oktFetcher()?.allAssetValue()
                    chain.tokenValue = chain.evmRpcFetcher()?.allTokenValue(id)
                }

                is ChainGnoTestnet -> {
                    chain.coinValue = chain.gnoRpcFetcher()?.allAssetValue()
                    chain.tokenValue = chain.gnoRpcFetcher()?.allGrc20TokenValue(id)
                }

                is ChainBabylon -> {
                    chain.coinValue = chain.babylonFetcher()?.allAssetValue()
                    chain.tokenValue = chain.cosmosFetcher()?.allTokenValue(id)
                }

                else -> {
                    if (chain.isEvmCosmos()) {
                        chain.coinValue = chain.cosmosFetcher()?.allAssetValue()
                        chain.tokenValue = chain.cosmosFetcher()?.allTokenValue(id)
                            ?.add(chain.evmRpcFetcher()?.allTokenValue(id))

                    } else if (chain.supportCosmos()) {
                        chain.coinValue = chain.cosmosFetcher()?.allAssetValue()
                        chain.tokenValue = chain.cosmosFetcher()?.allTokenValue(id)

                    } else {
                        chain.coinValue = chain.evmRpcFetcher()?.allAssetValue()
                        chain.tokenValue = chain.evmRpcFetcher()?.allTokenValue(id)
                    }
                }
            }
        }
    }

    private fun lastValue(tag: String): BigDecimal {
        return AppDatabase.getInstance().refAddressDao().selectRefAddress(id, tag)?.lastUsdValue()
            ?: BigDecimal.ZERO
    }

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
