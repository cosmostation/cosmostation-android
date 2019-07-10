package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.TotalReward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class TotalRewardTask extends CommonTask {

    private ArrayList<Account> mAccounts;

    public TotalRewardTask(BaseApplication app, TaskListener listener, ArrayList<Account> accounts) {
        super(app, listener);
        this.mAccounts          = accounts;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_TOTAL_REWARDS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        HashMap<Long, TotalReward>       mTotalRewards = new HashMap<>();
        try {
            for(Account account : mAccounts) {
                Response<ArrayList<Coin>> response = ApiClient.getCosmosChain(mApp).getTotalRewards(account.address).execute();
                if(response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    TotalReward totalReward = new TotalReward(account.id, response.body());
                    mTotalRewards.put(account.id, totalReward);
                }
            }
            mResult.resultData = mTotalRewards;
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.w("TotalRewardTask Error " + e.getMessage());
        }

        return mResult;
    }
}
