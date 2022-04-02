package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_INCENTIVIZED;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import osmosis.poolincentives.v1beta1.QueryGrpc;
import osmosis.poolincentives.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class OsmosisIncentivizedPoolsGrpcTask extends CommonTask {
    private BaseChain mChain;
    private QueryGrpc.QueryBlockingStub mStub;
    private ArrayList<QueryOuterClass.IncentivizedPool> mResultData = new ArrayList<>();

    public OsmosisIncentivizedPoolsGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.result.taskType = TASK_GRPC_FETCH_OSMOSIS_INCENTIVIZED;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryIncentivizedPoolsRequest request = QueryOuterClass.QueryIncentivizedPoolsRequest.newBuilder().build();
            QueryOuterClass.QueryIncentivizedPoolsResponse response = mStub.incentivizedPools(request);

            for (QueryOuterClass.IncentivizedPool incentive : response.getIncentivizedPoolsList()) {
                mResultData.add(incentive);
            }
            result.resultData = mResultData;
            result.isSuccess = true;

        } catch (Exception e) {
            WLog.e("OsmosisIncentivizedPoolsGrpcTask " + e.getMessage());
        }
        return result;
    }
}