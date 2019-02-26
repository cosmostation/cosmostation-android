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

public class SimpleRewardTask extends CommonTask {

    private Account     mAccount;
    private String      mFromValidatorAddress;
    private String      mRewardMemo;
    private Fee         mRewardFees;

    public SimpleRewardTask(BaseApplication app, TaskListener listener, Account account,
                            String fromValidatorAddress, String rewardMemo, Fee rewardFees) {
        super(app, listener);
        this.mAccount = account;
        this.mFromValidatorAddress = fromValidatorAddress;
        this.mRewardMemo = rewardMemo;
        this.mRewardFees = rewardFees;
        this.mResult.taskType   = BaseConstant.TASK_GEN_TX_SIMPLE_REWARD;
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

            WLog.w("SimpleRewardTask 00");
            Password checkPw = mApp.getBaseDao().onSelectPassword();
            if(!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
                WLog.w("SimpleRewardTask 99");
                return mResult;
            }
            WLog.w("SimpleRewardTask 11");

            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(entropy, Integer.parseInt(mAccount.path));

            Msg singleWithdrawDeleMsg = MsgGenerator.genWithdrawDeleMsg(mAccount.address, mFromValidatorAddress);

            ArrayList<Msg> msgs= new ArrayList<>();
            msgs.add(singleWithdrawDeleMsg);

            StdSignMsgWithType tosign = MsgGenerator.genToSignMsgWithType(
                    mAccount.baseChain,
                    ""+mAccount.accountNumber,
                    ""+mAccount.sequenceNumber,
                    msgs,
                    mRewardFees,
                    mRewardMemo);
            WLog.w("SimpleRewardTask tosign : " +  WUtil.getPresentor().toJson(tosign));

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

            StdTx signedTx = MsgGenerator.genSignedTransferTx(msgs, mRewardFees, mRewardMemo, signatures);
            signedTx.value.signatures = signatures;
            WLog.w("SimpleRewardTask signed1 : " +  WUtil.getPresentor().toJson(signedTx));

            String gentx = WUtil.str2Hex(WUtil.getPresentor().toJson(signedTx));
            WLog.w("SimpleRewardTask gentx : " +  gentx);
            Response<ResBroadTx> response = ApiClient.getCSService(mApp).broadcastTx(gentx).execute();
            if(response.isSuccessful() && response.body() != null) {
                ResBroadTx result = response.body();
                WLog.w("SimpleRewardTask result errorMsg : " + result.errorMsg);
                WLog.w("SimpleRewardTask result errorCode : " + result.errorCode);
                WLog.w("SimpleRewardTask result hash : " + result.hash);
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
