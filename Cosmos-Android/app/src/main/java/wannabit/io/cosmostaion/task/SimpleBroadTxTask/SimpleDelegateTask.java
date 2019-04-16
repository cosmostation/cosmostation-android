package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import android.text.TextUtils;

import org.bitcoinj.crypto.DeterministicKey;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
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
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class SimpleDelegateTask extends CommonTask {

    private Account         mAccount;
    private String          mToValidatorAddress;
    private Coin            mToDelegateAmount;
    private String          mToDelegateMemo;
    private Fee             mToFees;

    public SimpleDelegateTask(BaseApplication app, TaskListener listener,
                              Account account, String toValidatorAddress,
                              Coin toDelegateAmount,
                              String toDelegateMemo, Fee toFees) {
        super(app, listener);
        WLog.w("SimpleDelegateTask");
        this.mAccount = account;
        this.mToValidatorAddress = toValidatorAddress;
        this.mToDelegateAmount = toDelegateAmount;
        this.mToDelegateMemo = toDelegateMemo;
        this.mToFees = toFees;
        this.mResult.taskType   = BaseConstant.TASK_GEN_TX_SIMPLE_DELEGATE;
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
            Password checkPw = mApp.getBaseDao().onSelectPassword();
            if(!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
                return mResult;
            }

            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(entropy, Integer.parseInt(mAccount.path));

            Msg singleDelegateMsg = MsgGenerator.genDelegateMsg(mAccount.address, mToValidatorAddress, mToDelegateAmount, BaseChain.getChain(mAccount.baseChain));

            ArrayList<Msg> msgs= new ArrayList<>();
            msgs.add(singleDelegateMsg);

            StdSignMsgWithType tosign = MsgGenerator.genToSignMsgWithType(
                    mAccount.baseChain,
                    ""+mAccount.accountNumber,
                    ""+mAccount.sequenceNumber,
                    msgs,
                    mToFees,
                    mToDelegateMemo);
            WLog.w("SimpleDelegateTask tosign : " +  WUtil.getPresentor().toJson(tosign));

            byte[] ToSignByte = tosign.getToSignByte();
            WLog.w("ToSignByte  " + WUtil.ByteArrayToHexString(ToSignByte));

            String signatureTx = MsgGenerator.getSignature(deterministicKey, tosign.getToSignByte());
            WLog.w("String  " + signatureTx);

            // build Signature object
            Signature signature = new Signature();
            Pub_key pubKey = new Pub_key();
            pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
            pubKey.value = WKey.getPubKeyValue(deterministicKey);
            signature.pub_key = pubKey;
            signature.signature = signatureTx;

            ArrayList<Signature> signatures = new ArrayList<>();
            signatures.add(signature);

            StdTx signedTx = MsgGenerator.genSignedTransferTx(msgs, mToFees, mToDelegateMemo, signatures);
            signedTx.value.signatures = signatures;
            WLog.w("SimpleDelegateTask signed1 : " +  WUtil.getPresentor().toJson(signedTx));

            ReqBroadCast reqBroadCast = new ReqBroadCast();
            reqBroadCast.returns = "sync";
            reqBroadCast.tx = signedTx.value;
            WLog.w("ReqBroadCast : " +  WUtil.getPresentor().toJson(reqBroadCast));

            Response<ResBroadTx> response = ApiClient.getWannabitChain(mApp, BaseChain.getChain(mAccount.baseChain)).broadTx(reqBroadCast).execute();
            if(response.isSuccessful() && response.body() != null) {
                WLog.w("SimpleDelegateTask success!!");
                WLog.w("response.body() : " + response.body());
                if (response.body().txhash != null) {
                    mResult.resultData = response.body().txhash;
                }

                if(response.body().code != null) {
                    WLog.w("response.code() : " + response.body().code);
                    mResult.errorCode = response.body().code;
                    return mResult;
                }
                mResult.isSuccess = true;

            } else {
                WLog.w("SimpleDelegateTask not success!!");
                mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
            }

        } catch (Exception e) {
            WLog.w("e : " + e.getMessage());
            e.printStackTrace();
        }
        return mResult;
    }
}
