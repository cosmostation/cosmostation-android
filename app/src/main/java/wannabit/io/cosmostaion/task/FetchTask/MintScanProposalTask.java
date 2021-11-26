package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResIbcTokens;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_PROPOSAL;

public class MintScanProposalTask extends CommonTask {

    private String mChain;
    private String mProposalId;

    public MintScanProposalTask(BaseApplication app, TaskListener listener, String chain, String proposalId) {
        super(app, listener);
        this.mChain           = chain;
        this.mProposalId      = proposalId;
        this.mResult.taskType = TASK_FETCH_MINTSCAN_PROPOSAL;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            WLog.w("MinScan Path URL " +  ApiClient.getMintscan(mApp).getProposal(mChain, mProposalId).request().url());
            Response<ResProposal> response = ApiClient.getMintscan(mApp).getProposal(mChain, mProposalId).execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null) {
                mResult.resultData = response.body();
            }
        } catch (Exception e) {
            WLog.w("MintScanProposalTask Error " + e.getMessage());
        }
        return mResult;
    }
}
