package wannabit.io.cosmostaion.network;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResCdpDepositStatus;
import wannabit.io.cosmostaion.network.res.ResKavaAuctionParam;
import wannabit.io.cosmostaion.network.res.ResKavaBep3Param;
import wannabit.io.cosmostaion.network.res.ResKavaCdpParam;
import wannabit.io.cosmostaion.network.res.ResKavaHardInterestRate;
import wannabit.io.cosmostaion.network.res.ResKavaHardModuleAccount;
import wannabit.io.cosmostaion.network.res.ResKavaHardMyBorrow;
import wannabit.io.cosmostaion.network.res.ResKavaHardMyDeposit;
import wannabit.io.cosmostaion.network.res.ResKavaHardParam;
import wannabit.io.cosmostaion.network.res.ResKavaHardReserves;
import wannabit.io.cosmostaion.network.res.ResKavaHardTotalBorrow;
import wannabit.io.cosmostaion.network.res.ResKavaHardTotalDeposit;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveParam;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveReward;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.network.res.ResKavaMyCdps;
import wannabit.io.cosmostaion.network.res.ResKavaPoolInfo;
import wannabit.io.cosmostaion.network.res.ResKavaPriceFeedParam;
import wannabit.io.cosmostaion.network.res.ResKavaSupply;
import wannabit.io.cosmostaion.network.res.ResKavaSwapDeposit;
import wannabit.io.cosmostaion.network.res.ResKavaSwapInfo;
import wannabit.io.cosmostaion.network.res.ResKavaSwapParam;
import wannabit.io.cosmostaion.network.res.ResKavaSwapPool;
import wannabit.io.cosmostaion.network.res.ResKavaSwapSupply;
import wannabit.io.cosmostaion.network.res.ResLcdAllRewards;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdProposal;
import wannabit.io.cosmostaion.network.res.ResLcdProposalTally;
import wannabit.io.cosmostaion.network.res.ResLcdProposalVoted;
import wannabit.io.cosmostaion.network.res.ResLcdProposals;
import wannabit.io.cosmostaion.network.res.ResLcdProposer;
import wannabit.io.cosmostaion.network.res.ResLcdRedelegate;
import wannabit.io.cosmostaion.network.res.ResLcdRewardFromVal;
import wannabit.io.cosmostaion.network.res.ResLcdSingleBonding;
import wannabit.io.cosmostaion.network.res.ResLcdSingleUnBonding;
import wannabit.io.cosmostaion.network.res.ResLcdSingleValidator;
import wannabit.io.cosmostaion.network.res.ResLcdUnBondings;
import wannabit.io.cosmostaion.network.res.ResLcdValidators;
import wannabit.io.cosmostaion.network.res.ResLcdWithDrawAddress;
import wannabit.io.cosmostaion.network.res.ResMyVote;
import wannabit.io.cosmostaion.network.res.ResNodeInfo;
import wannabit.io.cosmostaion.network.res.ResStakingPool;
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




    @GET("pricefeed/parameters")
    Call<ResKavaPriceFeedParam> getPriceParam();

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


    @GET("cdp/parameters")
    Call<ResKavaCdpParam> getCdpParams();

    @GET("cdp/cdps")
    Call<ResKavaMyCdps> getMyCDPs(@Query("owner") String owner);

    @GET("cdp/cdps/cdp/deposits/{address}/{denom}")
    Call<ResCdpDepositStatus> getCdpDepositStatus(@Path("address") String address, @Path("denom") String denom);


    @GET("auction/parameters")
    Call<ResKavaAuctionParam> getAuctionParam();

    @GET("incentive/parameters")
    Call<ResKavaIncentiveParam> getIncentiveParam5();

    @GET("incentive/rewards")
    Call<ResKavaIncentiveReward> getIncentiveReward5(@Query("owner") String owner);


    @GET("hard/parameters")
    Call<ResKavaHardParam> getHardParam();

    @GET("hard/total-deposited")
    Call<ResKavaHardTotalDeposit> getHardTotalDeposit();

    @GET("hard/total-borrowed")
    Call<ResKavaHardTotalBorrow> getHardTotalBorrow();

    @GET("hard/deposits")
    Call<ResKavaHardMyDeposit> getHardMyDeposit(@Query("owner") String owner);

    @GET("hard/borrows")
    Call<ResKavaHardMyBorrow> getHardMyBorrow(@Query("owner") String owner);

    @GET("hard/interest-rate")
    Call<ResKavaHardInterestRate> getHardInterestRate();

    @GET("hard/reserves")
    Call<ResKavaHardReserves> getHardReserves();

    @GET("hard/accounts")
    Call<ResKavaHardModuleAccount> getHardModuleAccount();


    @GET("swap/parameters")
    Call<ResKavaSwapParam> getSwapParam();

    @GET("swap/pools")
    Call<ResKavaSwapPool> getSwapPool();

    @GET("swap/pool")
    Call<ResKavaPoolInfo> getSwapPoolById(@Query("pool") String poolId);

    @GET("swap/deposits")
    Call<ResKavaSwapDeposit> getMySwapDeposit(@Query("owner") String owner);





    @GET("faucet/{address}")
    Call<JSONObject> getFaucet(@Path("address") String address);

}
