package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Proposal;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdProposals;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class ProposalTask extends CommonTask {


    public ProposalTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_ALL_PROPOSAL;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
//            Response<ResLcdProposals> response = ApiClient.getCosmosChain(mApp).getProposalList().execute();
//            if(!response.isSuccessful()) {
//                mResult.isSuccess = false;
//                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
//                return mResult;
//            }
//
//            if(response.body() != null && response.body().result != null && response.body().result.size() > 0) {
//                mResult.resultData = response.body().result;
//                mResult.isSuccess = true;
//            }

            //TODO rollback cosmos-hub2
            Response<ArrayList<Proposal>> response = ApiClient.getCosmosChain(mApp).getProposalListLegacy().execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                return mResult;
            }

            if(response.body() != null && response.body().size() > 0) {
                mResult.resultData = response.body();
                mResult.isSuccess = true;
            }


        } catch (Exception e) {
            WLog.w("AllProposalTask Error " + e.getMessage());
        }

        return mResult;
    }
}
