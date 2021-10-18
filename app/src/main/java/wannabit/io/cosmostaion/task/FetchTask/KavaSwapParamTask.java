package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaSwapParam;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaSwapParamTask extends CommonTask {
    private BaseChain mChain;

    public KavaSwapParamTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_KAVA_SWAP_PARAM;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {

            WLog.w("KavaSwapParamTask " + ApiClient.getKavaChain(mApp).getSwapParam().request().url());
            Response<ResKavaSwapParam> response = ApiClient.getKavaChain(mApp).getSwapParam().execute();
            if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                mResult.resultData = response.body().result;
                mResult.isSuccess = true;

            } else {
                WLog.w("KavaSwapParamTask : NOk");
            }

        } catch (Exception e) {
            WLog.w("KavaSwapParamTask Error " + e.getMessage());
        }
        return mResult;
    }
}
