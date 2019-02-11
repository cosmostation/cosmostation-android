package wannabit.io.cosmostaion.task;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.utils.WLog;

public class TotalRewardTask extends CommonTask {

    private Account mAccount;

    public TotalRewardTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount = account;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_TOTAL_REWARDS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<Coin>> response = ApiClient.getWannabitChain(mApp).getTotalRewards(mAccount.address).execute();
            if(response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                ArrayList<Coin> mValidators = response.body();
                WLog.w("TotalRewardTask Coin : " + mValidators.size());
            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.w("TotalRewardTask Error " + e.getMessage());
        }

        return mResult;
    }
}
