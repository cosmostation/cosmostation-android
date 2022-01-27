package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OKEX_ALL_VALIDATORS;

public class ValidatorInfoAllTask extends CommonTask {
    private BaseChain mChain;

    public ValidatorInfoAllTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType = TASK_FETCH_OKEX_ALL_VALIDATORS;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(OKEX_MAIN)) {
                Response<ArrayList<Validator>> response = ApiClient.getOkexChain(mApp).getAllValidatorDetailList().execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            }

        } catch (Exception e) {
            WLog.w("ValidatorInfoAllTask Error " + e.getMessage());
        }

        return mResult;
    }
}
