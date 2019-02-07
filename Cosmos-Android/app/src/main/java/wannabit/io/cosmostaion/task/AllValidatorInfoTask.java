package wannabit.io.cosmostaion.task;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.utils.WLog;

public class AllValidatorInfoTask extends CommonTask {

    public AllValidatorInfoTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_ALL_VALIDATOR;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<Validator>> response = ApiClient.getWannabitChain(mApp).getValidatorDetailList().execute();
            if(response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                ArrayList<Validator> mValidators = response.body();
                WLog.w("mValidators : " + mValidators.size());
            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.w("AllValidatorInfo Error " + e.getMessage());
        }

        return mResult;
    }
}
