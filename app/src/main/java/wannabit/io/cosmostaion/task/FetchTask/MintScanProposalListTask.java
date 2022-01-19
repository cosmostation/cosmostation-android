package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_NETWORK;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_PROPOSAL_LIST;

public class MintScanProposalListTask extends CommonTask {

    private String mChain;

    public MintScanProposalListTask(BaseApplication app, TaskListener listener, String chain) {
        super(app, listener);
        this.mChain           = chain;
        this.mResult.taskType = TASK_FETCH_MINTSCAN_PROPOSAL_LIST;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            WLog.w("MinScan Path URL " +  ApiClient.getMintscan(mApp).getProposalList(mChain).request().url());
            Response<ArrayList<ResProposal>> response = ApiClient.getMintscan(mApp).getProposalList(mChain).execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = ERROR_CODE_NETWORK;
                return mResult;
            }

            if (response.body() != null) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            }
        } catch (Exception e) {
            WLog.w("MintScanProposalListTask Error " + e.getMessage());
        }
        return mResult;
    }
}
