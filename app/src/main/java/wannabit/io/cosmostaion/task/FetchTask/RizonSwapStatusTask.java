package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_RIZON_SWAP_STATUS;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.RizonSwapStatus;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class RizonSwapStatusTask extends CommonTask {

    private BaseChain mBaseChain;
    private Account mAccount;

    public RizonSwapStatusTask(BaseApplication app, TaskListener listener, BaseChain baseChain, Account account) {
        super(app, listener);
        this.mBaseChain = baseChain;
        this.mAccount = account;
        this.mResult.taskType = TASK_RIZON_SWAP_STATUS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<RizonSwapStatus>> response;
            if (mBaseChain.equals(BaseChain.RIZON_TEST)) {
                response = ApiClient.getRizonSwapTestStatus(mApp).getSwapStatus(mAccount.address).execute();
            } else {
                response = ApiClient.getRizonSwapStatus(mApp).getSwapStatus(mAccount.address).execute();
            }
            if (!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
            }

            if (response.isSuccessful() && response.body() != null) {
                mResult.isSuccess = true;
                mResult.resultData = response.body();
            }
        } catch (Exception e) {
            WLog.w("RizonSwapStatusTask Error " + e.getMessage());
        }
        return mResult;
    }
}
