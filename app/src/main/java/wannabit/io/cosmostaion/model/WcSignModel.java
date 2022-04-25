package wannabit.io.cosmostaion.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;

import org.bitcoinj.core.ECKey;

import java.io.IOException;
import java.util.TreeMap;

import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.model.type.Signature;

public class WcSignModel {
    TreeMap<String, Object> signed;
    Signature signature;

    public WcSignModel(JsonObject txMsg, ECKey key) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        try {
            this.signed = mapper.readValue(txMsg.toString(), TreeMap.class);
        } catch (IOException e) {
            this.signed = Maps.newTreeMap();
            e.printStackTrace();
        }
        this.signature = MsgGenerator.getWcKeplrBroadcaseReq(key, txMsg);
    }
}