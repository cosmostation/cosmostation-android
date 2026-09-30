package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.fetcher.GnoFetcher

@Parcelize
open class ChainGno : BaseChain(), Parcelable {

    override var name: String = "Gno"
    override var tag: String = "gno118"
    override var apiName: String = "gno"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_RPC
    override var stakeDenom: String = "ugnot"
    override var accountPrefix: String = "g"
    override var mainUrl: String = "https://rpc.onbloc.xyz:443"
    open var gnoIndexerUrl: String = "https://indexer.onbloc.xyz/graphql/query"

    fun gnoRpcFetcher(): GnoFetcher? {
        if (gnoRpcFetcher == null) {
            gnoRpcFetcher = GnoFetcher(this)
        }
        return gnoRpcFetcher
    }
}

const val GNO_HISTORY_QUERY = """
    query(${'$'}addr: String!) {
        getTransactions(where: { _or: [
            { messages: { value: { BankMsgSend: { from_address: { eq: ${'$'}addr } } } } },
            { messages: { value: { BankMsgSend: { to_address: { eq: ${'$'}addr } } } } },
            { messages: { value: { MsgCall: { caller: { eq: ${'$'}addr } } } } },
            { messages: { value: { MsgAddPackage: { creator: { eq: ${'$'}addr } } } } },
            { messages: { value: { MsgRun: { caller: { eq: ${'$'}addr } } } } }
        ]}, order: { heightAndIndex: DESC }) {
            hash
            block_height
            success
            memo
            gas_fee { denom amount }
            messages {
                typeUrl
                route
                value {
                    __typename
                    ... on BankMsgSend { from_address to_address amount }
                    ... on MsgCall { caller pkg_path func args }
                    ... on MsgAddPackage { creator package { path } }
                    ... on MsgRun { caller }
                }
            }
        }
    }
"""

const val GNO_BLOCK_TIME_QUERY = """
    query(${'$'}heights: [FilterBlock!]!) {
        getBlocks(where: { _or: ${'$'}heights }) {
            height
            time
        }
    }
"""