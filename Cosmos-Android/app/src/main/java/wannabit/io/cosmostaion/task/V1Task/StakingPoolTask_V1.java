package wannabit.io.cosmostaion.task.V1Task;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResStakingPool_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_STAKING_POOL;

public class StakingPoolTask_V1 extends CommonTask {
    private Account mAccount;

    public StakingPoolTask_V1(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = TASK_V1_FETCH_STAKING_POOL;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_MAIN)) {
                Response<ResStakingPool_V1> response = ApiClient.getCosmosChain(mApp).getStakingPool().execute();
                if (response.isSuccessful() && response.body().pool != null) {
                    mResult.resultData = response.body().pool;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_TEST)) {
                Response<ResStakingPool_V1> response = ApiClient.getCosmosTestChain(mApp).getStakingPool().execute();
                if (response.isSuccessful() && response.body().pool != null) {
                    mResult.resultData = response.body().pool;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_TEST)) {
                Response<ResStakingPool_V1> response = ApiClient.getIrisTestChain(mApp).getStakingPool().execute();
                if (response.isSuccessful() && response.body().pool != null) {
                    mResult.resultData = response.body().pool;
                }

            }
            mResult.isSuccess = true;

        } catch (Exception e) { }
        return mResult;
    }
}