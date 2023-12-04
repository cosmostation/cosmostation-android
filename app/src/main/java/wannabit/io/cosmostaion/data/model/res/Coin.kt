package wannabit.io.cosmostaion.data.model.res

data class Coin(val denom: String, val amount: String, val type: CoinType)

enum class CoinType { STAKE, NATIVE, IBC, BRIDGE, ETC }
