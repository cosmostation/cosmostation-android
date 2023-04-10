package wannabit.io.cosmostaion.network.res

data class ResRelayer(
    var relayer: Relayer
)

data class Relayer(
    var last_ping_at: String
)