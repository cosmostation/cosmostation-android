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
        return mResult;
    }

    @Override
    protected void onPostExecute(TaskResult taskResult) {
        Query.GetNodeInfoRequest request = Query.GetNodeInfoRequest.newBuilder().build();
        ServiceGrpc.ServiceStub txService = ServiceGrpc.newStub(ChannelBuilder.getChain(mChain));
        txService.getNodeInfo(request, new StreamObserver<Query.GetNodeInfoResponse>() {
            @Override
            public void onNext(Query.GetNodeInfoResponse value) {
                mResult.isSuccess = true;
                mResult.resultData = value.getDefaultNodeInfo();
                if (mListener != null) {
                    mListener.onTaskResponse(mResult);
                }
            }

            @Override
            public void onError(Throwable t) {}

            @Override
            public void onCompleted() {}
        });
    }
}
