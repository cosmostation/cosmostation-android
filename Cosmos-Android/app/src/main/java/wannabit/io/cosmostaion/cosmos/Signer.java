package wannabit.io.cosmostaion.cosmos;

import android.util.Base64;

import com.google.protobuf.Any;
import com.google.protobuf.ByteString;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.crypto.DeterministicKey;

import java.security.MessageDigest;
import java.util.ArrayList;

import cosmos.auth.v1beta1.Auth;
import cosmos.bank.v1beta1.Tx;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.tx.v1beta1.ServiceOuterClass;
import cosmos.tx.v1beta1.TxOuterClass;
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

import static cosmos.tx.signing.v1beta1.Signing.SignMode.SIGN_MODE_DIRECT;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.utils.WUtil.integerToBytes;

public class Signer {

    public static ReqBroadCast genSignedSendTxV1(String fromAddress, String accountNum, String sequenceNum,
                                                     String toAddress, ArrayList<Coin> amounts, Fee fee, String memo,
                                                     DeterministicKey pKey, BaseChain chain) {
        ArrayList<Msg>  msgList = new ArrayList<>();
        Msg             msg     = new Msg();
        Msg.Value       value   = new Msg.Value();

        if (chain.equals(COSMOS_TEST) || chain.equals(IRIS_TEST)) {
            value.from_address = fromAddress;
            value.to_address = toAddress;
            value.amount = amounts;
            msg.type = BaseConstant.COSMOS_MSG_TYPE_TRANSFER2;
            msg.value = value;
        }
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






    //gRpc Singer

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSendReq(Auth.BaseAccount fromAccount, String toAddress, ArrayList<Coin> amounts, Fee fee, String memo, DeterministicKey pKey, BaseChain chain) {
        CoinOuterClass.Coin toSendCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.get(0).amount).setDenom(amounts.get(0).denom).build();
        cosmos.bank.v1beta1.Tx.MsgSend msgSend = cosmos.bank.v1beta1.Tx.MsgSend.newBuilder().addAmount(toSendCoin).setFromAddress(fromAccount.getAddress()).setToAddress(toAddress).build();
        Any msgSendAny = Any.newBuilder().setTypeUrl("/cosmos.bank.v1beta1.MsgSend").setValue(msgSend.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSendAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(fromAccount, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(fromAccount, txBody, authInfo, pKey, chain);

        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSendSimulReq(Auth.BaseAccount fromAccount, String toAddress, ArrayList<Coin> amounts, Fee fee, String memo, DeterministicKey pKey, BaseChain chain) {
        CoinOuterClass.Coin toSendCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.get(0).amount).setDenom(amounts.get(0).denom).build();
        cosmos.bank.v1beta1.Tx.MsgSend msgSend = cosmos.bank.v1beta1.Tx.MsgSend.newBuilder().setFromAddress(fromAccount.getAddress()).setToAddress(toAddress).addAmount(toSendCoin).build();
        Any msgSendAny = Any.newBuilder().setTypeUrl("/cosmos.bank.v1beta1.MsgSend").setValue(msgSend.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSendAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(fromAccount, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(fromAccount, txBody, authInfo, pKey, chain);

        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcDelegateReq(Auth.BaseAccount fromAccount, String toValAddress, Coin amounts, Fee fee, String memo, DeterministicKey pKey, BaseChain chain) {
        CoinOuterClass.Coin toDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgDelegate msgDelegate = cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder().setDelegatorAddress(fromAccount.getAddress()).setValidatorAddress(toValAddress).setAmount(toDelegateCoin).build();
        Any msgDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgDelegate").setValue(msgDelegate.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgDelegateAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(fromAccount, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(fromAccount, txBody, authInfo, pKey, chain);

        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcUnDelegateReq(Auth.BaseAccount fromAccount, String toValAddress, Coin amounts, Fee fee, String memo, DeterministicKey pKey, BaseChain chain) {
        CoinOuterClass.Coin toDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgDelegate msgUnDelegate = cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder().setDelegatorAddress(fromAccount.getAddress()).setValidatorAddress(toValAddress).setAmount(toDelegateCoin).build();
        Any msgUnDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgUndelegate").setValue(msgUnDelegate.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgUnDelegateAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(fromAccount, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(fromAccount, txBody, authInfo, pKey, chain);

        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcClaimRewardsReq(Auth.BaseAccount fromAccount, ArrayList<String> toValAddresses, Fee fee, String memo, DeterministicKey pKey, BaseChain chain) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        for (String valAddr: toValAddresses) {
            cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward msgClaimReward = cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(fromAccount.getAddress()).setValidatorAddress(valAddr).build();
            Any msgClaimRewardAny = Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward").setValue(msgClaimReward.toByteString()).build();
            msgsAny.add(msgClaimRewardAny);
        }
        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(fromAccount, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(fromAccount, txBody, authInfo, pKey, chain);

        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcReDelegateReq(Auth.BaseAccount fromAccount, String fromValAddress, String toValAddress, Coin amounts, Fee fee, String memo, DeterministicKey pKey, BaseChain chain) {
        CoinOuterClass.Coin toReDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgBeginRedelegate msgReDelegate = cosmos.staking.v1beta1.Tx.MsgBeginRedelegate.newBuilder().setDelegatorAddress(fromAccount.getAddress()).setValidatorSrcAddress(fromValAddress).setValidatorDstAddress(toValAddress).setAmount(toReDelegateCoin).build();
        Any msgReDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgBeginRedelegate").setValue(msgReDelegate.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgReDelegateAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(fromAccount, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(fromAccount, txBody, authInfo, pKey, chain);

        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcReInvestReq(Auth.BaseAccount fromAccount, String valAddress, Coin amounts, Fee fee, String memo, DeterministicKey pKey, BaseChain chain) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward msgClaimReward = cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(fromAccount.getAddress()).setValidatorAddress(valAddress).build();
        Any msgClaimRewardAny = Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward").setValue(msgClaimReward.toByteString()).build();
        msgsAny.add(msgClaimRewardAny);

        CoinOuterClass.Coin toReinvsetCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgDelegate msgDelegate = cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder().setDelegatorAddress(fromAccount.getAddress()).setValidatorAddress(valAddress).setAmount(toReinvsetCoin).build();
        Any msgDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgDelegate").setValue(msgDelegate.toByteString()).build();
        msgsAny.add(msgDelegateAny);

        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(fromAccount, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(fromAccount, txBody, authInfo, pKey, chain);

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


    public static TxOuterClass.SignerInfo getGrpcSignerInfo(Auth.BaseAccount fromAccount, DeterministicKey pKey) {
        Any pubKey = WKey.generateGrpcPubKeyFromPriv(pKey.getPrivateKeyAsHex());
        TxOuterClass.ModeInfo.Single singleMode = TxOuterClass.ModeInfo.Single.newBuilder().setMode(SIGN_MODE_DIRECT).build();
        TxOuterClass.ModeInfo modeInfo = TxOuterClass.ModeInfo.newBuilder().setSingle(singleMode).build();
        return  TxOuterClass.SignerInfo.newBuilder().setPublicKey(pubKey).setModeInfo(modeInfo).setSequence(fromAccount.getSequence()).build();
    }

    public static TxOuterClass.AuthInfo getGrpcAuthInfo(TxOuterClass.SignerInfo signerInfo, Fee fee) {
        CoinOuterClass.Coin feeCoin = CoinOuterClass.Coin.newBuilder().setAmount(fee.amount.get(0).amount).setDenom(fee.amount.get(0).denom).build();
        TxOuterClass.Fee txFee = TxOuterClass.Fee.newBuilder().addAmount(feeCoin).setGasLimit(Long.parseLong(fee.gas)).build();
        return TxOuterClass.AuthInfo.newBuilder().setFee(txFee).addSignerInfos(signerInfo).build();
    }

    public static TxOuterClass.TxRaw getGrpcRawTx(Auth.BaseAccount fromAccount, TxOuterClass.TxBody txBody, TxOuterClass.AuthInfo authInfo, DeterministicKey pKey, BaseChain chain) {
        TxOuterClass.SignDoc signDoc = TxOuterClass.SignDoc.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).setChainId(chain.getChain()).setAccountNumber(fromAccount.getAccountNumber()).build();
        byte[] sigbyte = Signer.getGrpcByteSingleSignature(pKey, signDoc.toByteArray());
        return TxOuterClass.TxRaw.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).addSignatures(ByteString.copyFrom(sigbyte)).build();
    }

    public static TxOuterClass.Tx getGrpcSimulTx(Auth.BaseAccount fromAccount, TxOuterClass.TxBody txBody, TxOuterClass.AuthInfo authInfo, DeterministicKey pKey, BaseChain chain) {
        TxOuterClass.SignDoc signDoc = TxOuterClass.SignDoc.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).setChainId(chain.getChain()).setAccountNumber(fromAccount.getAccountNumber()).build();
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
