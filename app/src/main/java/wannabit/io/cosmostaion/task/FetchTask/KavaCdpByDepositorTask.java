package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResCdpDepositStatus;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaCdpByDepositorTask extends CommonTask {

    private String mAddress;
    private String mCollateralType;

    public KavaCdpByDepositorTask(BaseApplication app, TaskListener listener, String address, String collateralType) {
        super(app, listener);
        this.mResult.taskType = BaseConstant.TASK_FETCH_KAVA_CDP_DEPOSIT;
        this.mAddress = address;
        this.mCollateralType = collateralType;

    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResCdpDepositStatus> response = ApiClient.getKavaChain().getCdpDepositStatus(mAddress, mCollateralType).execute();
            if (response.isSuccessful() && response.body() != null && response.body().result != null) {
                mResult.resultData = response.body().result;
                mResult.isSuccess = true;

            } else {
                WLog.w("KavaCdpByDepositor : NOk");
            }

        } catch (Exception e) {
            WLog.w("KavaCdpByDepositor Error " + e.getMessage());
        }
        return mResult;
    }
}