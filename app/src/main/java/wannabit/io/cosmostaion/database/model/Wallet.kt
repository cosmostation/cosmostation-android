package wannabit.io.cosmostaion.database.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import net.i2p.crypto.eddsa.Utils
import wannabit.io.cosmostaion.database.CipherHelper

@Entity
data class Wallet(
    @PrimaryKey val id: Long,
    val uuid: String,
    val resource: String,
    val spec: String,
    val encSeed: String,
    val nickname: String,
    val wordsCnt: Int = 0,
    val walletType: WalletType,
    val customPath: Int,
    val modifyTime: Long
) {
    @delegate:Ignore
    val seed: ByteArray? by lazy {
        Utils.hexToBytes(CipherHelper.decrypt(encSeed))
    }
}
