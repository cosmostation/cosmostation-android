package wannabit.io.cosmostaion.task.gRpcTask;

import cosmos.staking.v1beta1.QueryGrpc;
import cosmos.staking.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_VALIDATOR_INFO;

public class ValidatorInfoGrpcTask extends CommonTask {
    private BaseChain   mChain;
    private String      mValOpAddress;
    private QueryGrpc.QueryBlockingStub mStub;

    public ValidatorInfoGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String opAddress) {
        super(app, listener);
        this.mChain = chain;
        this.mValOpAddress = opAddress;
        this.mResult.taskType = TASK_GRPC_FETCH_VALIDATOR_INFO;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryValidatorRequest request = QueryOuterClass.QueryValidatorRequest.newBuilder().setValidatorAddr(mValOpAddress).build();
            QueryOuterClass.QueryValidatorResponse response = mStub.validator(request);

            this.mResult.isSuccess = true;
            this.mResult.resultData = response.getValidator();

        } catch (Exception e) { WLog.e( "ValidatorInfoGrpcTask "+ e.getMessage()); }
        return mResult;
    }

}
