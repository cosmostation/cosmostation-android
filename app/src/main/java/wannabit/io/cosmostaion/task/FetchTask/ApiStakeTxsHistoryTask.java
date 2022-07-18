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

public class ApiStakeTxsHistoryTask extends CommonTask {

    private ChainConfig mChainConfig;
    private String mAddress;
    private String mValOpAddress;

    public ApiStakeTxsHistoryTask(BaseApplication app, TaskListener listener, ChainConfig chainConfig, String address, String vAddress) {
        super(app, listener);
        this.mChainConfig = chainConfig;
        this.mAddress = address;
        this.mValOpAddress = vAddress;
        this.mResult.taskType = BaseConstant.TASK_FETCH_API_STAKE_HISTORY;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<ResApiNewTxListCustom>> response = mChainConfig.getHistoryApi().getNewStakeTxsCustom(mAddress, mValOpAddress, "50").execute();
            if (response.isSuccessful() && response.body() != null) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            } else {
                WLog.w("ApiStakeTxsHistoryTask : NOk");
            }

        } catch (Exception e) {
            e.printStackTrace();
            WLog.w("ApiStakeTxsHistoryTask Error " + e.getMessage());
        }
        return mResult;
    }

}
