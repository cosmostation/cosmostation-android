package wannabit.io.cosmostaion.network;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import wannabit.io.cosmostaion.model.hdac.HdacUtxo;
import wannabit.io.cosmostaion.network.res.ResHdacStatus;

public interface HdacChain {
    @GET("status")
    Call<ResHdacStatus> getNodeInfo();

    @GET("addr/{address}/utxo")
    Call<ArrayList<HdacUtxo>> getUtxo(@Path("address") String address);
}
