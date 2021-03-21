package wannabit.io.cosmostaion.task.gRpcTask;

import cosmos.base.tendermint.v1beta1.Query;
import cosmos.base.tendermint.v1beta1.ServiceGrpc;
import io.grpc.stub.StreamObserver;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_NODE_INFO;

public class NodeInfoGrpcTask extends CommonTask {
    private BaseChain mChain;

    public NodeInfoGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_NODE_INFO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Query.GetNodeInfoRequest request = Query.GetNodeInfoRequest.newBuilder().build();
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
            Query.GetNodeInfoResponse response = txService.getNodeInfo(request);

            this.mResult.isSuccess = true;
            mResult.resultData = response.getDefaultNodeInfo();

        } catch (Exception e) { WLog.e( "NodeInfoGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
