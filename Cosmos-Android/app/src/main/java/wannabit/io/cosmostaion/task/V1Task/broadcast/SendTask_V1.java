package wannabit.io.cosmostaion.task.V1Task.broadcast;

import org.bitcoinj.crypto.DeterministicKey;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.model.Account_V1;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResAuth_V1;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_INVALID_PASSWORD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_BROAD_SEND;

public class SendTask_V1 extends CommonTask {

    private Account         mAccount;
    private String          mToAddress;
    private ArrayList<Coin> mAmount;
    private String          mMemo;
    private Fee             mFees;

    public SendTask_V1(BaseApplication app, TaskListener listener, Account account,
                          String toAddress, ArrayList<Coin> toSendAmount, String toSendMemo,
                          Fee toFees) {
        super(app, listener);
        this.mAccount           = account;
        this.mToAddress         = toAddress;
        this.mAmount            = toSendAmount;
        this.mMemo              = toSendMemo;
        this.mFees              = toFees;
        this.mResult.taskType   = TASK_V1_BROAD_SEND;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        Password checkPw = mApp.getBaseDao().onSelectPassword();
        if (!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {
            mResult.isSuccess = false;
            mResult.errorCode = ERROR_CODE_INVALID_PASSWORD;
            return mResult;

        }

        Account_V1 auth = onCheckAuth(mAccount.address);
        if (auth != null) {
            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(getChain(mAccount.baseChain), entropy, Integer.parseInt(mAccount.path), mAccount.newBip44);
            ReqBroadCast reqBroadCast = Signer.genSignedSendTxV1(mAccount.address, auth.account_number, auth.sequence, mToAddress, mAmount, mFees, mMemo, deterministicKey, getChain(mAccount.baseChain));
//            WLog.w("reqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));

            Response<ResBroadTx> response = onBroadTx(reqBroadCast);
            if (response.isSuccessful() && response.body() != null) {
                if (response.body().txhash != null) {
                    mResult.resultData = response.body().txhash;
                }
                if (response.body().code != null) {
                    mResult.errorCode = response.body().code;
                    mResult.errorMsg = response.body().raw_log;
                    return mResult;
                }
                mResult.isSuccess = true;

            } else {
                WLog.e("SendTask_V1 Error");
            }
        }
        return mResult;
    }



    private Account_V1 onCheckAuth(String address) {
        try {
            if (getChain(mAccount.baseChain).equals(COSMOS_MAIN)) {
                Response<ResAuth_V1> response = ApiClient.getCosmosChain(mApp).getAuth(address).execute();
                if (response.isSuccessful()) {
                    return response.body().account;
                }

            } else if (getChain(mAccount.baseChain).equals(COSMOS_TEST)) {
                Response<ResAuth_V1> response = ApiClient.getCosmosTestChain(mApp).getAuth(address).execute();
                if (response.isSuccessful()) {
                    return response.body().account;
                }

            } else if (getChain(mAccount.baseChain).equals(IRIS_TEST)) {
                Response<ResAuth_V1> response = ApiClient.getIrisTestChain(mApp).getAuth(address).execute();
                if (response.isSuccessful()) {
                    return response.body().account;
                }
            }

        } catch (Exception e) {
            WLog.e("onCheckAuth "  +e.getMessage());
            return null;
        }
        return null;
    }

    private Response<ResBroadTx> onBroadTx(ReqBroadCast reqBroadCast) {
        try {
            if (getChain(mAccount.baseChain).equals(COSMOS_MAIN)) {
                return ApiClient.getCosmosChain(mApp).broadTx(reqBroadCast).execute();

            } else if (getChain(mAccount.baseChain).equals(COSMOS_TEST)) {
                return ApiClient.getCosmosTestChain(mApp).broadTx(reqBroadCast).execute();

            } else if (getChain(mAccount.baseChain).equals(IRIS_TEST)) {
                return ApiClient.getIrisTestChain(mApp).broadTx(reqBroadCast).execute();
            }

        } catch (Exception e) {
            WLog.e("onBroadTx "  +e.getMessage());
            return null;
        }
        return null;
    }
}
