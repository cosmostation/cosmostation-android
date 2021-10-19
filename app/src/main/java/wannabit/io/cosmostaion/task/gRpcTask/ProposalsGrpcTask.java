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

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROPOSALS;

public class ProposalsGrpcTask extends CommonTask {
    private BaseChain mChain;
    private QueryGrpc.QueryBlockingStub mStub;
    private shentu.gov.v1alpha1.QueryGrpc.QueryBlockingStub mCtkStub;

    public ProposalsGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_PROPOSALS;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
        this.mCtkStub = shentu.gov.v1alpha1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setLimit(5000).build();
            if (mChain.equals(BaseChain.CERTIK_MAIN)) {
                shentu.gov.v1alpha1.QueryOuterClass.QueryProposalsRequest request = shentu.gov.v1alpha1.QueryOuterClass.QueryProposalsRequest.newBuilder().setPagination(pageRequest).build();
                shentu.gov.v1alpha1.QueryOuterClass.QueryProposalsResponse response = mCtkStub.proposals(request);
                this.mResult.resultData = response.getProposalsList();
            } else {
                QueryOuterClass.QueryProposalsRequest request = QueryOuterClass.QueryProposalsRequest.newBuilder().setPagination(pageRequest).build();
                QueryOuterClass.QueryProposalsResponse response = mStub.proposals(request);
                this.mResult.resultData = response.getProposalsList();
            }

        } catch (Exception e) { WLog.e( "ProposalsGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
