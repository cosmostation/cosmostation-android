package wannabit.io.cosmostaion.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MarketCapService {
    @GET("v2/ticker/{id}/")
    Call<JsonObject> getAtomTic(@Path("id") int id, @Query("convert") String currency);
}
