package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaHardInterestRate;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_INTEREST_RATE;

public class KavaHardInterestRateTask extends CommonTask {
    private BaseChain mChain;

    public KavaHardInterestRateTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType = TASK_FETCH_KAVA_HARD_INTEREST_RATE;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResKavaHardInterestRate> response = ApiClient.getKavaChain(mApp).getHardInterestRate().execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaHardInterestRateTask : NOk");
                }

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResKavaHardInterestRate> response = ApiClient.getKavaTestChain(mApp).getHardInterestRate().execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaHardInterestRateTask : NOk");
                }
            }

        } catch (Exception e) {
            WLog.w("KavaHardInterestRateTask Error " + e.getMessage());
        }
        return mResult;
    }
}
