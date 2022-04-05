package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_VALIDATOR_INFO;

import cosmos.staking.v1beta1.QueryGrpc;
import cosmos.staking.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class ValidatorInfoGrpcTask extends CommonTask {
    private final BaseChain mChain;
    private final String mValOpAddress;
    private final QueryGrpc.QueryBlockingStub mStub;

    public ValidatorInfoGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String opAddress) {
        super(app, listener);
        this.mChain = chain;
        this.mValOpAddress = opAddress;
        this.result.taskType = TASK_GRPC_FETCH_VALIDATOR_INFO;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryValidatorRequest request = QueryOuterClass.QueryValidatorRequest.newBuilder().setValidatorAddr(mValOpAddress).build();
            QueryOuterClass.QueryValidatorResponse response = mStub.validator(request);

            this.result.isSuccess = true;
            this.result.resultData = response.getValidator();

        } catch (Exception e) {
            WLog.e("ValidatorInfoGrpcTask " + e.getMessage());
        }
        return result;
    }

}
