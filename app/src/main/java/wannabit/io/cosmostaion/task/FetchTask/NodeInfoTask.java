package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_NODE_INFO;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResNodeInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class NodeInfoTask extends CommonTask {
    private BaseChain mChain;

    public NodeInfoTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_FETCH_NODE_INFO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BNB_MAIN)) {
                Response<ResNodeInfo> response = ApiClient.getBnbChain(mApp).getNodeInfo().execute();
                if (response.isSuccessful() && response.body() != null && response.body().node_info != null) {
                    mResult.resultData = response.body().node_info;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("NodeInfoTask : NOk");
                }

            } else if (mChain.equals(OKEX_MAIN)) {
                Response<ResNodeInfo> response = ApiClient.getOkexChain(mApp).getNodeInfo().execute();
                if (response.isSuccessful() && response.body() != null && response.body().node_info != null) {
                    mResult.resultData = response.body().node_info;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("NodeInfoTask : NOk");
                }

            }

        } catch (Exception e) {
            WLog.w("NodeInfoTask Error " + e.getMessage());
        }
        return mResult;
    }
}
