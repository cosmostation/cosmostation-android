package wannabit.io.cosmostaion.task.gRpcTask;

import cosmos.staking.v1beta1.QueryGrpc;
import cosmos.staking.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STAKING_POOL;

public class StakingPoolGrpcTask extends CommonTask {
    private BaseChain mChain;
    private QueryGrpc.QueryBlockingStub mStub;

    public StakingPoolGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_STAKING_POOL;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryPoolRequest request = QueryOuterClass.QueryPoolRequest.newBuilder().build();
            QueryOuterClass.QueryPoolResponse response = mStub.pool(request);
//            WLog.w("StakingPool " + response.getPool());

            this.mResult.isSuccess = true;
            this.mResult.resultData = response.getPool();

        } catch (Exception e) { WLog.e( "StakingPoolGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
