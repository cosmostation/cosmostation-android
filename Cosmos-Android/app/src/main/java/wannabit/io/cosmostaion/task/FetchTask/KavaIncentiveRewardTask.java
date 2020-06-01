package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveReward;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaIncentiveRewardTask extends CommonTask {

    private BaseChain mChain;
    private Account mAccount;
    private String mDenom;

    public KavaIncentiveRewardTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account, String denom) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_KAVA_INCENTIVE_REWARD;
        this.mChain = chain;
        this.mAccount = account;
        this.mDenom = denom;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                //mainnet not yet!

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResKavaIncentiveReward> response = ApiClient.getKavaTestChain(mApp).getIncentive(mAccount.address, mDenom).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                } else {
                    WLog.w("KavaIncentiveRewardTask : NO");
                }
            }

        } catch (Exception e) {
            WLog.w("KavaIncentiveRewardTask Error " + e.getMessage());
        }
        return mResult;
    }
}
