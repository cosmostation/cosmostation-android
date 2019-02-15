package wannabit.io.cosmostaion.task;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.TotalReward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.ApiClient;
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
                Response<ArrayList<Coin>> response = ApiClient.getWannabitChain(mApp).getTotalRewards(account.address).execute();
                if(response.isSuccessful() && response.body() != null && response.body().size() > 0) {
//                    mTotalRewards.put()
                    TotalReward totalReward = new TotalReward(account.id, response.body());
                    mTotalRewards.put(account.id, totalReward);
                }
            }
            mResult.resultData = mTotalRewards;
            mResult.isSuccess = true;


//            Response<ArrayList<Coin>> response = ApiClient.getWannabitChain(mApp).getTotalRewards(mAccount.address).execute();
//            if(!response.isSuccessful()) {
//                mResult.isSuccess = false;
//                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
//                return mResult;
//            }
//
//            if(response.body() != null && response.body().size() > 0) {
//                ArrayList<Coin> rewards = response.body();
////                WLog.w("TotalRewardTask Coin : " + rewards.size());
//                mResult.resultData = response.body();
//                mResult.isSuccess = true;
//            }

        } catch (Exception e) {
            WLog.w("TotalRewardTask Error " + e.getMessage());
        }

        return mResult;
    }
}
