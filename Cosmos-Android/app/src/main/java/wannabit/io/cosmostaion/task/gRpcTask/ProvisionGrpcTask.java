package wannabit.io.cosmostaion.task.gRpcTask;

import java.math.BigDecimal;

import cosmos.mint.v1beta1.QueryGrpc;
import cosmos.mint.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROVISION;

public class ProvisionGrpcTask extends CommonTask {
    private BaseChain mChain;
    private QueryGrpc.QueryBlockingStub mStub;

    public ProvisionGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_PROVISION;
        this.mResult.resultData = BigDecimal.ZERO;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }



    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryAnnualProvisionsRequest request = QueryOuterClass.QueryAnnualProvisionsRequest.newBuilder().build();
            WLog.w("ProvisionGrpcTask request "+ request.toString());
            QueryOuterClass.QueryAnnualProvisionsResponse response = mStub.annualProvisions(request);
            WLog.w("getAnnualProvisions " + response.getAnnualProvisions().toString("UTF-8"));

            BigDecimal provision = new BigDecimal(response.getAnnualProvisions().toString("UTF-8")).movePointLeft(18);
            this.mResult.isSuccess = true;
            this.mResult.resultData = provision;

        } catch (Exception e) { WLog.e( "ProvisionGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
