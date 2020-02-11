package wannabit.io.cosmostaion.task.SingleFetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdBonding;
import wannabit.io.cosmostaion.network.res.ResLcdSingleBonding;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

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
            if (mChain.equals(BaseChain.COSMOS_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getCosmosChain(mApp).getBonding(mDelegateAddr, mValidatorAddr).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.IRIS_MAIN)) {
                Response<ResLcdBonding> response = ApiClient.getIrisChain(mApp).getBonding(mDelegateAddr, mValidatorAddr).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }
            } else if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getKavaChain(mApp).getBonding(mDelegateAddr, mValidatorAddr).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }
            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResLcdSingleBonding> response = ApiClient.getKavaTestChain(mApp).getBonding(mDelegateAddr, mValidatorAddr).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }
            }

        } catch (Exception e) {
            WLog.w("SingleBondingStateTask Error " + e.getMessage());
        }
        return mResult;
    }
}
