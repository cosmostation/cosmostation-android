package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaPriceFeedParam;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaPriceFeedParamTask extends CommonTask {

    private BaseChain mChain;

    public KavaPriceFeedParamTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType = BaseConstant.TASK_FETCH_KAVA_PRICE_FEED_PARAM;
        this.mChain = chain;

    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                WLog.w("KavaPriceFeedParamTask : " + ApiClient.getKavaChain(mApp).getPriceParam().request().url());
                Response<ResKavaPriceFeedParam> response = ApiClient.getKavaChain(mApp).getPriceParam().execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result.markets;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaPriceFeedParamTask : NOk");
                }

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResKavaPriceFeedParam> response = ApiClient.getKavaTestChain(mApp).getPriceParam().execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result.markets;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaPriceFeedParamTask : NOk");
                }
            }

        } catch (Exception e) {
            WLog.w("KavaPriceFeedParamTask Error " + e.getMessage());
        }
        return mResult;
    }
}