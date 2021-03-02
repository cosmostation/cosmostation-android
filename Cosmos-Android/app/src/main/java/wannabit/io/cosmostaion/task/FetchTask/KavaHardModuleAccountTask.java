package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaHardModuleAccount;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_MODULE_ACCOUNT;

public class KavaHardModuleAccountTask extends CommonTask {
    private BaseChain mChain;

    public KavaHardModuleAccountTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType = TASK_FETCH_KAVA_HARD_MODULE_ACCOUNT;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResKavaHardModuleAccount> response = ApiClient.getKavaChain(mApp).getHardModuleAccount().execute();
                if(response.isSuccessful() && response.body() != null &&
                        response.body().result != null && response.body().result.size() > 0 && response.body().result.get(0).value != null &&
                        response.body().result.get(0).value.coins != null ) {
                    mResult.resultData = response.body().result.get(0).value.coins;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaHardModuleAccountTask : NOk");
                }

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResKavaHardModuleAccount> response = ApiClient.getKavaTestChain(mApp).getHardModuleAccount().execute();
                if(response.isSuccessful() && response.body() != null &&
                        response.body().result != null && response.body().result.size() > 0 && response.body().result.get(0).value != null &&
                        response.body().result.get(0).value.coins != null ) {
                    mResult.resultData = response.body().result.get(0).value.coins;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaHardModuleAccountTask : NOk");
                }
            }

        } catch (Exception e) {
            WLog.w("KavaHardModuleAccountTask Error " + e.getMessage());
        }
        return mResult;
    }
}
