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
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_BROADCAST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_RENEW_ACCOUNT;

public class SimpleRenewAccountTask extends CommonTask {

    private Account     mAccount;
    private BaseChain   mBaseChain;
    private String      mDomain, mName, mMemo;
    private Fee         mFees;

    public SimpleRenewAccountTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String domain, String name, String memo, Fee fee) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mDomain = domain;
        this.mName = name;
        this.mMemo = memo;
        this.mFees = fee;
        this.mResult.taskType = TASK_GEN_TX_RENEW_ACCOUNT;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Password checkPw = mApp.getBaseDao().onSelectPassword();
            if(!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
                return mResult;
            }

            if (mBaseChain.equals(IOV_MAIN)) {
                Response<ResLcdAccountInfo> accountResponse = ApiClient.getIovChain(mApp).getAccountInfo(mAccount.address).execute();
                if (!accountResponse.isSuccessful()) {
                    mResult.errorCode = ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromLcd(mAccount.id, accountResponse.body()));
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromLcd(mAccount.id, accountResponse.body()));
                mAccount = mApp.getBaseDao().onSelectAccount(""+mAccount.id);

            } else if (mBaseChain.equals(IOV_TEST)) {
                Response<ResLcdAccountInfo> accountResponse = ApiClient.getIovTestChain(mApp).getAccountInfo(mAccount.address).execute();
                if (!accountResponse.isSuccessful()) {
                    mResult.errorCode = ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromLcd(mAccount.id, accountResponse.body()));
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromLcd(mAccount.id, accountResponse.body()));
                mAccount = mApp.getBaseDao().onSelectAccount(""+mAccount.id);

            }

            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mAccount.baseChain), entropy, Integer.parseInt(mAccount.path), mAccount.newBip44);

            Msg renewAccountMsg = MsgGenerator.genAccountRenew(mDomain, mName, mAccount.address, mBaseChain);
            ArrayList<Msg> msgs= new ArrayList<>();
            msgs.add(renewAccountMsg);

            if (mBaseChain.equals(IOV_MAIN)) {
                ReqBroadCast reqBroadCast = MsgGenerator.getBraodcaseReq(mAccount, msgs, mFees, mMemo, deterministicKey);
                Response<ResBroadTx> response = ApiClient.getIovChain(mApp).broadTx(reqBroadCast).execute();
                if(response.isSuccessful() && response.body() != null) {
                    if (response.body().txhash != null) {
                        mResult.resultData = response.body().txhash;
                    }
                    if(response.body().code != null) {
                        mResult.errorCode = response.body().code;
                        mResult.errorMsg = response.body().raw_log;
                        return mResult;
                    }
                    mResult.isSuccess = true;

                } else {
                    mResult.errorCode = ERROR_CODE_BROADCAST;
                }

            } else if (mBaseChain.equals(IOV_TEST)) {
                ReqBroadCast reqBroadCast = MsgGenerator.getBraodcaseReq(mAccount, msgs, mFees, mMemo, deterministicKey);
                Response<ResBroadTx> response = ApiClient.getIovTestChain(mApp).broadTx(reqBroadCast).execute();
                if(response.isSuccessful() && response.body() != null) {
                    if (response.body().txhash != null) {
                        mResult.resultData = response.body().txhash;
                    }
                    if(response.body().code != null) {
                        mResult.errorCode = response.body().code;
                        mResult.errorMsg = response.body().raw_log;
                        return mResult;
                    }
                    mResult.isSuccess = true;

                } else {
                    mResult.errorCode = ERROR_CODE_BROADCAST;
                }
            }

        } catch (Exception e) {
            if(BaseConstant.IS_SHOWLOG) e.printStackTrace();
        }

        return mResult;
    }
}
