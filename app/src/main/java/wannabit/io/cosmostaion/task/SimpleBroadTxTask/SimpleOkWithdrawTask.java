package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_BROADCAST;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResOkAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class SimpleOkWithdrawTask extends CommonTask {

    private Account     mAccount;
    private Coin        mDepositCoin;
    private String      mMemo;
    private Fee         mFees;

    public SimpleOkWithdrawTask(BaseApplication app, TaskListener listener, Account account, Coin coin, String memo, Fee fees) {
        super(app, listener);
        this.mAccount = account;
        this.mDepositCoin = coin;
        this.mMemo = memo;
        this.mFees = fees;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResOkAccountInfo> accountResponse = ApiClient.getOkexChain().getAccountInfo(mAccount.address).execute();
            if (!accountResponse.isSuccessful()) {
                mResult.errorCode = ERROR_CODE_BROADCAST;
                return mResult;
            }
            mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromOkLcd(mAccount.id, accountResponse.body()));
            mApp.getBaseDao().mOkAccountInfo = accountResponse.body();

            Msg withdrawMsg = MsgGenerator.genOkWithdraw(mAccount.address, mDepositCoin);
            ArrayList<Msg> msgs= new ArrayList<>();
            msgs.add(withdrawMsg);
            ReqBroadCast reqBroadCast = MsgGenerator.getOKexBroadcaseReq(mAccount, msgs, mFees, mMemo, WKey.getECKey(mApp, mAccount), mApp.getBaseDao().getChainId());
            Response<ResBroadTx> response = ApiClient.getOkexChain().broadTx(reqBroadCast).execute();
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
                mResult.errorCode = ERROR_CODE_BROADCAST;
            }

        } catch (Exception e) {
            if(BuildConfig.DEBUG) e.printStackTrace();
        }

        return mResult;
    }
}
