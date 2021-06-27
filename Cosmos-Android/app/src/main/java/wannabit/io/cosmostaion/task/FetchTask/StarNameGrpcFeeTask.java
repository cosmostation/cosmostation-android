package wannabit.io.cosmostaion.task.FetchTask;

import java.util.concurrent.TimeUnit;

import starnamed.x.configuration.v1beta1.QueryGrpc;
import starnamed.x.configuration.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_FEE;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class StarNameGrpcFeeTask extends CommonTask {
    private BaseChain mChain;
    private QueryGrpc.QueryBlockingStub mStub;

    public StarNameGrpcFeeTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_STARNAME_FEE;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryFeesRequest request = QueryOuterClass.QueryFeesRequest.newBuilder().build();
            QueryOuterClass.QueryFeesResponse response = mStub.fees(request);
            mResult.resultData = response.getFees();
            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "StarNameGrpcFeeTask "+ e.getMessage()); }
        return mResult;
    }
}
