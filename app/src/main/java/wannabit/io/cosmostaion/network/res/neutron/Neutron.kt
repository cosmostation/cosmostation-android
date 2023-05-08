package wannabit.io.cosmostaion.network.res.neutron

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Neutron {

    @GET("v1/{chain}/dao/address/{address}/votes")
    fun getDaoMyVoteStatus(@Path("chain") chain: String?, @Path("address") address: String?): Call<List<ResMyVoteStatus>>
}