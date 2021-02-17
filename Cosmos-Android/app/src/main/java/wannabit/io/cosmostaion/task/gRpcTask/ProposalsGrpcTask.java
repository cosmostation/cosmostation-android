package wannabit.io.cosmostaion.task.gRpcTask;


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

    public ProposalsGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_PROPOSALS;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryProposalsRequest request = QueryOuterClass.QueryProposalsRequest.newBuilder().build();
            QueryOuterClass.QueryProposalsResponse response = mStub.proposals(request);
            this.mResult.resultData = response.getProposalsList();
//            WLog.w("ProposalsGrpcTask " + response.getProposalsList());

        } catch (Exception e) { WLog.e( "BalanceGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
