package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OKEX_ALL_VALIDATORS;

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

public class ValidatorInfoAllTask extends CommonTask {
    private final BaseChain mChain;

    public ValidatorInfoAllTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.result.taskType = TASK_FETCH_OKEX_ALL_VALIDATORS;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(OKEX_MAIN)) {
                Response<ArrayList<Validator>> response = ApiClient.getOkexChain(context).getAllValidatorDetailList().execute();
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

        } catch (Exception e) {
            WLog.w("ValidatorInfoAllTask Error " + e.getMessage());
        }

        return result;
    }
}
