package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResNodeInfo;
import wannabit.io.cosmostaion.network.res.ResOkAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkAccountToken;
import wannabit.io.cosmostaion.network.res.ResOkStaking;
import wannabit.io.cosmostaion.network.res.ResOkTokenList;
import wannabit.io.cosmostaion.network.res.ResOkUnbonding;
import wannabit.io.cosmostaion.network.res.ResTxInfo;

public interface OkChain {

    @GET("node_info")
    Call<ResNodeInfo> getNodeInfo();

    @GET("auth/accounts/{address}")
    Call<ResOkAccountInfo> getAccountInfo(@Path("address") String address);

    @GET("accounts/{address}")
    Call<ResOkAccountToken> getAccountBalance(@Path("address") String address);

    @GET("tokens")
    Call<ResOkTokenList> getTokenList();

    @GET("staking/validators?status=all")
    Call<ArrayList<Validator>> getAllValidatorDetailList();

    @GET("txs/{hash}")
    Call<ResTxInfo> getSearchTx(@Path("hash") String hash);

    @GET("staking/delegators/{address}")
    Call<ResOkStaking> getDepositInfo(@Path("address") String address);

    @GET("staking/delegators/{address}/unbonding_delegations")
    Call<ResOkUnbonding> getWithdrawInfo(@Path("address") String address);

    //Broadcast Tx
    @POST("txs")
    Call<ResBroadTx> broadTx(@Body ReqBroadCast data);
}
