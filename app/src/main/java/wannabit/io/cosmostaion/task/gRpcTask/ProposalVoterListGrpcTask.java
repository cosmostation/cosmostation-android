package wannabit.io.cosmostaion.task.gRpcTask;

import cosmos.base.query.v1beta1.Pagination;
import cosmos.gov.v1beta1.QueryGrpc;
import cosmos.gov.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROPOSAL_VOTER_LIST;

public class ProposalVoterListGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mProposalId;
    private QueryGrpc.QueryBlockingStub mStub;
    private shentu.gov.v1alpha1.QueryGrpc.QueryBlockingStub mCtkStub;

    public ProposalVoterListGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String proposalId) {
        super(app, listener);
        this.mChain = chain;
        this.mProposalId = proposalId;
        this.mResult.taskType = TASK_GRPC_FETCH_PROPOSAL_VOTER_LIST;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
        this.mCtkStub = shentu.gov.v1alpha1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setLimit(2000).build();
        try {
            if (mChain.equals(BaseChain.CERTIK_MAIN)) {
                shentu.gov.v1alpha1.QueryOuterClass.QueryVotesRequest request = shentu.gov.v1alpha1.QueryOuterClass.QueryVotesRequest.newBuilder().setProposalId(Long.parseLong(mProposalId)).build();
                shentu.gov.v1alpha1.QueryOuterClass.QueryVotesResponse response = mCtkStub.votes(request);
                this.mResult.resultData = response.getVotesList();
            } else {
                QueryOuterClass.QueryVotesRequest request = QueryOuterClass.QueryVotesRequest.newBuilder().setPagination(pageRequest).setProposalId(Long.parseLong(mProposalId)).build();
                QueryOuterClass.QueryVotesResponse response = mStub.votes(request);
                this.mResult.resultData = response.getVotesList();
//            WLog.w("ProposalVoterListGrpcTask " + response.getVotesList());
            }

        } catch (Exception e) { WLog.e( "ProposalVoterListGrpcTask "+ e.getMessage()); }
        return mResult;
    }

}
