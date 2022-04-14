package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveReward;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaIncentiveRewardTask extends CommonTask {

    private final Account mAccount;

    public KavaIncentiveRewardTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.result.taskType = BaseConstant.TASK_FETCH_KAVA_INCENTIVE_REWARD;
        this.mAccount = account;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResKavaIncentiveReward> response = ApiClient.getKavaChain(context).getIncentiveReward5(mAccount.address).execute();
            if (response.isSuccessful() && response.body() != null && response.body().result != null) {
                result.resultData = response.body().result;
                result.isSuccess = true;
            }
        } catch (Exception e) {
            WLog.w("KavaIncentiveRewardTask Error " + e.getMessage());
        }
        return result;
    }
}
