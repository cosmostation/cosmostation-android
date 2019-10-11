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
import wannabit.io.cosmostaion.network.res.ResBlockInfo;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdBonding;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
import wannabit.io.cosmostaion.network.res.ResLcdInflation;
import wannabit.io.cosmostaion.network.res.ResLcdProposalTally;
import wannabit.io.cosmostaion.network.res.ResLcdProposalVoted;
import wannabit.io.cosmostaion.network.res.ResLcdProposals;
import wannabit.io.cosmostaion.network.res.ResLcdProposer;
import wannabit.io.cosmostaion.network.res.ResLcdRedelegate;
import wannabit.io.cosmostaion.network.res.ResLcdRewardFromVal;
import wannabit.io.cosmostaion.network.res.ResLcdSingleBonding;
import wannabit.io.cosmostaion.network.res.ResLcdSingleUnBonding;
import wannabit.io.cosmostaion.network.res.ResLcdSingleValidator;
import wannabit.io.cosmostaion.network.res.ResLcdUnBonding;
import wannabit.io.cosmostaion.network.res.ResLcdUnBondings;
import wannabit.io.cosmostaion.network.res.ResLcdWithDrawAddress;
import wannabit.io.cosmostaion.network.res.ResProvisions;
import wannabit.io.cosmostaion.network.res.ResStakingPool;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.network.res.ResLcdValidators;

public interface CosmosChain {

    @GET("/node_info")
    Call<JsonObject> getNodeInfo();

    @GET("/auth/accounts/{address}")
    Call<ResLcdAccountInfo> getAccountInfo(@Path("address") String address);

    @GET("/txs/{hash}")
    Call<ResTxInfo> getSearchTx(@Path("hash") String hash);

    @GET("/blocks/{height}")
    Call<ResBlockInfo> getSearchBlock(@Path("height") String height);

    @GET("/staking/validators?status=bonded")
    Call<ResLcdValidators> getValidatorDetailList();

    @GET("/staking/validators?status=unbonding")
    Call<ResLcdValidators> getUnBondingValidatorDetailList();

    @GET("/staking/validators?status=unbonded")
    Call<ResLcdValidators> getUnBondedValidatorDetailList();

    @GET("/staking/delegators/{address}/delegations")
    Call<ResLcdBondings> getBondingList(@Path("address") String address);

    @GET("/staking/delegators/{address}/unbonding_delegations")
    Call<ResLcdUnBondings> getUnBondingList(@Path("address") String address);

    @GET("/distribution/delegators/{address}/rewards")
    Call<ArrayList<Coin>> getTotalRewards(@Path("address") String address);

    @GET("/distribution/delegators/{delegatorAddr}/rewards/{validatorAddr}")
    Call<ResLcdRewardFromVal> getRewardFromValidator(@Path("delegatorAddr") String delegatorAddr, @Path("validatorAddr") String validatorAddr);

    @GET("/distribution/delegators/{address}/withdraw_address")
    Call<ResLcdWithDrawAddress> getWithdrawAddress(@Path("address") String address);

    //Validator details
    @GET("/staking/validators/{validatorAddr}")
    Call<ResLcdSingleValidator> getValidatorDetail(@Path("validatorAddr") String validatorAddr);

    @GET("/staking/delegators/{address}/delegations/{validatorAddr}")
    Call<ResLcdSingleBonding> getBonding(@Path("address") String address, @Path("validatorAddr") String validatorAddr);

    @GET("/staking/delegators/{address}/unbonding_delegations/{validatorAddr}")
    Call<ResLcdSingleUnBonding> getUnbonding(@Path("address") String address, @Path("validatorAddr") String validatorAddr);


    //ReDelegate History
    @GET("/staking/redelegations")
    Call<ArrayList<ResLcdRedelegate>> getRedelegateAllHistory(@Query("delegator") String delegator, @Query("validator_from") String validator_from, @Query("validator_to") String validator_to);

    @GET("/staking/redelegations")
    Call<ArrayList<ResLcdRedelegate>> getRedelegateHistory(@Query("delegator") String delegator, @Query("validator_to") String validator_to);



    @GET("/minting/inflation")
    Call<ResLcdInflation> getInflation();

    @GET("/minting/annual-provisions")
    Call<ResProvisions> getProvisions();

    @GET("/staking/pool")
    Call<ResStakingPool> getStakingPool();

    //Proposals
    @GET("/gov/proposals")
    Call<ResLcdProposals> getProposalList();

    //Broadcast Tx
    @POST("/txs")
    Call<ResBroadTx> broadTx(@Body ReqBroadCast data);


    //TODO rollback cosmos-hub2
    @GET("/staking/validators?status=bonded")
    Call<ArrayList<Validator>> getValidatorDetailListLegacy();

    @GET("/staking/validators?status=unbonding")
    Call<ArrayList<Validator>> getUnBondingValidatorDetailListLegacy();

    @GET("/staking/validators?status=unbonded")
    Call<ArrayList<Validator>> getUnBondedValidatorDetailListLegacy();

    @GET("/staking/delegators/{address}/delegations")
    Call<ArrayList<ResLcdBonding>> getBondingListLegacy(@Path("address") String address);

    @GET("/staking/delegators/{address}/unbonding_delegations")
    Call<ArrayList<ResLcdUnBonding>> getUnBondingListLegacy(@Path("address") String address);

    @GET("/distribution/delegators/{delegatorAddr}/rewards/{validatorAddr}")
    Call<ArrayList<Coin>> getRewardFromValidatorLegacy(@Path("delegatorAddr") String delegatorAddr, @Path("validatorAddr") String validatorAddr);

    @GET("/distribution/delegators/{address}/withdraw_address")
    Call<String> getWithdrawAddressLegacy(@Path("address") String address);

    @GET("/staking/delegators/{address}/delegations/{validatorAddr}")
    Call<ResLcdBonding> getBondingLegacy(@Path("address") String address, @Path("validatorAddr") String validatorAddr);

    @GET("/staking/delegators/{address}/unbonding_delegations/{validatorAddr}")
    Call<ResLcdUnBonding> getUnbondingLegacy(@Path("address") String address, @Path("validatorAddr") String validatorAddr);


    @GET("/staking/validators/{validatorAddr}")
    Call<Validator> getValidatorDetailLegacy(@Path("validatorAddr") String validatorAddr);

    @GET("/minting/inflation")
    Call<String> getInflationLegacy();

    @GET("/minting/annual-provisions")
    Call<String> getProvisionsLegacy();

    @GET("/gov/proposals")
    Call<ArrayList<Proposal>> getProposalListLegacy();


    //TODO adjust for cosmoshub-3
    @GET("/gov/proposals/{proposalId}/proposer")
    Call<ResLcdProposer> getProposer(@Path("proposalId") String proposalId);

    @GET("/gov/proposals/{proposalId}")
    Call<Proposal> getProposalDetail(@Path("proposalId") String proposalId);

    @GET("/gov/proposals/{proposalId}/votes")
    Call<ArrayList<ResLcdProposalVoted>> getVotedList(@Path("proposalId") String proposalId);

    @GET("/gov/proposals/{proposalId}/tally")
    Call<ResLcdProposalTally> getTally(@Path("proposalId") String proposalId);

}
