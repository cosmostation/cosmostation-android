package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.model.SifIncentive;

public interface SifChain {
    //Liquidity and Validator Rewards
    @GET("api/vs")
    Call<SifIncentive> getVsIncentive(@Query("key") String key, @Query("address") String address, @Query("timestamp") String time);

    @GET("api/lm")
    Call<SifIncentive> getLmIncentive(@Query("key") String key, @Query("address") String address, @Query("timestamp") String time);
}
