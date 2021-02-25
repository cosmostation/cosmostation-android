package wannabit.io.cosmostaion.task.V1Task;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.IrisToken_V1;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResIrisTokenList_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_IRIS_TOKEN_LIST;

public class IrisTokenListTask_V1 extends CommonTask {
    private Account                 mAccount;
    private int                     mOffset = 0;
    private boolean                 mBreak = false;
    private ArrayList<IrisToken_V1> mResultData = new ArrayList<>();

    public IrisTokenListTask_V1(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = TASK_V1_FETCH_IRIS_TOKEN_LIST;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
//        while(!mBreak) {
            ArrayList<IrisToken_V1> temp = onDoingJob(mOffset);
            mResultData.addAll(temp);
//            if (temp.size() == 100) {
//                mOffset = mOffset + 100;
//            } else {
//                mBreak = true;
//            }
//        }

        mResult.resultData = mResultData;
        mResult.isSuccess = true;
        return mResult;
    }

    private ArrayList<IrisToken_V1> onDoingJob(int offset) {
        ArrayList<IrisToken_V1> resultData = new ArrayList<>();
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_MAIN)) {
                Response<ResIrisTokenList_V1> response = ApiClient.getIrisChain(mApp).getTokenList(100,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().tokens != null) {
                        resultData = response.body().tokens;
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_TEST)) {
                Response<ResIrisTokenList_V1> response = ApiClient.getIrisTestChain(mApp).getTokenList(100,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().tokens != null) {
                        resultData = response.body().tokens;
                    }
                }
            }

        } catch (Exception e) { }
        return resultData;
    }
}
