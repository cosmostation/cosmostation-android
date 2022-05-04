package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.chain.ChainFactory;
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class ApiStakeTxsHistoryTask extends CommonTask {

    private String mAddress;
    private String mValOpAddress;
    private BaseChain mChain;

    public ApiStakeTxsHistoryTask(BaseApplication app, TaskListener listener, String address, String vAddress, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_API_STAKE_HISTORY;
        this.mAddress = address;
        this.mValOpAddress = vAddress;
        this.mChain = chain;
    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<ResApiNewTxListCustom>> response = ChainFactory.getChain(mChain).getHistoryApi(mApp).getNewStakeTxsCustom(mAddress, mValOpAddress, "50").execute();

            if (response.isSuccessful() && response.body() != null) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            } else {
                WLog.w("ApiStakeTxsHistoryTask : NOk");
            }

        } catch (Exception e) {
            e.printStackTrace();
            WLog.w("ApiStakeTxsHistoryTask Error " + e.getMessage());
        }
        return mResult;
    }

}
