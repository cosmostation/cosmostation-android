package wannabit.io.cosmostaion.cosmos;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BTCB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BUSD_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_XRPB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BTCB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BUSD_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_XRPB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_XRPB;
import static wannabit.io.cosmostaion.utils.WUtil.integerToBytes;

import android.util.Base64;

import com.binance.dex.api.client.domain.broadcast.HtltReq;
import com.binance.dex.api.client.encoding.message.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
import org.json.JSONObject;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import ibc.core.client.v1.Client;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.crypto.Sha256;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.AssetPath;
import wannabit.io.cosmostaion.model.StdSignMsg;
import wannabit.io.cosmostaion.model.StdTx;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Pub_key;
import wannabit.io.cosmostaion.model.type.Signature;
import wannabit.io.cosmostaion.model.type.WcSignature;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;


public class MsgGenerator {

    public static Msg genTransferMsg(String fromAddr, String toAddr, ArrayList<Coin> coins, BaseChain chain) {
        Msg result = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(OKEX_MAIN)) {
            try {
                value.from_address = WKey.convertAddressEthToOkex(fromAddr);
                value.to_address = WKey.convertAddressEthToOkex(toAddr);
                value.amount = coins;

                result.type = BaseConstant.OK_MSG_TYPE_TRANSFER;
                result.value = value;
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            value.from_address = fromAddr;
            value.to_address = toAddr;
            value.amount = coins;

            result.type = BaseConstant.COSMOS_MSG_TYPE_TRANSFER2;
            result.value = value;
        }
        return result;
    }

    public static ArrayList<Msg> genDelegateMsgs(String fromAddr, String toValAddr, Coin amount) {
        ArrayList<Msg> result = new ArrayList<>();
        Msg msg  = new Msg();
        Msg.Value value = new Msg.Value();
        value.delegator_address = fromAddr;
        value.validator_address = toValAddr;
        value.amount = amount;

        msg.type = BaseConstant.COSMOS_MSG_TYPE_DELEGATE;
        msg.value = value;
        result.add(msg);
        return result;
    }

    public static ArrayList<Msg> genUndelegateMsgs(String requestAddr, String fromValAddr, Coin amount) {
        ArrayList<Msg> result = new ArrayList<>();
        Msg msg  = new Msg();
        Msg.Value value = new Msg.Value();
        value.delegator_address = requestAddr;
        value.validator_address = fromValAddr;
        value.amount = amount;

        msg.type = BaseConstant.COSMOS_MSG_TYPE_UNDELEGATE;
        msg.value = value;
        result.add(msg);
        return result;
    }

    public static ArrayList<Msg> genReDelegateMsgs(String accountAddr, String fromValAddr, String toValAddr, Coin amount) {
        ArrayList<Msg> result = new ArrayList<>();
        Msg msg  = new Msg();
        Msg.Value value = new Msg.Value();
        value.delegator_address = accountAddr;
        value.validator_src_address = fromValAddr;
        value.validator_dst_address = toValAddr;
        value.amount = amount;

        msg.type = BaseConstant.COSMOS_MSG_TYPE_REDELEGATE;
        msg.value = value;
        result.add(msg);
        return result;
    }

    public static ArrayList<Msg> genWithdrawDeleMsgs(String requestAddr, ArrayList<String> fromValAddresses) {
        ArrayList<Msg> result = new ArrayList<>();
        for (String valAddress : fromValAddresses) {
            Msg msg  = new Msg();
            Msg.Value value = new Msg.Value();
            value.delegator_address = requestAddr;
            value.validator_address = valAddress;

            msg.type = BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_DEL;
            msg.value = value;
            result.add(msg);
        }
        return result;
    }

    public static ArrayList<Msg> genCompoundingMsgs(String requestAddr, ArrayList<Distribution.DelegationDelegatorReward> rewards, ChainConfig chainConfig) {
        ArrayList<Msg> result = new ArrayList<>();
        for (Distribution.DelegationDelegatorReward reward : rewards) {
            Msg withdrawMsg = new Msg();
            Msg.Value withdrawValue = new Msg.Value();
            withdrawValue.delegator_address = requestAddr;
            withdrawValue.validator_address = reward.getValidatorAddress();

            withdrawMsg.type = BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_DEL;
            withdrawMsg.value = withdrawValue;
            result.add(withdrawMsg);

            Msg delegateMsg = new Msg();
            Msg.Value delegateValue = new Msg.Value();
            Coin rewardCoin = null;
            for (CoinOuterClass.DecCoin coin : reward.getRewardList()) {
                if (coin.getDenom().equalsIgnoreCase(chainConfig.mainDenom())) {
                    rewardCoin = new Coin(coin.getDenom(), new BigDecimal(coin.getAmount()).movePointLeft(18).setScale(0, RoundingMode.UP).toPlainString());
                }
            }
            delegateValue.delegator_address = requestAddr;
            delegateValue.validator_address = reward.getValidatorAddress();
            delegateValue.amount = rewardCoin;

            delegateMsg.type = BaseConstant.COSMOS_MSG_TYPE_DELEGATE;
            delegateMsg.value = delegateValue;
            result.add(delegateMsg);
        }
        return result;
    }

    public static ArrayList<Msg> genRewardAddressChanges(String requestAddr, String newRewardAddr) {
        ArrayList<Msg> result = new ArrayList<>();
        Msg msg  = new Msg();
        Msg.Value value = new Msg.Value();
        value.delegator_address = requestAddr;
        value.withdraw_address = newRewardAddr;

        msg.type = BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_MODIFY;
        msg.value = value;
        result.add(msg);
        return result;
    }

    public static ArrayList<Msg> genVoteMsgs(String accountAddr, Map<Integer, String> opinionMap) {
        ArrayList<Msg> result = new ArrayList<>();
        opinionMap.forEach((id, opinion) -> {
            Msg msg = new Msg();
            Msg.Value value = new Msg.Value();
            value.proposal_id = String.valueOf(id);
            int option;
            if (opinion.equalsIgnoreCase("VOTE_OPTION_YES")) option = 1;
            else if (opinion.contains("VOTE_OPTION_ABSTAIN")) option = 2;
            else if (opinion.contains("VOTE_OPTION_NO_WITH_VETO")) option = 4;
            else option = 3;
            value.option = option;
            value.voter = accountAddr;

            msg.value = value;
            msg.type = BaseConstant.COSMOS_MSG_TYPE_VOTE;

            result.add(msg);
        });
        return result;
    }

    public static ArrayList<Msg> genIbcTransferMsgs(String sender, String receiver, Coin amount, AssetPath assetPath, Client.Height lastHeight) {
        ArrayList<Msg> result = new ArrayList<>();
        Msg msg  = new Msg();
        Msg.Value value = new Msg.Value();
        value.receiver = receiver;
        value.sender = sender;
        value.source_channel = assetPath.channel;
        value.source_port = assetPath.port;
        value.token = amount;
        value.time_height = new Msg.TimeoutHeight(String.valueOf(lastHeight.getRevisionHeight() + 1000), String.valueOf(lastHeight.getRevisionNumber()));

        msg.type = BaseConstant.COSMOS_MSG_TYPE_IBC_TRANSFER;
        msg.value = value;
        result.add(msg);
        return result;
    }

    public static ArrayList<Msg> genSwapMsgs(String sender, String poolId, Coin swapInCoin, Coin swapOutCoin) {
        ArrayList<Msg> result = new ArrayList<>();
        Msg msg  = new Msg();
        Msg.Value value = new Msg.Value();
        Msg.Route route = new Msg.Route(poolId, swapOutCoin.denom);
        value.routes = new ArrayList<>();
        value.routes.add(route);
        value.sender = sender;
        value.token_in = swapInCoin;
        value.token_out_min_amount = swapOutCoin.amount;

        msg.type = BaseConstant.OSMOSIS_MSG_TYPE_SWAP;
        msg.value = value;
        result.add(msg);
        return result;
    }

    public static HtltReq getBnbHtlcCreateMsg(BaseChain fromChain, BaseChain toChain, Account fromAccount, Account toAccount, ArrayList<Coin> sendCoins, long timestamp, byte[] originData) {
        HtltReq htltReq = new HtltReq();
        Coin toSendCoin = sendCoins.get(0);
        if (fromChain.equals(BaseChain.BNB_MAIN)) {
            if (toChain.equals(KAVA_MAIN)) {
                if (sendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_BNB)) {
                    htltReq.setRecipient(BINANCE_MAIN_BNB_DEPUTY);
                    htltReq.setSenderOtherChain(KAVA_MAIN_BNB_DEPUTY);

                } else if (sendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_BTCB)) {
                    htltReq.setRecipient(BINANCE_MAIN_BTCB_DEPUTY);
                    htltReq.setSenderOtherChain(KAVA_MAIN_BTCB_DEPUTY);

                } else if (sendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_XRPB)) {
                    htltReq.setRecipient(BINANCE_MAIN_XRPB_DEPUTY);
                    htltReq.setSenderOtherChain(KAVA_MAIN_XRPB_DEPUTY);

                } else if (sendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_BUSD)) {
                    htltReq.setRecipient(BINANCE_MAIN_BUSD_DEPUTY);
                    htltReq.setSenderOtherChain(KAVA_MAIN_BUSD_DEPUTY);

                }

                htltReq.setRecipientOtherChain(toAccount.address);
                htltReq.setTimestamp(timestamp);
                htltReq.setRandomNumberHash(Sha256.getSha256Digest().digest(originData));
                Token token = new Token();
                token.setDenom(toSendCoin.denom);
                BigDecimal sendAmount = new BigDecimal(toSendCoin.amount).movePointRight(8);
                token.setAmount(sendAmount.longValue());
                htltReq.setOutAmount(Collections.singletonList(token));
                htltReq.setExpectedIncome(sendAmount.toPlainString() + ":" + toSendCoin.denom);
                htltReq.setHeightSpan(407547);
                htltReq.setCrossChain(true);

            }

        }

        return htltReq;
    }

    public static Msg genOkDeposit(String delegator, Coin coin) {
        Msg result = new Msg();
        Msg.Value value = new Msg.Value();
        try {
            value.delegator_address = WKey.convertAddressEthToOkex(delegator);
            value.quantity = coin;

            result.type = BaseConstant.OK_MSG_TYPE_DEPOSIT;
            result.value = value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Msg genOkWithdraw(String delegator, Coin coin) {
        Msg result = new Msg();
        Msg.Value value = new Msg.Value();
        try {
            value.delegator_address = WKey.convertAddressEthToOkex(delegator);
            value.quantity = coin;

            result.type = BaseConstant.OK_MSG_TYPE_WITHDRAW;
            result.value = value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Msg genOkVote(String delegator, ArrayList<String> toVals) {
        Msg result = new Msg();
        Msg.Value value = new Msg.Value();
        try {
            value.delegator_address = WKey.convertAddressEthToOkex(delegator);
            value.validator_addresses = toVals;

            result.type = BaseConstant.OK_MSG_TYPE_DIRECT_VOTE;
            result.value = value;
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static String getSignature(ECKey key, byte[] toSignByte) {
        MessageDigest digest = Sha256.getSha256Digest();
        byte[] toSignHash = digest.digest(toSignByte);
        ECKey.ECDSASignature Signature = key.sign(Sha256Hash.wrap(toSignHash));
        byte[] sigData = new byte[64];
        System.arraycopy(integerToBytes(Signature.r, 32), 0, sigData, 0, 32);
        System.arraycopy(integerToBytes(Signature.s, 32), 0, sigData, 32, 32);
        String base64 = Base64.encodeToString(sigData, Base64.DEFAULT).replace("\n", "");
        return base64;
    }

    public static ReqBroadCast getBroadcaseReq(Account account, ArrayList<Msg> msgs, Fee fee, String memo, ECKey key, String chainId) {
        StdSignMsg tosign = genToSignMsg(chainId, "" + account.accountNumber, "" + account.sequenceNumber, msgs, fee, memo);
//        WLog.w("Tendermint tosign " + WUtil.prettyPrinter(tosign));

        String signatureTx = MsgGenerator.getSignature(key, tosign.getToSignByte());
//        WLog.w("Tendermint signatureTx " + signatureTx);

        Signature signature = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;

        pubKey.value = WKey.getPubKeyValue(key);
        signature.pub_key = pubKey;
        signature.signature = signatureTx;
        signature.account_number = "" + account.accountNumber;
        signature.sequence = "" + account.sequenceNumber;

        ArrayList<Signature> signatures = new ArrayList<>();
        signatures.add(signature);

        StdTx signedTx = MsgGenerator.genStakeSignedTransferTx(msgs, fee, memo, signatures);
        WLog.w("signedTx : " + WUtil.prettyPrinter(signedTx));

        ReqBroadCast reqBroadCast = new ReqBroadCast();
        reqBroadCast.returns = "sync";
        reqBroadCast.tx = signedTx.value;

        WLog.w("ReqBroadCast : " + WUtil.prettyPrinter(reqBroadCast));


        return reqBroadCast;
    }

    public static ReqBroadCast getKavaWcBroadcastReq(JSONObject tx, ECKey key) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        try {
            String sortedTx = mapper.writeValueAsString(mapper.readValue(tx.toString(), TreeMap.class));
            String signatureTx = MsgGenerator.getSignature(key, sortedTx.getBytes(StandardCharsets.UTF_8));
            Signature signature = new Signature();
            Pub_key pubKey = new Pub_key();
            pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
            pubKey.value = WKey.getPubKeyValue(key);
            signature.pub_key = pubKey;
            signature.signature = signatureTx;
            ArrayList<Signature> signatures = new ArrayList<>();
            signatures.add(signature);
            StdTx.Value value = new StdTx.Value();
            value.fee = new Gson().fromJson(tx.getJSONObject("fee").toString(), Fee.class);
            value.signatures = signatures;
            value.memo = tx.getString("memo");
            ReqBroadCast reqBroadCast = new ReqBroadCast();
            reqBroadCast.returns = "block";
            reqBroadCast.tx = value;
            return reqBroadCast;
        } catch (Exception e) {
            return null;
        }
    }

    public static ReqBroadCast getWcTrustBroadcaseReq(Account account, ArrayList<Msg> msgs, Fee fee, String memo, ECKey key, String chainId) {
        StdSignMsg tosign = genToSignMsg(chainId, "" + account.accountNumber, "" + account.sequenceNumber, msgs, fee, memo);
//        WLog.w("Tendermint tosign " + WUtil.prettyPrinter(tosign));

        String signatureTx = MsgGenerator.getSignature(key, tosign.getToSignByte());
//        WLog.w("Tendermint signatureTx " + signatureTx);

        Signature signature = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;

        pubKey.value = WKey.getPubKeyValue(key);
        signature.pub_key = pubKey;
        signature.signature = signatureTx;

        ArrayList<Signature> signatures = new ArrayList<>();
        signatures.add(signature);

        StdTx signedTx = MsgGenerator.genStakeSignedTransferTx(Lists.newArrayList(), fee, memo, signatures);
        WLog.w("signedTx : " + WUtil.prettyPrinter(signedTx));

        ReqBroadCast reqBroadCast = new ReqBroadCast();
        reqBroadCast.returns = "block";
        reqBroadCast.tx = signedTx.value;

        WLog.w("ReqBroadCast : " + WUtil.prettyPrinter(reqBroadCast));


        return reqBroadCast;
    }

    public static Signature getWcSignDiectBroadcaseReq(ECKey key, byte[] signBytes) {
        String signatureTx = MsgGenerator.getSignature(key, signBytes);
        Signature signature = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
        pubKey.value = WKey.getPubKeyValue(key);
        signature.pub_key = pubKey;
        signature.signature = signatureTx;
        return signature;
    }

    public static WcSignature getWcKeplrBroadcaseReq(ECKey key, JsonObject txMsg) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        try {
            String test = mapper.writeValueAsString(mapper.readValue(txMsg.toString(), TreeMap.class));
            String signatureTx = MsgGenerator.getSignature(key, test.getBytes(Charset.forName("UTF-8")));

            WcSignature signature = new WcSignature();
            Pub_key pubKey = new Pub_key();
            pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
            pubKey.value = WKey.getPubKeyValue(key);
            signature.pub_key = pubKey;
            signature.signature = signatureTx;

            return signature;
        } catch (IOException e) {
            e.printStackTrace();
            return new WcSignature();
        }
    }

    public static ReqBroadCast getOKexBroadcaseReq(Account account, ArrayList<Msg> msgs, Fee fee, String memo, ECKey key, String chainId) {
        if (account.customPath == 0) {
            //using Tendermint type sig
            return getBroadcaseReq(account, msgs, fee, memo, key, chainId);
        } else {
            //using Ethermint type sig
            StdSignMsg tosign = genToSignMsg(chainId, "" + account.accountNumber, "" + account.sequenceNumber, msgs, fee, memo);
            String sig = MsgGenerator.getEthermintSignature(key, tosign.getToSignByte());

            Signature signature = new Signature();
            Pub_key pubKey = new Pub_key();
            pubKey.type = BaseConstant.ETHERMINT_KEY_TYPE_PUBLIC;

            String pubHex = WKey.generatePubKeyHexFromPriv(key.getPrivateKeyAsHex());
            pubKey.value = Strings.fromByteArray(org.bouncycastle.util.encoders.Base64.encode(Hex.decode(pubHex)));
            signature.pub_key = pubKey;
            signature.signature = sig;
            signature.account_number = "" + account.accountNumber;
            signature.sequence = "" + account.sequenceNumber;

            ArrayList<Signature> signatures = new ArrayList<>();
            signatures.add(signature);

            StdTx signedTx = MsgGenerator.genStakeSignedTransferTx(msgs, fee, memo, signatures);
//            WLog.w("Ethermint signedTx : " +  WUtil.prettyPrinter(signedTx));

            ReqBroadCast reqBroadCast = new ReqBroadCast();
            reqBroadCast.returns = "sync";
            reqBroadCast.tx = signedTx.value;
//            WLog.w("Ethermint reqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));

            return reqBroadCast;
        }
    }

    public static String getEthermintSignature(ECKey key, byte[] toSignByte) {
        BigInteger privKey = new BigInteger(key.getPrivateKeyAsHex(), 16);
        Sign.SignatureData sig = Sign.signMessage(toSignByte, ECKeyPair.create(privKey));
        return toBase64(sig);
    }

    public static String toBase64(Sign.SignatureData sig) {
        byte[] sigData = new byte[64];  // 32 bytes for R + 32 bytes for S
        System.arraycopy(sig.getR(), 0, sigData, 0, 32);
        System.arraycopy(sig.getS(), 0, sigData, 32, 32);
        return new String(org.bouncycastle.util.encoders.Base64.encode(sigData), Charset.forName("UTF-8"));
    }
}
