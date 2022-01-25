package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbFee;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class BnbFeesTask extends CommonTask {

    private BaseChain mBaseChain;

    public BnbFeesTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mBaseChain = chain;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_BNB_FEES;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
                Response<ArrayList<ResBnbFee>> response = ApiClient.getBnbChain(mApp).getFees().execute();
                if(response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    mResult.resultData = response.body();
                }

            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.w("BnbFeesTask Error " + e.getMessage());
        }
        return mResult;
    }
}