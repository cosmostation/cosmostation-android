package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdAllRewards;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
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

public interface KiChain {
    @GET("node_info")
    Call<ResNodeInfo> getNodeInfo();

    @GET("auth/accounts/{address}")
    Call<ResLcdAccountInfo> getAccountInfo(@Path("address") String address);

    @GET("txs/{hash}")
    Call<ResTxInfo> getSearchTx(@Path("hash") String hash);

    @GET("staking/validators?status=bonded")
    Call<ResLcdValidators> getBondedValidatorDetailList();

    @GET("staking/validators?status=unbonding")
    Call<ResLcdValidators> getUnBondingValidatorDetailList();

    @GET("staking/validators?status=unbonded")
    Call<ResLcdValidators> getUnBondedValidatorDetailList();

    @GET("staking/delegators/{address}/delegations")
    Call<ResLcdBondings> getBondingList(@Path("address") String address);

    @GET("staking/delegators/{address}/unbonding_delegations")
    Call<ResLcdUnBondings> getUnBondingList(@Path("address") String address);

    @GET("distribution/delegators/{delegatorAddr}/rewards")
    Call<ResLcdAllRewards> getAllRewards(@Path("delegatorAddr") String delegatorAddr);

    @GET("distribution/delegators/{delegatorAddr}/rewards/{validatorAddr}")
    Call<ResLcdRewardFromVal> getRewardFromValidator(@Path("delegatorAddr") String delegatorAddr, @Path("validatorAddr") String validatorAddr);

    @GET("staking/pool")
    Call<ResStakingPool> getStakingPool();

    @GET("distribution/delegators/{address}/withdraw_address")
    Call<ResLcdWithDrawAddress> getWithdrawAddress(@Path("address") String address);


    @GET("staking/validators/{validatorAddr}")
    Call<ResLcdSingleValidator> getValidatorDetail(@Path("validatorAddr") String validatorAddr);

    @GET("staking/delegators/{address}/delegations/{validatorAddr}")
    Call<ResLcdSingleBonding> getBonding(@Path("address") String address, @Path("validatorAddr") String validatorAddr);

    @GET("staking/delegators/{address}/unbonding_delegations/{validatorAddr}")
    Call<ResLcdSingleUnBonding> getUnbonding(@Path("address") String address, @Path("validatorAddr") String validatorAddr);

    @GET("staking/redelegations")
    Call<ResLcdRedelegate> getRedelegateHistory(@Query("delegator") String delegator, @Query("validator_to") String validator_to);

    @GET("staking/redelegations")
    Call<ResLcdRedelegate> getRedelegateAllHistory(@Query("delegator") String delegator, @Query("validator_from") String validator_from, @Query("validator_to") String validator_to);


    //Broadcast Tx
    @POST("txs")
    Call<ResBroadTx> broadTx(@Body ReqBroadCast data);


    //Proposals
    @GET("gov/proposals")
    Call<ResLcdProposals> getProposalList();

    @GET("gov/proposals/{proposalId}/proposer")
    Call<ResLcdProposer> getProposer(@Path("proposalId") String proposalId);

    @GET("gov/proposals/{proposalId}")
    Call<ResLcdProposal> getProposalDetail(@Path("proposalId") String proposalId);

    @GET("gov/proposals/{proposalId}/votes")
    Call<ResLcdProposalVoted> getVotedList(@Path("proposalId") String proposalId);

    @GET("gov/proposals/{proposalId}/tally")
    Call<ResLcdProposalTally> getTally(@Path("proposalId") String proposalId);

    @GET("gov/proposals/{proposalId}/votes/{address}")
    Call<ResMyVote> getMyVote(@Path("proposalId") String proposalId, @Path("address") String address);
}
