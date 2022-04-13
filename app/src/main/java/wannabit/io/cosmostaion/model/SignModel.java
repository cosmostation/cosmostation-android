package wannabit.io.cosmostaion.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.bitcoinj.core.ECKey;

import java.util.TreeMap;

import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.model.type.Signature;

public class SignModel {
    TreeMap<String, Object> signed;
    Signature signature;

    public SignModel(JsonObject txMsg, ECKey key) {
        this.signed = new Gson().fromJson(txMsg, TreeMap.class);
        this.signature = MsgGenerator.getWcKeplrBroadcaseReq(key, txMsg);
    }

}
