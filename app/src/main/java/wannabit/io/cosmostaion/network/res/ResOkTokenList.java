package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.dao.OkToken;

public class ResOkTokenList {
    @SerializedName("code")
    public int code;

    @SerializedName("msg")
    public String msg;

    @SerializedName("detail_msg")
    public String detail_msg;

    @SerializedName("data")
    public ArrayList<OkToken> data;
}
