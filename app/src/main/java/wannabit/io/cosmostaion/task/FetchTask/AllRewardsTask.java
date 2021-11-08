package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdAllRewards;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_ALL_REWARDS;

public class AllRewardsTask extends CommonTask {

    private Account mAccount;

    public AllRewardsTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount = account;
        this.mResult.taskType = TASK_FETCH_ALL_REWARDS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(KAVA_MAIN)) {
                Response<ResLcdAllRewards> response = ApiClient.getKavaChain(mApp).getAllRewards(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result.rewards;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(SECRET_MAIN)) {
                Response<ResLcdAllRewards> response = ApiClient.getSecretChain(mApp).getAllRewards(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result.rewards;
                    mResult.isSuccess = true;
                }

            }

        } catch (Exception e) {
            WLog.w("AllRewardsTask Error " + e.getMessage());

        }
        return mResult;
    }
}

