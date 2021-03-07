package wannabit.io.cosmostaion.task.V1Task;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResProposalVoterList_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_PROPOSAL_VOTERS;

public class ProposalVoterList_V1 extends CommonTask {
    private BaseChain   mBaseChain;
    private String      mProposalId;

    public ProposalVoterList_V1(BaseApplication app, TaskListener listener, BaseChain baseChain, String proposalId) {
        super(app, listener);
        this.mBaseChain        = baseChain;
        this.mProposalId        = proposalId;
        this.mResult.taskType   = TASK_V1_FETCH_PROPOSAL_VOTERS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mBaseChain.equals(COSMOS_MAIN)) {
                Response<ResProposalVoterList_V1> response = ApiClient.getCosmosChain(mApp).getProposalAllVoters(mProposalId, 1000, 0).execute();
                if (response.isSuccessful() && response.body().votes != null) {
                    mResult.resultData = response.body().votes;
                }

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                Response<ResProposalVoterList_V1> response = ApiClient.getIrisChain(mApp).getProposalAllVoters(mProposalId, 1000, 0).execute();
                if (response.isSuccessful() && response.body().votes != null) {
                    mResult.resultData = response.body().votes;
                }

            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("ProposalVoterList_V1 "  +e.getMessage());
        }
        return mResult;
    }
}
