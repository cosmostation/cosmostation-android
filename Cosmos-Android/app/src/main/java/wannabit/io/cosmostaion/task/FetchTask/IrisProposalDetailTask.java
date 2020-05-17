package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.IrisProposal;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class IrisProposalDetailTask extends CommonTask {

    private String proposal_id;


    public IrisProposalDetailTask(BaseApplication app, TaskListener listener, String id) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_IRIS_PROPOSAL_DETAIL;
        this.proposal_id = id;
    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<IrisProposal> response = ApiClient.getIrisChain(mApp).getProposalDetail(proposal_id).execute();
            if (response.isSuccessful()) {
                mResult.isSuccess = true;
                mResult.resultData = response.body();
            }

        } catch (Exception e) {
            WLog.w("AllProposalTask Error " + e.getMessage());
        }

        return mResult;
    }
}