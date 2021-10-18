package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaHardTotalDeposit;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_TOTAL_DEPOIST;

public class KavaHardTotalDepositTask extends CommonTask {
    private BaseChain mChain;

    public KavaHardTotalDepositTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType = TASK_FETCH_KAVA_HARD_TOTAL_DEPOIST;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResKavaHardTotalDeposit> response = ApiClient.getKavaChain(mApp).getHardTotalDeposit().execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaHardTotalDepositTask : NOk");
                }

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResKavaHardTotalDeposit> response = ApiClient.getKavaTestChain(mApp).getHardTotalDeposit().execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaHardTotalDepositTask : NOk");
                }
            }

        } catch (Exception e) {
            WLog.w("KavaHardTotalDepositTask Error " + e.getMessage());
        }
        return mResult;
    }
}