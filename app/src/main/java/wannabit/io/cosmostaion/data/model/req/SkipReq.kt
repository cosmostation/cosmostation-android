package wannabit.io.cosmostaion.data.model.req

import wannabit.io.cosmostaion.data.model.res.Operation

data class SkipRouteReq(
    val amount_in: String,
    val source_asset_denom: String?,
    val source_asset_chain_id: String?,
    val dest_asset_denom: String?,
    val dest_asset_chain_id: String?,
    val cumulative_affiliate_fee_bps: String? = "50"
)

data class SkipMsgReq(
    val address_list: MutableList<String>?,
    val slippage_tolerance_percent: String?,
    val amount_in: String?,
    val source_asset_denom: String?,
    val source_asset_chain_id: String?,
    val amount_out: String?,
    val dest_asset_denom: String?,
    val dest_asset_chain_id: String?,
    val operations: MutableList<Operation>?,
    val chain_ids_to_affiliates: Map<String, ChainInfo>?
)

data class ChainInfo(
    val affiliates: List<Affiliate>
)

data class Affiliate(
    val address: String?,
    val basis_points_fee: String?
)

