package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResOkAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkAccountToken;
import wannabit.io.cosmostaion.network.res.ResOkHistory;
import wannabit.io.cosmostaion.network.res.ResOkStaking;
import wannabit.io.cosmostaion.network.res.ResOkTickersList;
import wannabit.io.cosmostaion.network.res.ResOkTokenList;
import wannabit.io.cosmostaion.network.res.ResOkUnbonding;
import wannabit.io.cosmostaion.network.res.ResTxInfo;

public interface OkChain {
    @GET("auth/accounts/{address}")
    Call<ResOkAccountInfo> getAccountInfo(@Path("address") String address);

    @GET("accounts/{address}")
    Call<ResOkAccountToken> getAccountBalance(@Path("address") String address);

    @GET("tokens")
    Call<ResOkTokenList> getTokenList();

    @GET("tokens")
    Call<ResOkTickersList> getDexTickers();

    @GET("staking/validators?status=bonded")
    Call<ArrayList<Validator>> getBondedValidatorDetailList();

    @GET("staking/validators?status=unbonding")
    Call<ArrayList<Validator>> getUnBondingValidatorDetailList();

    @GET("staking/validators?status=unbonded")
    Call<ArrayList<Validator>> getUnBondedValidatorDetailList();

    @GET("txs/{hash}")
    Call<ResTxInfo> getSearchTx(@Path("hash") String hash);

    @GET("staking/delegators/{address}")
    Call<ResOkStaking> getDepositInfo(@Path("address") String address);

    @GET("staking/delegators/{address}/unbonding_delegations")
    Call<ResOkUnbonding> getWithdrawInfo(@Path("address") String address);

    @GET("transactions")
    Call<ResOkHistory> getTxHistory(@Query("address") String address);







    //Broadcast Tx
    @POST("txs")
    Call<ResBroadTx> broadTx(@Body ReqBroadCast data);
}
