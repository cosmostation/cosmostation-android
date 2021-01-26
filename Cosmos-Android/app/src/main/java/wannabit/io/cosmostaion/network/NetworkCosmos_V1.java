package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.res.ResAllReward_V1;
import wannabit.io.cosmostaion.network.res.ResBalance_V1;
import wannabit.io.cosmostaion.network.res.ResDelegation_V1;
import wannabit.io.cosmostaion.network.res.ResDelegations_V1;
import wannabit.io.cosmostaion.network.res.ResInflation_V1;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResParamMint_V1;
import wannabit.io.cosmostaion.network.res.ResProvision_V1;
import wannabit.io.cosmostaion.network.res.ResStakingPool_V1;
import wannabit.io.cosmostaion.network.res.ResUndelegations_V1;
import wannabit.io.cosmostaion.network.res.ResValidatorInfo_V1;
import wannabit.io.cosmostaion.network.res.ResValidators_V1;

public interface NetworkCosmos_V1 {

    @GET("cosmos/auth/v1beta1/accounts/{address}")
    Call<ResLcdAccountInfo> getAuth(@Path("address") String address);

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

    @GET("cosmos/staking/v1beta1/pool")
    Call<ResStakingPool_V1> getStakingPool();

    @GET("cosmos/mint/v1beta1/params")
    Call<ResParamMint_V1> getParamMint();

    @GET("cosmos/mint/v1beta1/inflation")
    Call<ResInflation_V1> getInflation();

    @GET("cosmos/mint/v1beta1/annual_provisions")
    Call<ResProvision_V1> getProvision();


}
