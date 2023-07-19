package wannabit.io.cosmostaion.model;

import com.google.gson.JsonObject;

import org.bitcoinj.core.ECKey;

import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.model.type.Signature;

public class WcSignDirectModel {
    public JsonObject signed;
    public Signature signature;

    public WcSignDirectModel(byte[] signBytes, JsonObject txMsg, ECKey key, String chainId) {
        this.signed = txMsg;
        this.signature = MsgGenerator.getWcSignDiectBroadcaseReq(key, signBytes, chainId);
    }
}