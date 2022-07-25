package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResCdpDepositStatus;
import wannabit.io.cosmostaion.network.res.ResKavaBep3Param;
import wannabit.io.cosmostaion.network.res.ResKavaHardModuleAccount;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveParam;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveReward;
import wannabit.io.cosmostaion.network.res.ResKavaSwapInfo;
import wannabit.io.cosmostaion.network.res.ResKavaSwapSupply;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;

public interface KavaChain {

    @GET("auth/accounts/{address}")
    Call<ResLcdKavaAccountInfo> getAccountInfo(@Path("address") String address);

    @POST("txs")
    Call<ResBroadTx> broadTx(@Body ReqBroadCast data);



    @GET("bep3/parameters")
    Call<ResKavaBep3Param> getSwapParams2();

    @GET("bep3/swap/{swapId}")
    Call<ResKavaSwapInfo> getSwapById(@Path("swapId") String swapId);

    @GET("bep3/supplies")
    Call<ResKavaSwapSupply> getSupplies2();

    @GET("cdp/cdps/cdp/deposits/{address}/{denom}")
    Call<ResCdpDepositStatus> getCdpDepositStatus(@Path("address") String address, @Path("denom") String denom);


    @GET("incentive/parameters")
    Call<ResKavaIncentiveParam> getIncentiveParam5();

    @GET("incentive/rewards")
    Call<ResKavaIncentiveReward> getIncentiveReward5(@Query("owner") String owner);


    @GET("hard/accounts")
    Call<ResKavaHardModuleAccount> getHardModuleAccount();

}
