package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import wannabit.io.cosmostaion.network.res.ResIovBalance;
import wannabit.io.cosmostaion.network.res.ResIovNonce;
import wannabit.io.cosmostaion.network.res.ResIovAddressInfo;
import wannabit.io.cosmostaion.network.res.ResIovToken;

public interface IovChain {

    @GET("/account/address/balance/{address}")
    Call<ResIovBalance> getBalance(@Path("address") String address);

    @GET("/account/address/nonce/{address}")
    Call<ResIovNonce> getNonce(@Path("address") String address);

    @GET("/account/address/{address}")
    Call<ResIovAddressInfo> getAddressInfo(@Path("address") String address);

    @GET("/tokens")
    Call<ResIovToken> getTokens();
}
