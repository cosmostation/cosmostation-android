package wannabit.io.cosmostaion.task;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.utils.WLog;

public class SingleValidatorInfoTask extends CommonTask {

    private String      mValidatorAddr;

    public SingleValidatorInfoTask(BaseApplication app, TaskListener listener, String validatorAddr) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_SINGLE_VALIDATOR;
        this.mValidatorAddr     = validatorAddr;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<Validator> response = ApiClient.getWannabitChain(mApp).getValidatorDetail(mValidatorAddr).execute();
            if(response.isSuccessful() && response.body() != null) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            }

        } catch (Exception e) {
            WLog.w("SingleValidatorInfoTask Error " + e.getMessage());
        }
        return mResult;
    }
}
