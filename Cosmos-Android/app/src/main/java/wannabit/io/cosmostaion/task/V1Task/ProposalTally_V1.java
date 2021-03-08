package wannabit.io.cosmostaion.task.V1Task;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResProposalTally_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_PROPOSAL_TALLY;

public class ProposalTally_V1 extends CommonTask {
    private BaseChain   mBaseChain;
    private String      mProposalId;

    public ProposalTally_V1(BaseApplication app, TaskListener listener, BaseChain baseChain, String proposalId) {
        super(app, listener);
        this.mBaseChain        = baseChain;
        this.mProposalId        = proposalId;
        this.mResult.taskType   = TASK_V1_FETCH_PROPOSAL_TALLY;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mBaseChain.equals(COSMOS_MAIN)) {
                Response<ResProposalTally_V1> response = ApiClient.getCosmosChain(mApp).getProposalTally(mProposalId).execute();
                if (response.isSuccessful() && response.body().tally != null) {
                    mResult.resultData = response.body().tally;
                }

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                Response<ResProposalTally_V1> response = ApiClient.getIrisChain(mApp).getProposalTally(mProposalId).execute();
                if (response.isSuccessful() && response.body().tally != null) {
                    mResult.resultData = response.body().tally;
                }

            } else if (mBaseChain.equals(AKASH_MAIN)) {
                Response<ResProposalTally_V1> response = ApiClient.getAkashChain(mApp).getProposalTally(mProposalId).execute();
                if (response.isSuccessful() && response.body().tally != null) {
                    mResult.resultData = response.body().tally;
                }

            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("ProposalTally_V1 "  +e.getMessage());
        }
        return mResult;
    }
}
