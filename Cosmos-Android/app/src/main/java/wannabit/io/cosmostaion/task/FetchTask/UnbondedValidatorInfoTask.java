package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class UnbondedValidatorInfoTask extends CommonTask {
    private BaseChain mChain;

    public UnbondedValidatorInfoTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_UNBONDED_VALIDATOR;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<Validator>> response = ApiClient.getCosmosChain(mApp).getUnBondedValidatorDetailList().execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                return mResult;
            }

            if(response.body() != null && response.body().size() > 0) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            }


        } catch (Exception e) {
            WLog.w("UnbondedValidatorInfo Error " + e.getMessage());
        }

        return mResult;
    }
}
