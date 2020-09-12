package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaSupply;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaTotalSupplyTask extends CommonTask {

    private BaseChain mChain;

    public KavaTotalSupplyTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_KAVA_TOTAL_SUPPLY;
        this.mChain = chain;

    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResKavaSupply> response = ApiClient.getKavaChain(mApp).getSupply().execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaTotalSupplyTask : NOk");
                }

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResKavaSupply> response = ApiClient.getKavaTestChain(mApp).getSupply().execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaTotalSupplyTask : NOk");
                }
            }

        } catch (Exception e) {
            WLog.w("KavaTotalSupplyTask Error " + e.getMessage());
        }
        return mResult;
    }
}