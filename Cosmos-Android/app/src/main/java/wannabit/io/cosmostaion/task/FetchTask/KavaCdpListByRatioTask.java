package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResCdpList;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaCdpListByRatioTask extends CommonTask {
    //Maybe do not need app side!!

    private BaseChain mChain;
    private String mDenom;
    private String mRatio;

    public KavaCdpListByRatioTask(BaseApplication app, TaskListener listener, BaseChain chain, String denom, String ratio) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_KAVA_CDP_LIST_RATIO;
        this.mChain = chain;
        this.mRatio = ratio;

    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                //mainnet not yet!

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResCdpList> response = ApiClient.getKavaTestChain(mApp).getCdpCoinRate(mDenom, mRatio).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaCdpListByRatioTask : NOk");
                }
            }

        } catch (Exception e) {
            WLog.w("KavaCdpListByRatioTask Error " + e.getMessage());
        }
        return mResult;
    }
}