package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResIovAddressInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class IovAddressInfoTask extends CommonTask {

    private Account mAccount;

    public IovAddressInfoTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_IOV_ADDRESS_INFO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResIovAddressInfo> response = ApiClient.getIovChain(mApp).getAddressInfo(mAccount.address).execute();
            if(response.isSuccessful()) {
                mResult.isSuccess = true;
                mResult.resultData = response.body();

            }

        } catch (Exception e) {
            WLog.w("IovNonceTask Error " + e.getMessage());
        }
        return mResult;
    }
}