package wannabit.io.cosmostaion.task.gRpcTask;

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

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_ACTIVE_GAUGES;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class OsmosisActiveGaugesGrpcTask extends CommonTask {
    private BaseChain mChain;
    private QueryGrpc.QueryBlockingStub mStub;
    private ArrayList<GaugeOuterClass.Gauge> mResultData = new ArrayList<>();

    public OsmosisActiveGaugesGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_OSMOSIS_ACTIVE_GAUGES;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setLimit(1000).build();
            QueryOuterClass.ActiveGaugesRequest request = QueryOuterClass.ActiveGaugesRequest.newBuilder().setPagination(pageRequest).build();
            QueryOuterClass.ActiveGaugesResponse response = mStub.activeGauges(request);

            for (GaugeOuterClass.Gauge gauge: response.getDataList()) {
                mResultData.add(gauge);
            }
            mResult.resultData = mResultData;
            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "OsmosisActiveGaugesGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}