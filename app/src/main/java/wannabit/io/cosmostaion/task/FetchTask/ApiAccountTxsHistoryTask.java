package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class ApiAccountTxsHistoryTask extends CommonTask {

    private ChainConfig mChainConfig;
    private String mAddress;

    public ApiAccountTxsHistoryTask(BaseApplication app, TaskListener listener, ChainConfig chainConfig, String address) {
        super(app, listener);
        this.mChainConfig = chainConfig;
        this.mAddress = address;
        this.mResult.taskType = BaseConstant.TASK_FETCH_API_ADDRESS_HISTORY;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<ResApiNewTxListCustom>> response = mChainConfig.getHistoryApi().getNewAccountTxCustom(mAddress, "50").execute();
            if (response.isSuccessful() && response.body() != null) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            } else {
                WLog.w("HistoryTask : NOk");
            }

        } catch (Exception e) {
            e.printStackTrace();
            WLog.w("ApiAccountTxsHistoryTask Error " + e.getMessage());
        }
        return mResult;
    }
}
