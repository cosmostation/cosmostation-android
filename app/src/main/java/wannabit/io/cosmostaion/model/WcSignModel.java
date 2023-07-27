package wannabit.io.cosmostaion.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;

import org.bitcoinj.core.ECKey;

import java.io.IOException;
import java.util.TreeMap;

import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.model.type.WcSignature;

public class WcSignModel {
    public TreeMap<String, Object> signed;
    public WcSignature signature;
    public TreeMap<String, Object> signDoc;

    public WcSignModel(JsonObject txMsg, ECKey key, String chainId) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        try {
            this.signed = mapper.readValue(txMsg.toString(), TreeMap.class);
        } catch (IOException e) {
            this.signed = Maps.newTreeMap();
            e.printStackTrace();
        }
        this.signDoc = signed;
        this.signature = MsgGenerator.getWcKeplrBroadcaseReq(key, txMsg, chainId);
    }
}