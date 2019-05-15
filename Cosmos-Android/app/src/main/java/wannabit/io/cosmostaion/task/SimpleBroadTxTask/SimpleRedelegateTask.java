package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import org.bitcoinj.crypto.DeterministicKey;

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
import wannabit.io.cosmostaion.model.StakeStdSignMsgWithType;
import wannabit.io.cosmostaion.model.StakeStdTx;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Pub_key;
import wannabit.io.cosmostaion.model.type.Signature;
import wannabit.io.cosmostaion.model.type.StakeMsg;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqStakeBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class SimpleRedelegateTask extends CommonTask {

    private Account     mAccount;
    private Validator   mFromValidator;
    private Validator   mToValidator;
    private Coin        mRedelegateAmount;
    private String      mReDelegateMemo;
    private Fee         mFees;

    public SimpleRedelegateTask(BaseApplication app, TaskListener listener, Account mAccount,
                                Validator mFromValidator, Validator mToValidator,
                                Coin mRedelegateAmount, String mReDelegateMemo,
                                Fee mFees) {
        super(app, listener);
        this.mAccount = mAccount;
        this.mFromValidator = mFromValidator;
        this.mToValidator = mToValidator;
        this.mRedelegateAmount = mRedelegateAmount;
        this.mReDelegateMemo = mReDelegateMemo;
        this.mFees = mFees;
        this.mResult.taskType   = BaseConstant.TASK_GEN_TX_SIMPLE_REDELEGATE;
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

            Response<ResLcdAccountInfo> accountResponse = ApiClient.getWannabitChain(mApp, BaseChain.getChain(mAccount.baseChain)).getAccountInfo(mAccount.address).execute();
            if(!accountResponse.isSuccessful()) {
                mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                return mResult;
            }
            mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromLcd(mAccount.id, accountResponse.body()));
            mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromLcd(mAccount.id, accountResponse.body()));
            mAccount = mApp.getBaseDao().onSelectAccount(""+mAccount.id);

            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(entropy, Integer.parseInt(mAccount.path));

            StakeMsg singleRedeleMsg = MsgGenerator.genReDelegateMsg(mAccount.address, mFromValidator.operator_address, mToValidator.operator_address, mRedelegateAmount);

            ArrayList<StakeMsg> msgs= new ArrayList<>();
            msgs.add(singleRedeleMsg);

            StakeStdSignMsgWithType tosign = MsgGenerator.genStakeToSignMsgWithType(
                    mAccount.baseChain,
                    ""+mAccount.accountNumber,
                    ""+mAccount.sequenceNumber,
                    msgs,
                    mFees,
                    mReDelegateMemo);
            WLog.w("SimpleRedelegateTask tosign : " +  WUtil.getPresentor().toJson(tosign));

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

            StakeStdTx signedTx = MsgGenerator.genStakeSignedTransferTx(msgs, mFees, mReDelegateMemo, signatures);
            signedTx.value.signatures = signatures;
            WLog.w("SimpleRedelegateTask signedTx : " +  WUtil.getPresentor().toJson(signedTx));

            ReqStakeBroadCast reqBroadCast = new ReqStakeBroadCast();
            reqBroadCast.returns = "sync";
            reqBroadCast.tx = signedTx.value;
            WLog.w("ReqBroadCast : " +  WUtil.getPresentor().toJson(reqBroadCast));

            Response<ResBroadTx> response = ApiClient.getWannabitChain(mApp, BaseChain.getChain(mAccount.baseChain)).broadStakeTx(reqBroadCast).execute();
            if(response.isSuccessful() && response.body() != null) {
                WLog.w("SimpleRedelegateTask success!!");
                WLog.w("response.body() : " + response.body());
                if (response.body().txhash != null) {
                    mResult.resultData = response.body().txhash;
                }

                if(response.body().code != null) {
                    WLog.w("response.code() : " + response.body().code);
                    mResult.errorCode = response.body().code;
                    mResult.errorMsg = response.body().raw_log;
                    return mResult;
                }
                mResult.isSuccess = true;

            } else {
                WLog.w("SimpleUndelegateTask not success!!");
                mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
//                try {
//                    JSONObject jObjError = new JSONObject(response.errorBody().string());
//                    WLog.w("jObjError1 : " + jObjError.toString());
//                } catch (Exception e) {
//                    WLog.w("jObjError3 : " + e.getMessage());
//                }
            }
        } catch (Exception e) {
            WLog.w("e : " + e.getMessage());
            if(BaseConstant.IS_SHOWLOG) e.printStackTrace();
        }
        return mResult;
    }
}
