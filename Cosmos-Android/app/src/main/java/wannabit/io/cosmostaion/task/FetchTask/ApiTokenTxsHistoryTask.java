package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.network.res.ResApiTxListCustom;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class ApiTokenTxsHistoryTask extends CommonTask {

    private String mAddress;
    private String mDenom;
    private BaseChain mChain;

    public ApiTokenTxsHistoryTask(BaseApplication app, TaskListener listener, String address, String denom, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_API_TOKEN_HISTORY;
        this.mAddress = address;
        this.mDenom = denom;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.COSMOS_MAIN)) {
                Response<ArrayList<ResApiTxList.Data>> response = ApiClient.getCosmosApi(mApp).getTokenTxs(mAddress, mDenom).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("ApiTokenTxsHistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.BNB_MAIN)) {
            } else if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ArrayList<ResApiTxList.Data>> response = ApiClient.getKavaApi(mApp).getTokenTxs(mAddress, mDenom).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("ApiTokenTxsHistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ArrayList<ResApiTxList.Data>> response = ApiClient.getKavaTestApi(mApp).getTokenTxs(mAddress, mDenom).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("ApiTokenTxsHistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.SIF_MAIN)) {
                Response<ArrayList<ResApiTxList.Data>> response = ApiClient.getSifApi(mApp).getTokenTxs(mAddress, mDenom).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("ApiTokenTxsHistoryTask : NOk");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            WLog.w("ApiTokenTxsHistoryTask Error " + e.getMessage());
        }
        return mResult;
    }
}
