package wannabit.io.cosmostaion.activities.txs.wc

enum class ConnectType {
    InternalDapp, ExternalDapp, QRWalletConnect, DeepLinkWalletConnect;

    fun isDapp(): Boolean {
        return this == InternalDapp || this == ExternalDapp
    }

    fun hasBaseAccount(): Boolean {
        return this == InternalDapp || this == QRWalletConnect
    }
}
