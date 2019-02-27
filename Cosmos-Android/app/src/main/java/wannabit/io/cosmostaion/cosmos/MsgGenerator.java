package wannabit.io.cosmostaion.cosmos;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.crypto.DeterministicKey;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.Sha256;
import wannabit.io.cosmostaion.model.StdSignMsg;
import wannabit.io.cosmostaion.model.StdSignMsgWithType;
import wannabit.io.cosmostaion.model.StdTx;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Delegation;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Input;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Output;
import wannabit.io.cosmostaion.model.type.Signature;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.utils.WUtil.ByteArrayToHexString;
import static wannabit.io.cosmostaion.utils.WUtil.BytearryToDecimalString;
import static wannabit.io.cosmostaion.utils.WUtil.integerToBytes;

public class MsgGenerator {

    public static Msg genTransferMsg(String fromAddr, String toAddr, ArrayList<Coin> coins) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        value.from_address = fromAddr;
        value.to_address = toAddr;
        value.amount = coins;

//        ArrayList<Input> inputs = new ArrayList<>();
//        ArrayList<Output> outputs = new ArrayList<>();
//
//        Input input = new Input(fromAddr, coins);
//        Output output = new Output(toAddr, coins);
//
//        inputs.add(input);
//        outputs.add(output);
//
//        value.inputs = inputs;
//        value.outputs = outputs;



        result.type = BaseConstant.COSMOS_MSG_TYPE_TRANSFER;
        result.value = value;

        return result;
    }

//    public static Msg genDelegateMsg(String fromAddr, String toValAddr, Delegation delegation) {
//        Msg result  = new Msg();
//        Msg.Value value = new Msg.Value();
//
//        value.delegator_addr = fromAddr;
//        value.validator_addr = toValAddr;
//        value.delegation = delegation;
//
//        result.type = BaseConstant.COSMOS_MSG_TYPE_DELEGATE;
//        result.value = value;
//
//        return result;
//    }

    public static Msg genDelegateMsg(String fromAddr, String toValAddr, Coin toDeleagteAmout) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();

        value.delegator_addr = fromAddr;
        value.validator_addr = toValAddr;
        value.value = toDeleagteAmout;

        result.type = BaseConstant.COSMOS_MSG_TYPE_DELEGATE;
        result.value = value;

        return result;
    }

    public static Msg genUnbondMsg(String requestAddr, String fromValAddr, String amount) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();

        value.delegator_addr = requestAddr;
        value.validator_addr = fromValAddr;
        value.shares_amount = amount;

        result.type = BaseConstant.COSMOS_MSG_TYPE_UNDELEGATE;
        result.value = value;

        return result;
    }


    public static Msg genWithdrawDeleMsg(String requestAddr, String fromValAddr) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();

        value.delegator_addr = requestAddr;
        value.validator_addr = fromValAddr;

        result.type = BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_DEL;
        result.value = value;

        return result;
    }




    public static StdTx genUnsignedTransferTx(ArrayList<Msg> msgs, Fee fee, String memo) {
        StdTx result = new StdTx();
        StdTx.Value value = new StdTx.Value();

        value.msg = msgs;
        value.fee = fee;
        value.signatures = null;
        value.memo = memo;

        result.type = BaseConstant.COSMOS_AUTH_TYPE_STDTX;
        result.value = value;
        return result;
    }

    public static StdTx genSignedTransferTx(ArrayList<Msg> msgs, Fee fee, String memo, ArrayList<Signature> signatures) {
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


    public static StdSignMsg genToSignMsg(String chainId, String accountNumber, String SequenceNumber, ArrayList<Msg.Value> msgs, Fee fee, String memo) {
        StdSignMsg result = new StdSignMsg();
        result.chain_id = chainId;
        result.account_number = accountNumber;
        result.sequence = SequenceNumber;
        result.msgs = msgs;
        result.fee = fee;
        result.memo = memo;
        return result;
    }

    public static StdSignMsgWithType genToSignMsgWithType(String chainId, String accountNumber, String SequenceNumber, ArrayList<Msg> msgs, Fee fee, String memo) {
        StdSignMsgWithType result = new StdSignMsgWithType();
        result.chain_id = chainId;
        result.account_number = accountNumber;
        result.sequence = SequenceNumber;
        result.msgs = msgs;
        result.fee = fee;
        result.memo = memo;

        return result;
    }


    public static String getSignature(DeterministicKey key, byte[] toSignByte) {
        MessageDigest digest = Sha256.getSha256Digest();
        byte[] toSignHash = digest.digest(toSignByte);
        ECKey.ECDSASignature Signature = key.sign(new Sha256Hash(toSignHash));
        byte[] sigData = new byte[64];
        System.arraycopy(integerToBytes(Signature.r, 32), 0, sigData, 0, 32);
        System.arraycopy(integerToBytes(Signature.s, 32), 0, sigData, 32, 32);
        String base64 = Base64.encodeToString(sigData, Base64.DEFAULT).replace("\n", "");
        return base64;
    }

}
