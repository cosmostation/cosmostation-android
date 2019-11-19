package wannabit.io.cosmostaion.task.SingleFetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdWithDrawAddress;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class CheckWithdrawAddressTask extends CommonTask {

    private Account mAccount;

    public CheckWithdrawAddressTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_WITHDRAW_ADDRESS;
        this.mAccount = account;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.COSMOS_MAIN)) {
//                Response<ResLcdWithDrawAddress> response = ApiClient.getCosmosChain(mApp).getWithdrawAddress(mAccount.address).execute();
//                if(!response.isSuccessful()) {
//                    mResult.isSuccess = false;
//                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
//                    return mResult;
//                }
//
//                if(response.body() != null && response.body().result != null) {
//                    mResult.resultData = response.body().result;
//                    mResult.isSuccess = true;
//                }
                //TODO rollback cosmos-hub2
                Response<String> response = ApiClient.getCosmosChain(mApp).getWithdrawAddressLegacy(mAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }


            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.IRIS_MAIN)) {
                Response<String> response = ApiClient.getIrisChain(mApp).getWithdrawAddress(mAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.KAVA_MAIN)) {
                Response<ResLcdWithDrawAddress> response = ApiClient.getKavaChain(mApp).getWithdrawAddress(mAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }
            }



        } catch (Exception e) {
            WLog.w("CheckWithdrawAddressTask Error " + e.getMessage());
        }

        return mResult;
    }
}
