package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import android.text.TextUtils;

import org.bitcoinj.crypto.DeterministicKey;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.model.StdSignMsgWithType;
import wannabit.io.cosmostaion.model.StdTx;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Pub_key;
import wannabit.io.cosmostaion.model.type.Signature;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class SimpleSendTask extends CommonTask {

    private Account         mAccount;
    private String          mToAddress;
    private ArrayList<Coin> mToSendAmount;
    private String          mToSendMemo;
    private Fee             mToFees;

    public SimpleSendTask(BaseApplication app, TaskListener listener, Account account,
                          String toAddress, ArrayList<Coin> toSendAmount, String toSendMemo,
                          Fee toFees) {
        super(app, listener);
        WLog.w("SimpleSendTask");
        this.mAccount           = account;
        this.mToAddress         = toAddress;
        this.mToSendAmount      = toSendAmount;
        this.mToSendMemo        = toSendMemo;
        this.mToFees            = toFees;
        this.mResult.taskType   = BaseConstant.TASK_GEN_TX_SIMPLE_SEND;
    }


    /**
     *
     * @param strings
     *  strings[0] : password
     *
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            WLog.w("SimpleSendTask 00");
            Password checkPw = mApp.getBaseDao().onSelectPassword();
            if(!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
                WLog.w("SimpleSendTask 99");
                return mResult;
            }
            WLog.w("SimpleSendTask 11");


            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(entropy, Integer.parseInt(mAccount.path));

            Msg singleSendMsg = MsgGenerator.genTransferMsg(mAccount.address, mToAddress, mToSendAmount);
//            WLog.w("SimpleSendTask stdTx : " +  WUtil.getPresentor().toJson(singleSendMsg));

            ArrayList<Msg> msgs= new ArrayList<>();
            msgs.add(singleSendMsg);

            StdSignMsgWithType tosign = MsgGenerator.genToSignMsgWithType(
                    mAccount.baseChain,
                    ""+mAccount.accountNumber,
                    ""+mAccount.sequenceNumber,
                    msgs,
                    mToFees,
                    mToSendMemo);
            WLog.w("SimpleSendTask tosign : " +  WUtil.getPresentor().toJson(tosign));

//            String signatureTx = MsgGenerator.getSignature(orikey, tosign.getToSignByte());
            String signatureTx = MsgGenerator.getSignature(deterministicKey, tosign.getToSignByte());
//            WLog.w("SimpleSendTask tosign1 : " +  WUtil.getPresentor().toJson(tosign));
//            WLog.w("SimpleSendTask tosign2 : " +  WUtil.prettyPrinter(tosign));

            // build Signature object
            Signature signature = new Signature();
            Pub_key pubKey = new Pub_key();
            pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
            pubKey.value = WKey.getPubKeyValue(deterministicKey);
            signature.pub_key = pubKey;
            signature.signature = signatureTx;

            ArrayList<Signature> signatures = new ArrayList<>();
            signatures.add(signature);


            // build complete tx type
            StdTx signedTx = MsgGenerator.genSignedTransferTx(msgs, mToFees, mToSendMemo, signatures);
            signedTx.value.signatures = signatures;
            WLog.w("SimpleSendTask signed1 : " +  WUtil.getPresentor().toJson(signedTx));
//            WLog.w("SimpleSendTask signed2 : " +  WUtil.prettyPrinter(signedTx));

            String gentx = WUtil.str2Hex(WUtil.getPresentor().toJson(signedTx));
            WLog.w("SimpleSendTask gentx : " +  gentx);
            Response<ResBroadTx> response = ApiClient.getCSService(mApp).broadcastTx(gentx).execute();
            if(response.isSuccessful() && response.body() != null) {
                ResBroadTx result = response.body();
                WLog.w("SimpleSendTask result errorMsg : " + result.errorMsg);
                WLog.w("SimpleSendTask result errorCode : " + result.errorCode);
                WLog.w("SimpleSendTask result hash : " + result.hash);
                if(!TextUtils.isEmpty(result.hash) && result.errorCode == 0) {
                    mResult.resultData = result.hash;
                    mResult.isSuccess = true;
                }
            }


        } catch (Exception e) {
            WLog.w("e : " + e.getMessage());
            e.printStackTrace();
        }
        return mResult;
    }
}
