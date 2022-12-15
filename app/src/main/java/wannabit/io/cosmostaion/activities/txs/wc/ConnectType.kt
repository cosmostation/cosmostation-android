package wannabit.io.cosmostaion.activities.txs.wc

enum class ConnectType {
    InternalDapp, ExternalDapp, QRWalletConnect, DeepLinkWalletConnect, DeepLinkWalletConnectCommon;

    fun isDapp(): Boolean {
        return this == InternalDapp || this == ExternalDapp
    }

    fun hasBaseAccount(): Boolean {
        return this == InternalDapp || this == QRWalletConnect
    }
}
