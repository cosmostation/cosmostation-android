package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaHardMyDeposit;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_MY_DEPOSIT;

public class KavaHardMyDepositTask extends CommonTask {
    private BaseChain mChain;
    private String mOwner;

    public KavaHardMyDepositTask(BaseApplication app, TaskListener listener, BaseChain chain, String address) {
        super(app, listener);
        this.mResult.taskType = TASK_FETCH_KAVA_HARD_MY_DEPOSIT;
        this.mChain = chain;
        this.mOwner = address;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResKavaHardMyDeposit> response = ApiClient.getKavaChain(mApp).getHardMyDeposit(mOwner).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaHardMyDepositTask : NOk");
                }

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResKavaHardMyDeposit> response = ApiClient.getKavaTestChain(mApp).getHardMyDeposit(mOwner).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaHardMyDepositTask : NOk");
                }
            }

        } catch (Exception e) {
            WLog.w("KavaHardMyDepositTask Error " + e.getMessage());
        }
        return mResult;
    }
}