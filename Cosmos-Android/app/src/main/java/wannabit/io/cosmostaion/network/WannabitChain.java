package wannabit.io.cosmostaion.network;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Proposal;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.req.ReqStakeBroadCast;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.res.ResBlockInfo;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
import wannabit.io.cosmostaion.network.res.ResLcdRedelegate;
import wannabit.io.cosmostaion.network.res.ResLcdUnBondings;
import wannabit.io.cosmostaion.network.res.ResStakeTxInfo;
import wannabit.io.cosmostaion.network.res.ResStakingPool;
import wannabit.io.cosmostaion.network.res.ResTxInfo;

public interface WannabitChain {

    @GET("/node_info")
    Call<JsonObject> getNodeInfo();

    @GET("/auth/accounts/{address}")
    Call<ResLcdAccountInfo> getAccountInfo(@Path("address") String address);

    @GET("/txs/{hash}")
    Call<ResTxInfo> getSearchTx(@Path("hash") String hash);

    @GET("/txs/{hash}")
    Call<ResStakeTxInfo> getStakeSearchTx(@Path("hash") String hash);

    @GET("/blocks/{height}")
    Call<ResBlockInfo> getSearchBlock(@Path("height") String height);

    @GET("/staking/validators?status=bonded")
    Call<ArrayList<Validator>> getValidatorDetailList();

    @GET("/staking/validators?status=unbonding")
    Call<ArrayList<Validator>> getUnBondingValidatorDetailList();

    @GET("/staking/validators?status=unbonded")
    Call<ArrayList<Validator>> getUnBondedValidatorDetailList();

    @GET("/staking/delegators/{address}/delegations")
    Call<ArrayList<ResLcdBondings>> getBondingList(@Path("address") String address);

    @GET("/staking/delegators/{address}/unbonding_delegations")
    Call<ArrayList<ResLcdUnBondings>> getUnBondingList(@Path("address") String address);

    @GET("/distribution/delegators/{address}/rewards")
    Call<ArrayList<Coin>> getTotalRewards(@Path("address") String address);

    @GET("/distribution/delegators/{delegatorAddr}/rewards/{validatorAddr}")
    Call<ArrayList<Coin>> getRewardFromValidator(@Path("delegatorAddr") String delegatorAddr, @Path("validatorAddr") String validatorAddr);

    @GET("/distribution/delegators/{address}/withdraw_address")
    Call<String> getWithdrawAddress(@Path("address") String address);

    //Validator details
    @GET("/staking/validators/{validatorAddr}")
    Call<Validator> getValidatorDetail(@Path("validatorAddr") String validatorAddr);

    @GET("/staking/delegators/{address}/delegations/{validatorAddr}")
    Call<ResLcdBondings> getBonding(@Path("address") String address, @Path("validatorAddr") String validatorAddr);

    @GET("/staking/delegators/{address}/unbonding_delegations/{validatorAddr}")
    Call<ResLcdUnBondings> getUnbonding(@Path("address") String address, @Path("validatorAddr") String validatorAddr);


    //ReDelegate History
    @GET("/staking/redelegations")
    Call<ArrayList<ResLcdRedelegate>> getRedelegateAllHistory(@Query("delegator") String delegator, @Query("validator_from") String validator_from, @Query("validator_to") String validator_to);

    @GET("/staking/redelegations")
    Call<ArrayList<ResLcdRedelegate>> getRedelegateHistory(@Query("delegator") String delegator, @Query("validator_to") String validator_to);



    @GET("/minting/inflation")
    Call<String> getInflation();

    @GET("/minting/annual-provisions")
    Call<String> getProvisions();

    @GET("/staking/pool")
    Call<ResStakingPool> getStakingPool();

    //Proposals
    @GET("/gov/proposals")
    Call<ArrayList<Proposal>> getProposalList();


    //Broadcast Tx
    @POST("/txs")
    Call<ResBroadTx> broadTx(@Body ReqBroadCast data);

    @POST("/txs")
    Call<ResBroadTx> broadStakeTx(@Body ReqStakeBroadCast data);
}
