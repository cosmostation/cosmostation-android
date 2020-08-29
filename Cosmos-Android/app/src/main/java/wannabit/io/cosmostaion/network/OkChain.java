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
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
import wannabit.io.cosmostaion.network.res.ResLcdUnBondings;
import wannabit.io.cosmostaion.network.res.ResOkAccountToken;
import wannabit.io.cosmostaion.network.res.ResOkDeposit;
import wannabit.io.cosmostaion.network.res.ResOkTokenList;
import wannabit.io.cosmostaion.network.res.ResOkWithdraw;
import wannabit.io.cosmostaion.network.res.ResTxInfo;

public interface OkChain {
    @GET("/auth/accounts/{address}")
    Call<ResLcdAccountInfo> getAccountInfo(@Path("address") String address);

    @GET("/accounts/{address}")
    Call<ResOkAccountToken> getAccountToken(@Path("address") String address);

    @GET("/tokens")
    Call<ResOkTokenList> getTokenList();

    @GET("/txs/{hash}")
    Call<ResTxInfo> getSearchTx(@Path("hash") String hash);

    @GET("/staking/validators?status=all")
    Call<ArrayList<Validator>> getValidatorDetailList();

    @GET("/staking/delegators/{address}")
    Call<ResOkDeposit> getDepositInfo(@Path("address") String address);

    @GET("/staking/delegators/{address}/unbonding_delegations")
    Call<ResOkWithdraw> getWithdrawInfo(@Path("address") String address);










    //Broadcast Tx
    @POST("/txs")
    Call<ResBroadTx> broadTx(@Body ReqBroadCast data);
}
