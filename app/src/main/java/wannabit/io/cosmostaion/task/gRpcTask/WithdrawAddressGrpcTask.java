package wannabit.io.cosmostaion.task.gRpcTask;


import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_WITHDRAW_ADDRESS;

import cosmos.distribution.v1beta1.QueryGrpc;
import cosmos.distribution.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class WithdrawAddressGrpcTask extends CommonTask {
    private BaseChain mChain;
    private Account mAccount;
    private QueryGrpc.QueryBlockingStub mStub;

    public WithdrawAddressGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mResult.taskType = TASK_GRPC_FETCH_WITHDRAW_ADDRESS;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryDelegatorWithdrawAddressRequest request = QueryOuterClass.QueryDelegatorWithdrawAddressRequest.newBuilder().setDelegatorAddress(mAccount.address).build();
            QueryOuterClass.QueryDelegatorWithdrawAddressResponse response = mStub.delegatorWithdrawAddress(request);
//            QueryOuterClass.QueryParamsRequest request = QueryOuterClass.QueryParamsRequest.newBuilder().build();
//            QueryOuterClass.QueryParamsResponse response = mStub.params(request);
//            WLog.w("getWithdrawAddress " + response.getWithdrawAddress());
//
            this.mResult.isSuccess = true;
            this.mResult.resultData = response.getWithdrawAddress();

        } catch (Exception e) {
            WLog.e("ParamMintGrpcTask " + e.getMessage());
        }
        return mResult;
    }
}
