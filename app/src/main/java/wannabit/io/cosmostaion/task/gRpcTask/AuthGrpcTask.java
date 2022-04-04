package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_AUTH;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.concurrent.TimeUnit;

import cosmos.auth.v1beta1.QueryGrpc;
import cosmos.auth.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class AuthGrpcTask extends CommonTask {
    private final String address;
    private final QueryGrpc.QueryBlockingStub queryStub;

    public AuthGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String address) {
        super(app, listener);
        this.address = address;
        this.result.taskType = TASK_GRPC_FETCH_AUTH;
        this.queryStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(chain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(address).build();
            QueryOuterClass.QueryAccountResponse response = queryStub.account(request);
            this.result.isSuccess = true;
            this.result.resultData = response.getAccount();

        } catch (Exception e) {
            WLog.e("AuthGrpcTask " + e.getMessage());
        }
        return result;
    }
}