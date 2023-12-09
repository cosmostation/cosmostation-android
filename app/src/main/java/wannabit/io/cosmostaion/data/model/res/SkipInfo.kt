package wannabit.io.cosmostaion.data.model.res

data class SkipChainResponse(
    val chains: MutableList<SkipChain>?
)

data class SkipChain(
    val chain_name: String?,
    val chain_id: String?,
)

data class SkipRouteResponse(
    val source_asset_denom: String?,
    val source_asset_chain_id: String?,
    val dest_asset_denom: String?,
    val dest_asset_chain_id: String?,
    val amount_in: String?,
    val amount_out: String?,
    val operations: MutableList<Operation>?,
    val chain_ids: MutableList<String>?,
    val swap_venue: SwapVenue?
)

data class Operation(
    val transfer: Transfer?,
    val swap: Swap?
) {
    data class Transfer(
        val port: String?,
        val channel: String?,
        val chain_id: String?,
        val pfm_enabled: Boolean?,
        val dest_denom: String?,
        val supports_memo: Boolean?
    )

    data class Swap(
        val swap_in: SwapIn?,
        val estimated_affiliate_fee: String?
    ) {
        data class SwapIn(
            val swap_venue: SwapVenue?,
            val swap_operations: MutableList<SwapOperation>,
            val swap_amount_in: String
        )
    }
}

data class SwapOperation(
    val pool: String?,
    val denom_in: String?,
    val denom_out: String?
)

data class SwapVenue(
    val name: String?,
    val chain_id: String
)

data class SkipMsgResponse(
    val msgs: MutableList<Msg>
) {
    data class Msg(
        val path: MutableList<String>?,
        val chain_id: String?,
        val msg_type_url: String?,
        val msg: String
    )
}

data class SkipErrorResponse(
    val code: Int?,
    val message: String?
)
