package wannabit.io.cosmostaion.model;

import static wannabit.io.cosmostaion.utils.WUtil.integerToBytes;

import android.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.TreeMap;

import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.model.type.Pub_key;
import wannabit.io.cosmostaion.model.type.Signature;
import wannabit.io.cosmostaion.utils.WKey;

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

    public WcSignModel(JsonObject txMsg, byte[] signature, byte[] pubKey) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        try {
            this.signed = mapper.readValue(txMsg.toString(), TreeMap.class);
            ECKey.ECDSASignature Signature = ECKey.ECDSASignature.decodeFromDER(signature);
            byte[] sigData = new byte[64];
            System.arraycopy(integerToBytes(Signature.r, 32), 0, sigData, 0, 32);
            System.arraycopy(integerToBytes(Signature.s, 32), 0, sigData, 32, 32);
            String signatureTx = Base64.encodeToString(sigData, Base64.DEFAULT).replace("\n", "");
            Signature signature1 = new Signature();
            Pub_key pubKey1 = new Pub_key();
            pubKey1.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
            pubKey1.value = Base64.encodeToString(pubKey, Base64.DEFAULT).replace("\n", "");
            signature1.pub_key = pubKey1;
            signature1.signature = signatureTx;
            this.signature = signature1;
        } catch (Exception e) {
            this.signed = Maps.newTreeMap();
            e.printStackTrace();
        }



    }
}