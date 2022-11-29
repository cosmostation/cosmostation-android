package wannabit.io.cosmostaion.task.gRpcTask;

import static stride.stakeibc.QueryGrpc.newBlockingStub;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_HOST_ZONE_CHAINID;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.concurrent.TimeUnit;

import stride.stakeibc.QueryGrpc;
import stride.stakeibc.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class HostZoneGrpcTask extends CommonTask {

    private ChainConfig mChainConfig;
    private String mChainId;
    private QueryGrpc.QueryBlockingStub mStub;

    public HostZoneGrpcTask(BaseApplication app, TaskListener listener, ChainConfig chainConfig, String chainId) {
        super(app, listener);
        this.mChainConfig = chainConfig;
        this.mChainId = chainId;
        this.mResult.taskType = TASK_GRPC_FETCH_HOST_ZONE_CHAINID;
        this.mStub = newBlockingStub(ChannelBuilder.getChain(mChainConfig.baseChain())).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryGetHostZoneRequest request = QueryOuterClass.QueryGetHostZoneRequest.newBuilder().setChainId(mChainId).build();
            QueryOuterClass.QueryGetHostZoneResponse response = mStub.hostZone(request);

            mResult.resultData = response.getHostZone();
            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "HostZoneGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
