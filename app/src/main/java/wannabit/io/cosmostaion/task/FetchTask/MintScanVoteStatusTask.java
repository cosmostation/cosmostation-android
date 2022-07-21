package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_VOTE_STATUS;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResVoteStatus;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class MintScanVoteStatusTask extends CommonTask {

    private String mChain;
    private int mProposalId;
    private String mVoter;

    public MintScanVoteStatusTask(BaseApplication app, TaskListener listener, String chain, int proposalId, String voter) {
        super(app, listener);
        this.mChain           = chain;
        this.mProposalId      = proposalId;
        this.mVoter           = voter;
        this.mResult.taskType = TASK_FETCH_MINTSCAN_VOTE_STATUS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            WLog.w("MinScan Path URL " +  ApiClient.getMintscan(mApp).getVoteStatus(mChain, mProposalId, mVoter).request().url());
            Response<ArrayList<ResVoteStatus>> response = ApiClient.getMintscan(mApp).getVoteStatus(mChain, mProposalId, mVoter).execute();
            if (!response.isSuccessful()) {
                mResult.isSuccess = false;
            }

            if (response.body() != null) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            }
        } catch (Exception e) {
            WLog.w("mVoteStatusList " + e.getMessage());
        }
        return mResult;
    }
}
