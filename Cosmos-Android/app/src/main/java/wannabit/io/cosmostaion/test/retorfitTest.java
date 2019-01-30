package wannabit.io.cosmostaion.test;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface retorfitTest {
    @GET("node_info")
    Call<JsonObject> getVersion();
}
