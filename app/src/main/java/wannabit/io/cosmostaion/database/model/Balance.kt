package wannabit.io.cosmostaion.database.model

import androidx.room.Entity

@Entity(primaryKeys = ["walletId", "chain", "denom"])
data class Balance(val walletId: Long, val chain: String, val denom: String, val amount: String, val lastModifyTime: Long)
