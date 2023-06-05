package wannabit.io.cosmostaion.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wallet(
    @PrimaryKey val id: Long,
    val uuid: String,
    val resource: String,
    val spec: String,
    val nickname: String,
    val wordsCnt: Int = 0,
    val walletType: WalletType,
    val customPath: Int,
    val modifyTime: Long
)

enum class WalletType { MNEMONIC, PRIVATE_KEY, LEDGER }