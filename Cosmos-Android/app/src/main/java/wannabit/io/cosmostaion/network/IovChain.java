package wannabit.io.cosmostaion.network;

import com.squareup.moshi.Json;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBlockInfo;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResIovBalance;
import wannabit.io.cosmostaion.network.res.ResIovNonce;
import wannabit.io.cosmostaion.network.res.ResIovOriginAddress;
import wannabit.io.cosmostaion.network.res.ResIovSubmitTx;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
import wannabit.io.cosmostaion.network.res.ResLcdInflation;
import wannabit.io.cosmostaion.network.res.ResLcdRedelegate;
import wannabit.io.cosmostaion.network.res.ResLcdRewardFromVal;
import wannabit.io.cosmostaion.network.res.ResLcdSingleBonding;
import wannabit.io.cosmostaion.network.res.ResLcdSingleUnBonding;
import wannabit.io.cosmostaion.network.res.ResLcdSingleValidator;
import wannabit.io.cosmostaion.network.res.ResLcdUnBondings;
import wannabit.io.cosmostaion.network.res.ResLcdValidators;
import wannabit.io.cosmostaion.network.res.ResLcdWithDrawAddress;
import wannabit.io.cosmostaion.network.res.ResProvisions;
import wannabit.io.cosmostaion.network.res.ResStakingPool;
import wannabit.io.cosmostaion.network.res.ResTxInfo;

public interface IovChain {

//    @GET("/account/address/balance/{address}")
//    Call<ResIovBalance> getBalance(@Path("address") String address);
//
//    @GET("/account/address/{address}")
//    Call<ResIovAddressInfo> getAddressInfo(@Path("address") String address);
//
//    @GET("/tokens")
//    Call<ResIovToken> getTokens();

        @GET("/cash/balances")
        Call<ResIovBalance> getBalance(@Query("address") String address);


        @GET("/nonce/address/{address}")
        Call<ResIovNonce> getNonce(@Path("address") String address);

        @GET("/username/resolve//{starname}")
        Call<ResIovOriginAddress> getOriginAddress(@Path("starname") String starname);

        //Broadcast Tx
        @POST("/tx/submit")
        Call<ResIovSubmitTx> broadTx(@Body RequestBody data);





        //new version for IOV


        @GET("/auth/accounts/{address}")
        Call<ResLcdAccountInfo> getAccountInfo(@Path("address") String address);

        @GET("/txs/{hash}")
        Call<ResTxInfo> getSearchTx(@Path("hash") String hash);

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

        @GET("/distribution/delegators/{delegatorAddr}/rewards/{validatorAddr}")
        Call<ResLcdRewardFromVal> getRewardFromValidator(@Path("delegatorAddr") String delegatorAddr, @Path("validatorAddr") String validatorAddr);

        @GET("/minting/inflation")
        Call<ResLcdInflation> getInflation();

        @GET("/minting/annual-provisions")
        Call<ResProvisions> getProvisions();

        @GET("/staking/pool")
        Call<ResStakingPool> getStakingPool();

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
        @POST("/txs")
        Call<ResBroadTx> broadTx(@Body ReqBroadCast data);
}
