package wannabit.io.cosmostaion.chain.majorClass

import android.content.Context
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.fetcher.SuiFetcher
import wannabit.io.cosmostaion.chain.fetcher.suiCoinSymbol
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey

@Parcelize
class ChainSui : BaseChain(), Parcelable {

    @IgnoredOnParcel
    var suiFetcher: SuiFetcher? = null

    override var name: String = "Sui"
    override var tag: String = "suiMainnet"
    override var apiName: String = "sui"

    override var accountKeyType = AccountKeyType(PubKeyType.SUI_ED25519, "m/44'/784'/0'/0/X")

    override var stakeDenom: String = SUI_MAIN_DENOM
    override var coinSymbol: String = "SUI"
    override var grpcHost: String = "fullnode.mainnet.sui.io"
    override var mainUrl: String = "https://graphql.mainnet.sui.io/graphql"

    override suspend fun setInfoWithPrivateKey(context: Context, privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        mainAddress = BaseKey.getAddressFromPubKey(context, publicKey, accountKeyType.pubkeyType)
    }

    fun suiFetcher(): SuiFetcher? {
        if (suiFetcher != null) return suiFetcher
        suiFetcher = SuiFetcher(this)
        return suiFetcher
    }

    override fun assetImg(originSymbol: String): String {
        suiFetcher()?.let { fetcher ->
            val asset = BaseData.getAsset(apiName, originSymbol)
            val metaData = fetcher.suiCoinMeta[originSymbol]

            if (asset != null) {
                return asset.image ?: ""
            } else if (metaData != null) {
                return metaData.iconUrl
            }
        }
        return ""
    }

    fun assetSymbol(denom: String): String? {
        suiFetcher()?.let { fetcher ->
            val asset = BaseData.getAsset(apiName, denom)
            val metaData = fetcher.suiCoinMeta[denom]

            if (asset != null) {
                return asset.symbol
            } else if (metaData != null) {
                return metaData.symbol
            }
        }
        return denom.suiCoinSymbol() ?: "UnKnown"
    }

    fun assetDecimal(denom: String): Int {
        suiFetcher()?.let { fetcher ->
            val asset = BaseData.getAsset(apiName, denom)
            val metaData = fetcher.suiCoinMeta[denom]

            if (asset != null) {
                return asset.decimals ?: 9
            } else if (metaData != null) {
                return metaData.decimals
            }
        }
        return 9
    }

    fun assetGeckoId(denom: String): String {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            return asset.coinGeckoId ?: ""
        }
        return ""
    }
}

const val SUI_TYPE_COIN = "0x2::coin::Coin"
const val SUI_MAIN_DENOM = "0x2::sui::SUI"
const val SUI_STAKED_TYPE = "0x3::staking_pool::StakedSui"

const val SUI_MIN_STAKE = "1000000000"
const val SUI_FEE_SEND = "4000000"
const val SUI_FEE_STAKE = "50000000"
const val SUI_FEE_UNSTAKE = "50000000"
const val SUI_FEE_DEFAULT = "70000000"

const val EXCHANGE_RATE_QUERY = """
    query(${'$'}tableId: SuiAddress!, ${'$'}epochKey: Base64!) {
        address(address: ${'$'}tableId) {
            dynamicField(name: { type: "u64", bcs: ${'$'}epochKey }) {
                value {
                    ... on MoveValue {
                        json
                    }
                }
            }
        }
    }
"""

const val SUI_HISTORY_QUERY = """
    query(${'$'}addr: SuiAddress!, ${'$'}last: Int!, ${'$'}before: String) {
      transactions(last: ${'$'}last, before: ${'$'}before, filter: {affectedAddress: ${'$'}addr}) {
        pageInfo { hasPreviousPage startCursor }
        nodes {
          digest
          sender { address }
          effects {
            checkpoint { sequenceNumber }
            status
            timestamp
            balanceChanges { nodes { owner { address } coinType { repr } amount } }
            gasEffects { gasSummary { computationCost storageCost storageRebate } }
          }
          kind {
            __typename
            ... on ProgrammableTransaction {
              inputs { nodes { __typename ... on MoveValue { type { repr } json } } }
              commands { nodes { __typename ... on MoveCallCommand { function { name module { name } } } } }
            }
          }
        }
      }
    }
"""