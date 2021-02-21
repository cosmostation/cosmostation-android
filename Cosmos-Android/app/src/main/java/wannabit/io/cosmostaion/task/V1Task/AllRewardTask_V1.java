package wannabit.io.cosmostaion.task.V1Task;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.Reward_V1;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResAllReward_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_ALL_REWARDS;

public class AllRewardTask_V1 extends CommonTask {
    private Account                     mAccount;
    private int                         mOffset = 0;
    private boolean                     mBreak = false;
    private ArrayList<Reward_V1>        mResultData = new ArrayList<>();

    public AllRewardTask_V1(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = TASK_V1_FETCH_ALL_REWARDS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        while (!mBreak) {
            ArrayList<Reward_V1> temp = onDoingJob(mOffset);
            mResultData.addAll(temp);
            if (temp.size() == 100) {
                mOffset = mOffset + 100;
            } else {
                mBreak = true;
            }
        }
        mResult.resultData = mResultData;
        mResult.isSuccess = true;
        return mResult;
    }

    private ArrayList<Reward_V1> onDoingJob(int offset) {
        ArrayList<Reward_V1> resultData = new ArrayList<>();
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_MAIN)) {
                Response<ResAllReward_V1> response = ApiClient.getCosmosChain(mApp).getAllRewards(mAccount.address, 100,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().rewards != null) {
                        resultData = response.body().rewards;
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_TEST)) {
                Response<ResAllReward_V1> response = ApiClient.getCosmosTestChain(mApp).getAllRewards(mAccount.address, 100,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().rewards != null) {
                        resultData = response.body().rewards;
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_TEST)) {
                Response<ResAllReward_V1> response = ApiClient.getIrisTestChain(mApp).getAllRewards(mAccount.address, 100,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().rewards != null) {
                        resultData = response.body().rewards;
                    }
                }
            }

        } catch (Exception e) { }
        return resultData;
    }
}
