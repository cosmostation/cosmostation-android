package wannabit.io.cosmostaion.chain.evmClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainEthereum : EthereumLine() {

    override var chainType: ChainType? = ChainType.ETH_TYPE
    override var name: String = "Ethereum"
    override var tag: String = "ethereum60"
    override var logo: Int = R.drawable.chain_ethereum
    override var swipeLogo: Int = R.drawable.chain_swipe_ethereum
    override var apiName: String = "ethereum"

    override var coinSymbol: String = "ETH"
    override var coinGeckoId: String = "ethereum"
    override var coinLogo: Int = R.drawable.token_eth

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var rpcURL: String = "http://eth4.cosmostation.io/rpc"
    override var explorerURL: String = "https://etherscan.io/"

    // TEST NET
//    override var rpcURL = "https://rpc.ankr.com/eth_goerli"
//    override var explorerURL = "https://etherscan.io/"

    override var addressURL = explorerURL + "address/"
    override var txURL = explorerURL + "tx/%@"
}