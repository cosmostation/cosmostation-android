package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_POOL_INFO;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.concurrent.TimeUnit;

import osmosis.gamm.poolmodels.balancer.BalancerPool;
import osmosis.gamm.v1beta1.QueryGrpc;
import osmosis.gamm.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class OsmosisPoolInfoGrpcTask extends CommonTask {
    private final BaseChain mChain;
    private final long mPoolId;
    private final QueryGrpc.QueryBlockingStub mStub;

    public OsmosisPoolInfoGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, long mPoolId) {
        super(app, listener);
        this.mChain = chain;
        this.mPoolId = mPoolId;
        this.result.taskType = TASK_GRPC_FETCH_OSMOSIS_POOL_INFO;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryPoolRequest request = QueryOuterClass.QueryPoolRequest.newBuilder().setPoolId(mPoolId).build();
            QueryOuterClass.QueryPoolResponse response = mStub.pool(request);
            result.resultData = BalancerPool.Pool.parseFrom(response.getPool().getValue());
            result.isSuccess = true;

        } catch (Exception e) {
            WLog.e("OsmosisGrpcPoolInfoTask " + e.getMessage());
        }
        return result;
    }

}
