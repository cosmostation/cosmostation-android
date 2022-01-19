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
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.network.res.ResKavaSupply;
import wannabit.io.cosmostaion.network.res.ResKavaSwapInfo;
import wannabit.io.cosmostaion.network.res.ResKavaSwapSupply;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdProposer;
import wannabit.io.cosmostaion.network.res.ResLcdWithDrawAddress;
import wannabit.io.cosmostaion.network.res.ResNodeInfo;
import wannabit.io.cosmostaion.network.res.ResTxInfo;

public interface KavaChain {

    @GET("node_info")
    Call<ResNodeInfo> getNodeInfo();

    @GET("txs/{hash}")
    Call<ResTxInfo> getSearchTx(@Path("hash") String hash);

    @GET("auth/accounts/{address}")
    Call<ResLcdKavaAccountInfo> getAccountInfo(@Path("address") String address);

    @GET("distribution/delegators/{address}/withdraw_address")
    Call<ResLcdWithDrawAddress> getWithdrawAddress(@Path("address") String address);

    @GET("gov/proposals/{proposalId}/proposer")
    Call<ResLcdProposer> getProposer(@Path("proposalId") String proposalId);

    @GET("supply/total")
    Call<ResKavaSupply> getSupply();

    @POST("txs")
    Call<ResBroadTx> broadTx(@Body ReqBroadCast data);



    @GET("pricefeed/price/{market}")
    Call<ResKavaMarketPrice> getPrice(@Path("market") String market);


    @GET("bep3/parameters")
    Call<ResKavaBep3Param> getSwapParams2();

    @GET("bep3/swap/{swapId}")
    Call<ResKavaSwapInfo> getSwapById(@Path("swapId") String swapId);

    @GET("bep3/swaps")
    Call<String> getSwaps();

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
