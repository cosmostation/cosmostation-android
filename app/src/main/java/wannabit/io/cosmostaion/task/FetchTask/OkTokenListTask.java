package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResOkTokenList;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class OkTokenListTask extends CommonTask {

    private final BaseChain mChain;

    public OkTokenListTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.result.taskType = BaseConstant.TASK_FETCH_OK_TOKEN_LIST;

    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.OKEX_MAIN)) {
                Response<ResOkTokenList> response = ApiClient.getOkexChain(context).getTokenList().execute();
                if (!response.isSuccessful()) {
                    result.isSuccess = false;
                    result.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return result;
                }

                if (response.body() != null) {
                    result.resultData = response.body();
                    result.isSuccess = true;
                }

            }
            result.isSuccess = true;

        } catch (Exception e) {
            WLog.w("OkDepositTask Error " + e.getMessage());

        }
        return result;
    }

}