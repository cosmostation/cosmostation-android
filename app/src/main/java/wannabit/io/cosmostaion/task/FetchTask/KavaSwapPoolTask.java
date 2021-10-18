package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaSwapPool;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaSwapPoolTask extends CommonTask {
    private BaseChain mChain;

    public KavaSwapPoolTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_KAVA_SWAP_POOL;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {

            WLog.w("KavaSwapPoolTask " + ApiClient.getKavaChain(mApp).getSwapPool().request().url());
            Response<ResKavaSwapPool> response = ApiClient.getKavaChain(mApp).getSwapPool().execute();
            if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                mResult.resultData = response.body().result;
                mResult.isSuccess = true;

            } else {
                WLog.w("KavaSwapPoolTask : NOk");
            }

        } catch (Exception e) {
            WLog.w("KavaSwapPoolTask Error " + e.getMessage());
        }
        return mResult;
    }
}
