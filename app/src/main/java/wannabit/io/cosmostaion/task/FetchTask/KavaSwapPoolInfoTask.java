package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaPoolInfo;
import wannabit.io.cosmostaion.network.res.ResKavaSwapPool;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaSwapPoolInfoTask extends CommonTask {

    private BaseChain mChain;
    private String mPoolId;

    public KavaSwapPoolInfoTask(BaseApplication app, TaskListener listener, BaseChain chain, String mPoolId) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_KAVA_SWAP_POOL_INFO;
        this.mChain = chain;
        this.mPoolId = mPoolId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {

            WLog.w("KavaSwapPoolInfoTask " + ApiClient.getKavaChain(mApp).getSwapPoolById(mPoolId).request().url());
            Response<ResKavaPoolInfo> response = ApiClient.getKavaChain(mApp).getSwapPoolById(mPoolId).execute();
            if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                mResult.resultData = response.body().result;
                mResult.isSuccess = true;

            } else {
                WLog.w("KavaSwapPoolInfoTask : NOk");
            }

        } catch (Exception e) {
            WLog.w("KavaSwapPoolInfoTask Error " + e.getMessage());
        }
        return mResult;
    }
}
