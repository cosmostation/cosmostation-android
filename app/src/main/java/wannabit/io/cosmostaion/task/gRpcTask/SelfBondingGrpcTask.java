package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_SELF_BONDING;

import cosmos.staking.v1beta1.QueryGrpc;
import cosmos.staking.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class SelfBondingGrpcTask extends CommonTask {
    private final BaseChain mChain;
    private final String mValOpAddress;
    private final String mAddress;
    private final QueryGrpc.QueryBlockingStub mStub;

    public SelfBondingGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String opAddress, String address) {
        super(app, listener);
        this.mChain = chain;
        this.mValOpAddress = opAddress;
        this.mAddress = address;
        this.result.taskType = TASK_GRPC_FETCH_SELF_BONDING;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryDelegationRequest request = QueryOuterClass.QueryDelegationRequest.newBuilder().setValidatorAddr(mValOpAddress).setDelegatorAddr(mAddress).build();
            QueryOuterClass.QueryDelegationResponse response = mStub.delegation(request);
            this.result.isSuccess = true;
            this.result.resultData = response.getDelegationResponse();

        } catch (Exception e) {
            WLog.e("SelfBondingGrpcTask " + e.getMessage());
        }
        return result;
    }
}
