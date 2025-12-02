package wannabit.io.cosmostaion.chain.fetcher

import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.database.Prefs
import xyz.mcxross.kaptos.Aptos
import xyz.mcxross.kaptos.generated.getaccountcoinsdata.current_fungible_asset_balances
import xyz.mcxross.kaptos.model.AccountAddress
import xyz.mcxross.kaptos.model.AptosConfig
import xyz.mcxross.kaptos.model.AptosSettings
import java.math.BigDecimal
import java.math.RoundingMode

class AptosFetcher(private val chain: BaseChain) {

    var aptosAssetBalance: MutableList<current_fungible_asset_balances>? = mutableListOf()

    fun aptosClient(): Aptos {
        val config = AptosConfig(
            AptosSettings(
                fullNode = chain.aptosFetcher?.getApi(),
                indexer = chain.aptosFetcher?.getGraphQL()
            )
        )
        return Aptos(config)
    }

    fun aptosAccount(): AccountAddress {
        return AccountAddress.fromString(chain.mainAddress)
    }

    fun allAssetValue(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        if (aptosAssetBalance?.isNotEmpty() == true) {
            aptosAssetBalance?.forEach { asset ->
                BaseData.getAsset(chain.apiName, asset.asset_type)?.let {
                    sum += aptosBalanceValue(asset.asset_type, isUsd)
                }
            }
        }
        return sum
    }

    fun aptosBalanceAmount(assetType: String): BigDecimal {
        if (aptosAssetBalance?.isNotEmpty() == true) {
            return aptosAssetBalance?.firstOrNull { it.asset_type == assetType }?.amount?.toBigDecimal()
                ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun aptosBalanceValue(assetType: String, isUsd: Boolean? = false): BigDecimal {
        if (aptosBalanceAmount(assetType) <= BigDecimal.ZERO) return BigDecimal.ZERO
        BaseData.getAsset(chain.apiName, assetType)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            return price.multiply(aptosBalanceAmount(assetType)).movePointLeft(asset.decimals ?: 8)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    fun getApi(): String {
        val endpoint = Prefs.getLcdEndpoint(chain)
        return endpoint.ifEmpty {
            chain.apiUrl
        }
    }

    fun getGraphQL(): String {
        val endpoint = Prefs.getEvmRpcEndpoint(chain)
        return if (endpoint?.isNotEmpty() == true) {
            endpoint
        } else {
            chain.mainUrl
        }
    }
}
