package wannabit.io.cosmostaion.network;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import wannabit.io.cosmostaion.model.RizonSwapStatus;
import wannabit.io.cosmostaion.model.hdac.HdacUtxo;
import wannabit.io.cosmostaion.network.req.ReqHdacBurn;
import wannabit.io.cosmostaion.network.res.ResHdacBurn;
import wannabit.io.cosmostaion.network.res.ResHdacStatus;

public interface HdacChain {
    @GET("status")
    Call<ResHdacStatus> getNodeInfo();

    @GET("addr/{address}/utxo")
    Call<ArrayList<HdacUtxo>> getUtxo(@Path("address") String address);

    @POST("tx/send")
    Call<ResHdacBurn> broadTx(@Body ReqHdacBurn req);

    // rizon swap status
    @GET("/swaps/rizon/{address}")
    Call<ArrayList<RizonSwapStatus>> getSwapStatus(@Path("address") String address);
}
