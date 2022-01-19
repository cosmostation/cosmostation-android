package wannabit.io.cosmostaion.task.gRpcTask;

import java.util.concurrent.TimeUnit;

import kava.cdp.v1beta1.QueryGrpc;
import kava.cdp.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_CDP_PARAMS;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class KavaCdpParamGrpcTask extends CommonTask {
    private BaseChain mChain;
    private QueryGrpc.QueryBlockingStub mStub;

    public KavaCdpParamGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_KAVA_CDP_PARAMS;
        this.mStub = kava.cdp.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryParamsRequest request = QueryOuterClass.QueryParamsRequest.newBuilder().build();
            QueryOuterClass.QueryParamsResponse response = mStub.params(request);

            mResult.resultData = response.getParams();
            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "KavaCdpParamGrpcTask "+ e.getMessage()); }
        return mResult;
    }

}

