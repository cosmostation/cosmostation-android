package wannabit.io.cosmostaion.model.type;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("gas_wanted")
    public String gas_wanted;

    @SerializedName("gas_used")
    public String gas_used;
}
