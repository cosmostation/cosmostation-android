package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_HDAC_BROAD_BURN;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqHdacBurn;
import wannabit.io.cosmostaion.network.res.ResHdacBurn;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class HdacBurnTask extends CommonTask {

    private BaseChain baseChain;
    private String mTxHex;

    public HdacBurnTask(BaseApplication app, TaskListener listener, BaseChain baseChain, String txHex) {
        super(app, listener);
        this.baseChain = baseChain;
        this.mTxHex = txHex;
        this.result.taskType = TASK_HDAC_BROAD_BURN;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ReqHdacBurn req = new ReqHdacBurn(mTxHex);
            Response<ResHdacBurn> response;
            if (baseChain.equals(BaseChain.RIZON_TEST)) {
                response = ApiClient.getTestHdac(context).broadTx(req).execute();
            } else {
                response = ApiClient.getMainHdac(context).broadTx(req).execute();
            }
            if (response.isSuccessful() && response.body() != null) {
                result.isSuccess = true;
                result.resultData = response.body().txid;
            }
        } catch (Exception e) {
            WLog.w("HdacBurnTask Error " + e.getMessage());
        }
        return result;
    }

}
