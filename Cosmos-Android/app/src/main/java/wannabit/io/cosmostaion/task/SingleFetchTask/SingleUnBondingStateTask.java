package wannabit.io.cosmostaion.task.SingleFetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdSingleUnBonding;
import wannabit.io.cosmostaion.network.res.ResLcdUnBonding;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class SingleUnBondingStateTask extends CommonTask {

    private Account mAccount;
    private String  mValidatorAddr;

    public SingleUnBondingStateTask(BaseApplication app, TaskListener listener, Account account, String validatorAddr) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_SINGLE_UNBONDING;
        this.mAccount = account;
        this.mValidatorAddr = validatorAddr;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.COSMOS_MAIN)) {
                Response<ResLcdSingleUnBonding> response = ApiClient.getCosmosChain(mApp).getUnbonding(mAccount.address, mValidatorAddr).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcd(mApp, mAccount.id, response.body().result));
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.IRIS_MAIN)) {
                Response<ResLcdUnBonding> response = ApiClient.getIrisChain(mApp).getUnbonding(mAccount.address, mValidatorAddr).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcd(mApp, mAccount.id, response.body()));
                    mResult.isSuccess = true;
                }
            }


        } catch (Exception e) {
            WLog.w("SingleUnBondingStateTask Error " + e.getMessage());
        }
        return mResult;
    }
}
