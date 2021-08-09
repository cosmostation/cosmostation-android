package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResNodeInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_NODE_INFO;

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
            if (mChain.equals(KAVA_MAIN)) {
                Response<ResNodeInfo> response = ApiClient.getKavaChain(mApp).getNodeInfo().execute();
                if (response.isSuccessful() && response.body() != null&& response.body().node_info != null) {
                    mResult.resultData = response.body().node_info;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("NodeInfoTask : NOk");
                }

            } else if (mChain.equals(BAND_MAIN)) {
                Response<ResNodeInfo> response =  ApiClient.getBandChain(mApp).getNodeInfo().execute();
                if(response.isSuccessful() && response.body() != null&& response.body().node_info != null) {
                    mResult.resultData = response.body().node_info;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("NodeInfoTask : NOk");
                }

            } else if (mChain.equals(CERTIK_MAIN)) {
                Response<ResNodeInfo> response =  ApiClient.getCertikChain(mApp).getNodeInfo().execute();
                if(response.isSuccessful() && response.body() != null&& response.body().node_info != null) {
                    mResult.resultData = response.body().node_info;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("NodeInfoTask : NOk");
                }

            } else if (mChain.equals(SECRET_MAIN)) {
                Response<ResNodeInfo> response =  ApiClient.getSecretChain(mApp).getNodeInfo().execute();
                if(response.isSuccessful() && response.body() != null&& response.body().node_info != null) {
                    mResult.resultData = response.body().node_info;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("NodeInfoTask : NOk");
                }

            } else if (mChain.equals(BNB_MAIN)) {
                Response<ResNodeInfo> response =  ApiClient.getBnbChain(mApp).getNodeInfo().execute();
                if(response.isSuccessful() && response.body() != null&& response.body().node_info != null) {
                    mResult.resultData = response.body().node_info;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("NodeInfoTask : NOk");
                }

            } else if (mChain.equals(OKEX_MAIN)) {
                Response<ResNodeInfo> response = ApiClient.getOkexChain(mApp).getNodeInfo().execute();
                if(response.isSuccessful() && response.body() != null&& response.body().node_info != null) {
                    mResult.resultData = response.body().node_info;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("NodeInfoTask : NOk");
                }

            } else if (mChain.equals(FETCHAI_MAIN)) {
                Response<ResNodeInfo> response = ApiClient.getFetchChain(mApp).getNodeInfo().execute();
                if(response.isSuccessful() && response.body() != null&& response.body().node_info != null) {
                    mResult.resultData = response.body().node_info;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("NodeInfoTask : NOk");
                }

            } else if (mChain.equals(KI_MAIN)) {
                Response<ResNodeInfo> response = ApiClient.getKiChain(mApp).getNodeInfo().execute();
                if(response.isSuccessful() && response.body() != null&& response.body().node_info != null) {
                    mResult.resultData = response.body().node_info;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("NodeInfoTask : NOk");
                }

            }

            else if (mChain.equals(KAVA_TEST)) {
                Response<ResNodeInfo> response = ApiClient.getKavaTestChain(mApp).getNodeInfo().execute();
                if(response.isSuccessful() && response.body() != null&& response.body().node_info != null) {
                    mResult.resultData = response.body().node_info;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("NodeInfoTask : NOk");
                }

            } else if (mChain.equals(CERTIK_TEST)) {
                Response<ResNodeInfo> response =  ApiClient.getCertikTestChain(mApp).getNodeInfo().execute();
                if(response.isSuccessful() && response.body() != null&& response.body().node_info != null) {
                    mResult.resultData = response.body().node_info;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("NodeInfoTask : NOk");
                }

            } else if (mChain.equals(BNB_TEST)) {
                Response<ResNodeInfo> response =  ApiClient.getBnbTestChain(mApp).getNodeInfo().execute();
                if(response.isSuccessful() && response.body() != null&& response.body().node_info != null) {
                    mResult.resultData = response.body().node_info;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("NodeInfoTask : NOk");
                }

            } else if (mChain.equals(OK_TEST)) {
                Response<ResNodeInfo> response =  ApiClient.getOkexChain(mApp).getNodeInfo().execute();
                if(response.isSuccessful() && response.body() != null&& response.body().node_info != null) {
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
