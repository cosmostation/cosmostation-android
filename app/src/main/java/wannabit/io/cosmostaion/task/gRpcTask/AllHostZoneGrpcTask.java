package wannabit.io.cosmostaion.task.gRpcTask;

import static stride.stakeibc.QueryGrpc.QueryBlockingStub;
import static stride.stakeibc.QueryGrpc.newBlockingStub;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_ALL_HOST_ZONE;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.concurrent.TimeUnit;

import cosmos.base.query.v1beta1.Pagination;
import stride.stakeibc.QueryOuterClass.QueryAllHostZoneRequest;
import stride.stakeibc.QueryOuterClass.QueryAllHostZoneResponse;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class AllHostZoneGrpcTask extends CommonTask {

    private BaseChain mChain;
    private QueryBlockingStub mStub;

    public AllHostZoneGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_ALL_HOST_ZONE;
        this.mStub = newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setLimit(1000).build();
            QueryAllHostZoneRequest request = QueryAllHostZoneRequest.newBuilder().setPagination(pageRequest).build();
            QueryAllHostZoneResponse response = mStub.hostZoneAll(request);

            mResult.resultData = response.getHostZoneList();
            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "AllHostZoneGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}

