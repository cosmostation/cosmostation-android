package wannabit.io.cosmostaion.network;

import com.squareup.moshi.Json;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResIovBalance;
import wannabit.io.cosmostaion.network.res.ResIovNonce;
import wannabit.io.cosmostaion.network.res.ResIovOriginAddress;
import wannabit.io.cosmostaion.network.res.ResIovSubmitTx;

public interface IovChain {

//    @GET("/account/address/balance/{address}")
//    Call<ResIovBalance> getBalance(@Path("address") String address);
//
//    @GET("/account/address/{address}")
//    Call<ResIovAddressInfo> getAddressInfo(@Path("address") String address);
//
//    @GET("/tokens")
//    Call<ResIovToken> getTokens();

        @GET("/cash/balances")
        Call<ResIovBalance> getBalance(@Query("address") String address);


        @GET("/nonce/address/{address}")
        Call<ResIovNonce> getNonce(@Path("address") String address);

        @GET("/username/resolve//{starname}")
        Call<ResIovOriginAddress> getOriginAddress(@Path("starname") String starname);

        //Broadcast Tx
        @POST("/tx/submit")
        Call<ResIovSubmitTx> broadTx(@Body RequestBody data);
}
