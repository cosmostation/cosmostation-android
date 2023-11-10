package wannabit.io.cosmostaion.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import wannabit.io.cosmostaion.data.model.res.Dao
import wannabit.io.cosmostaion.data.model.res.Vault

interface ChainListApi {

    @GET("{chain}/vaults.json")
    fun vaultData(@Path("chain") chain: String?): Call<MutableList<Vault>>

    @GET("{chain}/daos.json")
    fun daoData(@Path("chain") chain: String?): Call<MutableList<Dao>>
}