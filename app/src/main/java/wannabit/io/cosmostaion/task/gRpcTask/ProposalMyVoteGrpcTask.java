package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROPOSAL_MY_VOTE;

import cosmos.gov.v1beta1.QueryGrpc;
import cosmos.gov.v1beta1.QueryOuterClass;
import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.network.res.ResMyProposal;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class ProposalMyVoteGrpcTask extends CommonTask {
    private final BaseChain mChain;
    private final String mProposalId;
    private final String mAddress;
    private final QueryGrpc.QueryBlockingStub mStub;

    public ProposalMyVoteGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String proposalId, String address) {
        super(app, listener);
        this.mChain = chain;
        this.mProposalId = proposalId;
        this.mAddress = address;
        this.result.taskType = TASK_GRPC_FETCH_PROPOSAL_MY_VOTE;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.CERTIK_MAIN)) {
                Response<ResMyProposal> response = ApiClient.getCertikChain(context).getCertikProposal(mProposalId, mAddress).execute();
                if (response.isSuccessful() && response.body() != null && response.body().vote != null && response.body().vote.options != null) {
                    result.resultData = response.body();
                    result.isSuccess = true;
                } else {
                    result.isSuccess = false;
                }
            } else {
                QueryOuterClass.QueryVoteRequest request = QueryOuterClass.QueryVoteRequest.newBuilder().setProposalId(Long.parseLong(mProposalId)).setVoter(mAddress).build();
                QueryOuterClass.QueryVoteResponse response = mStub.vote(request);
                this.result.resultData = response.getVote();
            }

        } catch (Exception e) {
            WLog.e("ProposalMyVoteGrpcTask " + e.getMessage());
        }
        return result;
    }

}