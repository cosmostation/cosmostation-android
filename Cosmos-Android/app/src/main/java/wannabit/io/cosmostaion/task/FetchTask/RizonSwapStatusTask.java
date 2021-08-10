package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.RizonSwapStatus;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_RIZON_SWAP_STATUS;

public class RizonSwapStatusTask extends CommonTask {

    private Account mAccount;

    public RizonSwapStatusTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = TASK_RIZON_SWAP_STATUS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            WLog.w("swap status URL " +  ApiClient.getRizonSwapTestStatus(mApp).getSwapStatus(mAccount.address).request().url());
            Response<ArrayList<RizonSwapStatus>> response = ApiClient.getRizonSwapTestStatus(mApp).getSwapStatus(mAccount.address).execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null) {
                mResult.resultData = response.body();
            }
        } catch (Exception e) {
            WLog.w("RizonSwapStatusTask Error " + e.getMessage());
        }
        return mResult;
    }
}
