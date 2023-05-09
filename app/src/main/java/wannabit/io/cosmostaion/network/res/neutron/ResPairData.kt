package wannabit.io.cosmostaion.network.res.neutron

data class ResPairData(val pairs: List<Pair>)

data class Pair(
    val asset_infos: List<AssetInfo>,
    val contract_addr: String,
    val liquidity_token: String,
)

data class AssetInfo(
    val native_token: NativeToken?,
    val token: Token?
)

data class NativeToken(val denom: String)
data class Token(val contract_addr: String)
