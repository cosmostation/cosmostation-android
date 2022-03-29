package wannabit.io.cosmostaion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.nio.charset.Charset;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;

public class StdSignMsg {

    @SerializedName(value = "chain_id", alternate = {"chainId"})
    public String chain_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @SerializedName(value = "account_number", alternate = {"accountNumber"})
    public String account_number;

    @SerializedName("sequence")
    public String sequence;

    @SerializedName("fee")
    public Fee fee;

    @SerializedName(value = "msgs")
    public ArrayList<Msg> msgs;

    @SerializedName("memo")
    public String memo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public byte[] getToSignByte() {
        Gson Presenter = new GsonBuilder().disableHtmlEscaping().create();
        return Presenter.toJson(this).getBytes(Charset.forName("UTF-8"));
    }
}
