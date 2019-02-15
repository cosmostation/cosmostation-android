package wannabit.io.cosmostaion.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.res.ResKeyBaseUser;

public interface KeyBaseService {
    @GET("/_/api/1.0/user/lookup.json")
    Call<ResKeyBaseUser> getUserInfo(@Query("fields") String fields, @Query("key_suffix") String key_suffix);
}
