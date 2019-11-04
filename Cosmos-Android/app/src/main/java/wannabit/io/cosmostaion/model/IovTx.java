package wannabit.io.cosmostaion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.nio.charset.Charset;

import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.res.ResIovBalance;

public class IovTx {

    @SerializedName("kind")
    public String kind;

    @SerializedName("creator")
    public Creator creator;

    @SerializedName("sender")
    public String sender;

    @SerializedName("recipient")
    public String recipient;

    @SerializedName("memo")
    public String memo;

    @SerializedName("amount")
    public ResIovBalance.IovBalance amount;

    @SerializedName("fee")
    public Fee fee;


    public static class Fee {
        @SerializedName("tokens")
        public ResIovBalance.IovBalance tokens;
    }

    public static class Creator {
        @SerializedName("chainId")
        public String chainId;

        @SerializedName("pubkey")
        public PublicKey pubkey;
    }

    public static class PublicKey {
        @SerializedName("algo")
        public String algo;

        @SerializedName("data")
        public String data;
    }


//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public byte[] getToSignByte() {
//        Gson Presenter = new GsonBuilder().disableHtmlEscaping().create();
//        return Presenter.toJson(this).getBytes(Charset.forName("UTF-8"));
//    }

}
