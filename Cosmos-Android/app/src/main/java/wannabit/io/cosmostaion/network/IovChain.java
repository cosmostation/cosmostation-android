package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import wannabit.io.cosmostaion.network.res.ResIovBalance;

public interface IovChain {

    @GET("/account/address/balance/{address}")
    Call<ResIovBalance> getBalance(@Path("address") String address);
}
