package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdProposalTally;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class ProposalTallyTask extends CommonTask {

    private BaseChain mChain;
    private String mProposalId;

    public ProposalTallyTask(BaseApplication app, TaskListener listener, String proposalId, BaseChain chain) {
        super(app, listener);
        this.mProposalId = proposalId;
        this.mChain = chain;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_PROPOSAL_TALLY;
    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResLcdProposalTally> response = ApiClient.getKavaChain(mApp).getTally(mProposalId).execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.SECRET_MAIN)) {
                Response<ResLcdProposalTally> response = ApiClient.getSecretChain(mApp).getTally(mProposalId).execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            }

        } catch (Exception e) {
            WLog.w("ProposalTallyTask Error " + e.getMessage());
        }

        return mResult;
    }
}
