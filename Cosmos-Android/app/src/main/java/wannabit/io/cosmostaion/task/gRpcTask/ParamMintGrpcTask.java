package wannabit.io.cosmostaion.task.gRpcTask;

import cosmos.mint.v1beta1.QueryGrpc;
import cosmos.mint.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PARAM_MINT;

public class ParamMintGrpcTask extends CommonTask {
    private BaseChain mChain;
    private QueryGrpc.QueryBlockingStub mStub;

    public ParamMintGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_PARAM_MINT;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryParamsRequest request = QueryOuterClass.QueryParamsRequest.newBuilder().build();
            QueryOuterClass.QueryParamsResponse response = mStub.params(request);
//            WLog.w("ParamMint " + response.getParams());

            this.mResult.isSuccess = true;
            this.mResult.resultData = response.getParams();

        } catch (Exception e) { WLog.e( "ParamMintGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
