package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResAllReward_V1;
import wannabit.io.cosmostaion.network.res.ResAuth_V1;
import wannabit.io.cosmostaion.network.res.ResBalance_V1;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResDelegation_V1;
import wannabit.io.cosmostaion.network.res.ResDelegations_V1;
import wannabit.io.cosmostaion.network.res.ResInflation_V1;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
import wannabit.io.cosmostaion.network.res.ResLcdInflation;
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
import wannabit.io.cosmostaion.network.res.ResMintParam;
import wannabit.io.cosmostaion.network.res.ResMyVote;
import wannabit.io.cosmostaion.network.res.ResParamMint_V1;
import wannabit.io.cosmostaion.network.res.ResProposalDetail_V1;
import wannabit.io.cosmostaion.network.res.ResProposalMyVoted_V1;
import wannabit.io.cosmostaion.network.res.ResProposalTally_V1;
import wannabit.io.cosmostaion.network.res.ResProposalVoterList_V1;
import wannabit.io.cosmostaion.network.res.ResProposal_V1;
import wannabit.io.cosmostaion.network.res.ResProvision_V1;
import wannabit.io.cosmostaion.network.res.ResProvisions;
import wannabit.io.cosmostaion.network.res.ResRedelegations_V1;
import wannabit.io.cosmostaion.network.res.ResStakingPool;
import wannabit.io.cosmostaion.network.res.ResStakingPool_V1;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.network.res.ResUndelegations_V1;
import wannabit.io.cosmostaion.network.res.ResValidatorInfo_V1;
import wannabit.io.cosmostaion.network.res.ResValidators_V1;
import wannabit.io.cosmostaion.network.res.ResWithdrawAddress_V1;

public interface NetworkAkash_V1 {

    @GET("txs/{hash}")
    Call<ResTxInfo> getSearchTx(@Path("hash") String hash);

    @GET("cosmos/auth/v1beta1/accounts/{address}")
    Call<ResAuth_V1> getAuth(@Path("address") String address);

    @GET("cosmos/bank/v1beta1/balances/{address}")
    Call<ResBalance_V1> getBalance(@Path("address") String address, @Query("pagination.limit") int limit, @Query("pagination.offset") int offset);

    @GET("cosmos/staking/v1beta1/validators?status=BOND_STATUS_BONDED")
    Call<ResValidators_V1> getBondedValidatorList(@Query("pagination.limit") int limit, @Query("pagination.offset") int offset);

    @GET("cosmos/staking/v1beta1/validators?status=BOND_STATUS_UNBONDING")
    Call<ResValidators_V1> getUnBondingValidatorList(@Query("pagination.limit") int limit, @Query("pagination.offset") int offset);

    @GET("cosmos/staking/v1beta1/validators?status=BOND_STATUS_UNBONDED")
    Call<ResValidators_V1> getUnBondedValidatorList(@Query("pagination.limit") int limit, @Query("pagination.offset") int offset);

    @GET("cosmos/staking/v1beta1/delegations/{address}")
    Call<ResDelegations_V1> getDelegations(@Path("address") String address, @Query("pagination.limit") int limit, @Query("pagination.offset") int offset);

    @GET("cosmos/staking/v1beta1/delegators/{address}/unbonding_delegations")
    Call<ResUndelegations_V1> getUndelegations(@Path("address") String address, @Query("pagination.limit") int limit, @Query("pagination.offset") int offset);

    @GET("cosmos/distribution/v1beta1/delegators/{address}/rewards")
    Call<ResAllReward_V1> getAllRewards(@Path("address") String address, @Query("pagination.limit") int limit, @Query("pagination.offset") int offset);

    @GET("cosmos/staking/v1beta1/validators/{opAddress}")
    Call<ResValidatorInfo_V1> getValidatorInfo(@Path("opAddress") String opAddress);

    @GET("cosmos/staking/v1beta1/validators/{opAddress}/delegations/{address}")
    Call<ResDelegation_V1> getSelfBondInfo(@Path("opAddress") String opAddress, @Path("address") String address);

    @GET("cosmos/staking/v1beta1/delegators/{address}/redelegations")
    Call<ResRedelegations_V1> getRedelegationTo(@Path("address") String address, @Query("dst_validator_addr") String toValAddress);

    @GET("cosmos/staking/v1beta1/delegators/{address}/redelegations")
    Call<ResRedelegations_V1> getRedelegationFromTo(@Path("address") String address, @Query("validator_src_address") String fromValAddress, @Query("dst_validator_addr") String toValAddress);

    @GET("cosmos/staking/v1beta1/pool")
    Call<ResStakingPool_V1> getStakingPool();

    @GET("cosmos/mint/v1beta1/params")
    Call<ResParamMint_V1> getParamMint();

    @GET("cosmos/mint/v1beta1/inflation")
    Call<ResInflation_V1> getInflation();

    @GET("cosmos/mint/v1beta1/annual_provisions")
    Call<ResProvision_V1> getProvision();

    @GET("cosmos/distribution/v1beta1/delegators/{address}/withdraw_address")
    Call<ResWithdrawAddress_V1> getRewardAddress(@Path("address") String address);

    @GET("cosmos/gov/v1beta1/proposals")
    Call<ResProposal_V1> getProposals();

    @GET("cosmos/gov/v1beta1/proposals/{proposal_id}")
    Call<ResProposalDetail_V1> getProposalDetail(@Path("proposal_id") String proposalId);

    @GET("cosmos/gov/v1beta1/proposals/{proposal_id}/tally")
    Call<ResProposalTally_V1> getProposalTally(@Path("proposal_id") String proposalId);

    @GET("cosmos/gov/v1beta1/proposals/{proposal_id}/votes")
    Call<ResProposalVoterList_V1> getProposalAllVoters(@Path("proposal_id") String proposalId, @Query("pagination.limit") int limit, @Query("pagination.offset") int offset);

    @GET("cosmos/gov/v1beta1/proposals/{proposal_id}/votes/{address}")
    Call<ResProposalMyVoted_V1> getProposalMyVoted(@Path("proposal_id") String proposalId, @Path("address") String address);

    //Broadcast Tx
    @POST("txs")
    Call<ResBroadTx> broadTx(@Body ReqBroadCast data);













    @GET("/auth/accounts/{address}")
    Call<ResLcdKavaAccountInfo> getAccountInfo(@Path("address") String address);

//    @GET("/txs/{hash}")
//    Call<ResTxInfo> getSearchTx(@Path("hash") String hash);

    @GET("/staking/validators?status=bonded")
    Call<ResLcdValidators> getBondedValidatorDetailList();

    @GET("/staking/validators?status=unbonding")
    Call<ResLcdValidators> getUnBondingValidatorDetailList();

    @GET("/staking/validators?status=unbonded")
    Call<ResLcdValidators> getUnBondedValidatorDetailList();

    @GET("/staking/delegators/{address}/delegations")
    Call<ResLcdBondings> getBondingList(@Path("address") String address);

    @GET("/staking/delegators/{address}/unbonding_delegations")
    Call<ResLcdUnBondings> getUnBondingList(@Path("address") String address);

    @GET("/distribution/delegators/{delegatorAddr}/rewards/{validatorAddr}")
    Call<ResLcdRewardFromVal> getRewardFromValidator(@Path("delegatorAddr") String delegatorAddr, @Path("validatorAddr") String validatorAddr);

    @GET("/minting/parameters")
    Call<ResMintParam> getMintParam();

//    @GET("/minting/inflation")
//    Call<ResLcdInflation> getInflation();

    @GET("/minting/annual-provisions")
    Call<ResProvisions> getProvisions();

//    @GET("/staking/pool")
//    Call<ResStakingPool> getStakingPool();

    @GET("/distribution/delegators/{address}/withdraw_address")
    Call<ResLcdWithDrawAddress> getWithdrawAddress(@Path("address") String address);


    @GET("/staking/validators/{validatorAddr}")
    Call<ResLcdSingleValidator> getValidatorDetail(@Path("validatorAddr") String validatorAddr);

    @GET("/staking/delegators/{address}/delegations/{validatorAddr}")
    Call<ResLcdSingleBonding> getBonding(@Path("address") String address, @Path("validatorAddr") String validatorAddr);

    @GET("/staking/delegators/{address}/unbonding_delegations/{validatorAddr}")
    Call<ResLcdSingleUnBonding> getUnbonding(@Path("address") String address, @Path("validatorAddr") String validatorAddr);

    @GET("/staking/redelegations")
    Call<ResLcdRedelegate> getRedelegateHistory(@Query("delegator") String delegator, @Query("validator_to") String validator_to);

    @GET("/staking/redelegations")
    Call<ResLcdRedelegate> getRedelegateAllHistory(@Query("delegator") String delegator, @Query("validator_from") String validator_from, @Query("validator_to") String validator_to);


    //Broadcast Tx
//    @POST("/txs")
//    Call<ResBroadTx> broadTx(@Body ReqBroadCast data);

    //Proposals
    @GET("/gov/proposals")
    Call<ResLcdProposals> getProposalList();

    @GET("/gov/proposals/{proposalId}/proposer")
    Call<ResLcdProposer> getProposer(@Path("proposalId") String proposalId);

//    @GET("/gov/proposals/{proposalId}")
//    Call<ResLcdProposal> getProposalDetail(@Path("proposalId") String proposalId);

    @GET("/gov/proposals/{proposalId}/votes")
    Call<ResLcdProposalVoted> getVotedList(@Path("proposalId") String proposalId);

    @GET("/gov/proposals/{proposalId}/tally")
    Call<ResLcdProposalTally> getTally(@Path("proposalId") String proposalId);

    @GET("/gov/proposals/{proposalId}/votes/{address}")
    Call<ResMyVote> getMyVote(@Path("proposalId") String proposalId, @Path("address") String address);
}
