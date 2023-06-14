package wannabit.io.cosmostaion.database.model

import java.math.BigInteger

data class Balance(val chainName: String, val amount: BigInteger, val price: Int)
