package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import android.text.TextUtils;

import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigDecimal;
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

public class SimpleUndelegateTask extends CommonTask {

    private Account     mAccount;
    private String      mFromValidatorAddress;
    private String      mUndelegateAmount;
    private String      mUnDelegateMemo;
    private Fee         mUnFees;

    public SimpleUndelegateTask(BaseApplication app, TaskListener listener, Account account,
                                String fromValidatorAddress, String undelegateAmount,
                                String unDelegateMemo, Fee unFees) {
        super(app, listener);
        this.mAccount = account;
        this.mFromValidatorAddress = fromValidatorAddress;
        this.mUndelegateAmount = undelegateAmount;
        this.mUnDelegateMemo = unDelegateMemo;
        this.mUnFees = unFees;
        this.mResult.taskType   = BaseConstant.TASK_GEN_TX_SIMPLE_UNDELEGATE;
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
            WLog.w("SimpleUndelegateTask 00");
            Password checkPw = mApp.getBaseDao().onSelectPassword();
            if(!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
                WLog.w("SimpleUndelegateTask 99");
                return mResult;
            }
            WLog.w("SimpleUndelegateTask 11");

            String seed = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPath(seed, Integer.parseInt(mAccount.path));

            WLog.w("amount : " + new BigDecimal(mUndelegateAmount).setScale(18).toPlainString());
            Msg singleUnbondMsg = MsgGenerator.genUnbondMsg(mAccount.address, mFromValidatorAddress, new BigDecimal(mUndelegateAmount).setScale(18).toPlainString());

//            ArrayList<Msg.Value> msgs = new ArrayList<>();
            //TODO type change check
            ArrayList<Msg> msgs= new ArrayList<>();
            msgs.add(singleUnbondMsg);

            StdSignMsgWithType tosign = MsgGenerator.genToSignMsgWithType(
                    mAccount.baseChain,
                    ""+mAccount.accountNumber,
                    ""+mAccount.sequenceNumber,
                    msgs,
                    mUnFees,
                    mUnDelegateMemo);
            WLog.w("SimpleUndelegateTask tosign : " +  WUtil.getPresentor().toJson(tosign));

            String signatureTx = MsgGenerator.getSignature(deterministicKey, tosign.getToSignByte());

            // build Signature object
            Signature signature = new Signature();
            Pub_key pubKey = new Pub_key();
            pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
            pubKey.value = WKey.getPubKeyValue(deterministicKey);
            signature.pub_key = pubKey;
            signature.signature = signatureTx;

            ArrayList<Signature> signatures = new ArrayList<>();
            signatures.add(signature);

            StdTx signedTx = MsgGenerator.genSignedTransferTx(msgs, mUnFees, mUnDelegateMemo, signatures);
            signedTx.value.signatures = signatures;
            WLog.w("SimpleUndelegateTask signed1 : " +  WUtil.getPresentor().toJson(signedTx));

            String gentx = WUtil.str2Hex(WUtil.getPresentor().toJson(signedTx));
            WLog.w("SimpleUndelegateTask gentx : " +  gentx);
            Response<ResBroadTx> response = ApiClient.getCSService(mApp).broadcastTx(gentx).execute();
            if(response.isSuccessful() && response.body() != null) {
                ResBroadTx result = response.body();
                WLog.w("SimpleUndelegateTask result errorMsg : " + result.errorMsg);
                WLog.w("SimpleUndelegateTask result errorCode : " + result.errorCode);
                WLog.w("SimpleUndelegateTask result hash : " + result.hash);
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
