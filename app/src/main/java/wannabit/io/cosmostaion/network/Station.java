package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.dao.Param;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.dao.SupportPool;
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom;
import wannabit.io.cosmostaion.network.res.ResAssets;
import wannabit.io.cosmostaion.network.res.ResMintscanAssets;
import wannabit.io.cosmostaion.network.res.ResMyProposal;
import wannabit.io.cosmostaion.network.res.ResNotice;
import wannabit.io.cosmostaion.network.res.ResOkHistory;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.network.res.ResV1Proposal;
import wannabit.io.cosmostaion.network.res.ResRelayer;
import wannabit.io.cosmostaion.network.res.ResVoteStatus;

public interface Station {

    @GET("v2/utils/market/prices")
    Call<ArrayList<Price>> getPrice(@Query("currency") String currency);

    @GET("v2/utils/params/{chain}")
    Call<Param> getParam(@Path("chain") String chainName);

    @GET("v1/{chain}/proposals")
    Call<ArrayList<ResV1Proposal>> getProposalLists(@Path("chain") String chain);

    @GET("v2/{chain}/proposals/{proposalId}")
    Call<ResProposal> getProposal(@Path("chain") String chain, @Path("proposalId") String proposalId);

    @GET("v1/{chain}/account/{account}/votes")
    Call<ResVoteStatus> getVoteStatus(@Path("chain") String chain, @Path("account") String account);

    @GET("v3/assets")
    Call<ResAssets> getAssets();

    @GET("/v3/assets/{chain}/cw20")
    Call<ResMintscanAssets> getCw20Assets(@Path("chain") String chain);

    @GET("/v3/assets/{chain}/erc20")
    Call<ResMintscanAssets> getErc20Assets(@Path("chain") String chain);

    @GET("/cosmostation/chainlist/master/chain/{chain}/pool.json")
    Call<ArrayList<SupportPool>> getSupportPools(@Path("chain") String chain);

    @GET("v1/boards")
    Call<ResNotice> getNotice(@Query("chain") String chain, @Query("dashboard") boolean dashboard);

    @GET("v2/relayer/{chainId}/{channelId}")
    Call<ResRelayer> getRelayer(@Path("chainId") String chainId, @Path("channelId") String channelId);

    @GET("v1/{chain}/evm/tx/{etherTxHash}")
    Call<Object> getEvmTxHash(@Path("chain") String chain, @Path("etherTxHash") String etherTxHash);

    @GET("v1/{chain}/account/{address}/txs")
    Call<ArrayList<ResApiNewTxListCustom>> getNewAccountTxCustom(@Path("chain") String chain, @Path("address") String address, @Query("limit") String limit, @Query("from") int id);

    //okc history tx
    @GET("v1/utils/proxy/okc-transaction-list")
    Call<ResOkHistory> getNewOkcTxs(@Query("device") String device, @Query("chainShortName") String chainShortName, @Query("address") String address, @Query("limit") String limit);
}
