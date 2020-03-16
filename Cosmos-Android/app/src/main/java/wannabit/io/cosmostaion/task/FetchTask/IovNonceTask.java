package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResIovNonce;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class IovNonceTask extends CommonTask {

    private Account mAccount;

    public IovNonceTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_IOV_NONCE;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
//        try {
//            Response<ResIovNonce> response = ApiClient.getIovChain(mApp).getNonce(mAccount.address).execute();
//            if(response.isSuccessful()) {
//                mResult.isSuccess = true;
//                mResult.resultData = response.body().nonce;
//            }
//
//        } catch (Exception e) {
//            WLog.w("IovNonceTask Error " + e.getMessage());
//        }
        return mResult;
    }
}