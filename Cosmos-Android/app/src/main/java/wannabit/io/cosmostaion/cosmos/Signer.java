package wannabit.io.cosmostaion.cosmos;

import android.util.Base64;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.crypto.DeterministicKey;

import java.security.MessageDigest;
import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.Sha256;
import wannabit.io.cosmostaion.model.StdSignMsg;
import wannabit.io.cosmostaion.model.StdTx;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Pub_key;
import wannabit.io.cosmostaion.model.type.Signature;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.utils.WUtil.integerToBytes;

public class Signer {

    public static ReqBroadCast genSignedDelegateTxV1(String fromAddress, String accountNum, String sequenceNum,
                                                     String toValAddress, Coin amount, Fee fee, String memo,
                                                     DeterministicKey pKey, BaseChain chain) {
        ArrayList<Msg>  msgList = new ArrayList<>();
        Msg             msg     = new Msg();
        Msg.Value       value   = new Msg.Value();

        if (chain.equals(COSMOS_TEST) || chain.equals(IRIS_TEST)) {
            value.delegator_address = fromAddress;
            value.validator_address = toValAddress;
            value.amount = amount;
            msg.type = BaseConstant.COSMOS_MSG_TYPE_DELEGATE;
            msg.value = value;
        }
        msgList.add(msg);

        StdSignMsg              stdToSignMsg    = getToSignMsg(chain.getChain(), accountNum, sequenceNum, msgList, fee, memo);
        String                  signatureTx     = getSingleSignature(pKey, stdToSignMsg.getToSignByte());
        ArrayList<Signature>    signatures      = getSignatures(pKey, signatureTx, accountNum, sequenceNum);
        StdTx                   signedTx        = getSignedTx(msgList, fee, memo, signatures);

//        WLog.w("stdToSignMsg : " +  WUtil.prettyPrinter(stdToSignMsg));
//        WLog.w("signatureTx : " +  signatureTx);
//        WLog.w("signedTx : " +  WUtil.prettyPrinter(signedTx));

        return getBroadReq(signedTx);
    }

    public static ReqBroadCast genSignedUnDelegateTxV1(String fromAddress, String accountNum, String sequenceNum,
                                                     String toValAddress, Coin amount, Fee fee, String memo,
                                                     DeterministicKey pKey, BaseChain chain) {
        ArrayList<Msg>  msgList = new ArrayList<>();
        Msg             msg     = new Msg();
        Msg.Value       value   = new Msg.Value();

        if (chain.equals(COSMOS_TEST) || chain.equals(IRIS_TEST)) {
            value.delegator_address = fromAddress;
            value.validator_address = toValAddress;
            value.amount = amount;
            msg.type = BaseConstant.COSMOS_MSG_TYPE_UNDELEGATE2;
            msg.value = value;
        }
        msgList.add(msg);

        StdSignMsg              stdToSignMsg    = getToSignMsg(chain.getChain(), accountNum, sequenceNum, msgList, fee, memo);
        String                  signatureTx     = getSingleSignature(pKey, stdToSignMsg.getToSignByte());
        ArrayList<Signature>    signatures      = getSignatures(pKey, signatureTx, accountNum, sequenceNum);
        StdTx                   signedTx        = getSignedTx(msgList, fee, memo, signatures);

//        WLog.w("stdToSignMsg : " +  WUtil.prettyPrinter(stdToSignMsg));
//        WLog.w("signatureTx : " +  signatureTx);
//        WLog.w("signedTx : " +  WUtil.prettyPrinter(signedTx));

        return getBroadReq(signedTx);

    }

    public static ReqBroadCast genSignedClaimRewardsTxV1(String fromAddress, String accountNum, String sequenceNum,
                                                         ArrayList<String> valAddresses, Fee fee, String memo,
                                                       DeterministicKey pKey, BaseChain chain) {
        ArrayList<Msg>  msgList = new ArrayList<>();

        if (chain.equals(COSMOS_TEST) || chain.equals(IRIS_TEST)) {
            for (String val: valAddresses) {
                Msg msg = new Msg();
                Msg.Value value = new Msg.Value();

                value.delegator_address = fromAddress;
                value.validator_address = val;

                msg.type = BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_DEL;
                msg.value = value;
                msgList.add(msg);
            }
        }

        StdSignMsg              stdToSignMsg    = getToSignMsg(chain.getChain(), accountNum, sequenceNum, msgList, fee, memo);
        String                  signatureTx     = getSingleSignature(pKey, stdToSignMsg.getToSignByte());
        ArrayList<Signature>    signatures      = getSignatures(pKey, signatureTx, accountNum, sequenceNum);
        StdTx                   signedTx        = getSignedTx(msgList, fee, memo, signatures);

//        WLog.w("stdToSignMsg : " +  WUtil.prettyPrinter(stdToSignMsg));
//        WLog.w("signatureTx : " +  signatureTx);
//        WLog.w("signedTx : " +  WUtil.prettyPrinter(signedTx));

        return getBroadReq(signedTx);

    }





    public static ReqBroadCast getBroadReq(StdTx stdTx) {
        ReqBroadCast reqBroadCast = new ReqBroadCast();
        reqBroadCast.returns = "sync";
        reqBroadCast.tx = stdTx.value;
        return reqBroadCast;
    }


    public static StdTx getSignedTx(ArrayList<Msg> msgs, Fee fee, String memo, ArrayList<Signature> signatures) {
        StdTx result = new StdTx();
        StdTx.Value value = new StdTx.Value();

        value.msg = msgs;
        value.fee = fee;
        value.signatures = signatures;
        value.memo = memo;

        result.type = BaseConstant.COSMOS_AUTH_TYPE_STDTX;
        result.value = value;
        return result;
    }

    public static ArrayList<Signature> getSignatures(DeterministicKey key, String signature, String accountNum, String sequenceNum) {
        ArrayList<Signature> signatures = new ArrayList<>();
        Signature sig = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
        pubKey.value = WKey.getPubKeyValue(key);
        sig.pub_key = pubKey;
        sig.signature = signature;
        sig.account_number = accountNum;
        sig.sequence = sequenceNum;
        signatures.add(sig);
        return signatures;
    }

    public static String getSingleSignature(DeterministicKey key, byte[] toSignByte) {
        MessageDigest digest = Sha256.getSha256Digest();
        byte[] toSignHash = digest.digest(toSignByte);
        ECKey.ECDSASignature Signature = key.sign(Sha256Hash.wrap(toSignHash));
        byte[] sigData = new byte[64];
        System.arraycopy(integerToBytes(Signature.r, 32), 0, sigData, 0, 32);
        System.arraycopy(integerToBytes(Signature.s, 32), 0, sigData, 32, 32);
        String base64 = Base64.encodeToString(sigData, Base64.DEFAULT).replace("\n", "");
        return base64;
    }

    public static StdSignMsg getToSignMsg(String chainId, String accountNumber, String SequenceNumber, ArrayList<Msg> msgs, Fee fee, String memo) {
        StdSignMsg result = new StdSignMsg();
        result.chain_id = chainId;
        result.account_number = accountNumber;
        result.sequence = SequenceNumber;
        result.msgs = msgs;
        result.fee = fee;
        result.memo = memo;

        return result;
    }
}
