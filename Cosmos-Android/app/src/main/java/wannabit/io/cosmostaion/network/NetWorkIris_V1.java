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
import wannabit.io.cosmostaion.network.res.ResIrisTokenList_V1;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResParamMint_V1;
import wannabit.io.cosmostaion.network.res.ResProposal_V1;
import wannabit.io.cosmostaion.network.res.ResRedelegations_V1;
import wannabit.io.cosmostaion.network.res.ResStakingPool_V1;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.network.res.ResUndelegations_V1;
import wannabit.io.cosmostaion.network.res.ResValidatorInfo_V1;
import wannabit.io.cosmostaion.network.res.ResValidators_V1;
import wannabit.io.cosmostaion.network.res.ResWithdrawAddress_V1;

public interface NetWorkIris_V1 {

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

    @GET("irishub/mint/params")
    Call<ResParamMint_V1> getParamMint();

    @GET("cosmos/staking/v1beta1/pool")
    Call<ResStakingPool_V1> getStakingPool();

    @GET("cosmos/distribution/v1beta1/delegators/{address}/withdraw_address")
    Call<ResWithdrawAddress_V1> getRewardAddress(@Path("address") String address);

    @GET("/cosmos/gov/v1beta1/proposals")
    Call<ResProposal_V1> getProposals();


    @GET("irismod/token/tokens")
    Call<ResIrisTokenList_V1> getTokenList(@Query("pagination.limit") int limit, @Query("pagination.offset") int offset);


    //Broadcast Tx
    @POST("txs")
    Call<ResBroadTx> broadTx(@Body ReqBroadCast data);


}

