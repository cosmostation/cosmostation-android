package wannabit.io.cosmostaion.test;

import android.os.Bundle;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicHierarchy;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;

import java.util.ArrayList;
import java.util.Arrays;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.model.StdSignMsg;
import wannabit.io.cosmostaion.model.StdSignMsgWithType;
import wannabit.io.cosmostaion.model.StdTx;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Delegation;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Input;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Output;
import wannabit.io.cosmostaion.model.type.Pub_key;
import wannabit.io.cosmostaion.model.type.Signature;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class TestActivity extends BaseActivity {

    private String yongAddr = "cosmos1gfzexy3z0qfc97mjudjy5zeg2fje6k442phy6r";
    private String yong2Addr = "cosmos1y0yxn4jhp82dhc6uzpeswxyjvdpwnpzdzjhl9y";
    private String yong3Addr = "cosmos17amz4cp6yuj7uvt5c3kjfhg8v3r8fg9pjjawrm";

    private String valyongjoo = "cosmosvaloper1gfzexy3z0qfc97mjudjy5zeg2fje6k4404r3ks";
    private String accountNumber = "0";
    private String sequenceNumber = "20";

    private String accountNumber3 = "6";
    private String sequenceNumber3 = "8";

    private Gson Presenter = new GsonBuilder().disableHtmlEscaping().create();
//  private Gson Presenter = new GsonBuilder().serializeNulls().create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TestGenMultiTransfer1();
//        TestGenWithdrawValidatorReward();
//        TestGenWithdrawDelegationReward();
//        TestGenUnbond();
//        TestGenSingleDelegate();
//        TestGenSingleTransfer();
    }


    //a->b c->b
    private void TestGenMultiTransfer1() {
        WLog.w("TestGenMultiTransfer1");

        Msg singleSendMsg1 = MsgGenerator.genTransferMsg(yongAddr, yong2Addr, getTestAmountCoin());
        Msg singleSendMsg2 = MsgGenerator.genTransferMsg(yong3Addr, yong2Addr, getTestAmountCoin2());

        ArrayList<Msg> msgs = new ArrayList<>();
        msgs.add(singleSendMsg1);
        msgs.add(singleSendMsg2);

        ArrayList<Msg.Value> msgValues = new ArrayList<>();
        msgValues.add(singleSendMsg1.value);
        msgValues.add(singleSendMsg2.value);

        StdSignMsg tosign1 = MsgGenerator.genToSignMsg(
                BaseChain.WANNABIT_NET.getChain(),
                accountNumber,
                sequenceNumber,
                msgValues,
                getTestFee(),
                "");
        String signatureTx1 = MsgGenerator.getSignature(getTestKey(), tosign1.getToSignByte());
        Signature signature1 = new Signature();
        Pub_key pubKey1 = new Pub_key();
        pubKey1.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
        pubKey1.value = getPubKeyValue();
        signature1.pub_key = pubKey1;
        signature1.signature = signatureTx1;

        StdSignMsg tosign2 = MsgGenerator.genToSignMsg(
                BaseChain.WANNABIT_NET.getChain(),
                accountNumber3,
                sequenceNumber3,
                msgValues,
                getTestFee(),
                "");
        String signatureTx2 = MsgGenerator.getSignature(getTestKey3(), tosign2.getToSignByte());
        Signature signature2 = new Signature();
        Pub_key pubKey2 = new Pub_key();
        pubKey2.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
        pubKey2.value = getPubKeyValue3();
        signature2.pub_key = pubKey2;
        signature2.signature = signatureTx2;

        ArrayList<Signature> signatures = new ArrayList<>();
        signatures.add(signature1);
        signatures.add(signature2);

        StdTx signedTx = MsgGenerator.genSignedTransferTx(msgs, getTestFee(), "", signatures);
        WLog.w("Transfer signed : " +  Presenter.toJson(signedTx));
        WLog.w("Transfer signed22: " +  WUtil.prettyPrinter(signedTx));

        WLog.w("Transfer signed hex : " +  WUtil.str2Hex(Presenter.toJson(signedTx)));

    }


    //version 30.0
    private void TestGenWithdrawValidatorReward() {
        WLog.w("TestGenWithdrawValidatorReward");
    }

    private void TestGenWithdrawDelegationReward() {
        WLog.w("TestGenWithdrawDelegationReward");

        Msg singleWithdrawDeleMsg = MsgGenerator.genWithdrawDeleMsg(yong3Addr, valyongjoo);

        ArrayList<Msg> msgs= new ArrayList<>();
        msgs.add(singleWithdrawDeleMsg);

        StdSignMsgWithType tosign = MsgGenerator.genToSignMsgWithType(
                BaseChain.WANNABIT_NET.getChain(),
                accountNumber3,
                sequenceNumber3,
                msgs,
                getTestFee(),
                "");
        WLog.w("WithdrawDelegation tosign : " +  Presenter.toJson(tosign));
        WLog.w("WithdrawDelegation tosign22 : " +  WUtil.prettyPrinter(tosign));

        // build signature String
        String signatureTx = MsgGenerator.getSignature(getTestKey3(), tosign.getToSignByte());
        WLog.w("WithdrawDelegation signatureTx : " +  signatureTx);

        // build Signature object
        Signature signature = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
        pubKey.value = getPubKeyValue();
        signature.pub_key = pubKey;
        signature.signature = signatureTx;

        ArrayList<Signature> signatures = new ArrayList<>();
        signatures.add(signature);

        // build complete tx type
        StdTx signedTx = MsgGenerator.genSignedTransferTx(msgs, getTestFee(), "", signatures);
        signedTx.value.signatures = signatures;
        WLog.w("WithdrawDelegation signed : " +  Presenter.toJson(signedTx));
        WLog.w("WithdrawDelegation signed22: " +  WUtil.prettyPrinter(signedTx));

        WLog.w("WithdrawDelegation signed hex : " +  WUtil.str2Hex(Presenter.toJson(signedTx)));

    }

    private void TestGenUnbond() {
        WLog.w("TestGenUnbond");
        //build signle MSG
        Msg singleUnbondMsg = MsgGenerator.genUnbondMsg(yong3Addr, valyongjoo, "2.0000000000");

        ArrayList<Msg.Value> msgValues = new ArrayList<>();
        msgValues.add(singleUnbondMsg.value);

        StdSignMsg tosign = MsgGenerator.genToSignMsg(
                BaseChain.WANNABIT_NET.getChain(),
                accountNumber3,
                sequenceNumber3,
                msgValues,
                getTestFee(),
                "");
        WLog.w("Unbond tosign : " +  Presenter.toJson(tosign));
        WLog.w("Unbond tosign22 : " +  WUtil.prettyPrinter(tosign));

        // build signature String
        String signatureTx = MsgGenerator.getSignature(getTestKey3(), tosign.getToSignByte());
        WLog.w("Unbond signatureTx : " +  signatureTx);

        // build Signature object
        Signature signature = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
        pubKey.value = getPubKeyValue();
        signature.pub_key = pubKey;
        signature.signature = signatureTx;

        ArrayList<Signature> signatures = new ArrayList<>();
        signatures.add(signature);


        // build complete tx type
        ArrayList<Msg> msgs = new ArrayList<>();
        msgs.add(singleUnbondMsg);
        StdTx signedTx = MsgGenerator.genSignedTransferTx(msgs, getTestFee(), "", signatures);
        signedTx.value.signatures = signatures;
        WLog.w("Unbond signed : " +  Presenter.toJson(signedTx));
        WLog.w("Unbond signed22: " +  WUtil.prettyPrinter(signedTx));

        WLog.w("Unbond signed hex : " +  WUtil.str2Hex(Presenter.toJson(signedTx)));

    }

    private void TestGenSingleDelegate() {
        WLog.w("TestGenSingleTransfer");

        Msg singleDelegateMsg = MsgGenerator.genDelegateMsg(yongAddr, valyongjoo, getTestDelegateAmount());

        ArrayList<Msg> msgs= new ArrayList<>();
        msgs.add(singleDelegateMsg);

        StdSignMsgWithType tosign = MsgGenerator.genToSignMsgWithType(
                BaseChain.WANNABIT_NET.getChain(),
                accountNumber,
                sequenceNumber,
                msgs,
                getTestFee(),
                "");
        WLog.w("Delegate tosign : " +  Presenter.toJson(tosign));
        WLog.w("Delegate tosign22 : " +  WUtil.prettyPrinter(tosign));

        // build signature String
        String signatureTx = MsgGenerator.getSignature(getTestKey(), tosign.getToSignByte());
        WLog.w("Delegate signatureTx : " +  signatureTx);

        // build Signature object
        Signature signature = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
        pubKey.value = getPubKeyValue();
        signature.pub_key = pubKey;
        signature.signature = signatureTx;

        ArrayList<Signature> signatures = new ArrayList<>();
        signatures.add(signature);

        // build complete tx type
        StdTx signedTx = MsgGenerator.genSignedTransferTx(msgs, getTestFee(), "", signatures);
        signedTx.value.signatures = signatures;
        WLog.w("Delegate signed : " +  Presenter.toJson(signedTx));
        WLog.w("Delegate signed22: " +  WUtil.prettyPrinter(signedTx));

        WLog.w("Delegate signed hex : " +  WUtil.str2Hex(Presenter.toJson(signedTx)));
    }

    private void TestGenSingleTransfer() {
        WLog.w("TestGenSingleTransfer");


        //build signe MSG
        Msg singleSendMsg = MsgGenerator.genTransferMsg(yongAddr, yong2Addr, getTestAmountCoin());
        WLog.w("stdTx : " +  Presenter.toJson(singleSendMsg));

        // build unsigned Tx(useless)
//        ArrayList<Msg> msgs1 = new ArrayList<>();
//        msgs1.add(singleSendMsg);
//        StdTx unsignedTx = MsgGenerator.genUnsignedTransferTx(msgs1, getTestFee(), "");
//        WLog.w("Transfer unsignedTx : " +  Presenter.toJson(unsignedTx));
//        WLog.w("Transfer unsignedTx22 : " +  WUtil.prettyPrinter(unsignedTx));

        // build to sign target
        ArrayList<Msg.Value> msgValues = new ArrayList<>();
        msgValues.add(singleSendMsg.value);

        StdSignMsg tosign = MsgGenerator.genToSignMsg(
                BaseChain.WANNABIT_NET.getChain(),
                accountNumber,
                sequenceNumber,
                msgValues,
                getTestFee(),
                "");
        WLog.w("Transfer tosign : " +  Presenter.toJson(tosign));
        WLog.w("Transfer tosign22 : " +  WUtil.prettyPrinter(tosign));

        // build signature String
        String signatureTx = MsgGenerator.getSignature(getTestKey(), tosign.getToSignByte());
        WLog.w("Transfer signatureTx : " +  signatureTx);


        // build Signature object
        Signature signature = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
        pubKey.value = getPubKeyValue();
        signature.pub_key = pubKey;
        signature.signature = signatureTx;

        ArrayList<Signature> signatures = new ArrayList<>();
        signatures.add(signature);

        // build complete tx type
        ArrayList<Msg> msgs = new ArrayList<>();
        msgs.add(singleSendMsg);
        StdTx signedTx = MsgGenerator.genSignedTransferTx(msgs, getTestFee(), "", signatures);
        signedTx.value.signatures = signatures;
        WLog.w("Transfer signed : " +  Presenter.toJson(signedTx));
        WLog.w("Transfer signed22: " +  WUtil.prettyPrinter(signedTx));

        WLog.w("Transfer signed hex : " +  WUtil.str2Hex(Presenter.toJson(signedTx)));

    }
































    private DeterministicKey getTestKey() {
        String              mnemonic    = "iron breeze tongue voice stomach nut manage advance rather mad hurry neutral identify armed unusual crunch hammer scan riot mom surface horn stamp thank";
        ArrayList<String>   words       = new ArrayList<>(Arrays.asList(mnemonic.split("\\s+")));
        byte[]              entropy     = WKey.toEntropy(words);
        byte[]              seedHD      = WKey.getHDSeed(entropy);
        DeterministicKey masterKey      = HDKeyDerivation.createMasterPrivateKey(seedHD);
        return new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(), true, true,  new ChildNumber(0));
    }

    public String getPubKeyValue() {
        String result = "";
        DeterministicKey key = getTestKey();
        try {
            byte[] data = key.getPubKey();
            result = Base64.encodeToString(data, Base64.DEFAULT).replace("\n", "");
            WLog.w("base64 : " +  result);

        } catch (Exception e) {
            WLog.w("Exception");
        }
        return result;
    }

//    private DeterministicKey getTestKey2() {
//        String              mnemonic    = "episode among bitter process child entry practice certain note nasty cloth shield skill run uniform digital display market indicate island swear brisk thing reopen";
//        ArrayList<String>   words       = new ArrayList<>(Arrays.asList(mnemonic.split("\\s+")));
//        byte[]              entropy     = WKey.toEntropy(words);
//        byte[]              seedHD      = WKey.getHDSeed(entropy);
//        DeterministicKey masterKey      = HDKeyDerivation.createMasterPrivateKey(seedHD);
//        return new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(), true, true,  new ChildNumber(0));
//    }
//
//    public String getPubKeyValue2() {
//        String result = "";
//        DeterministicKey key = getTestKey2();
//        try {
//            byte[] data = key.getPubKey();
//            result = Base64.encodeToString(data, Base64.DEFAULT).replace("\n", "");
//            WLog.w("base64 : " +  result);
//
//        } catch (Exception e) {
//            WLog.w("Exception");
//        }
//        return result;
//    }

    private DeterministicKey getTestKey3() {
        String              mnemonic    = "kid order want shell various frog caution approve federal practice fruit firm shove scrap uniform acoustic upgrade auto elbow escape lake town bone scissors";
        ArrayList<String>   words       = new ArrayList<>(Arrays.asList(mnemonic.split("\\s+")));
        byte[]              entropy     = WKey.toEntropy(words);
        byte[]              seedHD      = WKey.getHDSeed(entropy);
        DeterministicKey masterKey      = HDKeyDerivation.createMasterPrivateKey(seedHD);
        return new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(), true, true,  new ChildNumber(0));
    }

    public String getPubKeyValue3() {
        String result = "";
        DeterministicKey key = getTestKey3();
        try {
            byte[] data = key.getPubKey();
            result = Base64.encodeToString(data, Base64.DEFAULT).replace("\n", "");
            WLog.w("base64 : " +  result);

        } catch (Exception e) {
            WLog.w("Exception");
        }
        return result;
    }

    private ArrayList<Coin> getTestAmountCoin() {
        ArrayList<Coin> result = new ArrayList<>();
        Coin testCoin = new Coin("STAKE", "1");
        result.add(testCoin);
        return result;
    }
    private ArrayList<Coin> getTestAmountCoin2() {
        ArrayList<Coin> result = new ArrayList<>();
        Coin testCoin = new Coin("STAKE", "2");
        result.add(testCoin);
        return result;
    }

    private Fee getTestFee() {
        Fee result = new Fee();
        ArrayList<Coin> amount = new ArrayList<>();
        Coin testCoin = new Coin("", "0");
        amount.add(testCoin);
        result.amount = amount;
        result.gas = "200000";
        return result;
    }

    private Delegation getTestDelegateAmount() {
        Delegation result = new Delegation();
        result.denom = "STAKE";
        result.amount = "1";
        return result;
    }
}
