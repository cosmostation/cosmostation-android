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

public class WcSignDirectModel {
    JsonObject signed;
    Signature signature;

    public WcSignDirectModel(byte[] signBytes, JsonObject txMsg, ECKey key) {
        this.signed = txMsg;
        this.signature = MsgGenerator.getWcSignDiectBroadcaseReq(key, signBytes);
    }
}