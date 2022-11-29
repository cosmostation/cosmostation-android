package wannabit.io.cosmostaion.base.chains

class TempCustomChainManager {
    companion object {
        val instance = TempCustomChainManager()
    }

    val infos = listOf(
        CustomChainInfo(
            0,
            "Crescent-testnet",
            "ucre",
            "mooncat-2-3",
            "cre",
            "testnet-endpoint.crescent.network"
        ), CustomChainInfo(
            1, "yong", "yong", "yong-1", "yong", "testnet-endpoint.crescent.network"
        )
    )
}