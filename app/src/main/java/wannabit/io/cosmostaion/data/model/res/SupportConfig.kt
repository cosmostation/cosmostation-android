package wannabit.io.cosmostaion.data.model.res

data class SupportConfig(
    val supportChainNames: List<String>?,
    val supportChainIds: List<String>?,
    val customChains: List<CustomChain>?
)

data class CustomChain(
    val chainId: String?,
    val denom: String?,
    val prefix: String?,
    val default_address_type: Boolean?
)