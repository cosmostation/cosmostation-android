package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.GET;
import wannabit.io.cosmostaion.network.res.ResVersionCheck;

public interface Cosmostation {

    @GET("/v1/app/version/android")
    Call<ResVersionCheck> getVersion();


}
