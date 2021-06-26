package wannabit.io.cosmostaion.task.gRpcTask;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import oracle.v1.Oracle;
import oracle.v1.QueryGrpc;
import oracle.v1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_BAND_ORACLE_STATUS;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class BandOracleStatusGrpcTask extends CommonTask {
    private BaseChain mChain;
    private ArrayList<Oracle.ActiveValidator> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public BandOracleStatusGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_BAND_ORACLE_STATUS;
        this.mStub = oracle.v1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryActiveValidatorsRequest request = QueryOuterClass.QueryActiveValidatorsRequest.newBuilder().build();
            QueryOuterClass.QueryActiveValidatorsResponse response = mStub.activeValidators(request);
            mResultData.addAll(response.getValidatorsList());
//            WLog.w("status : " + response.getValidatorsList());

            this.mResult.isSuccess = true;
            this.mResult.resultData = mResultData;

        } catch (Exception e) { WLog.e( "BandOracleStatusGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
