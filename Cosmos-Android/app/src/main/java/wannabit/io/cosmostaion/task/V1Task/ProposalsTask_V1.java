package wannabit.io.cosmostaion.task.V1Task;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResProposal_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_PROPOSALS;

public class ProposalsTask_V1 extends CommonTask {
    private BaseChain mBaseChain;

    public ProposalsTask_V1(BaseApplication app, TaskListener listener, BaseChain baseChain) {
        super(app, listener);
        this.mBaseChain        = baseChain;
        this.mResult.taskType   = TASK_V1_FETCH_PROPOSALS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mBaseChain.equals(COSMOS_MAIN)) {
                Response<ResProposal_V1> response = ApiClient.getCosmosChain(mApp).getProposals().execute();
                if (response.isSuccessful() && response.body().proposals != null) {
                    mResult.resultData = response.body().proposals;
                }

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                Response<ResProposal_V1> response = ApiClient.getIrisChain(mApp).getProposals().execute();
                if (response.isSuccessful() && response.body().proposals != null) {
                    mResult.resultData = response.body().proposals;
                }

            } else if (mBaseChain.equals(AKASH_MAIN)) {
                Response<ResProposal_V1> response = ApiClient.getAkashChain(mApp).getProposals().execute();
                if (response.isSuccessful() && response.body().proposals != null) {
                    mResult.resultData = response.body().proposals;
                }

            }
            mResult.isSuccess = true;

        } catch (Exception e) { }
        return mResult;
    }
}