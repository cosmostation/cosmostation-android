package wannabit.io.cosmostaion.task.SingleFetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdSingleValidator;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class SingleValidatorInfoTask extends CommonTask {

    private String      mValidatorAddr;
    private BaseChain   mChain;

    public SingleValidatorInfoTask(BaseApplication app, TaskListener listener, String validatorAddr, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_SINGLE_VALIDATOR;
        this.mValidatorAddr     = validatorAddr;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.COSMOS_MAIN)) {
                Response<ResLcdSingleValidator> response = ApiClient.getCosmosChain(mApp).getValidatorDetail(mValidatorAddr).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.IRIS_MAIN)) {
                Response<Validator> response = ApiClient.getIrisChain(mApp).getValidatorDetail(mValidatorAddr).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }
            } else if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResLcdSingleValidator> response = ApiClient.getKavaChain(mApp).getValidatorDetail(mValidatorAddr).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResLcdSingleValidator> response = ApiClient.getKavaTestChain(mApp).getValidatorDetail(mValidatorAddr).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }
            }

        } catch (Exception e) {
            WLog.w("SingleValidatorInfoTask Error " + e.getMessage());
        }
        return mResult;
    }
}
