package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResHdacStatus;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_HDAC_NODE_INFO;

public class HdacNodeStatusTask extends CommonTask {

    public HdacNodeStatusTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType = TASK_HDAC_NODE_INFO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResHdacStatus> response = ApiClient.getHdac(mApp).getNodeInfo().execute();
            if (response.isSuccessful() && response.body().info != null) {
                mResult.isSuccess = true;
            }
        } catch (Exception e) {
            WLog.w("HdacNodeStatusTask Error " + e.getMessage());
        }
        return mResult;
    }
}
