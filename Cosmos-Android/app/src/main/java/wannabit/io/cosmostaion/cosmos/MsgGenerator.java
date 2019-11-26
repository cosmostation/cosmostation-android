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
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.IrisStdSignMsg;
import wannabit.io.cosmostaion.model.StdSignMsg;
import wannabit.io.cosmostaion.model.StdTx;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Input;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Output;
import wannabit.io.cosmostaion.model.type.Pub_key;
import wannabit.io.cosmostaion.model.type.Signature;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.utils.WUtil.integerToBytes;

public class MsgGenerator {

    public static Msg genTransferMsg(String fromAddr, String toAddr, ArrayList<Coin> coins, BaseChain chain) {
        Msg         result      = new Msg();
        Msg.Value   value       = new Msg.Value();
        if (chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.KAVA_MAIN)) {
            value.from_address = fromAddr;
            value.to_address = toAddr;
            value.amount = coins;

            result.type = BaseConstant.COSMOS_MSG_TYPE_TRANSFER2;
            result.value = value;

        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
            ArrayList<Input> inputs     = new ArrayList<>();
            ArrayList<Output> outputs   = new ArrayList<>();
            Input input = new Input(fromAddr, coins);
            Output output = new Output(toAddr, coins);
            inputs.add(input);
            outputs.add(output);

            value.inputs = inputs;
            value.outputs = outputs;

            result.type = BaseConstant.IRIS_MSG_TYPE_TRANSFER;
            result.value = value;

        }
        return result;
    }

    public static Msg genDelegateMsg(String fromAddr, String toValAddr, Coin toDeleagteAmout, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.KAVA_MAIN)) {
            value.delegator_address = fromAddr;
            value.validator_address = toValAddr;
            value.amount = toDeleagteAmout;

            result.type = BaseConstant.COSMOS_MSG_TYPE_DELEGATE;
            result.value = value;

        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
            value.delegator_addr = fromAddr;
            value.validator_addr = toValAddr;
            value.delegation = toDeleagteAmout;

            result.type = BaseConstant.IRIS_MSG_TYPE_DELEGATE;
            result.value = value;

        }
        return result;
    }

    public static Msg genUnbondMsg(String requestAddr, String fromValAddr, Coin amount, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.KAVA_MAIN)) {
            value.delegator_address = requestAddr;
            value.validator_address = fromValAddr;
            value.amount = amount;

            result.type = BaseConstant.COSMOS_MSG_TYPE_UNDELEGATE2;
            result.value = value;

        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
            value.delegator_addr = requestAddr;
            value.validator_addr = fromValAddr;
            value.shares_amount = amount.amount + ".0000000000";

            result.type = BaseConstant.IRIS_MSG_TYPE_UNDELEGATE;
            result.value = value;
        }
        return result;
    }


    public static Msg genWithdrawDeleMsg(String requestAddr, String fromValAddr, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.KAVA_MAIN)) {
            value.delegator_address = requestAddr;
            value.validator_address = fromValAddr;

            result.type = BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_DEL;
            result.value = value;

        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
            value.delegator_addr = requestAddr;
            value.validator_addr = fromValAddr;

            result.type = BaseConstant.IRIS_MSG_TYPE_WITHDRAW;
            result.value = value;
        }
        return result;
    }

    public static Msg genWithdrawDeleAllMsg(String requestAddr, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        value.delegator_addr = requestAddr;

        result.type = BaseConstant.IRIS_MSG_TYPE_WITHDRAW_ALL;
        result.value = value;
        return result;
    }

    public static Msg genReDelegateMsg(String accountAddr, String fromValAddr, String toValAddr, Coin amount, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.KAVA_MAIN)) {
            value.delegator_address = accountAddr;
            value.validator_src_address = fromValAddr;
            value.validator_dst_address = toValAddr;
            value.amount = amount;

            result.type = BaseConstant.COSMOS_MSG_TYPE_REDELEGATE2;
            result.value = value;

        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
            value.delegator_addr = accountAddr;
            value.validator_src_addr = fromValAddr;
            value.validator_dst_addr = toValAddr;
            //TODO need cal bal to shares.
//            value.shares = amount.amount + ".0000000000";
            value.shares_amount = amount.amount + ".0000000000";

            result.type = BaseConstant.IRIS_MSG_TYPE_REDELEGATE;
            result.value = value;
        }

        return result;
    }

    public static Msg genRewardAddressChange(String requestAddr, String newRewardAddr, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();

        if (chain.equals(BaseChain.COSMOS_MAIN)) {
            value.delegator_address = requestAddr;
            value.withdraw_address = newRewardAddr;

            result.type = BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_MIDIFY;
            result.value = value;

        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
            value.delegator_addr = requestAddr;
            value.withdraw_addr = newRewardAddr;

            result.type = BaseConstant.IRIS_MSG_TYPE_WITHDRAW_MIDIFY;
            result.value = value;
        }
        return result;
    }

    public static Msg genVoteMsg(String accountAddr, String proposalId, String opinion, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.KAVA_MAIN)) {

        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
            value.proposal_id = proposalId;
            value.voter = accountAddr;
            value.option = opinion;

            result.type = BaseConstant.IRIS_MSG_TYPE_VOTE;
            result.value = value;
        }

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

    public static StdTx genStakeSignedTransferTx(ArrayList<Msg> msgs, Fee fee, String memo, ArrayList<Signature> signatures) {
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

    public static StdSignMsg genToSignMsg(String chainId, String accountNumber, String SequenceNumber, ArrayList<Msg> msgs, Fee fee, String memo) {
        StdSignMsg result = new StdSignMsg();
        result.chain_id = chainId;
        result.account_number = accountNumber;
        result.sequence = SequenceNumber;
        result.msgs = msgs;
        result.fee = fee;
        result.memo = memo;

        return result;
    }

    public static IrisStdSignMsg genIrisToSignMsg(String chainId, String accountNumber, String SequenceNumber, ArrayList<Msg> msgs, Fee fee, String memo) {
        IrisStdSignMsg result = new IrisStdSignMsg();
        result.chain_id = chainId;
        result.account_number = accountNumber;
        result.sequence = SequenceNumber;
        ArrayList<Msg.Value> tempMsgs = new ArrayList<>();
        for (Msg msg:msgs) {
            tempMsgs.add(msg.value);
        }
        result.msgs = tempMsgs;
        result.fee = fee;
        result.memo = memo;

        return result;
    }

    public static String getSignature(DeterministicKey key, byte[] toSignByte) {
        MessageDigest digest = Sha256.getSha256Digest();
        byte[] toSignHash = digest.digest(toSignByte);
//        ECKey.ECDSASignature Signature = key.sign(new Sha256Hash(toSignHash));
        ECKey.ECDSASignature Signature = key.sign(Sha256Hash.wrap(toSignHash));
        byte[] sigData = new byte[64];
        System.arraycopy(integerToBytes(Signature.r, 32), 0, sigData, 0, 32);
        System.arraycopy(integerToBytes(Signature.s, 32), 0, sigData, 32, 32);
        String base64 = Base64.encodeToString(sigData, Base64.DEFAULT).replace("\n", "");
        return base64;
    }

    public static ReqBroadCast getBraodcaseReq(Account account, ArrayList<Msg> msgs, Fee fee, String memo, DeterministicKey key) {
        StdSignMsg tosign = genToSignMsg(
                BaseChain.getDpChain(account.baseChain),
                ""+account.accountNumber,
                ""+account.sequenceNumber,
                msgs,
                fee,
                memo);

        String signatureTx = MsgGenerator.getSignature(key, tosign.getToSignByte());
//        WLog.w("signatureTx " + signatureTx);

        Signature signature = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
        pubKey.value = WKey.getPubKeyValue(key);
        signature.pub_key = pubKey;
        signature.signature = signatureTx;
        signature.account_number = ""+account.accountNumber;
        signature.sequence = ""+account.sequenceNumber;

        ArrayList<Signature> signatures = new ArrayList<>();
        signatures.add(signature);

        StdTx signedTx = MsgGenerator.genStakeSignedTransferTx(msgs, fee, memo, signatures);
//        WLog.w("signedTx : " +  WUtil.prettyPrinter(signedTx));

        ReqBroadCast reqBroadCast = new ReqBroadCast();
        reqBroadCast.returns = "sync";
        reqBroadCast.tx = signedTx.value;

//        WLog.w("ReqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));


        return reqBroadCast;
    }


    public static ReqBroadCast getIrisBraodcaseReq(Account account, ArrayList<Msg> msgs, Fee fee, String memo, DeterministicKey key) {
        IrisStdSignMsg tosign = genIrisToSignMsg(
                BaseChain.getDpChain(account.baseChain),
                ""+account.accountNumber,
                ""+account.sequenceNumber,
                msgs,
                fee,
                memo);
        String signatureTx = MsgGenerator.getSignature(key, tosign.getToSignByte());
//        WLog.w("Iris Send signatureTx " + signatureTx);

        Signature signature = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
        pubKey.value = WKey.getPubKeyValue(key);
        signature.pub_key = pubKey;
        signature.signature = signatureTx;
        signature.account_number = ""+account.accountNumber;
        signature.sequence = ""+account.sequenceNumber;

        ArrayList<Signature> signatures = new ArrayList<>();
        signatures.add(signature);

        StdTx signedTx = MsgGenerator.genStakeSignedTransferTx(msgs, fee, memo, signatures);
//        WLog.w("Iris Send signedTx : " +  WUtil.prettyPrinter(signedTx));

        ReqBroadCast reqBroadCast = new ReqBroadCast();
        reqBroadCast.returns = "sync";
        reqBroadCast.tx = signedTx.value;

        WLog.w("Iris Send ReqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));
        return reqBroadCast;
    }


    public static ReqBroadCast getIrisBraodcaseReq2(Account account, ArrayList<Msg> msgs, Fee fee, String memo, DeterministicKey key) {
        Msg tempMsg  = new Msg();
        Msg.Value tempValue = new Msg.Value();
        tempValue.delegator_addr = msgs.get(0).value.delegator_addr;
        tempValue.validator_src_addr = msgs.get(0).value.validator_src_addr;
        tempValue.validator_dst_addr = msgs.get(0).value.validator_dst_addr;
        tempValue.shares = msgs.get(0).value.shares_amount;
        tempMsg.type = BaseConstant.IRIS_MSG_TYPE_REDELEGATE;
        tempMsg.value = tempValue;

        ArrayList<Msg> tempMsgs= new ArrayList<>();
        tempMsgs.add(tempMsg);

        IrisStdSignMsg tosign = genIrisToSignMsg(
                BaseChain.getDpChain(account.baseChain),
                ""+account.accountNumber,
                ""+account.sequenceNumber,
                tempMsgs,
                fee,
                memo);
        String signatureTx = MsgGenerator.getSignature(key, tosign.getToSignByte());

        Signature signature = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
        pubKey.value = WKey.getPubKeyValue(key);
        signature.pub_key = pubKey;
        signature.signature = signatureTx;
        signature.account_number = ""+account.accountNumber;
        signature.sequence = ""+account.sequenceNumber;

        ArrayList<Signature> signatures = new ArrayList<>();
        signatures.add(signature);

        StdTx signedTx = MsgGenerator.genStakeSignedTransferTx(msgs, fee, memo, signatures);

        ReqBroadCast reqBroadCast = new ReqBroadCast();
        reqBroadCast.returns = "sync";
        reqBroadCast.tx = signedTx.value;

        WLog.w("Iris Send ReqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));
        return reqBroadCast;
    }
}
