package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;

public interface BinanceChain {

    @GET("/api/v1/account/{address}")
    Call<ResBnbAccountInfo> getAccountInfo(@Path("address") String address);
}
