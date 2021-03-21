package wannabit.io.cosmostaion.task.gRpcTask;

import cosmos.auth.v1beta1.QueryGrpc;
import cosmos.auth.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_AUTH;

public class AuthGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mAddress;
    private QueryGrpc.QueryBlockingStub mStub;

    public AuthGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String address) {
        super(app, listener);
        this.mChain = chain;
        this.mAddress = address;
        this.mResult.taskType = TASK_GRPC_FETCH_AUTH;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mAddress).build();
            QueryOuterClass.QueryAccountResponse response = mStub.account(request);
            this.mResult.isSuccess = true;
            this.mResult.resultData = response.getAccount();

        } catch (Exception e) { WLog.e( "AuthGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}