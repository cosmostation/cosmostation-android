package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import wannabit.io.cosmostaion.dao.ChainParam;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.network.req.ReqBroadAirDrop;
import wannabit.io.cosmostaion.network.res.ResAssets;
import wannabit.io.cosmostaion.network.res.ResBroadAirDrop;
import wannabit.io.cosmostaion.network.res.ResIbcPaths;
import wannabit.io.cosmostaion.network.res.ResIbcTokens;
import wannabit.io.cosmostaion.network.res.ResProposal;

public interface Station {

    @GET("v1/market/prices")
    Call<ArrayList<Price>> getPrice();

    @GET("v1/params/{chain_id}")
    Call<ChainParam> getParam(@Path("chain_id") String chain_id);

    @GET("v1/ibc/paths/{chain_id}")
    Call<ResIbcPaths> getIbcPaths(@Path("chain_id") String chain_id);

    @GET("v1/ibc/tokens/{chain_id}")
    Call<ResIbcTokens> getIbcTokens(@Path("chain_id") String chain_id);

    @GET("v1/{chain}/proposals/{proposalId}")
    Call<ResProposal> getProposal(@Path("chain") String chain, @Path("proposalId") String proposalId);

    @GET("v1/assets/{chain}")
    Call<ResAssets> getAssets(@Path("chain") String chain);

    //desmos airdrop
    @POST("airdrop/grants")
    Call<ResBroadAirDrop> broadAirDrop(@Body ReqBroadAirDrop data);

}
