package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SwapParam {
    @SerializedName("swap_fee")
    public String swap_fee;

    @SerializedName("allowed_pools")
    public ArrayList<AllowedPool> allowed_pools;

}
