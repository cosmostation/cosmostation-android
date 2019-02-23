package wannabit.io.cosmostaion.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import wannabit.io.cosmostaion.network.res.ResBroadTx;

public interface Cosmostation {

    @GET("/tx/broadcast/{gentx}")
    Call<ResBroadTx> broadcastTx(@Path("gentx") String gentx);
}
