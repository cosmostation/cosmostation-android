package wannabit.io.cosmostaion.network.req.neutron

data class SwapListReq(val pairs: Pairs)
class Pairs

data class SwapRateReq(val simulation: Simulation)
data class Simulation(val offer_asset: OfferAsset, val ask_asset_info: AskAssetInfo)
data class OfferAsset(val info: Info, val amount: String)
data class AskAssetInfo(val native_token: NativeToken)
data class Info(val native_token: NativeToken)
data class NativeToken(val denom: String)
