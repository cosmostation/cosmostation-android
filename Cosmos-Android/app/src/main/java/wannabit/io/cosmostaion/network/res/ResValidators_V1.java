package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.Validator_V1;

public class ResValidators_V1 {
    @SerializedName("validators")
    public ArrayList<Validator_V1> validators;
}
