package wannabit.io.cosmostaion.task.gRpcTask;


import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_WITHDRAW_ADDRESS;

import cosmos.distribution.v1beta1.QueryGrpc;
import cosmos.distribution.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class WithdrawAddressGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mAddress;
    private QueryGrpc.QueryBlockingStub mStub;

    public WithdrawAddressGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String address) {
        super(app, listener);
        this.mChain = chain;
        this.mAddress = address;
        this.mResult.taskType = TASK_GRPC_FETCH_WITHDRAW_ADDRESS;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryDelegatorWithdrawAddressRequest request = QueryOuterClass.QueryDelegatorWithdrawAddressRequest.newBuilder().setDelegatorAddress(mAddress).build();
            QueryOuterClass.QueryDelegatorWithdrawAddressResponse response = mStub.delegatorWithdrawAddress(request);

            this.mResult.isSuccess = true;
            this.mResult.resultData = response.getWithdrawAddress();

        } catch (Exception e) { WLog.e( "WithdrawAddressGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
