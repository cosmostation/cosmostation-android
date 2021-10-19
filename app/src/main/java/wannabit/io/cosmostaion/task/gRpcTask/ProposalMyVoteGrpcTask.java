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

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROPOSAL_MY_VOTE;

public class ProposalMyVoteGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mProposalId;
    private String mAddress;
    private QueryGrpc.QueryBlockingStub mStub;
    private shentu.gov.v1alpha1.QueryGrpc.QueryBlockingStub mCtkStub;

    public ProposalMyVoteGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String proposalId, String address) {
        super(app, listener);
        this.mChain = chain;
        this.mProposalId = proposalId;
        this.mAddress = address;
        this.mResult.taskType = TASK_GRPC_FETCH_PROPOSAL_MY_VOTE;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
        this.mCtkStub = shentu.gov.v1alpha1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.CERTIK_MAIN)) {
                shentu.gov.v1alpha1.QueryOuterClass.QueryVoteRequest request = shentu.gov.v1alpha1.QueryOuterClass.QueryVoteRequest.newBuilder().setProposalId(Long.parseLong(mProposalId)).setVoter(mAddress).build();
                shentu.gov.v1alpha1.QueryOuterClass.QueryVoteResponse response = mCtkStub.vote(request);
                this.mResult.resultData = response.getVote();
            } else {
                QueryOuterClass.QueryVoteRequest request = QueryOuterClass.QueryVoteRequest.newBuilder().setProposalId(Long.parseLong(mProposalId)).setVoter(mAddress).build();
                QueryOuterClass.QueryVoteResponse response = mStub.vote(request);
                this.mResult.resultData = response.getVote();
//            WLog.w("ProposalMyVoteGrpcTask " + response.getVote()));
            }

        } catch (Exception e) { WLog.e( "ProposalMyVoteGrpcTask "+ e.getMessage()); }
        return mResult;
    }

}