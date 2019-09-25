package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdValidators;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class UnbondingValidatorInfoTask extends CommonTask {
    private BaseChain mChain;

    public UnbondingValidatorInfoTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_UNBONDING_VALIDATOR;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
//            Response<ResLcdValidators> response = ApiClient.getCosmosChain(mApp).getUnBondingValidatorDetailList().execute();
//            if(!response.isSuccessful()) {
//                mResult.isSuccess = false;
//                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
//                return mResult;
//            }
//
//            if(response.body() != null && response.body().result != null && response.body().result.size() > 0) {
//                mResult.resultData = response.body().result;
//                mResult.isSuccess = true;
//            }

            //TODO rollback cosmos-hub2
            Response<ArrayList<Validator>> response = ApiClient.getCosmosChain(mApp).getUnBondingValidatorDetailListLegacy().execute();
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
            WLog.w("UnbondingValidatorInfo Error " + e.getMessage());
        }

        return mResult;
    }
}
