package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_MODULE_ACCOUNT;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.kava.ResKavaModuleAccount;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaHardModuleAccountTask extends CommonTask {

    public KavaHardModuleAccountTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType = TASK_FETCH_KAVA_HARD_MODULE_ACCOUNT;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResKavaModuleAccount> response = ApiClient.getKavaChain().getHardModuleAccount().execute();
            if (response.isSuccessful() && response.body() != null) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            }
        } catch (Exception e) {
            WLog.w("KavaHardModuleAccountTask Error " + e.getMessage());
        }
        return mResult;
    }
}
