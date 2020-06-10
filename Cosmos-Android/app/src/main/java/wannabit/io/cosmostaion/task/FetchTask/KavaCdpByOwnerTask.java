package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResCdpOwnerStatus;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.IS_SHOWLOG;

public class KavaCdpByOwnerTask extends CommonTask {

    private BaseChain mChain;
    private String mAddress;
    private String mDenom;

    public KavaCdpByOwnerTask(BaseApplication app, TaskListener listener, BaseChain chain, String address, String denom) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_KAVA_CDP_OWENER;
        this.mChain = chain;
        this.mAddress = address;
        this.mDenom = denom;

    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResCdpOwnerStatus> response = ApiClient.getKavaChain(mApp).getCdpStatusByOwner(mAddress, mDenom).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.resultData2 = mDenom;
                    mResult.isSuccess = true;

                }

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResCdpOwnerStatus> response = ApiClient.getKavaTestChain(mApp).getCdpStatusByOwner(mAddress, mDenom).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.resultData2 = mDenom;
                    mResult.isSuccess = true;

                }
            }

        } catch (Exception e) {
            WLog.w("KavaCdpByOwnerTask Error " + e.getMessage());
        }
        return mResult;
    }
}