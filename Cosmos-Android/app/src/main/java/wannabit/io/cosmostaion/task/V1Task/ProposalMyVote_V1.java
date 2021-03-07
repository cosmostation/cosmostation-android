package wannabit.io.cosmostaion.task.V1Task;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResProposalMyVoted_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_PROPOSAL_MY_VOTE;

public class ProposalMyVote_V1 extends CommonTask {
    private BaseChain   mBaseChain;
    private String      mProposalId;
    private String      mAddress;

    public ProposalMyVote_V1(BaseApplication app, TaskListener listener, BaseChain baseChain, String proposalId, String address) {
        super(app, listener);
        this.mBaseChain         = baseChain;
        this.mProposalId        = proposalId;
        this.mAddress           = address;
        this.mResult.taskType   = TASK_V1_FETCH_PROPOSAL_MY_VOTE;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mBaseChain.equals(COSMOS_MAIN)) {
                Response<ResProposalMyVoted_V1> response = ApiClient.getCosmosChain(mApp).getProposalMyVoted(mProposalId, mAddress).execute();
                if (response.isSuccessful() && response.body().vote != null) {
                    mResult.resultData = response.body().vote;
                }

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                Response<ResProposalMyVoted_V1> response = ApiClient.getIrisChain(mApp).getProposalMyVoted(mProposalId, mAddress).execute();
                if (response.isSuccessful() && response.body().vote != null) {
                    mResult.resultData = response.body().vote;
                }

            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("ProposalMyVote_V1 "  +e.getMessage());
        }
        return mResult;
    }
}