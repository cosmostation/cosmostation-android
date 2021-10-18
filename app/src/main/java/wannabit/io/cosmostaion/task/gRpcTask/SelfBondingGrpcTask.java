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

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_SELF_BONDING;

public class SelfBondingGrpcTask  extends CommonTask {
    private BaseChain   mChain;
    private String      mValOpAddress;
    private String      mAddress;
    private QueryGrpc.QueryBlockingStub mStub;

    public SelfBondingGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String opAddress, String address) {
        super(app, listener);
        this.mChain = chain;
        this.mValOpAddress = opAddress;
        this.mAddress = address;
        this.mResult.taskType = TASK_GRPC_FETCH_SELF_BONDING;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryDelegationRequest request = QueryOuterClass.QueryDelegationRequest.newBuilder().setValidatorAddr(mValOpAddress).setDelegatorAddr(mAddress).build();
            QueryOuterClass.QueryDelegationResponse response = mStub.delegation(request);
            this.mResult.isSuccess = true;
            this.mResult.resultData = response.getDelegationResponse();

        } catch (Exception e) { WLog.e( "SelfBondingGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
