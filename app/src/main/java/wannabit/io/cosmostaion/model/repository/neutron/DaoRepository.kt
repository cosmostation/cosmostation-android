package wannabit.io.cosmostaion.model.repository.neutron

import retrofit2.Response
import retrofit2.awaitResponse
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.network.res.neutron.ResDaoData

class DaoRepository {

    suspend fun getDaoData(chainConfig: ChainConfig): Response<List<ResDaoData>> {
        return ApiClient.getChainBase().getDaoData(chainConfig.chainName()).awaitResponse()
    }
}