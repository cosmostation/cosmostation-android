package wannabit.io.cosmostaion.task.gRpcTask;

import static stride.stakeibc.QueryGrpc.newBlockingStub;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_EPOCH_TRACKER;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.concurrent.TimeUnit;

import stride.stakeibc.QueryGrpc;
import stride.stakeibc.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class EpochTrackerGrpcTask extends CommonTask {

    private ChainConfig mChainConfig;
    private QueryGrpc.QueryBlockingStub mStub;

    public EpochTrackerGrpcTask(BaseApplication app, TaskListener listener, ChainConfig chainConfig) {
        super(app, listener);
        this.mChainConfig = chainConfig;
        this.mResult.taskType = TASK_GRPC_FETCH_EPOCH_TRACKER;
        this.mStub = newBlockingStub(ChannelBuilder.getChain(mChainConfig.baseChain())).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryGetEpochTrackerRequest request = QueryOuterClass.QueryGetEpochTrackerRequest.newBuilder().setEpochIdentifier("day").build();
            QueryOuterClass.QueryGetEpochTrackerResponse response = mStub.epochTracker(request);

            mResult.resultData = response.getEpochTracker();
            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "EpochTrackerGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
