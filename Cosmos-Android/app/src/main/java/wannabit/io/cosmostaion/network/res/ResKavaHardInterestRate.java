package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.kava.HardInterestRate;

public class ResKavaHardInterestRate {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<HardInterestRate> result;

}
