package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResCdpOwnerStatus;
import wannabit.io.cosmostaion.network.res.ResKavaCdpParam;
import wannabit.io.cosmostaion.network.res.ResKavaMyCdps;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaCdpByOwnerTask extends CommonTask {

    private BaseChain mChain;
    private String mAddress;
    private ResKavaCdpParam.KavaCollateralParam mParam;

    public KavaCdpByOwnerTask(BaseApplication app, TaskListener listener, BaseChain chain, String address, ResKavaCdpParam.KavaCollateralParam param) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_KAVA_CDP_OWENER;
        this.mChain = chain;
        this.mAddress = address;
        this.mParam = param;

    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResCdpOwnerStatus> response = ApiClient.getKavaChain(mApp).getCdpStatusByOwner(mAddress, mParam.type).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.resultData2 = mParam.denom;
                    mResult.isSuccess = true;

                }

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResKavaMyCdps> response = ApiClient.getKavaTestChain(mApp).getMyCDPs(mAddress).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;

                }
            }

        } catch (Exception e) {
            WLog.w("KavaCdpByOwnerTask Error " + e.getMessage());
        }
        return mResult;
    }
}