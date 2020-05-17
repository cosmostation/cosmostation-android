package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Vote;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class IrisVoteListTask extends CommonTask {

    private String proposal_id;


    public IrisVoteListTask(BaseApplication app, TaskListener listener, String id) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_IRIS_VOTE_LIST;
        this.proposal_id = id;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<Vote>> response = ApiClient.getIrisChain(mApp).getVoteList(proposal_id).execute();
            if(response.isSuccessful()) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;

            } else {
                WLog.w("IrisTokenList : NOk");
            }

        } catch (Exception e) {
            WLog.w("IrisTokenList Error " + e.getMessage());
        }
        return mResult;
    }
}
