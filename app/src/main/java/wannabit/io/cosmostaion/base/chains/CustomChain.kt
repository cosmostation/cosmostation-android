package wannabit.io.cosmostaion.base.chains

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain

class CustomChain(val chainInfo: CustomChainInfo) : ChainConfig() {
    override fun baseChain() = BaseChain.CUSTOM
    override fun chainImg() = R.drawable.chain_cosmos
    override fun chainInfoImg() = R.drawable.infoicon_cosmos
    override fun chainInfoTitle() = R.string.str_unknown_msg
    override fun chainInfoMsg() = R.string.str_unknown_msg
    override fun chainColor() = R.color.color_cosmos
    override fun chainBgColor() = R.color.colorTransBgCosmos
    override fun chainTabColor() = R.color.color_tab_myvalidator_cosmos
    override fun chainName() = chainInfo.name
    override fun chainKoreanName() = ""
    override fun chainIdPrefix() = chainInfo.chainId
    override fun mainDenomImg() = R.drawable.token_cosmos
    override fun mainDenom() = chainInfo.denom
    override fun addressPrefix() = chainInfo.prefix
    override fun dexSupport() = false
    override fun wcSupport() = false
    override fun authzSupport() = false
    override fun grpcUrl() = chainInfo.grpcUrl
    override fun apiUrl() = ""
    override fun explorerUrl() = ""
    override fun monikerUrl() = ""
    override fun homeInfoLink() = ""
    override fun blogInfoLink() = ""
    override fun coingeckoLink() = ""
    override fun chainKey() = super.chainKey() + chainInfo.chainId
    override fun channelMain(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(grpcUrl(), grpcPort()).usePlaintext()
            .useTransportSecurity().build()
    }
}