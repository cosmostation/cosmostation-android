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

    private final BaseChain mBaseChain;
    private final Account mAccount;

    public RizonSwapStatusTask(BaseApplication app, TaskListener listener, BaseChain baseChain, Account account) {
        super(app, listener);
        this.mBaseChain = baseChain;
        this.mAccount = account;
        this.result.taskType = TASK_RIZON_SWAP_STATUS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<RizonSwapStatus>> response;
            if (mBaseChain.equals(BaseChain.RIZON_TEST)) {
                response = ApiClient.getRizonSwapTestStatus(context).getSwapStatus(mAccount.address).execute();
            } else {
                response = ApiClient.getRizonSwapStatus(context).getSwapStatus(mAccount.address).execute();
            }
            if (!response.isSuccessful()) {
                result.isSuccess = false;
                result.errorCode = BaseConstant.ERROR_CODE_NETWORK;
            }

            if (response.isSuccessful() && response.body() != null) {
                result.isSuccess = true;
                result.resultData = response.body();
            }
        } catch (Exception e) {
            WLog.w("RizonSwapStatusTask Error " + e.getMessage());
        }
        return result;
    }
}
