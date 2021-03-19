package wannabit.io.cosmostaion.cosmos;

import android.util.Base64;

import com.google.protobuf.ByteString;
import com.google.protobuf2.Any;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.crypto.DeterministicKey;

import java.security.MessageDigest;
import java.util.ArrayList;

import cosmos.auth.v1beta1.Auth;
import cosmos.auth.v1beta1.QueryOuterClass;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.gov.v1beta1.Gov;
import cosmos.gov.v1beta1.Tx;
import cosmos.tx.v1beta1.ServiceOuterClass;
import cosmos.tx.v1beta1.TxOuterClass;
import cosmos.vesting.v1beta1.Vesting;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
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

import static cosmos.tx.signing.v1beta1.Signing.SignMode.SIGN_MODE_DIRECT;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_DEL;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_MIDIFY;
import static wannabit.io.cosmostaion.utils.WUtil.integerToBytes;

public class Signer {

    /*
    public static ReqBroadCast genSignedSendTxV1(String fromAddress, String accountNum, String sequenceNum,
                                                     String toAddress, ArrayList<Coin> amounts, Fee fee, String memo,
                                                     DeterministicKey pKey, BaseChain chain) {
        ArrayList<Msg>  msgList = new ArrayList<>();
        Msg             msg     = new Msg();
        Msg.Value       value   = new Msg.Value();

        value.from_address = fromAddress;
        value.to_address = toAddress;
        value.amount = amounts;
        msg.type = BaseConstant.COSMOS_MSG_TYPE_TRANSFER2;
        msg.value = value;

        msgList.add(msg);

        StdSignMsg              stdToSignMsg    = getToSignMsg(chain.getChain(), accountNum, sequenceNum, msgList, fee, memo);
        String                  signatureTx     = getSingleSignature(pKey, stdToSignMsg.getToSignByte());
        ArrayList<Signature>    signatures      = getSignatures(pKey, signatureTx, accountNum, sequenceNum);
        StdTx                   signedTx        = getSignedTx(msgList, fee, memo, signatures);

        return getBroadReq(signedTx);
    }

    public static ReqBroadCast genSignedDelegateTxV1(String fromAddress, String accountNum, String sequenceNum,
                                                     String toValAddress, Coin amount, Fee fee, String memo,
                                                     DeterministicKey pKey, BaseChain chain) {
        ArrayList<Msg>  msgList = new ArrayList<>();
        Msg             msg     = new Msg();
        Msg.Value       value   = new Msg.Value();

        value.delegator_address = fromAddress;
        value.validator_address = toValAddress;
        value.amount = amount;
        msg.type = COSMOS_MSG_TYPE_DELEGATE;
        msg.value = value;

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

        value.delegator_address = fromAddress;
        value.validator_address = toValAddress;
        value.amount = amount;
        msg.type = BaseConstant.COSMOS_MSG_TYPE_UNDELEGATE2;
        msg.value = value;

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
        for (String val: valAddresses) {
            Msg msg = new Msg();
            Msg.Value value = new Msg.Value();

            value.delegator_address = fromAddress;
            value.validator_address = val;

            msg.type = COSMOS_MSG_TYPE_WITHDRAW_DEL;
            msg.value = value;
            msgList.add(msg);
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

    public static ReqBroadCast genSignedReDelegateTxV1(String fromAddress, String accountNum, String sequenceNum,
                                                     String fromValAddress, String toValAddress, Coin amount, Fee fee, String memo,
                                                     DeterministicKey pKey, BaseChain chain) {
        ArrayList<Msg>  msgList = new ArrayList<>();
        Msg             msg     = new Msg();
        Msg.Value       value   = new Msg.Value();

        value.delegator_address = fromAddress;
        value.validator_src_address = fromValAddress;
        value.validator_dst_address = toValAddress;
        value.amount = amount;
        msg.type = BaseConstant.COSMOS_MSG_TYPE_REDELEGATE2;
        msg.value = value;

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

    public static ReqBroadCast genSignedReInvestTxV1(String fromAddress, String accountNum, String sequenceNum,
                                                       String valAddress, Coin amount, Fee fee, String memo,
                                                       DeterministicKey pKey, BaseChain chain) {
        ArrayList<Msg>  msgList = new ArrayList<>();

        Msg withDrawMsg = new Msg();
        Msg.Value withDrawValue = new Msg.Value();
        withDrawValue.delegator_address = fromAddress;
        withDrawValue.validator_address = valAddress;
        withDrawMsg.type = COSMOS_MSG_TYPE_WITHDRAW_DEL;
        withDrawMsg.value = withDrawValue;
        msgList.add(withDrawMsg);

        Msg delegateMsg = new Msg();
        Msg.Value delegateValue = new Msg.Value();
        delegateValue.delegator_address = fromAddress;
        delegateValue.validator_address = valAddress;
        delegateValue.amount = amount;
        delegateMsg.type = COSMOS_MSG_TYPE_DELEGATE;
        delegateMsg.value = delegateValue;
        msgList.add(delegateMsg);

        StdSignMsg              stdToSignMsg    = getToSignMsg(chain.getChain(), accountNum, sequenceNum, msgList, fee, memo);
        String                  signatureTx     = getSingleSignature(pKey, stdToSignMsg.getToSignByte());
        ArrayList<Signature>    signatures      = getSignatures(pKey, signatureTx, accountNum, sequenceNum);
        StdTx                   signedTx        = getSignedTx(msgList, fee, memo, signatures);

//        WLog.w("stdToSignMsg : " +  WUtil.prettyPrinter(stdToSignMsg));
//        WLog.w("signatureTx : " +  signatureTx);
//        WLog.w("signedTx : " +  WUtil.prettyPrinter(signedTx));

        return getBroadReq(signedTx);
    }

    public static ReqBroadCast genSignedSetWithdrawAddressTxV1(String fromAddress, String accountNum, String sequenceNum,
                                                       String setAddress, Fee fee, String memo,
                                                       DeterministicKey pKey, BaseChain chain) {
        ArrayList<Msg>  msgList = new ArrayList<>();
        Msg             msg     = new Msg();
        Msg.Value       value   = new Msg.Value();

        value.delegator_address = fromAddress;
        value.withdraw_address = setAddress;
        msg.type = COSMOS_MSG_TYPE_WITHDRAW_MIDIFY;
        msg.value = value;

        msgList.add(msg);

        StdSignMsg              stdToSignMsg    = getToSignMsg(chain.getChain(), accountNum, sequenceNum, msgList, fee, memo);
        String                  signatureTx     = getSingleSignature(pKey, stdToSignMsg.getToSignByte());
        ArrayList<Signature>    signatures      = getSignatures(pKey, signatureTx, accountNum, sequenceNum);
        StdTx                   signedTx        = getSignedTx(msgList, fee, memo, signatures);

        return getBroadReq(signedTx);
    }

    public static ReqBroadCast genSignedVoteTxV1(String fromAddress, String accountNum, String sequenceNum,
                                                     String proposalId, String opinion, Fee fee, String memo,
                                                     DeterministicKey pKey, BaseChain chain) {
        ArrayList<Msg>  msgList = new ArrayList<>();
        Msg             msg     = new Msg();
        Msg.Value       value   = new Msg.Value();

        value.voter = fromAddress;
        value.proposal_id = proposalId;
        if (opinion.equals("Yes")) {
            value.option = 1;
        } else if (opinion.equals("No")) {
            value.option = 3;
        } else if (opinion.equals("NoWithVeto")) {
            value.option = 4;
        } else if (opinion.equals("Abstain")) {
            value.option = 2;
        }
        msg.type = COSMOS_MSG_TYPE_VOTE;
        msg.value = value;

        msgList.add(msg);

        StdSignMsg              stdToSignMsg    = getToSignMsg(chain.getChain(), accountNum, sequenceNum, msgList, fee, memo);
        String                  signatureTx     = getSingleSignature(pKey, stdToSignMsg.getToSignByte());
        ArrayList<Signature>    signatures      = getSignatures(pKey, signatureTx, accountNum, sequenceNum);
        StdTx                   signedTx        = getSignedTx(msgList, fee, memo, signatures);

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
    */




    public static String onParseAddress(QueryOuterClass.QueryAccountResponse auth) {
        try {
            if (auth.getAccount().getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                Auth.BaseAccount account = Auth.BaseAccount.parseFrom(auth.getAccount().getValue());
                return account.getAddress();

            } else if (auth.getAccount().getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
                Vesting.PeriodicVestingAccount account = Vesting.PeriodicVestingAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseVestingAccount().getBaseAccount().getAddress();
            }

        }catch (Exception e) {}
        return "";
    }

    public static long onParseAccountNumber(QueryOuterClass.QueryAccountResponse auth) {
        try {
            if (auth.getAccount().getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                Auth.BaseAccount account = Auth.BaseAccount.parseFrom(auth.getAccount().getValue());
                return account.getAccountNumber();

            } else if (auth.getAccount().getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
                Vesting.PeriodicVestingAccount account = Vesting.PeriodicVestingAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseVestingAccount().getBaseAccount().getAccountNumber();
            }

        }catch (Exception e) {}
        return 0;
    }

    public static long onParseSequenceNumber(QueryOuterClass.QueryAccountResponse auth) {
        try {
            if (auth.getAccount().getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                Auth.BaseAccount account = Auth.BaseAccount.parseFrom(auth.getAccount().getValue());
                return account.getSequence();

            } else if (auth.getAccount().getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
                Vesting.PeriodicVestingAccount account = Vesting.PeriodicVestingAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseVestingAccount().getBaseAccount().getSequence();
            }

        }catch (Exception e) {}
        return 0;
    }

    //gRpc Singer
    public static ServiceOuterClass.BroadcastTxRequest getGrpcSendReq(QueryOuterClass.QueryAccountResponse auth, String toAddress, ArrayList<Coin> amounts, Fee fee, String memo, DeterministicKey pKey, String chainId) {
        CoinOuterClass.Coin toSendCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.get(0).amount).setDenom(amounts.get(0).denom).build();
        cosmos.bank.v1beta1.Tx.MsgSend msgSend = cosmos.bank.v1beta1.Tx.MsgSend.newBuilder().addAmount(toSendCoin).setFromAddress(onParseAddress(auth)).setToAddress(toAddress).build();
        Any msgSendAny = Any.newBuilder().setTypeUrl("/cosmos.bank.v1beta1.MsgSend").setValue(msgSend.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSendAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);

        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSendSimulReq(QueryOuterClass.QueryAccountResponse auth, String toAddress, ArrayList<Coin> amounts, Fee fee, String memo, DeterministicKey pKey, String chainId) {
        CoinOuterClass.Coin toSendCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.get(0).amount).setDenom(amounts.get(0).denom).build();
        cosmos.bank.v1beta1.Tx.MsgSend msgSend = cosmos.bank.v1beta1.Tx.MsgSend.newBuilder().setFromAddress(onParseAddress(auth)).setToAddress(toAddress).addAmount(toSendCoin).build();
        Any msgSendAny = Any.newBuilder().setTypeUrl("/cosmos.bank.v1beta1.MsgSend").setValue(msgSend.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSendAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);

        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcDelegateReq(QueryOuterClass.QueryAccountResponse auth, String toValAddress, Coin amounts, Fee fee, String memo, DeterministicKey pKey, String chainId) {
        CoinOuterClass.Coin toDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgDelegate msgDelegate = cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(toValAddress).setAmount(toDelegateCoin).build();
        Any msgDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgDelegate").setValue(msgDelegate.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgDelegateAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);

        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcUnDelegateReq(QueryOuterClass.QueryAccountResponse auth, String toValAddress, Coin amounts, Fee fee, String memo, DeterministicKey pKey, String chainId) {
        CoinOuterClass.Coin toDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgUndelegate msgUnDelegate = cosmos.staking.v1beta1.Tx.MsgUndelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(toValAddress).setAmount(toDelegateCoin).build();
        Any msgUnDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgUndelegate").setValue(msgUnDelegate.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgUnDelegateAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);

        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcClaimRewardsReq(QueryOuterClass.QueryAccountResponse auth, ArrayList<String> toValAddresses, Fee fee, String memo, DeterministicKey pKey, String chainId) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        for (String valAddr: toValAddresses) {
            cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward msgClaimReward = cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(valAddr).build();
            Any msgClaimRewardAny = Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward").setValue(msgClaimReward.toByteString()).build();
            msgsAny.add(msgClaimRewardAny);
        }
        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);

        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcReDelegateReq(QueryOuterClass.QueryAccountResponse auth, String fromValAddress, String toValAddress, Coin amounts, Fee fee, String memo, DeterministicKey pKey, String chainId) {
        CoinOuterClass.Coin toReDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgBeginRedelegate msgReDelegate = cosmos.staking.v1beta1.Tx.MsgBeginRedelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorSrcAddress(fromValAddress).setValidatorDstAddress(toValAddress).setAmount(toReDelegateCoin).build();
        Any msgReDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgBeginRedelegate").setValue(msgReDelegate.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgReDelegateAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);

        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcReInvestReq(QueryOuterClass.QueryAccountResponse auth, String valAddress, Coin amounts, Fee fee, String memo, DeterministicKey pKey, String chainId) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward msgClaimReward = cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(valAddress).build();
        Any msgClaimRewardAny = Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward").setValue(msgClaimReward.toByteString()).build();
        msgsAny.add(msgClaimRewardAny);

        CoinOuterClass.Coin toReinvsetCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgDelegate msgDelegate = cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(valAddress).setAmount(toReinvsetCoin).build();
        Any msgDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgDelegate").setValue(msgDelegate.toByteString()).build();
        msgsAny.add(msgDelegateAny);

        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);

        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcRewardAddressChangeReq(QueryOuterClass.QueryAccountResponse auth, String newAddress, Fee fee, String memo, DeterministicKey pKey, String chainId) {
        cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddress msgSetWithdrawAddress = cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddress.newBuilder().setDelegatorAddress(onParseAddress(auth)).setWithdrawAddress(newAddress).build();
        Any msgSetWithdrawAddressAny = Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgSetWithdrawAddress").setValue(msgSetWithdrawAddress.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSetWithdrawAddressAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);

        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcVoteReq(QueryOuterClass.QueryAccountResponse auth, String proposalId, String option, Fee fee, String memo, DeterministicKey pKey, String chainId) {
        Gov.VoteOption msgOption = null;
        if (option.equals("Yes")) {
            msgOption = Gov.VoteOption.VOTE_OPTION_YES;
        } else if (option.equals("No")) {
            msgOption = Gov.VoteOption.VOTE_OPTION_NO;
        } else if (option.equals("NoWithVeto")) {
            msgOption = Gov.VoteOption.VOTE_OPTION_NO_WITH_VETO;
        } else if (option.equals("Abstain")) {
            msgOption = Gov.VoteOption.VOTE_OPTION_ABSTAIN;
        }
        Tx.MsgVote msgVote = Tx.MsgVote.newBuilder().setProposalId(Long.parseLong(proposalId)).setVoter(onParseAddress(auth)).setOption(msgOption).build();
        Any msgSendAny = Any.newBuilder().setTypeUrl("/cosmos.gov.v1beta1.MsgVote").setValue(msgVote.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSendAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);

        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }




    public static TxOuterClass.TxBody getGrpcTxBody(Any msgAny, String memo) {
        return TxOuterClass.TxBody.newBuilder().addMessages(msgAny).setMemo(memo).build();
    }

    public static TxOuterClass.TxBody getGrpcTxBodys(ArrayList<Any> msgsAny, String memo) {
        TxOuterClass.TxBody.Builder builder = TxOuterClass.TxBody.newBuilder();
        for (Any msg: msgsAny) {
            builder.addMessages(msg);
        }
        return builder.setMemo(memo).build();
    }


    public static TxOuterClass.SignerInfo getGrpcSignerInfo(QueryOuterClass.QueryAccountResponse auth, DeterministicKey pKey) {
        Any pubKey = WKey.generateGrpcPubKeyFromPriv(pKey.getPrivateKeyAsHex());
        TxOuterClass.ModeInfo.Single singleMode = TxOuterClass.ModeInfo.Single.newBuilder().setMode(SIGN_MODE_DIRECT).build();
        TxOuterClass.ModeInfo modeInfo = TxOuterClass.ModeInfo.newBuilder().setSingle(singleMode).build();
        return  TxOuterClass.SignerInfo.newBuilder().setPublicKey(pubKey).setModeInfo(modeInfo).setSequence(onParseSequenceNumber(auth)).build();
    }

    public static TxOuterClass.AuthInfo getGrpcAuthInfo(TxOuterClass.SignerInfo signerInfo, Fee fee) {
        CoinOuterClass.Coin feeCoin = CoinOuterClass.Coin.newBuilder().setAmount(fee.amount.get(0).amount).setDenom(fee.amount.get(0).denom).build();
        TxOuterClass.Fee txFee = TxOuterClass.Fee.newBuilder().addAmount(feeCoin).setGasLimit(Long.parseLong(fee.gas)).build();
        return TxOuterClass.AuthInfo.newBuilder().setFee(txFee).addSignerInfos(signerInfo).build();
    }

    public static TxOuterClass.TxRaw getGrpcRawTx(QueryOuterClass.QueryAccountResponse auth, TxOuterClass.TxBody txBody, TxOuterClass.AuthInfo authInfo, DeterministicKey pKey, String chainId) {
        TxOuterClass.SignDoc signDoc = TxOuterClass.SignDoc.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).setChainId(chainId).setAccountNumber(onParseAccountNumber(auth)).build();
        byte[] sigbyte = Signer.getGrpcByteSingleSignature(pKey, signDoc.toByteArray());
        return TxOuterClass.TxRaw.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).addSignatures(ByteString.copyFrom(sigbyte)).build();
    }

    public static TxOuterClass.Tx getGrpcSimulTx(QueryOuterClass.QueryAccountResponse auth, TxOuterClass.TxBody txBody, TxOuterClass.AuthInfo authInfo, DeterministicKey pKey, String chainId) {
        TxOuterClass.SignDoc signDoc = TxOuterClass.SignDoc.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).setChainId(chainId).setAccountNumber(onParseAccountNumber(auth)).build();
        byte[] sigbyte = Signer.getGrpcByteSingleSignature(pKey, signDoc.toByteArray());
        return TxOuterClass.Tx.newBuilder().setAuthInfo(authInfo).setBody(txBody).addSignatures(ByteString.copyFrom(sigbyte)).build();
    }


    public static byte[] getGrpcByteSingleSignature(DeterministicKey key, byte[] toSignByte) {
        MessageDigest digest = Sha256.getSha256Digest();
        byte[] toSignHash = digest.digest(toSignByte);
        ECKey.ECDSASignature Signature = key.sign(Sha256Hash.wrap(toSignHash));
        byte[] sigData = new byte[64];
        System.arraycopy(integerToBytes(Signature.r, 32), 0, sigData, 0, 32);
        System.arraycopy(integerToBytes(Signature.s, 32), 0, sigData, 32, 32);
        return  sigData;
    }
}
