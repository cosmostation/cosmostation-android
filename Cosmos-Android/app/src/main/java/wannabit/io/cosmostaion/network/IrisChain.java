package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.model.type.IrisProposal;
import wannabit.io.cosmostaion.model.type.Proposal;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
import wannabit.io.cosmostaion.network.res.ResLcdIrisPool;
import wannabit.io.cosmostaion.network.res.ResLcdIrisRedelegate;
import wannabit.io.cosmostaion.network.res.ResLcdIrisReward;
import wannabit.io.cosmostaion.network.res.ResLcdRedelegate;
import wannabit.io.cosmostaion.network.res.ResLcdUnBondings;
import wannabit.io.cosmostaion.network.res.ResTxInfo;

public interface IrisChain {

    @GET("/stake/validators")
    Call<ArrayList<Validator>> getValidatorList(@Query("page") String page, @Query("size") String size);



    @GET("/bank/accounts/{address}")
    Call<ResLcdAccountInfo> getBankInfo(@Path("address") String address);

    @GET("/stake/delegators/{address}/delegations")
    Call<ArrayList<ResLcdBondings>> getBondingList(@Path("address") String address);

    @GET("/stake/delegators/{address}/unbonding-delegations")
    Call<ArrayList<ResLcdUnBondings>> getUnBondingList(@Path("address") String address);

    @GET("/distribution/{address}/rewards")
    Call<ResLcdIrisReward> getRewardsInfo(@Path("address") String address);

    @GET("/distribution/{address}/withdraw-address")
    Call<String> getWithdrawAddress(@Path("address") String address);

    @GET("/txs/{hash}")
    Call<ResTxInfo> getSearchTx(@Path("hash") String hash);


    //Broadcast Tx
    @POST("/tx/broadcast")
    Call<ResBroadTx> broadTx(@Body ReqBroadCast data);



    @GET("/stake/pool")
    Call<ResLcdIrisPool> getIrisPool();


    @GET("/gov/proposals")
    Call<ArrayList<IrisProposal>> getProposalList();

    //Validator details
    @GET("/stake/validators/{validatorAddr}")
    Call<Validator> getValidatorDetail(@Path("validatorAddr") String validatorAddr);

    @GET("/stake/delegators/{address}/delegations/{validatorAddr}")
    Call<ResLcdBondings> getBonding(@Path("address") String address, @Path("validatorAddr") String validatorAddr);

    @GET("/stake/delegators/{address}/unbonding-delegations/{validatorAddr}")
    Call<ResLcdUnBondings> getUnbonding(@Path("address") String address, @Path("validatorAddr") String validatorAddr);

    @GET("/stake/delegators/{delegatorAddr}/redelegations")
    Call<ArrayList<ResLcdIrisRedelegate>> getRedelegateState(@Path("delegatorAddr") String delegatorAddr);
}
