package wannabit.io.cosmostaion.network.req.neutron

data class SwapRateReq(val simulation: Simulation)
data class Simulation(val offer_asset: OfferAsset, val ask_asset_info: AskAssetInfo)
data class OfferAsset(val info: Info, val amount: String)
data class AskAssetInfo(val native_token: NativeToken?, val token: Token?)
data class Info(val native_token: NativeToken?, val token: Token?)
data class NativeToken(val denom: String)
data class Token(val contract_addr: String)

//data class SwapContractRateReq(val simulation: ContractSimulation)
//data class ContractSimulation(val offer_asset: ContractOfferAsset, val ask_asset_info: ContractAskAsset)
//data class ContractOfferAsset(val info: Info, val amount: String)
//data class ContractAskAsset(val token: Token)
//data class Token(val contract_addr: String)