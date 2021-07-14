package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBandOracleStatus;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;

public class BandOracleStatusTask extends CommonTask {
    private BaseChain mChain;

    public BandOracleStatusTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_BAND_ORACLE_STATUS;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BAND_MAIN)) {
                Response<ResBandOracleStatus> response = ApiClient.getBandChain(mApp).getOracleStatus().execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }
                if(response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }
            }

        } catch (Exception e) {
            WLog.w("BandOracleStatusTask Error " + e.getMessage());
        }

        return mResult;
    }
}

