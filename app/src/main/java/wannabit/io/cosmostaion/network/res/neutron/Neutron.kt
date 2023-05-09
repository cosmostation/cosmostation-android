package wannabit.io.cosmostaion.network.res.neutron

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Neutron {

    @GET("/cosmostation/chainlist/master/chain/{chain}/vaults.json")
    fun getVaultData(@Path("chain") chain: String?): Call<List<ResVaultData>>

    @GET("/cosmostation/chainlist/master/chain/{chain}/daos.json")
    fun getDaoData(@Path("chain") chain: String?): Call<List<ResDaoData>>
}