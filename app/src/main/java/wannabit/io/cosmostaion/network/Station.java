package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.dao.BnbTicker;
import wannabit.io.cosmostaion.dao.ChainParam;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.network.req.ReqBroadAirDrop;
import wannabit.io.cosmostaion.network.req.ReqDesmosAirDrop;
import wannabit.io.cosmostaion.network.res.ResAirdropClaimCheck;
import wannabit.io.cosmostaion.network.res.ResAssets;
import wannabit.io.cosmostaion.network.res.ResCw20Assets;
import wannabit.io.cosmostaion.network.res.ResIbcPaths;
import wannabit.io.cosmostaion.network.res.ResIbcTokens;
import wannabit.io.cosmostaion.network.res.ResMyProposal;
import wannabit.io.cosmostaion.network.res.ResNotice;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.network.res.ResVoteStatus;

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

    @GET("v1/{chain}/proposals/{proposalId}/votes")
    Call<ArrayList<ResVoteStatus>> getVoteStatus(@Path("chain") String chain, @Path("proposalId") int proposalId, @Query("voter") String voter);

    @GET("v1/{chain}/proposals")
    Call<ArrayList<ResProposal>> getProposalList(@Path("chain") String chain);

    @GET("v1/assets/{chain}")
    Call<ResAssets> getAssets(@Path("chain") String chain);

    @GET("/v1/assets/cw20")
    Call<ResCw20Assets> getCw20Assets();

    @GET("v1/boards")
    Call<ResNotice> getNotice(@Query("chain") String chain, @Query("dashboard") boolean dashboard);

    //desmos airdrop
    @POST("airdrop/grants")
    Call<String> broadAirDrop(@Body ReqBroadAirDrop data);

    @GET("users/{address}")
    Call<ResAirdropClaimCheck> getClaimable(@Path("address") String address);

    @POST("airdrop/claims")
    Call<String> ClaimAirDrop(@Body ReqDesmosAirDrop data);

    //certik lcd
    @GET("/shentu/gov/v1alpha1/proposals/{proposal_id}/votes/{voter}")
    Call<ResMyProposal> getCertikProposal(@Path("proposal_id") String proposal_id, @Path("voter") String voter);
}
