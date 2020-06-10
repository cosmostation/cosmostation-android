package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaMarketPriceTask extends CommonTask {

    private BaseChain mChain;
    private String mMarket;

    public KavaMarketPriceTask(BaseApplication app, TaskListener listener, BaseChain chain, String market) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_KAVA_TOKEN_PRICE;
        this.mChain = chain;
        this.mMarket = market;

    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResKavaMarketPrice> response = ApiClient.getKavaChain(mApp).getPrice(mMarket).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaMarketPriceTask : NOk");
                }

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResKavaMarketPrice> response = ApiClient.getKavaTestChain(mApp).getPrice(mMarket).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaMarketPriceTask : NOk");
                }
            }

        } catch (Exception e) {
            WLog.w("KavaMarketPriceTask Error " + e.getMessage());
        }
        return mResult;
    }
}