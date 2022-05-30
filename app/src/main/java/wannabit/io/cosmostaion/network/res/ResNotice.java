package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.dao.Boards;

public class ResNotice {
    @SerializedName("boards")
    public ArrayList<Boards> boards;

    @SerializedName("total_cnt")
    public int total_cnt;

}
