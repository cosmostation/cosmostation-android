package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqStarNameDomainInfo;
import wannabit.io.cosmostaion.network.res.ResIovStarNameDomainInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_STARNAME_DOMAIN_INFO;

public class StarNameDomainInfoTask extends CommonTask {

    private BaseChain   mBaseChain;
    private String      mDomainName;

    public StarNameDomainInfoTask(BaseApplication app, TaskListener listener, BaseChain basecahin, String domainName) {
        super(app, listener);
        this.mBaseChain = basecahin;
        this.mDomainName = domainName;
        this.mResult.taskType = TASK_FETCH_STARNAME_DOMAIN_INFO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        ReqStarNameDomainInfo req = new ReqStarNameDomainInfo(mDomainName);
        try {
            if (mBaseChain.equals(BaseChain.IOV_MAIN)) {
                Response<ResIovStarNameDomainInfo> response = ApiClient.getIovChain(mApp).getStarnameDomainInfo(req).execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null && response.body().result.domain != null) {
                    mResult.resultData = response.body().result.domain;
                    mResult.isSuccess = true;
                }
            } else if (mBaseChain.equals(BaseChain.IOV_TEST)) {
                Response<ResIovStarNameDomainInfo> response = ApiClient.getIovTestChain(mApp).getStarnameDomainInfo(req).execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null && response.body().result.domain != null) {
                    mResult.resultData = response.body().result.domain;
                    mResult.isSuccess = true;
                }
            }

        } catch (Exception e) {
            WLog.w("StarNameDomainInfoTask Error " + e.getMessage());
        }

        return mResult;
    }
}
