package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.dao.Param;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.network.res.ResAssets;
import wannabit.io.cosmostaion.network.res.ResCw20Assets;
import wannabit.io.cosmostaion.network.res.ResMyProposal;
import wannabit.io.cosmostaion.network.res.ResNotice;
import wannabit.io.cosmostaion.network.res.ResOkHistory;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.network.res.ResVoteStatus;

public interface Station {

    @GET("v2/utils/market/prices")
    Call<ArrayList<Price>> getPrice(@Query("currency") String currency);

    @GET("v1/utils/params/chain/{chain}")
    Call<Param> getParam(@Path("chain") String chainName);

    @GET("v1/{chain}/proposals/{proposalId}")
    Call<ResProposal> getProposal(@Path("chain") String chain, @Path("proposalId") String proposalId);

    @GET("v1/{chain}/account/{account}/votes")
    Call<ResVoteStatus> getVoteStatus(@Path("chain") String chain, @Path("account") String account);

    @GET("v1/{chain}/proposals")
    Call<ArrayList<ResProposal>> getProposalList(@Path("chain") String chain);

    @GET("v2/assets")
    Call<ResAssets> getAssets();

    @GET("/v2/assets/{chain}/token/cw20")
    Call<ResCw20Assets> getCw20Assets(@Path("chain") String chain);

    @GET("v1/boards")
    Call<ResNotice> getNotice(@Query("chain") String chain, @Query("dashboard") boolean dashboard);

    //certik lcd
    @GET("/shentu/gov/v1alpha1/proposals/{proposal_id}/votes/{voter}")
    Call<ResMyProposal> getCertikProposal(@Path("proposal_id") String proposal_id, @Path("voter") String voter);

    //okc history tx
    @GET("v1/utils/proxy/okc-transaction-list")
    Call<ResOkHistory> getNewOkcTxs(@Query("device") String device, @Query("chainShortName") String chainShortName, @Query("address") String address, @Query("limit") String limit);
}
