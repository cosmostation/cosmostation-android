package wannabit.io.cosmostaion.task.SingleFetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class SingleSelfBondingStateTask extends CommonTask {

    private String  mDelegateAddr;
    private String  mValidatorAddr;
    private BaseChain mChain;

    public SingleSelfBondingStateTask(BaseApplication app, TaskListener listener, String delegateAddr, String validatorAddr, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_SINGLE_SELF_BONDING;
        this.mDelegateAddr = delegateAddr;
        this.mValidatorAddr = validatorAddr;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResLcdBondings> response = ApiClient.getWannabitChain(mApp, mChain).getBonding(mDelegateAddr, mValidatorAddr).execute();
            if(response.isSuccessful() && response.body() != null) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            }


        } catch (Exception e) {
            WLog.w("SingleBondingStateTask Error " + e.getMessage());
        }
        return mResult;
    }
}
