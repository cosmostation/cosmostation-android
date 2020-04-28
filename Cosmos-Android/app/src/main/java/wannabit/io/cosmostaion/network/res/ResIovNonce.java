package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResIovNonce {

    @SerializedName("model")
    public NonceModel model;


    public class NonceModel {
        @SerializedName("sequence")
        public int sequence;
    }
}
