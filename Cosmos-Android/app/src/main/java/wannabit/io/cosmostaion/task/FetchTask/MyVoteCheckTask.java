package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResMyVote;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class MyVoteCheckTask extends CommonTask {

    private BaseChain mChain;
    private String mProposalId;
    private String mAddress;

    public MyVoteCheckTask(BaseApplication app, TaskListener listener, String proposalId, String address, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mProposalId = proposalId;
        this.mAddress = address;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_MY_VOTE;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
//            if (mChain.equals(BaseChain.COSMOS_MAIN)) {
//                Response<ResMyVote> response = ApiClient.getCosmosChain(mApp).getMyVote(mProposalId, mAddress).execute();
//                if (!response.isSuccessful()) {
//                    mResult.isSuccess = false;
//                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
//                    return mResult;
//                }
//
//                if (response.body() != null && response.body().result != null) {
//                    mResult.resultData = response.body().result;
//                    mResult.isSuccess = true;
//                }
//
//            } else
                if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResMyVote> response = ApiClient.getKavaChain(mApp).getMyVote(mProposalId, mAddress).execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.BAND_MAIN)) {
                Response<ResMyVote> response = ApiClient.getBandChain(mApp).getMyVote(mProposalId, mAddress).execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.CERTIK_MAIN)) {
                Response<ResMyVote> response = ApiClient.getCertikChain(mApp).getMyVote(mProposalId, mAddress).execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.CERTIK_TEST)) {
                Response<ResMyVote> response = ApiClient.getCertikTestChain(mApp).getMyVote(mProposalId, mAddress).execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.IOV_MAIN)) {
                Response<ResMyVote> response = ApiClient.getIovChain(mApp).getMyVote(mProposalId, mAddress).execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.SECRET_MAIN)) {
                Response<ResMyVote> response = ApiClient.getSecretChain(mApp).getMyVote(mProposalId, mAddress).execute();
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
