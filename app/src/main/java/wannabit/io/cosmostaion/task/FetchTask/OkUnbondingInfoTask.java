package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResOkUnbonding;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class OkUnbondingInfoTask extends CommonTask {

    private BaseChain mChain;
    private Account mAccount;

    public OkUnbondingInfoTask(BaseApplication app, TaskListener listener, Account account, BaseChain chain) {
        super(app, listener);
        this.mAccount = account;
        this.mChain = chain;
        this.result.taskType = BaseConstant.TASK_FETCH_OK_UNBONDING_INFO;

    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.OKEX_MAIN)) {
                Response<ResOkUnbonding> response = ApiClient.getOkexChain(context).getWithdrawInfo(mAccount.address).execute();
                if (!response.isSuccessful()) {
                    result.isSuccess = false;
                    result.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return result;
                }

                if (response.body() != null) {
                    result.resultData = response.body();
                    result.isSuccess = true;
                }

            }
            result.isSuccess = true;

        } catch (Exception e) {
            WLog.w("OkWithdrawTask Error " + e.getMessage());

        }
        return result;
    }

}