package wannabit.io.cosmostaion.network;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.dao.BnbTicker;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.network.res.ResBnbFee;
import wannabit.io.cosmostaion.network.res.ResBnbHistories;
import wannabit.io.cosmostaion.network.res.ResBnbSwapInfo;
import wannabit.io.cosmostaion.network.res.ResBnbTxInfo;
import wannabit.io.cosmostaion.network.res.ResNodeInfo;

public interface BinanceChain {

    @GET("api/v1/node-info")
    Call<ResNodeInfo> getNodeInfo();

    @GET("api/v1/account/{address}")
    Call<ResBnbAccountInfo> getAccountInfo(@Path("address") String address);

    @GET("api/v1/transactions")
    Call<ResBnbHistories> getHistory(@Query("address") String address, @Query("startTime") String startTime, @Query("endTime") String endTime);

    @GET("api/v1/transactions")
    Call<ResBnbHistories> getHistoryAsset(@Query("address") String address, @Query("startTime") String startTime, @Query("endTime") String endTime, @Query("txAsset") String txAsset);

    @GET("api/v1/tokens")
    Call<ArrayList<BnbToken>> getTokens(@Query("limit") String limit);

    @GET("api/v1/mini/tokens")
    Call<ArrayList<BnbToken>> getMiniTokens(@Query("limit") String limit);

    @GET("api/v1/ticker/24hr")
    Call<ArrayList<BnbTicker>> getTic();

    @GET("api/v1/mini/ticker/24hr")
    Call<ArrayList<BnbTicker>> getMiniTic();

    @GET("api/v1/tx/{hash}")
    Call<ResBnbTxInfo> getSearchTx(@Path("hash") String hash, @Query("format") String format);

    @GET("api/v1/atomic-swaps/{swapId}")
    Call<ResBnbSwapInfo> getSwapById(@Path("swapId") String swapId);

    @GET("claim/{address}")
    Call<JSONObject> getFaucet(@Path("address") String address);

    @GET("api/v1/fees")
    Call<ArrayList<ResBnbFee>> getFees();
}
