package wannabit.io.cosmostaion.model.type;

import com.google.gson.annotations.SerializedName;

public class Signature {

    @SerializedName("pub_key")
    public Pub_key pub_key;

    @SerializedName("signature")
    public String signature;
}
