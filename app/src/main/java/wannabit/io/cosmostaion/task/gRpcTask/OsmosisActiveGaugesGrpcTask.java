package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_ACTIVE_GAUGES;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cosmos.base.query.v1beta1.Pagination;
import osmosis.incentives.GaugeOuterClass;
import osmosis.incentives.QueryGrpc;
import osmosis.incentives.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class OsmosisActiveGaugesGrpcTask extends CommonTask {
    private BaseChain mChain;
    private long mPoolId;
    private QueryGrpc.QueryBlockingStub mStub;

    public OsmosisActiveGaugesGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, long poolId) {
        super(app, listener);
        this.mChain = chain;
        this.mPoolId = poolId;
        this.mResult.taskType = TASK_GRPC_FETCH_OSMOSIS_ACTIVE_GAUGES;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.GaugeByIDRequest request = QueryOuterClass.GaugeByIDRequest.newBuilder().setId(mPoolId).build();
            QueryOuterClass.GaugeByIDResponse response = mStub.gaugeByID(request);

            mResult.resultData = response.getGauge();
            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "OsmosisActiveGaugesGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}