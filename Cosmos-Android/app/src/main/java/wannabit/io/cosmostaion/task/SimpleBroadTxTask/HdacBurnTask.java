package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqHdacBurn;
import wannabit.io.cosmostaion.network.res.ResHdacBurn;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_HDAC_BROAD_BURN;

public class HdacBurnTask extends CommonTask {

    private String mTxHex;

    public HdacBurnTask(BaseApplication app, TaskListener listener, String txHex) {
        super(app, listener);
        this.mTxHex = txHex;
        this.mResult.taskType = TASK_HDAC_BROAD_BURN;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ReqHdacBurn req = new ReqHdacBurn(mTxHex);
            Response<ResHdacBurn> response = ApiClient.getHdac(mApp).broadTx(req).execute();
            if (response.isSuccessful() && response.body() != null) {
                mResult.isSuccess = true;
                mResult.resultData = response.body().txid;
            }
        } catch (Exception e) {
            WLog.w("HdacBurnTask Error " + e.getMessage());
        }
        return mResult;
    }

}
