package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.SifIncentive;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;


public class SifVsIncentiveTask extends CommonTask {

    private Account mAccount;

    public SifVsIncentiveTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_SIF_INCENTIVE_VS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<SifIncentive> response = ApiClient.getSifIncentiveApi(mApp).getVsIncentive("userData", mAccount.address, "now").execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null && response.body().user != null) {
                mResult.resultData = response.body().user;
            }
            mResult.isSuccess = true;
        } catch (Exception e) {
            WLog.w("SifVsIncentiveTask Error " + e.getMessage());
        }
        return mResult;
    }
}
