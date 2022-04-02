package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_HDAC_TX_DETAIL;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.hdac.HdacTxInfo;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class HdacTxDetailTask extends CommonTask {

    private BaseChain baseChain;
    private String mTxHash;

    public HdacTxDetailTask(BaseApplication app, TaskListener listener, BaseChain baseChain, String txHash) {
        super(app, listener);
        this.baseChain = baseChain;
        this.mTxHash = txHash;
        this.result.taskType = TASK_HDAC_TX_DETAIL;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<HdacTxInfo> response;
            if (baseChain.equals(BaseChain.RIZON_TEST)) {
                response = ApiClient.getTestHdac(context).gethdacTxDetail(mTxHash).execute();
            } else {
                response = ApiClient.getMainHdac(context).gethdacTxDetail(mTxHash).execute();
            }
            if (response.isSuccessful() && response.body() != null) {
                result.isSuccess = true;
                result.resultData = response.body();
            }

        } catch (Exception e) {
            WLog.w("HdacNodeStatusTask Error " + e.getMessage());
        }
        return result;
    }
}