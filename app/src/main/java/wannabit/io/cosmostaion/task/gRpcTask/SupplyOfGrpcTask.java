package wannabit.io.cosmostaion.task.gRpcTask;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cosmos.bank.v1beta1.QueryGrpc;
import cosmos.bank.v1beta1.QueryOuterClass;
import cosmos.base.v1beta1.CoinOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_SUPPLY_OF_INFO;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class SupplyOfGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mDenom;
    private QueryGrpc.QueryBlockingStub mStub;

    public SupplyOfGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String denom) {
        super(app, listener);
        this.mChain = chain;
        this.mDenom = denom;
        this.mResult.taskType = TASK_GRPC_FETCH_SUPPLY_OF_INFO;
        this.mStub = cosmos.bank.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QuerySupplyOfRequest request = QueryOuterClass.QuerySupplyOfRequest.newBuilder().setDenom(mDenom).build();
            QueryOuterClass.QuerySupplyOfResponse response = mStub.supplyOf(request);

            mResult.isSuccess = true;
            mResult.resultData = response;

        } catch (Exception e) { WLog.e( "SupplyOfGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
