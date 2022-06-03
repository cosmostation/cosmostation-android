package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

public class Boards {
    @SerializedName("id")
    public int id;

    @SerializedName("chain")
    public String chain;

    @SerializedName("type")
    public String type;

    @SerializedName("title")
    public String title;

    @SerializedName("created_at")
    public String created_at;

    @SerializedName("updated_at")
    public String updated_at;
}
