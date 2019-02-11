package wannabit.io.cosmostaion.network;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
import wannabit.io.cosmostaion.network.res.ResLcdUnBondings;

public interface WannabitChain {

    @GET("/node_info")
    Call<JsonObject> getNodeInfo();

    @GET("/auth/accounts/{address}")
    Call<ResLcdAccountInfo> getAccountInfo(@Path("address") String address);

    @GET("/txs")
    Call<JsonObject> getSearchTx();

    @GET("/staking/validators")
    Call<ArrayList<Validator>> getValidatorDetailList();

    @GET("/staking/delegators/{address}/delegations")
    Call<ArrayList<ResLcdBondings>> getBondingList(@Path("address") String address);

    @GET("/staking/delegators/{address}/unbonding_delegations")
    Call<ArrayList<ResLcdUnBondings>> getUnBondingList(@Path("address") String address);

    @GET("/distribution/delegators/{address}/rewards")
    Call<ArrayList<Coin>> getTotalRewards(@Path("address") String address);

    @GET("/distribution/delegators/{delegatorAddr}/rewards/{validatorAddr}")
    Call<ArrayList<Coin>> getRewardFromValidator(@Path("delegatorAddr") String delegatorAddr, @Path("validatorAddr") String validatorAddr);


}
