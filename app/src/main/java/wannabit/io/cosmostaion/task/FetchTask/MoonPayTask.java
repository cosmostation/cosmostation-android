package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqMoonPayKey;
import wannabit.io.cosmostaion.network.res.ResMoonPaySignature;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

public class MoonPayTask extends CommonTask {

    private String mQuery;

    public MoonPayTask(BaseApplication app, TaskListener listener, String query) {
        super(app, listener);
        this.mQuery = query;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ReqMoonPayKey reqMoonPayKey = new ReqMoonPayKey();
            reqMoonPayKey.api_key = mQuery;
            Response<ResMoonPaySignature> response = ApiClient.getCosmostationOld(mApp).getMoonPay(reqMoonPayKey).execute();
            if (response.isSuccessful() && response.body() != null && response.body().signature != null) {
                mResult.isSuccess = true;
                mResult.resultData = response.body().signature;
            }

        } catch (Exception e) {
            if(BuildConfig.DEBUG) e.printStackTrace();

        }
        return mResult;
    }
}