package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_PROPOSAL;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class MintScanProposalTask extends CommonTask {

    private final String mChain;
    private final String mProposalId;

    public MintScanProposalTask(BaseApplication app, TaskListener listener, String chain, String proposalId) {
        super(app, listener);
        this.mChain = chain;
        this.mProposalId = proposalId;
        this.result.taskType = TASK_FETCH_MINTSCAN_PROPOSAL;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            WLog.w("MinScan Path URL " + ApiClient.getMintscan(context).getProposal(mChain, mProposalId).request().url());
            Response<ResProposal> response = ApiClient.getMintscan(context).getProposal(mChain, mProposalId).execute();
            if (!response.isSuccessful()) {
                result.isSuccess = false;
                result.errorCode = ERROR_CODE_NETWORK;
                return result;
            }

            if (response.body() != null) {
                result.resultData = response.body();
            }
        } catch (Exception e) {
            WLog.w("MintScanProposalTask Error " + e.getMessage());
        }
        return result;
    }
}
