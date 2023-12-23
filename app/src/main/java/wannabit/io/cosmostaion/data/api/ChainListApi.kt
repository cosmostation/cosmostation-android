package wannabit.io.cosmostaion.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import wannabit.io.cosmostaion.data.model.res.Dao
import wannabit.io.cosmostaion.data.model.res.Vault

interface ChainListApi {

    @GET("{chain}/vaults.json")
    suspend fun vaultData(@Path("chain") chain: String?): MutableList<Vault>

    @GET("{chain}/daos.json")
    suspend fun daoData(@Path("chain") chain: String?): MutableList<Dao>
}