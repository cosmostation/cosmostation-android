package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaSwapDeposit;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaSwapDepositTask extends CommonTask {
    private BaseChain mChain;
    private Account mAccount;

    public KavaSwapDepositTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_KAVA_SWAP_DEPOSIT;
        this.mChain = chain;
        this.mAccount = account;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {

            WLog.w("KavaSwapDepositTask " + ApiClient.getKavaChain(mApp).getMySwapDeposit(mAccount.address).request().url());
            Response<ResKavaSwapDeposit> response = ApiClient.getKavaChain(mApp).getMySwapDeposit(mAccount.address).execute();
            if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                mResult.resultData = response.body().result;
                mResult.isSuccess = true;

            } else {
                WLog.w("KavaSwapDepositTask : NOk");
            }

        } catch (Exception e) {
            WLog.w("KavaSwapDepositTask Error " + e.getMessage());
        }
        return mResult;
    }
}
