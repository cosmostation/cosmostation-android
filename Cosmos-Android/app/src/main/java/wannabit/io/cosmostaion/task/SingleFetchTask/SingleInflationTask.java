package wannabit.io.cosmostaion.task.SingleFetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdInflation;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class SingleInflationTask extends CommonTask {
    private BaseChain mChain;

    public SingleInflationTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_INFLATION;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
//            Response<ResLcdInflation> response = ApiClient.getCosmosChain(mApp).getInflation().execute();
//            if(!response.isSuccessful()) {
//                mResult.isSuccess = false;
//                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
//                return mResult;
//            }
//
//            if(response.body() != null && response.body().result != null) {
//                mResult.resultData = response.body().result;
//                mResult.isSuccess = true;
//            }
            //TODO rollback cosmos-hub2
            Response<String> response = ApiClient.getCosmosChain(mApp).getInflationLegacy().execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                return mResult;
            }

            if(response.body() != null) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            }


        } catch (Exception e) {
            WLog.w("SingleInflationTask Error " + e.getMessage());
        }

        return mResult;
    }
}
