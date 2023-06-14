package wannabit.io.cosmostaion.database.model

import java.math.BigInteger

data class Balance1(val id: Long, val walletId: Long, val chainName: String, val amount: BigInteger, val price: Int, val lastUpdateTime: Long)
