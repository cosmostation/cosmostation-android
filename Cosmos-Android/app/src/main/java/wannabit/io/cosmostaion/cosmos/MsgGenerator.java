package wannabit.io.cosmostaion.cosmos;

import android.content.Context;
import android.util.Base64;

import com.binance.dex.api.client.domain.broadcast.HtltReq;
import com.binance.dex.api.client.domain.broadcast.TransactionOption;
import com.binance.dex.api.client.encoding.message.Token;
import com.github.orogvany.bip32.wallet.HdAddress;
import com.google.protobuf.ByteString;

import net.i2p.crypto.eddsa.EdDSAEngine;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable;
import net.i2p.crypto.eddsa.spec.EdDSAParameterSpec;
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

import wannabit.io.cosmostaion.R;
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
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.BNB_TEST_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.IS_SHOWLOG;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_TEST_DEPUTY;
import static wannabit.io.cosmostaion.utils.WUtil.integerToBytes;

public class MsgGenerator {

    public static Msg genTransferMsg(String fromAddr, String toAddr, ArrayList<Coin> coins, BaseChain chain) {
        Msg         result      = new Msg();
        Msg.Value   value       = new Msg.Value();
        if (chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.KAVA_MAIN) || chain.equals(BaseChain.KAVA_TEST)) {
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

    //Send Tx for IOV chain
    public static String getIovTransferTx(int nonce, String fromAddr, String toAddr, ArrayList<Coin> coins , Fee fee, String memo, HdAddress dKey) {

        long sendInterPart = WUtil.getQuotient(coins.get(0).amount).longValue();
        long sendDecimalPart = WUtil.getRemainder(coins.get(0).amount).movePointRight(9).longValue();

        coin.Codec.Coin.Builder sendCoin = coin.Codec.Coin.newBuilder();
        if (sendInterPart >= 0) {
            sendCoin.setWhole(sendInterPart);
        }
        if (sendDecimalPart >= 0) {
            sendCoin.setFractional(sendDecimalPart);
        }
        sendCoin.setTicker(COSMOS_IOV);

        long feeDecimalPart = WUtil.getRemainder(fee.amount.get(0).amount).movePointRight(9).longValue();
        coin.Codec.Coin.Builder sendFee = coin.Codec.Coin.newBuilder();
        sendFee.setFractional(feeDecimalPart);
        sendFee.setTicker(COSMOS_IOV);

        cash.Codec.FeeInfo.Builder sendFeeInfo = cash.Codec.FeeInfo.newBuilder();
        sendFeeInfo.setPayer(WKey.getIovByteStringfromDpAddress(fromAddr));
        sendFeeInfo.setFees(sendFee.build());

        weave.Codec.Metadata.Builder metaData = weave.Codec.Metadata.newBuilder();
        metaData.setSchema(1);

        cash.Codec.SendMsg.Builder sendMsg = cash.Codec.SendMsg.newBuilder();
        sendMsg.setSource(WKey.getIovByteStringfromDpAddress(fromAddr));
        sendMsg.setDestination(WKey.getIovByteStringfromDpAddress(toAddr));
        sendMsg.setAmount(sendCoin.build());
        sendMsg.setMetadata(metaData.build());
        sendMsg.setMemo(memo);

        WLog.w("sendMsg \n" + sendMsg.toString());

        bnsd.Codec.Tx.Builder sendTx = bnsd.Codec.Tx.newBuilder();
        sendTx.setCashSendMsg(sendMsg);
        sendTx.setFees(sendFeeInfo.build());

        WLog.w("sendTx \n" + sendTx.toString());

        try {
            byte[] inSig = WKey.getIovInSig(sendTx.build(), nonce);
            WLog.w("inSig " + WUtil.ByteArrayToHexString(inSig));

            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] midSig = digest.digest(inSig);
            WLog.w("midSig " + WUtil.ByteArrayToHexString(midSig));

            EdDSAParameterSpec spec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519);
            java.security.Signature sgr = new EdDSAEngine(MessageDigest.getInstance(spec.getHashAlgorithm()));

            EdDSAPrivateKeySpec privKey = new EdDSAPrivateKeySpec(dKey.getPrivateKey().getPrivateKey(), spec);
            PrivateKey sKey = new EdDSAPrivateKey(privKey);
            sgr.initSign(sKey);
            sgr.update(midSig);
            byte[] genSig = sgr.sign();
            WLog.w("genSig " + WUtil.ByteArrayToHexString(genSig));

            crypto.Models.PublicKey.Builder pubKey = crypto.Models.PublicKey.newBuilder();
            pubKey.setEd25519(ByteString.copyFrom(Arrays.copyOfRange(dKey.getPublicKey().getPublicKey(), 1, dKey.getPublicKey().getPublicKey().length)));
            WLog.w("pubkey " + WUtil.ByteArrayToHexString(Arrays.copyOfRange(dKey.getPublicKey().getPublicKey(), 1, dKey.getPublicKey().getPublicKey().length)));

            crypto.Models.Signature.Builder signature = crypto.Models.Signature.newBuilder();
            signature.setEd25519(ByteString.copyFrom(genSig));

            sigs.Codec.StdSignature.Builder stdSignature = sigs.Codec.StdSignature.newBuilder();
            stdSignature.setPubkey(pubKey.build());
            stdSignature.setSignature(signature.build());
            stdSignature.setSequence(nonce);

            sendTx.addSignatures(stdSignature);
            bnsd.Codec.Tx resultTx = sendTx.build();

            WLog.w("result " + "0x" + WUtil.ByteArrayToHexString(resultTx.toByteArray()));

            return "0x" + WUtil.ByteArrayToHexString(resultTx.toByteArray());

        } catch (Exception e) {
            if(IS_SHOWLOG) {
                WLog.w("IOV getTransferTx " +e);
            }
        }
        return "";

    }

    public static Msg genDelegateMsg(String fromAddr, String toValAddr, Coin toDeleagteAmout, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.KAVA_MAIN) || chain.equals(BaseChain.KAVA_TEST)) {
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
        if (chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.KAVA_MAIN) || chain.equals(BaseChain.KAVA_TEST)) {
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
        if (chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.KAVA_MAIN) || chain.equals(BaseChain.KAVA_TEST)) {
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
        if (chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.KAVA_MAIN) || chain.equals(BaseChain.KAVA_TEST)) {
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

        if (chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.KAVA_MAIN) || chain.equals(BaseChain.KAVA_TEST)) {
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
        if (chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.KAVA_MAIN) || chain.equals(BaseChain.KAVA_TEST)) {

        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
            value.proposal_id = proposalId;
            value.voter = accountAddr;
            value.option = opinion;

            result.type = BaseConstant.IRIS_MSG_TYPE_VOTE;
            result.value = value;
        }

        return result;
    }


    public static Msg genCreateCdpMsg(String sender, ArrayList<Coin> collateralCoins, ArrayList<Coin> principalCoins, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(BaseChain.KAVA_MAIN) || chain.equals(BaseChain.KAVA_TEST)) {
            value.sender = sender;
            value.collateral = collateralCoins;
            value.principal = principalCoins;

            result.type = BaseConstant.KAVA_MSG_TYPE_CREATE_CDP;
            result.value = value;

        }
        return result;
    }

    public static Msg genRepayCdpMsg(String sender, ArrayList<Coin> payments, String cdpDenom, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(BaseChain.KAVA_MAIN) || chain.equals(BaseChain.KAVA_TEST)) {
            value.sender = sender;
            value.payment = payments;
            value.cdp_denom = cdpDenom;

            result.type = BaseConstant.KAVA_MSG_TYPE_REPAYDEBT_CDP;
            result.value = value;

        }
        return result;
    }

    public static Msg genDrawDebtCdpMsg(String sender, ArrayList<Coin> principals, String cdpDenom, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(BaseChain.KAVA_MAIN) || chain.equals(BaseChain.KAVA_TEST)) {
            value.sender = sender;
            value.principal = principals;
            value.cdp_denom = cdpDenom;

            result.type = BaseConstant.KAVA_MSG_TYPE_DRAWDEBT_CDP;
            result.value = value;

        }
        return result;
    }

    public static Msg genDepositCdpMsg(String owner, ArrayList<Coin> collaterals, String depositor, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(BaseChain.KAVA_MAIN) || chain.equals(BaseChain.KAVA_TEST)) {
            value.depositor = depositor;
            value.owner = owner;
            value.collateral = collaterals;

            result.type = BaseConstant.KAVA_MSG_TYPE_DEPOSIT_CDP;
            result.value = value;

        }
        return result;
    }

    public static Msg genWithdrawCdpMsg(String owner, ArrayList<Coin> collaterals, String depositor, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(BaseChain.KAVA_MAIN) || chain.equals(BaseChain.KAVA_TEST)) {
            value.depositor = depositor;
            value.owner = owner;
            value.collateral = collaterals;

            result.type = BaseConstant.KAVA_MSG_TYPE_WITHDRAW_CDP;
            result.value = value;

        }
        return result;
    }




    public static HtltReq getBnbHtlcCreateMsg(BaseChain fromChain, BaseChain toChain, Account fromAccount, Account toAccount, ArrayList<Coin> sendCoins, long timestamp, byte[] originData) {
        HtltReq htltReq = new HtltReq();
        Coin toSendCoin = sendCoins.get(0);
        if (fromChain.equals(BaseChain.BNB_MAIN)) {
            if (toChain.equals(BaseChain.KAVA_MAIN)) {

            } else if (toChain.equals(BaseChain.KAVA_TEST)) {
                //NO case

            }

        } else if (fromChain.equals(BaseChain.BNB_TEST)) {
            if (toChain.equals(BaseChain.KAVA_MAIN)) {
                //NO case

            } else if (toChain.equals(BaseChain.KAVA_TEST)) {
                htltReq.setRecipient(BNB_TEST_DEPUTY);
                htltReq.setRecipientOtherChain(toAccount.address);
                htltReq.setSenderOtherChain(KAVA_TEST_DEPUTY);
                htltReq.setTimestamp(timestamp);
                htltReq.setRandomNumberHash(Sha256.getSha256Digest().digest(originData));
                Token token = new Token();
                token.setDenom(toSendCoin.denom);
                BigDecimal sendAmount = new BigDecimal(toSendCoin.amount).movePointRight(8);
                WLog.w("sendAmount " + sendAmount.longValue());
                token.setAmount(sendAmount.longValue());
                htltReq.setOutAmount(Collections.singletonList(token));
                htltReq.setExpectedIncome(sendAmount.toPlainString() + ":" + toSendCoin.denom);
//                htltReq.setHeightSpan(540);
                htltReq.setHeightSpan(10001);
                htltReq.setCrossChain(true);
            }

        }

        return htltReq;
    }

    public static Msg genClaimAtomicSwap(String from, String swapId, String randomNumber, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(BaseChain.KAVA_MAIN) || chain.equals(BaseChain.KAVA_TEST)) {
            value.from = from;
            value.swap_id = swapId.toUpperCase();
            value.random_number = randomNumber.toUpperCase();

            result.type = BaseConstant.KAVA_MSG_TYPE_BEP3_CLAM_SWAP;
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
