package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.HistoryApi;
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class ApiStakeTxsHistoryTask extends CommonTask {

    private final String address;
    private final String valOpAddress;
    private final BaseChain chain;

    public ApiStakeTxsHistoryTask(BaseApplication app, TaskListener listener, String address, String vAddress, BaseChain chain) {
        super(app, listener);
        this.result.taskType = BaseConstant.TASK_FETCH_API_STAKE_HISTORY;
        this.address = address;
        this.valOpAddress = vAddress;
        this.chain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            HistoryApi historyApi = ApiClient.getChainHistoryApi(chain);

            if (historyApi != null) {
                Response<ArrayList<ResApiNewTxListCustom>> response = historyApi
                        .getNewStakeTxsCustom(address, valOpAddress, "50")
                        .execute();
                if (response.isSuccessful() && response.body() != null) {
                    result.resultData = response.body();
                    result.isSuccess = true;
                } else {
                    try {
                        WLog.w("ApiStakeTxsHistoryTask : NOk => " + response.errorBody().string());
                    } catch (Exception ignore) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            WLog.w("ApiStakeTxsHistoryTask Error " + e.getMessage());
        }
        return result;
    }

}
