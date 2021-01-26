package wannabit.io.cosmostaion.task.V1Task;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.Delegation_V1;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResDelegations_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_DELEGATIONS;

public class DelegationsTask_V1 extends CommonTask {
    private Account                     mAccount;
    private int                         mOffset = 0;
    private boolean                     mBreak = false;
    private ArrayList<Delegation_V1>    mResultData = new ArrayList<>();

    public DelegationsTask_V1(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = TASK_V1_FETCH_DELEGATIONS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        while (!mBreak) {
            ArrayList<Delegation_V1> temp = onDoingJob(mOffset);
            mResultData.addAll(temp);
            if (temp.size() == 100) {
                mOffset = mOffset + 100;
            } else {
                mBreak = true;
            }
        }
        mResult.resultData = mResultData;
        mResult.isSuccess = true;
        return mResult;
    }

    private ArrayList<Delegation_V1> onDoingJob(int offset) {
        ArrayList<Delegation_V1> resultData = new ArrayList<>();
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_TEST)) {
                Response<ResDelegations_V1> response = ApiClient.getCosmosTestChain(mApp).getDelegations(mAccount.address, 100,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().delegation_responses != null) {
                        resultData = response.body().delegation_responses;
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_TEST)) {
                Response<ResDelegations_V1> response = ApiClient.getIrisTestChain(mApp).getDelegations(mAccount.address, 100,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().delegation_responses != null) {
                        resultData = response.body().delegation_responses;
                    }
                }
            }

        } catch (Exception e) { }
        return resultData;
    }
}
