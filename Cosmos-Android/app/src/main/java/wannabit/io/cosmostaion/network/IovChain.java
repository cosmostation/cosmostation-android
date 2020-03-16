package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.res.ResIovBalance;

public interface IovChain {

//    @GET("/account/address/balance/{address}")
//    Call<ResIovBalance> getBalance(@Path("address") String address);
//
//    @GET("/account/address/nonce/{address}")
//    Call<ResIovNonce> getNonce(@Path("address") String address);
//
//    @GET("/account/address/{address}")
//    Call<ResIovAddressInfo> getAddressInfo(@Path("address") String address);
//
//    @GET("/tokens")
//    Call<ResIovToken> getTokens();

        @GET("/cash/balances")
        Call<ResIovBalance> getBalance(@Query("address") String address);
}
